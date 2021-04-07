package bluegill;

// High performance class to compliment the Phasor class
public class Rectangular implements Complex {

	private double real;
	private double imag;
	
	
	
	public Rectangular () {
		real = 0.0;
		imag = 0.0;
	}

	public Rectangular ( double real, double imag ) {
		this.real = real;
		this.imag = imag;
	}
	
	public Rectangular ( Phasor p ) {
		real = p.real();
		imag = p.imag();
	}
	
	

	public Complex add ( Complex c ) {
		real += c.real();
		imag += c.imag();
		return this;
	}
	
	public Complex subtract ( Complex c ) {
		real -= c.real();
		imag -= c.imag();
		return this;
	}
	
	public Complex multiply ( Complex other ) {
		// source: https://mathworld.wolfram.com/ComplexMultiplication.html
		double a = real;
		double b = imag;
		double c = other.real();
		double d = other.imag();
		double ac = a*c;
		double bd = b*d;
		double aPb = a + b;
		double cPd = c + d;
		double iPart = aPb*cPd;
		real = ac - bd;
		imag = iPart - ac - bd;
    return this;
	}
	
	public Complex divide ( Complex other ) {
		// source: https://mathworld.wolfram.com/ComplexDivision.html
		double a = real;
		double b = imag;
		double c = other.real();
		double d = other.imag();
		double c2 = c*c;
		double d2 = d*d;
		double ac = a*c;
		double bd = b*d;
		double bc = b*c;
		double ad = a*d;
		double den = c2 + d2;
		double rNum = ac + bd;
		double iNum = bc - ad;
		real = rNum/den;
		imag = iNum/den;
		return this;
	}
	
	public Complex conjugate ( ) {
		imag *= -1.0;
		return this;
	}
	
	
	public double magnitude ( ) {
		double r2 = real*real;
		double i2 = imag*imag;
		double sum = r2 + i2;
		return Math.sqrt( sum );
	}
	
	public double phase ( ) {
		return Math.atan2( imag, real );
	}
	
	public double real ( ) {
		return real;
	}
	
	public double imag ( ) {
		return imag;
	}
	
	
	// clone method because this is a mutable class
	
	public Rectangular clone () {
		return new Rectangular( real, imag );
	}
	
	
  public String toString () {
    return real()+" + ⅉ"+imag();
  }
  
  public static void main (String[] args) {
  	Complex c = new Rectangular( new Phasor( 1.0, Math.PI/4) );
  	System.out.println( "c: "+c );
  	System.out.println( "c+c: "+c.add( new Phasor(1.0, Math.PI/4) ) );

  	Rectangular c0 = (Rectangular) c;
  	Rectangular c1 = c0.clone();
  	System.out.println( "c1 <- c: "+c1 );
  	System.out.println( "c1*2: "+c1.multiply( new Phasor(2.0, 0.0 ) ) );
  	System.out.println( "c1/2: "+c1.divide( new Phasor(2.0, 0.0 ) ) );

  	System.out.println( "c: "+c.subtract( new Phasor(1.0, Math.PI/4) ) );
  	System.out.println( "c*2: "+c.multiply( new Phasor(2.0, 0.0) ) );
  	System.out.println( "conj(c*2): "+c.conjugate() );
  }

}
package bluegill;

import java.util.*;

public abstract class Integral extends TransferFunction<Double> implements SignalPath {

	public Integral ( List<Double> coef ) {
		super( coef );
	}

	///////// Abstract method /////////
	public abstract double f ( int t ); // f(coef(t),x(t))


	public double sample ( double x ) {
		x( x );
		
		double y = 0;
		for (int t=0; t<size(); t++)
			y += f( t ); // y = f(coef(t),x(t))
			
		y( y );
		return y;
	}

	private Double notNull ( Double test ) {
		return ( test == null ? 0.0 : test );
	}

	public Double coef ( int t ) {
		return notNull( super.coef(t) );
	}
	
	public Double x ( int t ) {
		return notNull( super.x(t) );
	}
	
	public Double y ( int t ) {
		return notNull( super.y(t) );
	}

}



class TestIntegral extends Integral {

	public TestIntegral ( List<Double> list ) {
		super( list );
	}

	public double f ( int t ) {
		return coef(t) * x(t);
	}
	
	public static void main (String[] args) {
		TestIntegral ti = new TestIntegral( new ArrayList<Double>(Arrays.asList(0.1,0.1,0.1,0.1,0.1,0.1)) );
		for (double i=0.0; i<10.0; i++) {
			System.out.println( ti.sample(i) );
		}
		System.out.println( ti );
	}
	
}
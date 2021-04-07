package bluegill;

import java.util.*;

public abstract class ComplexIntegral extends TransferFunction<Complex> implements ComplexSignalPath {

	public ComplexIntegral ( int size ) {
		super( size );
	}

	public ComplexIntegral ( List<Complex> coef ) {
		super( coef );
	}

	///////// Abstract method /////////
	public abstract Complex f ( int t ); // models: f(coef(t),x(t))
	
	///////// Abstract method /////////
	public abstract Complex zero (); // definition of zero value is up to child class

	public Complex sample ( Complex x ) {
		x( x );
		Complex y = zero();
		for (int t=0; t<size(); t++)
			y = y.add( f( t ) ); // models: y = integral[ f(coef(t),x(t)) ]dt
		y( y );
		return y;
	}
	
	private Complex notNull ( Complex test ) {
		return ( test == null ? zero() : test );
	}

	public Complex coef ( int t ) {
		return notNull( super.coef(t) );
	}
	
	public Complex x ( int t ) {
		return notNull( super.x(t) );
	}
	
	public Complex y ( int t ) {
		return notNull( super.y(t) );
	}
	
}


class TestComplexIntegral extends ComplexIntegral {

	public TestComplexIntegral ( int size ) {
		super( size );
	}

	public Complex zero () {
		return new Rectangular();
	}

	public Complex f ( int t ) {
		return x(t).multiply(coef(t));
	}
	
	public static void main (String[] args) {
		TestComplexIntegral tci = new TestComplexIntegral( 5 );
		for (double i=1.0; i<=10.0; i++) {
			System.out.println( tci.sample( new Phasor( i, 0.0 ) ) );
		}
		System.out.println( tci );
	}
	
}
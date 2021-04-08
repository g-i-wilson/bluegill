package bluegill;

import java.util.*;

public abstract class ComplexIntegral extends ComplexWindow {

	public ComplexIntegral ( int length ) {
		super( length );
	}

	public ComplexIntegral ( List<Complex> coef ) {
		super( coef );
	}

	public ComplexIntegral ( List<Complex> coef, double[] a ) {
		super( coef, a );
	}

	///////// Abstract method /////////
	public abstract Complex f ( int t ); // models: f(coef(t),x(t))
	
	@Override
	public Complex sample ( Complex x ) {
		x( x );
		Complex y = zero();
		for (int t=0; t<size(); t++) {
			y = y.add( f( t ) ); // models: y = integral[ f(coef(t),x(t)) ]dt
		}
		y( y );
		return y;
	}
	
}




class TestComplexIntegral extends ComplexIntegral {

	public TestComplexIntegral ( int len ) {
		super(len);
	}

	public Complex zero () {
		return new Rectangular(); // addition is faster with Rectangular
	}

	public Complex f ( int t ) {
		return coef(t).multiply(x(t)); // if coef(t) is Phasor, then multiplication is faster
	}
	
	public static void main (String[] args) {
		TestComplexIntegral tci = new TestComplexIntegral( 10 );
		for (double t=0.0; t<=10.0; t++) {
			System.out.println( "time "+t+": "+tci.sample( new Phasor( t, 0.0 ) ) );
		}
		System.out.println( "\n"+tci );
	}
	
}
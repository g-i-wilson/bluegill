package bluegill;

import java.util.*;

public abstract class Integral extends Window {

	public Integral ( int length ) {
		super( length );
	}

	public Integral ( List<Double> coef ) {
		super( coef );
	}

	public Integral ( int length, double[] a ) {
		super( length, a );
	}

	public Integral ( List<Double> coef, double[] a ) {
		super( coef, a );
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

}



class TestIntegral extends Integral {

	public TestIntegral ( int length ) {
		super( length );
	}

	public double f ( int t ) {
		return coef(t) * x(t);
	}
	
	public static void main (String[] args) {
		TestIntegral ti = new TestIntegral( 10 );
		for (double i=0.0; i<10.0; i++) {
			System.out.println( ti.sample(i) );
		}
		System.out.println( ti );
	}
	
}
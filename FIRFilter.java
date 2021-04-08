package bluegill;

import java.util.*;

public class FIRFilter extends Integral {

	public FIRFilter ( int filterLength ) {
		this( filterLength, Windows.BlackmanNuttall );
	}

	public FIRFilter ( int filterLength, double[] a ) {
		super( filterLength, a );
	}

	public FIRFilter ( List<Double> coef ) {
		this( coef, Windows.BlackmanNuttall );
	}

	public FIRFilter ( List<Double> coef, double[] a ) {
		super( coef, a );
	}

	public double f ( int t ) {
		return coef(t) * x(t);
	}

	// test
	public static void main (String[] args) {
		FIRFilter sig0 = new FIRFilter( 10 );
		for (int i=0; i<10; i++) {
			System.out.println( sig0.sample( 0.0 ) );
		}
		for (int i=0; i<10; i++) {
			System.out.println( sig0.sample( 0.0 ) );
			System.out.println( sig0.sample( 1.0 ) );
		}
		for (int i=0; i<20; i++) {
			System.out.println( sig0.sample( 0.0 ) );
		}
	}

}

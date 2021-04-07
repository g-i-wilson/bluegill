package bluegill;

import java.util.*;

public class Windows {

	private Windows () {} // disallow instantiation
	
	
	// coefficients
	
	public static final double[] Hann = new double[]{ 0.5, 0.5, 0.0, 0.0 };

	public static final double[] Blackman = new double[]{ 0.42, 0.5, 0.08, 0.0 };
	
	public static final double[] BlackmanNuttall = new double[]{ 0.3635819, 0.4891775, 0.1365995, 0.0106411 };
	
	
	// helper methods
	
	public static List<Complex> window ( int len ) {
		List<Complex> coef = new ArrayList<>();
		for (int i=0; i<len; i++) {
			coef.add( new Phasor( 1.0, 0.0 ) ); // multiplication is faster with Phasors
		}
		return window( coef );
	}

	public static List<Complex> window ( List<Complex> coef ) {
		return window( coef, BlackmanNuttall );
	}

	public static List<Complex> window ( List<Complex> coef, double[] a ) {
		int N = coef.size()-1;
		for (int n=0; n<=N; n++) {
			Complex window = new Phasor( // multiplication is faster with Phasors
				  a[0]
				- a[1]*Math.cos((2*Math.PI*n)/N)
				+ a[2]*Math.cos((4*Math.PI*n)/N)
				- a[3]*Math.cos((6*Math.PI*n)/N)
				, 0.0 // window coefficients are real
			);
			coef.set( n, window.multiply(coef.get(n)) );
		}
		return coef;
	}
	
}
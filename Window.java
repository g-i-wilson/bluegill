package bluegill;

import java.util.*;

public abstract class Window extends Integral {

	public static List<Double> unity ( int len ) {
		List<Double> coef = new ArrayList<>();
		for (int i=0; i<len; i++) {
			coef.add( 1.0 );
		}
		return coef;
	}

	public static List<Double> applyWindow ( List<Double> coef, double a0, double a1, double a2, double a3 ) {
		int N = coef.size()-1;
		for (int n=0; n<=N; n++) {
			double window =
				  a0
				- a1*Math.cos((2*Math.PI*n)/N)
				+ a2*Math.cos((4*Math.PI*n)/N)
				- a3*Math.cos((6*Math.PI*n)/N)
			;
			coef.set( n, window*coef.get(n) );
		}
		return coef;
	}

	// Blackman-Nuttall
	public Window ( int length ) {
		this( length, 0.3635819, 0.4891775, 0.1365995, 0.0106411 );
	}

	// Blackman-Nuttall
	public Window ( List<Double> coef ) {
		this( coef, 0.3635819, 0.4891775, 0.1365995, 0.0106411 );
	}


	public Window ( int filterLength, double a0, double a1, double a2, double a3 ) {
		super(
			applyWindow(
				unity( filterLength ), a0, a1, a2, a3
			)
		);
	}

	public Window ( List<Double> coef, double a0, double a1, double a2, double a3 ) {
		super(
			applyWindow(
				coef, a0, a1, a2, a3
			)
		);
	}

}

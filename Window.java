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

	public static List<Double> applyWindow ( List<Double> coef, double[] a ) {
		int N = coef.size()-1;
		for (int n=0; n<=N; n++) {
			double window =
				  a[0]
				- a[1]*Math.cos((2*Math.PI*n)/N)
				+ a[2]*Math.cos((4*Math.PI*n)/N)
				- a[3]*Math.cos((6*Math.PI*n)/N)
			;
			coef.set( n, window*coef.get(n) );
		}
		return coef;
	}

	public Window ( int length ) {
		this( length, Windows.BlackmanNuttall );
	}

	public Window ( List<Double> coef ) {
		this( coef, Windows.BlackmanNuttall );
	}


	public Window ( int filterLength, double[] a ) {
		super(
			applyWindow(
				unity( filterLength ),
				a
			)
		);
	}

	public Window ( List<Double> coef, double[] a ) {
		super(
			applyWindow(
				coef,
				a
			)
		);
	}

}

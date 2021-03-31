package bluegill;

import java.util.*;

public abstract class Window extends Integral {

	public static List<Double> unity ( int len ) {
		List<Double> coefs = new ArrayList<>();
		for (int i=0; i<len; i++) {
			coefs.add( 1.0 );
		}
		return coefs;
	}

	public static List<Double> applyWindow ( List<Double> coefs, double a0, double a1, double a2, double a3 ) {
		int N = coefs.size()-1;
		for (int n=0; n<=N; n++) {
			double window =
				  a0
				- a1*Math.cos((2*Math.PI*n)/N)
				+ a2*Math.cos((4*Math.PI*n)/N)
				- a3*Math.cos((6*Math.PI*n)/N)
			;
			coefs.set( n, window*coefs.get(n) );
		}
		return coefs;
	}

	// operation( ) is still abstract...
	public abstract double operation ( Double a, Double b );

	// Blackman-Nuttall
	public Window ( int filterLength ) {
		this( filterLength, 0.3635819, 0.4891775, 0.1365995, 0.0106411 );
	}

	public Window ( List<Double> coefs ) {
		this( coefs, 0.3635819, 0.4891775, 0.1365995, 0.0106411 );
	}


	public Window ( int filterLength, double a0, double a1, double a2, double a3 ) {
		super(
			applyWindow(
				unity( filterLength ), a0, a1, a2, a3
			)
		);
	}

	public Window ( List<Double> coefs, double a0, double a1, double a2, double a3 ) {
		super(
			applyWindow(
				coefs, a0, a1, a2, a3
			)
		);
	}

	public static void main (String[] args) {
		System.out.println("Hann Window:");
		Window hann = new Window( 17, 0.5, 0.5, 0.0, 0.0 );
		System.out.println( hann );
		System.out.println("Blackman-Nuttall Window (default):");
		Window bn = new Window( 33 );
		System.out.println( bn );
	}

}

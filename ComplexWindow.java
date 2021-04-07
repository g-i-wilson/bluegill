package bluegill;

import java.util.*;

public abstract class ComplexWindow extends ComplexIntegral {

	public static List<Complex> unity ( int len ) {
		List<Complex> coef = new ArrayList<>();
		for (int i=0; i<len; i++) {
			coef.add( new Phasor( 1.0, 0.0 ) ); // multiplication is faster with Phasors
		}
		return coef;
	}

	public static List<Complex> applyWindow ( List<Complex> coef, double[] a ) {
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

	public ComplexWindow ( int length ) {
		this( length, Windows.BlackmanNuttall );
	}

	public ComplexWindow ( List<Complex> coef ) {
		this( coef, Windows.BlackmanNuttall );
	}

	public ComplexWindow ( int filterLength, double[] a ) {
		super(
			applyWindow(
				unity( filterLength ),
				a
			)
		);
	}

	public ComplexWindow ( List<Complex> coef, double[] a ) {
		super(
			applyWindow(
				coef,
				a
			)
		);
	}

}


class TestComplexWindow extends ComplexWindow {

	public TestComplexWindow ( int size, double[] a ) {
		super(size, a);
	}
	
	public Complex zero () {
		return new Rectangular();
	}

	public Complex f ( int t ) {
		return coef(t).multiply(x(t));
	}
	
	public static void main (String[] args) {
		System.out.println( new TestComplexWindow( 5, Windows.Hann ) );
		System.out.println( new TestComplexWindow( 5, Windows.Blackman ) );
		System.out.println( new TestComplexWindow( 5, Windows.BlackmanNuttall ) );
	}
}
package bluegill;

import java.util.*;

public class Convolution extends Window {

	public Convolution ( List<Double> coefs ) {
		super( coefs );
	}

	public double operation ( int i ) {
		if (coef(i) > 0.0) {
			return Math.min( coef(i), x(i) );
		} else if (coef(i) < 0.0) {
			return Math.max( coef(i), x(i) );
		} else {
			return 0.0; // there's never a perfect 0 in floating point arithmetic...
		}
	}

	// test convolution
	public static void main (String[] args) {
		List<Double> const0 = new ArrayList<Double>(Arrays.asList(0.7,0.7,0.7,0.7,0.7,0.7,0.7,0.7));
		Convolution sig0 = new Convolution( const0 );
		for (int i=0; i<10; i++) {
			System.out.println( sig0.sample( 0.0 ) );
		}
		System.out.println( sig0.sample( 0.1 ) );
		for (int i=0; i<10; i++) {
			System.out.println( sig0.sample( 0.0 ) );
		}
		System.out.println( sig0.sample( 1.0 ) );
		for (int i=0; i<10; i++) {
			System.out.println( sig0.sample( 0.0 ) );
		}
	}

}

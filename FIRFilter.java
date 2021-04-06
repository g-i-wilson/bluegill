package bluegill;

import java.util.*;

public class FIRFilter extends Window {

	public FIRFilter ( int filterLength ) {
		super( filterLength );
	}

	public FIRFilter ( List<Double> coef ) {
		super( coef );
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

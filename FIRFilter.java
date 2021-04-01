package bluegill;

import java.util.*;

public class FIRFilter extends Window {

	public FIRFilter ( int filterLength ) {
		super( filterLength );
	}

	public FIRFilter ( List<Double> coef ) {
		super( coef );
	}

	public double operation ( int i ) {
		return coef(i) * x(i);
	}

	// test
	public static void main (String[] args) {
		FIRFilter sig0 = new FIRFilter( 5 );
		for (int i=0; i<10; i++) {
			System.out.println( sig0.sample( 0.0 ) );
		}
		for (int i=0; i<10; i++) {
			System.out.println( sig0.sample( 0.0 ) );
			System.out.println( sig0.sample( 1.0 ) );
		}
		for (int i=0; i<10; i++) {
			System.out.println( sig0.sample( 0.0 ) );
		}
	}

}

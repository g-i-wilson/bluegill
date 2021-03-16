package bluegill;

import java.util.*;

public class FIRFilter extends MovingOperation {

	public FIRFilter ( List<Double> coefs ) {
		super( coefs );
	}

	public double operation ( Double a, Double b ) {
		return a * b;
	}
	
	// test convolution
	public static void main (String[] args) {
		List<Double> const0 = new ArrayList<Double>(Arrays.asList(0.2,0.4,0.6,0.4,0.2));
		FIRFilter sig0 = new FIRFilter( const0 );
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
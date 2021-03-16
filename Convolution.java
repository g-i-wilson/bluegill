package bluegill;

import java.util.*;

public class Convolution extends MovingOperation {

	public Convolution ( List<Double> coefs ) {
		super( coefs );
	}

	public double operation ( Double a, Double b ) {
		return Math.min( a, b );
	}
	
	// test convolution
	public static void main (String[] args) {
		List<Double> const0 = new ArrayList<Double>(Arrays.asList(0.7,0.7,0.7,0.7));
		Convolution sig0 = new Convolution( const0 );
		for (int i=0; i<3; i++) {
			System.out.println( sig0.sample( 0.5 ) );
		}
		for (int i=0; i<3; i++) {
			System.out.println( sig0.sample( 0.0 ) );
		}
	}
	
}
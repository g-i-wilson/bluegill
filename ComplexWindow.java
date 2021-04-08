package bluegill;

import java.util.*;

public abstract class ComplexWindow extends TransferFunction<Complex> implements ComplexSignalPath {

	public static List<Complex> unity ( int len ) {
		List<Complex> coef = new ArrayList<>();
		for (int i=0; i<len; i++) {
			coef.add( new Phasor( 1.0, 0.0 ) ); // multiplication is faster with Phasors
		}
		return coef;
	}

	public static List<Complex> applyWindow ( List<Complex> coef, double[] a ) {
		List<Complex> newCoef = new ArrayList<>();
		double[] winCoef = Windows.create( a, coef.size() );
		for (int i=0; i<coef.size(); i++) {
			Complex winPhasor = new Phasor( winCoef[i], 0.0 ); // purely real
			newCoef.add( winPhasor.multiply( coef.get(i) ) );
		}
		return newCoef;
	}

	public ComplexWindow ( int length ) {
		super( unity( length) );
	}

	public ComplexWindow ( List<Complex> coef ) {
		super( coef );
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
	
	///////// Abstract method /////////
	public abstract Complex zero (); // definition of zero value is up to child class

	public Complex sample ( Complex x ) {
		x( x );
		for (int i=0; i<size(); i++)
			y( coef(i).multiply(x(i)) ); // in this example, y(i) is holding a "windowed" set of samples
		return x; // also keep passing the un-altered samples along...
	}

	private Complex notNull ( Complex test ) {
		return ( test == null ? zero() : test );
	}

	@Override
	public Complex coef ( int t ) {
		return notNull( super.coef(t) );
	}
	
	@Override
	public Complex x ( int t ) {
		return notNull( super.x(t) );
	}
	
	@Override
	public Complex y ( int t ) {
		return notNull( super.y(t) );
	}

}


class TestComplexWindow extends ComplexWindow {

	public TestComplexWindow ( int size, double[] a ) {
		super(size, a);
	}
	
	public Complex zero () {
		return new Phasor();
	}

	public static void main (String[] args) {
		TestComplexWindow a = new TestComplexWindow( 5, Windows.Hann );
		System.out.println( a );
		for (int i=0; i<10; i++) {
			a.sample( new Rectangular( i, 0 ) );
			System.out.println( a );
		}
	}
}
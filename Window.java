package bluegill;

import java.util.*;

public abstract class Window extends TransferFunction<Double> implements SignalPath {

	public static List<Double> unity ( int len ) {
		List<Double> coef = new ArrayList<>();
		for (int i=0; i<len; i++) {
			coef.add( 1.0 );
		}
		return coef;
	}

	public static List<Double> applyWindow ( List<Double> coef, double[] a ) {
		List<Double> newCoef = new ArrayList<>();
		double[] winCoef = Windows.create( a, coef.size() );
		for (int i=0; i<coef.size(); i++)
			newCoef.add( winCoef[i] * coef.get(i) );
		return newCoef;
	}

	public Window ( int length ) {
		super( unity(length) );
	}

	public Window ( List<Double> coef ) {
		super( coef );
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

	private Double notNull ( Double test ) {
		return ( test == null ? 0.0 : test );
	}

	public Double coef ( int t ) {
		return notNull( super.coef(t) );
	}
	
	public Double x ( int t ) {
		return notNull( super.x(t) );
	}
	
	public Double y ( int t ) {
		return notNull( super.y(t) );
	}

}

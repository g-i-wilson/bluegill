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
		List<Double> newCoef = new ArrayList<>();
		double[] winCoef = Windows.create( a, coef.size() );
		for (int i=0; i<coef.size(); i++)
			newCoef.add( winCoef[i] * coef.get(i) );
		return newCoef;
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

package bluegill;

import java.util.*;

public abstract class Integral implements SignalPath {

	private List<Double> coef;
	private List<Double> x;
	private List<Double> y;

	public Integral ( List<Double> coef ) {
		this.coef = coef;
		x = new ArrayList<Double>();
		y = new ArrayList<Double>();
	}

	public static void addShift ( List<Double> list, double sample, int maxSize ) {
		list.add( sample );
		if ( list.size() > maxSize ) list.remove( 0 );
	}

	public static void addShiftReverse ( List<Double> list, double sample, int maxSize ) {
		list.add( 0, sample );
		if ( list.size() > maxSize ) list.remove( list.size()-1 );
	}

	///////// Abstract method /////////
	public abstract double operation ( int i );

	public double integration ( int length ) {
		double sum = 0;
		for (int i=0; i<length; i++)
			sum += operation( i );
		return sum;
	}

	public double sample ( double sample ) {
		addShift( x, sample, coef.size() );
		double output = integration( coef.size() );
		addShift( y, output, coef.size() );
		return output;
	}

	public double coef ( int i ) {
		if (i>coef.size()-1 || i<0) return 0.0;
		return coef.get(i).doubleValue();
	}

	public double x ( int i ) {
		if (i>x.size()-1 || i<0) return 0.0;
		return x.get(i).doubleValue();
	}

	public double y ( int i ) {
		if (i>y.size()-1 || i<0) return 0.0;
		return y.get(i).doubleValue();
	}

	public String toString () {
		String str = "Coefficients: \n";
		for (Double d : coef) str += d+"\n";
		str += "Current x state:\n";
		for (Double d : x) str += d+"\n";
		str += "Current y state:\n";
		for (Double d : y) str += d+"\n";
		return str;
	}

}

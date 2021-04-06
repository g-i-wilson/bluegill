package bluegill;

import java.util.*;

public abstract class Integral extends TransferFunction<Double> implements SignalPath {

	public Integral ( List<Double> coef ) {
		super( coef );
	}

	///////// Abstract method /////////
	public abstract double f ( int t ); // f(coef(t),x(t))


	public double sample ( double x ) {
		x( x );
		
		double y = 0;
		for (int t=0; t<size(); t++)
			y += f( t ); // y = f(coef(t),x(t))
			
		y( y );
		return y;
	}

	public double coef ( int i ) {
		if (i>coef().size()-1 || i<0) return 0.0;
		return coef().get(i).doubleValue();
	}

	public double x ( int i ) {
		if (i>x().size()-1 || i<0) return 0.0;
		return x().get(i).doubleValue();
	}

	public double y ( int i ) {
		if (i>y().size()-1 || i<0) return 0.0;
		return y().get(i).doubleValue();
	}

	public String toString () {
		String str = "Coefficients: \n";
		for (Double d : coef()) str += d+"\n";
		str += "Current x state:\n";
		for (Double d : x()) str += d+"\n";
		str += "Current y state:\n";
		for (Double d : y()) str += d+"\n";
		return str;
	}

}



class TestIntegral extends Integral {

	public TestIntegral ( List<Double> list ) {
		super( list );
	}

	public double f ( int t ) {
		return coef(t) * x(t);
	}
	
	public static void main (String[] args) {
		TestIntegral ti = new TestIntegral( new ArrayList<Double>(Arrays.asList(0.1,0.1,0.1,0.1,0.1,0.1)) );
		for (double i=0.0; i<10.0; i++) {
			System.out.println( ti.sample(i) );
		}
		System.out.println( ti );
	}
	
}
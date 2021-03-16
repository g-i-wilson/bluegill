package bluegill;

import java.util.*;

public abstract class MovingOperation {

	private List<Double> coefs;
	private List<Double> signal;

	public MovingOperation ( List<Double> coefs ) {
		this.coefs = coefs;
		this.signal = new ArrayList<Double>();
	}
	
	public double sample ( double sample ) {
		signal.add( 0, sample );
		if (signal.size()>coefs.size()) signal.remove( signal.size()-1 );
		double output = 0;
		for (int i=0; i<signal.size(); i++) {
			output += operation( signal.get(i), coefs.get(i) );
		}
		return output;
	}
	
	public abstract double operation ( Double a, Double b );
	
	public String toString () {
		String str = "Coefficients: \n";
		for (Double d : coefs) str += d+"\n";
		str += "Current signal state:\n";
		for (Double d : signal) str += d+"\n";
		return str;
	}
	
}
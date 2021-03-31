package bluegill;

import java.util.*;

public abstract class Integral implements SignalPath {

	private List<Double> coefs;
	private List<Double> signal;

	public Integral ( List<Double> coefs ) {
		this.coefs = coefs;
		this.signal = new ArrayList<Double>();
	}

	public double sample ( double sample ) {
		signal.add( 0, sample );
		if (signal.size()>coefs.size()) signal.remove( signal.size()-1 );
		double output = 0;
		for (int i=0; i<signal.size(); i++) {
			output += operation( i );
		}
		return output;
	}

	public abstract double operation ( int i );

	public double coefs ( int i ) {
		return coefs.get(i).doubleValue();
	}

	public double signal ( int i ) {
		return signal.get(i).doubleValue();
	}

	public String toString () {
		String str = "Coefficients: \n";
		for (Double d : coefs) str += d+"\n";
		str += "Current signal state:\n";
		for (Double d : signal) str += d+"\n";
		return str;
	}

}

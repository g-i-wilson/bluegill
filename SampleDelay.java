package bluegill;

import java.util.*;

public class SampleDelay implements SignalPath {

	private List<Double> path;
	private int delay;
	
	public SampleDelay ( int delay ) {
		this.delay = delay >= 0 ? delay : 0;
		if (delay>0) path = new ArrayList<Double>();
	}
	
	public double sample ( double sample ) {
		if (delay>0) {
			path.add( sample );
			if (path.size()-1>delay) path.remove(0);
			return path.get(0);
		} else {
			return sample;
		}
	}
	
	public static void main (String[] args) {
		for (int i=0; i<3; i++) {
			SignalPath delay = new SampleDelay(i);
			System.out.println( delay.sample( 1.0 ) );
			System.out.println( delay.sample( 2.0 ) );
			System.out.println( delay.sample( 3.0 ) );
			System.out.println( delay.sample( 4.0 ) );
		}
	}
	
	public String toString () {
		return path.toString();
	}
	
}
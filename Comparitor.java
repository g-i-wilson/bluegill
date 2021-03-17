package bluegill;

public class Comparitor implements SignalPath {

	private double high;
	private double low;
	private int state;
	private double amplitude;

	public Comparitor () {
		this( 0.3, 0.7 );
	}

	public Comparitor ( double low, double high ) {
		this( low, high, 1.0 );
	}

	public Comparitor ( double low, double high, double amplitude ) {
		this( low, high, amplitude, 0 );
	}

	public Comparitor ( double low, double high, double amplitude, int state ) {
		this.high = high;
		this.low = low;
		this.amplitude = amplitude;
		this.state = state;
	}
	
	public double sample ( double sample ) {
		if (state == 1) {
			if (sample < low && sample > -low) 	state = 0;
			if (sample < -high) 								state = -1;
		} else if (state == -1) {
			if (sample < low && sample > -low) 	state = 0;
			if (sample > high) 									state = 1;
		} else {
			if (sample > high) 									state = 1;
			if (sample < -high) 								state = -1;
		}
		return amplitude*state;
	}
	
	public int state () {
		return state;
	}

}
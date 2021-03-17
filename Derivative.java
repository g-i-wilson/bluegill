package bluegill;

import java.util.*;

public class Derivative implements SignalPath {

	private double n0;
	private double n1;
	private double timeInterval;
	
	public Derivative () {
		this( 1.0 );
	}

	public Derivative ( double timeInterval ) {
		this( timeInterval, 0.0, 0.0 );
	}
	
	public Derivative ( double timeInterval, double n0, double n1 ) {
		this.timeInterval = timeInterval;
		this.n0 = n0;
		this.n1 = n1;
	}
	
	public double sample ( double n1 ) {
		n0 = this.n1;
		this.n1 = n1;
		return (n1 - n0)/timeInterval;
	}
	
	// test Derivative
	public static void main (String[] args) {
		SignalPath wf = new WindowFilter( 16 );
		QuadratureModulator qm = new QuadratureModulator( 16.0 );
		QuadratureDemodulator qd = new QuadratureDemodulator( 16.0 );
		SignalPath first = new Derivative( 1.0/8.0 );
		SignalPath second = new Derivative( 1.0/8.0 );
		SignalPath delayFirst = new SampleDelay( 1 );
		for (int i=0; i<50; i++) {
			double signal = qd.input( wf.sample( qm.phase( Math.PI/2 ) ) ).amplitude();
			double fd = first.sample( signal );
			double sd = second.sample( fd );
			System.out.println( signal+","+delayFirst.sample(fd)+","+sd );
		}
		for (int i=0; i<50; i++) {
			double signal = qd.input( wf.sample( qm.phase( Math.PI/2 )*0.2 ) ).amplitude();
			double fd = first.sample( signal );
			double sd = second.sample( fd );
			System.out.println( signal+","+delayFirst.sample(fd)+","+sd );
		}
	}

}
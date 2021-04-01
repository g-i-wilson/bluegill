package bluegill;

public class QuadratureDemodulator {

	private double samplesPerCycle;
	private double iPhaseOffset;
	private int	filterLength;

	private double i0;
	private double i1;
	private double q0;
	private double q1;
	private double amplitude;
	private double phase;
	private double frequency;

	private int t;

	private FIRFilter iFilter;
	private FIRFilter qFilter;


	public static double multReal (double aR, double aI, double bR, double bI) {
		return aR*bR - aI*bI;
	}

	public static double multImag (double aR, double aI, double bR, double bI) {
		return aR*bI + aI*bR;
	}

	public QuadratureDemodulator ( double samplesPerCycle ) {
		this( samplesPerCycle, (int)(samplesPerCycle*2), 0.0, 0.0, 0.0 );
	}

	public QuadratureDemodulator ( double samplesPerCycle, int filterLength ) {
		this( samplesPerCycle, filterLength, 0.0, 0.0, 0.0 );
	}

	public QuadratureDemodulator ( double samplesPerCycle, int filterLength, double iPhaseOffset, double iInit, double qInit ) {
		this.samplesPerCycle = samplesPerCycle;
		this.iPhaseOffset = iPhaseOffset;
		this.filterLength = filterLength;
		i0 = iInit;
		i1 = iInit;
		q0 = qInit;
		q1 = qInit;
		t = 0;
		iFilter = new FIRFilter( filterLength );
		qFilter = new FIRFilter( filterLength );
	}

	public QuadratureDemodulator input ( double sample ) {

		i1 = i0;
		q1 = q0;

		// Mixing and filtering
		i0 = iFilter.sample( sample * Math.sin( 2*Math.PI*(1/samplesPerCycle)*t - (iPhaseOffset            ) ) );
		q0 = qFilter.sample( sample * Math.sin( 2*Math.PI*(1/samplesPerCycle)*t - (iPhaseOffset + Math.PI/2) ) );

		amplitude = Math.sqrt( i0*i0 + q0*q0 );
		phase = Math.atan2( i0, q0 );
		frequency =
			Math.atan2(
				multReal(i0, q0, i1, -q1),
				multImag(i0, q0, i1, -q1)
	  	);

		t++;

		return this;

	}

	public double amplitude () {
		return amplitude;
	}

	public double phase () {
		return phase;
	}

	public double frequency () {
		return frequency;
	}

	// test QuadratureDemodulator
	public static void main (String[] args) {
		SignalPath filter = new FIRFilter( 16 );
		QuadratureModulator qm = new QuadratureModulator( 16.0 );
		QuadratureDemodulator qd = new QuadratureDemodulator( 16.0 );
		SignalPath se = new SignalEnvelope( 32 );
		System.out.println( se );
		for (int i=0; i<20; i++) {
			double modSample = qm.phase( Math.PI/2 ) * 0.1;
			double filteredSample = filter.sample( modSample );
			qd.input( filteredSample );
			System.out.println( modSample+","+filteredSample+","+qd.amplitude()+","+qd.phase()+","+se.sample(filteredSample) );
		}
		for (int i=0; i<50; i++) {
			double modSample = qm.phase( Math.PI/2 );
			double filteredSample = filter.sample( modSample );
			qd.input( filteredSample );
			System.out.println( modSample+","+filteredSample+","+qd.amplitude()+","+qd.phase()+","+se.sample(filteredSample) );
		}
		for (int i=0; i<50; i++) {
			double modSample = qm.phase( Math.PI/2 ) * 0.1;
			double filteredSample = filter.sample( modSample );
			qd.input( filteredSample );
			System.out.println( modSample+","+filteredSample+","+qd.amplitude()+","+qd.phase()+","+se.sample(filteredSample) );
		}
	}

}

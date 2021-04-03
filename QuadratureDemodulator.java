package bluegill;

public class QuadratureDemodulator implements PhasorProducer {

	private double samplesPerCycle;
	private double iPhaseOffset;
	private int	filterLength;

	private Phasor p0;
	private Phasor p1;
	private double amplitude;
	private double phase;
	private double frequency;

	private int t;

	private FIRFilter iFilter;
	private FIRFilter qFilter;


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
		p0 = new Phasor( iInit, qInit );
		p1 = new Phasor( iInit, qInit );
		t = 0;
		iFilter = new FIRFilter( filterLength );
		qFilter = new FIRFilter( filterLength );
	}

	public Phasor sample ( double sample ) {

		p0 = p1;

		// Mixing and filtering
		p1 = new Phasor(
			iFilter.sample( sample * Math.sin( 2*Math.PI*(1/samplesPerCycle)*t - (iPhaseOffset            ) ) ),
			qFilter.sample( sample * Math.sin( 2*Math.PI*(1/samplesPerCycle)*t - (iPhaseOffset + Math.PI/2) ) )
		);

		t++;

		return p1;
	}

	public double frequency () {
		return p1.multiply( p0.conjugate() ).phase();
	}

	// test QuadratureDemodulator
	public static void main (String[] args) {
		SignalPath filter = new FIRFilter( 16 );
		QuadratureModulator qm = new QuadratureModulator( 16.0 );
		QuadratureDemodulator qd = new QuadratureDemodulator( 16.0 );
		// SignalPath se = new SignalEnvelope( 16, 0.5, 0.5, 0.0, 0.0 );
		SignalPath se = new SignalEnvelope( 16 );
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
		for (int i=0; i<100; i++) {
			double modSample = qm.phase( Math.PI/2 ) * 0.1;
			double filteredSample = filter.sample( modSample );
			qd.input( filteredSample );
			System.out.println( modSample+","+filteredSample+","+qd.amplitude()+","+qd.phase()+","+se.sample(filteredSample) );
		}
	}

}

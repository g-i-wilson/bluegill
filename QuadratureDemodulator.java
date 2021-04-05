package bluegill;

public class QuadratureDemodulator implements PhasorProducer {

	private double frequency;

	private Phasor iqData;
	private Phasor localOscillator;

	private SignalPath iFilter;
	private SignalPath qFilter;


	public QuadratureDemodulator ( double samplesPerCycle ) {
		this( samplesPerCycle, (int)(samplesPerCycle*2), 0.0 );
	}

	public QuadratureDemodulator ( double samplesPerCycle, int filterLength ) {
		this( samplesPerCycle, filterLength, 0.0 );
	}

	public QuadratureDemodulator ( double samplesPerCycle, int filterLength, double loPhase ) {
		frequency = 1/samplesPerCycle;
		iqData = new Phasor();
		localOscillator = new Phasor( loPhase );
		iFilter = new FIRFilter( filterLength );
		qFilter = new FIRFilter( filterLength );
	}

	public Phasor sample ( double sample ) {

		// Get next LO sample
		localOscillator = localOscillator.relative( frequency );

		// Mixing and filtering
		iqData = Phasor.rectangular(
			iFilter.sample(
				sample * localOscillator.real()  // Mixing/filtering with LO real to get In-phase
			),
			qFilter.sample(
				sample * localOscillator.imag()  // Mixing/filtering with LO imaginary to get Quadrature
			),
			iqData // save reference to current data; can be retreived as iqData.past()
		);

		return iqData;
	}

	// test QuadratureDemodulator
	public static void main (String[] args) {
		PhasorConsumer qm = new QuadratureModulator( 16.0 );
		SignalPath filter = new FIRFilter( 16 );
		PhasorProducer qd = new QuadratureDemodulator( 16.0, 32, Math.random()*2*Math.PI );
		// SignalPath se = new SignalEnvelope( 16, 0.5, 0.5, 0.0, 0.0 );
		SignalPath se = new SignalEnvelope( 16 );
		System.out.println( se );

		Phasor noSignal = new Phasor(0.1, Math.PI/2);
		Phasor pulseSignal = new Phasor(1.0, Math.PI/2);

		boolean signal = false;
		for (int a=0; a<5; a++) {
			signal = ( signal ? false : true );
			for (int b=0; b<40; b++) {
				double modSample = qm.sample( signal ? noSignal : pulseSignal );
				double filteredSample = filter.sample( modSample );
				Phasor p = qd.sample( filteredSample );
				System.out.println( modSample+","+filteredSample+","+p.magnitude()+","+p.phase()+","+se.sample(filteredSample) );
			}
		}

	}

}

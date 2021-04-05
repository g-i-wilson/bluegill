package bluegill;

import java.util.*;

public class QuadratureModulator implements PhasorConsumer {

	private double frequency;
	private Phasor localOscillator;

	public QuadratureModulator ( double samplesPerCycle ) {
		frequency = 1/samplesPerCycle;
		localOscillator = new Phasor();
	}

	// Each sample is a "mix" of the modulation with the real (In-phase) and imaginary (Quadrature) signals, which are added together.
	// The modulation Phasor simply scales the I vs Q in the output.
	public double sample ( Phasor modulation ) {
		localOscillator = localOscillator.relative( frequency );
		return
			modulation.real() * localOscillator.real() +
			modulation.imag() * localOscillator.imag()
		;
	}

	// test QuadratureModulator
	public static void main (String[] args) {
		PhasorConsumer qm = new QuadratureModulator( 16 );
		Phasor p0 = new Phasor( Math.PI/2 );
		Phasor p1 = new Phasor( Math.PI*3/2 );

		for (int a=0; a<5; a++) {
			for (int i=0; i<20; i++) {
				System.out.println( qm.sample( p0 ) );
			}
			for (int i=0; i<20; i++) {
				System.out.println( qm.sample( p1 ) );
			}
		}

	}

}

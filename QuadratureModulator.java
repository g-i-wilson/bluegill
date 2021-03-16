package bluegill;

import java.util.*;

public class QuadratureModulator {

	private double samplesPerCycle;
	
	private WindowFilter phaseFilter;
	private double phase;
	private int t;

	public QuadratureModulator ( double samplesPerCycle ) {
		this.samplesPerCycle = samplesPerCycle;
		phaseFilter = new WindowFilter( (int)samplesPerCycle, 0.5, 0.5, 0.0, 0.0 );
		t = 0;
	}
	
	public double sample ( double phaseAngle ) {
		phase = phaseFilter.sample(phaseAngle);
		double iAmp = Math.cos( phase ) * Math.sin( 2*Math.PI*(1/samplesPerCycle)*t             );
		double qAmp = Math.sin( phase ) * Math.sin( 2*Math.PI*(1/samplesPerCycle)*t - Math.PI/2 );		
		t++;
		return iAmp + qAmp;
	}
	
	public double phase () {
		return phase;
	}
	
	// test QuadratureModulator
	public static void main (String[] args) {
		QuadratureModulator qm = new QuadratureModulator( 16 );
		for (int a=0; a<5; a++) {
			for (int i=0; i<20; i++) {
				System.out.println( qm.phase()+", "+qm.sample( Math.PI/2 ) );
			}
			for (int i=0; i<20; i++) {
				System.out.println( qm.phase()+", "+qm.sample( Math.PI*3/2 ) );
			}
		}
	}

}
package bluegill;

import java.util.*;

public class QuadratureModulator {

	private double samplesPerCycle;
	
	private int t;

	public QuadratureModulator ( double samplesPerCycle ) {
		this.samplesPerCycle = samplesPerCycle;
		t = 0;
	}
	
	public double sample ( double phaseAngle ) {
		double iAmp = Math.cos( phaseAngle ) * Math.sin( 2*Math.PI*(1/samplesPerCycle)*t             );
		double qAmp = Math.sin( phaseAngle ) * Math.sin( 2*Math.PI*(1/samplesPerCycle)*t - Math.PI/2 );		
		t++;
		return iAmp + qAmp;
	}
	
	// test QuadratureModulator
	public static void main (String[] args) {
		QuadratureModulator qm = new QuadratureModulator( 16 );
		for (int a=0; a<5; a++) {
			for (int i=0; i<20; i++) {
				System.out.println( qm.sample( Math.PI/2 ) );
			}
			for (int i=0; i<20; i++) {
				System.out.println( qm.sample( Math.PI*3/2 ) );
			}
		}
	}

}
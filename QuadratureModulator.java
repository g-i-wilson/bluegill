package bluegill;

import java.util.*;

public class QuadratureModulator {

	private double samplesPerCycle;
	
	private int t;

	public QuadratureModulator ( double samplesPerCycle ) {
		this.samplesPerCycle = samplesPerCycle;
		t = 0;
	}
	
	public double phase ( double angle ) {
		double iAmp = Math.cos( angle ) * Math.sin( 2*Math.PI*(1/samplesPerCycle)*t             );
		double qAmp = Math.sin( angle ) * Math.sin( 2*Math.PI*(1/samplesPerCycle)*t - Math.PI/2 );		
		t++;
		return iAmp + qAmp;
	}
	
	// test QuadratureModulator
	public static void main (String[] args) {
		QuadratureModulator qm = new QuadratureModulator( 16 );
		for (int a=0; a<5; a++) {
			for (int i=0; i<20; i++) {
				System.out.println( qm.phase( Math.PI/2 ) );
			}
			for (int i=0; i<20; i++) {
				System.out.println( qm.phase( Math.PI*3/2 ) );
			}
		}
	}

}
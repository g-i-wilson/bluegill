package bluegill;

import java.util.*;
import java.util.concurrent.atomic.*;

public class QuadratureSinusoids {

	private List<Double> i;
	private List<Double> q;
	AtomicInteger samples;
	AtomicBoolean addLock;
	double samplesPerCycle;
	double iPhaseOffset;
	
	private void addSample ( int t ) {
		i.add( Math.sin( 2*Math.PI*(1/samplesPerCycle)*t - iPhaseOffset ) );
		q.add( Math.sin( 2*Math.PI*(1/samplesPerCycle)*t - (iPhaseOffset + Math.PI/2) ) );
	}
	
	public QuadratureSinusoids ( double samplesPerCycle ) {
		this( samplesPerCycle, 0.0 );
	}
	
	public QuadratureSinusoids ( double samplesPerCycle, double iPhaseOffset ) {
		i = new ArrayList<>();
		q = new ArrayList<>();
		addLock = new AtomicBoolean();
		samples = new AtomicInteger();
		this.samplesPerCycle = samplesPerCycle;
		this.iPhaseOffset = iPhaseOffset;
	}
	
	private double getSample ( List<Double> list, int t ) {
		if (t>=0 && t<samples.get()) return list.get(t).doubleValue();
		else return 0.0;
	}
	
	public double i ( int t ) {
		return getSample( i, t );
	}
	
	public double q ( int t ) {
		return getSample( q, t );
	}
	
	public void needSamples ( int neededSamples ) {
		while (neededSamples > samples.get()) {
			if (addLock.compareAndSet(false, true)) {
				for (int t=samples.get(); t<neededSamples; t++) {
					addSample(t);
					samples.incrementAndGet(); // samples++
				}
				addLock.set(false);
			}
		}
	}
	
	public String toString () {
		String str = "";
		for (int i=0; i<samples.get(); i++) {
			str += i(i)+","+q(i)+"\n";
		}
		return str;
	}
	
	// Test QuadratureSinusoids
	public static void main (String[] args) {
		QuadratureSinusoids qs = new QuadratureSinusoids( 8 );
		qs.needSamples( 9 );
		System.out.println( qs+"\n" );
		qs.needSamples( 17 );
		System.out.println( qs );
	}

}
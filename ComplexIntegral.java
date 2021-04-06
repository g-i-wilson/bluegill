package bluegill;

import java.util.*;

public abstract class Integral<T> extends TransferFunction<T> implements ComplexSignalPath {

	public Integral ( List<T> coef ) {
		super( coef );
	}

	///////// Abstract method /////////
	public abstract T f ( T yProcess, int t ); // models: f(coef(t),x(t))
	
	///////// Abstract method /////////
	public abstract T blank (); // returns a "blank" T object

	public T sample ( T x ) {
		x( x );
		T yProcess = blank();
		for (int t=0; t<size(); t++)
			yProcess = f( yProcess, t ); // models: y = integral[ f(coef(t),x(t)) ]dt
		y( yProcess );
		return yProcess;
	}
	
	private T notNull ( T test ) {
		return ( test == null ? blank() : test );
	}

	public T coef ( int t ) {
		return notNull( super.coef(t) );
	}
	
	public T x ( int t ) {
		return notNull( super.x(t) );
	}
	
	public T y ( int t ) {
		return notNull( super.y(t) );
	}
	
}


class TestIntegral extends Integral<Double> {

	public TestIntegral ( List<Double> list ) {
		super( list );
	}

	public Double blank () {
		return new Double(0.0);
	}

	public Double f ( Double yProcess, int t ) {
		return yProcess.doubleValue() + x(t).doubleValue()*coef(t).doubleValue();
	}
	
	public static void main (String[] args) {
		TestIntegral ti = new TestIntegral( new ArrayList<Double>(Arrays.asList(0.1,0.1,0.1,0.1,0.1,0.1)) );
		for (double i=0.0; i<10.0; i++) {
			System.out.println( ti.sample(i) );
		}
		System.out.println( ti );
	}
	
}
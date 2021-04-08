package bluegill;

import java.util.*;

public class TransferFunction<T> {

	private List<T> coef;
	private List<T> x;
	private List<T> y;
	private int maxSize;
	private boolean forward;
	
	
	public TransferFunction ( int maxSize ) {
		this( maxSize, true );
	}
	
	public TransferFunction ( int maxSize, boolean forward ) {
		init( maxSize, null, forward );
	}
	
	public TransferFunction ( List<T> coef ) {
		this( coef, true );
	}

	public TransferFunction ( List<T> coef, boolean forward ) {
		init( coef.size(), coef, forward );
	}
	

	private void init ( int maxSize, List<T> coef, boolean forward ) {
		this.forward = forward;
		this.maxSize = maxSize;
		this.coef = coef;
		x = new ArrayList<T>();
		y = new ArrayList<T>();
	}
	

	protected void addShift ( List<T> list, T element ) {
		list.add( element );
		if ( list.size() > maxSize ) list.remove( 0 );
	}

	protected void addShiftReverse ( List<T> list, T element ) {
		list.add( 0, element );
		if ( list.size() > maxSize ) list.remove( list.size()-1 );
	}

	protected T getElement ( List<T> list, int i ) {
		if (list == null || i>list.size()-1 || i<0) return null;
		return list.get(i);
	}
	
	protected void addElement ( List<T> list, T element ) {
		if (forward) addShift( list, element );
		else addShiftReverse( list, element );
	}
	
	protected TransferFunction<T> coef ( T e ) {
		addElement( coef, e );
		return this;
	}
	
	protected TransferFunction<T> x ( T e ) {
		addElement( x, e );
		return this;
	}
	
	protected TransferFunction<T> y ( T e ) {
		addElement( y, e );
		return this;
	}
	
	public T coef ( int i ) {
		return getElement(coef, i);
	}
	
	public T x ( int i ) {
		return getElement(x, i);
	}
	
	public T y ( int i ) {
		return getElement(y, i);
	}
	
	public List<T> coef () {
		return coef;
	}
	
	public List<T> x () {
		return x;
	}
	
	public List<T> y () {
		return y;
	}
	
	public int size () {
		return maxSize;
	}
	
	
	public String toString () {
		String str = "Coefficients: \n";
		if (coef != null) for (T e : coef) str += e+"\n";
		str += "Current x state:\n";
		for (T e : x) str += e+"\n";
		str += "Current y state:\n";
		for (T e : y) str += e+"\n";
		return str;
	}
	
	public static void main (String[] args) {
		TransferFunction<Double> tf = new TransferFunction<>( 4 );
		tf
			.x( 1.1 )
			.x( 1.2 )
			.x( 1.3 )
			.x( 1.4 )
		;
		System.out.println( tf );
		tf = new TransferFunction<>( new ArrayList<Double>(Arrays.asList(0.7,0.7,0.7,0.7,0.7,0.7,0.7,0.7)) );
		System.out.println( tf );
	}

}
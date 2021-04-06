package bluegill;

public interface Complex {

	public Complex add ( Complex c );
	
	public Complex subtract ( Complex c );
	
	public Complex multiply ( Complex c );
	
	public Complex divide ( Complex c );
	
	public Complex conjugate ( );
	
	
	public double magnitude ( );
	
	public double angle ( );
	
	public double real ( );
	
	public double imag ( );
	
}
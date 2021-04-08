package bluegill;

public abstract class Windows {

	// disallow instantiation
	
	private Windows () {}
	

	// cosine-sum coefficients to create windows
	
	public static final double[] Hann = new double[]{ 0.5, 0.5, 0.0, 0.0 };

	public static final double[] Blackman = new double[]{ 0.42, 0.5, 0.08, 0.0 };
	
	public static final double[] BlackmanNuttall = new double[]{ 0.3635819, 0.4891775, 0.1365995, 0.0106411 };
	
	
	// create a set of actual window coefficients from the cosine-sum coefficients
	
	public static double[] create ( double[] a, int size ) {
		double[] window = new double[size];
		int N = size-1;
		for (int n=0; n<=N; n++) {
			window[n] =
				  a[0]
				- a[1]*Math.cos((2*Math.PI*n)/N)
				+ a[2]*Math.cos((4*Math.PI*n)/N)
				- a[3]*Math.cos((6*Math.PI*n)/N)
			;
		}
		return window;
	}
	
	
}
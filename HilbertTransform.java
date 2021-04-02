package bluegill;

import java.util.*;

public class HilbertTransform extends Convolution {

  public static List<Double> computeHilbert ( int length ) {
    List<Double> hilbert = new ArrayList<>();
    int N = length-1;
    for (int n=0; n<=N; n++) {
      double h;
      double t = (double)n-((double)N/2);
      if (t > 0.25 || t < -0.25) { // t will either be very near an integer (x.000...) or very near half (x.500...)
        double tscaled = t * (Math.PI/N);  // scale to range of -pi..pi
        hilbert.add( 1/(Math.PI*tscaled) );
      } else {
        hilbert.add( 0.0 ); // actually is *indeterminate*, since this point lies "halfway" between +/- infinity
      }
    }
    return hilbert;
  }

  public HilbertTransform ( int length ) {
    super( computeHilbert(length) );
  }

  public HilbertTransform ( int length, double a0, double a1, double a2, double a3 ) {
    super( computeHilbert(length), a0, a1, a2, a3 );
  }

}

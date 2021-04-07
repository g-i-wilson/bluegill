package bluegill;

public class SignalEnvelope extends HilbertTransform {

  public SignalEnvelope ( int length ) {
    super( length );
  }

  public SignalEnvelope ( int length, double[] a ) {
    super( length, a );
  }

  @Override
  public double sample ( double i ) {
    double q = super.sample( i ); // HilbertTransform
    return Math.sqrt( i*i + q*q ); // vector amplitude
  }

}

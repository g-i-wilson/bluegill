package bluegill;

public class SignalEnvelope extends HilbertTransform {

  public SignalEnvelope ( int length ) {
    super( length );
  }

  public SignalEnvelope ( int length, double a0, double a1, double a2, double a3 ) {
    super( length, a0, a1, a2, a3 );
  }

  @Override
  public double sample ( double i ) {
    double q = super.sample( i ); // HilbertTransform
    return Math.sqrt( i*i + q*q ); // vector amplitude
  }

}

package bluegill;

public class SignalEnvelope implements ComplexProducer {

	private HilbertTransform ht;

  public SignalEnvelope ( int length ) {
    ht = new HilbertTransform( length );
  }

  public SignalEnvelope ( int length, double[] a ) {
    ht = new HilbertTransform( length, a );
  }

  public Complex sample ( double real ) {
  	double imag = ht.sample( real );
  	return new Rectangular( real, imag );
  }

}

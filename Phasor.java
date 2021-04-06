package bluegill;

public class Phasor implements Complex {

  private double magnitude;
  private double phase;

  private Phasor past;

  private static double normalizedPhase ( double phase ) {
    while ( phase > 2*Math.PI ) {
      phase -= 2*Math.PI;
    }
    while ( phase < -2*Math.PI ) {
      phase += 2*Math.PI;
    }
    return phase;
  }

  public Phasor () { // real unit vector
    this( 0.0 );
  }

  public Phasor ( double phase ) { // unit vector
    this( 1.0, phase );
  }

  public Phasor ( double magnitude, double phase ) { // vector
    this( magnitude, phase, null );
  }

  public Phasor ( double magnitude, double phase, Phasor past ) { // vector & reference to past
    this.magnitude = magnitude;
    this.phase = normalizedPhase( phase );
    this.past = past;
  }

  public static Phasor rectangular ( double real, double imag ) {
    return rectangular( real, imag, null );
  }

  public static Phasor rectangular ( double real, double imag, Phasor past ) {
    return new Phasor(
      Math.sqrt( Math.pow(real,2) + Math.pow(imag,2) ),
      Math.atan2( real, imag ),
      past
    );
  }

  public double real () {
    return magnitude * Math.cos(phase);
  }

  public double imag () {
    return magnitude * Math.sin(phase);
  }

  public Phasor multiply ( Phasor b ) {
    return new Phasor( magnitude()*b.magnitude(), phase()+b.phase() );
  }

  public Phasor divide ( Phasor b ) {
    return new Phasor( magnitude()/b.magnitude(), phase()-b.phase() );
  }

  public Phasor conjugate () {
    return new Phasor( magnitude(), -phase() );
  }

  public Phasor add ( Phasor b ) {
    return rectangular(
      real()+b.real(),
      imag()+b.imag()
    );
  }

  public Phasor subtract ( Phasor b ) {
    return rectangular(
      real()-b.real(),
      imag()-b.imag()
    );
  }

  public double magnitude () {
    return magnitude;
  }

  public double phase () {
    return phase;
  }

  public double angle () {
    return phase;
  }

  public Phasor past () {
    return past;
  }

  public double frequency () {
    return frequency( past != null ? past : this );
  }

  public double frequency ( Phasor p ) {
    // see https://en.wikipedia.org/wiki/Instantaneous_phase_and_frequency
    return multiply( p.conjugate() ).phase() / (2*Math.PI);
  }

  public Phasor relative ( double frequency ) {
    return relative( frequency, 1 );
  }

  public Phasor relative ( double frequency, int time ) {
    return relative( frequency, time, 0.0 );
  }

  public Phasor relative ( double frequency, int time, double phaseOffset ) {
    return new Phasor( magnitude, 2*Math.PI*frequency*time + phase + phaseOffset, this );
  }

  public String pretty () {
    return magnitude+" ∠ "+(phase/Math.PI)+"π, "+real()+" + ⅉ"+imag();
  }

  public String toString () {
    return magnitude+","+phase+","+real()+","+imag();
  }

  // test Phasor
  public static void main (String[] args) {
    Phasor p0 = new Phasor( 2.0, 0.0 );
    System.out.println( p0 );
    Phasor p1 = new Phasor( p0.magnitude(), (1.0/16.0)*(2*Math.PI), p0 );
    System.out.println( p1 );
    double f = p1.frequency();
    System.out.println( "frequency: "+f );
    for (int t=0; t<16; t++) {
      Phasor p = p0.relative( f, t );
      System.out.println( p );
    }
    for (int t=0; t<16; t++) {
      Phasor p = p0.relative( f, t, 2*Math.PI/4 );
      System.out.println( p );
    }
  }

}

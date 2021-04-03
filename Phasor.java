package bluegill;

public class Phasor {

  private double r;
  private double i;

  public Phasor ( double r, double i ) {
    this.r = r;
    this.i = i;
  }

  public double r () {
    return r;
  }

  public double i () {
    return i;
  }

  public Phasor multiply ( Phasor b ) {
    return new Phasor(
      r*b.r() - i*b.i(), // r part
      r*b.i() + i*b.r()  // i part
    );
  }

  public Phasor conjugate () {
    return new Phasor( r, -i );
  }

  public Phasor add ( Phasor b ) {
    return new Phasor(
      r + b.r(), // r part
      i + b.i()  // i part
    );
  }

  public double magnitude () {
    return Math.sqrt( Math.pow(r,2) + Math.pow(i,2) );
  }

  public double phase () {
    return Math.atan2( r, i );
  }

}

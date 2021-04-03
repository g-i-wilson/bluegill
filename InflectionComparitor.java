package bluegill;

public class InflectionComparitor extends Comparitor {

  private boolean outsideZero;
  private Derivative firstDer;
  private Derivative secondDer;
  private Comparitor firstDerComp;
  private Comparitor secondDerComp;

  public void init () {
    firstDer = new Derivative();
    secondDer = new Derivative();
    firstDerComp = new Comparitor( low(), high() );
    secondDerComp = new Comparitor( low(), low()*2 );
    outsideZero = true;
  }

  public InflectionComparitor () {
    super( 0.1, 1.0 );
    init();
  }

  public InflectionComparitor ( double low, double high ) {
    super( low, high );
    init();
  }

  public InflectionComparitor ( double low, double high, double amplitude ) {
    super( low, high, amplitude );
    init();
  }

  public InflectionComparitor ( Comparitor comp ) {
    super( (comp.high()-comp.low())/10, comp.high()-comp.low(), comp.amplitude(), comp.state() );
    init();
  }

  @Override
  public state ( double sample ) {
    int slopeState = firstDirComp.state( firstDer.sample( sample ) );
    int curveState = secondDerComp.state( secondDer.sample( slope ) );
    if (outsideZero && curveState == 0) { // state changes can only occur when curvature first transitions into zero boundary
      outsideZero = false;
      if (state() == 1) {
  			if (slopeState == -1) state(0);
  		} else {
  			if (slopeState == 1) state(1);
  		}
    } else if (!outsideZero && curveState != 0) {
      outsideZero = true;
    }
		return state();
  }

}

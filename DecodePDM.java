package bluegill;

public class DecodePDM extends Thread implements SignalPath {

	private DoubleBuffer samples;
	private FIRFilter pdmFilter;

	public DecodePDM ( int bufferSize ) {
		pdmFilter = new FIRFilter(  );
		samples = DoubleBuffer.allocate(bufferSize);
		start();
	}

	public run () {
		int input = System.in.read();
		if (input == -1) {
			sleep(1);
		} else {
			int msb = input & 0x80;
			if (msb == 0x08) {
				
		}
	}
	
	public double sample ( double sample ) {
		
	}
	

}
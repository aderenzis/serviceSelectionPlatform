package me.domain.timeouts;

/**
 * @author  andres
 */
public class Timeout {

	public static final int PERCENTAGE = 1;
	public static final int ABSOLUTE = 2;
	private double timeout;
	private int mode;

	public Timeout(double t) {
		this.timeout=t;
	}

	public double getTimeout() {
		return timeout;
	}

	public void setMode(int mode) {
		this.mode=mode;
	}

	public int getMode() {
		return mode;
	}


}

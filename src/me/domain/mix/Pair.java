package me.domain.mix;


/**
 * @author  andres
 */
public class Pair {
	private String version;
	private int timesUsed;

	public Pair(String version) {
		this.version=version;
		this.timesUsed=0;
	}

	public String getVersion() {
		return this.version;
	}

	public int getTimesUsed() {
		return this.timesUsed;
	}

	public void visit() {
		this.timesUsed++;
	}


}

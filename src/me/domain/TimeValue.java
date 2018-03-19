package me.domain;

/**
 * @author  andres
 */
public class TimeValue implements ITableValue {

	private long mutantTime;
	private boolean killed;

	public TimeValue(boolean killed, long mutantTime) {
		this.killed=killed;
		this.mutantTime=mutantTime;
	}

	public boolean isKilled() {
		return this.killed;
	}

	public String toString() {
		return (this.killed ? "X" : "") + "\t" + this.mutantTime;
	}

	public long getTime() {
		return this.mutantTime;
	}

	public String toHTML() {
		return "<td>" + (this.killed ? "X" : "") + "</td><td>" + this.mutantTime + "</td>";
	}
}

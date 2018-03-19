package me.domain;

/**
 * @author  andres
 */
public class KillValue implements ITableValue {
	
	private boolean killed;

	public KillValue(boolean killed) {
		this.killed=killed;
	}

	public boolean isKilled() {
		return this.killed;
	}

	public String toString() {
		return (this.killed ? "X" : "");
	}

	public String toHTML() {
		return "<td>" + this.toString() + "</td>";
	}

}

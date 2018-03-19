package testooj3.domain.algorithms.pairwise;

/**
 * @author  andres
 */
public class Pair {
	protected byte a;
	protected byte b;
	protected int weight;
	
	public Pair(byte a, byte b) {
		this.a=a;
		this.b=b;
		this.weight=0;
	}
			
	public String toString() {
		return "(" + a + ", " + b + ") -> " + this.weight;
	}

	public int weight() {
		return this.weight;
	}

	public byte getA() {
		return a;
	}

	public byte getB() {
		return b;
	}

	public void increaseWeight() {
		this.weight=weight+1;
	}

}

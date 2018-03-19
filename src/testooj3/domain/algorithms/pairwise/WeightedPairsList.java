package testooj3.domain.algorithms.pairwise;

import java.util.Vector;

/**
 * @author  andres
 */
public class WeightedPairsList {
	private Vector<Pair> pairs;

	public WeightedPairsList() {
		this.pairs=new Vector<Pair>();
	}

	public void add(Pair par) {
		this.pairs.add(par);
	}

	public int size() {
		return pairs.size();
	}

	public Vector<Pair> getPairs() {
		return this.pairs;
	}

	public void remove(Pair p) {
		this.pairs.remove(p);
	}

	public Pair get(int i) {
		return this.pairs.get(i);
	}

}

package testooj3.domain.algorithms.pairwise;

import java.util.Vector;

/**
 * @author  andres
 */
public class VectorPairList implements IPairList {
	private Vector<Pair> pairs=new Vector<Pair>();
	
	public void add(Pair par) {
		this.pairs.add(par);
	}

	public Pair get(int weight) {
		for (int i=0; i<pairs.size(); i++) {
			Pair p=this.pairs.get(i);
			if (p.weight==weight)
				return p;
		}
		return null;
	}

	public Vector<Pair> getPairs() {
		return this.pairs;
	}

	public void increaseWeight(byte a, byte b) {
		for (int i=0; i<this.pairs.size(); i++) {
			Pair p=this.pairs.get(i);
			if (p.getA()==a && p.getB()==b) {
				p.increaseWeight();
				return;
			}
		}
	}

}

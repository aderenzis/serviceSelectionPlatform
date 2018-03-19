package testooj3.domain.algorithms.pairwise;

import java.util.Vector;

public class WeightedPairsListCollection implements IPairList {
	private Vector<WeightedPairsList> lists; 
	
	public WeightedPairsListCollection() {
		this.lists=new Vector<WeightedPairsList>();
	}

	/* (non-Javadoc)
	 * @see testooj3.domain.algorithms.pairwise.IPairList#add(testooj3.domain.algorithms.pairwise.Pair)
	 */
	public void add(Pair par) {
		WeightedPairsList wpl;
		if (par.weight()>=lists.size()) {
			wpl=new WeightedPairsList();
			this.lists.add(wpl);
		} else wpl=lists.get(par.weight());
		wpl.add(par);
	}

	/* (non-Javadoc)
	 * @see testooj3.domain.algorithms.pairwise.IPairList#get(int)
	 */
	public Pair get(int weight) {
		if (this.lists.size()<weight) return null;
		WeightedPairsList wpl=this.lists.get(weight);
		if (wpl.size()==0) return null;
		return wpl.get(0);
	}

	/* (non-Javadoc)
	 * @see testooj3.domain.algorithms.pairwise.IPairList#getPairs()
	 */
	public Vector<Pair> getPairs() {
		Vector<Pair> result=new Vector<Pair>();
		for (int i=0; i<this.lists.size(); i++) {
			WeightedPairsList wpl=this.lists.get(i);
			result.addAll(wpl.getPairs());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see testooj3.domain.algorithms.pairwise.IPairList#increaseWeight(byte, byte)
	 */
	public void increaseWeight(byte a, byte b) {
		Vector<Pair> elements=this.getPairs();
		Pair p=null;
		for (int i=0; i<elements.size(); i++) {
			p=elements.get(i);
			if (p.getA()==a && p.getB()==b)
				break;
		}
		WeightedPairsList wpl=this.lists.get(p.weight);
		wpl.remove(p);
		p.increaseWeight();
		this.add(p);
	}
}

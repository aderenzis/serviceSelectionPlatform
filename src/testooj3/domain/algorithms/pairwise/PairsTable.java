package testooj3.domain.algorithms.pairwise;

import java.util.Vector;

/**
 * @author  andres
 */
public class PairsTable {
	protected IPairList listsCollection;
	protected int indexA;
	protected int indexB;

	public PairsTable(int numeroDeValoresA, int numeroDeValoresB) {
		this.listsCollection=new VectorPairList();
		for (byte i=0; i<numeroDeValoresA; i++) {
			for (byte j=0; j<numeroDeValoresB; j++) {
				Pair par=new Pair(i, j);
				this.listsCollection.add(par);
			}
		}
	}
	
	public String toString() {
		String r="";
		r+=this.indexA + "/" + this.indexB + "\n";
		Vector<Pair> elements=this.getPairs();
		for (int i=0; i<elements.size(); i++) {
			Pair p=elements.get(i);
			r+=p.toString() + "\n";
		}
		r+="\n\n";
		return r;
	}

	public void setIndexA(int a) {
		this.indexA=a;
	}

	public void setIndexB(int b) {
		this.indexB=b;
	}

	public Pair getPairWithWeight(int weight) {
		/*if (this.lists.size()<weight) return null;
		WeightedPairsList wpl=this.lists.get(weight);
		if (wpl.size()==0) return null;
		return wpl.get(0);*/
		return this.listsCollection.get(weight);
	}

	public int getIndexA() {
		return this.indexA;
	}
	
	public int getIndexB() {
		return this.indexB;
	}
	
	public int size() {
		return this.getPairs().size();
	}

	public Pair getPair(int i) {
		return this.getPairs().get(i);
	}

	public int weightOfThePairs(ByteTableEntry entry) {
		Vector<Pair> elements=getPairs();
		for (int i=0; i<elements.size(); i++) {
			Pair auxi=elements.get(i);
			if (auxi.getA()==entry.getTestValue(this.indexA) && auxi.getB()==entry.getTestValue(this.indexB))
				return auxi.weight();
		}
		return 0;
	}

	protected Vector<Pair> getPairs() {
		Vector<Pair> result=this.listsCollection.getPairs();
		return result;
	}

	public void increaseWeightOfPair(byte a, byte b) {
		this.listsCollection.increaseWeight(a, b);
	}

}

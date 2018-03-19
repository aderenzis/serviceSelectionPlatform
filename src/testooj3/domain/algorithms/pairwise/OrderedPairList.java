package testooj3.domain.algorithms.pairwise;

import java.util.Vector;

/**
 * @author  andres
 */
public class OrderedPairList implements IPairList {
	private Vector<Pair> pairs;
	
	public OrderedPairList() {
		this.pairs=new Vector<Pair>();
	}

	public void add(Pair par) {
		int min=0;
		int max=pairs.size()-1;
		int mid;
		Pair p;
		if (this.pairs.size()==0) {
			this.pairs.add(par);
			return;
		}
		boolean insertado=false;
		while (!insertado) {
			mid=(max+min)/2;
			p=this.pairs.get(mid);
			if (par.weight==p.weight) {
				this.pairs.insertElementAt(par, mid);
				insertado=true;
			} else if (par.weight<p.weight) {
				max=mid-1;
			} else {
				if (mid==this.pairs.size()-1) {
					this.pairs.add(par);
					insertado=true;
				} else {
					min=mid+1;
				}
			}
		}
	}

	public Pair get(int weight) {
		int min=0;
		int max=pairs.size()-1;
		int mid;
		Pair p=null;
		boolean encontrado=false;
		while (!encontrado) {
			mid=(max+min)/2;
			Pair par=this.pairs.get(mid);
			if (par.weight==weight) {
				p=par;
				encontrado=true;
			} else if (par.weight>weight) {
				if (mid==0) {
					encontrado=true;
				} else {
					max=mid-1;
				}
			} else {
				min=mid+1;
			}
		}
		return p;
	}

	public Vector<Pair> getPairs() {
		return this.pairs;
	}

	public void increaseWeight(byte a, byte b) {
		Pair p=null;
		int i;
		for (i=0; i<this.pairs.size(); i++) {
			p=this.pairs.get(i);
			if (p.getA()==a && p.getB()==b)
				break;
		}
		this.pairs.remove(i);
		p.increaseWeight();
		this.add(p);
	}

}

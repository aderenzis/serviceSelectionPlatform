package testooj3.domain.algorithms.pairwise;

import java.util.Vector;

public interface IPairList {

	public abstract void add(Pair par);

	public abstract Pair get(int weight);

	public abstract Vector<Pair> getPairs();

	public abstract void increaseWeight(byte a, byte b);

}
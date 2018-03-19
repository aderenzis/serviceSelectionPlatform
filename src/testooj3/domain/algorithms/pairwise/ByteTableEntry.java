package testooj3.domain.algorithms.pairwise;

/**
 * @author  andres
 */
public class ByteTableEntry {
	private byte[] combination;
	private String binaryCode;

	public ByteTableEntry(int numeroDeParametros) {
		this.combination=new byte[numeroDeParametros];
	}

	public String toString() {
		String r="{";
		for (int i=0; i<this.combination.length; i++) {
			r+=this.combination[i] + ", ";
		}
		if (r.endsWith(", "))
			r=r.substring(0, r.length()-2).trim();
		return r + "}";
	}

	public boolean contains(Pair p, int indexA, int indexB) {
		if (this.combination[indexA]==p.getA() && this.combination[indexB]==p.getB())
			return true;
		return false;
	}

	public int weightOfPairs(PairsTable[] pptt) {
		int result=0;
		for (int i=0; i<pptt.length; i++) {
			PairsTable pt=pptt[i];
			result+=pt.weightOfThePairs(this);
		}
		return result;
	}

	public void visitPairs(PairsTable[] pptt) {
		int cont=0;
		for (int i=0; i<this.combination.length; i++) {
			for (int j=i+1; j<this.combination.length; j++) {
				PairsTable pt=pptt[cont++];
				pt.increaseWeightOfPair(this.combination[i], this.combination[j]);
			}
		}
	}

	public byte getTestValue(int i) {
		return this.combination[i];
	}

	public void setValue(int pos, byte testValuePosition) {
		this.combination[pos]=testValuePosition;
	}

	public void setBinaryCode(String binaryCode) {
		this.binaryCode=binaryCode;
	}
	
	public String getBinaryCode() {
		return this.binaryCode;
	}
	
	public int length() {
		return this.combination.length;
	}
}

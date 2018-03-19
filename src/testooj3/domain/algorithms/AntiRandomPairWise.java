package testooj3.domain.algorithms;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import testooj3.domain.TParameter;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.algorithms.pairwise.ByteTableEntry;
import testooj3.domain.algorithms.pairwise.PairsTable;

public class AntiRandomPairWise extends AbstractPairWise {
	private int numeroDeCombinaciones;
	
	@Override
	public Vector<TestCase> getTestCases(TestTemplate template) throws Exception {
		numeroDeCombinaciones=template.getNumberOfCombinations();
		Vector<TParameter> parametros = this.getParameters();
		PairsTable[] pptt=this.getPairsTables(template);
		int[] divisores=calculaDivisores(parametros);
		int[] numberOfValuesPerParameter=numberOfValuesPerParameter(parametros);
		Hashtable<Integer, ByteTableEntry> selectedCombinations=new Hashtable<Integer, ByteTableEntry>();
		ByteTableEntry te=this.getCombination(numberOfValuesPerParameter, divisores, 0);
		te.setBinaryCode(this.getBinaryCode(0, numeroDeCombinaciones));
		te.visitPairs(pptt);
		selectedCombinations.put(0, te);
		PairsTable[] auxi={null};
		int[] combinationNumber=new int[1];
		while ((parDePesoCero(pptt, auxi))!=null) {
			te=getCombinationWithMaximumDistance(selectedCombinations, numberOfValuesPerParameter, divisores, combinationNumber);
			te.visitPairs(pptt);
			selectedCombinations.put(combinationNumber[0], te);
		}
		return getTestCases(template, pptt, selectedCombinations);
	}



	private ByteTableEntry getCombinationWithMaximumDistance(Hashtable<Integer, ByteTableEntry> selectedCombinations, int[] numberOfValuesPerParameter, int[] divisores, int[] combinationNumber) {
		int maxDistance=Integer.MIN_VALUE;
		ByteTableEntry selected=null;
		for (int i=0; i<this.numeroDeCombinaciones; i++) {
			if (selectedCombinations.get(i)==null) {
				String binaryCode=getBinaryCode(i, numeroDeCombinaciones);
				int distance=hammingDistance(binaryCode, selectedCombinations);
				if (distance>maxDistance) {
					maxDistance=distance;
					combinationNumber[0]=i;
					selected=this.getCombination(numberOfValuesPerParameter, divisores, i);
				}
			}
		}
		selected.setBinaryCode(this.getBinaryCode(combinationNumber[0], numeroDeCombinaciones));
		return selected;
	}

	private int hammingDistance(String binaryCode, Hashtable<Integer, ByteTableEntry> selectedCombinations) {
		Enumeration<ByteTableEntry> e=selectedCombinations.elements();
		int distancia=0;
		while (e.hasMoreElements()) {
			ByteTableEntry te=e.nextElement();
			distancia+=hammingDistance(te.getBinaryCode(), binaryCode);
		}
		return distancia;
	}

	private int hammingDistance(String a, String b) {
		int result=0;
		for (int i=0; i<a.length(); i++) {
			if (a.charAt(i)!=b.charAt(i))
				result++;
		}
		return result;
	}

	private String getBinaryCode(int combinationNumber, int numeroDeCombinaciones) {
		double bitsEstrictos=Math.log(numeroDeCombinaciones)/Math.log(2);
		double bitsNecesarios=Math.round(bitsEstrictos);
		if (bitsNecesarios-bitsEstrictos>0)
			bitsEstrictos=bitsEstrictos+1;
		String result="";
		int dividendo=combinationNumber;
		int cociente, resto=0;
		while (dividendo>=2) {
			cociente=dividendo/2;
			resto=dividendo%2;
			result=resto+result;
			dividendo=cociente;
		}
		result=dividendo+result;
		for (int i=result.length(); i<bitsEstrictos; i++)
			result="0"+result;
		return result;
	}

}

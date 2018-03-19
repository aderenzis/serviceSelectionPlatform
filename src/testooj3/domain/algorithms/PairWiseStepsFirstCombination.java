package testooj3.domain.algorithms;

import java.util.Hashtable;
import java.util.Vector;

import testooj3.domain.TParameter;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.algorithms.pairwise.ByteTableEntry;
import testooj3.domain.algorithms.pairwise.Pair;
import testooj3.domain.algorithms.pairwise.PairsTable;

public class PairWiseStepsFirstCombination extends AbstractPairWise {

	@Override
	public Vector<TestCase> getTestCases(TestTemplate template) throws Exception {
		Vector <TParameter> parametros=template.getParameters();
		PairsTable[] pptt=getPairsTables(template);
		Pair p;
		PairsTable[] auxi={null};
		int[] divisores=calculaDivisores(parametros);
		int[] numberOfValuesPerParameter=numberOfValuesPerParameter(parametros);

		Hashtable<Integer, ByteTableEntry> selectedCombinations=new Hashtable<Integer, ByteTableEntry>();
		while ((p=parDePesoCero(pptt, auxi))!=null) {
			long timeIni=System.currentTimeMillis();
			ByteTableEntry selectedCombination=null;
			int selectedCombinationPosition=0;
			int current=0;
			do {
					ByteTableEntry te=getCombination(numberOfValuesPerParameter, divisores, current);
					int indexA=auxi[0].getIndexA();
					int indexB=auxi[0].getIndexB();
					if (te.contains(p, indexA, indexB)) {
						selectedCombination=te;
						selectedCombinationPosition=current;
					}
					current++;
			} while (selectedCombination==null);
			selectedCombination.visitPairs(pptt);
			selectedCombinations.put(selectedCombinationPosition, selectedCombination);
			long timeFin=System.currentTimeMillis();
			System.out.println(selectedCombination.toString() + " found in " + ((timeFin-timeIni)/1000) + " seconds");
		}
		return this.getTestCases(template, pptt, selectedCombinations);
	}
	
}

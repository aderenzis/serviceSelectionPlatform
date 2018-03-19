package testooj3.domain.algorithms;

import java.util.Hashtable;
import java.util.Vector;

import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.algorithms.pairwise.ByteTableEntry;
import testooj3.domain.algorithms.pairwise.CombinationsTable;
import testooj3.domain.algorithms.pairwise.Pair;
import testooj3.domain.algorithms.pairwise.PairsTable;

public class PairWiseAC extends AbstractPairWise {

	@Override
	public Vector<TestCase> getTestCases(TestTemplate template) throws Exception {
		CombinationsTable ct=new CombinationsTable(template);
		//System.out.println(ct.toString());
		PairsTable[] pptt=getPairsTables(template);
		Pair p;
		PairsTable[] auxi={null};
		Hashtable<Integer, ByteTableEntry> selectedCombinations=new Hashtable<Integer, ByteTableEntry>();
		while ((p=parDePesoCero(pptt, auxi))!=null) {
			ByteTableEntry selectedCombination=null;
			int min=Integer.MAX_VALUE;
			long timeIni=System.currentTimeMillis();
			int selectedCombinationPosition=0;
			for (int i=0; i<ct.size(); i++) {
				ByteTableEntry te=ct.get(i);
				int indexA=auxi[0].getIndexA();
				int indexB=auxi[0].getIndexB();
				int wop=te.weightOfPairs(pptt);
				if (te.contains(p, indexA, indexB) && wop<min) {
					selectedCombinationPosition=i;
					selectedCombination=te;
					min=wop;
				}			
			}
			selectedCombination.visitPairs(pptt);
			selectedCombinations.put(selectedCombinationPosition, selectedCombination);
			long timeFin=System.currentTimeMillis();
			System.out.println(selectedCombination.toString() + " found in " + ((timeFin-timeIni)/1000) + " seconds");
		}
		return getTestCases(template, pptt, selectedCombinations);
	}






}

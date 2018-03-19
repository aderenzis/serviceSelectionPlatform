package testooj3.domain.algorithms;

import java.util.Hashtable;
import java.util.Vector;

import testooj3.domain.TParameter;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.algorithms.pairwise.ByteTableEntry;
import testooj3.domain.algorithms.pairwise.Pair;
import testooj3.domain.algorithms.pairwise.PairsTable;

public class PairWiseSteps extends AbstractPairWise {

	@Override
	public Vector<TestCase> getTestCases(TestTemplate template) throws Exception {
		int numeroDeCombinaciones=template.getNumberOfCombinations();
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
			int min=Integer.MAX_VALUE;
			int selectedCombinationPosition=0;
			for (int i=0; i<numeroDeCombinaciones; i++) {
				if (selectedCombinations.get(i)==null) {
					ByteTableEntry te=getCombination(numberOfValuesPerParameter, divisores, i);
					int indexA=auxi[0].getIndexA();
					int indexB=auxi[0].getIndexB();
					if (te.contains(p, indexA, indexB)) {
						int wop=te.weightOfPairs(pptt);
						if (wop<min) {
							selectedCombination=te;
							selectedCombinationPosition=i;
							min=wop;
						}
					}
				}
			}
			selectedCombination.visitPairs(pptt);
			selectedCombinations.put(selectedCombinationPosition, selectedCombination);
			long timeFin=System.currentTimeMillis();
			System.out.println(selectedCombination.toString() + " found in " + ((timeFin-timeIni)/1000) + " seconds");
		}
		return this.getTestCases(template, pptt, selectedCombinations);
		/*Vector<TestCase> resultado=new Vector<TestCase>();
		TestCase tc;
		Enumeration<ByteTableEntry> e=selectedCombinations.elements();
		int i=0;
		while (e.hasMoreElements()) {
			ByteTableEntry sc=e.nextElement();
			System.out.println(sc.toString());
			tc=new TestCase(template);
			tc.setNombre(templateName+"_"+(i+1));
			int cont=0;
			for (int j=0; j<constructor.getParametros().size(); j++) 
            {
				TParameter parameter=constructor.getParametro(j);
				TestValue tv=parameter.getTestValue(sc.getTestValue(cont++));
				tc.addParametro(tv);
            }
            for (int j=0; j<methods.size(); j++) 
            {
                TMethod m=(TMethod) methods.get(j);
                for (int k=0; k<m.getParametros().size(); k++) {
                	TParameter parameter=m.getParametro(k);
    				TestValue tv=parameter.getTestValue(sc.getTestValue(cont++));
    				tc.addParametro(tv, j);
                }
            }
            resultado.add(tc);
            i++;
		}
		for (i=0; i<pptt.length; i++)
			System.out.println(pptt[i].toString());
		return resultado;*/
	}
	





}

package testooj3.domain.algorithms;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;
import testooj3.domain.algorithms.pairwise.ByteTableEntry;
import testooj3.domain.algorithms.pairwise.Pair;
import testooj3.domain.algorithms.pairwise.PairsTable;

public abstract class AbstractPairWise extends Algorithm {

	@Override
	public abstract Vector<TestCase> getTestCases(TestTemplate template) throws Exception;
	
	protected Pair parDePesoCero(PairsTable[] pptt, PairsTable[] pt) {
		for (int i=0; i<pptt.length; i++) {
			Pair p=pptt[i].getPairWithWeight(0);
			if (p!=null) {
				pt[0]=pptt[i];
				return p;
			}
		}
		return null;
	}

	protected PairsTable[] getPairsTables(TestTemplate template) throws Exception {
		Vector<TestValue[]> parametros=new Vector<TestValue[]>();
		if (this.constructor!=null)
			for (int i=0; i<constructor.getParametros().size(); i++) {
				TParameter p=constructor.getParametro(i);
				if (p.getTestValues().length==0) 
					p.loadTestValues();
				parametros.add(p.getTestValues());
			}
		for (int i=0; i<methods.size(); i++) 
		{
			Operation metodo=(Operation) methods.get(i);
			for (int j=0; j<metodo.getParametros().size(); j++) 
			{
				TParameter p=metodo.getParametro(j);
				if (p.getTestValues().length==0) 
					p.loadTestValues();
				parametros.add(p.getTestValues());
			}
		}
		PairsTable result[]= getPairsTables(parametros);
		return result;
	}

	private PairsTable[] getPairsTables(Vector<TestValue[]> parametros) {
		int numeroDeTablas=(parametros.size()*(parametros.size()-1))/2;
		PairsTable[] result=new PairsTable[numeroDeTablas];
		int cont=0;
		for (int i=0; i<parametros.size(); i++) {
			for (int j=i+1; j<parametros.size(); j++) {
				result[cont++]=getPairsTable(parametros, i, j);
			}
		}
		return result;
	}

	private PairsTable getPairsTable(Vector<TestValue[]> parametros, int a, int b) {
		TestValue[] valoresA=parametros.get(a);
		TestValue[] valoresB=parametros.get(b);
		PairsTable result=new PairsTable(valoresA.length, valoresB.length);
		result.setIndexA(a);
		result.setIndexB(b);
		return result;
	}
	
	protected Vector<TestCase> getTestCases(TestTemplate template, PairsTable[] pptt, Hashtable<Integer, ByteTableEntry> selectedCombinations) {
		Vector<TestCase> resultado=new Vector<TestCase>();
		TestCase tc;
		Enumeration<ByteTableEntry> e=selectedCombinations.elements();
		int i=0;
		while (e.hasMoreElements()) {
			ByteTableEntry sc=e.nextElement();
			System.out.println(sc.toString());
			tc=new TestCase(template);
			tc.setNombre(templateName+"_"+(i+1));
			int cont=0;
			if (this.constructor!=null)
				for (int j=0; j<constructor.getParametros().size(); j++) 
	            {
					TParameter parameter=constructor.getParametro(j);
					TestValue tv=parameter.getTestValue(sc.getTestValue(cont++));
					tc.addParametro(tv);
	            }
            for (int j=0; j<methods.size(); j++) 
            {
                Operation m=(Operation) methods.get(j);
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
		return resultado;
	}
}

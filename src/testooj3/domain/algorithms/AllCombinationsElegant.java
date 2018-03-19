package testooj3.domain.algorithms;

import java.util.Vector;

import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;
import testooj3.domain.algorithms.pairwise.ByteTableEntry;

public class AllCombinationsElegant extends Algorithm {

	@Override
	public Vector<TestCase> getTestCases(TestTemplate template) throws Exception {
		int numeroDeCombinaciones=template.getNumberOfCombinations();
		Vector <TParameter> parametros=template.getParameters();
		int[] divisores=calculaDivisores(parametros);
		int[] numberOfValuesPerParameter=numberOfValuesPerParameter(parametros);
		
		Vector<TestCase> resultado=new Vector<TestCase>();
		TestCase tc;
		for (int i=0; i<numeroDeCombinaciones; i++) {
			ByteTableEntry sc=getCombination(numberOfValuesPerParameter, divisores, i);
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
                Operation m=(Operation) methods.get(j);
                for (int k=0; k<m.getParametros().size(); k++) {
                	TParameter parameter=m.getParametro(k);
    				TestValue tv=parameter.getTestValue(sc.getTestValue(cont++));
    				tc.addParametro(tv, j);
                }
            }
            resultado.add(tc);
		}
		return resultado;
	}
	
}

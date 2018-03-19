package testooj3.domain.algorithms;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;
import testooj3.domain.algorithms.pairwise.ByteTableEntry;
import testooj3.domain.arboles.Arbol;

/**
 * @author  andres
 */
public abstract class Algorithm {
    protected TestTemplate template;
    protected TConstructor constructor;
    protected Vector methods;
    protected String templateName;
    
    public void setTestTemplate(TestTemplate t) {
        template=t;
        constructor=template.getConstructor();
        methods=template.getMethods();
        templateName=template.getName();
    }
    
	protected Vector<TestCase> getTestCases(TestTemplate template, Hashtable<Integer, ByteTableEntry> selectedCombinations) {
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
		return resultado;
	}
    
    protected Arbol buildArbol() throws Exception {
        Arbol a=new Arbol();
        if (constructor.getParametros().size()>0) {
            TParameter p=constructor.getParametro(0);
            if (p.getTestValues().length==0) 
                p.loadTestValues();
            for (int j=0; j<p.getTestValues().length; j++) 
            {
                Arbol aux=new Arbol(p.getTestValue(j));
                a.addHijo(aux);
            }
        }
        for (int i=1; i<constructor.getParametros().size(); i++) 
        {
            Vector hojas=new Vector();
            a.cargaHojas(hojas);    
            TParameter p=constructor.getParametro(i);
            if (p.getTestValues().length==0) p.loadTestValues();
            for (int j=0; j<p.getTestValues().length; j++) 
            {
                for (int k=0; k<hojas.size(); k++) 
                {
                    Arbol aux=new Arbol(p.getTestValue(j));        
                    Arbol hoja=(Arbol) hojas.elementAt(k);
                    hoja.addHijo(aux);
                }
            }      
        }
        for (int i=0; i<methods.size(); i++) 
        {
            Operation metodo=(Operation) methods.get(i);
            for (int j=0; j<metodo.getParametros().size(); j++) 
            {
                TParameter p=metodo.getParametro(j);
                Vector hojas=new Vector();
                a.cargaHojas(hojas);
                if (p.getTestValues().length==0) p.loadTestValues();
                for (int k=0; k<p.getTestValues().length; k++) 
                {
                    for (int m=0; m<hojas.size(); m++) {
                        Arbol aux=new Arbol(p.getTestValue(k));
                        Arbol hoja=(Arbol) hojas.elementAt(m);
                        hoja.addHijo(aux);
                    }
                }
            }
        }
        return a;
    }	
    
    public abstract Vector<TestCase> getTestCases(TestTemplate template) throws Exception;

	public void removeFunctionallyEquivalentMutants(Vector ttc) {
		Vector<Integer> removables=new Vector<Integer>();
		for (int i=0; i<ttc.size(); i++) {
			TestCase tc=(TestCase) ttc.get(i);
			for (int j=0; j<ttc.size(); j++) {
				if (j!=i) {
					TestCase auxi=(TestCase) ttc.get(j);
					if (tc.functionallyEquivalent(auxi))
						removables.add(j); 
				}
			}
		}
		for (int i=removables.size()-1; i>=0; i--)
			removables.remove(i);
	}
	
    protected int[] calculaDivisores(Vector<TParameter> parametros) {
		int numeroDeParametros=parametros.size();
		int[] divisores=new int[numeroDeParametros];
		for (int i=0; i<numeroDeParametros; i++) {
			int divisor=1;
			for (int j=i+1; j<numeroDeParametros; j++) {
				TParameter auxi=parametros.get(j);
				divisor=divisor*auxi.getTestValues().length;
			}
			divisores[i]=divisor;
		}
		return divisores;
	}
    
    protected Vector<TParameter> getParameters() throws Exception {
    	Vector<TParameter> result=new Vector<TParameter>();
		for (int i=0; i<constructor.getParametros().size(); i++) {
			TParameter p=constructor.getParametro(i);
			result.add(p);
		}
		for (int i=0; i<methods.size(); i++) 
		{
			Operation metodo=(Operation) methods.get(i);
			for (int j=0; j<metodo.getParametros().size(); j++) 
			{
				TParameter p=metodo.getParametro(j);
				result.add(p);
			}
		}
		return result;
    }
    
    protected Vector<TestValue> getTestValues() throws Exception {
    	Vector<TestValue> testValues=new Vector<TestValue>();
		for (int i=0; i<constructor.getParametros().size(); i++) {
			TParameter p=constructor.getParametro(i);
			if (p.getTestValues().length==0) 
				p.loadTestValues();
			for (int j=0; j<p.getTestValues().length; j++)
				testValues.add(p.getTestValues()[j]);
		}
		for (int i=0; i<methods.size(); i++) 
		{
			Operation metodo=(Operation) methods.get(i);
			for (int j=0; j<metodo.getParametros().size(); j++) 
			{
				TParameter p=metodo.getParametro(j);
				if (p.getTestValues().length==0) 
					p.loadTestValues();
				for (int k=0; k<p.getTestValues().length; k++)
					testValues.add(p.getTestValues()[k]);
			}
		}
		return testValues;
    }
    
    protected int numberOfParameters() {
    	int result=this.constructor.getParametros().size();
    	for (int i=0; i<methods.size(); i++) {
    		Operation m=(Operation) methods.get(i);
    		result+=m.getParametros().size();
    	}
    	return result;
    }
	
    protected int[] numberOfValuesPerParameter(Vector<TParameter> parametros) {
		int numeroDeParametros=parametros.size();
		int[] result=new int[numeroDeParametros];
		for (int i=0; i<numeroDeParametros; i++) {
			TParameter p=parametros.get(i);
			result[i]=p.getTestValues().length;
		}
		return result;
	}
	
    protected ByteTableEntry getCombination(int[] numberOfValuesPerParameter, int[] divisores, int pos) {
		int numeroDeParametros=divisores.length;
		ByteTableEntry result=new ByteTableEntry(numeroDeParametros);
		for (int i=0; i<numeroDeParametros; i++) {
			int divisor=divisores[i];
			int posTestValue=(pos/divisor)%numberOfValuesPerParameter[i];
			result.setValue(i, (byte) posTestValue);
		}
		return result;
	}
}

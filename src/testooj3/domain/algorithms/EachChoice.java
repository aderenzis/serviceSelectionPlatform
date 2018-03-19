package testooj3.domain.algorithms;

import java.util.Hashtable;
import java.util.Vector;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.algorithms.pairwise.ByteTableEntry;

public class EachChoice extends Algorithm {
    public Vector<TestCase> getTestCases(TestTemplate template) throws Exception {
    	Vector<Vector<Boolean>> usados=buildUsos();
    	
    	Hashtable<Integer, ByteTableEntry> selectedCombinations=new Hashtable<Integer, ByteTableEntry>();
    	
    	int numeroDeParametros=template.getParameters().size();
    	int cont=0;
    	while (hayNoUsados(usados)) {
    		ByteTableEntry bte=getNonUsedCombination(usados,numeroDeParametros,template);
			selectedCombinations.put(cont++, bte);		
    	}
    	
    	System.out.println("selectedCombinations:"+selectedCombinations);
    	return this.getTestCases(template, selectedCombinations);
    }
    
    private ByteTableEntry getNonUsedCombination(Vector<Vector<Boolean>> usados, int numeroDeParametros, TestTemplate template) {
    	
    	ByteTableEntry result=new ByteTableEntry(numeroDeParametros);
    	int posicion=0,valoresParametro=0;
    	
    	for (int i=0; i<usados.size(); i++) {
    		Vector<Boolean> usosParametro=usados.get(i);
			for (int a=0; a<numeroDeParametros/usados.size(); a++) {
    	
				valoresParametro=((TParameter)(template.getParameters().get(a))).getTestValues().length;
				boolean puesto=false;
				
				for (int j=indice(a,template); j<indice(a,template)+valoresParametro; j++) {
					if (!usosParametro.get(j)) {
						result.setValue(posicion, (byte) ((int) (j-indice(a,template))%valoresParametro) );
						((Vector<Boolean>)(usados.get(i))).set(j, true);
						posicion++;
						puesto=true;
						break;
					}
				} 
				if (!puesto){
					result.setValue(posicion, (byte) 0);
					((Vector<Boolean>)(usados.get(i))).set(0, true);
					posicion++;
				}
								
			}
    	}
    	return result;
	}
    
    private int indice(int a, TestTemplate template) {
		int indice=0;
    	for(int i=0;i<a;i++){
    		indice+=((TParameter)(template.getParameters().get(i))).getTestValues().length;
    	}
		return indice;
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
    

	protected boolean hayNoUsados(Vector<Vector<Boolean>> usados) {
    	for (int i=0; i<usados.size(); i++) {
			Vector<Boolean> usosParametro=usados.get(i);
			for (int j=0; j<usosParametro.size(); j++)
				if (!usosParametro.get(j))
					return true;
		}	
    	return false;
    }
    
    protected Vector<Vector<Boolean>> buildUsos() {
		Vector<Vector<Boolean>> result=new Vector<Vector<Boolean>>();
		Vector<Boolean> usosConstructor=new Vector<Boolean>();
		for (int i=0; i<constructor.getParametros().size(); i++) 
        {
			TParameter parameter=constructor.getParametro(i);
			for (int j=0; j<parameter.getTestValues().length; j++)
				usosConstructor.add(false);
        }
		if (usosConstructor.size()>0)
			result.add(usosConstructor);
        for (int i=0; i<methods.size(); i++) 
        {
            Operation m=(Operation) methods.get(i);
            Vector<Boolean> usosMetodo=new Vector<Boolean>();
            for (int j=0; j<m.getParametros().size(); j++) {
            	TParameter parameter=m.getParametro(j);
            	for (int k=0; k<parameter.getTestValues().length; k++)
    				usosMetodo.add(false);
            }
            if (usosMetodo.size()>0)
            	result.add(usosMetodo);
        }
		return result;
	}

    		
}

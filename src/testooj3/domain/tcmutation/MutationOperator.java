package testooj3.domain.tcmutation;

import java.util.Vector;

import testooj3.domain.Operation;
import testooj3.domain.TestTemplate;

public abstract class MutationOperator {
    public abstract Vector<TestTemplate> buildMutants(TestTemplate template);
    
    public Vector eliminarPlantillasRepetidas(Vector v){
    	for(int i=0;i<v.size();i++){
    		TestTemplate t1 = (TestTemplate)v.get(i);
    		for(int j = i+1;j<v.size();j++){
    			TestTemplate t2 = (TestTemplate)v.get(j);
    			if(this.plantillasIguales(t1, t2)){
    				v.remove(j);
    				j--;
    			}
    		}
    	}
    	return v;
    }
    
    public boolean plantillasIguales(TestTemplate t1, TestTemplate t2){
    	
    	boolean iguales = false;
		char c1 = t1.getConstructor().getPos();
		char c2 = t2.getConstructor().getPos();
		if(c1 == c2){
			Vector ms1 = t1.getMethods();
			Vector ms2 = t2.getMethods();
			if(ms1.size()==ms2.size()){
				iguales=true;
				for(int k=0;k<ms1.size() && iguales;k++){
					char m1 = ((Operation)ms1.get(k)).getPos();
					char m2 = ((Operation)ms2.get(k)).getPos();
					if(m1!=m2){
						iguales = false;
					}
				}
			}
		}
    	
    	return iguales;
    }
}

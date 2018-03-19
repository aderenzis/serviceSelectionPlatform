package testooj3.domain.tcmutation;

import java.io.IOException;
import java.util.Vector;
import testooj3.domain.TJUnitMethod;
import testooj3.domain.Operation;
import testooj3.domain.TMutJUnitMethod;
import testooj3.domain.TestMethod;
import testooj3.domain.TestTemplate;

/**
 * @author  andres
 */
public class TCMutatorSystem {
    private Vector operators;
    private Vector testTemplates;

    public TCMutatorSystem(Vector testTemplates) {
        this.testTemplates=testTemplates;
        operators=TCMutationOperators.getInstance().buildOperators();
    }
    
    public Vector buildMutants() throws IOException, ClassNotFoundException {
        Vector mutants=new Vector();
        for (int i = 0; i <testTemplates.size(); i++) {
            TestTemplate template=(TestTemplate) testTemplates.get(i);
            for (int j=0; j<operators.size(); j++) {
                MutationOperator op=(MutationOperator) operators.get(j);
                Vector opMutants=op.buildMutants(template);
                
                opMutants = this.eliminarMutantesIguales(opMutants);
                
                for (int k=0; k<opMutants.size(); k++)
                    mutants.add(opMutants.get(k));
            }
        }
        mutants.addAll(this.testTemplates);
        return mutants;
    }

	public Vector getOperators() {
		return operators;
	}
	
	/**
	 * Método para eliminar del vector de mutantes
	 * las plantillas mutantes que sean iguales a alguna
	 * plantillas que no sea mutante.
	 * @param v
	 * @return
	 */
	public Vector eliminarMutantesIguales(Vector v){
		
		for(int i=0;i<this.testTemplates.size();i++){
			TestTemplate t1 = (TestTemplate)this.testTemplates.get(i);
			
			for(int j=0;j<v.size();j++){
				TestTemplate t2 = (TestTemplate)v.get(j);
				
				if(t2.getName().contains("CL") || t2.getName().contains("RC") || t2.getName().contains("IN")){
					
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

					if(iguales == true){
						v.remove(j);
						j--;
					}
				}
				
				
			}
		}
		return v;
	}
}

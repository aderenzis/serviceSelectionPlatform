package testooj3.domain.tcmutation;

import java.util.Vector;

import testooj3.domain.Operation;
import testooj3.domain.TestTemplate;

public class OpIN extends MutationOperator {

    public Vector<TestTemplate> buildMutants(TestTemplate template) {
        Vector mutantes=new Vector();
        for (int i=0; i<template.getMethods().size(); i++) {
            for (int j=i+1; j<template.getMethods().size(); j++) {
            	TestTemplate copia=template.copy();
	            copia.setName(template.getName() + "_IN_" + (i+1) + "_" + (j+1));
	            Operation metodo1=(Operation) copia.getMethods().get(i);
	            Operation metodo2=(Operation) copia.getMethods().get(j);
            	if (metodo1.getPos()!=metodo2.getPos()) {
		            copia.setMethod(i+1, metodo2);
		            copia.setMethod(j+1, metodo1);
		            mutantes.add(copia);
                }
            }
        }
        
        return mutantes;
    }
}

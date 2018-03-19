package testooj3.domain.tcmutation;

import java.util.Vector;

import testooj3.domain.Operation;
import testooj3.domain.TestTemplate;

public class OpRC extends MutationOperator {

    public Vector<TestTemplate> buildMutants(TestTemplate template) {
        Vector mutantes=new Vector();
        for (int i=0; i<template.getMethods().size(); i++) {
            TestTemplate copia=template.copy();
            copia.setName(template.getName() + "_RC_" + (i+1));
            mutantes.add(copia);
        }
        for (int i=0; i<mutantes.size(); i++) {
            TestTemplate mutante=(TestTemplate) mutantes.get(i);
            Operation m=(Operation) mutante.getMethods().get(i);
            mutante.getMethods().insertElementAt(m, i);
        }
        mutantes = this.eliminarPlantillasRepetidas(mutantes);
        return mutantes;
    }
}

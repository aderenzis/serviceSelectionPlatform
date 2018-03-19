package testooj3.domain.tcmutation;

import java.util.Vector;

import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestTemplate;

/**
 * @author Administrador
 */
public class OpPI extends MutationOperator {

    public Vector<TestTemplate> buildMutants(TestTemplate template) {
        Vector<TestTemplate> mutantes=new Vector<TestTemplate>();
        int contador=0;
        for (int j=0; j<template.getMethods().size(); j++) {
                Operation m=(Operation) template.getMethods().get(j);
                Vector<Integer> indexes=new Vector<Integer>();
                calculateCompatibleParameters(m, indexes);
                if (indexes.size()>0) {
                	int k=0;
                	while (k<indexes.size()) {
                        TestTemplate copia=template.copy();
                        copia.setName(template.getName() + "_PI_" + ++contador);
                        m=(Operation) copia.getMethods().get(j);
                        intercambiar(m, indexes.get(k), indexes.get(k+1));
                        mutantes.add(copia);
                        k+=2;
                    }
                }
            }
        return mutantes;
    }

    private void intercambiar(Operation m, int p1, int p2) {
		TParameter par1=m.getParametro(p1);
		TParameter par2=m.getParametro(p2);
    	Vector<TParameter> parameters=m.getParametros();
    	parameters.set(p1, par2);
    	parameters.set(p2, par1);
	}

	private void calculateCompatibleParameters(Operation m, Vector<Integer> indexes) {
        if (m.getParametros().size()<2) return;
        for (int i=0; i<m.getParametros().size(); i++) {
            TParameter pi=m.getParametro(i);
            for (int j=i+1; j<m.getParametros().size(); j++) {
                TParameter pj=m.getParametro(j);
                if (i!=j && pi.getTipo().equals(pj.getTipo())) {
                    indexes.add(new Integer(i));
                    indexes.add(new Integer(j));
                }
            }
        }
    }
}

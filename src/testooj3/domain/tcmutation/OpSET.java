package testooj3.domain.tcmutation;

import java.util.Vector;

import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;

public abstract class OpSET extends MutationOperator {
    public Vector<TestTemplate> buildMutants(TestTemplate template) {
        Vector<TestTemplate> mutantes=new Vector<TestTemplate>();
        for (int i=0; i<template.getMethods().size(); i++) {
            buildMutants(mutantes, template, i);
        }
        return mutantes;
    }

    private void buildMutants(Vector<TestTemplate> mutantes, TestTemplate template, int numeroDeMetodo) {
        Operation m=(Operation) template.getMethods().get(numeroDeMetodo);
        for (int j=0; j<m.getParametros().size(); j++) {
            TParameter p=(TParameter) m.getParametro(j);
            if (isOperatorApplicable(p)) {
                TestTemplate copia=template.copy();
                copia.setName(template.getName() + operatorName() + "_" + numeroDeMetodo + "_" + (j+1));
                Operation mc=(Operation) copia.getMethods().get(numeroDeMetodo);
                TParameter pc=(TParameter) mc.getParametro(j);
                for (int k=0; k<pc.getTestValues().length; k++) {
                    TestValue tv=pc.getTestValue(k);
                    tv=getValueToBeSet(p, tv);
                    pc.setTestValue(k, tv);
                }
                mutantes.add(copia);
            }
        }
    }

    protected abstract boolean isOperatorApplicable(TParameter p);
    protected abstract String operatorName();
    protected abstract TestValue getValueToBeSet(TParameter p, TestValue tv);

}

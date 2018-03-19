package testooj3.gui.guitesting;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;
import testooj3.domain.algorithms.PairWiseStepsFirstCombination;
import testooj3.domain.algorithms.pairwise.ByteTableEntry;
import testooj3.domain.algorithms.pairwise.PairsTable;

public class GuiPairWise extends PairWiseStepsFirstCombination {
	protected Vector<TestCase> getTestCases(TestTemplate normalTemplate, PairsTable[] pptt, Hashtable<Integer, ByteTableEntry> selectedCombinations) {
		GuiTestTemplate template=(GuiTestTemplate) normalTemplate;
		Vector<TestCase> resultado=new Vector<TestCase>();
		GuiTestCase tc;
		Enumeration<ByteTableEntry> e=selectedCombinations.elements();
		int i=0;
		while (e.hasMoreElements()) {
			ByteTableEntry sc=e.nextElement();
			System.out.println(sc.toString());
			tc=new GuiTestCase(template);
			tc.setNombre(templateName+"_"+(i+1));
			int cont=0;
            for (int j=0; j<template.getMessagges().size(); j++) 
            {
                IMessagge m = template.getMessagges().get(j);
                for (int k=0; k<m.getMethod().getParametros().size(); k++) {
                	TParameter parameter=m.getMethod().getParametro(k);
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

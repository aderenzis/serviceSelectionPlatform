package me.domain.algorithms;

import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

import me.domain.ObjectTable;

public class RandomPA extends PriorizationAlgorithm {

	public RandomPA(ObjectTable ot) {
		super(ot);
	}

	public Vector<String> priorizeTestCases(Vector<String> aliveMutants) {
        Vector<String> requiredTestCases=new Vector<String>();
        removeAliveMutants(aliveMutants);
        
        int n=mutants.size();
        Vector<String> mutantsKilled=new Vector<String>();
        Vector<String> mutantsNowKilled=new Vector<String>();
        while (n>0) {
            mutantsNowKilled.removeAllElements();
            TestCaseResult tcr=this.getRandomTestCase(mutantsNowKilled);
            if (tcr.getMutantsKilled().size()>0) {
	            requiredTestCases.add(tcr.getTestCaseName());
	            mutantsKilled.addAll(tcr.getMutantsKilled());
	            tcr.removeAllMutants();
	            n=mutants.size();
            } else n=n-1;
        }
        return requiredTestCases;
	}

	private TestCaseResult getRandomTestCase(Vector<String> mutantsNowKilled) {
		Random rnd=new Random();
		int numberOfMutants=this.mutantsResults.size();
		int randomIndex=rnd.nextInt(numberOfMutants);
		Enumeration e=this.mutantsResults.elements();
		for (int i=0; i<randomIndex; i++) 
			e.nextElement();
		TestCaseResult tcr=(TestCaseResult) e.nextElement();
        for (int i=0; i<tcr.getMutantsKilled().size(); i++) {
            String mutantName=tcr.getMutantsKilled().get(i).toString();
            this.mutants.remove(mutantName);
            Enumeration e2=this.mutantsResults.elements();
            while (e2.hasMoreElements()) {
                TestCaseResult auxi=(TestCaseResult) e2.nextElement();
                if (!auxi.getTestCaseName().equals(tcr.getTestCaseName()))
                    auxi.removeMutant(mutantName);
            }
        }	
		mutantsNowKilled.addAll(tcr.getMutantsKilled());
		return tcr;
	}

}

package me.domain.algorithms;

import java.util.Vector;

import me.domain.ObjectTable;

/**
 * @author Macario Polo
 */
public class Decreasing extends PriorizationAlgorithm {
    public Decreasing(ObjectTable ot) {
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
            Vector testCasesThatKillN=this.getTestCasesThatKill(n, mutantsNowKilled);
            if (testCasesThatKillN.size()>0) {
	            for (int i=0; i<testCasesThatKillN.size(); i++) {
	                TestCaseResult tcr=(TestCaseResult) testCasesThatKillN.get(i);
	                requiredTestCases.add(tcr.getTestCaseName());
	                mutantsKilled.addAll(tcr.getMutantsKilled());
	                tcr.removeAllMutants();
	            }
	            n=mutants.size();
            } else n=n-1;
        }
        return requiredTestCases;
    }
}

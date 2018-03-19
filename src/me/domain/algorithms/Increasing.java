package me.domain.algorithms;

import java.util.Vector;

import me.domain.ObjectTable;

/**
 * @author Macario Polo
 */
public class Increasing extends PriorizationAlgorithm {

    public Increasing(ObjectTable ot) {
        super(ot);
    }

    public Vector<String> priorizeTestCases(Vector<String> aliveMutants) {
        Vector<String> requiredTestCases=new Vector<String>();
        removeAliveMutants(aliveMutants);
        
        int n=1;
        Vector<String> mutantsKilled=new Vector<String>();
        Vector<String> mutantsNowKilled=new Vector<String>();
        while (this.mutants.size()>0) {
            mutantsNowKilled.removeAllElements();
            Vector testCasesThatKillN=this.getTestCasesThatKill(n, mutantsNowKilled);
            if (testCasesThatKillN.size()>0) {
	            for (int i=0; i<testCasesThatKillN.size(); i++) {
	                TestCaseResult tcr=(TestCaseResult) testCasesThatKillN.get(i);
	                requiredTestCases.add(tcr.getTestCaseName());
	                mutantsKilled.addAll(tcr.getMutantsKilled());
	                tcr.removeAllMutants();
	            }
	            n=1;
            } else n=n+1;
        }
        return requiredTestCases;
    }
}

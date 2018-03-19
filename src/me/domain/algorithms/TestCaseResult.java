package me.domain.algorithms;

import java.util.Vector;

/**
 * @author  Macario Polo
 */
public class TestCaseResult {

    private String testCaseName;
    private Vector<String> killedMutants;

    /**
     * @param testCaseName
     */
    public TestCaseResult(String testCaseName) {
        this.testCaseName=testCaseName;
        killedMutants=new Vector<String>();
    }

    /**
     * @param object
     */
    public void put(String killedMutant) {
        this.killedMutants.add(killedMutant);
    }

    /**
     * @return
     */
    public int getNumberOfMutantsKilled() {
        return killedMutants.size();
    }

    /**
     * @return
     */
    public String getTestCaseName() {
        return this.testCaseName;
    }

    /**
     * @return
     */
    public Vector<String> getMutantsKilled() {
        return killedMutants;
    }

    /**
     * @param mutantName
     */
    public void removeMutant(String mutantName) {
        this.killedMutants.remove(mutantName);
    }

    /**
     * 
     */
    public void removeAllMutants() {
        this.killedMutants.removeAllElements();        
    }

}

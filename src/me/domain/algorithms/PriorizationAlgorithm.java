package me.domain.algorithms;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import me.domain.ITableValue;
import me.domain.ObjectTable;

/**
 * @author  Macario Polo
 */
public abstract class PriorizationAlgorithm {
    protected ObjectTable objectTable;
    protected Vector testCases;
    protected Vector mutants;
    protected Hashtable<String, TestCaseResult> mutantsResults;

    public PriorizationAlgorithm(ObjectTable ot) {
        this.objectTable=ot;
        mutantsResults=new Hashtable<String, TestCaseResult>();
        this.testCases=objectTable.getColTitles();
        this.mutants=objectTable.getRowTitles();
        for (int i=0; i<testCases.size(); i++) {
            String testCaseName=testCases.get(i).toString();
            TestCaseResult testCaseResult=new TestCaseResult(testCaseName);
            int index=-1;
            for (int j=0; j<this.objectTable.getColTitles().size(); j++) {
            	if (this.objectTable.getColTitles().get(j).equals(testCaseName)) {
            		index=j;
            		break;
            	}
            }
            for (int j=0; j<objectTable.getRows().size(); j++) {
                Vector<ITableValue> row=objectTable.getDataRow(j);
				if (row.get(index).isKilled()) {
	                String mutantName=this.objectTable.getRowTitles().get(j).toString();
                    testCaseResult.put(mutantName);
                }
                /*
                int index=row.indexOf(testCaseName);
                if (index!=-1) {
                    String mutantName=objectTable.getRow(j).get(0).toString();
                    testCaseResult.put(mutantName);
                }*/
            }
            mutantsResults.put(testCaseName, testCaseResult);
        }
    }
    
    public abstract Vector<String> priorizeTestCases(Vector<String> aliveMutants);
    
    protected void removeAliveMutants(Vector<String> aliveMutants) {
    	boolean muere=false;
        for (int i=0; i<mutants.size(); i++) {
            String mutantName=mutants.get(i).toString();
            Vector<ITableValue> dataRow=objectTable.getDataRow(i);
            muere=false;
            for (int j=0; j<dataRow.size(); j++) {
            	if (dataRow.get(j).isKilled()) {
            		muere=true;
            		break;
            	}
            }
            if (!muere) {
                aliveMutants.add(mutantName);
                mutants.remove(i);
                objectTable.getRows().remove(i);
                i--;
            }
        } 
    }
    
    protected Vector<TestCaseResult> getTestCasesThatKill(int searchedNumberOfMutantsKilled, Vector<String> mutantsKilled) {
        Vector<TestCaseResult> result=new Vector<TestCaseResult>();
        Enumeration e=this.mutantsResults.elements();
        while (e.hasMoreElements()) {
            TestCaseResult tcr=(TestCaseResult) e.nextElement();
            if (tcr.getNumberOfMutantsKilled()==searchedNumberOfMutantsKilled) {
                result.add(tcr);
                mutantsKilled.addAll(tcr.getMutantsKilled());
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
             }
        }
        return result;
    }
    
    protected void removeMutantsKilledBy(String testCaseName) {
        int cont=0;
        System.out.print(testCaseName + ": ");
        for (int i=0; i<objectTable.getRows().size(); i++) {
            Vector dataRow=objectTable.getDataRow(i);
            if (dataRow.contains(testCaseName)) {
                System.out.print(objectTable.getRow(i).get(0).toString() + ", ");
                objectTable.getRows().remove(i);
                mutants.remove(i);
                i--;            
                cont++;
            }
        }
        System.out.println(cont);
    }
}

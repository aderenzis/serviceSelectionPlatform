package me.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.Vector;

import me.gui.ITestCasesWindowExecutor;
import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;

/**
 * @author  Administrador
 */
public class ResultsAnalyzer {

    private ITestCasesWindowExecutor mWindow;
    private Hashtable mMethods=new Hashtable();
	private String originalCUT;
	private String testFile;
    
    /**
     * @param executor
     */
    public ResultsAnalyzer(ITestCasesWindowExecutor window) {
        this.mWindow=window;
    }
    
    public ObjectTable analyzeResults() throws Exception {
        String analysisFolder=Configuration.getInstance().getTempPath()+ testFile;
        if (!analysisFolder.endsWith("/")) analysisFolder+="/";
        FileInputStream fi=new FileInputStream(analysisFolder + this.originalCUT + ".ser");
        ObjectInputStream ii=new ObjectInputStream(fi);
        Vector originales=(Vector) ii.readObject();
        fi.close();
        java.io.File directorio=new java.io.File(analysisFolder);
//        String[] lista=directorio.list();//quitar .directory en linux
        String[] lista = directorio.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".ser");
            }
        });
//        directorio.li
        mMethods=new Hashtable();
        ObjectTable ot = buildObjectTable(analysisFolder, originales, lista);
        return ot;
    }

    private ObjectTable buildObjectTable(String analysisFolder, Vector originales, String[] lista) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fi;
        ObjectInputStream ii;
        ObjectTable ot=new ObjectTable(analysisFolder, testFile);
        ot.setRowTitles(lista);
        mWindow.log("Number of execution results on the original CUT: " + originales.size());
        for (int i=0; i<originales.size(); i++) {
        	ExecutionResult resultado=(ExecutionResult) originales.get(i);
        	String methodName=resultado.getMethodName();
        	ot.addColTitle(methodName);
        }
        for (int i=0; i<lista.length; i++) {
            String mutantName=lista[i];
            try {
				fi=new FileInputStream(analysisFolder + lista[i]);
				ii=new ObjectInputStream(fi);
				Vector resultadosMutante=(Vector) ii.readObject();
				fi.close();
				for (int j=0; j<resultadosMutante.size(); j++) {
				    ExecutionResult original=(ExecutionResult) originales.get(j);
				    ExecutionResult resultadoMutante=(ExecutionResult) resultadosMutante.get(j);
				    if (original!=null && resultadoMutante!=null) {
				        Object originalInstance=original.getInstance();
				        Object mutantInstance=resultadoMutante.getInstance();
				        String methodName=original.getMethodName();
				        if (originalInstance==null && mutantInstance!=null)
				        	ot.setValueAt(methodName, mutantName, new KillValue(true));
				        else if (originalInstance!=null && mutantInstance==null)
				        	ot.setValueAt(methodName, mutantName, new KillValue(true));
				        else if (originalInstance!=null && mutantInstance!=null && !originalInstance.equals(mutantInstance)) { // && mKilledMutants.get(mutantName)==null) {
				        	ot.setValueAt(methodName, mutantName, new KillValue(true));
				        } else ot.setValueAt(methodName, mutantName, new KillValue(false));
				        /*String x;
				        if (mutantInstance==null)
				        	x="null";
				        else
				        	x=mutantInstance.toString();
				        ot.setValueAt(methodName, mutantName, x);*/
				    } else {
				    	System.out.println("Problems with " + mutantName + " (" + j + ")");
				    }
				}
			} catch (FileNotFoundException e) {
				this.mWindow.logError(mutantName + " not found");
			}
        }
        return ot;
    }
        
    public TimesTable analyzeTimesAndMutantsKilled() throws Exception {
        String analysisFolder=Configuration.getInstance().getTempPath()+ testFile;
        if (!analysisFolder.endsWith("/")) analysisFolder+="/";
        FileInputStream fi=new FileInputStream(analysisFolder + this.originalCUT + ".ser");
        ObjectInputStream ii=new ObjectInputStream(fi);
        Vector originales=(Vector) ii.readObject();
        fi.close();
        java.io.File directorio=new java.io.File(analysisFolder);
        String[] lista=directorio.list();
        mMethods=new Hashtable();
        TimesTable tt = buildTimesAndMutantsKilledTable(analysisFolder, originales, lista);
        return tt;
    }

    private TimesTable buildTimesAndMutantsKilledTable(String analysisFolder, Vector originales, String[] lista) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fi;
        ObjectInputStream ii;
        TimesTable tt=new TimesTable(analysisFolder, testFile);
        tt.setRowTitles(lista);
        for (int i=0; i<originales.size(); i++) {
        	ExecutionResult resultado=(ExecutionResult) originales.get(i);
            String methodName=resultado.getMethodName();
            tt.addColTitle(methodName);
        }       
        for (int i=0; i<lista.length; i++) {
            String mutantName=lista[i];
            try {
	            fi=new FileInputStream(analysisFolder + lista[i]);
	            ii=new ObjectInputStream(fi);
	            Vector resultadosMutante=(Vector) ii.readObject();
	            fi.close();
	            for (int j=0; j<resultadosMutante.size(); j++) {
	                ExecutionResult original=(ExecutionResult) originales.get(j);
	                ExecutionResult resultadoMutante=(ExecutionResult) resultadosMutante.get(j);
	                if (original!=null && resultadoMutante!=null) {
		                Object originalInstance=original.getInstance();
		                Object mutantInstance=resultadoMutante.getInstance();
		                long mutantTime=resultadoMutante.getExecutionTime();
		                String methodName=original.getMethodName();
		                if (originalInstance==null && mutantInstance!=null)
		                	tt.setValueAt(methodName, mutantName, new TimeValue(true, mutantTime));
		                else if (originalInstance!=null && mutantInstance==null)
		                	tt.setValueAt(methodName, mutantName, new TimeValue(true, mutantTime));
		                else if (originalInstance!=null && mutantInstance!=null && !originalInstance.equals(mutantInstance)) { // && mKilledMutants.get(mutantName)==null) {
		                    tt.setValueAt(methodName, mutantName, new TimeValue(true, mutantTime));
		                } else {
		                	tt.setValueAt(methodName, mutantName, new TimeValue(false, mutantTime));
		                }
	                } else {
	                	System.out.println("Problems with " + mutantName + " (" + j + ")");
	                }
	            }
            }
            catch (FileNotFoundException ex) {
            	this.mWindow.logError(mutantName + " not found");
            }
        }
        return tt;
    }

    public Hashtable getMethods() {
        return mMethods;
    }

	public void setOriginalCut(String original) {
		original=Auxi.substituteAll(original, ":", ".");
		original=Auxi.substituteAll(original, "/", ".");
		this.originalCUT=original;
	}

	public void setTestFile(String testFile) {
		this.testFile = testFile;
	}
}

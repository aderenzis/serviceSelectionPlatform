package me.domain;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Vector;

import me.MutantExecutor;
import me.gui.ITestCasesWindowExecutor;

/**
 * @author Administrador
 */
public class LocalMutantExecutor extends MutantExecutor {
	public LocalMutantExecutor(ITestCasesWindowExecutor ventana) {
        super(ventana);
    }    
   
    @SuppressWarnings("deprecation")
	protected Vector<ExecutionResult> executeTestCases(String fileWithTestCases, URL[] urls, String msg) throws Error, Exception {
        Vector<ExecutionResult> executionResults=new Vector<ExecutionResult>();
    	this.loadFileWithTheTestMethods(fileWithTestCases, urls, msg);
    	if (!mWindow.hasTestCases())
    		this.mWindow.setTestCases(this.methodsWithTestCases);
        Object instanceOfTheClassWithTestCases=constructorOfTheClassWithTestCases.newInstance((Object[]) null);
        
    	int row=this.mWindow.addVersionRow(versionPath, this.methodsWithTestCases.length);        
        for (int j=0; j<this.methodsWithTestCases.length; j++) {
            Method m=this.methodsWithTestCases[j];
            if (setUpMethod!=null)
                setUpMethod.invoke(instanceOfTheClassWithTestCases, (Object[]) null);
            this.mWindow.log(msg+"Executing " + m.getName());
            {
            	MethodExecutor me=MethodExecutor.newInstance(instanceOfTheClassWithTestCases, m, versionPath);
            	me.setWindow(this.mWindow);
            	me.setRowAndColumn(row, j+1);
        		Thread t=new Thread(me);
        		t.start();
        		
            	//me.run();
        		if (!this.versionPath.equals(this.original) && this.timeout.getTimeout()>0) {
        			long timeIni=System.currentTimeMillis();
        			long time=System.currentTimeMillis()-timeIni;
        			while (time<this.timeout.getTimeout() && !me.isStopped())
        				time=System.currentTimeMillis()-timeIni;
        			if (!me.isStopped()) {
        				me.stop();
        				mWindow.logError(m.getName() + " stopped on " + this.versionPath);
        				//t.stop();
        			}
        			ExecutionResult executionResult=me.getExecutionResult();
        			executionResults.add(executionResult);
        			me.kill();
        		}
            }
            if (tearDownMethod!=null)
                tearDownMethod.invoke(instanceOfTheClassWithTestCases, (Object[]) null);
        }
        return executionResults;
	}
}

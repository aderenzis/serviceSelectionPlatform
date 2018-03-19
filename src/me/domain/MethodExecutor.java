package me.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import me.gui.ITestCasesWindowExecutor;

/**
 * @author  andres
 */
public class MethodExecutor implements Runnable {
	private Object instance;
	private Method method;
	String versionPath;
	private ITestCasesWindowExecutor window;
	private boolean stopped;
	protected ExecutionResult executionResult;
	private static MethodExecutor mYo;
	
	private MethodExecutor(Object instance, Method m, String versionPath) {
		this.instance=instance;
		this.method=m;
		this.versionPath=versionPath;
		this.stopped=false;
	}
	
	public static MethodExecutor newInstance(Object instance, Method m, String versionPath) {
		if (mYo==null)
			mYo=new MethodExecutor(instance, m, versionPath);
		return mYo;
	}
	
	long timeIni;
	private int row;
	private int column;
	
	public void run() {
		Object cutInstance=null;
		this.window.log("Executing", this.row, this.column);
	    try {		
//	    	System.out.println("*****Thred actual "+Thread.currentThread().getId()); 
			executionResult=null;
			this.timeIni=System.currentTimeMillis();    	
			   System.out.println("Executing " + this.method.getName() + " on " + this.versionPath);
			cutInstance=this.method.invoke(instance, new Object[0]);
		    long time=System.currentTimeMillis()-this.timeIni;
//		    System.out.println();
		    if( (cutInstance instanceof Float))
		    {
		    	 System.out.println("Resultado "+ (Float)cutInstance+ " time " +time);
		    }
		   
		    executionResult=new ExecutionResult(cutInstance, this.method.getName(), time); 
			this.stopped=true;
//			System.out.println("*****A Stopear "+Thread.currentThread().getId());
			this.window.log("Ok", this.row, this.column);
		}
		catch (InvocationTargetException e) {
			
			long time=System.currentTimeMillis()-this.timeIni;
			executionResult=new ExecutionResult(cutInstance, this.method.getName(), time); 
			window.logError("Error with " + this.method.getName() + " on " + this.versionPath + ": " + e.getTargetException().toString());
			this.stopped=true;
			this.window.log("InvocationTargetException", this.row, this.column);
			return;
		}
	    catch (Exception e) {
			long time=System.currentTimeMillis()-this.timeIni;
			executionResult=new ExecutionResult(cutInstance, this.method.getName(), time); 
			window.logError("Error with " + this.method.getName() + " on " + this.versionPath + ": " + e.toString());
			this.stopped=true;
			this.window.log(e.getClass().getName(), this.row, this.column);
			return;
		}
		catch (Error e) {
			long time=System.currentTimeMillis()-this.timeIni;
			executionResult=new ExecutionResult(cutInstance, this.method.getName(), time); 
			window.logError("Error with " + this.method.getName() + " on " + this.versionPath + ": " + e.toString());
			this.stopped=true;
			this.window.log("Error", this.row, this.column);
			return;
		}
	}

	public void setWindow(ITestCasesWindowExecutor window) {
		this.window=window;
	}
	
	public void kill() {
		mYo=null;
	}

	public void stop() {
		executionResult=new ExecutionResult(null, this.method.getName(), Long.MAX_VALUE);  
		this.stopped=true;
		System.out.println("Stoping thread "+Thread.currentThread().getId());
		window.logError("Time out in " + this.method.getName());
		this.window.log("Stopped", this.row, this.column);
	}

	public boolean isStopped() {
		return this.stopped;
	}

	public ExecutionResult getExecutionResult() {
		return executionResult;
	}

	public void setRowAndColumn(int row, int column) {
		this.row=row;
		this.column=column;
	}


}

package me.domain;

import java.io.Serializable;

/**
 * @author  Administrador
 */
@SuppressWarnings("serial")
public class ExecutionResult implements Serializable {
    protected Object instance;
    protected String methodName;
	private long executionTime;
    

    /**
     * @param result
     * @param methodName
     * @param executionTime 
     */
    public ExecutionResult(Object instance, String methodName, long executionTime) {
        super();
        this.instance = instance;
        this.methodName = methodName;
        this.executionTime=executionTime;
    }

    public String getMethodName() {
        return this.methodName;
    }
    
    public Object getInstance() {
        return this.instance;
    }

	public long getExecutionTime() {
		return executionTime;
	}
}

package me.domain;

import java.util.Vector;

/**
 * @author  Administrador
 */
public class MethodResult {
    private String methodName;
    private Vector killedMutants;

    public MethodResult(String methodName) {
        this.methodName=methodName;
        this.killedMutants=new Vector();
    }

    public String getMethodName() {
        return methodName;
    }
    
    public void addKilledMutant(String mutantName) {
        killedMutants.add(mutantName);
    }
    
    public Vector getKilledMutants() {
        return killedMutants;
    }
}

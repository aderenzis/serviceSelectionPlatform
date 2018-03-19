package me.domain;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteMethodExecutor extends Remote {
	public void execute(String methodName) throws Exception, RemoteException;
	public void setFileWithTestMethods(String fileWithTestCases) throws RemoteException;
	public void setClasspath(String classpath) throws RemoteException;
	public void setVersionPath(String versionPath) throws RemoteException;
	public Object getCUTInstance() throws RemoteException;
	public long getTime() throws RemoteException;
}

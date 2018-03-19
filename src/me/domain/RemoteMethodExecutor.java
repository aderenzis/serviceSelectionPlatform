package me.domain;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.StringTokenizer;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class RemoteMethodExecutor extends UnicastRemoteObject implements IRemoteMethodExecutor {
	
	private String fileWithTestCases;
	private Class<?> testFileClass;
	private Constructor constructorOfTheClassWithTestCases;
	private Method[] methodsWithTestCases;
	private Method setUpMethod;
	private Method tearDownMethod;
	private Object cutInstance;
	private long time;
	private String originalClassPath;
	private String versionPath;

	protected RemoteMethodExecutor() throws RemoteException, MalformedURLException, NotBoundException, AlreadyBoundException {
		super();
		LocateRegistry.createRegistry(1000);
		try {
			Naming.bind("rmi://127.0.0.1:1000/methodexecutor", this);
		}
		catch (AlreadyBoundException e) {
			Naming.unbind("rmi://127.0.0.1:1000/methodexecutor");
			Naming.bind("rmi://127.0.0.1:1000/methodexecutor", this);
		}
	}
	
	public void setFileWithTestMethods(String fileWithTestCases) throws RemoteException {
		this.fileWithTestCases=fileWithTestCases;
	}
	
	public void setClasspath(String classpath) throws RemoteException {
		this.originalClassPath=classpath;
	}
	
	public void setVersionPath(String versionPath) throws RemoteException {
		this.versionPath=versionPath;
	}
	
	protected String loadFileWithTheTestMethods() throws Exception, RemoteException {
		String msg="";

		String classPath=originalClassPath + (originalClassPath.endsWith(";") ? "" : ";") + versionPath;
        URL[] urls=prepareURLs(classPath);
        if (fileWithTestCases.endsWith(".class"))
        	fileWithTestCases=fileWithTestCases.substring(0, fileWithTestCases.length()-6);
		
		URLClassLoader loader=new URLClassLoader(urls);
        Method setUpMethod=null, tearDownMethod=null;

        try {
			this.testFileClass=loader.loadClass(fileWithTestCases);
		} catch (ClassNotFoundException e2) {
			throw e2;
		}
		try {
	        this.constructorOfTheClassWithTestCases=testFileClass.getConstructors()[0];
	        //this.methodsWithTestCases=testFileClass.getDeclaredMethods();
	        this.setUpMethod=null;
	        this.tearDownMethod=null;
		}
		catch (Error error) {
			throw error;
		}
        try {
            setUpMethod=testFileClass.getDeclaredMethod("setUp", (Class[]) null);
            if (setUpMethod.getModifiers()!=Modifier.PUBLIC) {
            	setUpMethod=null;
            	msg+="<br><font color='red'>Warning: setUp method is not public and won't be considered</font>";
            }
        } catch (Exception e) {
        }
        try {
            tearDownMethod=testFileClass.getDeclaredMethod("tearDown", (Class[]) null);
            if (tearDownMethod.getModifiers()!=Modifier.PUBLIC) {
            	tearDownMethod=null;
            	msg+="<br><font color='red'>Warning: tearDown method is not public and won't be considered</font>";
            }
        } catch (Exception e1) {
        }
        return msg;
	}

	public void execute(String methodName) throws Exception, RemoteException {
		this.loadFileWithTheTestMethods();
		Object instanceOfTheClassWithTestCases=constructorOfTheClassWithTestCases.newInstance((Object[]) null);
		this.cutInstance=null;
		long timeIni=System.currentTimeMillis();
		Method method=testFileClass.getDeclaredMethod(methodName, (Class[]) null);
	    try {						
			System.out.println("Executing " + method.getName() + " on " + versionPath);
			this.cutInstance=method.invoke(instanceOfTheClassWithTestCases, (Object[]) null);
		    this.time=System.currentTimeMillis()-timeIni;
		} catch (Exception e) {
			this.time=System.currentTimeMillis()-timeIni;
			System.out.println("Exception with " + method.getName() + " on " + versionPath + ": " + e.toString());
			throw e;
		}
		catch (Error e) {
			this.time=System.currentTimeMillis()-timeIni;
			System.out.println("Error with " + method.getName() + " on " + versionPath + ": " + e.toString());
		}
	}
	
	public Object getCUTInstance() throws RemoteException {
		return cutInstance;
	}
	
	public long getTime() throws RemoteException {
		return this.time;
	}
	
	public static void main(String[] args) {
		try {
			try {
				RemoteMethodExecutor m=new RemoteMethodExecutor();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Escuchando");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private URL[] prepareURLs(String classPath) throws MalformedURLException {
        Hashtable auxi=new Hashtable();
        StringTokenizer st=new StringTokenizer(classPath, ";");
        while (st.hasMoreTokens()) {
            String token=st.nextToken();
            if (token.endsWith("\\"))
            	auxi.put("file://" + token, new URL("file://" + token));
            else if (token.endsWith(".jar"))
            	auxi.put(token, new File(token).toURL());
            else if (!token.endsWith(".jar")) {
                token+="\\";
                auxi.put("file://" + token, new URL("file://" + token));
            }
        }
        String lastURLKey="file://mujava06\\result\\testset\\";
        URL testset=new URL(lastURLKey);
        if (auxi.get(lastURLKey)==null)
        	auxi.put(lastURLKey, testset); 
        URL[] result=new URL[auxi.size()];
        Enumeration e=auxi.elements();
        int i=0;
        while (e.hasMoreElements()) {
        	result[i++]=(URL) e.nextElement();
        }
        return result;
    }
}

package me;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

import org.exolab.castor.util.Iterator;

import me.domain.ExecutionResult;
import me.domain.LocalMutantExecutor;
import me.domain.timeouts.Timeout;
import me.gui.ITestCasesWindowExecutor;
import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;

/**
 * @author  andres
 */
public abstract class MutantExecutor  implements Runnable {
	public static final int SINGLE_METHOD = 1;
	public static final int ALL_METHODS = 2;
	protected ITestCasesWindowExecutor mWindow;
	protected String versionPath;
	protected Class<?> testFileClass;
	protected Constructor constructorOfTheClassWithTestCases;
	protected Method[] methodsWithTestCases;
	protected Method setUpMethod;
	protected Method tearDownMethod;
	protected String fileWithTestCases;
	protected int executionMode;
	protected String original;
	protected Timeout timeout;
	protected Vector<String> versionsPaths;
	protected String mTestClassName;
	protected String originalClassPath;
	protected String cutName;
	
	public MutantExecutor(ITestCasesWindowExecutor ventana) {
		this.mWindow=ventana;
	}
	
	protected abstract Vector<ExecutionResult> executeTestCases(String fileWithTestCases, URL[] urls, String msg) 
		throws Error, Exception;
	
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
    	if (this.executionMode==LocalMutantExecutor.SINGLE_METHOD) {
    		try {
				this.executeTestCasesOnMutant();
    		}
            catch (NoClassDefFoundError e) {
            	this.mWindow.logError(e.toString() + ": probably you should review your classpath");
            }
            catch (Error e) {
            	this.mWindow.logError(e.toString());
            }
    	} else {
    		this.executeOriginalAndMutants();
    	}
    }
    	   
	public void executeTestCasesOnMutant() throws Error {
	    String msg="Executing " + fileWithTestCases + " against " + cutName + " in " + versionPath;
	    this.mWindow.log(msg);
	    try {
	    	String classPath=versionPath + (versionPath.endsWith(";") ? "" : ";") + originalClassPath;
            URL[] urls=prepareURLs(classPath);
            if (fileWithTestCases.endsWith(".class"))
            	fileWithTestCases=fileWithTestCases.substring(0, fileWithTestCases.length()-6);
            for(int i=0; urls.length >i; i++){
            	System.out.println("******"+urls[i]+"******");
            }
            Vector<ExecutionResult> executionResults=executeTestCases(fileWithTestCases, urls, msg);
            new File(Configuration.getInstance().getTempPath()+fileWithTestCases).mkdir();
            String originalVersionPath=versionPath;
            versionPath=Auxi.substituteAll(versionPath, ":", ".");
            versionPath=Auxi.substituteAll(versionPath, "/", ".");
            String resultsFileName=Configuration.getInstance().getTempPath()+ fileWithTestCases + "/" + versionPath + ".ser";
            String resultado = "***Resultado***\n";
            java.util.Iterator ite = executionResults.iterator();
            while(ite.hasNext()){
            	ExecutionResult er = (ExecutionResult) ite.next();
            	resultado+=er.getInstance()+" - "+er.getMethodName()+" - "+er.getExecutionTime()+"\n";
            }
            resultado+="***************";
            this.mWindow.log(resultado);
            System.out.println(resultado);
            FileOutputStream fo=new FileOutputStream(new File(resultsFileName));
            ObjectOutputStream oo=new ObjectOutputStream(fo);
            oo.writeObject(executionResults);
            fo.close();
            this.mWindow.log("Succesful execution of " + fileWithTestCases + " against " + cutName + " in " + originalVersionPath);
        }catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (Exception e) {
            this.mWindow.logError(e.toString());
        }	    
	}
    
    public void executeOriginalAndMutants() {
        try {
            this.mWindow.setNumberOfMutants(this.versionsPaths.size()-1);
            for (int i=0; i<this.versionsPaths.size(); i++) {
                this.setVersionPath(this.versionsPaths.get(i));
                this.mWindow.setCurrentMutant(i);
                executeTestCasesOnMutant();
            }
            this.mWindow.setNumberOfMutants(0);
        } catch (Exception e) {
        	this.mWindow.logError(e.toString());
        }
        catch (NoClassDefFoundError e) {
        	this.mWindow.logError(e.toString() + ": probably you should review your classpath");
        }
        catch (Error e) {
        	this.mWindow.logError(e.toString());
        }
    }
    
	protected void loadFileWithTheTestMethods(String fileWithTestCases, URL[] urls, String msg) throws ClassNotFoundException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		String auxi2="Loading ClassLoader with urls: ";
		for (int i=0; i<urls.length; i++)
			auxi2+=urls[i].toExternalForm() + ";";
		this.mWindow.log(auxi2);
		URLClassLoader loader=new URLClassLoader(urls);
		this.mWindow.log("ClassLoader loaded\nLoading file with test cases: " + fileWithTestCases);
        this.setUpMethod=null;
        this.tearDownMethod=null;

        try {
        	this.testFileClass=Class.forName(fileWithTestCases, true, loader);
			//this.testFileClass=loader.loadClass(fileWithTestCases);
			this.mWindow.log("File with test cases loaded (" + fileWithTestCases + ")");
		} catch (ClassNotFoundException e2) {
			throw e2;
		}
		try {
	        this.constructorOfTheClassWithTestCases=testFileClass.getConstructors()[0];
	        Method[] methods = testFileClass.getDeclaredMethods();
	        Vector<Method> auxi=new Vector<Method>();
	        for (int i=0; i<methods.length; i++) {
	        	Method m=methods[i];
	        	if (m.getName().startsWith("test") && m.getModifiers()==Modifier.PUBLIC)
	        		auxi.add(m);
	        }
	        this.methodsWithTestCases=new Method[auxi.size()];
	        for (int i=0; i<auxi.size(); i++) 
	        	methodsWithTestCases[i]=auxi.get(i);
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
            	msg+="Warning: setUp method is not public and won't be considered";
            }
        } catch (Exception e) {
        }
        try {
            tearDownMethod=testFileClass.getDeclaredMethod("tearDown", (Class[]) null);
            if (tearDownMethod.getModifiers()!=Modifier.PUBLIC) {
            	tearDownMethod=null;
            	msg+="Warning: tearDown method is not public and won't be considered";
            }
        } catch (Exception e1) {
        }
	}
    
	protected URL[] prepareURLs(String classPath) throws MalformedURLException {
		classPath=Auxi.substituteAll(classPath, "\\", "/");
		Vector<URL> urls=new Vector<URL>();
        StringTokenizer st=new StringTokenizer(classPath, ";");
        while (st.hasMoreTokens()) {
            String token=st.nextToken();
            if (token.endsWith("/"))
            	urls.add(new URL("file://" + token));
            else if (token.endsWith(".jar"))
            	urls.add(new File(token).toURL());
            else if (!token.endsWith(".jar")) {
                token+="/";
                urls.add(new URL("file://" + token));
            }
        }
        String lastURLKey="file://" + Configuration.getInstance().getMujavaRoot() + "testset/";
        URL testset=new URL(lastURLKey);
//        if (!urls.contains(testset))
//        	urls.insertElementAt(testset, 0); 
        //TODO PROBANDO
        URL[] result=new URL[urls.size()];
        for (int i=0; i<urls.size(); i++)
        	result[i]=urls.get(i);
        return result;
    }
	
	public void setVersionPath(String versionPath) {
		this.versionPath=versionPath;
	}
	
	public void setExecutionMode(int mode) {
		this.executionMode=mode;
	}
	
	public void setTimeout(Timeout timeout) {
		this.timeout=timeout;			
	}
	
	public void setFileWithTestCases(String fileWithTestCases) {
		this.fileWithTestCases=fileWithTestCases;
	}
	
	public void setClassPath(String classPath) {
		this.originalClassPath=classPath;
	}
	
	public void setCUTName(String cutName) {
		this.cutName=cutName;
	}
	
	public void setOriginal(String original) {
		this.original=original;
	}
	
	public void setVersionsPaths(Enumeration<String> vp) {
		this.versionsPaths=new Vector<String>();
		while (vp.hasMoreElements())
			this.versionsPaths.add(vp.nextElement());
	}

	public void setTestClassName(String testClassName) {
		this.mTestClassName=testClassName;
	}
}

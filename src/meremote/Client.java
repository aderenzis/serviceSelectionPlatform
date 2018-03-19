package meremote;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.rmi.Naming;
import java.util.Map;
import java.util.Vector;

import me.MutantExecutor;
import me.domain.ExecutionResult;
import me.domain.IRemoteMethodExecutor;
import me.gui.ITestCasesWindowExecutor;

public class Client extends MutantExecutor {
	public Client(ITestCasesWindowExecutor window) {
		super(window);
	}

	protected Vector<ExecutionResult> executeTestCases(String fileWithTestCases, URL[] urls, String msg) throws Error, Exception  {
		Vector<ExecutionResult> results=new Vector<ExecutionResult>();
				
        if (fileWithTestCases.endsWith(".class"))
        	fileWithTestCases=fileWithTestCases.substring(0, fileWithTestCases.length()-6);
		loadFileWithTheTestMethods(this.fileWithTestCases, urls, msg);
		
		for (int j=0; j<this.methodsWithTestCases.length; j++) {
            Method m=this.methodsWithTestCases[j];
            if (m.getName().startsWith("test") && m.getModifiers()==Modifier.PUBLIC) {
        		Process process=arrancarServidor();
        		IRemoteMethodExecutor rme=(IRemoteMethodExecutor) Naming.lookup("rmi://127.0.0.1:1000/methodexecutor");
        		rme.setClasspath(originalClassPath);
        		rme.setFileWithTestMethods(fileWithTestCases);
        		rme.setVersionPath(versionPath);
        		RunnableMethodExecutor me=new RunnableMethodExecutor(rme, m.getName());
        		Thread t=new Thread(me);
        		t.start();
        		long timeIni=System.currentTimeMillis();
    			long time=System.currentTimeMillis()-timeIni;
    			while (time<this.timeout.getTimeout())
    				time=System.currentTimeMillis()-timeIni;
                ExecutionResult er=new ExecutionResult(rme.getCUTInstance(), m.getName(), rme.getTime());
                results.add(er);        		
                process.destroy();
            }
        }
		System.out.println("Remote method executed");
		this.mWindow.log("Remote methods executed");
    	return results;
	}

	private Process arrancarServidor() throws IOException {
		ProcessBuilder pb =  new ProcessBuilder("java", "me.domain.RemoteMethodExecutor");
		File f=new File("c:\\Documents and Settings\\Macario Polo\\workspace\\me");
		pb.directory(f);
		Map<String, String> env = pb.environment();
		env.put("CLASSPATH", "c:\\Documents and Settings\\Macario Polo\\workspace\\me");
		StringBuffer strBuffer= new StringBuffer();
        String line=null;
		Process process = pb.start();
		return process;
	}

}

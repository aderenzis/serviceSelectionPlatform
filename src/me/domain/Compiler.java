package me.domain;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Vector;

import me.gui.IProgressWindow;
import me.gui.JFMutantsMixer;
import testooj3.domain.Configuration;
import testooj3.gui.components.ILogWindow;

/**
 * @author  andres
 */
public class Compiler implements Runnable {
	private Vector<String> locationsOfTheCUTVersions;
	private String thePackage;
	private String cutName;
	private String classPath;
	private ILogWindow logWindow;
	private IProgressWindow progressWindow;
	
	public Compiler(ILogWindow logWindow) {
		this.logWindow=logWindow;
	}

	public Compiler(Vector<String> locationsOfTheCUTVersions, String thePackage, String cutName, String classPath, ILogWindow window, IProgressWindow window2) {
		this.locationsOfTheCUTVersions=locationsOfTheCUTVersions;
		this.thePackage=thePackage;
		this.cutName=cutName;
		this.classPath=classPath;
		this.logWindow=window;
		this.progressWindow=window2;
	}
	
	protected String getSlashedPath(String path) {
		String result="";
		for (int i=0; i<path.length(); i++)
			if (path.charAt(i)=='.')
					result+="/";
			else result+=path.charAt(i);
		return result;
	}
	
	public void run() {
		String path;
		int max=locationsOfTheCUTVersions.size();
		if (this.progressWindow!=null)
			this.progressWindow.setMaximum(max-1);
		for (int i=0; i<this.locationsOfTheCUTVersions.size(); i++) {
			if (this.progressWindow!=null)
				this.progressWindow.setPos(i);
			path=this.locationsOfTheCUTVersions.get(i);
			path=path.endsWith("/") ? path : path + "/";
			this.logWindow.log("Compiling " + path + " (" + (i+1) + " of " + max + ")");
			this.compile(path, thePackage, cutName);
		}
		if (this.progressWindow!=null)
			this.progressWindow.setPos(0);
	}
	
	public void compile(String path, String thePackage, String cutName) {
        try {
        	thePackage=getSlashedPath(thePackage);
        	thePackage=thePackage.endsWith("/") ? thePackage : thePackage + "/";
            String fcn=thePackage + cutName + ".java";
            System.out.println("COMPILANDO: "+"javac "+ fcn +" -cp "+ this.classPath);
            ProcessBuilder pb = new ProcessBuilder("javac", fcn, "-cp", this.classPath);
    		File f=new File(path);
    		pb.directory(f);
//			Map<String, String> env = pb.environment();
//			env.put("CLASSPATH", this.classPath);
    		Process process = pb.start();
    		//process.waitFor();
            InputStream sis=process.getInputStream();
            InputStream ses=process.getErrorStream();
            int tam=sis.available();
            byte[] b=new byte[tam];
            sis.read(b);
            String msg=new String(b);
            this.logWindow.log(msg);
            tam=ses.available();
            b=new byte[tam];
            ses.read(b);
            msg=new String(b);
            this.logWindow.logError(msg);
            System.err.println(msg);
        } catch (Exception e) {
            this.logWindow.logError("Error compiling: " + e.toString());
        }  		
	}
	
	public void simpleCompile(String path, String thePackage, String cutName) {
        try {
        	Configuration cfg=Configuration.getInstance();
        	thePackage=thePackage.endsWith(File.separator) ? thePackage : thePackage + File.separator;
//            ProcessBuilder pb = new ProcessBuilder("javac", thePackage + cutName + ".java");
        	System.out.println(path+thePackage + cutName + ".java");
        	String classPath = "/home/diego/workspace/TestOojNuevo/lib/mujava/testset/";
        	System.out.println("COMPILANDO: "+"javac "+ path+thePackage + cutName + ".java "+ " -cp "+ classPath);
            ProcessBuilder pb = new ProcessBuilder("javac", path+thePackage + cutName + ".java", "-cp", classPath);//Modificado por diego
            File f=new File(path);
    		pb.directory(f);
    		Map<String, String> env = pb.environment();
//    		env.put("CLASSPATH", "c:\\Documents and Settings\\Macario Polo\\workspace\\me");
//    		+ "/home/diego/workspace/TestOojNuevo/lib/mujava/testset/;"
//    		pb = new ProcessBuilder(System.getenv("AXIS2_HOME")+"\\bin\\wsdl2java.bat","-o",pathtoDeploy+"\\stub","-uri",pathToWSDL);
//    		javac /home/diego/workspace/TestOojNuevo/lib/mujava/testset/Mujavacalculator.java -cp /home/diego/workspace/TestOojNuevo/lib/mujava/testset/

    		classPath=cfg.getMujavaRoot()+ "testset"+File.separator + ";"  +
        		(cfg.getClassPath()!=null ? cfg.getClassPath() + ";" : "");
//    		env.put("CLASSPATH", classPath);
    		
    		Process process = pb.start();
    		process.waitFor();
    		//obtiene los mensajes y los muestra...
            InputStream sis=process.getInputStream();
            InputStream ses=process.getErrorStream();
            int tam=sis.available();
            byte[] b=new byte[tam];
            sis.read(b);
            String msg=new String(b);
            this.logWindow.log(msg);
            tam=ses.available();
            b=new byte[tam];
            ses.read(b);
            msg=new String(b);
            this.logWindow.logError(msg);
        } catch (Exception e) {
            this.logWindow.logError("Error compiling: " + e.toString());
        }  		
	}
	
	public static void simpleCompilerConector(String[] javaClass, String classPath) {
        try {
        	Configuration cfg=Configuration.getInstance();
//        	System.out.println("javac -sourcepath /home/diego/results/wrappers/0/ -cp \"/home/diego/Dropbox/TESIS/axis2-1.6.3/lib/*\" /home/diego/results/wrappers/0/katze/axis/services/calculator/*");//por consola funciona
//            ProcessBuilder pb = new ProcessBuilder("javac", "-sourcepath", "/home/diego/results/wrappers/0/", "-classpath", "/home/diego/Dropbox/TESIS/axis2-1.6.3/lib/*", "/home/diego/results/wrappers/0/katze/axis/services/calculator/*");
        	String file1ToCompile = javaClass[0];
        	String file2ToCompile = javaClass[1];
        	String file3ToCompile = javaClass[2];
        	System.out.println("COMPILANDO: javac " +file1ToCompile+" "+file2ToCompile+" "+file3ToCompile+" "+ " -cp " + classPath);
        	ProcessBuilder pb = new ProcessBuilder("javac", file1ToCompile, file2ToCompile, file3ToCompile, "-cp", classPath);
    		Process process = pb.start();
    		process.waitFor();
    		//obtiene los mensajes y los muestra...
            InputStream sis=process.getInputStream();
            InputStream ses=process.getErrorStream();
            int tam=sis.available();
            byte[] b=new byte[tam];
            sis.read(b);
            String msg=new String(b);
            tam=ses.available();
            b=new byte[tam];
            ses.read(b);
            msg=new String(b);
            if(msg.length()!=0)
            	System.out.println("*****COMPILER ERROR:"+msg+"**********");
        } catch (Exception e) {
        	System.out.println("ERROR: NO pudo compilar");
        }  		
	}
	
}

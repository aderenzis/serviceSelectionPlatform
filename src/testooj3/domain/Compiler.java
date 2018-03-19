package testooj3.domain;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Vector;
import testooj3.gui.components.ILogWindow;

/**
 * @author  andres
 */
public class Compiler {
	private Vector<String> files;
	private String thePackage;
	private ILogWindow window;

	public Compiler(Vector<String> files, String thePackage, ILogWindow window) {
		this.files=files;
		this.thePackage=thePackage;
		this.window=window;
	}
	
	public void compile() {
		String path;
		int max=files.size();
		for (int i=0; i<this.files.size(); i++) {
			String fileName=files.get(i);
			this.window.log("Compiling " + fileName + " (" + (i+1) + " of " + max + ")");
			this.compile(thePackage, fileName);
		}
	}
	
	protected void compile(String thePackage, String fileName) {
        try {
        	Configuration cfg=Configuration.getInstance();
        	thePackage=getSlashedPath(thePackage);
        	if (thePackage.length()>0) 
        		thePackage+="\\";
        	String argumento=thePackage + fileName + ".java";
             pb = new ("javac", argumento);
            String workingPath=cfg.getMujavaRoot() + "src\\";
    		File f=new File(workingPath);
    		pb.directory(f);
    		Map<String, String> env = pb.environment();
    		String classPath=cfg.getJUnitLocation() + ";" +
        		(cfg.getClassPath()!=null ? cfg.getClassPath() + ";" : "");
    		env.put("CLASSPATH", classPath);
    		Process process = pb.start();
    		process.waitFor();
            InputStream sis=process.getInputStream();
            InputStream ses=process.getErrorStream();
            int tam=sis.available();
            byte[] b=new byte[tam];
            sis.read(b);
            String msg=new String(b);
            this.window.log(msg);
            tam=ses.available();
            b=new byte[tam];
            ses.read(b);
            msg=new String(b);
            this.window.logError(msg);
        } catch (Exception e) {
            this.window.logError("Error compiling: " + e.toString());
        }  		
	}
	
	protected String getSlashedPath(String path) {
		String result="";
		for (int i=0; i<path.length(); i++)
			if (path.charAt(i)=='.')
					result+="\\";
			else result+=path.charAt(i);
		return result;
	}
}

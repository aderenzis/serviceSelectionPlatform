package testooj3.auxiliares;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import javax.swing.tree.DefaultMutableTreeNode;

import testooj3.persistence.Agente;

public class ClassPathFunctions {
	public static Vector<String> getSplittedClassPath(String classPath) {
		Vector<String> result=new Vector<String>();
		StringTokenizer st=new StringTokenizer(classPath, ";");
		while (st.hasMoreTokens()) 
		{
			String entrada=st.nextToken();
			result.add(entrada);
		}
		return result;
	}
	
	public static Vector<String> findClass(String className, String classPath) {
		Vector<String> result=new Vector<String>();
		if (!className.endsWith(".class"))
			className+=".class";
		className=className.substring(0, className.lastIndexOf("."));
		className=Auxi.substituteAll(className, ".", File.separator);
		className=File.separator + className + ".class";

		StringTokenizer st=new StringTokenizer(classPath, ";");
		while (st.hasMoreTokens()) 
		{
			String entrada=st.nextToken();
			if (entrada.toLowerCase().endsWith(".zip") || entrada.toLowerCase().endsWith(".jar")) 
			{
				result.addAll(classExistsInZIPOrJAR(className, entrada));
			} else { // es un directorio
				result.addAll(classExistsInDir(className, entrada));
			}
		}
		return result;
	}

	private static Vector<String> classExistsInZIPOrJAR(String className, String zipFile) 
	{
		Vector<String> result=new Vector<String>();
		try {
			ZipFile file=new ZipFile(zipFile);
			Enumeration entries=file.entries();
			while (entries.hasMoreElements()) 
			{
				String fileName=entries.nextElement().toString();
				if (fileName.equals(className)) {
					file.close();
					result.add(zipFile);
				}
			}
			file.close();
		}
		catch (Exception ex) 
		{
		}
		return result;
	}

	private static Vector<String> classExistsInDir(String className, String dirName) 
	{
		Vector<String> result=new Vector<String>();
		try
		{
			Agente a=Agente.getAgente();
			Vector v=a.lista(dirName, ".class", true);
			for (int i=0; i<v.size(); i++) 
			{
				String fileName=v.get(i).toString();
				if (fileName.equals(className) || (File.separator+fileName).equals(className)) {
					result.add(dirName);
				}
			}
		}
		catch (Exception e)
		{     
		}
		return result;
	}

	public static Vector<String> getClassesIn(String entrada) {
		Vector<String> result=new Vector<String>();
		if (entrada.toLowerCase().endsWith(".zip") || entrada.toLowerCase().endsWith(".jar")) {
			try {
				System.out.println(entrada+" : es in zip o un jar");
				ZipFile file=new ZipFile(entrada);
				Enumeration entries=file.entries();
				while (entries.hasMoreElements()) 
				{
					String fileName=entries.nextElement().toString();
					if (fileName.endsWith(".class"))
						fileName=fileName.substring(0, fileName.length()-6);
					result.add(fileName);
				}
				file.close();
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			
		} else {
			try
			{
				Agente a=Agente.getAgente();
				Vector v=a.lista(entrada, ".class", true);
				for (int i=0; i<v.size(); i++) 
				{
					String fileName=v.get(i).toString();
					if (fileName.endsWith(".class"))
						fileName=fileName.substring(0, fileName.length()-6);
					result.add(fileName);
				}
			}
			catch (Exception e)
			{     
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String changeSlashUNIX(String path){
		return path.replace('\\', '/');
	}
	
	public static Vector getClassesInPath(String path){
		Vector <String> result=new Vector<String>();
		path=changeSlashUNIX(path);
		File f=new File(path);
		if (f.isDirectory()){
			String [] files=f.list();
//			System.out.println(f.list().toString());
			for (int i=0;i<files.length;i++){
				result.addAll(getClassesIn(path+files[i]));
//				System.out.println(i+":"+path+files[i]);
			}
		}
		return result;
	}
}

class Filtro implements FilenameFilter{
    String extension;
    Filtro(String extension){
        this.extension=extension;
    }
    public boolean accept(File dir, String name){
        return name.endsWith(extension);
    }
}


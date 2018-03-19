package testooj3.domain;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestoojClassLoader {

	public static Interface load(String cutName, String classPath, boolean alsoInherited) throws MalformedURLException, ClassNotFoundException {
		URL[] urls=prepareURLs(classPath);
		URLClassLoader loader=new URLClassLoader(urls);
		Class c=loader.loadClass(cutName);
		Interface result=new Interface(c, alsoInherited);
		return result;
	}

	public static Interface loadRemote(String cutName, URL [] urls,boolean alsoInherited) throws MalformedURLException, ClassNotFoundException {
		URLClassLoader loader=new URLClassLoader(urls);
		JarClassLoader jcl;
		Class c = loader.loadClass(cutName);
		
		Interface result=new Interface(c, alsoInherited);
		return result;
	}

	public static URL[] prepareURLs(String classPath) throws MalformedURLException {
		String[] auxi = classPath.split(";");
		URL[] result=new URL[auxi.length];
		for (int i=0; i<auxi.length; i++) {
			String url=auxi[i];
			if (url.endsWith("\\"))
                result[i]=new URL("file://" + url);
            else if (url.endsWith(".jar"))
            	result[i]=new File(auxi[i]).toURL();
            else if (!url.endsWith(".jar")) {
            	url+="\\";
            	result[i]=new URL("file://" + url);
            	
            }
			System.out.println("result[i].toString(): "+result[i].toString());
		}
        return result;
    }
}

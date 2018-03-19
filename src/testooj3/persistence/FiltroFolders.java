package testooj3.persistence;

import java.io.*;
import java.util.Vector;

public class FiltroFolders implements FilenameFilter {
	String mPath;
	static String mPathRaiz;
	static Vector mFolders; //=new Vector();
	
	protected FiltroFolders() {
	}
	
	public FiltroFolders (String pathRaiz, String path) {
	    mPathRaiz=pathRaiz;
	    mPath=path;
	    mFolders=new Vector();
	    mFolders.addElement(pathRaiz);
	    lista();
	}
	
	protected void setPath (String path) {
	    mPath=path;
	}
	
	public boolean accept (File dir, String name) {
	    String fileName=dir.getAbsolutePath();
	    fileName=fileName.substring(0, fileName.length()-1)+name;
	    File f=new File(fileName);
	    if (f.isDirectory())        
	        return true;
	    return false;
	}
	
	protected void lista() {
	    String path=new String();
        path=changeSeparator(mPath);
	    File f;
	    if (path.endsWith("/"))
	        f=new File(path+".");
	    else
	        f=new File(path+"/.");
	    String s[]=f.list(this);
	    for (int i=0; i<s.length; i++) {
	        File aux=new File(s[i]);
	        if (path.endsWith("/"))
	            mFolders.addElement(path+s[i]);
	        else
                mFolders.addElement(path+"/"+s[i]);
	        FiltroFolders filtro=new FiltroFolders();
	        if (path.endsWith("/"))	
	            filtro.setPath(path+s[i]);
	        else
	            filtro.setPath(path+"/"+s[i]);
	        filtro.lista();
	    }
	}
	
	public String[] getFolders() {
	    String r[]=new String[mFolders.size()];
	    for (int i=0; i<mFolders.size(); i++)
	        r[i]=mFolders.elementAt(i).toString();
	    return r;
	}
	
	protected String changeSeparator(String path) {
	    String r=new String();
	    for (int i=0; i<path.length(); i++)
	        if (path.charAt(i)=='\\')
	            r+='/';
	        else
	            r+=path.charAt(i);
	    return r;
	}	
}
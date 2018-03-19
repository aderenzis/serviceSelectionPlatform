package testooj3.persistence;

import java.io.*;
import java.util.Vector;

/**
 * @author  andres
 */
public class Filtro implements FilenameFilter {
	String mPath;
	String mExt;
	private boolean strictEnd;
	
	public Filtro(String path, String ext) {
	    mPath=path;
	    mExt=ext.toLowerCase();
	}
	
	public boolean accept (File dir, String name) {
	    if (name.toLowerCase().endsWith(mExt)) {
	    	if (!strictEnd) 
	    		return true;
	    	if (name.length()>mExt.length())
	    		return false;
	    	return true;
	    }
	    return false;
	}
	
	public void lista(Vector resul, String ruta, boolean directorio) throws Exception {
	    changeSeparator();
	    if (mPath.endsWith("."))
	        mPath=mPath.substring(0, mPath.length()-1);
	    if (!mPath.endsWith(File.separator))
	        mPath+=File.separator;
//	    File f=new File(mPath+ruta+"\\."), aux;
	    File f=new File(mPath+ruta+File.separator+"."), aux;
	    String s[]=f.list();
	    if (s==null)
	        return;
	    for (int i=0; i<s.length; i++) {
	      aux=new File(mPath+ruta, s[i]);
        if (aux.isFile()){
          if (accept(aux, aux.getName ())) {
            if (!ruta.equals (""))
              resul.addElement((ruta.endsWith(File.separator) ? ruta : ruta + File.separator)+aux.getName()); 
            else
              resul.addElement(aux.getName()); 
          }
        } else
          if (directorio){
            String path=mPath;
            lista (resul, ruta+File.separator+aux.getName(), directorio);
            mPath=path;
          }
	    }
	}
	
	protected void changeSeparator() {
	    String r=new String();
	    for (int i=0; i<mPath.length(); i++)
	        if (mPath.charAt(i)=='\\')
	            r+='/';
	        else
	            r+=mPath.charAt(i);
	    mPath=new String(r);
	}

	public void setStrictEnd(boolean b) {
		this.strictEnd=b;
	}
}
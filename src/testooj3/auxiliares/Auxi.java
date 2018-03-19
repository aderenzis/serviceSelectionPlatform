package testooj3.auxiliares;

import java.io.File;

public class Auxi 
{
    private static final byte[] b={13,10};
    public static final String nl=new String(b);
    
    
    public static String recorta(String s) 
    {
        if (s.indexOf(".")==-1) 
            return s;		
        String r=s.substring(s.lastIndexOf(".")+1, s.length());
        return r;
    }
    
    public static String recorta_package(String s) 
    {
        if (s.indexOf(".")==-1) 
            return s;		
        String r=s.substring(0, s.lastIndexOf("."));
        return r;
    }
    
    public static String escapa(String path) 
    {
        String r="";
        for (int i=0; i<path.length(); i++)
            if (path.charAt(i)=='\\')
                r+="\\\\";
            else
                r+=path.charAt(i);
        return r;
    }
    
    public static String substituteAll(String oldText, String pattern, String newText) {
        String code = new String ();
        String cRes = oldText;
        while (cRes.indexOf(pattern, 0) != -1) {
            int pos=cRes.indexOf(pattern, 0);
            code=cRes.substring(0, pos) + newText;
            code+=cRes.substring(pos+pattern.length(), cRes.length());
            cRes = code;
        }
        return cRes;
    }	
    
    public static String substitute(String oldText, String pattern, String newText) {
        if (oldText.indexOf(pattern)==-1) return oldText;
        int pos=oldText.indexOf(pattern, 0);
        String code=oldText.substring(0, pos) + newText;
        code+=oldText.substring(pos+pattern.length(), oldText.length());
        return code;
    }
    
    public static void deleteDirectory(String sessionPath, int numberOfFiles) {
        for (int i=1; i<=numberOfFiles; i++) {
            new File(sessionPath+File.separator + i + ".testcase").delete();
        }
        new File(sessionPath).delete();
    }
}
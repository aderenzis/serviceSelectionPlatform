package testooj3.auxiliares;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 

/** 
 * @author: Crysfel Villa 
 * Created: Friday, June 03, 2005 4:54:59 PM 
 * Modified: Friday, June 03, 2005 4:54:59 PM 
 */ 


public class Copiar{ 
     
    /** 
     * Copia un directorio con todo y su contendido 
     * @param srcDir 
     * @param dstDir 
     * @throws IOException 
     */ 
	public void copiaDirectorios(String srcDir, String dstDir)throws IOException{
		System.out.println("COPIANDO DIR: de "+srcDir+" a "+dstDir );
		File dirOrigen  = new File(srcDir);
		File dirDestino = new File(dstDir);
		if (dirOrigen.isDirectory())
		{
			String[] children = dirOrigen.list();
			if (!dirDestino.exists())
				dirDestino.mkdir();
			for (int i=0; i<children.length;i++){
					System.out.println(children[i]);
					File archivoDest = new File(dstDir+File.separator+children[i]);
					File archivoOrig = new File(srcDir+File.separator+children[i]);
					if (archivoOrig.isDirectory()){
						copiaDirectorios(archivoOrig.getPath(),archivoDest.getPath());
					}
					else{
						File nuevoarchivo = new File(srcDir+File.separator+children[i]);
						copy(nuevoarchivo, archivoDest);
					}
			}
		}
		else{
			copy(dirOrigen, dirDestino);
		}
	}
    public void copyDirectory(File srcDir, File dstDir) throws IOException { 
        if (srcDir.isDirectory()) { 
            if (!dstDir.exists()) { 
                dstDir.mkdir(); 
            } 
             
            String[] children = srcDir.list(); 
            for (int i=0; i<children.length; i++) { 
                copyDirectory(new File(srcDir, children[i]),new File(dstDir, children[i])); 
            } 
        } else { 
        		if (!dstDir.isDirectory())
        			copy(srcDir, dstDir);
        		else
        			System.out.println("el destino es un directorio");
        } 
    } 
     
    /** 
     * Copia un solo archivo 
     * @param src 
     * @param dst 
     * @throws IOException 
     */ 
    public void copy(File src, File dst) throws IOException { 
        InputStream in = new FileInputStream(src); 
        OutputStream out = new FileOutputStream(dst); 
         
         
        byte[] buf = new byte[1024]; 
        int len; 
        while ((len = in.read(buf)) > 0) { 
            out.write(buf, 0, len); 
        } 
        in.close(); 
        out.close(); 
    } 
     /*
    public static void main(String arg[]){ 
        Copiar cp = new Copiar(); 
        try{ 
        	cp.copiaDirectorios("c:/wtp","c:/destino");
            //cp.copy(new File("C:\\wtp"),new File("C:/MAP")); 
            System.out.print("Copiado con exito"); 
        }catch(FileNotFoundException ex){
        	  System.out.println(ex.getMessage() + " en el directorio especficado.");
        	  System.exit(0);
        	  }
        	  catch(IOException e){
        	  System.out.println(e.getMessage());  
        	  } 
    } */
    public void deleteDirs(String srcDir){
    	File dirOrigen  = new File(srcDir);
		File[] archivos =  dirOrigen.listFiles();
		
		for (int i=0; i<archivos.length;i++){
			if(archivos[i].isDirectory()){
				deleteDirs(archivos[i].getPath());
			}
			archivos[i].delete();
		}
		
	}
    
}  
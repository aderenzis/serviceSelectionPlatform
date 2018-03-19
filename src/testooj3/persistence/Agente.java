package testooj3.persistence;

import java.io.*;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import testooj3.domain.Configuration;
import testooj3.domain.PrimitiveValue;
import testooj3.domain.SerializedValue;
import testooj3.domain.TestValue;

/**
 * @author  andres
 */
public class Agente
{
    // nica	instancia de la	clase.
    private static Agente mInstancia;
    
    /******************************************************************************
     * Constructor PRIVADO de	la clase.
     ******************************************************************************/
    private Agente() {
    }
    
    /******************************************************************************
     * Mtodo	que	devuelve la	nica instancia	de la clase	para su	uso.
     ******************************************************************************/
    public static Agente getAgente()	{
        if (mInstancia==null)
            mInstancia=new Agente();
        return mInstancia;
    }
    
    /******************************************************************************
     * Mtodo	que	carga un objeto	Object que supuestamente est serializado
     * en	un fichero de ruta igual a la cadena recibida como parmetro.
     ******************************************************************************/
    public Object cargarObjeto(String fichero) throws Exception{
        FileInputStream f=new FileInputStream(fichero);
        ObjectInputStream entrada=new ObjectInputStream(f);
        try{
            Object p=entrada.readObject();
            f.close();
            entrada.close();
            return p;
        }catch (Exception ex){
            f.close();
            entrada.close();
            throw ex;
        }
    }
    
    public void copiarFichero(String origen, String destino) throws Exception {
        FileOutputStream arriba=new FileOutputStream(destino);
        FileInputStream f=new FileInputStream(origen);
        byte[] contenido=new byte[f.available()];
        f.read(contenido);
        f.close();
        String s=new String(contenido);
        arriba.write(s.getBytes());
        arriba.close();	
    }
    
    //devuelve un array con los objetos que están en el directorio de conjetura de errores
    public TestValue[] cogerFicheros(String clase) throws IOException, ClassNotFoundException {
        FileInputStream f;
        ObjectInputStream entrada;
        String[] lista=cogerNombreFicheros(clase);
        Object objeto;
        TestValue[]resultado;
        if (lista!=null)
            resultado=new TestValue[lista.length];
        else resultado=new TestValue[0];
        
        for (int i=0;i<lista.length ;i ++) {
            f=new FileInputStream(Configuration.getInstance().getSerializedObjectsPath() +lista[i]);
            entrada=new	ObjectInputStream(f);
            // Leo el objeto por si acaso no es tal.
            objeto=entrada.readObject();
            f.close();
            resultado[i]=new SerializedValue(Configuration.getInstance().getSerializedObjectsPath() +lista[i]);
            entrada.close();
        }
        return resultado;
    }
    
    // Le paso el path en el que se encuentran los objetos serializados
    public String[] cogerNombreFicheros(String clase)  {
        java.io.File directorio=new java.io.File(Configuration.getInstance().getSerializedObjectsPath());
        String[]lista=directorio.list();
        Vector salida=new Vector ();
        if (clase.endsWith (";"))
            clase=clase.substring (0,clase.length()-1);
        for (int i=0;i<lista.length ;i++){
            if (lista[i].startsWith (clase))
                salida.addElement(lista[i]);
        }
        lista=pasarAArray(salida);
        
        return lista;
    }
    
    private String[] pasarAArray(Vector v){
        String [] salida=new String [v.size ()];
        
        for (int i=0;i<v.size ();i ++){
            salida[i]=(String)v.elementAt (i);
        }
        return salida;
    }
    
//    public boolean esPrimitivo(String tipo) {
//    	if (tipo.startsWith("["))
//    		tipo=tipo.substring (2, tipo.length());
//		if (tipo.startsWith ("java.lang.")) 
//            tipo=tipo.substring (tipo.lastIndexOf (".")+1,tipo.length ());
//		return tipo.equalsIgnoreCase("byte") || tipo.equalsIgnoreCase("short") || tipo.equalsIgnoreCase("int") || 
//    		tipo.equalsIgnoreCase("long") || tipo.equalsIgnoreCase("char") || tipo.equalsIgnoreCase("float") || 
//    		tipo.equalsIgnoreCase("double") || tipo.equalsIgnoreCase("boolean") || tipo.equalsIgnoreCase("String") ||
//    		tipo.equalsIgnoreCase("Byte") || tipo.equalsIgnoreCase("Short") || tipo.equalsIgnoreCase("Integer") || 
//    		tipo.equalsIgnoreCase("Long") || tipo.equalsIgnoreCase("Character") || tipo.equalsIgnoreCase("Float") || 
//    		tipo.equalsIgnoreCase("Double") || tipo.equalsIgnoreCase("Boolean");
//    }
    

	public boolean isArrayOfPrimitives(String tipo) {
		if (tipo.length()!=2) return false;
		char c1=tipo.charAt(0);
		char c2=tipo.charAt(1);
		if (c1=='[') {
			if (c2=='B' || c2=='S' || c2=='I' || c2=='L' || c2=='C' || c2=='F' || 
					c2=='D' || c2=='B');
				return true;
		}
		return false;
	}  
	
	public String getPrimitiveTypeOfArray(String tipo) {
		char c1=tipo.charAt(0);
		char c2=tipo.charAt(1);
		if (c1=='[') {
			if (c2=='D') return "double";
			if (c2=='I') return "int";
			if (c2=='F') return "float";
		}
		return "GENERIC";
	}   
    
    public TestValue[] cogerValor(String tipo) throws FileNotFoundException, IOException {  
        String fileName=Configuration.getInstance().getValuesFile();
        FileInputStream f=null;
        try {
        	f=new FileInputStream(fileName);
        }
        catch (FileNotFoundException e) {
        	createValuesFile();
        	f=new FileInputStream(fileName);
        }
        Properties pp=new Properties();
        pp.load(f);
        f.close();
        String valores=pp.getProperty(tipo);
        if (valores==null) return null;
        StringTokenizer tk=new StringTokenizer(valores, ",");
        TestValue[] result=new TestValue[tk.countTokens()];
        int i=0;
        while (tk.hasMoreTokens()) 
        {
            result[i++]=new PrimitiveValue(tk.nextToken().trim());
        }
        return result;
    }
    
    private void createValuesFile() throws IOException {
    	Properties pp=new Properties();
    	pp.setProperty("int", "-1, 3, 4");
    	pp.setProperty("short", "-1, 3, 4");
    	pp.setProperty("byte", "-1, 3, 4");
    	pp.setProperty("long", "-1, 3, 4");
    	pp.setProperty("java.lang.String", "hola, pepe");
    	pp.setProperty("boolean", "true, false");
    	pp.setProperty("double", "-100.0, 50.0, 0.0, 9423942395.435443");
    	pp.setProperty("float", "-100.0, 50.0, 0.0, 42395.435");
    	pp.setProperty("char", "a, b, z, $");
    	FileOutputStream f=new FileOutputStream(Configuration.getInstance().getValuesFile());
    	pp.store(f, "");
    	f.close();
	}

	public Vector lista(String ruta,String ext, boolean directorio) throws Exception {
        Filtro f=new Filtro(ruta, ext);
        Vector ficheros=new Vector();
        f.lista(ficheros, "", directorio);
        return ficheros;
    }
    
    public byte[] cogerBytes(String ruta)throws Exception {
        java.io.File fichero=new java.io.File (ruta);
        FileInputStream f=new FileInputStream(fichero);
        byte []datos=new byte [(int)fichero.length ()];
        
        for (int i=0;i<datos.length;i++)
            datos[i]=(byte)f.read ();
        f.close();
        return datos;
    }
    
    //Escribe en el fichero de log de una ejecucin
    public void escribirEnFichero(String path, String texto)throws Exception {
        RandomAccessFile escribir=new RandomAccessFile (path+"/log.txt", "rw");
        escribir.seek (escribir.length ());
        escribir.write (texto.getBytes ());
        escribir.close ();
    }
    
    public static void mkdir(String parent, String dir) {
        if (!parent.endsWith("/"))
            parent+="/";
        File nuevo = new File(parent+dir);
        nuevo.mkdir();						
    }    
    
    public static String open(String fileName)  throws Exception
    {
        FileInputStream f=new FileInputStream(fileName);
        byte[] b=new byte[f.available()];
        f.read(b);
        f.close();
        return new String(b);
    }

	public TestValue[] getRandomValues(String tipo, int numberOfRandomTestValues) {
		// int#Integer#char#Character#double#float#long#short#string#byte#boolean#
		if (numberOfRandomTestValues==0)
			numberOfRandomTestValues=4;
		TestValue[] result=new TestValue[numberOfRandomTestValues];
		Random rnd=new Random();
		if (tipo.equals("int") || tipo.equals("Integer")) {
			for (int i=0; i<numberOfRandomTestValues; i++) {
				int n=rnd.nextInt();
				TestValue tv=new PrimitiveValue(""+n);
				result[i]=tv;
			}
		} else if (tipo.equals("char") || tipo.equals("Character")) {
			for (int i=0; i<numberOfRandomTestValues; i++) {
				byte b[]=new byte[1];
				rnd.nextBytes(b);
				TestValue tv=new PrimitiveValue(""+(char) b[0]);
				result[i]=tv;
			}
		} else if (tipo.equals("double") || tipo.equals("Double")) {
			for (int i=0; i<numberOfRandomTestValues; i++) {
				double n=rnd.nextDouble();
				TestValue tv=new PrimitiveValue(""+n);
				result[i]=tv;
			}
		} else if (tipo.equals("float") || tipo.equals("Float")) {
			for (int i=0; i<numberOfRandomTestValues; i++) {
				float n=rnd.nextFloat();
				TestValue tv=new PrimitiveValue(""+n);
				result[i]=tv;
			}
		} else if (tipo.equals("long") || tipo.equals("Long")) {
			for (int i=0; i<numberOfRandomTestValues; i++) {
				long n=rnd.nextLong();
				TestValue tv=new PrimitiveValue(""+n);
				result[i]=tv;
			}
		} else if (tipo.equals("short") || tipo.equals("Short")) {
			for (int i=0; i<numberOfRandomTestValues; i++) {
				int aux=rnd.nextInt();
				short n=(short) aux;
				TestValue tv=new PrimitiveValue(""+n);
				result[i]=tv;
			}
		} else if (tipo.equals("byte") || tipo.equals("Byte")) {
			for (int i=0; i<numberOfRandomTestValues; i++) {
				byte b[]=new byte[1];
				rnd.nextBytes(b);
				TestValue tv=new PrimitiveValue(""+ b[0]);
				result[i]=tv;
			}
		}  else if (tipo.equals("boolean") || tipo.equals("Boolean")) {
			for (int i=0; i<numberOfRandomTestValues; i++) {
				int aux=rnd.nextInt();
				TestValue tv;
				if (aux%2==0)
					tv=new PrimitiveValue("true");
				else
					tv=new PrimitiveValue("false");
				result[i]=tv;
			}
		} else if (tipo.equals("String")) {
			for (int i=0; i<numberOfRandomTestValues; i++) {
				int length=rnd.nextInt();
				StringBuffer sb=new StringBuffer("");
				for (int j=0; j<length; j++) {
					byte[] b=new byte[1];
					rnd.nextBytes(b);
					sb.append((char) b[0]);
				}
				TestValue tv=new PrimitiveValue(sb.toString());
				result[i]=tv;
			}
		}  
		return result;
	}
	
	public boolean save(Object [] v,String ruta) throws IOException{
        //Serializacion del objeto/s
        FileOutputStream fos = new FileOutputStream(ruta);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (int i=0;i<v.length;i++)oos.writeObject(v[i]);
        oos.flush();
        oos.close();
        return true;
    }
	
	public boolean save(Object v,String ruta) throws IOException{
        //Serializacion del objeto	
        FileOutputStream fos = new FileOutputStream(ruta);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(v);
        oos.flush();
        oos.close();
        return true;
    }
}

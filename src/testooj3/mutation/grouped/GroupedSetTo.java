package testooj3.mutation.grouped;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import testooj3.domain.TestCase;
import testooj3.domain.TestoojMethod;
import testooj3.domain.grouped.GroupedTestMethod;
import testooj3.mutation.Mutador;

public class GroupedSetTo extends Mutador
{
  String mDefaultValue;
  
	public GroupedSetTo(String df)
	{
		super();
    mDefaultValue=df.trim();
	}
	
	public  int mutar(TestoojMethod o, FileOutputStream f, int msgNum) throws IOException 
	{
    GroupedTestMethod original=(GroupedTestMethod) o;
    // Buscamos las líneas con declaraciones, que terminan en "};"
		Vector declaraciones=new Vector(), 
      corchetes=new Vector(), 
      subrayados=new Vector(), 
      iguales=new Vector(),
      numerosDeLinea=new Vector(),
      tipos=new Vector(),
      variables=new Vector();
		for (int i=0; i<original.getLineas().size()-2; i++) 
		{
      String linea=original.getLinea(i);
      if (linea.trim().endsWith("};")) {
        declaraciones.add(linea);
        int posCorchetes=linea.indexOf("[]");
        corchetes.add(new Integer(posCorchetes));
        int posSubrayado=linea.indexOf("_", posCorchetes);
        subrayados.add(new Integer(posSubrayado));
        int posIgual=linea.indexOf("=");
        iguales.add(new Integer(posIgual));
        String tipo=linea.substring(0, posCorchetes);
        tipos.add(tipo);
        numerosDeLinea.add(new Integer(i));
      }
		}

    for (int i=0; i<numerosDeLinea.size(); i++) 
    {
      String tipo=tipos.elementAt(i).toString().trim();
      if (esNumerico(tipo)) {
        TestoojMethod mutante=original.copy(msgNum++);
        mutante.setNombre(mutante.getNombre() + "_" + this.getMutantName() + "_" + msgNum);
        int numero=((Integer) numerosDeLinea.elementAt(i)).intValue();
        int posIgual=((Integer) iguales.elementAt(i)).intValue();
        String declaracion=mutante.getLinea(numero);
        if (getMutantName().equals("NEG")) 
        {
          //int posCorchetes=((Integer) corchetes.elementAt(numero-1)).intValue();
          int posCorchetes=((Integer) corchetes.elementAt(i)).intValue();
          String nombreParam=mutante.getLinea(numero).substring(posCorchetes+2, posIgual).trim();
          declaracion+="for (int i=0; i<" + nombreParam + ".length; i++) " + nombreParam + "[i]=-" + nombreParam + "[i];";          
        } else if (getMutantName().equals("MAX") || getMutantName().equals("MIN")) {
          int numeroDeComas=numeroDeComas(declaracion);
          declaracion=declaracion.substring(0, posIgual) + "={";
          for (int j=0; j<=numeroDeComas; j++) 
            declaracion+= this.getAdequateValue(tipo) + ", ";
          declaracion=declaracion.substring(0, declaracion.length()-2);
          declaracion=declaracion+"};\n";
        } else if (getMutantName().equals("ADD") || getMutantName().equals("SUB")) 
        {
          //int posCorchetes=((Integer) corchetes.elementAt(numero-1)).intValue();
          int posCorchetes=((Integer) corchetes.elementAt(i)).intValue();
          String nombreParam=mutante.getLinea(numero).substring(posCorchetes+2, posIgual).trim();
          declaracion+="for (int i=0; i<" + nombreParam + ".length; i++) " + 
            nombreParam + "[i]" + (this.getMutantName().equals("ADD") ? "+" : "-") + "=1;";
        } else if (getMutantName().equals("ZE")) {
          int numeroDeComas=numeroDeComas(declaracion);
          declaracion=declaracion.substring(0, posIgual) + "={";
          for (int j=0; j<=numeroDeComas; j++) 
            declaracion+= "0" + ", ";
          declaracion=declaracion.substring(0, declaracion.length()-2);
          declaracion=declaracion+"};\n";          
        }
        mutante.setLinea(numero, declaracion);
        original.add(mutante);
      } else if (this.getMutantName().equals("ZE"))
      {
        TestoojMethod mutante=original.copy(msgNum++);
        mutante.setNombre(mutante.getNombre() + "_" + this.getMutantName() + "_" + msgNum);
        int numero=((Integer) numerosDeLinea.elementAt(i)).intValue();
        int posIgual=((Integer) iguales.elementAt(i)).intValue();
        String declaracion=mutante.getLinea(numero);
        if (mDefaultValue.equals("0")) 
        {
          int numeroDeComas=numeroDeComas(declaracion);
          declaracion=declaracion.substring(0, posIgual) + "={";
          for (int j=0; j<=numeroDeComas; j++) 
            declaracion+= "null" + ", ";
          declaracion=declaracion.substring(0, declaracion.length()-2);
          declaracion=declaracion+"};\n";
        }
        mutante.setLinea(numero, declaracion);
        original.add(mutante);        
      }
    }
		return msgNum;
	}
  
  private String getMutantName() 
  {
     if (this.mDefaultValue.equals("MAX")) return "MAX";
     if (this.mDefaultValue.equals("MIN")) return "MIN";
     if (this.mDefaultValue.equals("NEG")) return "NEG";
      if (this.mDefaultValue.equals("ADD")) return "ADD";
       if (this.mDefaultValue.equals("SUB")) return "SUB";
     return "ZE";
  }
  
  private String getAdequateValue(String tipo) 
  {
    if (this.mDefaultValue.equals("MAX")) {
      if (tipo.equals("byte")) return ""+Byte.MAX_VALUE;
      if (tipo.equals("int")) return ""+ Integer.MAX_VALUE;
      if (tipo.equals("float")) return ""+ Float.MAX_VALUE;
      if (tipo.equals("double")) return ""+ Double.MAX_VALUE;
      if (tipo.equals("long")) return ""+ Long.MAX_VALUE;
    } else if (this.mDefaultValue.equals("MIN")) 
    {
      if (tipo.equals("byte")) return ""+ Byte.MIN_VALUE;
      if (tipo.equals("int")) return ""+ Integer.MIN_VALUE;
      if (tipo.equals("float")) return ""+ Float.MIN_VALUE;
      if (tipo.equals("double")) return ""+ Double.MIN_VALUE;
      if (tipo.equals("long")) return ""+ Long.MIN_VALUE;      
    } else if (this.mDefaultValue.trim().equals("0") || this.mDefaultValue.trim().equals("0.0"))
      return "0";
    return mDefaultValue;
  }
  
  private int numeroDeComas(String linea) 
  {
    int result=0;
    for (int i=0; i<linea.length(); i++) 
    {
      if (linea.charAt(i)==',') result++;
    }
    return result;
  }
  
  private boolean esNumerico(String tipo) 
  {
    String[] tipos={"byte", "int", "float", "double", "long"}; 
    for (int i=0; i<tipos.length; i++)
      if (tipos[i].equals(tipo.trim()))
        return true;
    return false;
  }
	
	public void mutar(TestCase tc, FileOutputStream f, int msgNum) throws IOException, ClassNotFoundException
	{
		// Construyo el método de junit correspondiente al test case original
		TestoojMethod jum=buildJUnitMethod(tc, msgNum);
		mutar(jum, f, msgNum);
	}
}
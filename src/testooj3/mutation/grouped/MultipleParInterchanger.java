package testooj3.mutation.grouped;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import testooj3.domain.TestCase;
import testooj3.domain.TestoojMethod;
import testooj3.domain.grouped.GroupedTestMethod;
import testooj3.mutation.Mutador;
import testooj3.mutation.grouped.multiple.Pair;

public class MultipleParInterchanger extends Mutador
{
	public MultipleParInterchanger()
	{
		super();
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
    
    Vector pares=new Vector();
    for (int i=0; i<tipos.size(); i++) 
    {
      String tipoA=tipos.elementAt(i).toString();
      for (int j=0; j<tipos.size(); j++) 
      {
        if (j!=i) 
        {
          String tipoB=tipos.elementAt(j).toString();
          if (tipoA.equals(tipoB)) 
          {
            int linea1=((Integer) numerosDeLinea.elementAt(i)).intValue();
            int linea2=((Integer) numerosDeLinea.elementAt(j)).intValue();
            String par1=original.getLinea(linea1).toString();
            String par2=original.getLinea(linea2).toString();
            int posSubrayado1=par1.indexOf("_");
            int posSubrayado2=par2.indexOf("_");
            int posIgual1=par1.indexOf("=", posSubrayado1);
            int posIgual2=par2.indexOf("=", posSubrayado2);
            par1=par1.substring(posSubrayado1, posIgual1).trim();
            par2=par2.substring(posSubrayado2, posIgual2).trim();
            if (par1.equals(par2)) {
              Pair par=new Pair(tipoA, linea1, linea2);
              Pair.add(pares, par);
            }
          }
        }
      }
    }

    /* Hay que recordar que los valores se guardan en arrays con el formato TIPO vX_Y, donde X es el nº de parámetro 
     e Y el nº de método. Para intercambiar parámetros, hay que buscar declaraciones donde el término Y sea igual
     y donde TIPO sea igual. 
     Por ejemplo: estas dos declaraciones hacen referencia dos parámetros de tipo doble del mismo método:
       double[] v0_0= {100, 1, 0};
       double[] v1_0= {15, 10, 5};
     */
     for (int i=0; i<pares.size(); i++) 
    {
      TestoojMethod mutante=original.copy(msgNum++);
      Pair par=(Pair) pares.elementAt(i);
      int nLinea1=par.getLinea1();
      int nLinea2=par.getLinea2();
      String linea1=mutante.getLinea(nLinea1);
      String linea2=mutante.getLinea(nLinea2);
      int posCorchetes1=((Integer) corchetes.elementAt(nLinea1-1)).intValue();
      int posCorchetes2=((Integer) corchetes.elementAt(nLinea2-1)).intValue();
      int posIgual1=((Integer) iguales.elementAt(nLinea1-1)).intValue();
      int posIgual2=((Integer) iguales.elementAt(nLinea2-1)).intValue();
      String nombreParametro1=linea1.substring(posCorchetes1+2, posIgual1).trim();
      String nombreParametro2=linea2.substring(posCorchetes2+2, posIgual2).trim();
      
      linea1=linea1.substring(0, posCorchetes1+2) + " " + nombreParametro2 + linea1.substring(posIgual1);
      linea2=linea2.substring(0, posCorchetes2+2) + " " + nombreParametro1 + linea2.substring(posIgual2);      
      mutante.setLinea(nLinea1, linea1);
      mutante.setLinea(nLinea2, linea2);
      
      String for1=mutante.getLinea(nLinea1+declaraciones.size()).trim();
      String tabs=for1.substring(0, for1.indexOf("f"));
      String forNuevo=tabs+"for (int c" + nombreParametro1 + "=" + nombreParametro1 + ".length-1; " +
        "c" + nombreParametro1 + ">=0; c" + nombreParametro1 + "--) {\n";
      mutante.setLinea(nLinea1+declaraciones.size(), for1);
      
      mutante.setNombre(mutante.getNombre() + "_PI_" + (msgNum));
      original.add(mutante);
    }
    

		return msgNum;
	}
	
	public void mutar(TestCase tc, FileOutputStream f, int msgNum) throws IOException, ClassNotFoundException
	{
		// Construyo el método de junit correspondiente al test case original
		TestoojMethod jum=buildJUnitMethod(tc, msgNum);
		mutar(jum, f, msgNum);
	}
}
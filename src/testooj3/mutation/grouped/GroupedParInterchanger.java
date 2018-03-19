package testooj3.mutation.grouped;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import testooj3.domain.TestCase;
import testooj3.domain.TestoojMethod;
import testooj3.domain.grouped.GroupedTestMethod;
import testooj3.mutation.Mutador;

public class GroupedParInterchanger extends Mutador
{
	public GroupedParInterchanger()
	{
		super();
	}
	
	public  int mutar(TestoojMethod o, FileOutputStream f, int msgNum) throws IOException 
	{
    GroupedTestMethod original=(GroupedTestMethod) o;  
    TestoojMethod mutante=original.copy(msgNum++);
		// Buscamos las líneas con declaraciones, que terminan en "};"
		Vector declaraciones=new Vector(), 
      corchetes=new Vector(), 
      subrayados=new Vector(), 
      iguales=new Vector(),
      numerosDeLinea=new Vector();;
		for (int i=0; i<mutante.getLineas().size()-2; i++) 
		{
      String linea=mutante.getLinea(i);
      if (linea.trim().endsWith("};")) {
        declaraciones.add(linea);
        int posCorchetes=linea.indexOf("[]");
        corchetes.add(new Integer(posCorchetes));
        int posSubrayado=linea.indexOf("_", posCorchetes);
        subrayados.add(new Integer(posSubrayado));
        int posIgual=linea.indexOf("=");
        iguales.add(new Integer(posIgual));
        numerosDeLinea.add(new Integer(i));
      }
		}
    
    /* Hay que recordar que los valores se guardan en arrays con el formato TIPO vX_Y, donde X es el nº de parámetro 
     e Y el nº de método. Para intercambiar parámetros, hay que buscar declaraciones donde el término Y sea igual
     y donde TIPO sea igual. 
     Por ejemplo: estas dos declaraciones hacen referencia dos parámetros de tipo doble del mismo método:
       double[] v0_0= {100, 1, 0};
       double[] v1_0= {15, 10, 5};
     */
    int numeroMetodoAnt=-1;
    String tipoAnt="", nombreParametroAnt="", declaracionAnt="";
    int lineaAnt=-1;
    boolean mutado=false;
    for (int i=0; i<declaraciones.size(); i++) {
      String declaracion=declaraciones.elementAt(i).toString();
      int posCorchetes=((Integer) corchetes.elementAt(i)).intValue();
      String tipo=declaracion.substring(0, posCorchetes);
      int posSubrayado=((Integer) subrayados.elementAt(i)).intValue();
      int posIgual=((Integer) iguales.elementAt(i)).intValue();
      int numeroMetodo=(new Integer(declaracion.substring(posSubrayado+1, posIgual))).intValue();
      if (numeroMetodoAnt!=-1 && numeroMetodoAnt==numeroMetodo) 
      {
        if (tipo.equals(tipoAnt)) 
        {
          String nombreParametro=declaracion.substring(posCorchetes+2, posIgual).trim();
          declaracion=declaracion.substring(0, posCorchetes+2) + " " + nombreParametroAnt + declaracion.substring(posIgual);
          declaracionAnt=declaracionAnt.substring(0, posCorchetes+2) + " " + nombreParametro + declaracionAnt.substring(posIgual);
          int numeroDeLinea=((Integer) numerosDeLinea.elementAt(i)).intValue();
          mutante.setLinea(numeroDeLinea, declaracion);
          //mutante.setLinea(numeroDeLinea-1, declaracionAnt);
          mutante.setLinea(lineaAnt, declaracionAnt);
          declaraciones.setElementAt(declaracionAnt, i-1);
          mutado=true;
        }
      } else 
      {
        numeroMetodoAnt=numeroMetodo;
        tipoAnt=tipo;
        nombreParametroAnt=declaracion.substring(posCorchetes+2, posIgual).trim();
        declaracionAnt=declaracion;
        lineaAnt=i+1;
      }
      if (mutado) break;
    }    
    if (mutado) {
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
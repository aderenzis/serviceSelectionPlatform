package testooj3.mutation.grouped;

import testooj3.domain.TestoojMethod;
import testooj3.domain.grouped.GroupedTestMethod;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import testooj3.mutation.Intercambiador;
import testooj3.mutation.grouped.multiple.Pair;

public class MultipleInterchanger extends Intercambiador 
{
  public MultipleInterchanger()
  {
    super();
  }

	public  int mutar(TestoojMethod o, FileOutputStream f, int msgNum) throws IOException 
	{
    GroupedTestMethod original=(GroupedTestMethod) o;  
    Vector lineasDeLlamada=new Vector();
    for (int i=0; i<original.getLineas().size(); i++)
      if (original.getLinea(i).trim().startsWith("pos++; "))
        lineasDeLlamada.add(new Integer(i));
    
    Vector pares=new Vector();
    for (int i=0; i<lineasDeLlamada.size(); i++) 
    {
      for (int j=0; j<lineasDeLlamada.size(); j++) 
      {
        if (j!=i) 
        {
          Pair par=new Pair("", ((Integer) lineasDeLlamada.elementAt(i)).intValue(), 
            ((Integer) lineasDeLlamada.elementAt(j)).intValue());
            Pair.add(pares, par);
        }
      }
    }
    
    for (int i=0; i<pares.size(); i++) 
    {
      TestoojMethod mutante=(TestoojMethod) original.copy(msgNum++);
      mutante.setNombre(original.getNombre() + "_IN_" + msgNum);
      
      Pair par=(Pair) pares.elementAt(i);
      int nLinea1=par.getLinea1();
      int nLinea2=par.getLinea2();
      String linea1=mutante.getLinea(nLinea1);
      String linea2=mutante.getLinea(nLinea2);
      mutante.setLinea(nLinea1, linea2);
      mutante.setLinea(nLinea2, linea1);
      original.add(mutante);
    }
		return msgNum;
	}    
}
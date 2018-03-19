package testooj3.mutation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import testooj3.domain.TestCase;
import testooj3.domain.TestoojMethod;

public class Comentador extends Mutador
{
	public Comentador()
	{
		super();
	}
	
	public  int mutar(TestoojMethod original, FileOutputStream f, int msgNum) throws IOException 
	{
		// Hago tantas copias del método de JUnit como líneas haya		(excepto el constructor y el return)
		Vector tmp=new Vector();
		for (int i=0; i<original.getLineas().size()-2; i++) 
		{
			TestoojMethod mutante=original.copy(msgNum++);
			mutante.setNombre(mutante.getNombre() + "_CL_" + (i+1));
			tmp.addElement(mutante);
		}
		
		// Ejecuto la mutación
		for (int i=0; i<tmp.size(); i++) 
		{
			TestoojMethod mutante=(TestoojMethod) tmp.elementAt(i);
			mutante.setLinea(i+1, "/*" + mutante.getLinea(i+1) + "*/");
			original.add(mutante);
			if (f!=null) 
			{
				f.write(mutante.toString().getBytes());
			}
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
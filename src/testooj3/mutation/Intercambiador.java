package testooj3.mutation;

import java.io.FileOutputStream;
import java.io.IOException;

import testooj3.domain.TestCase;
import testooj3.domain.TestoojMethod;

public class Intercambiador extends Mutador
{
	public Intercambiador()
	{
		super();
	}
	
	public int mutar(TestoojMethod original, FileOutputStream f, int msgNum) throws IOException 	
	{
		if (original.getLineas().size()<=3)
			return msgNum;

		// Hago una copia del método de JUnit 
		TestoojMethod mutante=original.copy(msgNum++);
		mutante.setNombre(mutante.getNombre() + "_IN");
		
		// Ejecuto la mutación, intercambiando la línea 2 por la 3
		String linea1=mutante.getLinea(1);
		String linea2=mutante.getLinea(2);
		mutante.setLinea(2, linea1);
		mutante.setLinea(1, linea2);
		original.add(mutante);
		if (f!=null) 
		{
			f.write(mutante.toString().getBytes());
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
package testooj3.mutation;

import java.io.FileOutputStream;
import java.io.IOException;

import testooj3.domain.TestCase;
import testooj3.domain.TestoojMethod;

public class Nullifier extends Mutador
{
	public Nullifier()
	{
		super();
	}
	
	public int mutar(TestoojMethod original, FileOutputStream f, int msgNum) throws IOException 	
	{
		if (!original.getLinea(original.getLineas().size()-1).startsWith("return"))
			return msgNum;

		// Hago una copia del método de JUnit 
		TestoojMethod mutante=original.copy(msgNum++);
		mutante.setNombre(mutante.getNombre() + "_NUL");
		
		// Ejecuto la mutación, sustituyendo la línea con el return por "return null"
		String linea="return null;";
		mutante.setLinea(mutante.getLineas().size()-1, linea);
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
		TestoojMethod jum=buildJUnitMethod( tc, msgNum);
		mutar(jum, f, msgNum);
	}
}
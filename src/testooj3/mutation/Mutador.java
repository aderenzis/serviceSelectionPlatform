package testooj3.mutation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import testooj3.domain.Operation;
import testooj3.domain.TestCase;
import testooj3.domain.TestoojMethod;

public abstract class Mutador 
{
	public Mutador()
	{
	}
	
	public static TestoojMethod buildJUnitMethod(TestCase tc, int msgNum) throws IOException, FileNotFoundException, ClassNotFoundException {
		TestoojMethod jum=new TestoojMethod(tc.getNombre(), true);
		jum.setTipo(tc.getTipo());
		Vector valores=(Vector) tc.getValoresParametrosConstructor();
	    Vector lineas=tc.getConstructor().getDeclaraciones(valores, "c");
	    for (int j=0; j<lineas.size(); j++)
	        jum.getLineas().add(lineas.get(j).toString());
		String linea="\t\t" + tc.getConstructor().getNombre() + " " + tc.getConstructor().getLlamada("o", "pars", 0);
		jum.addLinea(linea);
		for (int i=0; i<tc.getMetodos().size(); i++) 
		{
			Operation m=(Operation) tc.getMetodos().elementAt(i);
			valores=(Vector) tc.getValoresParametros(i);
			linea="\t\ttry {";
			linea+="\t\t\t" + m.getAssertsPre();        
			linea+="\n\t\t\t" + m.getLlamada("o", TestCase.valoresParametrosM(valores, m.getParametros()), i);                  
			linea+=	"\t\t" + m.getAssertsPost();
			linea+="\n\t\t}\n\t\tcatch (Exception ex) { msg[" + msgNum + "]+=ex.toString(); }";
			linea+="\n\t\tcatch (AssertionError ae) {}\n";
			jum.addLinea(linea);        
		}	
		jum.addLinea("\n\t\treturn o;\n");		
		return jum;
	}
	
	public abstract void mutar(TestCase tc, FileOutputStream f, int msgNum) throws IOException, ClassNotFoundException;
	public abstract int mutar(TestoojMethod original, FileOutputStream f, int msgNum) throws IOException;	
}
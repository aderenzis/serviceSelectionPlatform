package testooj3.mutation.grouped;

import java.io.FileOutputStream;
import java.io.IOException;

import testooj3.domain.TestoojMethod;
import testooj3.domain.grouped.GroupedTestMethod;
import testooj3.mutation.Nullifier;

public class GroupedNullifier extends Nullifier
{
  public GroupedNullifier()
  {
    super();
  }

	public  int mutar(TestoojMethod o, FileOutputStream f, int msgNum) throws IOException 
	{
    GroupedTestMethod original=(GroupedTestMethod) o;		// Hago una sola copia del método original JUnit  
    TestoojMethod mutante=(TestoojMethod) original.copy(msgNum);
    mutante.setNombre(original.getNombre() + "_NUL");
    int i=0;
    String linea="";
    do 
    {
      linea=original.getLinea(i++);
    } while (!linea.trim().startsWith("result.add(o);"));
    // En este punto, linea vale "resul.add(o);"
    linea="\t\t\tresult.add(null);";
    mutante.setLinea(i-1, linea);
    original.add(mutante);
    if (f!=null) 
    {
      f.write(mutante.toString().getBytes());
    }
		return msgNum;
	}    
}
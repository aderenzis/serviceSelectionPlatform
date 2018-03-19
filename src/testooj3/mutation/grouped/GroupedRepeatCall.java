package testooj3.mutation.grouped;

import testooj3.auxiliares.Auxi;
import testooj3.domain.TestoojMethod;
import testooj3.domain.grouped.GroupedTestMethod;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import testooj3.mutation.Comentador;

public class GroupedRepeatCall extends Comentador 
{
  public GroupedRepeatCall()
  {
    super();
  }

  public int mutar(TestoojMethod o, FileOutputStream f, int msgNum) throws IOException {
    GroupedTestMethod original=(GroupedTestMethod) o;
		// Hago tantas copias del método de JUnit como instrucciones haya	dentro de los bucles for
    int i=0;
    String linea="";
    do 
    {
      linea=original.getLinea(i++);
    } while (!linea.trim().startsWith("try {"));
    int primeraLineaEjecutable=i;
    boolean tieneInvariantes=false;
    do 
    {
      linea=original.getLinea(i++);
      if (linea.indexOf("InvariantViolationException")!=-1)
        tieneInvariantes=true;
    } while (!linea.trim().startsWith("}"));
    int ultimaLineaEjecutable=i-2;
    if (tieneInvariantes)
      ultimaLineaEjecutable=ultimaLineaEjecutable-1;

    Vector tmp=new Vector();    
    for (i=0; i<ultimaLineaEjecutable-primeraLineaEjecutable; i++) 
    {
      TestoojMethod mutante=(TestoojMethod) original.copy(msgNum++);
			mutante.setNombre(mutante.getNombre() + "_RC_" + (i+1));
			tmp.addElement(mutante);
		}
		
		// Ejecuto la mutación
    int lineaMutante=primeraLineaEjecutable+1;
		for (i=0; i<tmp.size(); i++) 
		{
			TestoojMethod mutante=(TestoojMethod) tmp.elementAt(i);
      String texto=mutante.getLinea(lineaMutante);
      int posLlave=texto.indexOf("{");
      int posMessages=texto.indexOf("messages.add(\"\");");
      String llamada1=texto.substring(posLlave+1, posMessages);
      String llamada2=new String(llamada1);
      llamada2=Auxi.substituteAll(llamada2, "result", "xxx");
      llamada2=Auxi.substituteAll(llamada2, "xxx", "result2");
      texto=texto.substring(0, posLlave) + "{" + llamada1 + " messages.add(\"\");} {" + llamada2 + "}\n";
			mutante.setLinea(lineaMutante, texto);
			original.add(mutante);
      lineaMutante++;
			if (f!=null) 
			{
				f.write(mutante.toString().getBytes());
			}
		}	
		return msgNum;
	}    
}
package testooj3.mutation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import testooj3.domain.TestoojMethod;
import testooj3.domain.TestCase;

public class ParInterchanger extends Mutador
{
  public ParInterchanger()
  {
    super();
  }
  
	public  int mutar(TestoojMethod original, FileOutputStream f, int msgNum) throws IOException 
	{
    if (original.getLineas().size()==3) 
      return msgNum;
    TestoojMethod mutante=original.copy(msgNum++);
		// Buscamos las líneas con declaraciones, que son las que empiezan por try (las líneas vienen juntas aunque separadas por \n)
		int posicionTry=-1;
    boolean mutado=false;
    String linea;
    int i=0;
    do {
      linea=mutante.getLinea(i);
      i++;
    } while (linea.startsWith("try {"));
    Vector posicionesTry=new Vector(); //i-1;
    
    for (i=0; !mutado && i<posicionesTry.size(); i++) 
    {
      int pos=((Integer) posicionesTry.elementAt(i)).intValue();
      linea=mutante.getLinea(pos).trim();
      mutado=mutar(mutante, linea, msgNum);
    }
    
    if (mutado) {
      mutante.setNombre(mutante.getNombre() + "_PI_" + (msgNum));
      original.add(mutante);
    }
		return msgNum;
	}
  
  protected boolean mutar(TestoojMethod mutante, String linea, int msgNum)
  {
    boolean mutado=false;
    Vector lineas=new Vector(),
      declaraciones=new Vector(), 
      iguales=new Vector(),
      numerosDeLinea=new Vector();
    int posAnt=0;
    for (int i=0; i<linea.length(); i++) 
    {
      if (linea.charAt(i)=='\n') 
      {
        if (linea.substring(posAnt, linea.indexOf("\n", posAnt+1)).trim().length()>0) 
          lineas.add(linea.substring(posAnt, linea.indexOf("\n", posAnt+1)).trim());
        posAnt=linea.indexOf("\n", posAnt+1);
      }
    }
    int i=0;
    do 
    {
      linea=lineas.elementAt(i+1).toString().trim();
      if (!linea.trim().startsWith("o."))
        declaraciones.add(linea);
      i++;
    } while (!linea.trim().startsWith("o."));
    if (declaraciones.size()<=1) return false;
    int lineaA=-1, lineaB=-1;
    for (int j=0; j<declaraciones.size(); j++) 
    {
      String declaracion=declaraciones.elementAt(j).toString().trim();
      String tipo=declaracion.substring(0, declaracion.indexOf(" x")).trim();
      for (int k=0; k<declaraciones.size(); k++) 
      {
        if (k!=j) 
        {
          String otraDeclaracion=declaraciones.elementAt(k).toString().trim();
          String otroTipo=declaracion.substring(0, otraDeclaracion.indexOf(" x")).trim();
          if (otroTipo.equals(tipo)) 
          {
            String var1=new String(declaracion.substring(declaracion.indexOf(" x"), declaracion.indexOf("=")));
            String var2=new String(otraDeclaracion.substring(otraDeclaracion.indexOf(" x"), otraDeclaracion.indexOf("=")));
            declaraciones.set(j, tipo + " " + var2 + "=" + declaracion.substring(declaracion.indexOf("(")));
            declaraciones.set(k, tipo + " " + var1 + "=" + otraDeclaracion.substring(otraDeclaracion.indexOf("(")));
            lineaA=j; lineaB=k;
            mutado=true;
          }
        }
        if (mutado) break;
      }
      if (mutado) break;
    }
    String result="";
    for (i=0; i<declaraciones.size(); i++)
      result+="\t\t\t" + declaraciones.elementAt(i).toString() + "\n";
    result="\t\ttry {\n" + result + "\t\t\t" + linea + "\n\t\t}\n\t\tcatch (Exception ex) { " +
      "msg[" + (msgNum-1) + "]+=ex.toString(); }\n\t\tcatch (AssertionError ae) {}";
    mutante.getLineas().set(lineaB, result);
    return mutado;
  }    
	
	public void mutar(TestCase tc, FileOutputStream f, int msgNum) throws IOException, ClassNotFoundException
	{
		// Construyo el método de junit correspondiente al test case original
		TestoojMethod jum=buildJUnitMethod(tc, msgNum);
		mutar(jum, f, msgNum);
	}  
}
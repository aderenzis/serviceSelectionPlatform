package testooj3.mutation.junit;
import java.util.Vector;
import testooj3.domain.TestMethod;

public class RC extends JUnitMutantOperator
{
  public RC(TestMethod method)
  {
    super(method);    
  }
  
  public void buildMutants() 
  {
    Vector inicioBloque=new Vector(), finBloque=new Vector();
    for (int i=0; i<this.mOriginalMethod.getLineas().size(); i++) 
    {
      String linea=this.mOriginalMethod.getLinea(i);
      if (linea.trim().equals("{"))
        inicioBloque.add(new Integer(i));
      else if (linea.trim().equals("}"))
        finBloque.add(new Integer(i));
    }
    inicioBloque.remove(0); finBloque.remove(0);
    for (int i=inicioBloque.size()-1; i>=0; i--) 
    {
      int lineaPrimera=((Integer) inicioBloque.get(i)).intValue();
      int lineaFinal=((Integer) finBloque.get(i)).intValue();
      Vector bloque=new Vector();
      for (int j=lineaPrimera; j<=lineaFinal; j++) 
      {
        bloque.add(this.mOriginalMethod.getLinea(j));
      }
      TestMethod mutante=this.mOriginalMethod.copy("RC_" + (i+1));
      for (int j=0; j<bloque.size(); j++) 
      {
        String linea=bloque.get(j).toString();
        mutante.getLineas().insertElementAt(linea, lineaFinal+j+1);
      }
      this.mMutantes.add(mutante);
    }
  }
}
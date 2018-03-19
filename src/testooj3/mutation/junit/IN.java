package testooj3.mutation.junit;
import java.util.Vector;
import testooj3.domain.TestMethod;

public class IN extends JUnitMutantOperator
{
  public IN(TestMethod method)
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
    if (inicioBloque.size()<=1) return;
    
    for (int i=0; i<inicioBloque.size()-1; i++) 
    {
      TestMethod mutante=this.mOriginalMethod.copy("IN_" + (i+1));
      Integer inicioBloque1=(Integer) inicioBloque.get(i);
      Integer finBloque1=(Integer) finBloque.get(i);
      Integer inicioBloque2=(Integer) inicioBloque.get(i+1);
      Integer finBloque2=(Integer) finBloque.get(i+1);
      Vector lineasBloque1=new Vector();
      Vector lineasBloque2=new Vector();
      for (int j=inicioBloque1.intValue(); j<=finBloque1.intValue(); j++) 
      {
        lineasBloque1.add(mutante.getLinea(j));
        mutante.setLinea(j, "");
      }
      for (int j=inicioBloque2.intValue(); j<=finBloque2.intValue(); j++) 
      {
        lineasBloque2.add(mutante.getLinea(j));
        mutante.setLinea(j, "");
      }
      Vector nuevoBloque=new Vector();
      for (int j=0; j<lineasBloque2.size(); j++)
        nuevoBloque.add(lineasBloque2.get(j));
      for (int j=0; j<lineasBloque1.size(); j++)
        nuevoBloque.add(lineasBloque1.get(j));
      for (int j=0; j<nuevoBloque.size(); j++) 
      {
        mutante.setLinea(inicioBloque1.intValue()+j, nuevoBloque.get(j).toString());
      }
      this.mMutantes.add(mutante);
    }
  }
}
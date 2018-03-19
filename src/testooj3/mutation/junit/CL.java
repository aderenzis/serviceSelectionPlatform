package testooj3.mutation.junit;
import java.util.Vector;
import testooj3.domain.TestMethod;

public class CL extends JUnitMutantOperator
{
  public CL(TestMethod method)
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
    for (int i=0; i<inicioBloque.size(); i++) 
    {
      TestMethod mutante=this.mOriginalMethod.copy("CL_" + (i+1));
      Integer inicio=(Integer) inicioBloque.get(i);
      String lineaInicial=mutante.getLinea(inicio.intValue());
      lineaInicial="/*" + lineaInicial;
      Integer fin=(Integer) finBloque.get(i);
      String lineaFinal=this.mOriginalMethod.getLinea(fin.intValue());
      lineaFinal=lineaFinal + "*/";
      mutante.setLinea(inicio.intValue(), lineaInicial);
      mutante.setLinea(fin.intValue(), lineaFinal);
      this.mMutantes.add(mutante);
    }
  }
}
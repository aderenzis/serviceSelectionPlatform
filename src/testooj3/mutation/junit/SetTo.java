package testooj3.mutation.junit;
import java.util.Vector;
import testooj3.domain.TestMethod;
import testooj3.mutation.Operators;

public class SetTo extends JUnitMutantOperator
{
  protected int mOperator;
  
  public SetTo(TestMethod method, int operator)
  {
    super(method);    
    mOperator=operator;
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
    int numeroDeBloques=inicioBloque.size();
    for (int i=0; i<numeroDeBloques; i++) 
    {
      int lineaInicial=((Integer) (inicioBloque.get(i))).intValue();
      int lineaFinal=((Integer) (finBloque.get(i))).intValue();
      procesarBloque(this.mOriginalMethod, lineaInicial+1, lineaFinal-1);
    }
  }
  
  protected void procesarBloque(TestMethod metodo, int lI, int lF) 
  {
    for (int i=lI; i<lF; i++)
    {
      TestMethod mutante=metodo.copy(Operators.getName(this.mOperator)+"_"+ i);
      String linea=mutante.getLinea(i);
      String lineaMutada=getValue(linea);
      if (!lineaMutada.equals(linea)) {
        mutante.setLinea(i, lineaMutada);
        this.mMutantes.add(mutante);
      }
    }
  }
  
  protected String getValue(String linea) 
  {
    if (linea.trim().startsWith("byte") && linea.indexOf("arg")!=-1) return getByte(linea);
    if (linea.trim().startsWith("int") && linea.indexOf("arg")!=-1) return getInt(linea);
    if (linea.trim().startsWith("float") && linea.indexOf("arg")!=-1) return getFloat(linea);
    if (linea.trim().startsWith("double") && linea.indexOf("arg")!=-1) return getDouble(linea);
    if (linea.trim().startsWith("long") && linea.indexOf("arg")!=-1) return getLong(linea);
    return linea;
  }

  protected String getByte(String linea) 
  {
    String izda=linea.substring(0, linea.lastIndexOf(")")+1);
    String valor=" " + linea.substring(linea.lastIndexOf(")")+1, linea.length()-1).trim();
    switch (this.mOperator) {
      case Operators.ZE : izda+=" 0"; break;
      case Operators.MAX : izda+=" Byte.MAX_VALUE";  break;
      case Operators.MIN : izda+=" Byte.MIN_VALUE"; break;
      case Operators.NEG : izda+=" -("+valor.trim() + ")"; break;
      case Operators.ADD : izda+=valor+"+1"; break;
      case Operators.SUB : izda+=valor+"-1"; break;
    }
    linea=izda+";";
    return linea;
  }
  
  protected String getInt(String linea) 
  {
    String izda=linea.substring(0, linea.lastIndexOf(")")+1);
    String valor=" " + linea.substring(linea.lastIndexOf(")")+1, linea.length()-1).trim();
    switch (this.mOperator) {
      case Operators.ZE : izda+=" 0"; break;
      case Operators.MAX : izda+=" Integer.MAX_VALUE";  break;
      case Operators.MIN : izda+=" Integer.MIN_VALUE"; break;
      case Operators.NEG : izda+=" -("+valor.trim() + ")"; break;
      case Operators.ADD : izda+=valor+"+1"; break;
      case Operators.SUB : izda+=valor+"-1"; break;
    }
    linea=izda+";";
    return linea;
  }
  
  protected String getFloat(String linea) 
  {
    String izda=linea.substring(0, linea.lastIndexOf(")")+1);
    String valor=" " + linea.substring(linea.lastIndexOf(")")+1, linea.length()-1).trim();
    switch (this.mOperator) {
      case Operators.ZE : izda+=" 0"; break;
      case Operators.MAX : izda+=" Float.MAX_VALUE";  break;
      case Operators.MIN : izda+=" Float.MIN_VALUE"; break;
      case Operators.NEG : izda+=" -("+valor.trim() + ")"; break;
      case Operators.ADD : izda+=valor+"+1"; break;
      case Operators.SUB : izda+=valor+"-1"; break;
    }
    linea=izda+";";
    return linea;
  }
  
  protected String getDouble(String linea) 
  {
    String izda=linea.substring(0, linea.lastIndexOf(")")+1);
    String valor=" " + linea.substring(linea.lastIndexOf(")")+1, linea.length()-1).trim();
    switch (this.mOperator) {
      case Operators.ZE : izda+=" 0"; break;
      case Operators.MAX : izda+=" Double.MAX_VALUE";  break;
      case Operators.MIN : izda+=" Double.MIN_VALUE"; break;
      case Operators.NEG : izda+=" -("+valor.trim() + ")"; break;
      case Operators.ADD : izda+=valor+"+1"; break;
      case Operators.SUB : izda+=valor+"-1"; break;
    }
    linea=izda+";";
    return linea;
  }
  
  protected String getLong(String linea) 
  {
    String izda=linea.substring(0, linea.lastIndexOf(")")+1);
    String valor=" " + linea.substring(linea.lastIndexOf(")")+1, linea.length()-1).trim();
    switch (this.mOperator) {
      case Operators.ZE : izda+=" 0"; break;
      case Operators.MAX : izda+=" Long.MAX_VALUE";  break;
      case Operators.MIN : izda+=" Long.MIN_VALUE"; break;
      case Operators.NEG : izda+=" -("+valor.trim() + ")"; break;
      case Operators.ADD : izda+=valor+"+1"; break;
      case Operators.SUB : izda+=valor+"-1"; break;
    }
    linea=izda+";";
    return linea;
  }
}
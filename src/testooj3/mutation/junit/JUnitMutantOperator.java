package testooj3.mutation.junit;
import java.util.Vector;
import testooj3.domain.TestMethod;

/**
 * @author  andres
 */
public abstract class JUnitMutantOperator 
{
  protected TestMethod mOriginalMethod;
  protected Vector mMutantes=new Vector();
  
  public JUnitMutantOperator(TestMethod method)
  {
    this.mOriginalMethod=method;
  }
  
  public abstract void buildMutants();
  
  public Vector getMutantes() 
  {
    return mMutantes;
  }
}
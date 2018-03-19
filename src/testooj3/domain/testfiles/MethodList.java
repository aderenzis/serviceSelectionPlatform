package testooj3.domain.testfiles;
import java.util.Vector;
import testooj3.domain.TestMethod;

public class MethodList 
{
  protected Vector mMethods;
  
  public MethodList()
  {
    mMethods=new Vector();
  }
  
  public void add(TestMethod m) 
  {
    this.mMethods.add(m);
  }
  
  public String toString() 
  {
    StringBuffer result=new StringBuffer();
    for (int i=0; i<mMethods.size(); i++) {
      TestMethod m=(TestMethod) mMethods.elementAt(i);
      result.append(m.toString(false));
      result.append("\n");
    }
    return result.toString();
  }
  
  public int size() 
  {
    return mMethods.size();
  }
  
  public TestMethod get(int i) 
  {
    return (TestMethod) mMethods.get(i);
  }
}
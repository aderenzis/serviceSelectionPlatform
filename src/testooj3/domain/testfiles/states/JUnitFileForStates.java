package testooj3.domain.testfiles.states;
import java.io.FileOutputStream;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.Operation;
import testooj3.domain.TestMethod;
import testooj3.domain.states.State;
import testooj3.domain.testfiles.junit.JUnitFile;
import testooj3.domain.testfiles.junit.JUnitHeader;

/**
 * @author  andres
 */
public class JUnitFileForStates extends JUnitFile 
{
  protected Interface mClass;
  
  public JUnitFileForStates(String className, Interface c)
  {
      super(className, c);
      mHeader = new JUnitStatesHeader(className);
  }
  
  public void setClass(Interface c) {
      mClass=c;
  }
  
  protected String getCheckState() 
  {
    String result="\tprotected String checkState(" + mClass.getName() + " obtained) {\n" ;
    result+="\t\ttry {\n";
    for (int i=0; i<mClass.getStates().size(); i++) 
    {
      State st=(State) mClass.getState(i);
      result+="\t\t\tif (" + st.getDescription() + ") return \"" + st.getName() + "\";\n";
    }
    result+="\t\t\treturn \"UNDEFINED\";\n";
    result+="\t\t}\n";
    result+="\t\tcatch (Exception ex) {\n";
    result+="\t\t\treturn \"Exception: \" + ex.toString();\n";
    result+="\t\t}\n";
    result+="\t}\n\n";
    return result;
  }  
  
  protected String getDetailedState() 
  {
    String result="\tprotected String checkDetailedState(" + mClass.getName() + " obtained) {\n";
    result+="\t\tString result=\"\";\n";
    result+="\t\tresult+=\"<detailed_state>\";\n";
    for (int i=0; i<mClass.getObserverMethods().size(); i++) {
      Operation m=(Operation) mClass.getObserverMethods().get(i);
      result+="\t\ttry {\n";
      result+="\t\t\tresult+=\"<observer_method name='" + m.getId() + "' value='\" + obtained." + m.getId() + " + \"' exception='false'/>\";\n";
      result+="\t\t}\n";
      result+="\t\tcatch (Exception ex) {\n";
      result+="\t\t\tresult+=\"<observer_method name='" + m.getId() + "' value'\" + ex.toString() + \"' exception='true'/>\";\n";
      result+="\t\t}\n";
    }
    result+="\t\tresult+=\"</detailed_state>\";\n";
    result+="\t\treturn result;\n";
    result+="\t}\n\n";
    return result;
  }
  
  protected String getLastTestCase() 
  {
      String result="\tpublic void testLast() {\n" +
        "\t\ttry {\n" +
        "\t\t\tString cab=\"</states>\\n\";\n" +
        "\t\t\tmXML.write(cab.getBytes());\n" +
        "\t\t\tmXML.close();\n" +
        "\t\t}\n" +
        "\t\tcatch (Exception ex) {\n" +
        "\t\t\tSystem.err.println(\"Error: \" + ex.toString());\n" +
        "\t\t}\n" +
        "\t}\n\n";
      return result;
  }
  
  public int saveTo(FileOutputStream f, int firstTestCase) throws Exception {
      String result = mHeader.toString() + "\n";
      f.write(result.getBytes());
      int totalSize=0, lastTestCase=0;
      int i=firstTestCase;
      while (i<=this.mNumberOfTestCases && totalSize<Configuration.getInstance().getSplitJUnitEvery()) {
          String fileName = mSessionPath + "\\" + i;
          TestMethod m = TestMethod.load(fileName, TestMethod.STATES_JUNIT);
          result = m.toString(false) + "\n";
          f.write(result.getBytes());
          totalSize+=result.getBytes().length;
          i++;
      }
      result=this.getCheckState();
      f.write(result.getBytes());
      result=this.getDetailedState();
      f.write(result.getBytes());
      lastTestCase=i;
      result=this.getLastTestCase();
      f.write(result.getBytes());
      result = getMain() + "\n}";
      f.write(result.getBytes());
      return lastTestCase;
  }
  
  protected String getMain() {
      String result = "\tpublic static void main (String [] args) {\n";
      result += "\t\tjunit.swingui.TestRunner.run(" + mClassName
              + ".class);\n";
      result += "\n\t}\n";
      return result;
  }

}
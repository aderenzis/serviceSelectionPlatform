package testooj3.domain.testfiles.states;
import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.domain.testfiles.Header;

public class JUnitStatesHeader extends Header
{
    public JUnitStatesHeader(String className)
    {
        super();
        String xmlFileName=Auxi.escapa(Configuration.getInstance().getResultsPath() + "States" + className + ".xml");
        mCode=getPackageDeclaration();
        mCode+="import java.io.*;\n";
        mCode+="import java.util.Vector;\n";
        mCode+="import junit.framework.*;\n";
        mCode+="\npublic class " + className + " extends TestCase" + " {\n" +
        "\tprotected static java.io.FileOutputStream mXML;\n" + 
        "\tpublic " + className + "() {\n" +
        "\t\tsuper();\n" +
        "\t\ttry {\n" +
        "\t\t\tmXML=new FileOutputStream(\"" + xmlFileName + "\");\n" + 
        "\t\t\tString cab=\"<states>\";\n" +
        "\t\t\tmXML.write(cab.getBytes());\n" +
        "\t\t}\n" +
        "\t\tcatch (Exception ex) {\n" + 
        "\t\tSystem.err.println(\"Error: \" + ex.toString());\n" + 
        "\t\t}\n" + 
        "\t}\n";
    }
}
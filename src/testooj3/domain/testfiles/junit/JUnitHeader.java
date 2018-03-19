package testooj3.domain.testfiles.junit;

import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.testfiles.Header;

public class JUnitHeader extends Header 
{
    public JUnitHeader(String className, Interface c)
    {
        super();
        mCode=getPackageDeclaration();
        mCode+="import java.io.*;\n";
        mCode+="import java.util.Vector;\n";
        mCode+="import junit.framework.*;\n";
        mCode+="import " + c.getName() + ";\n";
        mCode+="\npublic class " + className + " extends TestCase" + " {\n" +
        "\tpublic " + className + "() {\n" +
        "\t\tsuper();\n" +
        "\t}\n";
    }
}
package testooj3.gui.guitesting;

import testooj3.domain.Interface;
import testooj3.domain.testfiles.Header;

public class GuiJUnitHeader extends Header {
    public GuiJUnitHeader(String className, Interface c)
    {
        super();
        mCode=getPackageDeclaration();
        mCode+="import java.io.*;\n";
        mCode+="import java.util.Vector;\n";
        mCode+="import junit.framework.*;\n";
        mCode+="import java.lang.reflect.Field;\n";
        mCode+="import org.uispec4j.UISpecTestCase;\n";
        mCode+="import " + c.getName() + ";\n";
        mCode+="\npublic class " + className + " extends TestCase" + " {\n" +
        "\tpublic " + className + "() {\n" +
        "\t\tsuper();\n" +
        "\t}\n";
    }
}

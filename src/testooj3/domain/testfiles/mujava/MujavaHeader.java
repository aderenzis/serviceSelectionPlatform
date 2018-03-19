package testooj3.domain.testfiles.mujava;

import testooj3.domain.testfiles.Header;

public class MujavaHeader extends Header 
{
    public MujavaHeader(String className)
    {
        super();
        mCode="";
        mCode+="import java.io.*;\n";
        mCode+="import java.util.Vector;\n";
        mCode+="\npublic class " + className + " " + " {\n" +
        "\tpublic " + className + "() {\n" +
        "\t}\n";
    }
    
    protected String getPackageDeclaration() {
        return "";
    }
}
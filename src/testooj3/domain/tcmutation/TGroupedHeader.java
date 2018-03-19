package testooj3.domain.tcmutation;

import testooj3.domain.Interface;
import testooj3.domain.testfiles.Header;

public class TGroupedHeader extends Header {
    public TGroupedHeader(String className, Interface c)
    {
        super();
        mCode=getPackageDeclaration();
        mCode+="import java.io.*;\n";
        mCode+="import java.util.Vector;\n";
        mCode+="import " + c.getName() + ";\n";
        mCode+="\npublic class " + className + " " + " {\n" +
        "\tprotected FileOutputStream resultsFile;\n" +
        "\tpublic " + className + "() {\n" +
        "\t\tsuper();\n" +
        "\t\tresultsFile=new FileOutputStream(\"results.txt\");\n" +
        "\t}\n" +
        getSaveMethod();
    }

    /**
     * @return
     */
    private String getSaveMethod() {
        String code="\n\tpublic void save(String text) {\n" +
        	"\t\ttry { resultsFile.write(text.getBytes()); }\n" +
        	"\t\tcatch (Exception ex) {}\n" +
        	"\t}\n";
        return code;
    }
}

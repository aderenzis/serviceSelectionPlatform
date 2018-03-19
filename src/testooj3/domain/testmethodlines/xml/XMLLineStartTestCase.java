package testooj3.domain.testmethodlines.xml;

import testooj3.domain.TestCase;

/**
 * @author Administrador
 */
public class XMLLineStartTestCase extends XMLLine {  
    private String constructorName;

    public XMLLineStartTestCase(TestCase tc) {
        this.constructorName=tc.getNombre();
    }

     public String toString() {
        return "\t\tString xml=\"<" + this.constructorName + ">\";\n";
    }

}

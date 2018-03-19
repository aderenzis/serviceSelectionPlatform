package testooj3.domain.testmethodlines.xml;

import testooj3.domain.TestCase;

/**
 * @author Administrador
 */
public class XMLLineEndTestCase extends XMLLine {

    private String constructorName;

    /**
     * @param tc
     */
    public XMLLineEndTestCase(TestCase tc) {
        this.constructorName=tc.getNombre();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "\t\txml+=\"</" + constructorName + ">\";\n";
    }

}

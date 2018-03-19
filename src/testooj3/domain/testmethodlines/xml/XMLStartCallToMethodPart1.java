package testooj3.domain.testmethodlines.xml;

import testooj3.domain.Operation;

/**
 * @author Administrador
 */
public class XMLStartCallToMethodPart1 extends XMLLine {

    private String methodName;

    /**
     * @param m
     */
    public XMLStartCallToMethodPart1(Operation m) {
        this.methodName=m.getNombre();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "\t\txml+=\"<operation name='" + methodName + "'>\";\n" +
        	"\t\txml+=\"<sourceState name='\" + checkState(obtained) + \"'/>\";\n";
    }

}

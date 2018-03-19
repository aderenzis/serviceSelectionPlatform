package testooj3.domain.testmethodlines.xml;

import testooj3.domain.TParameter;
import testooj3.domain.TestValue;

/**
 * @author Administrador
 */
public class XMLParameterDeclarationLine extends XMLLine {

    private String parameterType;
    private String value;

    /**
     * @param par
     * @param valor
     */
    public XMLParameterDeclarationLine(TParameter par, TestValue valor) {
        this.parameterType=par.getTipo();
        this.value=valor.getValue().toString();
    }

    /**
     * 
     */
    public XMLParameterDeclarationLine() {
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "\t\txml+=\"<parameter type='" + this.parameterType + "' value='" + this.value + "'/>\";\n";
    }

}

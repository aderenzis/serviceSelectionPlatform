package testooj3.domain.testmethodlines.grouped;

import java.util.Vector;
import testooj3.domain.TestMethodLine;
import testooj3.domain.TestValue;

/**
 * @author  andres
 */
public class ArrayDeclarationLine extends TestMethodLine {

    private String type;
    private int orderOfTheOperation;
    private Vector testValues;
    private int orderOfTheParameter;
    
    public ArrayDeclarationLine() {
        testValues=new Vector();
    }

    public void setParameterType(String type) {
        this.type=type;
    }

    public void setOrderOfTheOperation(int orderOfTheOperation) {
        this.orderOfTheOperation=orderOfTheOperation;
    }
    
    public void setOrderOfTheParameter(int orderOfTheParameter) {
        this.orderOfTheParameter=orderOfTheParameter;
    }

    public void addTestValue(TestValue tv) {
        this.testValues.add(tv);
    }

    public String toString() {
        String texto="\t\t" + type + "[] arg" + orderOfTheOperation + "_" + orderOfTheParameter + "={";
        for (int i=0; i<testValues.size(); i++) {
            TestValue tv=(TestValue) testValues.get(i);
            texto+=tv.getValue().toString() + ", ";
        }
        if (texto.endsWith(", "))
            texto=texto.substring(0, texto.length()-2);
        texto+="};\n";
        return texto;
    }

}

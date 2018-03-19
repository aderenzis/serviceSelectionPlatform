package testooj3.domain.testmethodlines.grouped;

import testooj3.domain.TestMethodLine;

/**
 * @author  andres
 */
public class ForLine extends TestMethodLine {
    private int orderOfTheParameter;
    private int orderOfTheOperation;
    private int orderOfTheLoop;

    public void setOrderOfTheOperation(int orderOfTheOperation) {
        this.orderOfTheOperation=orderOfTheOperation;
    }

    public void setOrderOfTheParameter(int orderOfTheParameter) {
        this.orderOfTheParameter=orderOfTheParameter;
    }

    public String toString() {
        String coordinates=orderOfTheOperation + "_" + orderOfTheParameter;
        String counter="counter" + coordinates;
        String tabs="\t";
        for (int i=0; i<orderOfTheLoop; i++)
            tabs+="\t";
        String texto=tabs + "for (int " + counter + "=0; counter<arg" + coordinates + ".length; " + counter + "++) {\n";
        return texto;
    }

    public void setOrderOfTheLoop(int orderOfTheLoop) {
        this.orderOfTheLoop=orderOfTheLoop+1;
    }
}

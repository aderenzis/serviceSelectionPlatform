package testooj3.domain.testmethodlines;

import java.io.IOException;
import java.io.Serializable;

import testooj3.domain.TestMethodLine;

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssertionLine extends TestMethodLine implements Serializable {

    private String mAssertion;

    /**
     * @param asercion
     */
    public AssertionLine(String assertion) {
        this.mAssertion=assertion;
    }

    /* (non-Javadoc)
     * @see testooj3.domain.TestMethodLine#getText()
     */
    public String toString() {
        String result=mAssertion+"\n";
        if (this.mIndented)
            result="\t" + result;
        if (result.endsWith("\n\n"))
            result=result.substring(0, result.length()-1);
        return result;
    }



}

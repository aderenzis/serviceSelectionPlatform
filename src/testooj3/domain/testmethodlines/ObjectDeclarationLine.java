package testooj3.domain.testmethodlines;

import java.io.Serializable;
import testooj3.domain.TConstructor;
import testooj3.domain.TestMethodLine;

/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class ObjectDeclarationLine extends TestMethodLine implements Serializable {
    private String mConstructorName;
    private String className;
    private boolean constructorIsFactory;
    private String tabs;

    /**
     * @param constructor
     */
    public ObjectDeclarationLine(TConstructor constructor) {
        this.mConstructorName=constructor.getNombre(); 
        this.className=constructor.getClassName();
        this.constructorIsFactory=constructor.isFactory();
    }

    /* (non-Javadoc)
     * @see testooj3.domain.TestMethodLine#getText()
     */
    public String toString() {
        String result;
        if (tabs==null)
            result="\t\t";
        else result=tabs;
        if (!this.constructorIsFactory)
        	result+=this.mConstructorName;
        else
        	result+=className;
        result+=" obtained=null;\n";
        
        if (mIndented) result="\t" + result;
        return result;
    }

    /**
     * @param tabs
     */
    public void setTabs(String tabs) {
        this.tabs=tabs;
    }

}

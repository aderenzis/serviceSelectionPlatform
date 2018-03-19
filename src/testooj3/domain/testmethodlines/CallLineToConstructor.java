package testooj3.domain.testmethodlines;

import java.io.IOException;
import java.io.Serializable;

import testooj3.domain.TConstructor;
import testooj3.domain.TestMethodLine;

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CallLineToConstructor extends TestMethodLine implements Serializable {
    protected String mConstructorName;
    protected String className;
    protected int mNumberOfParameters;
    protected boolean constructorIsFactory;

    /**
     * @param constructor
     */
    public CallLineToConstructor(TConstructor constructor) {
        this.mConstructorName=constructor.getNombre();
        this.constructorIsFactory=constructor.isFactory();
        this.className=constructor.getClassName();
        mNumberOfParameters=constructor.getParametros().size();
    }

    /* (non-Javadoc)
     * @see testooj3.domain.TestMethodLine#getText()
     */
    public String toString() { 
        String result;
        if (!constructorIsFactory) 
        	result="\t\tobtained =new " + mConstructorName +"(";
        else
        	result="\t\tobtained=" + className + "." + mConstructorName + "(";
 		for (int i=0; i<mNumberOfParameters; i++) 
		{
			result+="arg" + (i+1) + ", ";
		}
		if (mNumberOfParameters>0)
			result=result.substring(0, result.length()-2);
		result+=");\n";
		if (mIndented) result="\t" + result;
		return result;
    }

}

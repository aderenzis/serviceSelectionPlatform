package testooj3.domain.testmethodlines.grouped;

import java.util.Vector;
import testooj3.domain.Operation;
import testooj3.domain.testmethodlines.CallLineToMethod;

/**
 * @author  andres
 */
public class GroupedCallToMethod extends CallLineToMethod {

    private String tabs;
    private Vector exceptions;

    public GroupedCallToMethod(Operation m, int pos, int posMetodo) {
        super(m, pos, posMetodo);
        exceptions=m.getLaunchedExceptions();
    }
    
    public void setTabs(String tabs) {
        this.tabs=tabs;
    }

    public String toString() {
	    String result="obtained" + "." + mMethodName +"(";
	    if (!mReturnType.equals("void"))
	        result=mReturnType + " result=" + result;
 		for (int i=0; i<mNumberOfParameters; i++) 
		{
 		    String coordinates=mPos + "_" + i;
			result+="arg" + coordinates + "[counter" + coordinates + "], ";
		}
 		if (result.endsWith(", "))
 		    result=result.substring(0, result.length()-2);
		result+=");";
		
		result="try { " + result + " }";		
		result = getCatchs(result);
		result+="\n";
		if (tabs!=null)
		    result=tabs+result;
		return result;   
    }
    
    private String getCatchs(String result) {
        String save="save(\"" + mMethodName + "\\t\" + ex.toString() + \"\\t\\n\");";
        if (exceptions.size()==0)
		    result+=" catch (Exception ex) {" + save + "}";
		else {
		    for (int i=0; i<exceptions.size(); i++) {
		        String ex=exceptions.get(i).toString();
		        result+=" catch (" + ex + " ex) {" + save + "}";
		    }
		}
        return result;
    }
}

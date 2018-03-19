package testooj3.domain.testmethodlines.grouped;

import java.util.Vector;
import testooj3.domain.TConstructor;
import testooj3.domain.testmethodlines.CallLineToConstructor;

/**
 * @author  andres
 */
public class GroupedCallLineToConstructor extends CallLineToConstructor {

    private String tabs;
    private Vector exceptions;

    public GroupedCallLineToConstructor(TConstructor constructor) {
        super(constructor);
        exceptions=constructor.getLaunchedExceptions();
    }

    public String toString() {
        String result;
        result="obtained =new " + mConstructorName +"(";
 		for (int i=0; i<mNumberOfParameters; i++) 
		{
 		    String coordinates="0_" + i;
			result+="arg" + coordinates + ", ";
		}
		if (mNumberOfParameters>0)
			result=result.substring(0, result.length()-2);
		result+=");";
		result="try { " + result + " }";		
		result = getCatchs(result);		
        if (tabs==null)
            result="\t\t" + result;
        else result=tabs + result;
	    result+="\n";
		return result;
    }
    
    private String getCatchs(String result) {
        String save="save(\"" + mConstructorName + "\\t\" + ex.toString() + \"\\t\\n\");";
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

    public void setTabs(String tabs) {
        this.tabs=tabs;
    }
}

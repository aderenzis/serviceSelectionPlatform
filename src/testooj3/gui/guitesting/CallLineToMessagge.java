package testooj3.gui.guitesting;

import testooj3.domain.Operation;
import testooj3.domain.TestMethodLine;

public class CallLineToMessagge extends TestMethodLine {
	private int pos;
	private int posMetodo;
	private int numberOfParameters;
	private String targetName;
	private String methodName;
	private String returnType;

	public CallLineToMessagge(IMessagge m, int pos, int posMetodo) {
		this.targetName=m.getTarget().getName();
		this.methodName=m.getMethod().getNombre();
		this.returnType=m.getMethod().getTipo();
		this.pos=pos;
		this.posMetodo=posMetodo;
		this.numberOfParameters=m.getMethod().getParametros().size();
	}

	@Override
	public String toString() {
		String result=this.targetName + "." + methodName +"(";
	    if (!returnType.equals("void"))
	        result=this.returnType + " result" + this.posMetodo + "=" + result;
 		for (int i=0; i<this.numberOfParameters; i++) 
		{
			result+="arg" + (pos+i) + ", ";
		}
 		result="\t\t" + result;
		if (numberOfParameters>0)
			result=result.substring(0, result.length()-2);
		result+=");\n";
		if (mIndented) result="\t" + result;
		return result;   
	}

}

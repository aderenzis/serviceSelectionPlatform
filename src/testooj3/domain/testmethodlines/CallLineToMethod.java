package testooj3.domain.testmethodlines;

import java.io.IOException;
import java.io.Serializable;

import testooj3.domain.Operation;
import testooj3.domain.TestMethodLine;

public class CallLineToMethod extends TestMethodLine implements Serializable {
    protected String mMethodName;
    protected String mReturnType;
    protected int mNumberOfParameters;
    protected int mPos;
    protected int mPosMetodo;

    /**
     * @param m
     * @param pos
     */
    public CallLineToMethod(Operation m, int pos, int posMetodo) {
        this.mMethodName=m.getNombre();
        this.mNumberOfParameters=m.getParametros().size();
        this.mReturnType=m.getTrueClass().getName();
        this.mPos=pos;
        this.mPosMetodo=posMetodo;
    }

    public String toString() {
	    String result="obtained" + "." + mMethodName +"(";
	    if (!mReturnType.equals("void"))
	        result=mReturnType + " result" + this.mPosMetodo + "=" + result;
 		for (int i=0; i<mNumberOfParameters; i++) 
		{
			result+="arg" + (mPos+i) + ", ";
		}
 		result="\t\t" + result;
		if (mNumberOfParameters>0)
			result=result.substring(0, result.length()-2);
		result+=");\n";
		if (mIndented) result="\t" + result;
		return result;   
    }

    public String getMMethodName() {
		return mMethodName;
	}

	public void setMMethodName(String methodName) {
		mMethodName = methodName;
	}

	public String getMReturnType() {
		return mReturnType;
	}

	public void setMReturnType(String returnType) {
		mReturnType = returnType;
	}

	public int getMNumberOfParameters() {
		return mNumberOfParameters;
	}

	public void setMNumberOfParameters(int numberOfParameters) {
		mNumberOfParameters = numberOfParameters;
	}

	public int getMPos() {
		return mPos;
	}

	public void setMPos(int pos) {
		mPos = pos;
	}

	public int getMPosMetodo() {
		return mPosMetodo;
	}

	public void setMPosMetodo(int posMetodo) {
		mPosMetodo = posMetodo;
	}
    
}

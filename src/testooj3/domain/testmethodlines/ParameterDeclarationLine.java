package testooj3.domain.testmethodlines;

import java.io.IOException;
import java.io.Serializable;
import testooj3.domain.TParameter;
import testooj3.domain.TestMethodLine;
import testooj3.domain.TestValue;
import testooj3.persistence.Agente;

/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class ParameterDeclarationLine extends TestMethodLine implements Serializable {
    protected String mParameterType;
    protected int mOrder;
    private TestValue mValue;
	private boolean mUseValuesAsDescribed;
    
    public ParameterDeclarationLine(TParameter parameter, int order, TestValue value) {
        super();
        mParameterType = parameter.getTipo();
        mOrder=order;
        this.mValue=value;
        this.mUseValuesAsDescribed=parameter.useValuesAsDescribed();
    }
    
    public String getVarName() {
        return "arg" + mOrder;
    }

    /* (non-Javadoc)
     * @see testooj3.domain.TestMethodLine#toString()
     */
    public String toString() {
        String result="";
        boolean primitiveSimple=false, arrayOfPrimitives=false;
        primitiveSimple = Agente.getAgente().esPrimitivo(mParameterType);
        arrayOfPrimitives = Agente.getAgente().isArrayOfPrimitives(mParameterType);
        if (primitiveSimple) {
            String comillas="";
            if (mParameterType.equals("char")) 
                comillas="'";
            else if (mParameterType.equals("java.lang.String")) 
                comillas="\"";
            result= "=(" + mParameterType + ") " + comillas + mValue.getValue().toString() + comillas + ";";
            result="\t\t" + mParameterType + " arg" + mOrder + result + "\n";
            if (mIndented) result="\t" + result;
        } else if (arrayOfPrimitives) {
        	 result= "=(" + Agente.getAgente().getPrimitiveTypeOfArray(mParameterType) + "[]) " + mValue.getValue().toString() + ";";
        	 result="\t\t" + Agente.getAgente().getPrimitiveTypeOfArray(mParameterType) + "[] " + " arg" + mOrder + result + "\n";
             if (mIndented) result="\t" + result;
        } else if (mUseValuesAsDescribed) {
        	result= "=" + mValue.getValue().toString() + ";";
        	result="\t\t\t" + mParameterType + " arg" + mOrder + result + "\n";
        } else {
            String linea1="\t\tFileInputStream fIn" + (mOrder) + "=new FileInputStream(\"" + mValue.getValue() + "\");\n";
            String linea2="\t\tObjectInputStream oIn" + (mOrder) + "=new ObjectInputStream(fIn" + (mOrder) + ");\n";
            String linea3="\t\t" + mParameterType + " arg" + (mOrder) + "=(" + mParameterType + ") oIn" + (mOrder) + ".readObject();\n";
            String linea4="\t\tfIn" + (mOrder) + ".close(); oIn" + (mOrder) + ".close();\n";
            if (mIndented) {
                linea1="\t" + linea1;
                linea2="\t" + linea2;
                linea3="\t" + linea3;
                linea4="\t" + linea4;
            }
            result=linea1+linea2+linea3+linea4;
        }  
        return result;
    }
}

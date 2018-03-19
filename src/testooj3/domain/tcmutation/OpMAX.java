package testooj3.domain.tcmutation;

import java.io.IOException;
import java.util.Vector;

import testooj3.domain.PrimitiveValue;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;
import testooj3.persistence.Agente;

public class OpMAX extends OpSET {
    protected TestValue getValueToBeSet(TParameter p, TestValue tv) {
        PrimitiveValue newValue=new PrimitiveValue(getMAX(p));
        return newValue;
    }
    
    private Object getMAX(TParameter p) {
        if (p.getTipo().equalsIgnoreCase("int")) return "Integer.MAX_VALUE";
        if (p.getTipo().equalsIgnoreCase("float")) return "Float.MAX_VALUE";
        if (p.getTipo().equalsIgnoreCase("double")) return "Double.MAX_VALUE";
        if (p.getTipo().equalsIgnoreCase("long")) return "Long.MAX_VALUE";
        if (p.getTipo().equalsIgnoreCase("short")) return "Short.MAX_VALUE";
        if (p.getTipo().equalsIgnoreCase("byte")) return "Byte.MAX_VALUE";
        return null; 
    }
    
    protected String operatorName() {
        return "_MAX";
    }
    
    protected boolean isOperatorApplicable(TParameter p) {
        return (Agente.getAgente().esPrimitivo(p.getTipo()) && p.isNumeric());
    }
}

package testooj3.domain.tcmutation;

import java.io.IOException;
import java.util.Vector;

import testooj3.domain.PrimitiveValue;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;
import testooj3.persistence.Agente;

public class OpMIN extends OpSET {
    protected TestValue getValueToBeSet(TParameter p, TestValue tv) {
        PrimitiveValue newValue=new PrimitiveValue(getMIN(p));
        return newValue;
    }
    
    private Object getMIN(TParameter p) {
        if (p.getTipo().equalsIgnoreCase("int")) return "Integer.MIN_VALUE";
        if (p.getTipo().equalsIgnoreCase("float")) return "Float.MIN_VALUE";
        if (p.getTipo().equalsIgnoreCase("double")) return "Double.MIN_VALUE";
        if (p.getTipo().equalsIgnoreCase("long")) return "Long.MIN_VALUE";
        if (p.getTipo().equalsIgnoreCase("short")) return "Short.MIN_VALUE";
        if (p.getTipo().equalsIgnoreCase("byte")) return "Byte.MIN_VALUE";
        return null; 
    }
    
    protected String operatorName() {
        return "_MIN";
    }
    
    protected boolean isOperatorApplicable(TParameter p) {
        return (Agente.getAgente().esPrimitivo(p.getTipo()) && p.isNumeric());
    }
}

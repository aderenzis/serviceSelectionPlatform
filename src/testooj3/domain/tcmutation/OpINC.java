package testooj3.domain.tcmutation;

import java.io.IOException;
import java.util.Vector;

import testooj3.domain.PrimitiveValue;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;
import testooj3.persistence.Agente;

public class OpINC extends OpSET {
    protected TestValue getValueToBeSet(TParameter p, TestValue tv) {
        PrimitiveValue auxi=(PrimitiveValue) tv;
        PrimitiveValue newValue=new PrimitiveValue("(" + auxi.getValue() + "+1)");
        return newValue;
    }
    
    protected String operatorName() {
        return "_INC";
    }
    
    protected boolean isOperatorApplicable(TParameter p) {
        return (Agente.getAgente().esPrimitivo(p.getTipo()) && p.isNumeric());
    }  
}

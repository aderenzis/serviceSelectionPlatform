package testooj3.domain.tcmutation;

import java.io.IOException;
import java.util.Vector;

import testooj3.domain.PrimitiveValue;
import testooj3.domain.SerializedValue;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;
import testooj3.persistence.Agente;

public class OpNUL extends OpSET {
    protected TestValue getValueToBeSet(TParameter p, TestValue tv) {
        SerializedValue newValue=new SerializedValue("[null]");
        return newValue;
    }
    
    protected String operatorName() {
        return "_NUL";
    }
    
    protected boolean isOperatorApplicable(TParameter p) {
        return (Agente.getAgente().esPrimitivo(p.getTipo()) && p.isNumeric());
    }
}

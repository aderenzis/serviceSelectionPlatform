package testooj3.domain.tcmutation;

import java.util.Vector;

/**
 * @author  Administrador
 */
public class TCMutationOperators {
    protected static TCMutationOperators mInstance;
    public boolean CL;
    public boolean IN;
    public boolean PI;
    public boolean RC;
    public boolean NUL;
    public boolean MAX;
    public boolean MIN;
    public boolean INC;
    public boolean DEC;
    
    protected TCMutationOperators() {
        CL=false;
        IN=false;
        PI=false;
        RC=false;
        NUL=false;
        MAX=false;
        MIN=false;
        INC=false;
        DEC=false;
    }
    
    public static TCMutationOperators getInstance() {
        if (mInstance==null)
            mInstance=new TCMutationOperators();
        return mInstance;
    }
    
    public Vector buildOperators() {
        Vector result=new Vector();
        if (CL) result.add(new OpCL());
        if (IN) result.add(new OpIN());
        if (PI) result.add(new OpPI());
        if (RC) result.add(new OpRC());
        if (NUL) result.add(new OpNUL());
        if (MAX) result.add(new OpMAX());
        if (MIN) result.add(new OpMIN());
        if (INC) result.add(new OpINC());
        if (DEC) result.add(new OpDEC());
        return result;
    }
}

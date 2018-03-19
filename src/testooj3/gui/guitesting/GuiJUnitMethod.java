package testooj3.gui.guitesting;

import java.io.IOException;
import java.util.Vector;

import testooj3.domain.TJUnitMethod;
import testooj3.domain.Operation;
import testooj3.domain.TestCase;
import testooj3.domain.testmethodlines.APeloLine;
import testooj3.domain.testmethodlines.CallLineToConstructor;
import testooj3.domain.testmethodlines.CallLineToMethod;
import testooj3.domain.testmethodlines.ObjectDeclarationLine;

public class GuiJUnitMethod extends TJUnitMethod {
    public GuiJUnitMethod(GuiTestCase tc) throws IOException
    {
        mNombre=tc.getNombre();
        mLanzaExcepciones=false;
        String firstExceptionLaunched=null;
        mTipo="void";
        mLineas=new Vector();
        int[] cont={1};
        int pos=cont[0];
        Vector lineas=new Vector();
        APeloLine llave1=new APeloLine("{");
        APeloLine llave2=new APeloLine("}");

        for (int i=0; i<tc.getMessagges().size(); i++) 
        {
            pos=cont[0];
            IMessagge m=(IMessagge) tc.getMessagges().get(i);
            if (m.getMethod().getLanzaExcepciones())
                mLanzaExcepciones=true;    
            Vector valores=(Vector) tc.getParsMetodos()[i];
            lineas=m.getMethod().getDeclaraciones(valores, cont, true, null);
            if (firstExceptionLaunched==null)
                firstExceptionLaunched=m.getMethod().getExceptionLaunched(valores);
            for (int j=0; j<m.getMethod().getLaunchedExceptions().size(); j++) {
                String lEx=m.getMethod().getLaunchedExceptions().get(j).toString();
                if (!mPossibleExceptions.containsKey(lEx))
                    mPossibleExceptions.put(lEx, lEx);
            }
            cont[0]+=(valores!=null ? valores.size() : 0);
            for (int j=0; j<lineas.size(); j++)
                mLineas.add(lineas.get(j));
            mLineas.add(llave1);
            addAssertions(true, m.getMethod(), mLineas, pos);
            CallLineToMessagge cltm=new CallLineToMessagge(m, pos, i);
            mLineas.add(cltm);
            addAssertions(false, m.getMethod(), mLineas, pos);
            mLineas.add(llave2);
        }
        if (firstExceptionLaunched!=null)
            surroundWith(firstExceptionLaunched);
    }
}

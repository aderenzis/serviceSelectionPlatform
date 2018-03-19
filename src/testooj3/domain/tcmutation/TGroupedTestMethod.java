package testooj3.domain.tcmutation;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import testooj3.domain.TJUnitMethod;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestMethod;
import testooj3.domain.TestMethodLine;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;
import testooj3.domain.testmethodlines.APeloLine;
import testooj3.domain.testmethodlines.CallLineToConstructor;
import testooj3.domain.testmethodlines.CallLineToMethod;
import testooj3.domain.testmethodlines.ObjectDeclarationLine;
import testooj3.domain.testmethodlines.grouped.ArrayDeclarationLine;
import testooj3.domain.testmethodlines.grouped.ForLine;
import testooj3.domain.testmethodlines.grouped.GroupedCallLineToConstructor;
import testooj3.domain.testmethodlines.grouped.GroupedCallToMethod;

public class TGroupedTestMethod extends TJUnitMethod {

    public TGroupedTestMethod(TestTemplate ts) throws IOException
    {
        mNombre=ts.getName();
        mLanzaExcepciones=false;
        mTipo="void";
        mLineas=new Vector();
        addArrayDeclarations(ts);
        int numberOfLoops=addFors(ts);
        String tabs="\t";
        for (int j=0; j<numberOfLoops+1; j++)
            tabs+="\t";
        ObjectDeclarationLine odl=new ObjectDeclarationLine(ts.getConstructor());
        odl.setTabs(tabs);
        mLineas.add(odl);
        GroupedCallLineToConstructor cl=new GroupedCallLineToConstructor(ts.getConstructor());
        cl.setTabs(tabs);
        mLineas.add(cl);
        
        for (int i=0; i<ts.getMethods().size(); i++) {
            Operation m=(Operation) ts.getMethods().get(i);
            GroupedCallToMethod ctm=new GroupedCallToMethod(m, i+1, i);
            ctm.setTabs(tabs);
            mLineas.add(ctm);
        }
        
        for (int i=numberOfLoops; i>0; i--) {
            String texto="}";
            tabs="";
            for (int j=0; j<i-1; j++)
                tabs+="\t";
            texto=tabs+texto;
            APeloLine apl=new APeloLine(texto);
            mLineas.add(apl);
        }
    }
    
    private int addFors(TestTemplate ts) {
        int numberOfLoops=0;
        Vector parameters=ts.getConstructor().getParametros();
        for (int i=0; i<parameters.size(); i++) {
            ForLine fl=new ForLine();
            fl.setOrderOfTheLoop(numberOfLoops);
            fl.setOrderOfTheOperation(0);
            fl.setOrderOfTheParameter(i);
            mLineas.add(fl);
            numberOfLoops++;
        }
        for (int i=0; i<ts.getMethods().size(); i++) {
            Operation m=(Operation) ts.getMethods().get(i);
            parameters=m.getParametros();
            for (int j=0; j<parameters.size(); j++) {
                ForLine fl=new ForLine();
                fl.setOrderOfTheLoop(numberOfLoops);
                fl.setOrderOfTheOperation(i+1);
                fl.setOrderOfTheParameter(j);
                mLineas.add(fl);
                numberOfLoops++;
            }    
        }
        return numberOfLoops;
    }

    private void addArrayDeclarations(TestTemplate ts) {
        Vector parameters=ts.getConstructor().getParametros();
        for (int i=0; i<parameters.size(); i++) {
            TParameter p=(TParameter) parameters.get(i);
            ArrayDeclarationLine adl=new ArrayDeclarationLine();
            adl.setParameterType(p.getTipo());
            adl.setOrderOfTheOperation(0);
            adl.setOrderOfTheParameter(i);
            for (int j=0; j<p.getTestValues().length; j++) {
                TestValue tv=p.getTestValue(j);
                adl.addTestValue(tv);
            }
            mLineas.add(adl);
        }
        for (int i=0; i<ts.getMethods().size(); i++) {
            Operation m=(Operation) ts.getMethods().get(i);
            parameters=m.getParametros();
            for (int j=0; j<parameters.size(); j++) {
                TParameter p=(TParameter) parameters.get(j);
                ArrayDeclarationLine adl=new ArrayDeclarationLine();
                adl.setParameterType(p.getTipo());
                adl.setOrderOfTheOperation(i+1);
                adl.setOrderOfTheParameter(j);
                for (int k=0; k<p.getTestValues().length; k++) {
                    TestValue tv=p.getTestValue(k);
                    adl.addTestValue(tv);
                }
                mLineas.add(adl);
            }    
        }
    }

    /**
     * 
     */
    protected TGroupedTestMethod() {
        super();
        mLanzaExcepciones=false;
        mTipo="void";
        mLineas=new Vector();        
    }

    public String toString(boolean forLotOfTC) {
        StringBuffer result=new StringBuffer("\tpublic " + mTipo + " " + mNombre + "() " + 
                (this.mLanzaExcepciones ? " throws Exception " : "") + "{\n");
        for (int i=0; i<mLineas.size(); i++) 
        {
            TestMethodLine linea=(TestMethodLine) mLineas.get(i);
            result.append(linea.toString());
        }
        result.append("\t}\n");
        return result.toString();    
    }

    public TestMethod copy(String name) {
        TGroupedTestMethod result=new TGroupedTestMethod();
        result.mLanzaExcepciones=this.mLanzaExcepciones;
        result.mNombre=this.mNombre + "_" + name;
        for (int i=0; i<getLineas().size(); i++)
            result.addLinea(getLineas().get(i).toString());
        return result;
    }

    protected String getExtension() {
        return "grouped";
    }

}

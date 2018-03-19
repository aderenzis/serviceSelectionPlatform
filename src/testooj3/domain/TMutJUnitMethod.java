package testooj3.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import testooj3.auxiliares.Auxi;
import testooj3.domain.testmethodlines.APeloLine;
import testooj3.domain.testmethodlines.CallLineToConstructor;
import testooj3.domain.testmethodlines.CallLineToMethod;
import testooj3.domain.testmethodlines.ObjectDeclarationLine;
import testooj3.mutation.Operators;
import testooj3.mutation.junit.CL;
import testooj3.mutation.junit.IN;
import testooj3.mutation.junit.SetTo;
import testooj3.mutation.junit.RC;

public class TMutJUnitMethod extends TJUnitMethod implements Serializable
{
    protected Hashtable mPossibleExceptions=new Hashtable();
    protected String mExceptionLaunched=null;
    
    public TMutJUnitMethod(TestCase tc) throws IOException
    {
        mNombre=tc.getNombre();
        mLanzaExcepciones=false;
        String firstExceptionLaunched=null;
        mTipo="String";
        mLineas=new Vector();
        int[] cont={1};
        int pos=cont[0];
        APeloLine resultLine=new APeloLine("String obtainedState=\"\";");
        mLineas.add(resultLine);
        ObjectDeclarationLine odl=new ObjectDeclarationLine(tc.getConstructor());
        mLineas.add(odl);
        Vector lineas=tc.getConstructor().getDeclaraciones(tc.mParsConstructor, cont, true, null);
        firstExceptionLaunched=tc.getConstructor().getExceptionLaunched(tc.mParsConstructor);
        for (int i=0; i<tc.getConstructor().getLaunchedExceptions().size(); i++) {
            String lEx=tc.getConstructor().getLaunchedExceptions().get(i).toString();
            if (!mPossibleExceptions.containsKey(lEx))
                mPossibleExceptions.put(lEx, lEx);
        }
        pos=cont[0];
        for (int j=0; j<lineas.size(); j++)
            mLineas.add(lineas.get(j));
        APeloLine llave1=new APeloLine("{");
        mLineas.add(llave1);
        if (tc.getConstructor().getLanzaExcepciones())
            this.mLanzaExcepciones=true;
        CallLineToConstructor cl=new CallLineToConstructor(tc.getConstructor());
        mLineas.add(cl);
        APeloLine llave2=new APeloLine("}");
        mLineas.add(llave2);
        cont[0]=tc.getConstructor().getParametros().size()+1;
        for (int i=0; i<tc.getMetodos().size(); i++) 
        {
            pos=cont[0];
            Operation m=(Operation) tc.getMetodos().elementAt(i);
            if (m.getLanzaExcepciones())
                mLanzaExcepciones=true;    
            Vector valores=(Vector) tc.mParsMetodos[i];
            lineas=m.getDeclaraciones(valores, cont, true, null);
            if (firstExceptionLaunched==null)
                firstExceptionLaunched=m.getExceptionLaunched(valores);
            for (int j=0; j<m.getLaunchedExceptions().size(); j++) {
                String lEx=m.getLaunchedExceptions().get(j).toString();
                if (!mPossibleExceptions.containsKey(lEx))
                    mPossibleExceptions.put(lEx, lEx);
            }
            cont[0]+=(valores!=null ? valores.size() : 0);
            for (int j=0; j<lineas.size(); j++)
                mLineas.add(lineas.get(j));
            mLineas.add(llave1);
            CallLineToMethod clm=new CallLineToMethod(m, pos, i);
            mLineas.add(clm);
            mLineas.add(llave2);
        }
        if (firstExceptionLaunched!=null)
            surroundWith(firstExceptionLaunched);
        APeloLine returnLine=new APeloLine("return obtainedState;");
        mLineas.add(returnLine);
    }
    
    protected void surroundWith(String exception) {
        this.mExceptionLaunched=exception;
        APeloLine a1=new APeloLine("try {");
        mLineas.insertElementAt(a1, 1);
        String texto="obtainedState+=\"" + exception + " expected\";";
        APeloLine a2=new APeloLine(texto);
        mLineas.add(a2);
        APeloLine a3=new APeloLine("}\n");
        mLineas.add(a3);
        APeloLine a4=new APeloLine("catch (" + exception + " ex) {}\n");
        mLineas.add(a4);
        if (!exception.equals("Exception") && 
                !exception.equals("java.lang.Exception")) {
            APeloLine a5=new APeloLine("catch (Exception ex) {\n");
            mLineas.add(a5);
	        APeloLine a6=new APeloLine(texto);
			mLineas.add(a6);
			APeloLine a7=new APeloLine("}\n");
			mLineas.add(a7);
        }
    }
    
    public String toString(boolean forLotOfTC) 
    {
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
    
    protected void removeTrys() {
        int i=mLineas.size()-2;
        boolean quitada=false;
        while (mLineas.get(i) instanceof APeloLine) {
            mLineas.remove(i);
            i--;
            quitada=true;
        }
        if (quitada)
            mLineas.remove(0);
    }
    
    public void setMustFailWith(String exception) {
        removeTrys();
        if (exception==null) return;
        this.surroundWith(exception);
    }

    public void mutar(Vector originales, Vector operadores) 
    {
        if (operadores.contains(new Integer(Operators.CL))) 
        {
            CL mutador=new CL(this);
            mutador.buildMutants();
            for (int i=0; i<mutador.getMutantes().size(); i++)
                originales.add(mutador.getMutantes().get(i));
        }
        if (operadores.contains(new Integer(Operators.IN))) 
        {
            IN mutador=new IN(this);
            mutador.buildMutants();
            for (int i=0; i<mutador.getMutantes().size(); i++)
                originales.add(mutador.getMutantes().get(i));
        }
        if (operadores.contains(new Integer(Operators.ZE))) 
        {
            SetTo mutador=new SetTo(this, Operators.ZE);
            mutador.buildMutants();
            for (int i=0; i<mutador.getMutantes().size(); i++)
                originales.add(mutador.getMutantes().get(i));
        }
        if (operadores.contains(new Integer(Operators.MAX))) 
        {
            SetTo mutador=new SetTo(this, Operators.MAX);
            mutador.buildMutants();
            for (int i=0; i<mutador.getMutantes().size(); i++)
                originales.add(mutador.getMutantes().get(i));
        }
        if (operadores.contains(new Integer(Operators.MIN))) 
        {
            SetTo mutador=new SetTo(this, Operators.MIN);
            mutador.buildMutants();
            for (int i=0; i<mutador.getMutantes().size(); i++)
                originales.add(mutador.getMutantes().get(i));
        }
        if (operadores.contains(new Integer(Operators.NEG))) 
        {
            SetTo mutador=new SetTo(this, Operators.NEG);
            mutador.buildMutants();
            for (int i=0; i<mutador.getMutantes().size(); i++)
                originales.add(mutador.getMutantes().get(i));
        }
        if (operadores.contains(new Integer(Operators.ADD))) 
        {
            SetTo mutador=new SetTo(this, Operators.ADD);
            mutador.buildMutants();
            for (int i=0; i<mutador.getMutantes().size(); i++)
                originales.add(mutador.getMutantes().get(i));
        }
        if (operadores.contains(new Integer(Operators.SUB))) 
        {
            SetTo mutador=new SetTo(this, Operators.SUB);
            mutador.buildMutants();
            for (int i=0; i<mutador.getMutantes().size(); i++)
                originales.add(mutador.getMutantes().get(i));
        }
        if (operadores.contains(new Integer(Operators.SUB))) 
        {
            RC mutador=new RC(this);
            mutador.buildMutants();
            for (int i=0; i<mutador.getMutantes().size(); i++)
                originales.add(mutador.getMutantes().get(i));
        }
    }
    
    public Vector getMutantes() 
    {
        return mMutantes;
    }
    
    protected TMutJUnitMethod() {
        super();
        mLanzaExcepciones=false;
        mTipo="void";
        mLineas=new Vector();
    }
    
    public TestMethod copy(String name) 
    {
        TMutJUnitMethod result=new TMutJUnitMethod();
        result.mExceptionLaunched=this.mExceptionLaunched;
        result.mLanzaExcepciones=this.mLanzaExcepciones;
        result.mNombre=this.mNombre + "_" + name;
        for (int i=0; i<getLineas().size(); i++)
            result.addLinea(getLineas().get(i).toString());
        Enumeration e=this.mPossibleExceptions.elements();
        while (e.hasMoreElements()) {
            String exception=e.nextElement().toString();
            result.mPossibleExceptions.put(e, e);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see testooj3.domain.TestMethod#getExtension()
     */
    protected String getExtension() {
        return "mutjunit";
    }
    public Hashtable getPossibleExceptions() {
        return mPossibleExceptions;
    }
    public String getExceptionLaunched() {
        return mExceptionLaunched;
    }
}
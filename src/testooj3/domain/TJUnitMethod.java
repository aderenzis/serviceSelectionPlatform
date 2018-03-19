package testooj3.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import testooj3.auxiliares.Auxi;
import testooj3.domain.testmethodlines.APeloLine;
import testooj3.domain.testmethodlines.AssertionLine;
import testooj3.domain.testmethodlines.CallLineToConstructor;
import testooj3.domain.testmethodlines.CallLineToMethod;
import testooj3.domain.testmethodlines.ObjectDeclarationLine;
import testooj3.mutation.Operators;
import testooj3.mutation.junit.CL;
import testooj3.mutation.junit.IN;
import testooj3.mutation.junit.SetTo;
import testooj3.mutation.junit.RC;

public class TJUnitMethod extends TestMethod implements Serializable
{
    protected Hashtable mPossibleExceptions=new Hashtable();
    protected String mExceptionLaunched=null;
    
    public TJUnitMethod(TestCase tc) throws IOException
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
        if (tc.getConstructor()!=null) {
	        ObjectDeclarationLine odl=new ObjectDeclarationLine(tc.getConstructor());
	        mLineas.add(odl);
	        lineas=tc.getConstructor().getDeclaraciones(tc.mParsConstructor, cont, true, null);
	        firstExceptionLaunched=tc.getConstructor().getExceptionLaunched(tc.mParsConstructor);
	        for (int i=0; i<tc.getConstructor().getLaunchedExceptions().size(); i++) {
	            String lEx=tc.getConstructor().getLaunchedExceptions().get(i).toString();
	            if (!mPossibleExceptions.containsKey(lEx))
	                mPossibleExceptions.put(lEx, lEx);
	        }
	        pos=cont[0];
	        for (int j=0; j<lineas.size(); j++)
	            mLineas.add(lineas.get(j));
	       
	        mLineas.add(llave1);
	        addAssertions(true, tc.getConstructor(), mLineas, pos);
	        if (tc.getConstructor().getLanzaExcepciones())
	            this.mLanzaExcepciones=true;
	        CallLineToConstructor cl=new CallLineToConstructor(tc.getConstructor());
	        mLineas.add(cl);
	        addAssertions(false, tc.getConstructor(), mLineas, pos);
	        
	        mLineas.add(llave2);
	        cont[0]=tc.getConstructor().getParametros().size()+1;
        }
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
            addAssertions(true, m, mLineas, pos); 
            CallLineToMethod clm=new CallLineToMethod(m, pos, i);
            mLineas.add(clm);
            addAssertions(false, m, mLineas, pos);
            mLineas.add(llave2);
        }
        if (firstExceptionLaunched!=null)
            surroundWith(firstExceptionLaunched);
    }
    
    protected void surroundWith(String exception) {
        this.mExceptionLaunched=exception;
        APeloLine a1=new APeloLine("try {");
        mLineas.insertElementAt(a1, 0);
        APeloLine a2=new APeloLine("fail(\"" + getNombre() + ": " + exception + 
        	" expected\");\n");
        mLineas.add(a2);
        APeloLine a3=new APeloLine("}\n");
        mLineas.add(a3);
        APeloLine a4=new APeloLine("catch (" + exception + " ex) {}\n");
        mLineas.add(a4);
        if (!exception.equals("Exception") && 
                !exception.equals("java.lang.Exception")) {
            APeloLine a5=new APeloLine("catch (Exception ex) {\n");
            mLineas.add(a5);
	        APeloLine a6=new APeloLine("fail(\"" + getNombre() + ": " + exception + 
	        	" expected\");\n");
			mLineas.add(a6);
			APeloLine a7=new APeloLine("}\n");
			mLineas.add(a7);
        }
    }

    protected void addAssertions(boolean pre, TOperation op, Vector lineas, int cont) 
    {
        String asercion= pre ? op.getPreassertions() : op.getPostassertions();
        Vector argumentos=new Vector(), nuevos=new Vector();
        int pos=0;
        while (asercion.indexOf("arg", pos)!=-1) {
            String numero=""; boolean esArgumento=false;
            for (int j=asercion.indexOf("arg", pos)+3; Character.isDigit(asercion.charAt(j)); j++) {
                numero+=asercion.charAt(j);
                esArgumento=true;
            }
            if (esArgumento) {
                pos=asercion.indexOf("arg", pos)+numero.length();
                argumentos.add("arg" + numero);
                nuevos.add("arg" + (Integer.parseInt(numero)+cont-1));
            } else pos=asercion.indexOf("arg", pos)+1;
        }
        for (int j=0; j<argumentos.size(); j++)
            if (!argumentos.elementAt(j).toString().equals(nuevos.elementAt(j).toString()))
                asercion=Auxi.substituteAll(asercion, argumentos.elementAt(j).toString(), nuevos.elementAt(j).toString());
        String[] lineasAserciones=asercion.split("\n");
        asercion="";
        if (lineasAserciones==null || lineasAserciones.length==0) return;
        for (int i=0; i<lineasAserciones.length; i++)
            if (lineasAserciones[i].trim().length()>0)
                asercion+="\t\t" + lineasAserciones[i] + "\n";
        AssertionLine al=new AssertionLine(asercion);
        if (asercion.trim().length()>0)
            lineas.add(al);
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
    
    protected TJUnitMethod() {
        super();
        mLanzaExcepciones=false;
        mTipo="void";
        mLineas=new Vector();
    }
    
    public TestMethod copy(String name) 
    {
        TJUnitMethod result=new TJUnitMethod();
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
        return "junit";
    }
    public Hashtable getPossibleExceptions() {
        return mPossibleExceptions;
    }
    public String getExceptionLaunched() {
        return mExceptionLaunched;
    }
}
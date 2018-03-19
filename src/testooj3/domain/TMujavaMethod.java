package testooj3.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

import testooj3.domain.testmethodlines.APeloLine;
import testooj3.domain.testmethodlines.CallLineToConstructor;
import testooj3.domain.testmethodlines.CallLineToMethod;
import testooj3.domain.testmethodlines.ObjectDeclarationLine;
import testooj3.mutation.Operators;
import testooj3.mutation.junit.CL;
import testooj3.mutation.junit.IN;
import testooj3.mutation.junit.RC;
import testooj3.mutation.junit.SetTo;

public class TMujavaMethod extends TestMethod implements Serializable
{
  protected boolean mMustFailExecution;
  
  public TMujavaMethod(TestCase tc) throws IOException
  {
    mNombre=tc.getNombre();
    mMustFailExecution=false;
    mLanzaExcepciones=false;
    mTipo="String";
    mLineas=new Vector();
    int[] cont={1};
    int pos=cont[0];
    ObjectDeclarationLine odl=new ObjectDeclarationLine(tc.getConstructor());
    //String linea="\t\t" + tc.getConstructor().getDeclaracionDeObjeto("obtained");
    mLineas.add(odl);
    Vector lineas=tc.getConstructor().getDeclaraciones(tc.mParsConstructor, cont, true, null);
    pos=cont[0];
    for (int j=0; j<lineas.size(); j++)
        mLineas.add(lineas.get(j));
    if (tc.getConstructor().getLanzaExcepciones())
      this.mLanzaExcepciones=true;
    CallLineToConstructor cl=new CallLineToConstructor(tc.getConstructor());
    mLineas.add(cl);
    cont[0]=tc.getConstructor().getParametros().size()+1;
    for (int i=0; i<tc.getMetodos().size(); i++) 
    {
      pos=cont[0];
      Operation m=(Operation) tc.getMetodos().elementAt(i);
      if (m.getLanzaExcepciones())
        mLanzaExcepciones=true;
      Vector valores=(Vector) tc.mParsMetodos[i];
      lineas=m.getDeclaraciones(valores, cont, true, null);
      cont[0]+=(valores!=null ? valores.size() : 0);
      for (int j=0; j<lineas.size(); j++)
        mLineas.add(lineas.get(j));
      CallLineToMethod clm=new CallLineToMethod(m, pos, i);
      mLineas.add(clm);
    }
    APeloLine apl=new APeloLine("return obtained.toString();");
    mLineas.add(apl);
    if (this.mLanzaExcepciones) 
    {
      for (int i=0; i<mLineas.size(); i++) {
          TestMethodLine linea=(TestMethodLine) mLineas.get(i);
          linea.indent(true);
      }
      APeloLine a1=new APeloLine("try {");
      mLineas.insertElementAt(a1, 0);
      APeloLine a2=new APeloLine("}");
      APeloLine a3=new APeloLine("catch (Exception ex) {");
      APeloLine a4=new APeloLine("return ex.toString();");
      APeloLine a5=new APeloLine("}");
      mLineas.add(a2);
      mLineas.add(a3);
      mLineas.add(a4);
      mLineas.add(a5);
    }
  }
  
  public String toString(boolean forLotOfTC) 
  {
      StringBuffer result=new StringBuffer("\tpublic " + mTipo + " " + mNombre + "() {\n");
      for (int i=0; i<mLineas.size(); i++) 
      {
          TestMethodLine linea=(TestMethodLine) mLineas.get(i);
          result.append(linea.toString());
      }
      result.append("\t}\n");
      return result.toString();
  }

  public void setMustFailExecution(boolean accion) {
    if (this.mMustFailExecution && accion) return;
    if (!this.mMustFailExecution && accion) {
      mMustFailExecution=true;
      addTry("Exception");
      return;
    }
    if (this.mMustFailExecution && !accion) 
    {
      mMustFailExecution=false;
      removeTry();
      mMustFailExecution=false;
    }
    if (!this.mMustFailExecution && !accion)
      mMustFailExecution=false;
  }
  
  protected void addTry(String tipoExcepcion) 
  {
    mLineas.insertElementAt("\t\ttry {\n", 0);
    addLinea("\t\tfail(\"" + getNombre() + " should have failed\");\n");
    addLinea("\t\t}\n");
    addLinea("\t\tcatch (" + tipoExcepcion + " ex) {}");	    
  }
  
  protected void removeTry() 
  {
    mLineas.remove(mLineas.size()-1);
    mLineas.remove(mLineas.size()-1);
    mLineas.remove(mLineas.size()-1);
    mLineas.remove(0);    
  }
  
  public boolean getMustFailExecution() 
  {
    return this.mMustFailExecution;
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
    if (operadores.contains(new Integer(Operators.RC))) 
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
  
  protected TMujavaMethod() {
    super();
    mMustFailExecution=false;
    mLanzaExcepciones=false;
    mTipo="String";
    mLineas=new Vector();
  }
  
  public TestMethod copy(String name) 
  {
    TMujavaMethod result=new TMujavaMethod();
    result.mMustFailExecution=this.mMustFailExecution;
    result.mLanzaExcepciones=this.mLanzaExcepciones;
    result.mNombre=this.mNombre + "_" + name;
    for (int i=0; i<getLineas().size(); i++)
      result.addLinea(getLineas().get(i).toString());
    return result;
  }

	/* (non-Javadoc)
	 * @see testooj3.domain.TestMethod#getExtension()
	 */
	protected String getExtension() {
	    return "mujava";
	}
}
package testooj3.domain;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.Vector;

/**
 * @author  andres
 */
public class TConstructor extends TOperation implements Serializable
{
	private boolean isFactory;

	public TConstructor(Method m, int pos) {
        mNombre=m.getName();
        mPars=new Vector();
        calculaPos(pos);
        Class[] tipos=m.getParameterTypes();
        for (int i=0; i<tipos.length; i++) {
            TParameter p=new TParameter(tipos[i]);
            mPars.addElement(p);
        }
        this.loadLaunchedExceptions(m);
        this.isFactory=true;
	}
	
    public TConstructor(Constructor c, int pos)  
    {
        mNombre=c.getName();
        mPars=new Vector();
        calculaPos(pos);
        Class[] tipos=c.getParameterTypes();
        for (int i=0; i<tipos.length; i++) {
            TParameter p=new TParameter(tipos[i]);
            mPars.addElement(p);
        }
        this.loadLaunchedExceptions(c);
    }  
    
    public TConstructor(String nombre, Vector <String> parTypes, int pos) 
    {
        mVisibilidad="public";
        mNombre=nombre;
        calculaPos(pos);
        Class[] tipos=getTypes(parTypes);
        mPars=new Vector();
        mPre=new Vector();
        mPost=new Vector();
        for (int i=0; i<tipos.length; i++) 
        {
            TParameter tipo=new TParameter(tipos[i]);
            mPars.addElement(tipo);
        }
        this.mLaunchedExceptions=new Vector();
    }
    
    
    public TConstructor(String nombre, Vector <String> parTypes, Vector <String> parValues,int pos) 
    {
        mVisibilidad="public";
        mNombre=nombre;
        calculaPos(pos);
        Class[] tipos=getTypes(parTypes);
        mPars=new Vector();
        mPre=new Vector();
        mPost=new Vector();
        for (int i=0; i<tipos.length; i++) 
        {
            TParameter tipo=new TParameter(tipos[i],parValues.elementAt(i));
            mPars.addElement(tipo);
        }
        this.mLaunchedExceptions=new Vector();
    }
    
    
    public String toString() 
    {
        String r=mNombre + "(";
        for (int i=0; i<mPars.size(); i++) 
        {
            TParameter p=(TParameter) mPars.elementAt(i);
           // r+="arg" + i;
            r+=p.mTipo+" arg"+ i+",";
        }
        if (mPars.size()>0)
            r=r.substring(0, r.length()-1);
        r+=")";
        return r;
    }
    
    public String getDeclaracionDeObjeto(String objeto) 
    {
        return this.mNombre + " " + objeto + "=null;";
    }
    
    public String getInstanciacion(String objeto) 
    {
        return objeto + "=new " + toString() + ";";
    }
    
    public String getLlamada(String objeto) 
    {
        String r= objeto + "=new " + mNombre +"(";
        for (int i=0; i<mPars.size(); i++) 
        {
            TParameter p=(TParameter) mPars.elementAt(i);
            r+="arg" + (i+1) + ", ";
        }
        if (mPars.size()>0)
            r=r.substring(0, r.length()-2);
        r+=");";
        return r;   
    }  
    
    public String getLlamada(String objeto, String valores, int pos) 
    {
        String r= objeto + "=new " + mNombre +"(";
        for (int i=0; i<mPars.size(); i++) 
        {
            TParameter p=(TParameter) mPars.elementAt(i);
            r+="v" + i + "_0[cv" + i + "_" + "0], ";
        }
        if (mPars.size()>0)
            r=r.substring(0, r.length()-2);
        r+=");";
        return r;   
    }
    
    protected void loadLaunchedExceptions(Constructor c) {
        for (int i=0; i<c.getExceptionTypes().length; i++) {
            Class ex=c.getExceptionTypes()[i];
            String exName=ex.getName();
            this.mLaunchedExceptions.add(exName);
        }
    }
    
    protected void loadLaunchedExceptions(Method m) {
        for (int i=0; i<m.getExceptionTypes().length; i++) {
            Class ex=m.getExceptionTypes()[i];
            String exName=ex.getName();
            this.mLaunchedExceptions.add(exName);
        }
    }

    public TOperation copy() {
        TConstructor result=new TConstructor();
        result.mNombre=this.mNombre;
        result.mPars=new Vector();
        result.mPos=this.mPos;
        for (int i=0; i<this.getParametros().size(); i++) {
            TParameter p=(TParameter) this.getParametro(i);
            result.mPars.addElement(p.copy());
        }
        for (int i=0; i<this.mLaunchedExceptions.size(); i++) {
            result.mLaunchedExceptions.add(this.mLaunchedExceptions.get(i));
        }
        return result;
    }
    
    protected TConstructor() {
    }

	public boolean isFactory() {
		return isFactory;
	}


}
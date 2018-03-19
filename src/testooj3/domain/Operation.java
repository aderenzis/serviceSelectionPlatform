package testooj3.domain;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Vector;

import testooj3.persistence.Agente;

import banco1.Cuenta;

import com.thoughtworks.paranamer.BytecodeReadingParanamer;

/**
 * @author  andres
 */
public class Operation extends TOperation implements Serializable
{
    Class mTipo;
	private boolean isFinal;
    
    public Operation(Method m, int pos) 
    {
        mVisibilidad="public";
        mNombre=m.getName();
        mTipo=m.getReturnType();
        calculaPos(pos);
        Class[] tipos=m.getParameterTypes();
        mPars=new Vector();
        mPre=new Vector();
        mPost=new Vector();
        System.out.println("Metodo: "+mNombre); 
        
        System.out.println(mTipo.toString());
        
        //Obtenemos el nombre de los parametros
        BytecodeReadingParanamer asm= new BytecodeReadingParanamer();
		String[] parameterNames = asm.lookupParameterNames(m);  
		
		//  Se imprimen los nombre de los parametros de los metodos involucrados
		System.out.println("Metodo: "+m.getName());
		for (int i=0; i<parameterNames.length; i++) 
        {
            System.out.println("\t "+parameterNames[i]);
        }
		System.out.println();
		
		
        for (int i=0; i<tipos.length; i++) 
        {
            TParameter parametro=new TParameter(tipos[i],"",parameterNames[i]);
            if(!Agente.getAgente().esPrimitivo(parametro.getTipo()))
           	{
            	parametro.addCampos(tipos[i].getFields());
            	System.out.println("Campos: "+ parametro.getMCampos());
        	}
            mPars.addElement(parametro);
        }
        this.loadLaunchedExceptions(m);
    }
    
    public Operation(String nombre, String rType,Vector <String> parTypes, int pos) 
    {
        mVisibilidad="public";
        mNombre=nombre;
        //try {
        	mTipo=this.getType(rType);
       // }
       // catch (Exception e){
        //	mTipo=Class.class.;
        //	e.printStackTrace();
       // }
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
    
    
    public Operation(String nombre, String rType,Vector <String> parTypes, Vector <String> parValues, int pos) 
    {
        mVisibilidad="public";
        mNombre=nombre;
        //try {
        	mTipo=this.getType(rType);
       // }
       // catch (Exception e){
        //	mTipo=Class.class.;
        //	e.printStackTrace();
       // }
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
        String r=mVisibilidad + " " + mTipo.getName() + " " + mNombre + "(";
        for (int i=0; i<mPars.size(); i++) 
        {
            TParameter p=(TParameter) mPars.elementAt(i);
            r+=p.toString() +" "+ p.getMNombre()+ ", ";// " x" + (i+1) + ", ";
        }
        if (mPars.size()>0)
            r=r.substring(0, r.length()-2);
        r+=")";
        return r;	
    }
    
    public String getLlamada(String objeto, String valores, int pos)
    {
        String r=""; 
        r=objeto + "." + mNombre + "(" + valores + ");";
        if (!mTipo.equals(void.class))
            r=mTipo.getName() + " result" + (pos+1) + "=" + r;
        return r;
    }
    
    public String getLlamada(String objeto, int cont) 
    {
        String r=objeto + "." + mNombre +"(";
        if (!mTipo.equals(void.class))
            r=mTipo.getName() + " result=" + r;
        for (int i=0; i<mPars.size(); i++) 
        {
            TParameter p=(TParameter) mPars.elementAt(i);
            r+="arg" + (cont+i) + ", ";
        }
        if (mPars.size()>0)
            r=r.substring(0, r.length()-2);
        r+=");";
        return r;   
    }    
    
    public String getTipo() 
    {
        return this.mTipo.getName();
    }
    
    public boolean hasResultPostcondition() 
    {
        for (int i=0; i<this.getPost().size(); i++) 
        {
            String linea=getPost().get(i).toString();
            if (linea.indexOf("ResultPostconditionException")!=-1)
                return true;
        }
        return false;
    }
    
    public Class getTrueClass() {
        return mTipo;
    }
    
    protected void loadLaunchedExceptions(Method m) {
        for (int i=0; i<m.getExceptionTypes().length; i++) {
            Class ex=m.getExceptionTypes()[i];
            String exName=ex.getName();
            this.mLaunchedExceptions.add(exName);
        }
    }
    
    public TOperation copy() {
        Operation result=new Operation();
        result.mTipo=this.mTipo;
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
    
    protected Operation() {
    }    
    
    public String getSignature() {
      	String r=this.mTipo.getName() + " " + mNombre + "(";
      	for (int i=0; i<mPars.size(); i++) {
      		TParameter p=(TParameter) mPars.get(i);
      		r+=p.getTipo() + ", ";
      	}
      	if (r.endsWith(", "))
      		r=r.substring(0, r.length()-2);
      	r+=")";
      	return r;
      }

	public String getWholeSignature() {
		String r=mVisibilidad + (this.isFinal ? " final" : "") + " " + mTipo.getName() + " " + mNombre + "(";
        for (int i=0; i<mPars.size(); i++) 
        {
            TParameter p=(TParameter) mPars.elementAt(i);
            r+=p.toString() + " x" + (i+1) + ", ";
        }
        if (mPars.size()>0)
            r=r.substring(0, r.length()-2);
        r+=")";
		String ex="";
		for (int i=0; i<this.mLaunchedExceptions.size(); i++) {
			ex+=this.mLaunchedExceptions.get(i).toString() + ", ";
		}
		if (ex.length()>0) {
			ex=ex.substring(0, ex.length()-2);
			r= r + " throws " + ex;
		}
      	return r;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal=isFinal;
	}

	public boolean getFinal() {
		return this.isFinal;
	}
    
}
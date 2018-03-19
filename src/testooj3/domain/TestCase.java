package testooj3.domain;

import java.util.Vector;

/**
 * @author  andres
 */
public class TestCase implements java.io.Serializable
{
	protected String mNombre;
	protected TestTemplate mTS;
	protected Vector mParsConstructor;	// Valores de los parámetros del constructor
	protected Object[] mParsMetodos;		// Array de vectores. Cada vector tiene los valores de los parámetros de cada método
	
	public TestCase(TestTemplate ts) {
		mTS=ts;
		mParsConstructor=new Vector();
		mParsMetodos=new Object[mTS.mMetodos.size()];
	}
	
	public boolean functionallyEquivalent(TestCase t) {
		if (!this.mTS.getConstructor().equals(t.getConstructor()))
			return false;
		if (this.mTS.getMethods().size()!=t.getMetodos().size())
			return false;
		for (int i=0; i<this.mTS.getMethods().size(); i++) {
			Operation thisM=(Operation) this.getMetodos().get(i);
			Operation otroM=(Operation) t.getMetodos().get(i);
			if (!thisM.equals(otroM))
				return false;
		}
		for (int i=0; i<this.mParsConstructor.size(); i++) {
			Object thisValue=this.mParsConstructor.get(i);
			Object otroValue=t.mParsConstructor.get(i);
			if (thisValue!=otroValue) return false;
		}
		for (int i=0; i<this.mParsMetodos.length; i++) {
			Vector parsThisMetodo=(Vector) this.mParsMetodos[i];
			Vector parsOtroMetodo=(Vector) t.mParsMetodos[i];
			if (parsThisMetodo!=null && parsOtroMetodo==null || parsThisMetodo==null && parsOtroMetodo!=null)
				return false;
			if (parsThisMetodo!=null && parsOtroMetodo!=null)
				for (int j=0; j<parsThisMetodo.size(); j++) {
					Object thisValue=parsThisMetodo.get(j);
					Object otroValue=parsOtroMetodo.get(j);
					if (thisValue!=otroValue) return false;
				}
		}
		return true;
	}
	
	public boolean lanzaExcepciones() 
	{
		for (int i=0; i<this.getMetodos().size(); i++) 
		{
			Operation m=(Operation) getMetodos().elementAt(i);
			if (m.getAssertsPre().length()>0 || m.getAssertsPost().length()>0)
				return true;
		}
		return false;
	}
	
	public void setNombre(String s) {
		mNombre=s;
	}
	
	public String getNombre (){
		return mNombre;
	}
	
	public String getTipo() 
	{
		return mTS.getConstructor().mNombre;
	}
	
	public void addParametro(TestValue vp) {
		mParsConstructor.addElement(vp);
	}
	
	public void addParametro(TestValue vp, int numeroDeMetodo) {
		if (mParsMetodos[numeroDeMetodo]==null)
			mParsMetodos[numeroDeMetodo]=new Vector();
		((Vector) mParsMetodos[numeroDeMetodo]).addElement(vp);
	}
	
	/*metodo para obtener el testcase para mostrarlo en el arbol*/
	public Vector cogerDefinicion() {
		Vector r=new Vector();
		r.addElement(this.mTS.mConstructor.mNombre + "(" + valoresParametrosC() + ");");
		for (int i=0; i<mTS.mMetodos.size(); i++) {
			Operation m=(Operation) mTS.mMetodos.elementAt(i);
			Vector v=(Vector) this.mParsMetodos[i];
			if (v==null) v=new Vector();
			r.addElement(m.mNombre + "(" + valoresParametrosM(v, m.mPars) + ");");
		}
		return r;
	}
	
	public TConstructor getConstructor() 
	{
		return this.mTS.mConstructor;
	}
	
	public Vector getMetodos() 
	{
		return this.mTS.mMetodos;
	}
  
  public TestTemplate getTestTemplate() 
  {
    return this.mTS;
  }
	
	public String toString() {
		String r="";
    if (mTS.mConstructor!=null) {
      r+="// " + this.mNombre + "\n" +
           this.mTS.mConstructor.getLlamada("o", "pars", 0) + "\n";  
    }
		for (int i=0; i<mTS.mMetodos.size(); i++) {
			Operation m=(Operation) mTS.mMetodos.elementAt(i);
			Vector v=(Vector) this.mParsMetodos[i];
			r+=m.getLlamada("o", valoresParametrosM(v, m.mPars), i) + "\n";
		}
		return r;
	}
	
	public Vector getValoresParametrosConstructor() 
	{
		return this.mParsConstructor;
	}
	
	public Vector getValoresParametros(int numMetodo) 
	{
		return (Vector) this.mParsMetodos[numMetodo];
	}
	
	protected String valoresParametrosC() {
		String r=new String();
		Vector parametros=mTS.mConstructor.mPars;
		for (int i=0; i<this.mParsConstructor.size(); i++) {
			r+="("+((TParameter)parametros.elementAt(i)).getTipo ()+")"+this.mParsConstructor.elementAt(i).toString () + ", ";
		}
		if (!r.equals (""))
			r=r.substring(0, r.length()-2);
		return r;
	}
	
	private Object[] parametrosConstructor(){
		Object [] salida=new Object [mParsConstructor.size ()];
		
		for (int i=0;i<mParsConstructor.size ();i ++){
			salida[i]=mParsConstructor.elementAt (i);
		}
		return salida;
	}
	
  public static String valoresParametrosM(Vector valores, Vector parametros) {
    int[] contador={-1};
    return valoresParametrosM(valores, parametros, contador);
  }
	
	public static String valoresParametrosM(Vector valores, Vector parametros, int[] contador) {
		String r="";
		if (valores!=null && parametros!=null) {
			for (int i=0; i<valores.size(); i++) {
				TParameter par=(TParameter) parametros.elementAt(i);
        if (contador[0]!=-1)
          r+="x" + (contador[0]++) + ", ";
        else
  				r+="x" + (i+1) + ", ";
			}
			if (!r.equals (""))
				r=r.substring(0, r.length()-2);
		}
		return r;
	}
	
	private Object[] parametrosMetodo(Vector v){
		Object [] salida=new Object [v.size ()];
		
		for (int i=0;i<v.size ();i ++){
			salida[i]=v.elementAt (i);
		}
		return salida;
	}

    /**
     * @return
     */
    public Vector getConstructorParameters() {
        return this.mParsConstructor;
    }

	public Object[] getParsMetodos() {
		return mParsMetodos;
	}


}

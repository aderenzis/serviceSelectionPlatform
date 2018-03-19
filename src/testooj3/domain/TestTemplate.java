package testooj3.domain;

import java.util.Vector;

/**
 * @author  andres
 */
public class TestTemplate implements java.io.Serializable
{
    protected String mNombre;
    protected TConstructor mConstructor;
    protected Vector<TOperation> mMetodos;
    
    public TestTemplate() {
        mMetodos=new Vector<TOperation>();
    }
    
    public void setName(String s) {
        mNombre=s;
    }
    
    public String getName() {
        return mNombre;
    }
    
    public TestTemplate(TestTemplate sec) {
        mConstructor=sec.mConstructor;
        mMetodos=sec.mMetodos;
    }
    
    public TConstructor getConstructor(){
        return mConstructor;
    }
    
    public Vector<TOperation> getMethods(){
        return mMetodos;
    }
    
    public Object clone() {
        TestTemplate sec=new TestTemplate();
        sec.mConstructor=mConstructor;
        
        for (int i=0; i<mMetodos.size(); i++) {
            Operation metodo=(Operation) mMetodos.elementAt(i);
            sec.mMetodos.addElement(metodo);
        }
        return sec;
    }
    
    public void setConstructor(TConstructor c) {
        this.mConstructor=c;
    }
    
    public void setMethod(int i, Operation metodo) {
        if (mMetodos.size()<i)
            mMetodos.setSize(i);
        mMetodos.setElementAt(metodo, i-1);
    }
    
    public void addMethod(Operation metodo){
    	this.mMetodos.add(metodo);
    }
    
    public void addMethodFirst(Operation metodo){
    	this.mMetodos.add(0,metodo);
    }
    
    public void addOperationFirst(TOperation op){
    	if (op instanceof TConstructor)
    		this.setConstructor((TConstructor) op);
    	else
    		this.addMethodFirst((Operation) op);
    }
    
    public void addOperation(TOperation op){
    	if (op instanceof TConstructor)
    		this.setConstructor((TConstructor) op);
    	else
    		this.addMethod((Operation) op);
    }
    
    public String toString() {
        String r="";
        byte[] b={13,10};
        String nl=new String(b);
        if (mConstructor!=null)
            r=this.getName()+":"+nl+mConstructor.toString() + nl;
        
        for (int i=0; i<mMetodos.size(); i++) {
        	r+=mMetodos.elementAt(i).toString()+nl;
        }
        return r;
    }		
    
    /**
     * Devuelve la expresión regular formada por los valores pos de las operaciones incluidas en este objeto
     * @return 
     */
    public String getRE() 
    {
        String r="";
        if (mConstructor!=null)
            r+=this.mConstructor.getPos();
        for (int i=0; i<this.mMetodos.size(); i++) 
        {
            Operation m=(Operation) mMetodos.elementAt(i);
            r+=m.getPos();
        }
        return r;
    }
    
    public TestTemplate copy() {
        TestTemplate result=new TestTemplate();
        result.setName(this.getName());
        result.setConstructor((TConstructor) this.getConstructor().copy());
        for (int i=0; i<this.mMetodos.size(); i++) {
            Operation m=(Operation) this.mMetodos.get(i);
            result.mMetodos.add(m.copy());
        }
        return result;
    }

	public int getNumberOfParameters() {
		int result=this.mConstructor.getParametros().size();
		for (int i=0; i<this.getMethods().size(); i++) {
			Operation m=(Operation) this.getMethods().get(i);
			result+=m.getParametros().size();
		}
		return result;
	}

	public Vector<TParameter> getParameters() {
		Vector<TParameter> result=new Vector<TParameter>();
		if (this.mConstructor!=null)
			for (int i=0; i<this.mConstructor.getParametros().size(); i++) {
				TParameter p=this.mConstructor.getParametro(i);
				result.add(p);
			}
		for (int i=0; i<this.getMethods().size(); i++) {
			Operation m=(Operation) this.getMethods().get(i);
			for (int j=0; j<m.getParametros().size(); j++) {
				TParameter p=m.getParametro(j);
				result.add(p);
			}
		}
		return result;
	}

	public int getNumberOfCombinations() throws Exception {
		int numeroDeCombinaciones=1;
		for (int i=0; i<this.mConstructor.getParametros().size(); i++) {
			TParameter p=this.mConstructor.getParametro(i);
			if (p.getTestValues().length==0) 
				p.loadTestValues();
			numeroDeCombinaciones*=p.getTestValues().length;
		}
		for (int i=0; i<this.getMethods().size(); i++) {
			Operation m=(Operation) this.getMethods().get(i);
			for (int j=0; j<m.getParametros().size(); j++) {
				TParameter p=m.getParametro(j);
				if (p.getTestValues().length==0) 
					p.loadTestValues();
				numeroDeCombinaciones*=p.getTestValues().length;
			}
		}
		return numeroDeCombinaciones;
	}	
}
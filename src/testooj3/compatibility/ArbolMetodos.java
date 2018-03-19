package testooj3.compatibility;

import java.util.Vector;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;

/**
 * @author  andres
 */
public class ArbolMetodos 
{
	protected Operation mValor;
	protected Vector<ArbolMetodos> mHijos;
	protected ArbolMetodos mPadre;
	protected int profundidad;
	protected Operation originalMethod;
	protected Vector<Integer> ordenParametros;

	public ArbolMetodos() 
	{
		this.mValor=null;
		this.mPadre=null;
		this.mHijos=new Vector<ArbolMetodos>();
		this.profundidad=0;
		this.ordenParametros=new Vector<Integer>();
	}
	
	protected ArbolMetodos(Operation method, Operation originalMethod) {
		this.mValor=method;
		this.originalMethod=originalMethod;
		this.mHijos=new Vector<ArbolMetodos>();
		this.ordenParametros=new Vector<Integer>();
	}

	public void add(Operation method, int nivel, Operation originalMethod) {
		ArbolMetodos raiz=this.getRaiz();
		if (nivel==1) {
			ArbolMetodos hijo=new ArbolMetodos(method, originalMethod);
			raiz.addHijo(hijo);
			for (int i=0; i<method.getParametros().size(); i++) {
				hijo.addParametroIEsimo(i);
			}
		} else {
			Vector<ArbolMetodos> nodos=new Vector<ArbolMetodos>();
			this.cargaNodosDeNivel(raiz, nivel-1, nodos);
			for (int i=0; i<nodos.size(); i++) {
				ArbolMetodos nodo=(ArbolMetodos) nodos.get(i);
				ArbolMetodos hijo=new ArbolMetodos(method, originalMethod);
				if (!nodo.contiene(hijo))
					nodo.addHijo(hijo);
				for (int j=0; j<method.getParametros().size(); j++) {
					hijo.addParametroIEsimo(j);
				}
			}
		}
	}
	
	private void addParametroIEsimo(int i) {
		this.ordenParametros.add(i);
	}

	private boolean contiene(ArbolMetodos hijo) {
		if (this.mValor==null) return false;
		if (this.mValor.getWholeSignature().equals(hijo.mValor.getWholeSignature()))
			return true;
		for (int i=0; i<this.mHijos.size(); i++) {
			ArbolMetodos auxi=this.mHijos.get(i);
			if (hijo.mValor.getWholeSignature().equals(auxi.mValor.getWholeSignature()))
				return true;
		}
		return false;
	}

	private void cargaNodosDeNivel(ArbolMetodos padre, int nivel, Vector<ArbolMetodos> result) {
		Vector<ArbolMetodos> hijos=padre.mHijos;
		for (int i=0; i<hijos.size(); i++) {
			ArbolMetodos hijo=(ArbolMetodos) hijos.get(i);
			if (hijo.profundidad==nivel)
				result.add(hijo);
			else
				this.cargaNodosDeNivel(hijo, nivel, result);
				
		}
	}

	protected ArbolMetodos getRaiz() {
		ArbolMetodos a=this;
		while (a.mPadre!=null)
			a=a.mPadre;
		return a;
	}
	
	public void addHijo(ArbolMetodos hijo)
	{
		mHijos.add(hijo);
		hijo.mPadre=this;
		hijo.profundidad=this.profundidad+1;
	}

	public ArbolMetodos getPadre() 
	{
		return mPadre;
	}

	public void cargaHojas(Vector<ArbolMetodos> hojas)
	{
		if (this.mHijos.size()==0) {
			hojas.add(this);
		}
		for (int i=0; i<mHijos.size(); i++) 
		{
			ArbolMetodos hijo=(ArbolMetodos) mHijos.elementAt(i);
			hijo.cargaHojas(hojas);
		}
	}

	public Vector<ArbolMetodos> getRama() 
	{
		Vector<ArbolMetodos> aux=new Vector<ArbolMetodos>();
		cargaRama(aux);
		Vector<ArbolMetodos> result=new Vector<ArbolMetodos>();
		for (int i=aux.size()-1; i>=0; i--)
			result.add(aux.elementAt(i));
		return result;
	}

	protected void cargaRama(Vector<ArbolMetodos> rama) 
	{
		rama.add(this);
		if (mPadre!=null && mPadre.mValor!=null) 
			mPadre.cargaRama(rama);
	}

	public int getHeight() {
		int height=0;
		ArbolMetodos nodo=this;
		while (nodo.mPadre!=null) {
			height++;
			nodo=nodo.mPadre;
		}
		return height;
	}

	public ArbolMetodos root() {
		ArbolMetodos nodo=this;
		while (nodo.mPadre!=null) {
			nodo=nodo.mPadre;
		}
		return nodo;
	}
	
	public String toString() {
		String r;
		if (this.mValor==null)
			r="root\n";
		else 
			r=this.mValor + "/" + this.originalMethod + "/" + this.ordenParametros + "\n";
		r+=showHijos(this);
		return r;
	}

	private String showHijos(ArbolMetodos padre) {
		String r="";
		for (int i=0; i<padre.mHijos.size(); i++) {
			ArbolMetodos hijo=padre.mHijos.get(i);
			for (int j=0; j<hijo.profundidad; j++) {
				r=r+"\t";
			}
			r=r+hijo.mValor + "/" + hijo.originalMethod + "/" + this.ordenParametros + "\n";
			r+=showHijos(hijo);
		}
		return r;
	}

	public void addHijos(ArbolMetodos auxi, int nivel) {
		ArbolMetodos raiz=this.getRaiz();
		if (nivel==1) {
			raiz.addHijo(auxi);
		} else {
			Vector<ArbolMetodos> nodos=new Vector<ArbolMetodos>();
			this.cargaNodosDeNivel(raiz, nivel-1, nodos);
			for (int i=0; i<nodos.size(); i++) {
				ArbolMetodos nodo=nodos.get(i);
				nodo.addHijo(auxi);
			}
		}
	}

	public String getCall() {
		String r=this.mValor.getNombre() + "(";
		for (int i=0; i<this.ordenParametros.size(); i++) 
        {
            int orden=this.ordenParametros.get(i);
            r+="x" + (orden+1) + ", ";
        }
		if (r.endsWith(", ")) r=r.substring(0, r.length()-2);
		r+=");";
		return r;
	}
}
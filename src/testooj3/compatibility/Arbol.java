package testooj3.compatibility;

import java.util.Vector;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;

/**
 * @author  andres
 */
public class Arbol 
{
	protected Operation mValor;
	protected Vector paramMatches;
	protected Vector mHijos;
	protected Arbol mPadre;
	protected int profundidad;
	protected Operation originalMethod;

	public Arbol() 
	{
		this.mValor=null;
		this.paramMatches=null;
		this.mPadre=null;
		this.mHijos=new Vector();
		this.profundidad=0;
	}
	
	protected Arbol(Operation method, Vector paramMatches, Operation originalMethod) {
		this.mValor=method;
		this.paramMatches=paramMatches;
		this.originalMethod=originalMethod;
		this.mHijos=new Vector();
	}

	public void add(Operation candidateMethod, Vector paramMatches, int nivel, Operation originalMethod) {
		Arbol raiz=this.getRaiz();
		if (nivel==1) {
			Arbol hijo=new Arbol(candidateMethod,paramMatches,originalMethod);
			raiz.addHijo(hijo);
		} else {
			Vector nodos=new Vector();
			this.cargaNodosDeNivel(raiz, nivel-1, nodos);
			for (int i=0; i<nodos.size(); i++) {
				Arbol nodo=(Arbol) nodos.get(i);
				//if (! nodo.isRepeatedOnBranch(candidateMethod)){
					Arbol hijo=new Arbol(candidateMethod,paramMatches,originalMethod);
					nodo.addHijo(hijo);
				//}
			}
		}
	}
	
	
	
	private boolean isRepeatedOnBranch(Operation candidateMethod) {
		boolean result = false;
		if (this.mValor == candidateMethod)
			result = true;
		else {
			if (mPadre!=null && mPadre.mValor!=null) 
				result = mPadre.isRepeatedOnBranch(candidateMethod);
		}
		return result;
	}

	private void cargaNodosDeNivel(Arbol padre, int nivel, Vector result) {
		Vector hijos=padre.mHijos;
		for (int i=0; i<hijos.size(); i++) {
			Arbol hijo=(Arbol) hijos.get(i);
			if (hijo.profundidad==nivel)
				result.add(hijo);
			else
				this.cargaNodosDeNivel(hijo, nivel, result);
				
		}
	}

	protected Arbol getRaiz() {
		Arbol a=this;
		while (a.mPadre!=null)
			a=a.mPadre;
		return a;
	}
	
	public void addHijo(Arbol hijo)
	{
		mHijos.add(hijo);
		hijo.mPadre=this;
		hijo.profundidad=this.profundidad+1;
	}

	public Arbol getPadre() 
	{
		return mPadre;
	}

	public void cargaHojas(Vector hojas)
	{
		if (this.mHijos.size()==0) {
			hojas.add(this);
		}
		for (int i=0; i<mHijos.size(); i++) 
		{
			Arbol hijo=(Arbol) mHijos.elementAt(i);
			hijo.cargaHojas(hojas);
		}
	}

	public Vector getRama() 
	{
		Vector aux=new Vector();
		cargaRama(aux);
		Vector result=new Vector();
		for (int i=aux.size()-1; i>=0; i--)
			result.add(aux.elementAt(i));
		return result;
	}

	protected void cargaRama(Vector rama) 
	{
		rama.add(this);
		if (mPadre!=null && mPadre.mValor!=null) 
			mPadre.cargaRama(rama);
	}

	public int getHeight() {
		int height=0;
		Arbol nodo=this;
		while (nodo.mPadre!=null) {
			height++;
			nodo=nodo.mPadre;
		}
		return height;
	}

	public Arbol root() {
		Arbol nodo=this;
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
			r=this.mValor + "/" + this.originalMethod + "\n";
		r+=showHijos(this);
		return r;
	}

	
	private String showHijos(Arbol padre) {
		String r="";
		for (int i=0; i<padre.mHijos.size(); i++) {
			Arbol hijo=(Arbol) padre.mHijos.get(i);
			for (int j=0; j<hijo.profundidad; j++) {
				r=r+"\t";
			}
			r=r+hijo.mValor + "/" + hijo.originalMethod + "\n";
			r+=showHijos(hijo);
		}
		return r;
	}

	public int cntHojas() {
		// llama y recupera cantidad de hojas del Arbol
		return cntHojas(this);
	}
	
	public int cntHojas(Arbol hoja){
		int c=0;
		if (hoja.mHijos.size()==0){
				c+=1;
		}

		for (int i=0; i<hoja.mHijos.size(); i++) 
		{
			Arbol hijo=(Arbol) hoja.mHijos.elementAt(i);
			c +=cntHojas(hijo);
		}	
		return c;
	}

	public void muestraArbol(){
		System.out.println(this.showHijos(this));
	}
}
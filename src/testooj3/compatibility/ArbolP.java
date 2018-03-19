package testooj3.compatibility;

import java.util.Vector;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;

/**
 * @author  andres
 */
public class ArbolP 
{
	protected TParameter mValor;
	protected int posParametro;
	protected Vector<ArbolP> mHijos;
	protected ArbolP mPadre;
	protected int profundidad;
	protected Vector<Vector<Integer>> combinaciones;

	public ArbolP() 
	{
		this.mValor=null;
		this.mPadre=null;
		this.mHijos=new Vector<ArbolP>();
		this.profundidad=0;
		this.combinaciones=new Vector<Vector<Integer>>();
	}
	
	protected ArbolP(TParameter p, int posParametro) {
		this.mValor=p;
		this.posParametro=posParametro;
		this.mHijos=new Vector<ArbolP>();
	}

	public void addHijos(Operation m, int parIndex, int nivel) {
		TParameter p=m.getParametro(parIndex);
		ArbolP raiz=this.getRaiz();
		if (nivel==1) {
			ArbolP hijo=new ArbolP(p, parIndex);
			raiz.addHijo(hijo);
		} else {
			Vector<ArbolP> nodos=new Vector<ArbolP>();
			this.cargaNodosDeNivel(raiz, nivel-1, nodos);
			for (int i=0; i<nodos.size(); i++) {
				ArbolP nodo=nodos.get(i);
				ArbolP hijo=new ArbolP(p, parIndex);
				if (!nodo.contiene(hijo))
					nodo.addHijo(hijo);
			}
		}
	}
	
	private boolean contiene(ArbolP hijo) {
		boolean r=false;
		if (this.mValor==null) 
			return false;
		if (this.mValor.equals(hijo.mValor) && this.posParametro==hijo.posParametro)
			return true;
		if (this.mPadre!=null) {
			r=r || this.mPadre.contiene(hijo);
		}
		return r;
	}

	private void cargaNodosDeNivel(ArbolP padre, int nivel, Vector<ArbolP> result) {
		Vector<ArbolP> hijos=padre.mHijos;
		for (int i=0; i<hijos.size(); i++) {
			ArbolP hijo=(ArbolP) hijos.get(i);
			if (hijo.profundidad==nivel)
				result.add(hijo);
			else
				this.cargaNodosDeNivel(hijo, nivel, result);				
		}
	}

	protected ArbolP getRaiz() {
		ArbolP a=this;
		while (a.mPadre!=null)
			a=a.mPadre;
		return a;
	}
	
	public void addHijo(ArbolP hijo)
	{
		mHijos.add(hijo);
		hijo.mPadre=this;
		hijo.profundidad=this.profundidad+1;
	}

	public ArbolP getPadre() 
	{
		return mPadre;
	}

	public void cargaHojas(Vector<ArbolP> hojas)
	{
		if (this.mHijos.size()==0) {
			hojas.add(this);
		}
		for (int i=0; i<mHijos.size(); i++) 
		{
			ArbolP hijo=(ArbolP) mHijos.elementAt(i);
			hijo.cargaHojas(hojas);
		}
	}

	public Vector<ArbolP> getRama() 
	{
		Vector<ArbolP> aux=new Vector<ArbolP>();
		cargaRama(aux);
		Vector<ArbolP> result=new Vector<ArbolP>();
		for (int i=aux.size()-1; i>=0; i--)
			result.add(aux.elementAt(i));
		return result;
	}

	protected void cargaRama(Vector<ArbolP> rama) 
	{
		rama.add(this);
		if (mPadre!=null && mPadre.mValor!=null) 
			mPadre.cargaRama(rama);
	}

	public int getHeight() {
		int height=0;
		ArbolP nodo=this;
		while (nodo.mPadre!=null) {
			height++;
			nodo=nodo.mPadre;
		}
		return height;
	}

	public ArbolP root() {
		ArbolP nodo=this;
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
			r=this.mValor + "/" + this.posParametro + "\n";
		r+=showHijos(this);
		return r;
	}

	private String showHijos(ArbolP padre) {
		String r="";
		for (int i=0; i<padre.mHijos.size(); i++) {
			ArbolP hijo=padre.mHijos.get(i);
			for (int j=0; j<hijo.profundidad; j++) {
				r=r+"\t";
			}
			r=r+hijo.mValor + "/" + hijo.posParametro + "\n";
			r+=showHijos(hijo);
		}
		return r;
	}

	public void calcularCombinaciones() {
		Vector<ArbolP> hojas=new Vector<ArbolP>();
		this.cargaHojas(hojas);
		for (ArbolP hoja : hojas) {
			Vector<Integer> auxi=new Vector<Integer>();
			while (hoja.mPadre!=null) {
				auxi.add(hoja.posParametro);
				hoja=hoja.mPadre;
			}
			Vector<Integer> combinacion=new Vector<Integer>();
			for (int i=auxi.size()-1; i>=0; i--) {
				combinacion.add(auxi.get(i));
			}
			this.combinaciones.add(combinacion);
		}
	}

}
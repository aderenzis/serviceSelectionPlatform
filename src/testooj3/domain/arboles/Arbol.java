package testooj3.domain.arboles;
import java.util.Vector;
import testooj3.domain.TestValue;

/**
 * @author  andres
 */
public class Arbol 
{
	protected TestValue mValor;
	protected Vector mHijos;
	protected Arbol mPadre;

	public Arbol() 
	{
		mValor=null;
		mPadre=null;
		mHijos=new Vector();
	}
	
	
	public Arbol(TestValue v) 
	{
		mValor=v;
		mHijos=new Vector();
	}

	public void addHijo(Arbol hijo)
	{
		mHijos.add(hijo);
		hijo.mPadre=this;
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

	public TestValue getTestValue() 
	{
		return this.mValor;
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

	public TestValue getValueAt(int altura) {
		Arbol hoja=this;
		while (hoja.mPadre.mValor!=null)
			hoja=hoja.mPadre;
		int cont=1;
		while (cont<altura) {
			hoja=(Arbol) hoja.mHijos.get(0);
			cont++;
		}
		return hoja.getTestValue();
	}

	public Arbol root() {
		Arbol nodo=this;
		while (nodo.mPadre!=null) {
			nodo=nodo.mPadre;
		}
		return nodo;
	}
}
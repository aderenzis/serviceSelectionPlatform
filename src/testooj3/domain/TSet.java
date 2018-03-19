package testooj3.domain;

import java.util.Vector;

public class TSet extends Vector
{
	public TSet() {
		super();
	}
	
	public TSet(Object[] a) {
		this();
		for (int i=0; i<a.length; i++)
			addElement(a[i]);
	}
	
	public TSet(Vector v) {
		this();
		for (int i=0; i<v.size(); i++)
			add(v.elementAt(i));
	}
	
	public boolean add(Object o) {
		if (!contains(o)) {
			addElement(o);
			return true;
		}
		return false;
	}
	
	public int cardinal() {
		return size();
	}
	
	public Object get(int i) {
		return elementAt(i);
	}
	
	public static TSet productoCartesiano(TSet a, TSet b) {
		int cardA=a.cardinal();
		int cardB=b.cardinal();
		
		TSet r=new TSet();
		for (int i=0; i<cardA; i++) {
			for (int j=0; j<cardB; j++) {
				Vector e=new Vector();
				e.addElement(a.get(i));
				e.addElement(b.get(j));
				r.add(e);
			}
		}
		return r;
	}
	
	public static TSet productoCartesiano(Object[] a, Object[] b) {
		TSet cA=new TSet(a);
		TSet cB=new TSet(b);
		return productoCartesiano(cA, cB);
	}
	
	public static TSet productoCartesiano(Vector a, Vector b) {
		TSet cA=new TSet(a);
		TSet cB=new TSet(b);
		return productoCartesiano(cA, cB);
	}
	
	public static TSet productoCartesiano(Vector conjuntos) {
		TSet r;
		if (conjuntos.size()==1) {
			 r=(TSet) conjuntos.elementAt(0);
		} else {
			r=new TSet();
			TSet a=(TSet) conjuntos.elementAt(0);
			TSet b=(TSet) conjuntos.elementAt(1);
			r=productoCartesiano(a, b);
			for (int i=2; i<conjuntos.size(); i++) {
				r=productoCartesiano(r, (TSet) conjuntos.elementAt(i));
			}
		}
		return r;
	}
}

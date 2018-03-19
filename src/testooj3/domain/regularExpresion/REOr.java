package testooj3.domain.regularExpresion;

import java.util.Vector;

public class REOr extends RegularExpresion{

	protected Vector<RegularExpresion> expresiones = new Vector<RegularExpresion>();
	
	public REOr(Vector v){
		for(int i=0;i<v.size();i++){
			Object o = v.get(i);
			if(o instanceof REAnd){
				expresiones.add((RegularExpresion)o);
			}
		}
	}
	
	@Override
	public Vector<String> getSecuencia(int l) {
		Vector resultado = new Vector();
		
		for(int i=0;i<expresiones.size();i++){
			RegularExpresion e = expresiones.get(i);
			Vector<String> sec = e.getSecuencia(l);
			resultado.addAll(sec);
		}
		return resultado;
	}

	
}

package testooj3.domain.regularExpresion;

import java.util.Vector;

public class REAnd extends RegularExpresion{

	protected Vector<RegularExpresion> expresiones = new Vector<RegularExpresion>();
	
	public REAnd(Vector v){
		for(int i=0;i<v.size();i++){
			Object o = v.get(i);
			if(o instanceof Character){
				RESimple s = new RESimple((Character)o);
				expresiones.add(s);
			}
			else{
				expresiones.add((RegularExpresion)o);
			}
		}
	}
	
	@Override
	public Vector getSecuencia(int l) {
		Vector resultado = new Vector();

		resultado = expresiones.get(0).getSecuencia(l);
		
		for(int i=1;i<expresiones.size();i++){
			RegularExpresion e = expresiones.get(i);
			Vector<String> sec = e.getSecuencia(l);
			Vector<String> aux = new Vector<String>();
			for(int j=0;j<resultado.size();j++){
				for(int k = 0;k<sec.size();k++){
					String cadena = resultado.get(j)+sec.get(k);
					aux.add(cadena);
				}
			}
			resultado = aux;
			
		}
		return resultado;
	}

}

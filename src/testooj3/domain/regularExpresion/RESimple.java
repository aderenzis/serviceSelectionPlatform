package testooj3.domain.regularExpresion;

import java.util.Vector;

public class RESimple extends RegularExpresion {

	protected String elemento;
	public RESimple(char element){
		this.elemento = ""+element;
	}
	
	@Override
	public Vector<String> getSecuencia(int l) {
		Vector<String> resultado = new Vector<String>();
		resultado.add(elemento);
		return resultado;
	}

	
	
}

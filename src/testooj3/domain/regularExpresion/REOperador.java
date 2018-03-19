package testooj3.domain.regularExpresion;

import java.util.Vector;

/**
 * @author  andres
 */
public class REOperador extends RegularExpresion{

	protected char operador;
	protected RegularExpresion operando;
	
	public REOperador(char op,Object operando){
		
		if(operando instanceof Character){
			this.operando = new RESimple((Character)operando); 
		}
		else{
			this.operando = (RegularExpresion)operando; 
		}
		this.operador = op;
		
	}
	
	@Override
	public Vector<String> getSecuencia(int l) {
		Vector<String> resultado = new Vector<String>();
		
		switch(operador){
		case '*':
			resultado = this.obtenerClausura(l);
			break;
		case '+':
			resultado = this.obtenerClausuraPositiva(l);
			break;
		case '?':
			resultado = this.obtenerinterrogación(l);
			break;
		}
		
		return resultado;
	}

	protected Vector<String> obtenerClausura(int l){
		
		Vector<String> resultado = this.obtenerClausuraPositiva(l);
		resultado.add("");
		return resultado;
	}
	
	protected Vector<String> obtenerClausuraPositiva(int l){
		Vector<String> sec = this.operando.getSecuencia(l);
		
		int n = sec.size();
		int exp = l;
		while(Math.pow(n, exp)>1000){
			exp--;
		}
		System.out.println("El exponenete es: "+exp);
		
		l=exp;
		
		Vector<String> resultado = new Vector<String>();
		for(int i=0;i<sec.size();i++){
			resultado.add(sec.get(i));
		}
		
		Vector<String> aux = new Vector<String>();
		aux.addAll(resultado);
		for(int i=1;i<l;i++){
			Vector<String> aux2 = new Vector<String>();
			for(int j=0;j<aux.size();j++){
				for(int k=0;k<sec.size();k++){
					aux2.add(aux.get(j)+sec.get(k));
				}
				if(Runtime.getRuntime().freeMemory()<5000){
					System.gc();
				}
			}
			resultado.addAll(aux2);
			aux = aux2;
		}
		 
		return resultado;
	}
	
	protected Vector<String> obtenerinterrogación(int l){
		Vector<String> sec = this.operando.getSecuencia(l);
		Vector<String> resultado = new Vector<String>();
		for(int i=0;i<sec.size();i++){
			resultado.add(sec.get(i));
		}
		resultado.addElement("");
		return resultado;
	}
}

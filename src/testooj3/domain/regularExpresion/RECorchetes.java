package testooj3.domain.regularExpresion;

import java.util.Vector;

import testooj3.domain.TOperation;

public class RECorchetes extends RegularExpresion{

	protected Vector<RECorchetes> añadidos = new Vector<RECorchetes>();
	protected Vector<RECorchetes> intersectados = new Vector<RECorchetes>();
	protected Vector<RESecuenciaCorchete> secuencias = new Vector<RESecuenciaCorchete>();
	protected boolean negado;
	
	public RECorchetes(Vector v){
		//Obtener añadidos e intersectados
		for(int i =0;i<v.size();i++){
			if(v.get(i) instanceof RECorchetes){
				if(i>0){
					if(v.get(i-1) instanceof Character){
						char c = (Character)v.get(i-1);
						if(c == '&'){
							this.intersectados.add((RECorchetes)v.get(i));
						}
						else{
							añadidos.add((RECorchetes)v.get(i));
						}
					}
					else{
						añadidos.add((RECorchetes)v.get(i));
					}
				}else{
					añadidos.add((RECorchetes)v.get(i));
				}
			}
		}
		
		//Buscar negado
		this.negado = false;
		if(v.get(0) instanceof Character){
			char c = (Character)v.get(0);
			if(c == '^' ){
				this.negado = true;
			}
		}
		
		//Buscar secuencias
		for(int i=0;i<v.size();i++){
			RESecuenciaCorchete sc = new RESecuenciaCorchete(v,i);
			if(sc.correcta()){
				this.secuencias.add(sc);
			}
		}
	}
	
	@Override
	public Vector<String> getSecuencia(int l) {
		Vector<String> resultado = new Vector<String>();
		for(int i=0;i<this.secuencias.size();i++){
			RESecuenciaCorchete cs = secuencias.get(i);
			resultado = this.componerSecuencias(resultado,cs.getSecuencia(l));
		}
		
		for(int i=0;i<this.añadidos.size();i++){
			RECorchetes cs = this.añadidos.get(i);
			resultado = this.componerSecuencias(resultado,cs.getSecuencia(l));
		}
		
		for(int i=0;i<this.intersectados.size();i++){
			RECorchetes cs = this.intersectados.get(i);
			resultado = this.hacerInterseccion(resultado,cs.getSecuencia(l));
		}
		if(this.negado){
			Vector<String> abc = new Vector<String>();
			char c = 'A';
			while(c<='Z'){
				abc.add(""+c);
				c++;
			}
			c = 'a';
			while(c<='z'){
				abc.add(""+c);
				c++;
			}
			resultado = this.hacerResta(abc, resultado);
		}
		
		if(resultado.size()==0){
			resultado.add("");
		}
		
		return resultado;
	}

	
	protected Vector<String> componerSecuencias(Vector<String>s1,Vector<String>s2){
		Vector<String> resultado = new Vector<String>();
		resultado.addAll(s1);
		for(int i=0;i<s2.size();i++){
			boolean encontrado = false;
			for(int j=0;j<s1.size() && !encontrado;j++){
				if(s1.get(j).equals(s2.get(i))){
					encontrado = true;
				}
			}
			if(!encontrado){
				resultado.add(s2.get(i));
			}
		}
		return resultado;
	}
	
	protected Vector<String> hacerInterseccion(Vector<String>s1,Vector<String>s2){
		Vector<String> resultado = new Vector<String>();
		for(int i=0;i<s2.size();i++){
			boolean encontrado = false;
			for(int j=0;j<s1.size() && !encontrado;j++){
				if(s1.get(j).equals(s2.get(i))){
					encontrado = true;
				}
			}
			if(encontrado){
				resultado.add(s2.get(i));
			}
		}
		return resultado;
	}
	
	protected Vector<String> hacerResta(Vector<String>s1,Vector<String>s2){
		Vector<String> resultado = new Vector<String>();
		for(int i=0;i<s1.size();i++){
			boolean encontrado = false;
			for(int j=0;j<s2.size() && !encontrado;j++){
				if(s2.get(j).equals(s1.get(i))){
					encontrado = true;
				}
			}
			if(!encontrado){
				resultado.add(s1.get(i));
			}
		}
		return resultado;
	}
}

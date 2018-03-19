package testooj3.domain.regularExpresion;

import java.util.List;
import java.util.Vector;

public abstract class RegularExpresion {

	/**
	 * Método para obtener una expresión regular ya construida
	 * @param expresion
	 * @return
	 */
	public static RegularExpresion construir(String expresion){
		
		//Creamos una lista
		Vector v = new Vector();
		Vector vsinp = new Vector();
		for(int i=0;i<expresion.length();i++){
			v.add(expresion.charAt(i));
		}
		
		System.out.println(v.toString());
		
		//Primera parte - Eliminar parentesis
		for(int i=0;i<v.size();i++){
			char c = (Character)v.get(i);
			if(c == '('){
				int nParentesis = 0;
				boolean fin = false;
				for(int j=i+1;j<v.size() && !fin;j++){
					char c2 =(Character)v.get(j);
					if(c2 =='('){
						nParentesis++;
					}
					else if(c2 == ')'){
						if(nParentesis ==0){
							String subexpresion = expresion.substring(i+1,j);
							RegularExpresion re = RegularExpresion.construirParentesis(subexpresion);
							vsinp.add(re);
							i = j;
							fin = true;
						}
						else{
							nParentesis--;
						}
					}
				}
			}
			else{
				vsinp.add(c);
			}
		}
		
		v = vsinp;
		vsinp = new Vector();
		
		System.out.println(v.toString());
		
		//segunda parte - Eliminar corchetes
		for(int i=0;i<v.size();i++){
			Object o = v.get(i);
			if(o instanceof Character){
				char c = (Character)o;
				if(c == '['){
					int corchetes = 0;
					boolean fin = false;
					for(int j=i+1;j<v.size() && !fin;j++){
						Object o2 = v.get(j);
						if(o2 instanceof Character){
							char c2 =(Character)o2;
							if(c2 =='['){
								corchetes++;
							}
							else if(c2 == ']'){
								if(corchetes ==0){
									List l = v.subList(i+1, j);
									System.out.println("Expresion para corchetes: "+l.toString());
									RegularExpresion re = RegularExpresion.construirCorchetes(l);
									vsinp.add(re);
									i = j;
									fin = true;
								}
								else{
									corchetes--;
								}
							}
						}
					}
				}
				else{
					vsinp.add(c);
				}
			}
			else{
				vsinp.add(o);
			}
		}
		
		v = vsinp;
		vsinp = new Vector();
		
		
		System.out.println(v.toString());
		
		//tercera parte - aplicar operadores
		for(int i=0;i<v.size();i++){
			Object o = v.get(i);
			if(o instanceof Character){
				char c = (Character)o;
				if(c == '?' ||c == '*' ||c == '+'){
					Object operando = v.get(i-1);
					vsinp.remove(vsinp.size()-1);
					RegularExpresion er = RegularExpresion.aplicarOperador(c,operando);
					vsinp.add(er);
					if(i+1 < v.size()){
						Object oextra = v.get(i+1);
						if(oextra instanceof Character){
							char extra = (Character)oextra;
							if(extra == '+' || extra == '?'){
								i++;
							}
						}
					}
				}
				else if(c == '{'){
					Object operando = v.get(i-1);
					vsinp.remove(vsinp.size()-1);
					boolean fin = false;
					for(int j=i+1;j<v.size() && !fin;j++){
						Object o2 = v.get(j);
						if(o2 instanceof Character){
							char c2 =(Character)o2;
							if(c2 == '}'){
								List l = v.subList(i+1, j);
								RegularExpresion re = RegularExpresion.aplicarOperador(l,operando);
								vsinp.add(re);
								i = j;
								fin = true;
								if(i+1 < v.size()){
									Object oextra = v.get(i+1);
									if(oextra instanceof Character){
										char extra = (Character)oextra;
										if(extra == '+' || extra == '?'){
											i++;
										}
									}
								}
							}
						}
					}
				}
				else{
					vsinp.add(c);
				}
			}
			else{
				vsinp.add(o);
			}
		}
		
		v = vsinp;
		vsinp = new Vector();
		Vector ors = new Vector();
		
		
		System.out.println(v.toString());
		
		
		//Cuarta parte - aplicar operadores And
		for(int i=0;i<v.size();i++){
			
			Object o = v.get(i);
			if(o instanceof Character){
				char c = (Character)o;
				if(c == '|'){
					RegularExpresion re = RegularExpresion.aplicarAnd(vsinp);
					ors.add(re);
					ors.add(c);
					vsinp = new Vector();
				}
				else if(i+1 >=v.size()){
					vsinp.add(c);
					RegularExpresion re = RegularExpresion.aplicarAnd(vsinp);
					ors.add(re);
					vsinp = new Vector();
				}
				else{
					vsinp.add(c);
				}
			}
			else if(i+1 >=v.size()){
				vsinp.add(o);
				RegularExpresion re = RegularExpresion.aplicarAnd(vsinp);
				ors.add(re);
				vsinp = new Vector();
			}
			else{
				vsinp.add(o);
			}
		}
		
		v = ors;
		vsinp = new Vector();
		
		System.out.println(v.toString());
		
		//Quinta parte - aplicar operadores or
		RegularExpresion r = RegularExpresion.aplicarOr(v);
		
		return r;
	}
	
	/**
	 * Método para obtener la expresión regular que hay dentro de los
	 * parentesis
	 * @param expresion
	 * @return
	 */
	protected static RegularExpresion  construirParentesis(String expresion){
		return RegularExpresion.construir(expresion);
	}
	
	/**
	 * Método para obtener la expresión regular que hay dentro de los
	 * corchetes
	 * @param expresion
	 * @return
	 */
	protected static RegularExpresion  construirCorchetes(List expresion){
		//Creamos una lista
		Vector v = new Vector();
		Vector vsinp = new Vector();
		for(int i=0;i<expresion.size();i++){
			v.add(expresion.get(i));
		}
		
		System.out.println(v.toString());
		
		for(int i=0;i<v.size();i++){
			Object o = v.get(i);
			System.out.println("objeto: "+o.toString()+" posicion: "+i);
			if(o instanceof Character){
				char c = (Character)o;
				if(c == '['){
					int corchetes = 0;
					boolean fin = false;
					for(int j=i+1;j<v.size() && !fin;j++){
						Object o2 = v.get(j);
						if(o2 instanceof Character){
							char c2 =(Character)o2;
							if(c2 =='['){
								corchetes++;
							}
							else if(c2 == ']'){
								if(corchetes ==0){
									List l = v.subList(i+1, j);
									System.out.println("Expresion para corchetes: "+l.toString());
									RegularExpresion re = RegularExpresion.construirCorchetes(l);
									vsinp.add(re);
									i = j;
									fin = true;
								}
								else{
									corchetes--;
								}
							}
						}
					}
				}
				else{
					vsinp.add(c);
				}
			}
			else{
				vsinp.add(o);
			}
		}
		
		v = vsinp;
		
		return new RECorchetes(v);
	}
	
	/**
	 * Método para obtener una expresión regular aplicando un operador de repetición
	 * @param operador
	 * @param operando
	 * @return
	 */
	protected static RegularExpresion aplicarOperador(List operador, Object operando){
		return new REOperadorR(operador,operando);
	}
	
	/**
	 * Método pra obtener una expresión regular aplicando un operador unitario
	 * @param operador
	 * @param operando
	 * @return
	 */
	protected static RegularExpresion aplicarOperador(char operador, Object operando){
		return new REOperador(operador,operando);
	}
	
	/**
	 * Método pra obtener una expresión regular aplicando un operador and
	 * @param v
	 * @return
	 */
	protected static RegularExpresion aplicarAnd(Vector v){
		return new REAnd(v);
	}
	
	/**
	 * Método pra obtener una expresión regular aplicando un operador or
	 * @param v
	 * @return
	 */
	protected static RegularExpresion aplicarOr(Vector v){
		return new REOr(v);
	}
	
	
	public String toString(){
		return this.getClass().getName().toString();
	}
	
	/**
	 * Método en el cual se devuelbe un Vector con las secuencias de 
	 * caracteres que se pueden obtener a partir de la expresión regular
	 */
	public abstract Vector<String> getSecuencia(int longitud);
}

package testooj3.domain.regularExpresion;

import java.util.List;
import java.util.Vector;

/**
 * @author  andres
 */
public class REOperadorR extends RegularExpresion{

	protected int longMax;
	protected int longMin;
	protected RegularExpresion operando;
	
	public REOperadorR(List op,Object operando){
		if(operando instanceof Character){
			this.operando = new RESimple((Character)operando); 
		}
		else{
			this.operando = (RegularExpresion)operando; 
		}
		
		//Contraer lista
		
		String num = "";
		int min = -1;
		int max = -1;
		boolean guionEncontrado = false;
		for(int i=0;i<op.size();i++){
			char c = (Character)op.get(i);
			if(c == ','){
				min = Integer.parseInt(num);
				num = "";
				guionEncontrado = true;
			}
			else{
				num += c;
			}
		}
		if(guionEncontrado){
			if(!num.equals("")){
				max = Integer.parseInt(num);
			}
		}else{
			min = Integer.parseInt(num);
			max = min;
		}
		
		longMin = min;
		longMax = max;
		System.out.println("El mumero maximo y minimo: "+min+" "+max);
		
	}
	
	@Override
	public Vector<String> getSecuencia(int l) {
		Vector<String> sec = this.operando.getSecuencia(l);
		Vector<String> resultado = new Vector<String>();
		for(int i=0;i<sec.size();i++){
			resultado.add(sec.get(i));
		}
		
		int n = sec.size();
		int exp = longMin;
		while(Math.pow(n, exp)>1000){
			exp--;
		}
		System.out.println("El exponenete es: "+exp);
		
		int minimo=exp;
		
		for(int i=1;i<minimo;i++){
			Vector<String> aux = new Vector<String>();
			for(int j=0;j<resultado.size();j++){
				for(int k = 0;k<sec.size();k++){
					String cadena = resultado.get(j)+sec.get(k);
					aux.add(cadena);
				}
			}
			resultado = aux;	
		}
		
		int maximo=0;
		if(this.longMax != -1){
			maximo = this.longMax;	
		}
		else{
			if(l>this.longMin){
				maximo = l;
			}
		}
		
		n = sec.size();
		exp = maximo;
		while(Math.pow(n, exp)>1000){
			exp--;
		}
		System.out.println("El exponenete es: "+exp);
		
		maximo=exp;
		
		Vector<String> aux = new Vector<String>();
		aux.addAll(resultado);
		for(int i=this.longMin;i<maximo;i++){
			Vector<String> aux2 = new Vector<String>();
			for(int j=0;j<aux.size();j++){
				for(int k=0;k<sec.size();k++){
					String cadena = aux.get(j)+sec.get(k);
					aux2.add(cadena);
				}
			}
			resultado.addAll(aux2);
			aux = aux2;
		}
		
		return resultado;
		
	}

	
	
}

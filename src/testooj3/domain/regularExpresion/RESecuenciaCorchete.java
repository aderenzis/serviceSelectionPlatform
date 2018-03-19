package testooj3.domain.regularExpresion;

import java.util.Vector;

import testooj3.domain.TOperation;

public class RESecuenciaCorchete extends RegularExpresion{

	protected Vector secuencia = new Vector();
	protected boolean correcta = false;
	
	public RESecuenciaCorchete(Vector v,int posicion){
		
		Object o = v.get(posicion);
		if(o instanceof Character){
			char c = (Character)o;
			if ((c>='A' && c<='Z') || (c>='a' && c<='z')) {
				char cI = c;
				char cD = c;
				if(posicion>0){
                	Object oI = v.get(posicion-1);
                	if(oI instanceof Character){
                		cI = (Character)oI;
                	}
				}
				if(posicion<v.size()-1){
                	Object oD = v.get(posicion+1);
                	if(oD instanceof Character){
                		cD = (Character)oD;
                	}
                }
				if(cI != '-' && cD != '-'){
					correcta = true;
					secuencia.add(c);
				}
            }
			else if(c == '-'){
				correcta = true;
				char cI = c;
				char cD = c;
				if(posicion>0){
                	Object oI = v.get(posicion-1);
                	if(oI instanceof Character){
                		cI = (Character)oI;
                	}
				}
				if(posicion<v.size()-1){
                	Object oD = v.get(posicion+1);
                	if(oD instanceof Character){
                		cD = (Character)oD;
                	}
                }
				if ((cI>='A' && cI<='Z') || (cI>='a' && cI<='z')) {
					secuencia.add(cI);
				}
				this.secuencia.add(c);
				if ((cD>='A' && cD<='Z') || (cD>='a' && cD<='z')) {
					secuencia.add(cD);
				}
				
			}
		}
		
	}
	
	
	public boolean correcta(){
		return correcta;
	}
	
	@Override
	public Vector<String> getSecuencia(int longitud) {
		Vector<String> resultado = new Vector<String>();
		
		switch(secuencia.size()){
		case 1:
			resultado = this.getLong1();
			break;
		case 2:
			resultado = this.getLong2();
			break;
		case 3:
			resultado = this.getLong3();
			break;
		}
		return resultado;
	}

	
	public Vector <String> getLong1(){
		Vector<String> resultado = new Vector<String>();
		char c = (Character)secuencia.get(0);
		if(c == '-'){
			char c1 = 'a';
			while(c1<='z'){
				resultado.add(""+c1);
				c1++;
			}
			c1 = 'A';
			while(c1<='Z'){
				resultado.add(""+c1);
				c1++;
			}
		}
		else{
			resultado.add(""+c);
		}
		return resultado;
	}
	public Vector <String> getLong2(){
		Vector<String> resultado = new Vector<String>();
		char c1 = (Character)secuencia.get(0);
		char c2 = (Character)secuencia.get(1);
		if(c1 == '-'){
			if (c2>='A' && c2<='Z'){
				char c = 'A';
				while(c<=c2){
					resultado.add(""+c);
					c++;
				}
			}else{
				char c = 'A';
				while(c<='Z'){
					resultado.add(""+c);
					c++;
				}
				c = 'a';
				while(c<=c2){
					resultado.add(""+c);
					c++;
				}
			}
		}
		else{
			if (c1>='A' && c1<='Z'){
				char c = c1;
				while(c<='Z'){
					resultado.add(""+c);
					c++;
				}
				c = 'a';
				while(c<='z'){
					resultado.add(""+c);
					c++;
				}
			}else{
				char c = c1;
				while(c<='z'){
					resultado.add(""+c);
					c++;
				}
			}
		}
		return resultado;
	}
	public Vector <String> getLong3(){
		Vector<String> resultado = new Vector<String>();
		char c1 = (Character)secuencia.get(0);
		char c2 = (Character)secuencia.get(2);
		if (c1>='A' && c1<='Z'){
			if(c2>='A' && c2<='Z'){
				char c = c1;
				while(c<=c2){
					resultado.add(""+c);
					c++;
				}
			}
			else{
				char c = c1;
				while(c<='Z'){
					resultado.add(""+c);
					c++;
				}
				c = 'a';
				while(c<=c2){
					resultado.add(""+c);
					c++;
				}
			}
		}else{
			if(c2>='a' && c2<='z'){
				char c = c1;
				while(c<=c2){
					resultado.add(""+c);
					c++;
				}
			}
			else{
				char c = c1;
				while(c<='z'){
					resultado.add(""+c);
					c++;
				}
				c = 'A';
				while(c<=c2){
					resultado.add(""+c);
					c++;
				}
			}
		}
		return resultado;
		
	}
}

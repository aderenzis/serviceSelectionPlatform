package testooj3.compatibility.wrappers;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Vector;

//import temp.test;
import reducedTest.ControlTypes;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;

/**
 * @author andres
 */
public class WMethod implements Serializable {
	protected Operation originalMethod; // @jve:decl-index=0:
	protected Operation candidateMethod; // @jve:decl-index=0:
	protected Vector paramMatches; // @jve:decl-index=0:

	public void setOriginalMethod(Operation originalMethod) {
		this.originalMethod = originalMethod;
	}

	public void setCandidateMethod(Operation candidateMethod) {
		this.candidateMethod = candidateMethod;
	}

	public void setParamMatches(Vector paramMatches) {
		this.paramMatches = paramMatches;
	}

	public String getJava(String tipoW) {
		boolean generaExcepcion = this.originalMethod.getLanzaExcepciones();
		System.out.println("DENTRO DE GETJAVA() EN WMETHOD");
		String r = "\tpublic " + this.originalMethod.getTipo() + " "
				+ this.originalMethod.getNombre() + " (";
		for (int i = 0; i < this.originalMethod.getParametros().size(); i++) {//Coloca los nombre en orden
			TParameter aPar = this.originalMethod.getParametro(i);
			r += aPar.getTipo() + " arg" + (i + 1) + ", ";//se puede cambiar a nombres de alan...
		}
		if (r.endsWith(", "))
			r = r.substring(0, r.length() - 2);
		// / Controla si genera excepci�n agrego throws Exception
		if (!generaExcepcion) {
			r += ") {\n";
		} else {
			r += ") throws Exception {\n";
		}

		r += "\t\t";
		//Defino variable de retorno if retorno es <> null
		String parmCons = " ";
		
		if (!originalMethod.getTipo().equals("void")){
			
			if (originalMethod.getTipo().contentEquals("boolean")) {
				
				parmCons=ControlTypes.printDefaultValue(getClaseTipo(originalMethod.getTipo()));
				r+=originalMethod.getTipo()+" ret0 = "+parmCons+" ;\n";
			}
			else {
				
					r+=originalMethod.getTipo()+" ret0;\n";
			
			}

			r += "\t\t";
		}
		if (generaExcepcion) {
			r += "try";
			r += "{\n";
			r += "\t\t";
		}

		if (tipoW.equals("S")) {
			r += "try";
			r += "{\n";

		}
		// ---------------------------------------------------------<<
		Vector mapPmatch = new Vector();
		Vector mapBmatch = new Vector();
		//creo mapPmatch para llevar controlado que parm tuvo que ser casteado
		
		for (int i = 0; i < this.originalMethod.getParametros().size(); i++) {

			// obtengo el tipo del parametro del m�todo original
			String tipoParmOriginal = this.originalMethod.getParametro(i)
					.getTipo();
			// busco con el indice i, el pos de parammatches.
			int pos = devuelvePosParmR(i);//yo ya tengo la relacion [pR,PS]
//			int[] paramMatch = (int[]) this.paramMatches.get(i);
//			int pos = paramMatch[];
			
			// con esa posici�n busco el tipo del parm nro pos, en el candidato.
			String tipoParmCandidato = this.candidateMethod.getParametro(pos)
					.getTipo();
			// comparo los tipos. si el original es mayor al candidato (no lo
			// incluye)
			boolean b=false;
			if (!tipoParmCandidato.equals(tipoParmOriginal)){
				b = testooj3.compatibility.InterfacesCompatibilityChecker
					.isSubTyping(tipoParmCandidato, tipoParmOriginal);
				mapPmatch.add(i,true);
			}
			else{
				mapPmatch.add(i,false);
			}
			mapBmatch.add(i,b);
			System.out.println(mapPmatch);
			if (b) {
				// entonces debo realizar una conversi�n con codigo.
//				String nombreParmIntermedio = "vim" + String.valueOf(i);
//
//				r += "\t\t\t" + getClaseTipo(tipoParmOriginal) + " "
//						+ nombreParmIntermedio + " = arg"
//						+ String.valueOf(i + 1) + ";\n";
				String nombreParmIntermedio = "arg" + String.valueOf(i + 1);
				r += "\t\t\t"
						+ tipoParmCandidato
						+ " parg"
						+ String.valueOf(i + 1)
						+ " = "
						+ recOpConversion(tipoParmOriginal, tipoParmCandidato,
								nombreParmIntermedio,0) + ";\n\n"; //ACA FALLAAAAAAAAAAA
			}
		}
		// -----------------------------------------------------------<<
		// -------------------------------------------------------------------.
		String nombreVariable = "";
		if (!this.originalMethod.getTipo().equals("void"))
			r += "\t\t\tret0=";
			//r += "\t\t\treturn ";
			
		if (tipoW.equals("C")) {
			r += "\tcandidate." + this.candidateMethod.getNombre() + "(";
		} else {
			String tipo = this.originalMethod.getTipo();
			if (!tipo.equals("void")) {
				nombreVariable = "getProxy()."
						+ this.candidateMethod.getNombre() + "(";
			}
			else {
				r += "\tgetProxy()." + this.candidateMethod.getNombre() + "(";
			}
		}

		if (paramMatches != null) {
			
//			for (int i = 0; i < paramMatches.size(); i++) {
//				try {
//					System.out.println("this.paramMatches.get(i): "
//							+ paramMatches.get(i));
//					int pos = ((Integer) paramMatches.get(i));
//					
//					if ((Boolean)mapPmatch.get(i))
//						nombreVariable += "parg" + (pos + 1) + ", ";
//					else
//						nombreVariable += "arg" + (pos + 1) + ", ";
//					
//				} catch (Exception e) {
//					System.out.println("error en casteo");
//				}
//			}
//			int selectParamR=0;
			for (int i = 0; i < candidateMethod.getParametros().size(); i++) {
				try {
					int posR = this.devuelvePosParmS(i);  
					if(posR != -1) {
						
						if ((Boolean)mapPmatch.get(posR))
							nombreVariable += "parg" + (posR + 1) + ", ";
						else
							nombreVariable += "arg" + (posR + 1) + ", ";
//						selectParamR++;
					} else {
						nombreVariable += ControlTypes.printDefaultValue(candidateMethod.getParametro(i).getTipo()) + ", ";
					}
					
				} catch (Exception e) {
					System.out.println("error en casteo");
				}
			}
			if (nombreVariable.endsWith(", "))
				nombreVariable = nombreVariable.substring(0, nombreVariable
						.length() - 2);
			nombreVariable += ")";
		}
		
		//ACA LO CIERRO PERO TODAVIA NO HABIA QUE CERRAR EL PARENTESIS, TIENE QUE PONERSE EN EL STRING.VALUEOF

		//ME PARECE Q SI ES TIP OORIGINAL ES VOID NO TIENE Q LLAMAR ACA
		r += recOpConversion(this.originalMethod.getTipo(), this.candidateMethod.getTipo(),
				nombreVariable,1);
		r+=";\n";
		if (tipoW.equals("S")) {
			r += "\t\t}\n";
			r += "\t\tcatch (Exception ex){\n";
			r += "\t\t\tex.printStackTrace();\n";
			r += "\t\t\tthrow new RuntimeException(ex); \n";
			r += "\t\t}\n";
		}
		if (generaExcepcion) {
			// Agregado por Israel - Control de excepciones - parte 2
			r += "\t\t";
			r += "}\n";
			r += "\t\t";
			r += "catch (Exception e){\n";
			r += "\t\t\t e.getCause(); \n";
			r += "\t\t";
			r += "}\n";
			// ------------------------------------------------------
		}
		if (!originalMethod.getTipo().equals("void"))
			r += "\t\treturn ret0;\n";
		
		r += "\t}\n";
		//System.out.println("retorno de getJAVA: "+r);
		return r;
	}
	
	
	/**
	 * devuelve la correspondencia recomendada para el parametro i
	 * si devuelve -1 no encontro
	 * @param i
	 * @return
	 */
	public int devuelvePosParmR(int i) {
		/*int pos = 0;
		if (this.paramMatches != null) {

			pos = ((Integer) paramMatches.get(i));

		}*/
		
		Enumeration enume=paramMatches.elements();
		int paramS=-1;
        while(enume.hasMoreElements()){
            //System.out.println("pos: "+i+" "+enume.nextElement());
        	int[] next = (int[])enume.nextElement();
        	if (next[0]==i){
        		//FALTA ACA SETEAR NEXT[1] valor del param S
        		paramS=next[1];
        		break;
        	}
        }
		return paramS;
		
//		int pos = 0;
//		Enumeration enume=paramMatches.elements();
//		int in=0;
//        while(enume.hasMoreElements()){
//            //System.out.println("pos: "+i+" "+enume.nextElement());
//        	int[] next = (int[])enume.nextElement();
//        	if ((Integer)enume.nextElement()==i){
//        		pos=in;
//        		break;
//        	}
//        	in+=1;
//        }
//		return pos;

	}

	public int devuelvePosParmS(int i) {
		Enumeration enume=paramMatches.elements();
		int paramS=-1;
        while(enume.hasMoreElements()){
            //System.out.println("pos: "+i+" "+enume.nextElement());
        	int[] next = (int[])enume.nextElement();
        	if (next[1]==i) {
        		paramS=next[0];
        		break;
        	}
        }
		return paramS;
	}
	
	public static String getClaseTipo(String tipo) {
		String clase = "";
		if (tipo.toLowerCase().equals("long"))
			clase = "Long";
		else if (tipo.equals("int") || tipo.equals("Integer"))
			clase = "Integer";
		else if (tipo.toLowerCase().equals("java.lang.double") || tipo.toLowerCase().equals("double"))
			clase = "Double";
		else if (tipo.toLowerCase().equals("float"))
			clase = "Float";
		else if (tipo.toLowerCase().equals("java.lang.string"))
			clase = "String";
		else if (tipo.toLowerCase().equals("byte"))
			clase = "Byte";
		else if (tipo.toLowerCase().equals("short"))
			clase = "Short";	
		else if (tipo.toLowerCase().equals("boolean"))
			clase = "Boolean";		
		else if (tipo.toLowerCase().equals("char"))
			clase= "char";

		return clase;
	}

	public static String recOpConversion(String tipoOrigen, String tipoDestino,
			String nombreParmIntermedio, int tipo) {
		
		String funcion = "";
		if (getClaseTipo(tipoOrigen).toLowerCase().equals(getClaseTipo(tipoDestino).toLowerCase())) {
			funcion=nombreParmIntermedio;
		} else {

			//Conversi�n de parametros
			if (tipo==0)  { 
				
				if (tipoOrigen.toLowerCase().equals("java.lang.string")) {
					
					if (tipoDestino.toLowerCase().equals("int")) {
						funcion = getClaseTipo(tipoDestino)+".parseInt("+ nombreParmIntermedio + ")";
					}
					else
						funcion = getClaseTipo(tipoDestino)+".parse"+getClaseTipo(tipoDestino)+"("+ nombreParmIntermedio + ")";			
					

				} else {
					if (tipoDestino.toLowerCase().equals("java.lang.string")) {
						funcion = "String.valueOf(" + nombreParmIntermedio + ")";
					} else {
						
						funcion = "new "+getClaseTipo(tipoOrigen)+"("+nombreParmIntermedio+")" + "."+tipoDestino.toLowerCase()+"Value()";

						
						/*
						//ACA DEBERIA HABER UNA CONVERSION A FLOAT PARA EL CASO QUE FALLA

						if (getClaseTipo(tipoOrigen).toLowerCase().equals("double")) {
							if (tipoDestino.toLowerCase().equals("long")) {
								funcion = "("+nombreParmIntermedio+")" + ".longValue()";
							}
							else if (getClaseTipo(tipoDestino).equals("Integer")) {
									funcion = "("+nombreParmIntermedio+").intValue()";
							}
							//AGREGADO POR MI (TINCHO W.)
							else if (tipoDestino.toLowerCase().equals("float")) {
								funcion = "("+nombreParmIntermedio+")" + ".floatValue()";
							}
							
							
						}*/
						/*
						else if (getClaseTipo(tipoOrigen).toLowerCase().equals("float")) {
							 NO HARIA FALTA
							if (tipoDestino.toLowerCase().equals("double")) {
								funcion = "(double) "+nombreParmIntermedio;
							}
							if (tipoDestino.toLowerCase().equals("long")) {
								funcion = "("+nombreParmIntermedio+")" + ".longValue()";
							}						
						}*/
						/*
						else if (tipoOrigen.toLowerCase().equals("long")) {
							/* NO HARIA FALTA
								if (getClaseTipo(tipoDestino).toLowerCase()
									.equals("double")) {
								funcion = nombreParmIntermedio + ".doubleValue()";
								} 
								
								if (getClaseTipo(tipoDestino).toLowerCase()
										.equals("float")) {
									funcion = nombreParmIntermedio + ".floatValue()";
									}	
							if (getClaseTipo(tipoDestino).equals("Integer")) {
								funcion = "("+nombreParmIntermedio+").intValue()";
							}							
								
								
						}/* NO HARIA FALTA
							else{
								if (getClaseTipo(tipoOrigen).toLowerCase().equals("integer"))
									if (getClaseTipo(tipoDestino).toLowerCase().equals("double"))
									{
										funcion=nombreParmIntermedio;
									}	
									else if (getClaseTipo(tipoDestino).toLowerCase().equals("long"))
									{
										funcion="new Long("+nombreParmIntermedio+")";
									}		

								}*/
						}

				}
				
				
			}
			
			//Conversi�n de retorno
			else {	

				if (tipoOrigen.toLowerCase().equals("java.lang.string")) {
					funcion = "String.valueOf(" + nombreParmIntermedio + ")";
				}
				else if (tipoDestino.toLowerCase().equals("java.lang.string")) {
					if (tipoOrigen.toLowerCase().equals("char")) {
						//HAY QUE AVERIGUAR QUE HAGO EN ESTE CASO, DEVUELVO EL PRIMERO CHAR??
						funcion = nombreParmIntermedio+".charAt(0)";
					}
					else {
						if (tipoOrigen.toLowerCase().equals("int")) {
							funcion = getClaseTipo(tipoOrigen)+".parseInt("+ nombreParmIntermedio + ")";
						}
						else
							funcion = getClaseTipo(tipoOrigen)+".parse"+getClaseTipo(tipoOrigen)+"("+ nombreParmIntermedio + ")";
					}
				}
				
				else if (testooj3.compatibility.InterfacesCompatibilityChecker
				.isSubTyping(tipoOrigen, tipoDestino)) {
					funcion = "(new "+getClaseTipo(tipoDestino)+"("+nombreParmIntermedio+"))."+tipoOrigen+"Value()";
				}
				
				else funcion = nombreParmIntermedio;
				
				
			}
			
		}

		
		return funcion;
	}

	public static String getTipoNull(String clasetipo){
		String parmCons="";
		if (clasetipo.equals("String"))
			parmCons="\"\"";
		else
			if (clasetipo.equals("Double"))
				parmCons="0.0";
			else
				if (clasetipo.equals("Float"))
					parmCons="0";
				else
					if (clasetipo.equals("Integer"))
						parmCons="0";
					else
						if (clasetipo.equals("Long"))
							parmCons="0";
		return parmCons;
	}
}

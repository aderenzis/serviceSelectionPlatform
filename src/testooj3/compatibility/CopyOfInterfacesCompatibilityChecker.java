package testooj3.compatibility;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import testooj3.domain.Interface;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestoojClassLoader;
import testooj3.gui.compatibility.JFCompatibility;
import testooj3.gui.compatibility.NonEditableDefaultTableModel;

/**
 * @author  andres
 */
public class CopyOfInterfacesCompatibilityChecker implements Runnable {
	protected final int NO_COMPATIBLE= 0;
	protected String summary="";
	protected String summaryList="";
	protected Interface a;
	protected Interface b;
	private JFCompatibility parentWindow;
	protected Hashtable<String, Vector> compatibilities;

	public CopyOfInterfacesCompatibilityChecker(JFCompatibility parentWindow, String originalClassName, String candidateClassName, 
			boolean considerInheritedOperations, String classPath1, String classPath2) throws Exception {
		this.parentWindow=parentWindow;
		a=TestoojClassLoader.load(originalClassName, classPath1, considerInheritedOperations);
		b=TestoojClassLoader.load(candidateClassName, classPath2, considerInheritedOperations);
		this.parentWindow.setNumberOfIterations(a.getMethods().size());
	}

	public void run() {
		calculateCompatibilities();
		this.parentWindow.log(this.getSummary());
	}

	private void calculateCompatibilities() {
		Vector<Operation> methods=a.getMethods();
		this.compatibilities=new Hashtable<String, Vector>();
		for (int i=0; i<methods.size(); i++) {
			this.parentWindow.setCurrentIteration((i+1));
			Operation method=methods.get(i);
			compatibilities.put(method.getWholeSignature(), new Vector());
			loadCompatibleMethods(method, b);
		}
		buildSummary();
	}

	private void buildSummary() {
		Enumeration<Vector> vectores=this.compatibilities.elements();
		int max=0;
		while (vectores.hasMoreElements()) {
			Vector v=vectores.nextElement();
			if (v.size()>max)
				max=v.size();
		}

		Enumeration<String> keys=this.compatibilities.keys();
		StringBuffer sb=new StringBuffer("<table border=1 FONTSIZE='3'>");
		sb.append("<tr><th><FONT SIZE='3'>");
		sb.append(a.getName());
		sb.append("</FONT></th>");
		sb.append("<th colspan=");
		sb.append(compatibilities.size());
		sb.append("><FONT SIZE='3'>"); 
		sb.append(b.getName());
		sb.append("</FONT></th></tr>");
		while (keys.hasMoreElements()) {
			//String signature=keys.nextElement().toString();
			String aMethodName= keys.nextElement();
			//Vector compatibles=(Vector) compatibilities.get(signature);
			Vector compatibles=(Vector) compatibilities.get(aMethodName);
			if (compatibles.size()>0) {
				sb.append("<tr><td bgcolor=\"#F0F8FF\"><FONT SIZE='3'>");
				//sb.append(signature);
				sb.append(aMethodName);
				sb.append("</FONT></td>"); //Hay compatibles -> celeste
			} else {
				sb.append("<tr><td bgcolor=\"#FF6347\"><FONT SIZE='3'>");
//				sb.append(signature);
				sb.append(aMethodName);
				sb.append("</FONT></td>");//No Hay compatibles -> rojo
			}
			for (int i=0; i<compatibles.size(); i++) {
				//if (compatibles.get(i).toString().equals(signature)){
				Vector compToPrint = (Vector) compatibles.get(i);
				Operation bMethod = (Operation) compToPrint.get(2);
				String bMethToPrint = "[";
				for (int j=0; j<compToPrint.size();j++){
					if (j==2)
						bMethToPrint = bMethToPrint + " " +  bMethod.getWholeSignature(); 
					else
						bMethToPrint = bMethToPrint + " " +  compToPrint.get(j).toString();
				}
				bMethToPrint = bMethToPrint + " ]";  
				if (bMethod.getWholeSignature().equals(aMethodName)){
					sb.append("<td bgcolor=\"#F0F8FF\"><FONT SIZE='3'>");
					//sb.append(compatibles.get(i).toString());
					sb.append(bMethToPrint);
					sb.append("</FONT></td>");  //Hay iguales -> celeste
				}
				else{
					sb.append("<td><FONT SIZE='3'>");
//					sb.append(compatibles.get(i).toString());
					sb.append(bMethToPrint);
					sb.append("</FONT></td>"); //El resto -> blanco
				}
			}
			sb.append("<td></td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		summary=sb.toString();
	}

	private void loadCompatibleMethods(Operation c1Method, Interface c2) {
		Vector<Vector> compatibles = (Vector<Vector>) this.compatibilities.get(c1Method.getWholeSignature());
		for (int i=0; i<c2.getMethods().size(); i++) {
			int paramCase = 0; // no compatible
			int nameCase = 0;
			int returnCase = 0; // no compatible
			int excepCase = 0; // no compatible
			Operation c2Method=c2.getMethod(i);
			
			returnCase=getReturnCase(c1Method, c2Method);
			paramCase=getParamCase(c1Method, c2Method);
			nameCase=getNameCase(c1Method, c2Method);
			excepCase=getExcepCase(c1Method, c2Method);
			
			// Identificar el Nivel de compatibilidad
			Vector level = getLevelCompatibilidad(returnCase, nameCase, paramCase, excepCase);
			// Establece el nivel de compatibilidad de Interfaces (syntactica) alcanzado
			Integer nroLevel = (Integer) level.get(0);
			if (nroLevel.intValue() != NO_COMPATIBLE) // si es NO_COMPATIBLE
				compatibles = addCompatibleMethod(compatibles, c2Method, level);
		}
		compatibles = getSortedVector(compatibles,26);
		//compatibilities.put(c1Method.getWholeSignature(), compatibles);
		compatibilities.put(c1Method.getWholeSignature(), compatibles);
	}

	private int getExcepCase(Operation method1, Operation method2) {
		boolean lanzaTodas=true;
		for (int i=0; i<method1.getLaunchedExceptions().size(); i++) {
			String e1 = method1.getLaunchedExceptions().get(i).toString();
			lanzaTodas=lanzaTodas && lanza(method2, e1);
		}
		if (lanzaTodas)
			return 1;
		
		return 0;
	}
	
	private boolean lanza(Operation m, String e) {
		return (m.getLaunchedExceptions().contains(e));
	}

	private int getNameCase(Operation method1, Operation method2) {
		if (method1.getNombre().equals(method2.getNombre()))
			return 1;
		if (isSubstringEqName(method1.getNombre(), method2.getNombre()))
			return 2;
		return 0;
	}

	private int getParamCase(Operation method1, Operation method2) {
		if (method1.getParametros().size()!=method2.getParametros().size()) 
			return 0;
		boolean todos=true;
		for (int i=0; i<method1.getParametros().size(); i++) {
			TParameter pa=method1.getParametro(i);
			TParameter pb=method2.getParametro(i);
			todos=todos && (pa.getTipo().equals(pb.getTipo()));
		}
		if (todos)
			return 1;
		if (areCompatibleParameters(method1, method2)) 
			return 2; // Pc2: P1 /\ P2 /\ ¬ P3
		if (areSubTypeParameters(method1, method2))
			return 3; // Pc3: P1 /\ P2.1
		return 0;
	}

	private int getReturnCase(Operation method1, Operation method2) {
		if (method1.getTipo().equals(method2.getTipo()))
			return 1; // condicion R1
		if (isSubType(method1.getTipo(), method2.getTipo()))
			return 2;  // condicion R2
		return 0;
	}

	private Vector getLevelCompatibilidad(int returnCase, int nameCase, int paramCase, int excepCase) {
		// Idenfica el caso de compatibilidad
		Vector level = new Vector();
		if (paramCase != 0 && returnCase != 0 && excepCase != 0) {
			String rCase = "R" + returnCase;
			String nCase = "N" + nameCase;
			String pCase = "P" + paramCase;
			String eCase = "E" + excepCase;
			level.add(rCase);
			level.add(nCase);
			level.add(pCase);
			level.add(eCase);
			if (returnCase == 1 && nameCase == 1 && paramCase == 1 && excepCase ==1){
				level.add(0,1); // "exact"
				level.add(1,"exact");
				return level;
			}
			if (returnCase == 1 && nameCase == 1 && paramCase == 1 && excepCase == 2){
				level.add(0,2); // "n_exact_1"
				level.add(1,"n_exact_1");
				return level;
			}
			if (returnCase == 1 && nameCase == 1 && paramCase == 2 && excepCase == 1){
				level.add(0,3); // "n_exact_2"
				level.add(1,"n_exact_2");
				return level;
			}
			if (returnCase == 1 && nameCase == 2 && paramCase == 1 && excepCase == 1){
				level.add(0,4); // "n_exact_3"
				level.add(1,"n_exact_3");
				return level;
			}
			if (returnCase == 1 && nameCase == 2 && paramCase == 2 && excepCase == 1){
				level.add(0,5); // "n_exact_4"
				level.add(1,"n_exact_4");
				return level;
			}
			if (returnCase == 1 && nameCase == 2 && paramCase == 2 && excepCase == 2){
				level.add(0,6); // "n_exact_5"
				level.add(1,"n_exact_5");
				return level;
			}
			if (returnCase == 1 && nameCase == 1 && paramCase == 2 && excepCase == 3){
				level.add(0,7); // "soft_1"
				level.add(1,"soft_1");
				return level;
			}
			if (returnCase == 1 && nameCase == 2 && paramCase == 2 && excepCase == 3){
				level.add(0,8); // "soft_2"
				level.add(1,"soft_2");
				return level;
			}
			if (returnCase == 1 && nameCase == 0 && paramCase == 1 && excepCase == 1){
				level.add(0,9); // "soft_3"
				level.add(1,"soft_3");
				return level;
			}
			if (returnCase == 1 && nameCase == 0 && paramCase == 2 && excepCase == 1){
				level.add(0,10); // "soft_4"
				level.add(1,"soft_4");
				return level;
			}
			if (returnCase == 1 && nameCase == 0 && paramCase == 2 && excepCase == 3){
				level.add(0,11); // "soft_5"
				level.add(1,"soft_5");
				return level;
			}
			if (returnCase == 1 && nameCase == 1 && paramCase == 3 && excepCase == 1){
				level.add(0,12); // "soft_6"
				level.add(1,"soft_6");
				return level;
			}
			if (returnCase == 2 && nameCase == 1 && paramCase == 3 && excepCase == 1){
				level.add(0,13); // "soft_7"
				level.add(1,"soft_7");
				return level;
			}
			if (returnCase == 1 && nameCase == 1 && paramCase == 3 && excepCase == 2){
				level.add(0,14); // "soft_8"
				level.add(1,"soft_8");
				return level;
			}
			if (returnCase == 2 && nameCase == 1 && paramCase == 3 && excepCase == 2){
				level.add(0,15); // "soft_9"
				level.add(1,"soft_9");
				return level;
			}
			if (returnCase == 1 && nameCase == 2 && paramCase == 3 && excepCase == 1){
				level.add(0,16); // "soft_10"
				level.add(1,"soft_10");
				return level;
			}
			if (returnCase == 2 && nameCase == 2 && paramCase == 3 && excepCase == 1){
				level.add(0,17); // "soft_11"
				level.add(1,"soft_11");
				return level;
			}
			if (returnCase == 1 && nameCase == 2 && paramCase == 3 && excepCase == 2){
				level.add(0,18); // "soft_12"
				level.add(1,"soft_12");
				return level;
			}
			if (returnCase == 2 && nameCase == 2 && paramCase == 3 && excepCase == 2){
				level.add(0,19); // "soft_13"
				level.add(1,"soft_13");
				return level;
			}
			if (returnCase == 1 && nameCase == 1 && paramCase == 3 && excepCase == 3){
				level.add(0,20); // "n_soft_1"
				level.add(1,"n_soft_1");
				return level;
			}
			if (returnCase == 2 && nameCase == 1 && paramCase == 3 && excepCase == 3){
				level.add(0,21); // "n_soft_2"
				level.add(1,"n_soft_2");
				return level;
			}
			if (returnCase == 1 && nameCase == 2 && paramCase == 3 && excepCase == 3){
				level.add(0,22); // "n_soft_3"
				level.add(1,"n_soft_3");
				return level;
			}
			if (returnCase == 2 && nameCase == 2 && paramCase == 3 && excepCase == 3){
				level.add(0,23); // "n_soft_4"
				level.add(1,"n_soft_4");
				return level;
			}
			if (returnCase == 2 && nameCase == 0 && paramCase == 3 && excepCase == 3){
				level.add(0,24); // "n_soft_5"
				level.add(1,"n_soft_5");
				return level;
			}
			level.add(0,25); // Tener en cuenta el Sort para poner mas numeros
			level.add(1,"misterioso");
		}
		else {
			level.add(new Integer(NO_COMPATIBLE));
		}
		return level;
	}

	private boolean areCompatibleParameters(Operation c1Method, Operation c2Method) {	
		boolean allUsed=true;
		int max1 = c1Method.getParametros().size();
		int max2 = c2Method.getParametros().size();
		String class1=c1Method.getClassName();
		String class2=c2Method.getClassName();
		if (max1 != 0) {
			boolean[] used=new boolean[max1];
			for (int j=0; j<max1; j++)
				used[j]=false;
			for (int j=0; j<max1; j++) {
				TParameter m1Par=c1Method.getParametro(j);
				for (int k=0; k<max2; k++) {
					TParameter m2Par=c2Method.getParametro(k);
					if ((m1Par.getTipo().equals(m2Par.getTipo()) ||
							m1Par.getTipo().equals(class1) && m2Par.getTipo().equals(class2)) && !used[k]) 
						used[k]=true;
				}
			}
			for (int k=0; k<used.length; k++)
				allUsed=allUsed && used[k];
		}
		return allUsed;
	}

	private boolean isCompatibleExceptions(Operation c1Method, Operation c2Method) {	
		boolean allUsed=true;
		int max1 = c1Method.getLaunchedExceptions().size();
		int max2 = c2Method.getLaunchedExceptions().size();
		if (max1 == max2) {
			if (max1 != 0) {
				boolean[] used=new boolean[max1];
				for (int j=0; j<max1; j++)
					used[j]=false;
				for (int j=0; j<max1; j++) {
					String m1Excep=c1Method.getLaunchedExceptions().get(j).toString();
					for (int k=0; k<max2; k++) {
						String m2Excep=c2Method.getLaunchedExceptions().get(k).toString();
						if (m1Excep.equals(m2Excep) && !used[k]) 
							used[k]=true;
					}
				}
				for (int k=0; k<used.length; k++)
					allUsed=allUsed && used[k];
			}
			return allUsed;
		}
		else
			return false;
	}


	private boolean isExistsException(Operation c1Method, Operation c2Method) {	
		if (c1Method.getLaunchedExceptions().size()!=0){ 
			if (c2Method.getLaunchedExceptions().size()== 0) 
				return false;
		}
		return true;
	}

	private boolean isSubstringEqName(String c1MethName, String c2MethName) {
		Vector<String> name1Vector = getSubstringsVector(c1MethName);
		Vector<String> name2Vector = getSubstringsVector(c2MethName);
		boolean found = false;
		int i = 0;
		while (!found && i<name2Vector.size()) {
			if (name1Vector.contains(name2Vector.get(i)))  
				found = true;
			i += 1;
		}
		return found;
	}	

	private Vector<String> getSubstringsVector(String palabra) {
		Vector<String> palabras = new Vector<String>();
		String subPalabra="";
		for(int i=0; i< palabra.length();i++){
			subPalabra=subPalabra + Character.toLowerCase(palabra.charAt(i));
			if ((i+1)<palabra.length() && Character.isUpperCase(palabra.charAt(i+1))){
				palabras.add(subPalabra);
				subPalabra="";
			}
		}
		palabras.add(subPalabra);
		return palabras;
	}


	private boolean areSubTypeParameters(Operation c1Method, Operation c2Method) {	
		boolean allUsed=true;
		int max1 = c1Method.getParametros().size();
		int max2 = c2Method.getParametros().size();
		String class1=c1Method.getClassName();
		String class2=c2Method.getClassName();
		if (max1 != 0) {
			boolean[] used=new boolean[max1];
			for (int j=0; j<max1; j++)
				used[j]=false;
			for (int j=0; j<max1; j++) {
				TParameter m1Par=c1Method.getParametro(j);
				for (int k=0; k<max2; k++) {
					TParameter m2Par=c2Method.getParametro(k);
					if ((isSubType(m1Par.getTipo(),m2Par.getTipo()) ||
							m1Par.getTipo().equals(class1) && m2Par.getTipo().equals(class2))
							&& !used[k]) 
						used[k]=true;
				}
			}
			for (int k=0; k<used.length; k++)
				allUsed=allUsed && used[k];
		}
		return allUsed;
	}	


	private boolean isSubType(String type1, String type2) {
		boolean res = false;
		if (type1 == "char")
			if (type2 == "String")
				res = true;
		if (type1 == "byte")
			if (type2 == "short" || type2 == "int" || type2 == "long" || type2 == "double" || type2 == "Double")
				res = true;		    	 
		if (type1 == "short") 
			if (type2 == "int" || type2 == "long" || type2 == "double" || type2 == "Double")
				res = true;
		if (type1 == "int") 
			if (type2 == "long" || type2 == "double" || type2 == "Double")
				res = true;		    	 
		if (type1 == "long") 
			if (type2 == "double" || type2 == "Double")
				res = true;		    	 
		return res;  
	}


	private Vector<Vector> addCompatibleMethod(Vector<Vector> compatibles, Operation c2Method, Vector level) {
		// no envio el HashTable, sino solo el vector.. 
		//level.add(2,c2Method.getWholeSignature()); // nivel de compatibility
		level.add(2,c2Method);

		if (compatibles==null) {
			compatibles = new Vector<Vector>();
		}
		compatibles.add(level);
		return compatibles;
	}


	/**-----------------------------------------------------------------
	 * Ordenamiento en O(n).  Se usa cuando las claves de búsqueda están dentro de
	 * un rango determinado (finito y conocido).
	 * Algoritmo: enumeración por Distribución
	 **/

	public Vector<Vector> getSortedVector(Vector<Vector> a, int rango) {
		int max = a.size(); 
		Vector[] salida = new Vector[max]; 
		Integer clave = new Integer(0);
		int[] count = new int[rango];

		for (int i = 0; i < max; i++) {
			//cuenta las veces que se repite una clave en count.
			clave =(Integer)a.get(i).get(0); 
			count[clave.intValue()]++;
		}

		for (int i = 1; i < rango; i++) {
			//calcula la posicion más a la derecha de una clave.+1
			count[i] = count[i - 1] + count[i];
		}

		for (int i = max - 1; i >= 0; i--) {
			//ubica al elemento en la posicion que corresponde.
			clave =(Integer)a.get(i).get(0);	      
			int j = count[clave.intValue()]-1;
			salida[j] = a.get(i);
			count[clave.intValue()]--;
		}

		Vector<Vector> resultado = new Vector<Vector>(max); 
		for (int i = 0; i < max; i++) {
			resultado.add(salida[i]);
		}

		return resultado;
	}

	public String getSummary() {
		return summary;
	}
	
	public void buildWrappers() {
		int nivel=1;
		ArbolMetodos a=new ArbolMetodos();
		Enumeration<Vector> e=this.compatibilities.elements();
		Enumeration<String> k=this.compatibilities.keys();
		while (e.hasMoreElements()) {
			Vector v=(Vector) e.nextElement();
			String method1Signature=k.nextElement();
			Operation c1Method = this.a.findMethodByWholeSignature(method1Signature);
			if (v!=null && v.size()>0) {
				Vector oper0=(Vector) v.get(0);
				Integer compat0=(Integer) oper0.get(0); 
				Operation c2Method=(Operation) oper0.get(2);
				a.add(c2Method, nivel, c1Method);
				String compatP=oper0.get(5).toString();
				/*if (compatP.equals("P1")) {
					System.out.println("Replicando " + c2Method.toString());
					@SuppressWarnings("unused")
					ArbolP ap=replicar(c2Method);
					Vector<ArbolP> hojas=new Vector<ArbolP>();
					ap.cargaHojas(hojas);
					for (ArbolP hoja : hojas) {
						
					}
					System.out.println(ap.toString());
				}*/
				for (int i=1; i<v.size(); i++) {
					Vector operI=(Vector) v.get(i);
					Integer compatI=(Integer) operI.get(0); 
					Operation c2MethodI=(Operation) operI.get(2);
					if (compat0.intValue()==compatI.intValue()) {
						a.add(c2MethodI, nivel, c1Method);
					} else
						break;
				}
				nivel=nivel+1;
			}
		}
		this.generarCodigo(a);
	}
	
	private ArbolP replicar(Operation m) {
		ArbolP a=new ArbolP();
		int nivel=1;		
		for (int i=0; i<m.getParametros().size(); i++) {
			TParameter p = m.getParametro(i);
			a.addHijos(m, i, nivel);
			for (int j=0; j<m.getParametros().size(); j++) {
				if (j!=i) {
					TParameter auxi=m.getParametro(j);
					if (p.getTipo().equals(auxi.getTipo()))
						a.addHijos(m, j, nivel);
				}					
			}
			nivel++;
		}
		return a;
	}

	private void generarCodigo(ArbolMetodos a) {
		Vector<ArbolMetodos> hojas=new Vector<ArbolMetodos>();
		a.cargaHojas(hojas);
		for (ArbolMetodos hoja : hojas) {
			ArbolMetodos r=expandir(hoja);
			Vector<ArbolMetodos> hojasR=new Vector<ArbolMetodos>();
			r.cargaHojas(hojasR);
			for (ArbolMetodos hojaR : hojasR) {
				generarCodigoHoja(hojaR);
			}
		}
	}

	private ArbolMetodos expandir(ArbolMetodos hoja) {
		ArbolMetodos r=new ArbolMetodos();
		int nivel=1;
		while (hoja.mPadre!=null) {
			ArbolP ap=replicar(hoja.mValor);
			ap.calcularCombinaciones();
			for (int i=0; i<ap.combinaciones.size(); i++) {
				Vector<Integer> comb=ap.combinaciones.get(i);
				ArbolMetodos auxi=new ArbolMetodos(hoja.mValor, hoja.originalMethod);
				auxi.ordenParametros=comb;
				r.addHijos(auxi, nivel);
			}
			nivel++;			
			hoja=hoja.mPadre;
		}
		return r;
	}

	private void generarCodigoHoja(ArbolMetodos hoja) {
		String r="public class Wrapper {\n";
		while (hoja.mPadre!=null) {
			r+="\t" + hoja.originalMethod.getWholeSignature() + " {\n";
			r+="\t\to." + hoja.getCall() + "\n";
			r+="\t}\n\n";
			hoja=hoja.mPadre;
		}
		r+="}\n";
		System.out.println(r);
	}

	public Interface getA() {
		return a;
	}

	public Interface getB() {
		return b;
	}

	public Hashtable<String, Vector> getCompatibilities() {
		return this.compatibilities;
	}
}


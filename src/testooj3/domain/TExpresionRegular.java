package testooj3.domain;

import java.util.Vector;
import java.util.regex.Pattern;
import testooj3.domain.regularExpresion.RegularExpresion;





/**
 * Clase que se encarga de modelar una expresión regular. Esta clase contendrá un array de TestTemplates
 * @author  Pedro Reales Mateo
 */
public class TExpresionRegular {

	public TExpresionRegular(Interface c, String ex){
		this.cut = c;
		this.expresion = ex;
		this.mTS = new Vector();
	}
	
	/**
	 * Clase a la que está asociada la expresión regular
	 * @uml.property  name="cut"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="expresionRegular:dominio.Cut"
	 */
	protected Interface cut = null;
	
	/**
	 * Cadena que contiene la expresión
	 * @uml.property  name="expresion"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="expresionRegular:dominio.exsionesRegulares.Expresion"
	 */
	protected String expresion = null;
	
	 /**
     * Plantillas de prueba
     */
    protected Vector mTS;
    
    /**
     * Encontrar una plantilla de prueba
     * @param nombre
     * @return
     */
    public TestTemplate findTestTemplate(String nombre) 
    {
        for (int i=0; i<mTS.size(); i++) 
        {
            TestTemplate ts=(TestTemplate) mTS.elementAt(i);
            if (ts.getName().equals(nombre))
                return ts;
        }
        return null;
    }
    
    /**
     * Borrar una plantilla de prueba
     * @param nombre
     */
    public void removeTestTemplate(String nombre) 
    {
        for (int i=0; i<mTS.size(); i++) 
        {
            TestTemplate ts=(TestTemplate) mTS.elementAt(i);
            if (ts.getName().equals(nombre)) {
                mTS.remove(i);
                return;
            }
        }		
    }
    
    /**
     * Obtener todas las plantillas de prueba en un vector
     * @return
     */
    public Vector getTestTemplates() 
    {
        return mTS;
    }
    
    /**
     * Establecer todas la plantillas de prueba con un vector
     * @param tt
     */
    public void setTestTemplates(Vector tt) {
        mTS=tt;
    }
    
    /**
     * Obtener una plantilla de prueba indicando la posición
     * que ocupa en el vector
     * @param i
     * @return
     */
    public TestTemplate getTestTemplate(int i) 
    {
        return (TestTemplate) mTS.elementAt(i);
    }
    
    
    /**
     * Construir las plantillas de prueba
     * @param maxLength
     * @param monitor 
     * @param er
     * @return
     * @throws Exception
     */
    public Vector generarTestTemplates(int maxLength) throws Exception
    {
    	String er = this.expresion;
    	
    	for (int i=0; i<this.cut.getConstructors().size(); i++) 
        {
            TConstructor c=(TConstructor) cut.getConstructors().elementAt(i);
            c.setUsar(false);
        }
        for (int i=0; i<this.cut.getMethods().size(); i++) 
        {
            Operation m=(Operation) cut.getMethods().elementAt(i);
            m.setUsar(false);
        }    
        Vector operacionesSeleccionadas=new Vector();
        for (int i=0; i<er.length(); i++) 
        {
            if (((er.charAt(i)>='A' && er.charAt(i)<='Z') || (er.charAt(i)>='a' && er.charAt(i)<='z')) &&
                    !operacionesSeleccionadas.contains(""+er.charAt(i))) {
                operacionesSeleccionadas.add(""+er.charAt(i));
                TOperation op=this.cut.getOperation(er.charAt(i));
                op.setUsar(true);
            }
        }    
        
        TSet r=new TSet();
        int posAnt=0;
        int[] cont={1};
        for (int i=1; i<maxLength+1; i++) {
        	posAnt=getTestTemplates(i, posAnt, cont, r);
        }	
        
        depura(r, er);
        
        mTS=r;
        return r;
    }

    /**
     * Método auxiliar para obtener las plantillas de prueba
     * Este elimina las plantillas generadas que no machean con la
     * expresion regular
     * @param tts
     * @param er
     * @param monitor 
     */
    protected  void depura(TSet tts, String er) 
    {
        if (er==null || er.length()==0) return;
        
        for (int i=0; i<tts.size(); i++) 
        {
            TestTemplate ts=(TestTemplate) tts.elementAt(i);
            String expr=ts.getRE();
            if (!Pattern.matches(er, expr)) {
                tts.remove(i);
                i=i-1;
            }
        }
        for (int i=0; i<tts.size(); i++) 
        {
            TestTemplate ts=(TestTemplate) tts.elementAt(i);
            ts.setName("testTS_" + (i+1));
        }    
    }
    
    /**
     * Método auxiliar para obtener las plantillas de prueba
     * Genera todas las conbinaciones posibles con una longitud
     * especifica y teniendo en cuenta los métodos que 
     * intervienen en la expresion regular
     * @param longitud
     * @param posAnt
     * @param cont
     * @param tts
     * @return
     * @throws Exception
     */
    protected int getTestTemplates(int longitud, int posAnt, int[] cont, TSet tts) throws Exception {
        if (longitud==1) {
            for (int i=0; i<cut.getConstructors().size(); i++) {
                TConstructor c=(TConstructor) cut.getConstructors().get(i);
                if (c.getUsar()) {
                    TestTemplate ts=new TestTemplate();
                    ts.setName("testTS_" + cont[0]++);
                    ts.setConstructor(c);
                    tts.add(ts);
                }
            }
        } else {
            int card=tts.cardinal();
            int i=0;
            for (i=posAnt; i<card; i++) {
                TestTemplate auxi=(TestTemplate) tts.get(i);
                TestTemplate ts=(TestTemplate) auxi.clone();
                for (int j=0; j<this.cut.getMethods().size(); j++) {
                    Operation metodo=(Operation) cut.getMethods().elementAt(j);
                    if (metodo.getUsar() && metodo.mVisibilidad.equals("public")) {
                        ts.setMethod(longitud-1, metodo);
                        ts.setName("testTS_" + cont[0]++);
                        tts.add(ts);
                        ts=new TestTemplate((TestTemplate) ts.clone());
                    }
                }
            }
            posAnt=i;
        }
        return posAnt;
    }		
    
    
    /**
     * Añadir una plantilla de prueba
     * @param t
     */
    public void add(TestTemplate t) {
        this.mTS.add(t);
    }
    
    
	
	/**
	 * Método para obtener la clase bajo prueba
	 * @return
	 */
	public Interface getCut() {
		return cut;
	}
	/**
	 * Método para modificar la clase bajo prueba
	 * @param cut
	 */
	public void setCut(Interface cut) {
		this.cut = cut;
	}
	
	/**
	 * Método para obtener la expresión
	 * @return
	 */
	public String getExpresion() {
		return expresion;
	}
	/**
	 * Método para modificar la expresión
	 * @param expresion
	 */
	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}
	
	
	/**
     * Obtener la expresión regular con los nombres de los metodos
     * @param er
     * @return
     */
    public String getExpandedRegularExpression() {
        String er = this.getExpresion();
    	String result="";
        for (int i=0; i<er.length(); i++) {
            char caracter=er.charAt(i);
            if ((caracter>='A' && caracter<='Z') || (caracter>='a' && caracter<='z')) {
                result+=this.cut.getOperation(caracter).getId();
            } else result+="<b>"+caracter+"</b>";
        }
        return result;        
    }
	
    /**
     * Metodo para obtener la lista de 
     * métodos que intervienen en la expresión regular
     */
    public Vector getMetodos(){
    	Vector metodos = new Vector();
    	Vector operacionesSeleccionadas=new Vector();
        for (int i=0; i<expresion.length(); i++) 
        {
            if (((expresion.charAt(i)>='A' && expresion.charAt(i)<='Z') || (expresion.charAt(i)>='a' && expresion.charAt(i)<='z')) &&
                    !operacionesSeleccionadas.contains(expresion.charAt(i))) {
                operacionesSeleccionadas.add(expresion.charAt(i));
                TOperation op=this.cut.getOperation(expresion.charAt(i));
                if(op!=null)metodos.add(op);
            }
        }
        return metodos;
    }
	
    
    
    
    /**
     * Nuevo algoritmo de generación de plantillas
     */
    public Vector generarTestTemplatesEficiente(int maxLength){
    	RegularExpresion e = RegularExpresion.construir(this.expresion);
    	Vector<String> gen = e.getSecuencia(maxLength);
    	
    	//Eliminar las mayores y menores
    	for(int i=0;i<gen.size();i++){
    		String c = gen.get(i);
    		if(c.length()>maxLength || c.length()<1){
    			gen.remove(i);
    			i--;
    		}
    	}
    	
    	//Eliminar las que no empiezan por constructor o tienen constructores en medio
    	Vector <String> constructores = new Vector<String>();
    	for(int i=0;i<this.cut.getConstructors().size();i++){
    		constructores.add(""+((TConstructor)this.cut.getConstructors().get(i)).mPos);
    	}
    	
    	for(int i=0;i<gen.size();i++){
    		boolean encontrado = false;
    		boolean borrarlo = false;
    		for(int j=0;j<constructores.size()&&!encontrado&&!borrarlo;j++){
    			if(gen.get(i).lastIndexOf(constructores.get(j))==0){
    				encontrado = true;
    			}else if(gen.get(i).lastIndexOf(constructores.get(j))!=-1){
    				borrarlo = true;
    			}
    		}
    		if(!encontrado || borrarlo){
    			gen.remove(i);
    			i--;
    		}
    	}
    	
    	//Construimos las plantillas partir de las cadenas
    	//Vector tts = new Vector();
    	TSet tts=new TSet();
    	for(int i=0;i<gen.size();i++){
    		char [] cadena = gen.get(i).toCharArray();
    		TestTemplate ts = new TestTemplate();
    		ts.setConstructor((TConstructor)this.cut.getOperation(cadena[0]));
    		for(int j=1;j<cadena.length;j++){
    			ts.addMethod((Operation)this.cut.getOperation(cadena[j]));
    		}
    		ts.setName("testTS_"+i);
    		tts.add(ts);
    	}
    	
    	this.mTS = tts;
    	return tts;
    	
    }
    
}

package testooj3.domain;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;
import testooj3.domain.testmethodlines.ParameterDeclarationLine;
import testooj3.domain.testmethodlines.xml.XMLParameterDeclarationLine;
import testooj3.persistence.Agente;

public abstract class TOperation  implements Serializable
{
    protected String mNombre;
    protected Vector mPars;
    protected char mPos;
    protected Vector mPost;
    protected String mPostassertions;
    protected Vector mPre;
    protected String mPreassertions;
    protected String mVisibilidad;
    protected boolean mUsar;
    protected boolean mLanzaExcepciones;
    protected Vector mLaunchedExceptions;
    protected String mClassName;
    
    public TOperation() 
    {
        mPars=new Vector();
        mPost=new Vector();
        mPre=new Vector();
        mPreassertions="";
        mPostassertions="";
        mUsar=false;
        mLanzaExcepciones=false;
        mLaunchedExceptions=new Vector();
        mClassName=null;
    }
       
    protected void calculaPos(int pos)  
    {
        if (pos<=25)
            mPos=(char) (65+pos);
        else if (pos<=50)
            mPos=(char) (97+pos-26);  // Minúsculas, que empiezan en 97
        else mPos=(char) (97+pos);
    }
    
    protected Class [] getTypes( Vector <String> types){
    	Class [] result=new Class[types.size()];
    	for (int i=0;i<result.length;i++){
    		result[i]=this.getType(types.get(i).toString());
    	}
    	return result;
    }
    
    protected Class getType(String type){
    	Class result=null;
		try {
			result=Class.forName(type);
		}
		catch(Exception e){
			if ("int".equals(type))
				result=Integer.class;
			else if ("char".equals(type))
				result=Character.class;
			else if ("float".equals(type))
				result=Float.class;
			else if ("double".equals(type))
				result=Double.class;
			else if ("long".equals(type))
				result=Long.class;
			else if ("byte".equals(type))
				result=Byte.class;
			else if ("boolean".equals(type))
				result=Boolean.class;
			else {
				try {
					result=TestoojClassLoader.load(type, Configuration.getInstance().getClassPath(), false).getJavaClass();
				}
				catch (Exception ex) {
					return void.class;
				}
			}
		}
		return result;
    }
    
    public void setLanzaExcepciones(boolean v) 
    {
        this.mLanzaExcepciones=v;
    }
    
    public boolean getLanzaExcepciones() 
    {
        return this.mLaunchedExceptions.size()>0;
    }
    
    public abstract String getLlamada(String objeto, String valores, int pos);
    
    public boolean getUsar() 
    {
        return mUsar;
    }
    
    public void setUsar(boolean usar) 
    {
        mUsar=usar;
    }
    
    public void resetTestValues() 
    {
        for (int i=0; i<mPars.size(); i++) 
        {
            TParameter p=(TParameter) this.mPars.elementAt(i);
            p.resetTestValues();
        }
    }
    
    public TParameter getParametro(int i) 
    {
        return (TParameter) mPars.elementAt(i);
    }
    
    public Vector<TParameter> getParametros() 
    {
        return mPars;
    }	
    
    public String getNombre() 
    {
        return mNombre;
    }
    
    public String getNombre1Upper() 
    {
    	String n1raMayuscula=mNombre.substring(0,1).toUpperCase()+mNombre.substring(1,mNombre.length());
        return n1raMayuscula;
    }
    
    public char getPos()
    {
        return mPos;
    }
    
    public void setPos(int pos)
    {
        calculaPos(pos);
    }
    
    public Vector getPre() 
    {
        return this.mPre;
    }
    
    public void setPreassertions(String pre) 
    {
        this.mPreassertions=pre;
    }
    
    public void setPostassertions(String post) 
    {
        this.mPostassertions=post;
    }
    
    public void removePreassertions() 
    {
        this.mPreassertions="";
    }
    
    public void removePostassertions() 
    {
        this.mPostassertions="";
    }  
    
    public String getPreassertions() 
    {
        return this.mPreassertions;
    }
    
    public String getPostassertions() 
    {
        return this.mPostassertions;
    }
    
    public void addPre(String pre) 
    {
        mPre.addElement(pre);
    }  
    
    public void removePre(int x) 
    {
        mPre.remove(x);
    }
    
    public void removePre() 
    {
        mPre.removeAllElements();
    }
    
    public Vector getPost() 
    {
        return this.mPost;
    }
    
    public void addPost(String post)
    {
        mPost.addElement(post);
    }	
    
    public void removePost(int x) 
    {
        mPost.remove(x);
    }
    
    public void removePost() 
    {
        mPost.removeAllElements();
    }
    
    public String getAssertsPre() 
    {
        String r="";
        for (int i=0; i<mPre.size(); i++) 
        {
            String pre=(String) mPre.elementAt(i).toString();
            r+=pre;
        }
        return r;
    }
    
    public String getAssertsPost() 
    {
        String r="";
        for (int i=0; i<mPost.size(); i++) 
        {
            String post=(String) mPost.elementAt(i).toString();
            r+=post;
        }
        return r;
    }	
    
    //comprueba si el metodo tiene algun parametro que es un objeto
    public boolean tieneParametrosComplejos() throws IOException, FileNotFoundException {
        for (int i=0; i<this.mPars.size(); i++){
            TParameter p=(TParameter) mPars.elementAt (i);
            if (!Agente.getAgente().esPrimitivo(p.getTipo()))
                return true;
        }
        return false;
    }		
    
    // devuelve el codigo necesario en el fichero de test en el caso de que un parametro sea un objeto
    public String crearObjeto(Vector valores) throws IOException, ClassNotFoundException {
        String cadena=new String ();
        TParameter parametro;
        String aux;
        String coger=new String();
        String parametros=new String ();
        String path=tratarRuta(Configuration.getInstance().getPrimitivesFile());
        for(int i=0;i<valores.size();i++) {
            parametro=(TParameter) mPars.elementAt(i);
            aux=((TParameter) mPars.elementAt (i)).getTipo ();
            //array como parametro
            if (aux.startsWith ("[")){
                Object[] array=(Object[]) valores.elementAt (i);
                aux=aux.substring (2,aux.length ());
                //array de objetos primitivos
                if (Agente.getAgente().esPrimitivo(parametro.getTipo())) {
                    aux=aux.concat ("[]");
                    coger+="\t\t"+aux+" x"+(i+1)+"={";
                    for (int j=0;j<array.length;j++){
                        coger=coger.concat(array[j].toString ()+", ");
                    }
                    parametros+="x"+(i+1)+", ";
                } else{
                    for (int j=0;j<array.length ;j++){
                        coger+="\n\t\t\tFileInputStream f" + (j+1) + "=new FileInputStream (\""+path+"\\\\ce\\\\"+cogerNombreFichero(array[j])+"\");\n"+
                        "\t\t\tObjectInputStream o"+(j+1)+"=new ObjectInputStream(f" + (j+1) + ");\n"+
                        "\t\t\t"+aux+" a"+(j+1)+"=(" + aux + ") o" + (j+1) + ".readObject();\n" +
                        "\t\t\tf"+(j+1)+".close();\n";
                    }
                    aux=aux.concat ("[]");
                    coger+="\t\t"+aux+" x"+(i+1)+"={";
                    for (int j=0;j<array.length;j++){
                        coger+="a"+(j+1)+", ";
                    }
                    parametros+="x"+(i+1)+", ";
                }
                coger=coger.substring (0,coger.length ()-2);
                coger+="};\n";
                //objeto como parametro
            } else{
                //objeto primitivo
                if (Agente.getAgente().esPrimitivo(parametro.getTipo())){
                    if (aux.startsWith ("java.lang.") && !aux.endsWith ("String"))
                        parametros+="new "+parametro.getClass ().getName ()+"("+parametro.toString () + "), ";
                    else{
                        if (aux.endsWith ("String"))
                            parametros+=parametro.toString () + ", ";
                        else
                            parametros+="("+aux+")"+parametro.toString () + ", ";
                    }
                } else {
                    //objeto serializado
                    coger+="\n\t\t\tFileInputStream f"+(i+1)+"=new FileInputStream(\""+path+"\\\\ce\\\\"+cogerNombreFichero(parametro)+"\");\n"+
                    "\t\t\tObjectInputStream o" + (i+1) + "=new ObjectInputStream (f"+(i+1)+");\n"+
                    "\t\t\t"+parametro.getClass ().getName ()+" x"+(i+1)+"=(" + parametro.getClass ().getName () + ") o" + (i+1) + ".readObject();\n" +
                    "\t\t\tf"+(i+1)+".close();\n";
                    parametros+="x"+(i+1)+", ";
                }
            }
        }
        if (parametros.length ()>2)
            parametros=parametros.substring (0,parametros.length ()-2);
        cadena+=coger+"\t\t\to." + mNombre+ "("+parametros+");\n";			
        return cadena;
    }	
    
    //busca el nombre del fichero a partir del cual se ha recuperado el objeto
    private String cogerNombreFichero(Object objeto) throws IOException, ClassNotFoundException {
        String []lista=Agente.getAgente().cogerNombreFicheros(objeto.getClass().getName());
        Object []objetos=Agente.getAgente().cogerFicheros(objeto.getClass().getName ());
        int i=0;
        
        while (i<objetos.length){
            if (comparar(objeto,objetos[i]))
                return lista[i];
            else i++;
        }
        return new String();
    }		
    
    //compara dos objetos de una clase cualquiera, comparando el valor de sus atributos
    private boolean comparar(Object o1, Object o2){
        if (!o1.getClass ().getName ().equals (o2.getClass ().getName ()))
            return false;
        
        Field[] f1=o1.getClass ().getFields ();
        Field[] f2=o2.getClass ().getFields ();
        boolean iguales=true;
        
        if (f1.length != f2.length)
            return false;
        
        for (int i=0;iguales && i<f1.length;i ++){
            if (!f1[i].equals (f2[i]))iguales=false;
        }
        return iguales;
    }	
    
    private String tratarRuta(String ruta){
        char c;
        boolean insertado=false;
        for (int i=0;i<ruta.length ();i++){
            c=ruta.charAt (i);
            if (c=='\\'&&!insertado){
                ruta=ruta.substring (0,i+1)+"\\"+ruta.substring (i+1,ruta.length ());
                insertado=true;
            }else insertado=false;
        }
        return ruta;
    }			
    
    public Vector getDeclaraciones(Vector valores, String nombreVar) throws IOException
    {
        int[] contador={-1};
        return getDeclaraciones(valores, contador, false, null);
    }
    
    public Vector getDeclaraciones(Vector valores, int[] contador, boolean paraJUnit, Vector xml) throws IOException
    {
        Vector result=new Vector();
        int cont=contador[0];
        if (valores!=null && this.getParametros().size()>0) {
            for (int i=0; i<valores.size(); i++) {
                TestValue valor=(TestValue) valores.get(i);
                TParameter par=(TParameter) this.getParametros().get(i);
                ParameterDeclarationLine pdl=new ParameterDeclarationLine(par, cont++, valor);
                if (xml!=null) {
                    XMLParameterDeclarationLine line=new XMLParameterDeclarationLine(par, valor);
                    xml.add(line);
                }
                result.add(pdl);
            }
        }
        return result;
    }  
    
    public String getExceptionLaunched(Vector valores)
    {
        if (valores!=null && this.getParametros().size()>0) {
            for (int i=0; i<valores.size(); i++) {
                TestValue valor=(TestValue) valores.get(i);
                if (valor.getExceptionLaunched()!=null)
                    return valor.getExceptionLaunched();
            }
        }
        return null;
    }  
    
    public void saveDescription(String constraintsPath) throws Exception
    {
        String fileName=constraintsPath + this.getId() + ".constraints";
        FileOutputStream f=new FileOutputStream(fileName);
        Properties pp=new Properties(); 
        for (int j=0; j<this.getPre().size(); j++) 
        {
            pp.setProperty("pre_" + j, this.getPre().elementAt(j).toString().trim());
        }
        for (int j=0; j<this.getPost().size(); j++) 
        {
            pp.setProperty("post_" + j, this.getPost().elementAt(j).toString().trim());
        }        
        pp.setProperty("preassertion_0", this.getPreassertions().trim());
        pp.setProperty("postassertion_0", this.getPostassertions().trim());
        Vector pars=this.getParametros();
        for (int j=0; j<pars.size(); j++) 
        {
            TParameter par=(TParameter) pars.elementAt(j);
            TestValue[] values=par.getTestValues();
            for (int k=0; k<values.length; k++) 
            {
                // Se guarda así: TestValue_0_2. Es el valor de prueba nº 2 para el parámetro nº 0
                pp.setProperty("TestValue_" + j + "_" + k, ""+values[k].getValue());
                if (values[k].getExceptionLaunched()!=null)
                    pp.setProperty("Exception_" + j + "_" + k, values[k].getExceptionLaunched());
            }
            pp.setProperty("UseValuesAsDescribed_" + j, ""+par.useValuesAsDescribed());
            pp.setProperty("NumberOfRandomTestValues_" + j, ""+par.getNumberOfRandomTestValues());            
        }
        pp.store(f, "Description of " + this.getId());
        f.close();        
    }    
    
    /**
     * 
     */
    public void removeDescriptions() {
        for (int i=0; i<this.mPars.size(); i++) {
            TParameter p=(TParameter) mPars.get(i);
            p.resetTestValues();
        }
        this.removePreassertions();
        this.removePostassertions();
    }
    
    public void loadDescription(String constraintsClassPath) throws Exception
    {
        Properties pp = new Properties();  
        String fileName=constraintsClassPath + this.getId() + ".constraints";
        FileInputStream f=new FileInputStream(fileName);
        pp.load(f); 
        Enumeration ppn=pp.propertyNames();
        while (ppn.hasMoreElements()) 
        {
            String pn=ppn.nextElement().toString();
            if (pn.startsWith("pre_")) 
            {
                this.addPre(pp.getProperty(pn));
            } else if (pn.startsWith("post_")) 
            {
                this.addPost(pp.getProperty(pn));
            } else if (pn.startsWith("preassertion_")) 
            {
                this.setPreassertions(pp.getProperty(pn));
            } else if (pn.startsWith("postassertion_")) 
            {
                this.setPostassertions(pp.getProperty(pn));
            } else if (pn.startsWith("TestValue_")) 
            {
                int parametro=Integer.parseInt(pn.substring(10, pn.lastIndexOf("_")));
                int valor=Integer.parseInt(pn.substring(pn.lastIndexOf("_")+1));
                TParameter p=(TParameter) this.getParametros().get(parametro);
                TestValue tv=new PrimitiveValue(pp.getProperty(pn));
                p.addTestValue(tv);
                if (pp.containsKey("Exception_" + parametro + "_" + valor))
                    tv.setExceptionLaunched(pp.getProperty("Exception_" + parametro + "_" + valor));                 
            } else if (pn.startsWith("NumberOfRandomTestValues_")) 
            {
                int parametro=Integer.parseInt(pn.substring(25));
                String valor=pp.getProperty(pn);
                TParameter p=(TParameter) this.getParametros().get(parametro);
                p.setNumberOfRandomTestValues(Integer.parseInt(valor));
            } else if (pn.startsWith("UseValuesAsDescribed_")) {
            	int parametro=Integer.parseInt(pn.substring(21));
            	TParameter p=(TParameter) this.getParametros().get(parametro);
            	String valor=pp.getProperty(pn);
            	boolean useValuesAsDescribed=Boolean.parseBoolean(valor);
            	p.useValuesAsDescribed(useValuesAsDescribed);
            }
        }
        f.close();    
    }    
    
    public String getId() 
    {
        String r=mNombre + "(";
        for (int i=0; i<mPars.size(); i++) 
        {
            TParameter p=(TParameter) mPars.elementAt(i);
            r+=p.getTipo() + ",";
        }
        if (r.endsWith(","))
            r=r.substring(0, r.length()-1);
        r+=")";
        return r;
    }
    
    /**
     *  
     * @uml.property name="parameters"
     * @uml.associationEnd inverse="tOperation:testooj3.domain.TParameter" multiplicity="(0 -1)" ordering="ordered"
     * 
     */
    private Vector parameters;
    
    /**
     * 
     * @uml.property name="parameters"
     */
    public void setParameters(java.util.Vector value) {
        parameters = value;
    }
    
    /**
     * 
     * @uml.property name="parameters"
     */
    public Iterator parametersIterator() {
        return parameters.iterator();
    }
    
    /**
     * 
     * @uml.property name="parameters"
     */
    public boolean addParameters(testooj3.domain.TParameter element) {
        return parameters.add(element);
    }
    
    /**
     * 
     * @uml.property name="parameters"
     */
    public boolean removeParameters(testooj3.domain.TParameter element) {
        return parameters.remove(element);
    }
    
    /**
     * 
     * @uml.property name="parameters"
     */
    public boolean isParametersEmpty() {
        return parameters.isEmpty();
    }
    
    /**
     * 
     * @uml.property name="parameters"
     */
    public void clearParameters() {
        parameters.clear();
    }
    
    /**
     * 
     * @uml.property name="parameters"
     */
    public boolean containsParameters(testooj3.domain.TParameter element) {
        return parameters.contains(element);
    }
    
    /**
     * 
     * @uml.property name="parameters"
     */
    public boolean containsAllParameters(Collection elements) {
        return parameters.containsAll(elements);
    }
    
    /**
     * 
     * @uml.property name="parameters"
     */
    public int parametersSize() {
        return parameters.size();
    }
    
    /**
     * 
     * @uml.property name="parameters"
     */
    public testooj3.domain.TParameter[] parametersToArray() {
        return (testooj3.domain.TParameter[]) parameters
        .toArray(new testooj3.domain.TParameter[parameters.size()]);
    }

    /**
     * @return
     */
    public Vector getLaunchedExceptions() {
        return this.mLaunchedExceptions;
    }
    
    public String getClassName() {
    	return this.mClassName;
    }
    
    public void setClassName(String className) {
    	this.mClassName=className;
    }

    public abstract TOperation copy();
    
    public boolean equals(Object o) {
    	if (!(o instanceof TOperation))
    		return false;
    	TOperation auxi=(TOperation) o;
    	//System.out.println(auxi.getNombre()+","+this.getNombre()+"="+auxi.getNombre().equals(this.getNombre()));
    	if (!auxi.getNombre().equals(this.getNombre())) return false;
    	if (auxi.getParametros().size()!=this.getParametros().size())
    		return false;
    	for (int i=0; i<auxi.getParametros().size(); i++) {
    		TParameter auxiP=auxi.getParametro(i);
    		TParameter thisP=this.getParametro(i);
    		if (!auxiP.equals(thisP))
    			return false;
    	}
    	return true;
    }
    
    public String toString(){
    	return this.getNombre();
    }
    
    public String getSignature(){
    	return this.getNombre();
    }
}
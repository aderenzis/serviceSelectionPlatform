package testooj3.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import testooj3.domain.algorithms.Algorithm;
import testooj3.domain.grouped.GroupedTestMethod;
import testooj3.domain.grouped.GroupedTestMethodForLotOfCases;
import testooj3.domain.states.State;
import testooj3.domain.tcmutation.TGroupedTestMethod;
import testooj3.persistence.Agente;

/**
 * @author  andres
 */
public class Interface
{
    public Class mClase;  //  @jve:decl-index=0:
    protected String mInvariants="";  //  @jve:decl-index=0:
    protected boolean mAlsoInherited;
    protected Vector mStates;
    protected Vector mObserverMethods;  //  @jve:decl-index=0:
    protected boolean mImplements;
    protected String mClassImpl;
    //protected Vector mTS;
    
    /**
     * Vector que contiene las expresiones regulares que 
     * se han creado para la clase
     *
     */
    protected Vector mER = new Vector();  //  @jve:decl-index=0:
    
    public Interface()  
    {
        mAlsoInherited=false;
    }
    
    public void setClass(String name) throws ClassNotFoundException 
    {
        mClase=Class.forName(name);
        fields=new Vector();
        constructors=new Vector();
        methods=new Vector();
        mStates=new Vector();
        mObserverMethods=new Vector();
        mImplements = false;
        mClassImpl = "";
        carga();    
    }
    
    public Interface(String name, boolean alsoInherited) throws ClassNotFoundException, Exception 
    {
        this(Class.forName(name), alsoInherited);
    }
    
    public Interface(Class c, boolean alsoInherited)  
    {
        mAlsoInherited=alsoInherited;
        mClase=c;
        fields=new Vector();
        constructors=new Vector();
        methods=new Vector();
        mStates=new Vector();
        mObserverMethods=new Vector();
        mImplements = false;
        mClassImpl = "";	
        carga();
    }
    
    protected void carga()   
    {
    	String iFace = "";
    	if (mClase.getInterfaces().length!=0){
    		iFace = mClase.getInterfaces()[0].getName();
    		}
		//System.out.println("la clase "+mClase.getName()+ " Implementa IF: "+iFace);
		if (!iFace.equals("")){
			 	mImplements = true;
		        mClassImpl = iFace;
		}
		
        cargaCampos();
        int letraActual=cargaConstructores();
        cargaMetodos(letraActual);
    }
    
    protected void cargaCampos() 
    {
        Field[] ff=mClase.getFields();
        for (int i=0; i<ff.length; i++) 
        {
            TField c=new TField(ff[i]);
            fields.addElement(c);
        }
    }
    
    protected int cargaConstructores() 
    {
        Constructor cc[];
        if (!this.mAlsoInherited)
            cc=mClase.getDeclaredConstructors();
        
        else
            cc=mClase.getConstructors();
        int letraActual=0;
        for (int i=0; i<cc.length; i++) 
        {
            //if (Modifier.isPublic(cc[i].getModifiers())) {
                TConstructor c=new TConstructor(cc[i], letraActual++);
                c.setClassName(this.getName());
                if (cc[i].getExceptionTypes().length!=0)
                    c.setLanzaExcepciones(true);
                constructors.addElement(c);
            //}
        }		
        return letraActual;
    }
    
    protected void cargaMetodos(int letraActual)  
    {
        Method mm[];
        if (!this.mAlsoInherited)
            mm=mClase.getDeclaredMethods();
        else
            mm=mClase.getMethods();
        for (int i=0; i<mm.length; i++) 
        {
            if (Modifier.isPublic(mm[i].getModifiers())) {
                Operation m=new Operation(mm[i], letraActual++);
                m.setClassName(this.getName());
                m.setFinal(Modifier.isFinal(mm[i].getModifiers()));
                if (mm[i].getExceptionTypes().length!=0)
                    m.setLanzaExcepciones(true);        
                methods.addElement(m);
            }
            if (Modifier.isStatic(mm[i].getModifiers()) && mm[i].getReturnType().getName().equals(this.mClase.getName())) {
            	TConstructor c=new TConstructor(mm[i], letraActual++);
            	c.setClassName(this.getName());
                if (mm[i].getExceptionTypes().length!=0)
                    c.setLanzaExcepciones(true);
                constructors.addElement(c);
            }
        }		
    }	
    
    public String getName() 
    {
        return mClase.getName();
    }
    
    
    
    public Operation findMethod(String nombre) 
    {
        for (int i=0; i<methods.size(); i++) 
        {
            Operation m=(Operation) methods.elementAt(i);
            if (m.getNombre().equals(nombre))
                return m;
        }
        return null;
    }
    
    public int findMethodNumber(String id) 
    {
        for (int i=0; i<methods.size(); i++) 
        {
            Operation m=(Operation) methods.elementAt(i);
            if (m.getId().equals(id))
                return m.getPos();
        }
        return -1;
    }  
    
    public Operation findMethodById(String id) 
    {
        for (int i=0; i<methods.size(); i++) 
        {
            Operation m=(Operation) methods.elementAt(i);
            if (m.getId().equals(id))
                return m;
        }
        return null;
    }    
    
    public Vector getConstructors() 
    {
        return this.constructors;
    }
    
    public TConstructor getConstructor(int i) 
    {
        return (TConstructor) this.constructors.elementAt(i);
    }
    
    public TOperation getOperation(char id) 
    {
        for (int i=0; i<constructors.size(); i++) 
        {
            TConstructor c=(TConstructor) constructors.elementAt(i);
            if (c.getPos()==id)
                return c;
        }
        for (int i=0; i<methods.size(); i++) 
        {
            Operation m=(Operation) methods.elementAt(i);
            if (m.getPos()==id)
                return m;
        }
        return null;
    }  
    
    public TOperation getOperation(String id) 
    {
        for (int i=0; i<constructors.size(); i++) 
        {
            TConstructor c=(TConstructor) constructors.elementAt(i);
            if (c.getId().equals(id))
                return c;
        }
        for (int i=0; i<methods.size(); i++) 
        {
            Operation m=(Operation) methods.elementAt(i);
            if (m.getId().equals(id))
                return m;
        }
        return null;
    }
    
    public int findOperationNumber(String id) 
    {
        int x=this.findConstructorNumber(id);
        if (x==-1) return this.findMethodNumber(id);
        return x;
    }
    
    public int findConstructorNumber(String id) 
    {
        for (int i=0; i<constructors.size(); i++) 
        {
            TConstructor c=(TConstructor) constructors.elementAt(i);
            if (c.getId().equals(id))
                return c.getPos();
        }
        return -1;
    }    
    
    public void removeConstructor(int i) 
    {
        constructors.remove(i);
    }
    
    public void removeMethod(int i) 
    {
        methods.remove(i);
    }
    
    
    
    public Vector getMethods() 
    {
        return methods;
    }
    
    public Operation getMethod(int x) 
    {
        return (Operation) this.methods.elementAt(x);
    }
    
    
    
    public String getExpandedRegularExpression(String er) {
        String result="";
        for (int i=0; i<er.length(); i++) {
            char caracter=er.charAt(i);
            if ((caracter>='A' && caracter<='Z') || (caracter>='a' && caracter<='z')) {
                result+=this.getOperation(caracter).getId();
            } else result+="<b>"+caracter+"</b>";
        }
        return result;        
    }
    
    
    
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
    
    protected int getTestTemplates(int longitud, int posAnt, int[] cont, TSet tts) throws Exception {
        if (longitud==1) {
            for (int i=0; i<constructors.size(); i++) {
                TConstructor c=(TConstructor) constructors.get(i);
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
                for (int j=0; j<this.methods.size(); j++) {
                    Operation metodo=(Operation) methods.elementAt(j);
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
    
    /** Devuelve un Vector de objetos de clase GroupedTestMethod 
     * */
    public Vector getOriginalesSmall(boolean lotOfCases) throws Exception {
        Vector tts=this.getTestTemplates();
        Vector r=new Vector();
        for (int i=0; i<tts.size(); i++) 
        {
            TestTemplate ts=(TestTemplate) tts.elementAt(i);
            GroupedTestMethod gm;
            if (lotOfCases)
                gm=new GroupedTestMethodForLotOfCases(this, ts.getName(), false);
            else 
                gm=new GroupedTestMethod(ts.getName(), false);
            gm.group(ts);
            r.add(gm);
        }					
        return r;
    }	  
    
    public String getGroupedTestCases(int[] numberOfTestCases) throws Exception {
        Vector tts = this.getTestTemplates();
        long sessionId=new Random().nextLong();
        String sessionPath=Configuration.getInstance().getTempPath()+sessionId;
        new File(sessionPath).mkdir();
        int contador;
        for (contador = 0; contador < tts.size(); contador++) {
            TestTemplate ts = (TestTemplate) tts.elementAt(contador);
            TestMethod testCase=new TGroupedTestMethod(ts);
            testCase.setId(contador+1);
            testCase.save(sessionPath);
        }
        numberOfTestCases[0]=contador;
        String fileName=Configuration.getInstance().getTempPath() + "\\" + sessionId + ".session";
        FileOutputStream f=new FileOutputStream(fileName);
        Properties pp=new Properties(); 
        String cut=this.getName();
        pp.setProperty("CUT", cut);      
        pp.setProperty("SessionId", ""+sessionId);
        pp.setProperty("SessionType", "grouped");
        pp.setProperty("NumberOfTestCases", ""+contador);
        pp.setProperty("DateTime", ""+new Date().getTime());
        pp.store(f, "Session description");
        f.close();        
        return sessionPath;
    }
    
    public String getTestCases(Algorithm algorithm, int format, int[] numberOfTestCases) throws Exception {
    	Vector tts = this.getTestTemplates();
    	return this.getTestCases(algorithm, format, numberOfTestCases, tts);
    }
    public String getTestCases(Algorithm algorithm, int format, int[] numberOfTestCases,Vector tts) throws Exception {
        String sessionType="";
        if (format==TestMethod.JUNIT)
            sessionType="junit";
        else if (format==TestMethod.MUJAVA)
            sessionType="mujava";
        else if (format==TestMethod.STATES_JUNIT)
            sessionType="states";
        else if (format==TestMethod.MUT_JUNIT)
            sessionType="mutjunit";
        else
            throw new Exception("Unrecognized test case format");
        Date dateTime=new Date();
        String sessionId=this.getName() + "_" + dateTime.getDate() + "-" + (1+dateTime.getMonth()) + "-" + 
        	(1900+dateTime.getYear()) + "_" + dateTime.getHours() + "-" + dateTime.getMinutes()+ "-" + dateTime.getTime();
        String sessionPath=Configuration.getInstance().getTempPath()+sessionId;
        new File(sessionPath).mkdir();
        int contador=0;
        for (int i = 0; i < tts.size(); i++) {
            TestTemplate template = (TestTemplate) tts.elementAt(i);
            System.out.print("Generating test methods for the test template "+template.getRE());
            algorithm.setTestTemplate(template);
            Vector ttc = algorithm.getTestCases(template);
            algorithm.removeFunctionallyEquivalentMutants(ttc);
            for (int j = 0; j < ttc.size(); j++) {
                TestCase tc = (TestCase) ttc.elementAt(j);
                contador++;
                TestMethod testCase=null;
                switch (format) {
                case TestMethod.JUNIT:
                    testCase = new TJUnitMethod(tc);
                    break;
                case TestMethod.MUJAVA:
                    testCase = new TMujavaMethod(tc);
                    break;
                case TestMethod.STATES_JUNIT:
                    testCase =new TJUnitMethodForState(tc);
                	break;
                case TestMethod.MUT_JUNIT :
                    testCase=new TMutJUnitMethod(tc);
                	break;
                }
                testCase.setId(contador);
                testCase.save(sessionPath);
            }
        }
        String fileName=Configuration.getInstance().getTempPath() + "\\" + sessionId + ".session";
        FileOutputStream f=new FileOutputStream(fileName);
        Properties pp=new Properties(); 
        String cut=this.getName();
        pp.setProperty("CUT", cut);      
        pp.setProperty("SessionId", ""+sessionId);
        pp.setProperty("SessionType", sessionType);
        pp.setProperty("NumberOfTestCases", ""+contador);
        pp.setProperty("DateTime", ""+ dateTime.getTime());
        pp.store(f, "Session description");
        f.close();
        String statesDescription=this.getXMLStatesDescription();
        fileName=Configuration.getInstance().getTempPath() + "\\" + sessionId + "\\states_definition.xml";
        f=new FileOutputStream(fileName);
        f.write(statesDescription.getBytes());
        f.close();
        numberOfTestCases[0]=contador;
        return sessionPath;
    }
    
    /**
     * @return
     */
    protected String getXMLStatesDescription() {
        String result="<states_description>";
        for (int i=0; i<this.mStates.size(); i++) {
            State st=(State) mStates.get(i);
            result+="<state name='" + st.getName() + "' description='" + st.getDescription() + "'/>";
        }
        result+="</states_description>";
        return result;
    }

    public void resetTestValues() 
    {
        for (int i=0; i<this.getConstructors().size(); i++) 
        {
            TConstructor c=this.getConstructor(i);
            c.resetTestValues();
        }  
        for (int i=0; i<this.getMethods().size(); i++) 
        {
            Operation m=this.getMethod(i);
            m.resetTestValues();
        }  
    }
    
    public void removeDescription() {
        for (int i=0; i<this.getConstructors().size(); i++) 
        {
            TConstructor c=this.getConstructor(i);
            c.removeDescriptions();
        }  
        for (int i=0; i<this.getMethods().size(); i++) 
        {
            Operation m=this.getMethod(i);
            m.removeDescriptions();
        }
    }
    
    public void loadDescription() throws Exception 
    {
        this.removeDescription();
        for (int i=0; i<this.getConstructors().size(); i++) 
        {
            TConstructor c=this.getConstructor(i);
            c.loadDescription(Configuration.getInstance().getConstraintsPath()+this.getName()+"\\");
        }  
        for (int i=0; i<this.getMethods().size(); i++) 
        {
            Operation m=this.getMethod(i);
            m.loadDescription(Configuration.getInstance().getConstraintsPath()+this.getName()+"\\");
        }
        String fileName=Configuration.getInstance().getConstraintsPath() +this.getName()+"\\"
        + "states" + ".states";
        Properties pp = new Properties();  
        FileInputStream f=new FileInputStream(fileName);
        pp.load(f);
        Enumeration ppn=pp.propertyNames();
        while (ppn.hasMoreElements()) 
        {
            String pn=ppn.nextElement().toString();
            State st=new State(pn, pp.getProperty(pn));
            this.add(st);
        }
        f.close();
        fileName=Configuration.getInstance().getConstraintsPath() +this.getName()+"\\"
        + "observer" + ".observer";
        pp = new Properties();  
        f=new FileInputStream(fileName);
        pp.load(f);
        ppn=pp.propertyNames();
        while (ppn.hasMoreElements()) 
        {
            String pn=ppn.nextElement().toString();
            Operation m=this.findMethodById(pp.getProperty(pn));
            this.addObserverMethod(m);
        }
        f.close();    
        try {
            fileName=Configuration.getInstance().getConstraintsPath() +this.getName()+"\\"
            + "Invariants" + ".constraints";
            f=new FileInputStream(fileName);
            byte[] b=new byte[f.available()];
            f.read(b);
            mInvariants=new String(b);
            f.close();          
        }
        catch (Exception ex) {
            mInvariants="";
        }
    }
    
    public void saveDescription() throws Exception 
    {
        Agente.mkdir(Configuration.getInstance().getConstraintsPath(), this.getName());  
        for (int i=0; i<this.getConstructors().size(); i++) 
        {
            TConstructor c=this.getConstructor(i);
            c.saveDescription(Configuration.getInstance().getConstraintsPath() + this.getName() + "\\");
        }    
        for (int i=0; i<this.getMethods().size(); i++) 
        {
            Operation m=this.getMethod(i);
            m.saveDescription(Configuration.getInstance().getConstraintsPath() + this.getName() + "\\");
        }
        String fileName=Configuration.getInstance().getConstraintsPath() + this.getName() + "\\" + "states" + ".states";
        FileOutputStream f=new FileOutputStream(fileName);
        Properties pp=new Properties(); 
        for (int i=0; i<mStates.size(); i++) 
        {
            State st=(State) this.mStates.get(i);
            pp.setProperty(st.getName(), st.getDescription());      
        }
        pp.store(f, "Description of the states");
        f.close();
        fileName=Configuration.getInstance().getConstraintsPath() + this.getName() + "\\" + "observer" + ".observer";
        f=new FileOutputStream(fileName);
        pp=new Properties(); 
        for (int i=0; i<this.mObserverMethods.size(); i++) 
        {
            Operation m=(Operation) this.mObserverMethods.get(i);
            pp.setProperty("observer_" + i, m.getId());
        }
        pp.store(f, "Observer methods");
        f.close();
        fileName=Configuration.getInstance().getConstraintsPath() + 
        this.getName() + "\\Invariants" + ".constraints";
        f=new FileOutputStream(fileName);
        f.write(this.mInvariants.getBytes());
        f.close();
    }  
    
    public void setInvariants(String x) 
    {
        this.mInvariants=x;
    }
    
    public String getInvariants() 
    {
        return this.mInvariants;
    }
    
    public Vector getStates() 
    {
        return mStates;
    }
    
    public State getState(int i) 
    {
        return (State) mStates.get(i);
    }
    
    public void add(State st) throws Exception
    {
        if (getState(st.getName())!=null)
            throw new Exception("That state already exists");
        this.mStates.add(st);
    }
    
    public State getState(String stateName) 
    {
        for (int i=0; i<mStates.size(); i++) 
        {
            State st=(State) mStates.get(i);
            if (st.getName().equals(stateName))
                return st;
        }
        return null;
    }
    
    public void addObserverMethod(Operation m) 
    {
        this.mObserverMethods.add(m);
    }
    
    public Vector getObserverMethods() 
    {
        return this.mObserverMethods;
    }
    
    public void removeObserverMethod(String methodSignature) 
    {
        for (int i=0; i<this.mObserverMethods.size(); i++) 
        {
            Operation m=(Operation) this.mObserverMethods.get(i);
            if (m.getId().equals(methodSignature)) 
            {
                this.mObserverMethods.remove(i);
                return;
            }
        }
    }

    /**
     *  
     * @uml.property name="fields"
     * @uml.associationEnd inverse="tClass:testooj3.domain.TField" multiplicity="(0 -1)" ordering="ordered"
     * 
     */
    private Vector fields;





    /**
     * 
     * @uml.property name="fields"
     */
    public void setFields(java.util.Vector value) {
        fields = value;
    }

    /**
     * 
     * @uml.property name="fields"
     */
    public Iterator fieldsIterator() {
        return fields.iterator();
    }

    /**
     * 
     * @uml.property name="fields"
     */
    public boolean addFields(testooj3.domain.TField element) {
        return fields.add(element);
    }

    /**
     * 
     * @uml.property name="fields"
     */
    public boolean removeFields(testooj3.domain.TField element) {
        return fields.remove(element);
    }

    /**
     * 
     * @uml.property name="fields"
     */
    public boolean isFieldsEmpty() {
        return fields.isEmpty();
    }

    /**
     * 
     * @uml.property name="fields"
     */
    public void clearFields() {
        fields.clear();
    }

    /**
     * 
     * @uml.property name="fields"
     */
    public boolean containsFields(testooj3.domain.TField element) {
        return fields.contains(element);
    }

    /**
     * 
     * @uml.property name="fields"
     */
    public boolean containsAllFields(Collection elements) {
        return fields.containsAll(elements);
    }

    /**
     * 
     * @uml.property name="fields"
     */
    public int fieldsSize() {
        return fields.size();
    }

    /**
     * 
     * @uml.property name="fields"
     */
    public testooj3.domain.TField[] fieldsToArray() {
        return (testooj3.domain.TField[]) fields
            .toArray(new testooj3.domain.TField[fields.size()]);
    }

    /**
     *  
     * @uml.property name="constructors"
     * @uml.associationEnd inverse="tClass:testooj3.domain.TConstructor" multiplicity="(0 -1)" ordering="ordered"
     * 
     */
    private Vector constructors;

    /**
     * 
     * @uml.property name="constructors"
     */
    public void setConstructors(java.util.Vector value) {
        constructors = value;
    }

    /**
     * 
     * @uml.property name="constructors"
     */
    public Iterator constructorsIterator() {
        return constructors.iterator();
    }

    /**
     * 
     * @uml.property name="constructors"
     */
    public boolean addConstructors(testooj3.domain.TConstructor element) {
        return constructors.add(element);
    }

    /**
     * 
     * @uml.property name="constructors"
     */
    public boolean removeConstructors(testooj3.domain.TConstructor element) {
        return constructors.remove(element);
    }

    /**
     * 
     * @uml.property name="constructors"
     */
    public boolean isConstructorsEmpty() {
        return constructors.isEmpty();
    }

    /**
     * 
     * @uml.property name="constructors"
     */
    public void clearConstructors() {
        constructors.clear();
    }

    /**
     * 
     * @uml.property name="constructors"
     */
    public boolean containsConstructors(testooj3.domain.TConstructor element) {
        return constructors.contains(element);
    }

    /**
     * 
     * @uml.property name="constructors"
     */
    public boolean containsAllConstructors(Collection elements) {
        return constructors.containsAll(elements);
    }

    /**
     * 
     * @uml.property name="constructors"
     */
    public int constructorsSize() {
        return constructors.size();
    }

    /**
     * 
     * @uml.property name="constructors"
     */
    public testooj3.domain.TConstructor[] constructorsToArray() {
        return (testooj3.domain.TConstructor[]) constructors
            .toArray(new testooj3.domain.TConstructor[constructors.size()]);
    }

    /**
     *  
     * @uml.property name="methods"
     * @uml.associationEnd inverse="tClass:testooj3.domain.TMethod" multiplicity="(0 -1)" ordering="ordered"
     * 
     */
    private Vector methods;  //  @jve:decl-index=0:

    /**
     * 
     * @uml.property name="methods"
     */
    public void setMethods(java.util.Vector value) {
        methods = value;
    }

    /**
     * 
     * @uml.property name="methods"
     */
    public Iterator methodsIterator() {
        return methods.iterator();
    }

    /**
     * 
     * @uml.property name="methods"
     */
    public boolean addMethods(testooj3.domain.Operation element) {
        return methods.add(element);
    }

    /**
     * 
     * @uml.property name="methods"
     */
    public boolean removeMethods(testooj3.domain.Operation element) {
        return methods.remove(element);
    }

    /**
     * 
     * @uml.property name="methods"
     */
    public boolean isMethodsEmpty() {
        return methods.isEmpty();
    }

    /**
     * 
     * @uml.property name="methods"
     */
    public void clearMethods() {
        methods.clear();
    }

    /**
     * 
     * @uml.property name="methods"
     */
    public boolean containsMethods(testooj3.domain.Operation element) {
        return methods.contains(element);
    }

    /**
     * 
     * @uml.property name="methods"
     */
    public boolean containsAllMethods(Collection elements) {
        return methods.containsAll(elements);
    }

    /**
     * 
     * @uml.property name="methods"
     */
    public int methodsSize() {
        return methods.size();
    }

    /**
     * 
     * @uml.property name="methods"
     */
    public testooj3.domain.Operation[] methodsToArray() {
        return (testooj3.domain.Operation[]) methods
            .toArray(new testooj3.domain.Operation[methods.size()]);
    }

    

	public Operation findMethodBySignature(String signature) {
        for (int i=0; i<methods.size(); i++) 
        {
            Operation m=(Operation) methods.elementAt(i);
            if (m.getSignature().equals(signature))
                return m;
        }
        return null;
	}

	public void removeState(State searched) {
		for (int i=0; i<this.mStates.size(); i++) {
			State st=(State) this.mStates.get(i);
			if (st.equals(searched)) {
				this.mStates.remove(i);
				return;
			}
		}
	}

	public void removeOperation(String id) {
	       for (int i=0; i<constructors.size(); i++) 
	        {
	            TConstructor c=(TConstructor) constructors.elementAt(i);
	            if (c.getId().equals(id)) {
	                this.constructors.remove(i);
	                return;
	            }
	        }
	        for (int i=0; i<methods.size(); i++) 
	        {
	            Operation m=(Operation) methods.elementAt(i);
	            if (m.getId().equals(id)) {
	                this.methods.remove(i);
	                return;
	            }
	        }
	}

	public void reassignIds() {
		for (int i=0; i<this.constructors.size(); i++) {
			TConstructor c=this.getConstructor(i);
			c.setPos(i);
		}
		for (int i=0; i<this.methods.size(); i++) {
			Operation m=this.getMethod(i);
			m.setPos(i+this.constructors.size());
		}
	}
	
	public Class getJavaClass() {
		return this.mClase;
	}

	public Vector getFields() {
		return fields;
	}
	
	public String toHTML() {
		return "<br/>Constructores: "+this.constructors.toString()+"" +
				"<br/>Metodos: "+this.methods.toString()+"" +
				"<br/>Campos: "+this.fields.toString()+"";
	}

	/*public TOperation getOperation(String id) {
		TOperation op;
		for (Iterator it=this.methods.iterator();it.hasNext();){
			op=(TOperation) it.next();
			if (op.getId().equals(id))
				return op;
		}
		for (Iterator it=this.constructors.iterator();it.hasNext();){
			op=(TOperation) it.next();
			if (op.getId().equals(id))
				return op;
		}
		return null;
	}*/
	
	
	
	
	
/*
 *
 *Métodos añadidos para el manejo de las expresiones regulares
 *
 *
 */
	
	/**
     * Añadir una expresión regular
     * @param t
     */
    public void add(TExpresionRegular er) {
        this.mER.add(er);
    }
    
    /**
     * Método que nos dice si una expresión regular se puede obtener a partir de la clase
     * @param er
     * @throws ExpresionException
     * @throws PatternSyntaxException
     */
    public void comprobarER(String er) throws Exception,PatternSyntaxException{
		for (int i=0; i<er.length(); i++) 
        {
            if ((er.charAt(i)>='A' && er.charAt(i)<='Z') || (er.charAt(i)>='a' && er.charAt(i)<='z')) {
                TOperation op=this.getOperation(er.charAt(i));
                if(op == null){
                	throw new Exception("Identifiers exist that do not belong to any method of the class under test");
                }
            }
        } 
		
		Pattern p = Pattern.compile(er);
	}
	
    
    /**
     * Encontrar una expresion regular
     * @param expresion
     * @return
     */
    public TExpresionRegular findRegularExpresion(String expresion) 
    {
        for (int i=0; i<mER.size(); i++) 
        {
            TExpresionRegular er=(TExpresionRegular) mER.elementAt(i);
            if (er.getExpresion().equals(expresion))
                return er;
        }
        return null;
    }
	
    /**
     * Obtener una expresión regular indicando la posición
     * que ocupa en el vector
     * @param i
     * @return
     */
    public TExpresionRegular getRegularExpresion(int i) 
    {
        return (TExpresionRegular) mER.elementAt(i);
    }
    
    /**
     * Obtener la expresion regulares en un Vector
     * @return
     */
    public Vector getRegularExpresions(){
    	return this.mER;
    }
    
    /**
     * Borrar una expresión regular
     * @param expresion
     */
    public void removeRegularExpresion(String expresion) 
    {
        for (int i=0; i<mER.size(); i++) 
        {
            TExpresionRegular er=(TExpresionRegular) mER.elementAt(i);
            if (er.getExpresion().equals(expresion)) {
                mER.remove(i);
                return;
            }
        }		
    }
    
    /**
     * Establecer las expresiones regulares con un vecor
     * @param er
     */
    public void setRegularExpresion(Vector er){
    	mER=er;
    }
    
    
/* 
 * 
 * Métodos reprogramados siguiendo el patrón adaptador
 * 
 * 
 */
	
	public TestTemplate findTestTemplate(String nombre) 
    {
		
		if(this.mER.size()>=1){
			TExpresionRegular er =  (TExpresionRegular)this.mER.get(0);
			return er.findTestTemplate(nombre);
		}
        return null;
    }
	
	public void removeTestScript(String nombre) 
    {
		
		if(this.mER.size()>=1){
			TExpresionRegular er =  (TExpresionRegular)this.mER.get(0);
			er.removeTestTemplate(nombre);
		}
    }
	
	public Vector getTestTemplates() 
    {
		if(this.mER.size()>=1){
			TExpresionRegular er =  (TExpresionRegular)this.mER.get(0);
			return er.getTestTemplates();
		}
        return null;
    }
    
    public void setTestTemplates(Vector tt) {
    	if(this.mER.size()>=1){
			TExpresionRegular er =  (TExpresionRegular)this.mER.get(0);
			er.setTestTemplates(tt);
		}
    	else{
    		TExpresionRegular er = new TExpresionRegular(this,"");
    		er.setTestTemplates(tt);
    		this.mER.add(er);
    	}
    }
    
    public TestTemplate getTestTemplate(int i) 
    {
    	if(this.mER.size()>=1){
			TExpresionRegular er =  (TExpresionRegular)this.mER.get(0);
			return er.getTestTemplate(i);
		}
        return null;
    }
    
    
    /**
     * Los dos métodos auxiliares que complementan a esta función se han
     * trasladado a la clase TExpresionRegular
     * @param maxLength
     * @param er
     * @return
     * @throws Exception
     */
    public Vector getTestTemplates(int maxLength, String er) throws Exception
    {
    	TExpresionRegular expR = new TExpresionRegular(this,er);
    	return expR.generarTestTemplatesEficiente(maxLength);
    }
    
    
    /**
     * @param t
     */
    public void add(TestTemplate t) {
    	if(this.mER.size()>=1){
			TExpresionRegular er =  (TExpresionRegular)this.mER.get(0);
			er.add(t);
		}
    	else{
    		TExpresionRegular er = new TExpresionRegular(this,"");
    		er.add(t);
    		this.mER.add(er);
    	}
    }
    
	public TField findField(String fieldSignature) {
		for (int i=0; i<this.fields.size(); i++) {
			TField tf=(TField) this.fields.get(i);
			String signature=tf.getSignature();
			if (signature.equals(fieldSignature))
				return tf;
			else if (tf.getSubFields().size()>0) {
				TField auxi=tf.findSubField(signature, fieldSignature);
				if (auxi!=null)
					return auxi;
			}				
		}
		return null;
	}
	
	public Operation findMethodByWholeSignature(String wholeSignature) {
        for (int i=0; i<methods.size(); i++) 
        {
            Operation m=(Operation) methods.elementAt(i);
            if (m.getWholeSignature().equals(wholeSignature))
                return m;
        }
        return null;
	}
	
	public String getIFName(){
		return this.mClassImpl;
	}
}
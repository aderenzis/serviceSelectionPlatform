package pruebaDiego;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import me.gui.IProgressWindow;

import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.domain.PrimitiveValue;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.TJUnitMethod;
import testooj3.domain.TJUnitMethodForState;
import testooj3.domain.Operation;
import testooj3.domain.TMujavaMethod;
import testooj3.domain.TMutJUnitMethod;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.TestCase;
import testooj3.domain.TestMethod;
import testooj3.domain.TestMethodLine;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestoojClassLoader;
import testooj3.domain.algorithms.Algorithm;
import testooj3.domain.algorithms.AllCombinationsElegant;
import testooj3.domain.states.State;
import testooj3.domain.tcmutation.TCMutatorSystem;
import testooj3.domain.testfiles.junit.JUnitFile;
import testooj3.domain.testfiles.mujava.MujavaFile;
import testooj3.domain.testmethodlines.APeloLine;
import testooj3.domain.testmethodlines.CallLineToConstructor;
import testooj3.domain.testmethodlines.CallLineToMethod;
import testooj3.domain.testmethodlines.ObjectDeclarationLine;
import testooj3.gui.JDClassPathExplorer;
import testooj3.gui.JDListOfMujavaMethods;
import testooj3.gui.components.ILogWindow;

public class PruebaMUJAVA implements ILogWindow{

	public static void main(String [] args)
	{
		/** Seleccion de la clase */
		String classpath="D:/Dropbox/workspace/TestOoj/bin/";
		Configuration.getInstance().setClassPath(classpath);
		System.out.println("Class path es: "+ Configuration.getInstance().getClassPath());
		JDClassPathExplorer classname=new JDClassPathExplorer();
		classname.setModal(true);
		classname.setVisible(true);
		
		
        if (classname.getSelectedClass()!=null)
            System.out.println("la clase seleccionada es: "+ classname.getSelectedClass());
        
        /** Carga de los miembros de la clase */
        
        Interface c=null;
        try 
        {
            if (classname.getSelectedClass()==null || classname.getSelectedClass().trim().length()==0)
                throw new Exception("Write or select a valid class");
            c=TestoojClassLoader.load(classname.getSelectedClass(), Configuration.getInstance().getClassPath(), false);
            //c=new TClass(Class.forName(this.jtfClassName.getText()), this.jchIncludeInherited.isSelected());
            if (c.getConstructors().size()+c.getMethods().size()>50) {
            	System.out.println("la clase tiene mas de 50 operaciones!");
            }
//            mParentWindow.setClass(c);
//            mParentWindow.showMembers(c);
            /** muestro los datos del TClass creado a partir de la clase seleccionada */
            
            
            //busco metodos conflictivos
        	ArrayList<String> conflictivos=new ArrayList<String>();
        	conflictivos.add("deposit");
        	conflictivos.add("getCliente");
//        	conflictivos.add("getBalance");
        	
        	//obtengo las letras de los conflictivos para la exp regular
        	
        	String metodoConflic;
            
            System.out.println("la clase es: "+c.getName());
            System.out.println("sus metodos son: ");
            System.out.println("Constructor: ");
            for(int i=0; i<c.constructorsSize();i++)
            {
            	System.out.println(c.getConstructor(i).toString());
            }
            System.out.println("Metodos: ");
//            char[] letrasConfVoid=new char[20];
//            char[] letrasConfNoVoid=new char[20];
//            char[] letrasReturns=new char[20];
            ArrayList<Character> letrasConfVoid= new ArrayList<Character>();
            ArrayList<Character> letrasConfNoVoid= new ArrayList<Character>();
            ArrayList<Character> letrasReturns= new ArrayList<Character>();
            int posMP=0;
//            int contLetrasConf=0;
//            int contLetrasRet=0;
            
//        		metodoConflic=(String) conflictivos.get(i);
            	
        	    for(int i=0; i<c.methodsSize();i++)
        	    {
        	    	metodoConflic="";
        	    	System.out.println(c.getMethod(i).toString()+" Letra: "+c.getMethod(i).getPos());
        	    	Iterator ite=conflictivos.iterator();
        	    	while(ite.hasNext())
        	    	{
        	    		if(ite.next().equals(c.getMethod(i).getNombre()))//si el metodo que estoy recorriendo es conflictivo
        	    			metodoConflic=c.getMethod(i).getNombre();
        	    	}
        	    	if(c.getMethod(i).getNombre().equals(metodoConflic))
        	    	{
        	    		if(c.getMethod(i).getTipo().equals("void"))
        	    			letrasConfVoid.add(c.getMethod(i).getPos());
        	    		else 
        	    			letrasConfNoVoid.add(c.getMethod(i).getPos());
//        	    		contLetrasConf++;
        	    	}
            	
        	    	//busco retornadores..
        	    	if(c.getMethod(i).getNombre().equalsIgnoreCase("getBalance"))
        	    		posMP=i;
        	    	if(!c.getMethod(i).getTipo().equals("void"))
        	    	{
        	    		letrasReturns.add(c.getMethod(i).getPos());
//        	    		contLetrasRet++;
        	    	}
        	    }
            
            //genero exp regular
            String expReg="A(";
            int k=0;
            int cnv=0;
            while(k<letrasConfVoid.size()+letrasConfNoVoid.size()){
            	if(k<letrasConfVoid.size())
            	{
            		expReg+=letrasConfVoid.get(k);
            		int j=0;
            		if(j<letrasReturns.size())
            		{
            			expReg+="(";
	            		while(j<letrasReturns.size())
	            		{
	            			expReg+=letrasReturns.get(j);
	            			j++;
	            			if(j<letrasReturns.size())
	            				expReg+="|";
	            		}
	            		expReg+=")";
            		}
            	}
            	else
            	{
            		expReg+=letrasConfNoVoid.get(cnv);
            		cnv++;
            	}
            	k++;
            	if(k<letrasConfVoid.size()+letrasConfNoVoid.size())
            		expReg+="|";
            }
//            String expReg="A("+letra1+"|"+letra2+")";
            expReg+=")";
            System.out.println("Expresion regular: "+expReg);
            
            /** Aca deberia permitir agregar atributos a cada uno de los metodos que lo requieran*/
           
            /** genero los T load()*/
//            JSPClassStructure JSPClassStructure=new JSPClassStructure();
//            JSPClassStructure.setClass(c);
//            this.mClass=c;
//            this.JSPClassStructure.showLevels(3);
//            this.JSPClassStructure.setClass(c);
            
//            this.mSelectedParameter.addTestValue(new PrimitiveValue(this.jtfTestValue.getText()));
            TParameter p;
            for (int i=0; i<c.getConstructors().size(); i++) 
            {
                TConstructor tc=(TConstructor) c.getConstructors().elementAt(i);
                    for (int j=0; j<tc.getParametros().size(); j++) {
                        p=tc.getParametro(j);
                        p.addTestValue(new PrimitiveValue("5"));//atributo para el parametro... en caso de tener parametros.
                    }
            
            }
//            for (int i=0; i<c.getMethods().size(); i++) // les esta agregando el mismo parametro a todos los metodos...
//            {
//                TMethod m=c.getMethod(i);
//                if(m.getNombre().equalsIgnoreCase("deposit"))
//                    for (int j=0; j<m.getParametros().size(); j++) {//repite una sola vez...
//                        p=m.getParametro(j);
//                        p.addTestValue(new PrimitiveValue("7"));
//                        p.addTestValue(new PrimitiveValue("8"));
//                        p.addTestValue(new PrimitiveValue("0"));
//                    }
//                if(m.getNombre().equalsIgnoreCase("withdraw"))
//                    for (int j=0; j<m.getParametros().size(); j++) {//repite una sola vez...
//                        p=m.getParametro(j);
//                        p.addTestValue(new PrimitiveValue("88"));
//                        p.addTestValue(new PrimitiveValue("24"));
//                    }
//                
//            }
           /** agregando parametros por usuario*/
           //preguntar solo por los conflictivos... usar arreglos de letras conflictivas...
            
            for (int i=0; i<c.getMethods().size(); i++) // les esta agregando el mismo parametro a todos los metodos...
            {
                Operation m=c.getMethod(i);
                Character letraMetodo=new Character(m.getPos());//obtengo letra de el metodo
                int esConflicNoV=letrasConfNoVoid.indexOf(letraMetodo);
                int esConflicV=letrasConfVoid.indexOf(letraMetodo);
                if(esConflicNoV>=0 || esConflicV>=0){//si es uno de los conflictivos...
	                boolean otroParametro=!m.getParametros().isEmpty();//si no esta vacio...
	                while(otroParametro)
	                {
	                	//una llamada de parametros...
	                	//deberia mostrar como es la forma del metodo con los tipos de valores
	                	System.out.println("El metodo conflictivo "+m.getNombre()+" tiene parametros ");
	                	System.out.println("Debe ingresarlos en orden segun el siguiente formato del metodo: ");
	                	System.out.println(m.toString());
	                	for (int j=0; j<m.getParametros().size(); j++) {
	                        p=m.getParametro(j);
	                        String tipoParametro=p.getTipo();
//	                        if(tipoParametro.equals("double"))
//	                        {
	                        	System.out.println("Ingrese el valor para el parametro  de tipo \""+tipoParametro+" x"+(j+1)+"\": ");
	                        	String valor=TecladoIn.readLine();
	                        	p.addTestValue(new PrimitiveValue(valor));
//	                        }
	                    }
	                	otroParametro=false;
	                	System.out.println("¿Desea hacer otra llamada al metodo "+m.getNombre()+"?");
	                	System.out.println("Ingrese <S>i para aceptar, sino presione cualquier otra tecla.");
	                	char sigue=TecladoIn.readLineNonwhiteChar();
	                	if(sigue=='S' || sigue=='s')
	                		otroParametro=true;
	                	else
	                		otroParametro=false;
	                	
	                }
                }
            }
            
            /** FIN agregando parametros por usuario*/
            //pedir valores para los parametros
            
            //pedir pre y post condiciones
//            mSelectedOperation.setPreassertions("");
//	        mSelectedOperation.setPostassertions("");
//            /** PRE y POST*/
//                 
//            for (int i=0; i<c.getMethods().size(); i++) // les esta agregando el mismo parametro a todos los metodos...
//            {
//                TMethod m=c.getMethod(i);
//                if(m.getNombre().equalsIgnoreCase("deposit"))
//                {
//                	m.setPreassertions("String sal=obtained.getBalance();");
//                    m.setPostassertions("assertTrue(sal==obtained.getBalance()+arg1);");
//                }
//                if(m.getNombre().equalsIgnoreCase("withdraw"))
//                {
//                	m.setPreassertions("String sal=obtained.getBalance();");
//                	m.setPostassertions("assertTrue(sal==obtained.getBalance()-arg1);");
//                }
//                
//            }
            
            
	        String path=Configuration.getInstance().getConstraintsPath();
            
            /**cargo los templates*/
            
            if(c==null)
            	throw new Exception("There is not any class loaded");
            Vector tts=c.getTestTemplates(Integer.parseInt("5"),expReg);
            c.setTestTemplates(tts);
            
            /**construccion de los test*/
            //tomo a los templates generados con la expresion regular
            //TCMutatorSystem tiene las op con parametros conflictivas (deposit withdraw)
            
            
            //buildMujavaFile
            TCMutatorSystem mutator=new TCMutatorSystem(c.getTestTemplates());
            
            Vector templates=mutator.buildMutants();
            for (int i=0; i<templates.size(); i++)//2 veces
                c.add((TestTemplate) templates.get(i));
            
            int[] numberOfTestCases=new int[1];
            //String sessionPath=this.mClass.getTestCases(this.algorithm, TestMethod.MUJAVA, numberOfTestCases);
            Algorithm algorithm=new AllCombinationsElegant();
            //ahora se crean los casos de test con el formato mujava y agrega las propiedades al archivo .session 
            //(con la id de sesion calculada segun la hora) 
            
            /**Inicio Ultima modificacion*/
            /** En esta instancia se intenta agregar  */
            
            //String sessionPath=c.getTestCases(algorithm, TestMethod.MUJAVA, numberOfTestCases);
            Vector tts2 = c.getTestTemplates();
//        	return this.getTestCases(algorithm, format, numberOfTestCases, tts2);
            int format=TestMethod.MUJAVA;
            String sessionType="";
        	sessionType="mujava";
        	Date dateTime=new Date();
            String sessionId=c.getName() + "_" + dateTime.getDate() + "-" + (1+dateTime.getMonth()) + "-" + 
            	(1900+dateTime.getYear()) + "_" + dateTime.getHours() + "-" + dateTime.getMinutes()+ "-" + dateTime.getTime();
            String sessionPath=Configuration.getInstance().getTempPath()+sessionId;
            new File(sessionPath).mkdir();
            
            int contador=0;
            for (int i = 0; i < tts2.size(); i++) {
                TestTemplate template = (TestTemplate) tts2.elementAt(i);
                System.out.print("Generating test methods for the test template "+template.getRE());
                algorithm.setTestTemplate(template);
                Vector ttc = algorithm.getTestCases(template);//ACA AGREGA LOS VALORES DE LOS PARAMETROS
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
                    	/** 
                    	 * Aca para no modificar la clase TMujavaMethod lo que hice fue una vez 
                    	 * generados los casos de test normales, 
                    	 * los transformo para que tomen la forma que me interesa.
                    	 * Se trataran de solucionar el problema que tenian los obtained.toString()
                    	 * para intentar buscar la forma de obtener algo con sentido y que pueda ser comparable
                    	 * para esto se necesita poder DETECTAR los metodos que obtiene resultados por ejemplo:
                    	 * en los casos donde las operaciones son de tipo void, se hacia esto: return obtained.toString();
                    	 * ahora lo mas indicado seria poder hacer esto: return obtained.getBalance().toString();
                    	 * ya que getBalance devuelve un tipo double.
                    	 * 
                    	 * 
                    	 * */
                    	testCase = new TMujavaMethod(tc);
                    	Vector lineasTest=testCase.getLineas();//saco lo que se seteo arriba
                    	Operation ope= (Operation) tc.getMetodos().elementAt(0);//siempre hay solo un metodo en mi caso
                    	
                    	if(!ope.getLanzaExcepciones())
                    	{
	                    	int ultimaLinea=lineasTest.size()-1;//siempre modifico la ultima
	                    	if(ope.getTipo().equals("void"))
	                    	{
	                    		lineasTest.removeElementAt(ultimaLinea);//la del return vieja...
	//                    		String metodoPrimitivo= ((TMethod) c.getMethods().elementAt(posMP)).getNombre();//cambiar despues el getNombre por getTipo para buscar prim
	                    		//se espera que metodoPrimitivo sea getBalance
	                    		CallLineToMethod lineaResultado=(CallLineToMethod)lineasTest.get(lineasTest.size()-1);
	                    		String tipoRetorno=transformarPrimitivo(lineaResultado.getMReturnType());
	                    		if(!tipoRetorno.equals(""))//si es un primitivo
	                    		{
	                    			APeloLine apl=new APeloLine("return new "+tipoRetorno+"(result"+lineaResultado.getMPosMetodo()+").toString();");
	                        		lineasTest.add(apl);
	                    		}else
	                    		{//si no es un primitivo
	                    			if(lineaResultado.getMReturnType().equals("String")|| lineaResultado.getMReturnType().equals("java.lang.String"))
	                    			{
	                    				APeloLine apl=new APeloLine("return result"+lineaResultado.getMPosMetodo()+";");
	                            		lineasTest.add(apl);
	                    			}
	                    			else //si es un tipo COMPLEJO (solucionar despues)
	                    			{
	                    				APeloLine apl=new APeloLine("return result"+lineaResultado.getMPosMetodo()+".toString();");
	                    				lineasTest.add(apl);
	                    			}
	                    		}
	                    		
	                    	}else //Si es un metodo con retorno
	                    	{
	                    		String noPrimitivo=transformarPrimitivo(ope.getTipo());
	                    		if(!noPrimitivo.equals(""))//es un primitivo
	                    		{
	                    			lineasTest.removeElementAt(ultimaLinea);
	                    			CallLineToMethod clm=(CallLineToMethod) lineasTest.elementAt(lineasTest.size()-1);//yo se que solo va a haber una solo un resultado
	                    			System.out.println(clm.getMReturnType());//solo para ver si daba igual que usar noprimitivo
	                    			APeloLine apl=new APeloLine("return new "+noPrimitivo+"(result"+clm.getMPosMetodo()+").toString();");
	                        		lineasTest.add(apl);
	                    		}
	                    		else{
	                    			System.out.println(ope.getTipo());
	                    			if(ope.getTipo().equals("String") || ope.getTipo().equals("java.lang.String"))
	                    			{
	                    				lineasTest.removeElementAt(ultimaLinea);
	                    				CallLineToMethod clm=(CallLineToMethod) lineasTest.elementAt(lineasTest.size()-1);
	                    				APeloLine apl=new APeloLine("return result"+clm.getMPosMetodo()+";");
	                            		lineasTest.add(apl);
	                    			}
	                    			else //si es un tipo COMPLEJO
	                    			{
	                    				lineasTest.removeElementAt(ultimaLinea);
	                    				CallLineToMethod clm=(CallLineToMethod) lineasTest.elementAt(lineasTest.size()-1);
	                    				APeloLine apl=new APeloLine("return result"+clm.getMPosMetodo()+".toString();");
	                    				lineasTest.add(apl);
	                    			}
	                    			
	                    		}
	                    	}
                    	}else //si lanza excepciones...
                    	{
                    		
                    	}
                    	//segun tipo de retorno de la anteultima linea
                    	
                    	/** hecho con andres*/
//                    	lineasTest.removeElementAt(3);
//                    	CallLineToMethod tml=(CallLineToMethod)lineasTest.elementAt(2);
//                    	if(tml.getMMethodName().equals("getBalance"))
//                    	{
//                    		String metodosConResultados="getBalance()";//se deberian identificar a estos tipos de metodos...
//                    	}
//                    	APeloLine apl=new APeloLine("return new Double(result"+tml.getMPosMetodo()+").toString();");
//                    	lineasTest.add(apl);
                    	/** FIN hecho con andres*/
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
            String fileName2=Configuration.getInstance().getTempPath() + "\\" + sessionId + ".session";
            FileOutputStream f2=new FileOutputStream(fileName2);
            Properties pp=new Properties(); 
            String cut=c.getName();
            pp.setProperty("CUT", cut);      
            pp.setProperty("SessionId", ""+sessionId);
            pp.setProperty("SessionType", sessionType);
            pp.setProperty("NumberOfTestCases", ""+contador);
            pp.setProperty("DateTime", ""+ dateTime.getTime());
            pp.store(f2, "Session description");
            f2.close();
//          String statesDescription=this.getXMLStatesDescription();
            String result="<states_description>";
            for (int i=0; i<c.getStates().size(); i++) {
                State st=(State) c.getStates().get(i);
                result+="<state name='" + st.getName() + "' description='" + st.getDescription() + "'/>";
            }
            result+="</states_description>";
            String statesDescription= result;
            
            fileName2=Configuration.getInstance().getTempPath() + "\\" + sessionId + "\\states_definition.xml";
            f2=new FileOutputStream(fileName2);
            f2.write(statesDescription.getBytes());
            f2.close();
            numberOfTestCases[0]=contador;
//            return sessionPath;
            
            
            /**Fin Ultima modificacion*/
            
            //ahora hacer 
            //showMujavaDialog(numberOfTestCases, sessionPath, null);
            
//          saveMujavaFiles(numberOfTestCases, sessionPath, numberOfFiles, cont, cutName);
//          saveMujavaFiles(int[] numberOfTestCases, String sessionPath, int numberOfFiles, int cont, String cutName)
//            saveMujavaFiles(33, String sessionPath, 1, 1, null)
            //ver que viene en numberOfTestCases[]
            int numberOfFiles=1+(numberOfTestCases[0]/300), cont=1;
            for (int i=1; i<=numberOfFiles; i++) 
            {
            	MujavaFile testFile=new MujavaFile("Mujava" + Auxi.recorta(c.getName())+"_" + cont);
            	Vector<TMujavaMethod> casos=new Vector<TMujavaMethod>();
            	for (int j=cont; j<=300*i; j++) 
            	{
            		cont++;
            		if (j<=numberOfTestCases[0]) 
            		{
            			casos.add(MujavaFile.load(sessionPath+"\\"+j+".mujava"));
            			//agrego los archivos .mujava a la carpeta
            		}
            	}
            	testFile.setMethodList(casos);
            	String fileName=Configuration.getInstance().getMujavaRoot()+"testset\\"+testFile.getFileName();
            	FileOutputStream f=new FileOutputStream(fileName);
            	String exp=c.getRegularExpresion(0).getExpresion();
            	String regExp="// Regular expression: " + exp + "\n";
            	regExp+="// Max. length: " + Integer.parseInt("5") + "\n";
            	f.write(regExp.getBytes());
            	String texto=testFile.toString();
            	System.out.println(texto);
            	byte[] ct=texto.getBytes();
            	f.write(ct);
            	f.close();
            	System.out.println("path :"+fileName);
            }
            Auxi.deleteDirectory(sessionPath, numberOfTestCases[0]);        
//            this.log(numberOfFiles + " Mujava files generated (leaved at " + Configuration.getInstance().getMujavaRoot()+"testset\\)");
            
            //PARA COMPILAR
            boolean compileFiles=true;
            if (compileFiles) {
            	Vector<String> files=new Vector<String>();
            	for (int i=1; i<=numberOfFiles; i++) {
            		String fileName="Mujava" + Auxi.recorta(c.getName())+"_" + i;
            		files.add(fileName);
            		String path2=Configuration.getInstance().getMujavaRoot() + "testset";
            		System.out.println("path compilar:"+path2);
            		
//            		this.compile(path2, fileName); 
            		
//            		me.domain.Compiler compiler=new me.domain.Compiler(this);
//            		me.domain.Compiler compiler=new me.domain.Compiler(this);
            		
//                	compiler.simpleCompile(path, "", fileName); //MODIFICADO POR ISRAEL
//                	System.out.println(path+":"+fileName);
            		
            	}
            }
            
        }
        catch (ClassNotFoundException ex) 
        {
        	System.out.println("The class under test (" + classname.getSelectedClass() + ") has not been found");
        } 
        catch (MalformedURLException ex) {
        	System.out.println("There is an error in the classpath (MalformedURLException");
        }
        catch (Exception ex) {
        	System.out.println(ex.toString());
        }
        
	}
	

	public void log(String msg) {
		// TODO Auto-generated method stub
		 msg=msg+"";
		 System.out.println(msg);
	}

	public void logError(String msg) {
		// TODO Auto-generated method stub
		
	        msg="<font color='red'>" + msg + "</font>"; //+jepMsg.getText().trim();
	        System.out.println(msg);
//	        jepMsg.select(0, 0);  
		
	}
	
	private static String transformarPrimitivo(String primitivo)
	{
		String suObjeto="";
		if(primitivo.equals("int"))
				suObjeto="Integer";
		else if(primitivo.equals("double"))
				suObjeto="Double";
		else if(primitivo.equals("boolean"))
				suObjeto="Boolean";
		else if(primitivo.equals("float"))
				suObjeto="Float";
		else if(primitivo.equals("long"))
				suObjeto="Long";
		else if(primitivo.equals("byte"))
				suObjeto="Byte";
		else if(primitivo.equals("char"))
				suObjeto="Character";
		return suObjeto;
	}
	
}

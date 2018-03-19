package reducedTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JEditorPane;

import testooj3.auxiliares.Auxi;
import testooj3.auxiliares.Copiar;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TJUnitMethod;
import testooj3.domain.TJUnitMethodForState;
import testooj3.domain.Operation;
import testooj3.domain.TMujavaMethod;
import testooj3.domain.TMutJUnitMethod;
import testooj3.domain.TestCase;
import testooj3.domain.TestMethod;
import testooj3.domain.TestTemplate;
import testooj3.domain.algorithms.Algorithm;
import testooj3.domain.algorithms.AllCombinationsElegant;
import testooj3.domain.states.State;
import testooj3.domain.tcmutation.TCMutatorSystem;
import testooj3.domain.testfiles.mujava.MujavaFile;
import testooj3.domain.testmethodlines.APeloLine;
import testooj3.domain.testmethodlines.CallLineToMethod;
import testooj3.gui.JDListOfMujavaMethods;

public class ReducedMuJavaTS {
	
	private Interface c;
	private Vector conflictMethods;
	private String expresion;
	private int[] numberOfTestCases;
	private JDConflictingMethods jdcm;
	
	public ReducedMuJavaTS(Interface c, Vector conflictMethods, JDConflictingMethods jdcm)
	{
		this.c=c;
		this.conflictMethods=conflictMethods;
		this.expresion="";
		numberOfTestCases=new int[1];
		this.jdcm=jdcm;
	}

	public void generateTS()
	{
		expresion=this.generateExpReg();
		jdcm.log("\nLa expresion gerenada es : "+expresion);//muestro la expresion por consola...
		try
		{
			//copio lo generado por el Shadow (original) para permitir la compilacion del Mujava
            Copiar cop = new Copiar();
            String dirOrigial=Configuration.getInstance().getWorkingPath() + "wrappers" + File.separator + "original";
            String dirMujava=Configuration.getInstance().getMujavaRoot() + "testset";
     		
            cop.copiaDirectorios(dirOrigial, dirMujava);
            
			/**carga de templates*/
			this.buildTemplates();
            this.buildMujavaFile();
            
            //copio lo generado a la carpeta de trabajo...
            String fileName="Mujava" + Auxi.recorta(c.getName())+".class";
            String dirGenMujava=Configuration.getInstance().getMujavaRoot() + "testset"+File.separator+fileName;
     		String dirWrappers=Configuration.getInstance().getWorkingPath() + "wrappers"+File.separator+fileName;
            cop.copiaDirectorios(dirGenMujava, dirWrappers);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void buildMujavaFile()
	{
		try
		{
			this.generateMutants();
//			int[] numberOfTestCases=new int[1];
			String sessionPath=this.generateTestCases(numberOfTestCases);
			jdcm.log("\nLa cantidad de casos de test generados es: "+numberOfTestCases[0]);
			jdcm.log("\nArchivo generado en: "+sessionPath);
//          showMujavaDialog
            JDListOfMujavaMethods jd=new JDListOfMujavaMethods();
//            jd.setParentWindow(jdcm);//pasar jdcm como parametro..
            jd.setTestCases(sessionPath, numberOfTestCases[0]);
            jd.setModal(true);
            jd.setVisible(true);
            
            int numberOfFiles=1+(numberOfTestCases[0]/300);
            this.saveMujavaFiles(numberOfFiles,numberOfTestCases,sessionPath);
            
            //compilar
            compile(numberOfFiles,jd);
           
         
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	private void compile(int numberOfFiles,JDListOfMujavaMethods jd)
	{
		 boolean compileFiles=jd.getCompile();
         if (compileFiles) {
         	Vector<String> files=new Vector<String>();
         	for (int i=1; i<=numberOfFiles; i++) {
//         		String fileName="Mujava" + Auxi.recorta(c.getName())+"_" + i;
         		String fileName="Mujava" + Auxi.recorta(c.getName());
         		files.add(fileName);
         		String path2=Configuration.getInstance().getMujavaRoot() + "testset";
//         		String path2=Configuration.getInstance().getWorkingPath() + "wrappers";
         		System.out.println("path compilar:"+path2);
//         		this.compilo(path2,fileName); //Martin
         		this.compile(path2,fileName);
         	}
         }
	}
	
	private void saveMujavaFiles(int numberOfFiles,int[] numberOfTestCases,String sessionPath)
	{
		try{
			
			int cont=1;
	        for (int i=1; i<=numberOfFiles; i++) 
	        {
	        	MujavaFile testFile=new MujavaFile("Mujava" + Auxi.recorta(c.getName())); //+"_" + cont);
	        	Vector<TMujavaMethod> casos=new Vector<TMujavaMethod>();
	        	for (int j=cont; j<=300*i; j++) 
	        	{
	        		cont++;
	        		if (j<=numberOfTestCases[0]) 
	        		{
	        			casos.add(MujavaFile.load(sessionPath+File.separator+j+".mujava"));
	        			//agrego los archivos .mujava a la carpeta
	        		}
	        	}
	        	testFile.setMethodList(casos);
	        	String fileName=Configuration.getInstance().getMujavaRoot()+"testset"+File.separator+testFile.getFileName();
//	        	String fileName=Configuration.getInstance().getWorkingPath()+"wrappers"+File.separator+testFile.getFileName();
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void generateMutants()
	{
		try{
			TCMutatorSystem mutator=new TCMutatorSystem(c.getTestTemplates());
			Vector templates=mutator.buildMutants();
			for (int i=0; i<templates.size(); i++)
				c.add((TestTemplate) templates.get(i));
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
	}
	
	private String generateTestCases(int[] numberOfTestCases)
	{
		String sessionPath=null;
		try{
//			String sessionPath=this.mClass.getTestCases(this.algorithm, TestMethod.MUJAVA, numberOfTestCases);
//			createDirectoriFiles();
			Date dateTime=new Date();
	        String sessionId=c.getName() + "_" + dateTime.getDate() + "-" + (1+dateTime.getMonth()) + "-" + 
	        	(1900+dateTime.getYear()) + "_" + dateTime.getHours() + "-" + dateTime.getMinutes()+ "-" + dateTime.getTime();
	        sessionPath=Configuration.getInstance().getTempPath()+sessionId;
	        new File(sessionPath).mkdirs();
	        
//	        generateTestCases();
	        Vector tts2 = c.getTestTemplates();
	        Algorithm algorithm=new AllCombinationsElegant();
	        int contador=0;
	        for (int i = 0; i < tts2.size(); i++) 
	        {
	            TestTemplate template = (TestTemplate) tts2.elementAt(i);
	            System.out.print("Generating test methods for the test template "+template.getRE());
	            algorithm.setTestTemplate(template);
	            Vector ttc = algorithm.getTestCases(template);//ACA AGREGA LOS VALORES DE LOS PARAMETROS
	            algorithm.removeFunctionallyEquivalentMutants(ttc);
	            for (int j = 0; j < ttc.size(); j++)
	            {
	                TestCase tc = (TestCase) ttc.elementAt(j);
	                contador++;
	                TestMethod testCase=null;
	            	testCase = new TMujavaMethod(tc);
	            	/**
	            	 * PREGUNTA: esto se sigue haciendo desde afuera?? o ahora se podria entrar y agregar 
	            	 * una nueva operacion dentro del TMujavaMethod?
	            	 * */
	            	Vector lineasTest=testCase.getLineas();//saco lo que se seteo arriba
	            	Operation ope= (Operation) tc.getMetodos().elementAt(0);//siempre hay solo un metodo en mi caso
	            	
	            	if(!ope.getLanzaExcepciones())
	            	{
	                	int ultimaLinea=lineasTest.size()-1;//siempre modifico la ultima
	                	if(ope.getTipo().equals("void"))
	                	{
	                		lineasTest.removeElementAt(ultimaLinea);//la del return vieja...
//	                		String metodoPrimitivo= ((TMethod) c.getMethods().elementAt(posMP)).getNombre();//cambiar despues el getNombre por getTipo para buscar prim
//	                		se espera que metodoPrimitivo sea getBalance
	                		CallLineToMethod lineaResultado=(CallLineToMethod)lineasTest.get(lineasTest.size()-1);
	                		String tipoRetorno=ControlTypes.transformarPrimitivo(lineaResultado.getMReturnType());
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
	                		String noPrimitivo=ControlTypes.transformarPrimitivo(ope.getTipo());
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
	                testCase.setId(contador);
	                testCase.save(sessionPath);
	            }
	        }
//	        creo la session...
	        String fileName2=Configuration.getInstance().getTempPath() + File.separator + sessionId + ".session";
	        FileOutputStream f2=new FileOutputStream(fileName2);
	        Properties pp=new Properties(); 
	        String cut=c.getName();
	        pp.setProperty("CUT", cut);      
	        pp.setProperty("SessionId", ""+sessionId);
	        pp.setProperty("SessionType", "mujava");
	        pp.setProperty("NumberOfTestCases", ""+contador);
	        pp.setProperty("DateTime", ""+ dateTime.getTime());
	        pp.store(f2, "Session description");
	        f2.close();
//	        String statesDescription=this.getXMLStatesDescription();
	        String result="<states_description>";
	        for(int i=0; i<c.getStates().size(); i++) {
	            State st=(State) c.getStates().get(i);
	            result+="<state name='" + st.getName() + "' description='" + st.getDescription() + "'/>";
	        }
	        result+="</states_description>";
	        String statesDescription= result;
	        
	        fileName2=Configuration.getInstance().getTempPath() + File.separator + sessionId + File.separator +"states_definition.xml";
	        f2=new FileOutputStream(fileName2);
	        f2.write(statesDescription.getBytes());
	        f2.close();
	        numberOfTestCases[0]=contador;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sessionPath;
	}
	
	private void compilo(String path,String file) {
		
		String fileC=path+"/"+file+".java";
		System.out.println("largo a compilar la interfase: "+fileC);
		
		String classpath=Configuration.getInstance().getClassPath();
		String command=System.getenv("JAVA_HOME")+File.separator+"bin"+File.separator+"javac";
		String[] commandArray = {"javac", " -d "+classpath+ " -classpath "+classpath+" ",fileC};
		System.out.println("comando: "+commandArray[0]+" "+commandArray[1]+" "+commandArray[2]);
		String[] environment ={};
		Runtime runtime = Runtime.getRuntime();
        try {
			Process javac = runtime.exec(commandArray, environment);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 private void compile(String path, String cutName) {
		me.domain.Compiler compiler=new me.domain.Compiler(jdcm);
	   	compiler.simpleCompile(path, "", cutName); //MODIFICADO POR ISRAEL
	    System.out.println(path+":"+cutName);
	 }
	
	
	private void buildTemplates()
	{
		try
		{
			if(c==null)
	        	throw new Exception("There is not any class loaded");
	        Vector tts=c.getTestTemplates(Integer.parseInt("5"),expresion);//Limitar cantidad en base a la cantidad de metodos... hacer pruebas.
	        c.setTestTemplates(tts);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String generateExpReg()
	{
		String expReg="";
		if(c!=null)
		{
			ArrayList<Character> letrasConfVoid= new ArrayList<Character>();
			ArrayList<Character> letrasConfNoVoid= new ArrayList<Character>();
	        ArrayList<Character> letrasReturns= new ArrayList<Character>();
			this.discriminateMethodos(letrasConfVoid,letrasConfNoVoid,letrasReturns);
			
			expReg="A(";
            int k=0;
            int cnv=0;
            while(k<letrasConfVoid.size()+letrasConfNoVoid.size())
            {
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
            expReg+=")";
            System.out.println("Expresion regular: "+expReg);
            
		}
		return expReg;
	}
	
	private void discriminateMethodos(ArrayList<Character> letrasConfVoid,
				ArrayList<Character> letrasConfNoVoid,ArrayList<Character> letrasReturns)
	{
		Iterator ite=this.conflictMethods.iterator();
    	while(ite.hasNext())
    	{
    		Operation tm=(Operation) ite.next();
    		if(tm.getTipo().equals("void"))
    			letrasConfVoid.add(tm.getPos());
    		else 
    			letrasConfNoVoid.add(tm.getPos());
    	}
    	//busco retornadores..
    	ite=this.c.getMethods().iterator();
    	while(ite.hasNext())
    	{
    		Operation m=(Operation) ite.next();
    		if(!m.getTipo().equals("void"))
    		{
    			letrasReturns.add(m.getPos());
    		}
    	}
	}
	
	
	
}

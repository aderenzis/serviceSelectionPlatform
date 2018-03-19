package pruebaDiego;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.domain.PrimitiveValue;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.TestMethod;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestValue;
import testooj3.domain.TestoojClassLoader;
import testooj3.domain.algorithms.Algorithm;
import testooj3.domain.algorithms.AllCombinationsElegant;
import testooj3.domain.tcmutation.TCMutatorSystem;
import testooj3.domain.testfiles.junit.JUnitFile;
import testooj3.gui.JDClassPathExplorer;
import testooj3.gui.JDListOfJUnitMethods;
import testooj3.gui.JDOperationsSelection;
import testooj3.gui.components.IMainWindow;
import testooj3.gui.components.JSPClassStructure;

public class PruebaJUNIT {

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
            
            System.out.println("la clase es: "+c.getName());
            System.out.println("sus metodos son: ");
            System.out.println("Constructor: ");
            for(int i=0; i<c.constructorsSize();i++)
            {
            	System.out.println(c.getConstructor(i).toString());
            }
            System.out.println("Metodos: ");
            for(int i=0; i<c.methodsSize();i++)
            {
            	System.out.println(c.getMethod(i).toString());
            }
            
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
            for (int i=0; i<c.getMethods().size(); i++) // les esta agregando el mismo parametro a todos los metodos...
            {
                Operation m=c.getMethod(i);
                if(m.getNombre().equalsIgnoreCase("deposit"))
                    for (int j=0; j<m.getParametros().size(); j++) {//repite una sola vez...
                        p=m.getParametro(j);
                        p.addTestValue(new PrimitiveValue("7"));
                        p.addTestValue(new PrimitiveValue("8"));
                        p.addTestValue(new PrimitiveValue("0"));
                    }
                if(m.getNombre().equalsIgnoreCase("withdraw"))
                    for (int j=0; j<m.getParametros().size(); j++) {//repite una sola vez...
                        p=m.getParametro(j);
                        p.addTestValue(new PrimitiveValue("88"));
                        p.addTestValue(new PrimitiveValue("24"));
                    }
                
            }
            
            
            
            
            //pedir valores para los parametros
            
            //pedir pre y post condiciones
//            mSelectedOperation.setPreassertions("");
//	        mSelectedOperation.setPostassertions("");
            /** PRE y POST*/
                 
            for (int i=0; i<c.getMethods().size(); i++) // les esta agregando el mismo parametro a todos los metodos...
            {
                Operation m=c.getMethod(i);
                if(m.getNombre().equalsIgnoreCase("deposit"))
                {
                	m.setPreassertions("String sal=obtained.getBalance();");
                    m.setPostassertions("assertTrue(sal==obtained.getBalance()+arg1);");
                }
                if(m.getNombre().equalsIgnoreCase("withdraw"))
                {
                	m.setPreassertions("String sal=obtained.getBalance();");
                	m.setPostassertions("assertTrue(sal==obtained.getBalance()-arg1);");
                }
                
            }
            
            
	        String path=Configuration.getInstance().getConstraintsPath();
//            c.saveDescription();
	        
            
            
            /** 
             * generando los tests
	             * public void testTS_0_1() {
					banco2.Account obtained=null;
					{
					obtained =new banco2.Account();
					}
					double arg1=(double) 50;
					{
					double saldo= obtained.getBalance();
					obtained.deposit(arg1);
					assertTrue(obtained.getBalance()==saldo+50);
					}
				}
 				
             	if (this.jrbBuildJunitFile.isSelected())
					this.buildJUnitFile();
             */
            
            /**cargo los templates*/
            
            if(c==null)
            	throw new Exception("There is not any class loaded");
            Vector tts=c.getTestTemplates(Integer.parseInt("5"),"A(B|C)");
            c.setTestTemplates(tts);
            
            /**construccion de los test*/
            //tomo a los templates generados con la expresion regular
            //TCMutatorSystem tiene las op con parametros conflictivas (deposit withdraw)
            TCMutatorSystem mutator=new TCMutatorSystem(c.getTestTemplates());
            
            Vector templates=mutator.buildMutants();
            for (int i=0; i<templates.size(); i++)//2 veces
                c.add((TestTemplate) templates.get(i));
            
            int[] numberOfTestCases=new int[1];
            Algorithm algorithm=new AllCombinationsElegant();
            String sessionPath=c.getTestCases(algorithm, TestMethod.JUNIT, numberOfTestCases);
            
//          showJUnitDialog(numberOfTestCases, TestMethod.JUNIT, sessionPath, null, null);
//            FileOutputStream testArch = new FileOutputStream ("E:\\Dropbox\\workspace\\TESTDIEGO\\test.txt");
//            DataOutputStream salida=new DataOutputStream(testArch);
//            salida
            
//           saveJUnitFile(numberOfTestCases, sessionType, sessionPath, compileFile, cut);
            
            int cont=1, lastTestCase=1;
            JUnitFile testFile;
            while (lastTestCase<=numberOfTestCases[0]) {
            	String junitFileName="Test" + Auxi.recorta(c.getName()) + cont;
                testFile=new JUnitFile(junitFileName, c);
                testFile.setSessionType(TestMethod.JUNIT);
                testFile.setSessionPath(sessionPath, numberOfTestCases[0]);
                String fileName=Configuration.getInstance().getResultsPath()+testFile.getFileName();
    	        FileOutputStream f=new FileOutputStream(fileName);	
    	        String regExp="// Regular expression: " + "\n";
                regExp+="// Max. length: " + "\n";
                f.write(regExp.getBytes());
                lastTestCase=testFile.saveTo(f, lastTestCase);
                f.close();
    	        
    	        String pathh=Configuration.getInstance().getResultsPath();
    	        //compile(path, fileName);
    	        
//    	        me.domain.Compiler compiler=new me.domain.Compiler(this);
//    	    	compiler.simpleCompile(path, "", fileName); //MODIFICADO POR ISRAEL
    	    	System.out.println("path :"+fileName);

    	    	
    	    	//    	        private void compile(String path, String cutName) {
//    	        	me.domain.Compiler compiler=new me.domain.Compiler(this);
//    	        	//compiler.simpleCompile(path, ".", cutName); //ORIGINAL
//    	        	compiler.simpleCompile(path, "", cutName); //MODIFICADO POR ISRAEL
//    	        	System.out.println(path+":"+cutName);
    	        
    	        cont++;
            }
//            this.log((cont-1) + " JUnit file(s) generated");
            Auxi.deleteDirectory(sessionPath, numberOfTestCases[0]);
            System.out.println();
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
	
}

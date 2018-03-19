package pruebaDiego;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import reducedTest.Campo;
import reducedTest.DoMethod;
import reducedTest.Efecto;
import reducedTest.EfectosCaso;
import reducedTest.Shadow;
import testooj3.domain.Configuration;
import testooj3.domain.PrimitiveValue;
import testooj3.domain.Interface;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestoojClassLoader;
import testooj3.gui.JDClassPathExplorer;

import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class PruebaShadow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
        	conflictivos.add("deposit");//void - con para
//        	conflictivos.add("getCliente");
        	conflictivos.add("getBalance");//Novoid - sin para (2 posibles)
        	conflictivos.add("clear");//void - sin para
//        	conflictivos.add("withdraw");
        	conflictivos.add("calcularCuota");//Novoid - con para (2 posibles)
        	
        	String metodoConflic;
        	/**mostramos interfaz de la clase*/
            System.out.println("la clase es: "+c.getName());
            System.out.println("sus metodos son: ");
            System.out.println("Constructor: ");
            for(int i=0; i<c.constructorsSize();i++)
            {
            	System.out.println(c.getConstructor(i).toString());
            }
            System.out.println("Metodos: ");
            ArrayList<Character> letrasConfVoid= new ArrayList<Character>();
            ArrayList<Character> letrasConfNoVoid= new ArrayList<Character>();
            ArrayList<Character> letrasReturns= new ArrayList<Character>();
            int posMP=0;
            
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
        	
        	
        	/**se debe analizar los tipos de metodos conflictivos de cada metodo de test*/
//        	JFPruebaShadow sh =new JFPruebaShadow();
//        	sh.setVisible(true);
        	
    	    Shadow sh= new Shadow(c.getName());
        	
    	    System.out.println("Es necesario que defina los campos que contiene la clase");
    	    boolean continuar=preguntarParaContinuar("¿Desea ingresar un campo?");
    	    String efecNombre;
    	    String efecTipo;
    	    while(continuar)
    	    {
    	    	System.out.println("Ingrese el nombre del campo");
    	    	efecNombre=TecladoIn.readLine();
    	    	System.out.println("Ingrese el tipo del campo");
    	    	efecTipo=TecladoIn.readLine();
    	    	Campo camp=new Campo(efecNombre,efecTipo);
    	    	sh.getCampos().add(camp);
    	    	continuar=preguntarParaContinuar("¿Desea ingresar otro campo?");
    	    }
    	    for (int i=0; i<c.getMethods().size(); i++) // les esta agregando el mismo parametro a todos los metodos...
            {
                Operation m=c.getMethod(i);
    	    	//si el  metodo es void entonces deberiamos solicitar cual seria la causa segun los parametros... un estado.
    	    	int p=0;
    	    
    	    	while(p<letrasConfVoid.size())//void
                {
	    	    	if(m.getPos()==letrasConfVoid.get(p))
	                {
	    	    		DoMethod dm=new DoMethod(m.getNombre(),m.getTipo());
	                	System.out.println("El metodo conflictivo "+m.toString()+" necesita que se le asignen sus efectos: ");
	                	Vector<EfectosCaso> efectosDM = new Vector<EfectosCaso>(); 
	                	if(m.getParametros().size()>0)//void - causa y efecto
	                	{
		                	boolean otroDatoDeTest=true;
		                	while(otroDatoDeTest)//dentro de un mismo metodo se ingresa cada caso de test o sea un DoMethod
			                {
		                		solicitarParametrosDelMetodo(m);
			                	//ahora solicito los efectos...
			                	
			                	Vector<Efecto> vectorEf=new Vector<Efecto>();
			                	System.out.println("Como el metodo es void debe ingresar al menos un efecto.");
	    	                	boolean otroEfecto=true;	                
	    	                	while(otroEfecto)//ingresamos los efectos de este caso...
	    	                	{
	    	                		Efecto ef=solicitarEfecto();
	    	                		vectorEf.add(ef);
	    	                		otroEfecto=preguntarParaContinuar("¿Desea ingresar otro efecto para le metodo "+m.getNombre()+"?");
	    	                	}
			                	
	    	                	EfectosCaso ec=new EfectosCaso(vectorEf);
	    	                	efectosDM.add(ec);
	    	                	
	    	                	//preguntamos si se desea agregar mas datos de test...
			                	otroDatoDeTest=preguntarParaContinuar("¿Desea otra causa-efecto para el metodo "+m.getNombre()+"?");
			                }
	                	}
	                	else //void - efecto
	                	{
		                	Vector<Efecto> vectorEf=new Vector<Efecto>();
		                	System.out.println("Como el metodo es void debe ingresar al menos un efecto.");
    	                	boolean otroEfecto=true;	                
    	                	while(otroEfecto)//ingresamos los efectos de este caso...
    	                	{
    	                		Efecto ef=solicitarEfecto();
    	                		vectorEf.add(ef);
    	                		otroEfecto=preguntarParaContinuar("¿Desea ingresar otro efecto para le metodo "+m.getNombre()+"?");
    	                	}
    	                	EfectosCaso ec=new EfectosCaso(vectorEf);
    	                	efectosDM.add(ec);
	                	}
	                	//una vez terminado de agregar todos los datos de los parametros
	                	dm.setParametros(m.getParametros());//agrego todos los parametros con los valores a la clase DoMethod
	                	dm.setEfectosCaso(efectosDM);
	                	sh.getMethods().add(dm);
	                }//fin if
	    	    	p++;
                }
                p=0;
                while(p<letrasConfNoVoid.size())
                {
	    	    	if(m.getPos()==letrasConfNoVoid.get(p))
	                {
	    	    		DoMethod dm=new DoMethod(m.getNombre(),m.getTipo());
//	    	    		System.out.println("El metodo conflictivo "+m.toString()+" necesita que se le asignen sus efectos: ");
	                	Vector<EfectosCaso> efectosDM = new Vector<EfectosCaso>();
	                	Vector<String> valores = new Vector<String>();
	                	System.out.println("la funcion  "+m.toString()+" tiene retorno.");
	                	if(m.getParametros().size()>0)//novoid - con parametros- causa
	                	{
	                		boolean otroDatoDeTest=true;
		                	while(otroDatoDeTest)//dentro de un mismo metodo se ingresa cada caso de test o sea un DoMethod
			                {
		                		solicitarParametrosDelMetodo(m);
			                	//pedimos el retorno...
		                		
		                    	if(preguntarPorEfectoOValor())//novoid - con parametros- causa y retorno efecto
		                    	{
		                    		System.out.println("Ingrese el efecto de tipo "+m.getTipo()+" a ser retornado:");
		                    		String valorRetorno=TecladoIn.readLine();
		                    		valores.add(valorRetorno);
		                    	}
		                    	else //novoid - con parametros- causa y retorno valor
		                    	{
		                    		System.out.println("Ingrese el valor de retorno esperado de tipo "+m.getTipo()+":");
		                    		String valorRetorno=TecladoIn.readLine();
		                    		valores.add(valorRetorno);
		                    	}
	    	                	//preguntamos si se desea agregar mas datos de test...
			                	otroDatoDeTest=preguntarParaContinuar("¿Desea ingresar otro caso de test para le metodo "+m.getNombre()+"?");
			                }
		                	//una vez terminado de agregar todos los datos de los parametros
		                	dm.setParametros(m.getParametros());//agrego todos los parametros con los valores a la clase DoMethod
		                	dm.setEfectosCaso(efectosDM);
//		                	dm.setValores(valores);
		                	sh.getMethods().add(dm);
		                }
	                	else	//novoid - sin parametros
	                	{
	                		if(preguntarPorEfectoOValor())//novoid - sin parametros- causa y retorno efecto
	                    	{
	                    		System.out.println("Ingrese el efecto de tipo "+m.getTipo()+" a ser retornado:");
	                    		String valorRetorno=TecladoIn.readLine();
	                    		valores.add(valorRetorno);
	                    	}
	                    	else //novoid - sin parametros- causa y retorno valor
	                    	{
	                    		System.out.println("Ingrese el valor de retorno esperado de tipo "+m.getTipo()+":");
	                    		String valorRetorno=TecladoIn.readLine();
	                    		valores.add(valorRetorno);
	                    	}
	                		dm.setParametros(m.getParametros());//agrego todos los parametros con los valores a la clase DoMethod
		                	dm.setEfectosCaso(efectosDM);
//		                	dm.setValores(valores);
		                	sh.getMethods().add(dm);
	                	}
	                }
	    	    	p++;
                }
    	    }
    	    System.out.println("****************************************");
    	    System.out.println(sh.toString());
    	    
    	    
        
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
	}

	private static boolean preguntarParaContinuar(String pregunta)
	{
		boolean otroDatoDeTest=false;
		char sigue;
    	System.out.println(pregunta);
    	System.out.println("Ingrese <S>i para aceptar, sino presione cualquier otra tecla.");
    	sigue=TecladoIn.readLineNonwhiteChar();
    	if(sigue=='S' || sigue=='s')
    		otroDatoDeTest=true;
    	else
    		otroDatoDeTest=false;
    	return otroDatoDeTest;
	}
	
	private static boolean preguntarPorEfectoOValor()
	{
		System.out.println("¿Desea ingresar un efecto o un valor a ser retornado?");
		boolean esEfecto=false;
		char efectoOValor;
    	System.out.println("Ingrese <E>fecto para retornar un efecto, presione cualquier otra tecla para ingresar un valor");
    	efectoOValor=TecladoIn.readLineNonwhiteChar();
    	if(efectoOValor=='E' || efectoOValor=='e')
    		esEfecto=true;
    	else
    		esEfecto=false;
    	return esEfecto;
	}
	
	private static Efecto solicitarEfecto()
	{
		System.out.println("Datos del efecto");
		System.out.println("nombre del \"field\" ");
		String nombreEf=TecladoIn.readLine();
		System.out.println("ingrese el \"valor\" ");
		String valorEf=TecladoIn.readLine();
		Efecto ef=new Efecto();//(nombreEf, valorEf);
		return ef;
	}
	
	private static void solicitarParametrosDelMetodo(Operation m)
	{
		if(m.getParametros().size()>0)
    		System.out.println("Ingrese los valores de las causas del metodo:");
    	for (int j=0; j<m.getParametros().size(); j++) {//lleno los parametros...
            TParameter para=m.getParametro(j);
            String tipoParametro=para.getTipo();
            System.out.println("Ingrese el valor para el parametro  de tipo \""+tipoParametro+" x"+(j+1)+"\": ");
            String valor=TecladoIn.readLine();
            para.addTestValue(new PrimitiveValue(valor));
        }
    	
	}
}  

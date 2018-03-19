package testooj3.compatibility.wsclient;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;




	public class Wsdl {
		public static Vector getArgs(String pathWsdl,String nombreMetodo){
			  String ns="";	
			  Vector args = new Vector();
				try
			      {
					
					
			         // Create the in memory model of services and operations
			         // defined in the current WSDL
			         ComponentBuilder builder = new ComponentBuilder();
			         List services = builder.buildComponents(pathWsdl);

			         // List all the services defined in the current WSDL
			         Iterator iter = services.iterator();
			       
			         while(iter.hasNext())
			         {
			            // Load each service into the services combobox model
			            ServiceInfo serviceInfo = (ServiceInfo)iter.next();
			            
				         // Load the operations model with operations defined for this service
				         Iterator iterOper = serviceInfo.getOperations();
				         
				         while(iterOper.hasNext())
				         {
				            // Load each service into the appropriate combo box model
				            OperationInfo operInfo = (OperationInfo)iterOper.next();
				   
				           // System.out.println("Operacion: "+operInfo.getTargetMethodName());
				            if((operInfo != null) && (operInfo.getTargetMethodName().toLowerCase().equals(nombreMetodo.toLowerCase())))
				            {
				               Map<String,String> map = operInfo.getParmType();
				               Iterator it = map.entrySet().iterator();
				               //System.out.println("operInfo: "+operInfo.getTargetMethodName());
				               //System.out.println("map.size(): "+map.size());
				               while (it.hasNext()) {
				               	Map.Entry e = (Map.Entry)it.next();
				               	System.out.println(e.getKey() + " " + e.getValue());
				               	args.add(e.getKey().toString());
				               }
				            }
		            
				         }
			            ns = serviceInfo.getName();
			         }
			         
			      }

			      catch(Exception e)
			      {
			         // Report the error to the user
			         System.err.println(e.getMessage());
			         e.printStackTrace();

			      }
			      return args;
		}
		public static String primeraUpper(String palabra){
			return palabra.substring(0,1).toUpperCase()+palabra.substring(1,palabra.length());
		}
		public static String primeraLower(String palabra){
			return palabra.substring(0,1).toLowerCase()+palabra.substring(1,palabra.length());
		}
		public static String nombreServicio(String nameWsdlFile){
			  String ns="";	
			try
		      {
		         // Create the in memory model of services and operations
		         // defined in the current WSDL
		         ComponentBuilder builder = new ComponentBuilder();
		         List services = builder.buildComponents(nameWsdlFile);

		         // List all the services defined in the current WSDL
		         Iterator iter = services.iterator();
		       
		         while(iter.hasNext())
		         {
		            // Load each service into the services combobox model
		            ServiceInfo serviceInfo = (ServiceInfo)iter.next();
		            ns = serviceInfo.getName();
		         }
		         
		      }

		      catch(Exception e)
		      {
		         // Report the error to the user
		         System.err.println(e.getMessage());
		         e.printStackTrace();

		      }
		      return ns;
		}
		
		public static Vector recuperaTipoRetorno(String pathWsdl,String nombreMetodo){
			String ns="";	
			  Vector args = new Vector();
			try
		      {
				
				
		         // Create the in memory model of services and operations
		         // defined in the current WSDL
		         ComponentBuilder builder = new ComponentBuilder();
		         List services = builder.buildComponents(pathWsdl);

		         // List all the services defined in the current WSDL
		         Iterator iter = services.iterator();
		       
		         while(iter.hasNext())
		         {
		            // Load each service into the services combobox model
		            ServiceInfo serviceInfo = (ServiceInfo)iter.next();
		            
			         // Load the operations model with operations defined for this service
			         Iterator iterOper = serviceInfo.getOperations();
			         
			         while(iterOper.hasNext())
			         {
			            // Load each service into the appropriate combo box model
			            OperationInfo operInfo = (OperationInfo)iterOper.next();
			            if((operInfo != null) && (operInfo.getTargetMethodName().toLowerCase().equals(nombreMetodo.toLowerCase())))
			            {
				            String retorno = operInfo.getTipoReturn();
				            System.out.println("retorno: "+retorno);
				            System.out.println("nombre Operación retorno: "+operInfo.getNombreOpRetorno());
				            args.add(0,retorno);
				            args.add(1,operInfo.getNombreOpRetorno());
			            }
			         }
		            ns = serviceInfo.getName();
		         }
		         
		      }

		      catch(Exception e)
		      {
		         // Report the error to the user
		         System.err.println(e.getMessage());
		         e.printStackTrace();

		      }
		      return args;
		}
		
		public static void MuestraElementosVector(Vector v){
			Enumeration enume=v.elements();
			int i=0;
	        while(enume.hasMoreElements()){
	            System.out.println("pos: "+i+" "+enume.nextElement());
	            i+=1;
	        }
		}
		
		public static void main(String[] args){
			String ns = nombreServicio("http://localhost:8080/axis2/services/Calculadora?wsdl");
			System.out.println("el nombre de servicio encontrado es: " + ns);
			Vector vec = getArgs("http://localhost:8080/axis2/services/Calculadora?wsdl","restar");
			//Vector vec =  recuperaTipoRetorno("http://www.w3schools.com/webservices/tempconvert.asmx?wsdl","FahrenheitToCelsius");
			MuestraElementosVector(vec);

					 
			//System.out.println("vec.get(0): "+Wsdl.primeraUpper((String)vec.get(0)));
			//boolean b = testooj3.compatibility.InterfacesCompatibilityChecker.isSubTyping(tipoParmCandidato, tipoParmOriginal);
			//System.out.println("testooj3.compatibility.InterfacesCompatibilityChecker.isSubTyping("+tipoParmCandidato+","+tipoParmOriginal+"): "+b);
		}
}

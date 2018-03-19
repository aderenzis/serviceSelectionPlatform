import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import edu.giisco.SoaML.metamodel.Interface;
import edu.uncoma.fai.WsdlToSoaML.parser.WsdlToSoaML;
import testooj3.compatibility.InterfacesCompatibilityChecker;

public class TestLucasExplosion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Crear objetos para los siguientes metodos 
//	    public boolean createUser(
//	            String firstname,
//	            String lastname,
//	            String email,
//	            String username,
//	            String password1,
//	            String password2,
//	            String country,
//	            long birthyear,
//	            long birthmonth,
//	            long birthday) 
//
//
	
//		Interface requiredInterface = new Interface();
//		
////      public boolean login(String username, String password)
//		Operation login = new Operation();
//		login.setId(1);
//		login.setName("login");
//		Parameter usernameLogIn = new Parameter();
//		usernameLogIn.setId(1);
//		usernameLogIn.setName("username");
//		SimpleType string = new SimpleType(SimpleType.STRING);
//		usernameLogIn.setType(string);
//		
//		Parameter password = new Parameter();
//		password.setId(2);
//		password.setName("password");
//		password.setType(string);
//	
//		login.addParameter(usernameLogIn);
//		login.addParameter(password);
//		
//		Response loginResponse = new Response("loginOk", new SimpleType(SimpleType.BOOLEAN));
//		login.setResponse(loginResponse);
//		
////      public boolean logout(String username, String password)
//		Operation logout= new Operation();
//		logout.setId(2);
//		logout.setName("logout");
//		
//		Parameter usernameLogOut= new Parameter();
//		usernameLogOut.setId(3);
//		usernameLogOut.setName("username");
//		usernameLogOut.setType(string);
//		
//		Parameter passwordLogOut = new Parameter();
//		passwordLogOut.setId(3);
//		passwordLogOut.setName("username");
//		passwordLogOut.setType(string);
//		logout.addParameter(usernameLogOut);
//		logout.addParameter(passwordLogOut);
//		
//		Response logoutResponse = new Response("logoutOK", new SimpleType(SimpleType.BOOLEAN));
//		logout.setResponse(logoutResponse);
//	
//		//public boolean sendMessageTo(String username, String password, String receiver, String message)
//		
//		Operation sendMessageTo = new Operation();
//		sendMessageTo.setId(3);
////		prueba 1
////		sendMessageTo.setName("sendMessageTo");
//		sendMessageTo.setName("sendMailTo"); //baja de -2 a -1.75 por ser sinónimo
//		
//		Parameter usernameSendMessageTo = new Parameter();
//		usernameSendMessageTo.setId(4);
//		usernameSendMessageTo.setName("username");
//		usernameSendMessageTo.setType(string);
//		sendMessageTo.addParameter(usernameSendMessageTo);
//		
//		Parameter passwordSendMessageTo = new Parameter();
//		passwordSendMessageTo.setId(5);
//		passwordSendMessageTo.setName("password");
//		passwordSendMessageTo.setType(string);
//		sendMessageTo.addParameter(passwordSendMessageTo);
//		
//		Parameter receiverSendMessageTo = new Parameter();
//		receiverSendMessageTo.setId(6);
//		receiverSendMessageTo.setName("reciver");
//		receiverSendMessageTo.setType(string);
//		sendMessageTo.addParameter(receiverSendMessageTo);
//		
//		Parameter messageSendMessageTo = new Parameter();
//		messageSendMessageTo.setId(7);
//		messageSendMessageTo.setName("message");
//		messageSendMessageTo.setType(string);
//		sendMessageTo.addParameter(messageSendMessageTo);
//		
//		Response sendMessageToResponse = new Response("sentMessageOK", new SimpleType(SimpleType.BOOLEAN));
//		sendMessageTo.setResponse(sendMessageToResponse);
// 
////      public String receiveNextMessage(String username, String password)
//
//		Operation receiveNextMessage = new Operation();
//		receiveNextMessage.setId(4);
//		receiveNextMessage.setName("receiveNextMessage");
//		Parameter usernameReceiveNextMessage = new Parameter();
//		usernameReceiveNextMessage.setId(1);
//		usernameReceiveNextMessage.setName("username");
//		usernameReceiveNextMessage.setType(string);
//		
//		Parameter passwordReceiveNextMessage = new Parameter();
//		passwordReceiveNextMessage.setId(2);
//		passwordReceiveNextMessage.setName("password");
//		passwordReceiveNextMessage.setType(string);
//	
//		receiveNextMessage.addParameter(usernameReceiveNextMessage);
//		receiveNextMessage.addParameter(passwordReceiveNextMessage);
//		
//		Response ReceiveNextMessageResponse = new Response("messageOk", new SimpleType(SimpleType.STRING));
//		receiveNextMessage.setResponse(ReceiveNextMessageResponse);
//
////		public void addUser(User user);
////		Donde user es: 
////		String firstname,String lastname,String email,String username,String password1,String password2,String country,
////		long birthyear,long birthmonth,long birthday
//		Operation addUser = new Operation();
//		addUser.setId(5);
//		addUser.setName("addUser");
//		Parameter user = new Parameter();
//		user.setId(9);
//		user.setName("user");
//		Element firstname= new Element(); firstname.setId(9); firstname.setName("fName"); firstname.setType(string);
//		Element lastname= new Element(); lastname.setId(10); lastname.setName("lName"); lastname.setType(string);
//		Element email= new Element(); email.setId(11); email.setName("email"); email.setType(string);
//		Element password1= new Element(); password1.setId(12); password1.setName("key1"); password1.setType(string);
//		Element password2= new Element(); password2.setId(13); password2.setName("key2"); password2.setType(string);
//		Element country= new Element(); country.setId(14); country.setName("country"); country.setType(string);
//		Element birthyear= new Element(); birthyear.setId(15); birthyear.setName("birthyear"); birthyear.setType(new SimpleType(SimpleType.LONG));
//		Element birthmonth= new Element(); birthmonth.setId(16); birthmonth.setName("birthmonth"); birthmonth.setType(new SimpleType(SimpleType.LONG));
//		Element birthday= new Element(); birthday.setId(17); birthday.setName("birthday"); birthday.setType(new SimpleType(SimpleType.LONG));
//		ArrayList<Element> elementsUser= new ArrayList<Element>();
//		elementsUser.add(firstname);elementsUser.add(lastname);elementsUser.add(email);elementsUser.add(password1);
//		elementsUser.add(password2);
////		elementsUser.add(country);elementsUser.add(birthyear);elementsUser.add(birthmonth);
//		elementsUser.add(birthday);		
//		ComplexType userType= new ComplexType();
//		userType.setId(0);
//		userType.setName("User");
//		userType.setElements(elementsUser);
//		user.setType(userType);
//		addUser.addParameter(user);
//		
//		requiredInterface.setId(0);
//		requiredInterface.setName("chatService");
//		ArrayList<Operation> operations= new ArrayList<Operation>();
//		operations.add(addUser);
//		operations.add(login);operations.add(logout);operations.add(sendMessageTo);operations.add(receiveNextMessage);
//		requiredInterface.setOperations(operations);
//		SimpleType voidd = new SimpleType(SimpleType.ANY_TYPE);
//		Response addUserResponse = new Response("void", voidd);
//		addUser.setResponse(addUserResponse);
//		
//		
//		
//	Interface serviceInterface = new Interface();
//		
////      public boolean login(String username, String password)
//		Operation login1 = new Operation();
//		login1.setId(1);
//		login1.setName("login");
//		Parameter usernameLogIn1 = new Parameter();
//		usernameLogIn1.setId(1);
//		usernameLogIn1.setName("username");
//		usernameLogIn1.setType(string);
//		
//		Parameter password1P = new Parameter();
//		password1P.setId(2);
//		password1P.setName("password");
//		password1P.setType(string);
//	
//		login1.addParameter(usernameLogIn1);
//		login1.addParameter(password1P);
//		
//		Response loginResponse1 = new Response("loginOk", new SimpleType(SimpleType.BOOLEAN));
//		login1.setResponse(loginResponse1);
//		
////      public boolean logout(String username, String password)
//		Operation logout1= new Operation();
//		logout1.setId(2);
//		logout1.setName("logout");
//		
//		Parameter usernameLogOut1= new Parameter();
//		usernameLogOut1.setId(3);
//		usernameLogOut1.setName("username");
//		usernameLogOut1.setType(string);
//		
//		Parameter passwordLogOut1 = new Parameter();
//		passwordLogOut1.setId(3);
//		passwordLogOut1.setName("username");
//		passwordLogOut1.setType(string);
//		logout1.addParameter(usernameLogOut1);
//		logout1.addParameter(passwordLogOut1);
//		
//		Response logoutResponse1 = new Response("logoutOK", new SimpleType(SimpleType.BOOLEAN));
//		logout1.setResponse(logoutResponse1);
//	
//		//public boolean sendMessageTo(String username, String password, String receiver, String message)
//		
//		Operation sendMessageTo1 = new Operation();
//		sendMessageTo1.setId(3);
//		sendMessageTo1.setName("sendMessageTo");
//		
//		Parameter usernameSendMessageTo1 = new Parameter();
//		usernameSendMessageTo1.setId(4);
//		usernameSendMessageTo1.setName("username");
//		usernameSendMessageTo1.setType(string);
//		sendMessageTo1.addParameter(usernameSendMessageTo1);
//		
//		Parameter passwordSendMessageTo1 = new Parameter();
//		passwordSendMessageTo1.setId(5);
//		passwordSendMessageTo1.setName("password");
//		passwordSendMessageTo1.setType(string);
//		sendMessageTo1.addParameter(passwordSendMessageTo1);
//		
//		Parameter receiverSendMessageTo1 = new Parameter();
//		receiverSendMessageTo1.setId(6);
//		receiverSendMessageTo1.setName("reciver");
//		receiverSendMessageTo1.setType(string);
//		sendMessageTo1.addParameter(receiverSendMessageTo1);
//		
//		Parameter messageSendMessageTo1 = new Parameter();
//		messageSendMessageTo1.setId(7);
//		messageSendMessageTo1.setName("message");
//		messageSendMessageTo1.setType(string);
//		sendMessageTo1.addParameter(messageSendMessageTo1);
//		
//		Response sendMessageToResponse1 = new Response("sentMessageOK", new SimpleType(SimpleType.BOOLEAN));
//		sendMessageTo1.setResponse(sendMessageToResponse1);
// 
////      public String receiveNextMessage(String username, String password)
//
//		Operation receiveNextMessage1 = new Operation();
//		receiveNextMessage1.setId(4);
//		receiveNextMessage1.setName("receiveNextMessage");
//		Parameter usernameReceiveNextMessage1 = new Parameter();
//		usernameReceiveNextMessage1.setId(1);
//		usernameReceiveNextMessage1.setName("username");
//		usernameReceiveNextMessage1.setType(string);
//		
//		Parameter passwordReceiveNextMessage1 = new Parameter();
//		passwordReceiveNextMessage1.setId(2);
//		passwordReceiveNextMessage1.setName("password");
//		passwordReceiveNextMessage1.setType(string);
//	
//		receiveNextMessage1.addParameter(usernameReceiveNextMessage1);
//		receiveNextMessage1.addParameter(passwordReceiveNextMessage1);
//		
//		Response ReceiveNextMessageResponse1 = new Response("messageOk", new SimpleType(SimpleType.STRING));
//		receiveNextMessage1.setResponse(ReceiveNextMessageResponse1);
//
////		public void addUser(User user);
////		Donde user es: 
////		String firstname,String lastname,String email,String username,String password1,String password2,String country,
////		long birthyear,long birthmonth,long birthday
//		Operation addUser1 = new Operation();
//		addUser1.setId(5);
//		addUser1.setName("JOseOrosco");
//		Parameter user1 = new Parameter();
//		user1.setId(9);
//		user1.setName("user");
//		Element firstname1= new Element(); firstname1.setId(9); firstname1.setName("fisrtName"); firstname1.setType(string);
//		Element lastname1= new Element(); lastname1.setId(10); lastname1.setName("lastName"); lastname1.setType(string);
//		Element email1= new Element(); email1.setId(11); email1.setName("email"); email1.setType(string);
//		Element password11= new Element(); password11.setId(12); password11.setName("password1"); password11.setType(string);
//		Element password21= new Element(); password21.setId(13); password21.setName("password2"); password21.setType(string);
//		Element country1= new Element(); country1.setId(14); country1.setName("country"); country1.setType(string);
//		Element birthyear1= new Element(); birthyear1.setId(15); birthyear1.setName("birthyear"); birthyear1.setType(new SimpleType(SimpleType.LONG));
//		Element birthmonth1= new Element(); birthmonth1.setId(16); birthmonth1.setName("birthmonth"); birthmonth1.setType(new SimpleType(SimpleType.LONG));
//		Element birthday1= new Element(); birthday1.setId(17); birthday1.setName("birthday"); birthday1.setType(new SimpleType(SimpleType.LONG));
//		ArrayList<Element> elementsUser1= new ArrayList<Element>();
//		elementsUser1.add(firstname1);elementsUser1.add(lastname1);elementsUser1.add(email1);elementsUser1.add(password11);
//		elementsUser1.add(password21);elementsUser1.add(country1);elementsUser1.add(birthyear1);elementsUser1.add(birthmonth1);
//		elementsUser1.add(birthday1);		
//		ComplexType userType1= new ComplexType();
//		userType1.setId(0);
//		userType1.setName("Pepe");
//		userType1.setElements(elementsUser1);
//		user1.setType(userType1);
//		addUser1.addParameter(user1);
//		
//		serviceInterface.setId(0);
//		serviceInterface.setName("chatService");
//		ArrayList<Operation> operations1= new ArrayList<Operation>();
//		operations1.add(addUser1);operations1.add(login1);operations1.add(logout1);operations1.add(sendMessageTo1);operations1.add(receiveNextMessage1);
//		serviceInterface.setOperations(operations1);
//		Response addUserResponse1 = new Response("void", voidd);
//		addUser1.setResponse(addUserResponse1);
//		
		
//		try {
//			Interface requiredInterface= WsdlToSoaML.createSoaMLInterface("C:\\Users\\lenovo1\\Desktop\\absolutedrinks Original.wsdl");
//			Interface serviceInterface= WsdlToSoaML.createSoaMLInterface("C:\\Users\\lenovo1\\Desktop\\\\absolutedrinks Candidato.wsdl");
//			InterfacesCompatibilityChecker icc = new InterfacesCompatibilityChecker();
//			icc.setRequiredInterface(requiredInterface);
//			icc.setServiceInterface(serviceInterface);
//			icc.run();
//			Hashtable compatibilities = icc.getCompatibilities();
//			//System.out.println(compatibilities.size());
//			//System.out.println(compatibilities.get(1));
//			//System.out.println(icc.getAdaptabilityGap());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		runExperiment("C:\\pruebasEclipse\\Convertidos\\");
		
	}
	
	public static void runExperiment(String path) {
		ArrayList <Interface > wdslInterfaces = new ArrayList<>();
		// Creacion de archivo resultado.txt
		File fResult;
		fResult = new File("C:\\Users\\lenovo1\\Documents\\WSDLtoSOAML20160530.txt");
		// Escritura
		try {
			FileWriter w = new FileWriter(fResult);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			wr.write("");// escribimos en el archivo
			// Directorio que vas a analizar.. El que contiene los documentos WSDL
			String pathConvertedTo20= path;
			File dir = new File(pathConvertedTo20);
			String[] files = dir.list();
			if (files == null)
				System.out.println("No files into the specified folder");
			else {
				int countErrors = 0;
				File[] fileList = dir.listFiles();

				for (int i = 0; i < fileList.length; i++) {
					File fileAux = fileList[i];

					System.out.println("Analizando " + i + "  de  "
							+ fileList.length + "  --- Errors: " + countErrors
							+ " -- " + fileAux.getName());
					try {
						String fileName = fileAux.getName();
						//Interface soaMLInterface = WsdlToSoaML.createSoaMLInterface(pathConvertedTo20+"yotpo.wsdl2");
//						Interface soaMLInterface = WsdlToSoaML.createSoaMLInterface(pathConvertedTo20+"yourmapper.wsdl2");
						Interface soaMLInterface = WsdlToSoaML.createSoaMLInterface(pathConvertedTo20+fileName);
						wdslInterfaces.add(soaMLInterface);
						wr.append(soaMLInterface.toString());
						wr.append("\n\n--------------------------------------------------------------------------------------\n\n");
					}
					catch (Exception e) {
						e.printStackTrace();
						countErrors++;
						wr.append(fileAux.getName() + "  -  "+ e.getStackTrace() + "\n");
					}
				}
			}
			wr.close();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		int errorCount =0;int cantComparation=0;
		for (Interface originalInterface: wdslInterfaces)
		{
			for(Interface serviceInterface: wdslInterfaces)
			{
				InterfacesCompatibilityChecker icc;
				try {
					cantComparation++;
					System.out.println("\nNúmero de errores hasta el momento: "+errorCount+ " Comparaciones: "+cantComparation); 
					icc = new InterfacesCompatibilityChecker();
					icc.setRequiredInterface(originalInterface);
					icc.setServiceInterface(serviceInterface);
					System.out.println("\nOriginal:"+originalInterface.getName()+ "\nCandidato:"+serviceInterface.getName());
					icc.run();
					
					Hashtable compatibilities = icc.getCompatibilities();
					//System.out.println(compatibilities.size());
					//System.out.println(compatibilities.get(1));
					//System.out.println(icc.getAdaptabilityGap());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorCount++;
					e.printStackTrace();
				}
				catch(Error e)
				{
					errorCount++;
					e.printStackTrace();
				}
			}
		}
		System.out.println(errorCount);
	}
	
}

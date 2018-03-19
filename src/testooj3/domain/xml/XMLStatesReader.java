package testooj3.domain.xml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.states.DetailedDescription;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.Transition;
import testooj3.domain.states.exceptions.TransitionExistException;
import testooj3.domain.states.exceptions.TransitionUnsupportedException;

public class XMLStatesReader 
{
  private Document mDoc;

  public XMLStatesReader(String fileName) throws ParserConfigurationException, IOException, SAXException
  {
    DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
    File f=new File(fileName); 
    mDoc=db.parse(f);
  }
   
  public StateMachine getDetailedStateMachine() 
  {
    StateMachine result=new StateMachine();
    Node raiz=mDoc.getFirstChild();
    NodeList hijos=raiz.getChildNodes();
    for (int i=0; i<hijos.getLength(); i++) 
    {
      Node tc=hijos.item(i);
      String testCaseName=processTestCase(result, tc);
      System.out.println(i + ") " + testCaseName);
      System.out.println("-----------------");
      Enumeration e=result.getStates().elements();
      while (e.hasMoreElements()) {
    	  State st=(State) e.nextElement();
    	  System.out.println("\tEstado: "+ st.getName());
      }
      System.out.println("\n");
    }
    return result;
  }
  
  protected String processTestCase(StateMachine result, Node tc) {
	  String testCaseName=tc.getNodeName();
	  Node constructorNode=tc.getFirstChild();
	  State lastDetailedState=processConstructorNode(result, constructorNode);
	  Node currentNode;
	  if (constructorNode.getNextSibling()!=null)
		  currentNode=constructorNode.getNextSibling();
	  else return testCaseName;
	  Node methodNode=currentNode.getNextSibling();
	  while (methodNode!=null) {
		  lastDetailedState=processMethodNode(lastDetailedState, result, methodNode);
		  methodNode=methodNode.getNextSibling();
	  }
	  return testCaseName;
  }
  
  protected State processMethodNode(State previousDetailedState, StateMachine result, Node methodNode) { 
	  String operationName=methodNode.getAttributes().getNamedItem("name").getNodeValue();
	  Node sourceStateNode=methodNode.getFirstChild();
	  String sourceSuperStateName=sourceStateNode.getAttributes().getNamedItem("name").getNodeValue();

	  Node parameterNode=sourceStateNode.getNextSibling();
	  Vector parValues=new Vector(), parTypes=new Vector();
	  while (parameterNode.getNodeName().equals("parameter")) {
		  parTypes.add(parameterNode.getAttributes().getNamedItem("type").getNodeValue());
		  parValues.add(parameterNode.getAttributes().getNamedItem("value").getNodeValue());
		  parameterNode=parameterNode.getNextSibling();
	  }

	  Node targetStateNode=parameterNode;
	  String targetSuperStateName=targetStateNode.getAttributes().getNamedItem("name").getNodeValue();
	  boolean isExceptionNode=Boolean.parseBoolean(targetStateNode.getAttributes().getNamedItem("exception").getNodeValue());		
	  if (isExceptionNode) {
		  State superTargetState=result.add(targetSuperStateName);
		  //TOperation operation=new TMethod(operationName,null,parTypes,0);
		  TOperation operation = new Operation(operationName,null,parTypes,parValues,0);
		  Transition t=new Transition(previousDetailedState, superTargetState,operation,parValues);
		  try {
			result.addTransition(t);
		  } catch (Exception e) {
			//e.printStackTrace();
		  }
		  return superTargetState;
	  }
	  Node detailedState=targetStateNode.getNextSibling();
	  String targetDetailedStateName="";
	  Vector<DetailedDescription> detailedDescriptions=new Vector<DetailedDescription>();
	  for (int i=0; i<detailedState.getChildNodes().getLength(); i++) 
	  {
		  Node observerMethod=detailedState.getChildNodes().item(i);
		  String osName=observerMethod.getAttributes().getNamedItem("name").getNodeValue();
		  String osValue=observerMethod.getAttributes().getNamedItem("value").getNodeValue();
		  DetailedDescription detailedDescription=new DetailedDescription(osName, osValue);
		  String description=osName + "=" + osValue;
		  targetDetailedStateName+=description + " and ";
		  detailedDescriptions.add(detailedDescription);
	  } 
	  if (targetDetailedStateName.length()>0)
		  targetDetailedStateName=targetDetailedStateName.substring(0, targetDetailedStateName.length()-5);
	  State targetDetailedState=result.add(targetDetailedStateName);
	  targetDetailedState.setSuperstateName(targetSuperStateName);
	  
	  //TOperation operation=new TMethod(operationName,null,parTypes,0);
	  TOperation operation = new Operation(operationName,null,parTypes,parValues,0);
	  Transition t=new Transition(previousDetailedState, targetDetailedState,operation,parValues);
	  
	  try {
		result.addTransition(t);
	} catch (Exception e) {
		//e.printStackTrace();
	}
	  targetDetailedState.setDetailedDescriptions(detailedDescriptions);
	  return targetDetailedState;
  }
  
  protected State processConstructorNode(StateMachine result, Node constructorNode) {
      String sourceSuperStateName="init";
      Vector parValues=new Vector(), parTypes=new Vector();;
      int j=0;
      for (j=0; j<constructorNode.getChildNodes().getLength()-1; j++) 
      {
        Node parameter=constructorNode.getChildNodes().item(j);
        parTypes.add(parameter.getAttributes().getNamedItem("type").getNodeValue());
        parValues.add(parameter.getAttributes().getNamedItem("value").getNodeValue());
      }
      Node targetStateNode=constructorNode.getChildNodes().item(j);
      String targetSuperStateName=targetStateNode.getAttributes().getNamedItem("name").getNodeValue();
      boolean isExceptionNode=Boolean.parseBoolean(targetStateNode.getAttributes().getNamedItem("exception").getNodeValue());

      State superSourceState=result.add(sourceSuperStateName);      
      if (isExceptionNode) {
          State superTargetState=result.add(targetSuperStateName);    	
          
          //TOperation operation=new TConstructor("Constructor",parTypes,0);
          TOperation operation = new TConstructor("Constructor",parTypes,parValues,0);
    	  Transition t=new Transition(superSourceState, superTargetState,operation,parValues);
    	 
    	  try {
			result.addTransition(t);
		} catch (Exception e) {
			//e.printStackTrace();
		}
    	  return superTargetState;
      }
      Node detailedState=constructorNode.getNextSibling();
      String targetSubStateName="";
      Vector<DetailedDescription> detailedDescriptions=new Vector<DetailedDescription>();
      for (j=0; j<detailedState.getChildNodes().getLength(); j++) 
      {
        Node observerMethod=detailedState.getChildNodes().item(j);
        String osName=observerMethod.getAttributes().getNamedItem("name").getNodeValue();
		String osValue=observerMethod.getAttributes().getNamedItem("value").getNodeValue();
		DetailedDescription detailedDescription=new DetailedDescription(osName, osValue);
		String description=osName + "=" + osValue;
        targetSubStateName+=description + " and ";
        detailedDescriptions.add(detailedDescription);
      }
      if (targetSubStateName.length()>0) 
    	  targetSubStateName=targetSubStateName.substring(0, targetSubStateName.length()-5);
      State subTargetDetailedState=result.add(targetSubStateName);
      subTargetDetailedState.setSuperstateName(targetSuperStateName);
      
      //TOperation operation=new TConstructor("Constructor",parTypes,0);
      TOperation operation = new TConstructor("Constructor",parTypes,parValues,0);
	  Transition t=new Transition(superSourceState, subTargetDetailedState,operation,parValues);
	  
      try {
		result.addTransition(t);
	} catch (Exception e) {
		//e.printStackTrace();
	}
      subTargetDetailedState.setDetailedDescriptions(detailedDescriptions);
      return subTargetDetailedState;      
  }

	public Vector getTestCases() {
		Node raiz=mDoc.getFirstChild();
	    NodeList hijos=raiz.getChildNodes();
	    Vector result=new Vector();
		for (int i=0; i<hijos.getLength(); i++) 
	    {
	      Node tc=hijos.item(i);
	      String tcName=tc.getNodeName();
	      result.add(tcName);
	    }
	    return result;
	}

	public StateMachine getStateMachine(int index) {
		StateMachine result = new StateMachine();
		Node raiz = mDoc.getFirstChild();
		NodeList hijos = raiz.getChildNodes();
		Node tc = hijos.item(index);
		String testCaseName = processTestCase(result, tc);
		System.out.println(index + ") " + testCaseName);
		System.out.println("-----------------");
		Enumeration e = result.getStates().elements();
		while (e.hasMoreElements()) {
			State st = (State) e.nextElement();
			System.out.println("\t" + st.getName());
		}
		System.out.println("\n");
		return result;
	}
  
}
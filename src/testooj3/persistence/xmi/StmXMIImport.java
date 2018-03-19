package testooj3.persistence.xmi;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import testooj3.domain.Interface;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.states.ChoiceState;
import testooj3.domain.states.Condition;
import testooj3.domain.states.ITransition;
import testooj3.domain.states.InitialState;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.Transition;
import testooj3.domain.states.TransitionFromChoice;
import testooj3.domain.states.TransitionToChoice;
import testooj3.domain.states.exceptions.TransitionExistException;
import testooj3.domain.states.exceptions.TransitionUnsupportedException;

/**
 * @author  andres
 */
public class StmXMIImport {
	
	protected StateMachine stm;
	protected String xmiPath;
	protected Document mDoc;
	private Interface c;
	
	private HashMap <String,State> states;
	private Vector <ITransition> transitions;
	
	
	public StmXMIImport(String xmiPath, Interface c) throws ParserConfigurationException, SAXException, IOException{
		this.xmiPath=xmiPath;
		this.stm=null;
		this.states=new HashMap<String, State>();
		this.transitions=new Vector<ITransition>();
		this.c=c;
		
		DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    File f=new File(xmiPath); 
	    mDoc=db.parse(f);
	}
	
	public StateMachine getStm() throws TransitionExistException, TransitionUnsupportedException{
		if (this.stm==null){
			loadStm();
		}
		return this.stm;
	}

	protected void loadStm() throws TransitionExistException, TransitionUnsupportedException {
		this.stm=new StateMachine();
		Node stmNode=getStmNode();
		this.loadStates(stmNode);
		this.loadTransitions( stmNode);		
	}

	private void loadTransitions(Node stmNode) throws TransitionExistException, TransitionUnsupportedException {
		Node aux=null;
		for (int i=0; i<stmNode.getChildNodes().getLength();i++){
			aux=stmNode.getChildNodes().item(i);
			if (aux!=null && aux.getNodeName().equalsIgnoreCase("transition")){
				this.transitions.add(createTransition(aux));
			}else{
				loadTransitions(aux);
			}
			/*else if (aux.getNodeName().equalsIgnoreCase("transition")){
				createTransition(aux);
			}*/
		}
	}

	/**Nuestra máquina de estados no permite tener estados con el mismo
	 * nombre, ya que lo utilizamos como identificador. 
	 * @throws TransitionUnsupportedException 
	 * @throws TransitionExistException */
	private ITransition createTransition(Node aux) throws TransitionExistException, TransitionUnsupportedException {
		NamedNodeMap atribs = aux.getAttributes();
		String sourceId = atribs.getNamedItem("source").getNodeValue();
		String targetId =atribs.getNamedItem("target").getNodeValue();
		State source=(State) this.states.get(sourceId);
		State target=(State) this.states.get(targetId);
		ITransition it;
		if (source instanceof ChoiceState){
			it=new TransitionFromChoice(source,target);
			((TransitionFromChoice)it).addConditions(getConditions(aux.getChildNodes()));
		}else{
			it=new Transition(source,target,getEvent(aux),null);
			if (target instanceof ChoiceState)
				it=new TransitionToChoice(source,target,(Transition) it);
		}
		//=new Transition(source,target,event,params);
		this.stm.addTransition(it);
		return it;
	}

	//Devuelve una lista de condiciones a partir de los hijos de la transicion que se pasan como arg
	private Condition getConditions(NodeList childNodes) {
		// TODO Auto-generated method stub
		Node aux1,aux2;
		Condition result=new Condition();
		for (int i=0; i<childNodes.getLength();i++){
			aux1=childNodes.item(i);
			if (aux1.getNodeName().equalsIgnoreCase("guard"))
				for (int j=0; j<aux1.getChildNodes().getLength();j++){
					aux2=aux1.getChildNodes().item(j);
					if (aux2.getNodeName().equalsIgnoreCase("specification"))
						result.add(aux2.getAttributes().getNamedItem("value").getNodeValue());
				}
		}	
		return result;
	}

	private TOperation getEvent(Node aux) {
		NodeList hijos = aux.getChildNodes();
		for (int i=0; i<hijos.getLength(); i++)
			if (hijos.item(i).getNodeName().equalsIgnoreCase("trigger"))
				return buildMethodFromId(XMIUtils.getIdRef(hijos.item(i)));
		return null;
	}

	private TOperation buildMethodFromId(String id) {
		return this.c.getOperation(id);
	}

	/**Como nuestra máquina de estados no tiene estados submáquina,
	 * en el momento en que se encuentra un estado para la recursividad*/
	private void  loadStates(Node stmNode) {
		Node aux=null;
		for (int i=0; i<stmNode.getChildNodes().getLength();i++){
			aux=stmNode.getChildNodes().item(i);
			if (aux.getNodeName().equalsIgnoreCase("subvertex")){
				State stAux=createState(aux);
				this.states.put(getXMIid(aux), stAux);
				this.stm.add(stAux);
			}else{
				loadStates(aux);
			}
		}
		
	}

	private String getXMIid(Node aux) {
		return aux.getAttributes().getNamedItem("xmi:id").getNodeValue();
	}

	private State createState(Node aux) {
		NamedNodeMap atribs = aux.getAttributes();
		String tipo = XMIUtils.getType(aux);
		String nombre=XMIUtils.getName(aux);
		State st=null;
		if (tipo.equalsIgnoreCase("uml:Pseudostate")){
			Node subTipo = atribs.getNamedItem("kind");
			if (subTipo==null || subTipo.getNodeValue().equalsIgnoreCase("init")){
				st=new InitialState();
			}else if (subTipo.getNodeValue().equalsIgnoreCase("choice")){
				st=new ChoiceState();
				st.setName(nombre);
			}else{
				st=new State(nombre, getStateDescription(aux));
			}
		}else{
			st=new State(nombre, getStateDescription(aux));
		}
		return st;
	}

	private String getStateDescription(Node aux) {
		NodeList descrips = aux.getChildNodes();
		Node dsAux=null;
		String result=" ";
		for (int i=0;descrips!=null && i<descrips.getLength();i++){
			dsAux=descrips.item(i).getAttributes().getNamedItem("name");
			if (dsAux!=null)
				result+=dsAux.getNodeValue()+" , ";
		}
		result.substring(0, result.length()-1);
		return result;
	}

	protected Node getStmNode() {
		return getStmNode(mDoc.getFirstChild());
	}
	
	private Node getStmNode(Node stmNode){
		if (isStmNode(stmNode)){
			return stmNode;
		}else{
			NodeList hijos = stmNode.getChildNodes();
			int numHijos=hijos.getLength();
			if (hijos!=null && numHijos>0){
				for (int i=0; i<numHijos; i++){
					Node aux=getStmNode(hijos.item(i));
					if  (aux!=null)
						return aux;
				}
				return null;
			}else{
				return null;
			}
				
		}
	}

	private boolean isStmNode(Node stmNode) {
		NamedNodeMap atribs=stmNode.getAttributes();
		Node tipo=atribs.getNamedItem("xmi:type");
		return tipo!=null && tipo.getNodeValue().equalsIgnoreCase("uml:StateMachine");
	}
	

}

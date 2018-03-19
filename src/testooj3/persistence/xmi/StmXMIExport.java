package testooj3.persistence.xmi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.TField;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.states.ChoiceState;
import testooj3.domain.states.Condition;
import testooj3.domain.states.ITransition;
import testooj3.domain.states.InitialState;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.TransitionFromChoice;

/**
 * Esta clase esta limitada a una nica mquina de estados, sin permitir submquinas ni estados finales
 */

public class StmXMIExport {
	private Document doc;

	private StateMachine stm;

	private String fileName;

	private Interface c;

	public StmXMIExport(String fileName, StateMachine stm, Interface c)
			throws ParserConfigurationException, IOException, SAXException {
		this.fileName = fileName;
		this.stm = stm;
		this.c = c;

		try {
			// Find the implementation
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation impl = builder.getDOMImplementation();

			// Create the document
			this.doc = impl.createDocument(
					"http://schema.omg.org/spec/XMI/2.1", "xmi:XMI", null);

			init();

		} catch (FactoryConfigurationError e) {
			System.out.println("Could not locate a JAXP factory class");
		} catch (ParserConfigurationException e) {
			System.out.println("Could not locate a JAXP DocumentBuilder class");
		} catch (DOMException e) {
			System.err.println(e);
		}
	}

	public void serialize() throws TransformerException, IOException {

		// Serialize the document onto System.out

		TransformerFactory xformFactory = TransformerFactory.newInstance();
		Transformer idTransform;

		idTransform = xformFactory.newTransformer();
		Source input = new DOMSource(doc);
		File f = new File(this.fileName);
		/* if (f.createNewFile()){ */
		System.err.println(fileName);
		f.createNewFile();
		PrintWriter escribir = new PrintWriter(new BufferedWriter(
				new FileWriter(this.fileName)));
		Result output = new StreamResult(escribir);
		idTransform.transform(input, output);
		/*
		 * }else throw new IOException("No se ha podido crear el archivo
		 * "+fileName);
		 */

	}

	private void init() {
		Element root = doc.getDocumentElement();
		setRootAtribs(root);
		root.appendChild(generatePrincipalPackage());
		// root.appendChild(this.getStateMachine());
	}

	private Element generatePrincipalPackage() {
		String packageName = "PrincipalPackage" + Math.random();
		Element pack = this.doc.createElement("uml:Package");
		pack.setAttribute("xmi:id", packageName);
		pack.setAttribute("xmi:uuid", packageName);
		pack.setAttribute("name", "Root" + Math.random());

		// pack.appendChild(getOwnedMemberPackage());
		pack.appendChild(this.getStateMachine());

		pack.appendChild(this.getClase());

		return pack;
		// <uml:Package xmi:id="U00000001-7510-11d9-86f2-000476a22f44"
		// xmi:uuid="00000001-7510-11d9-86f2-000476a22f44" name="Root">

	}

	// private Element getOwnedMemberPackage() {
	// <ownedMember xmi:type="uml:Package"
	// xmi:id="U00000003-7510-11d9-86f2-000476a22f44"
	// xmi:uuid="00000003-7510-11d9-86f2-000476a22f44" name="Component View"
	// visibility="public">
	/*
	 * Element owned=this.doc.createElement("ownedMember");
	 * owned.setAttribute("xmi:type","uml:Package");
	 * owned.setAttribute("xmi:id","Component-View");
	 * owned.setAttribute("xmi:uuid","Component-View");
	 * owned.setAttribute("name","Component View");
	 * owned.setAttribute("visibility","public");
	 * 
	 * owned.appendChild(getStateMachine());
	 * 
	 * return owned;
	 */
	// return getStateMachine()+ getClass();
	// }
	private Element getClase() {
		// <ownedMember xmi:type="uml:Class"
		// xmi:id="U038caea2-0f89-496a-ac56-90094a0cf5e5"
		// xmi:uuid="038caea2-0f89-496a-ac56-90094a0cf5e5" name="Clase1"
		// visibility="public">
		Element clase = this.doc.createElement("ownedMember");
		clase.setAttribute("xmi:type", "uml:Class");
		clase.setAttribute("xmi:id", "clase_principal");
		clase.setAttribute("xmi:uuid", "clase_principal");
		clase.setAttribute("name", this.c.getName());

		loadPropiedadesIn(clase);
		loadMetodosIn(clase);

		return clase;
	}

	private void loadMetodosIn(Element clase) {
		// <ownedOperation xmi:type="uml:Operation"
		// xmi:id="U34961209-4fcc-46bf-939d-949b82282e30"
		// xmi:uuid="34961209-4fcc-46bf-939d-949b82282e30" name="metodo"
		// visibility="public">
		Element metAux;
		TOperation tm;
		Vector<TOperation> operaciones = new Vector<TOperation>();
		operaciones.addAll(this.c.getConstructors());
		operaciones.addAll(this.c.getMethods());
		for (Iterator it = operaciones.iterator(); it.hasNext();) {
			tm = (TOperation) (it.next());
			metAux = this.doc.createElement("ownedOperation");
			metAux.setAttribute("xmi:type", "uml:Operation");
			metAux.setAttribute("xmi:id", tm.getId());
			metAux.setAttribute("xmi:uuid", tm.getId());
			metAux.setAttribute("name", tm.getNombre());
			clase.appendChild(metAux);
			loadParamsIn(tm, metAux);
		}
	}

	private void loadParamsIn(TOperation tm, Element met) {
		Element parAux;
		TParameter tpar;
		int i = 0;
		if (tm.getParametros() != null)
			for (Iterator iterParams = tm.getParametros().iterator(); iterParams
					.hasNext(); i++) {
				tpar = (TParameter) iterParams.next();
				parAux = getParam(tm, tpar, i);
				// parAux.setAttribute("direction", "return");
				met.appendChild(parAux);
				// tm.getTipo();
			}

		// El parmetro de retorno en el caso de que sea un metodo
		if (tm instanceof Operation
				&& !((Operation) tm).getTipo().equalsIgnoreCase("void")) {
			parAux = this.getParam(tm, ((Operation) tm).getTipo(), i);
			parAux.setAttribute("direction", "return");
			met.appendChild(parAux);
		}
	}

	private Element getParam(TOperation tm, TParameter tpar, int id) {
		return getParam(tm, tpar.getTipo(), id);
	}

	private Element getParam(TOperation tm, String tipo, int id) {
		// <ownedParameter xmi:type="uml:Parameter"
		// xmi:id="Ue119584d-565d-4619-852d-93f96a2ccfa4"
		// xmi:uuid="e119584d-565d-4619-852d-93f96a2ccfa4" name="return"
		// direction="return" type="U68014f30-f0d0-44d7-98bf-415da5684c0f"/>
		// <ownedParameter xmi:type="uml:Parameter"
		// xmi:id="Uffef9fe6-f431-48b4-b95f-a51737d1a97e"
		// xmi:uuid="ffef9fe6-f431-48b4-b95f-a51737d1a97e" name="a"
		// type="U68014f30-f0d0-44d7-98bf-415da5684c0f"/>
		Element param = this.doc.createElement("ownedOperation");
		param.setAttribute("xmi:type", "uml:Parameter");
		param.setAttribute("xmi:id", tm.getId() + "-" + id);
		param.setAttribute("xmi:uuid", tm.getId() + "-" + id);
		param.setAttribute("name", tm.getId() + "-" + id);
		param.setAttribute("type", tipo);
		return param;
	}

	private void loadPropiedadesIn(Element clase) {
		// <ownedAttribute xmi:type="uml:Property"
		// xmi:id="Ucec48c31-27f2-4b77-9154-572f326daeb6"
		// xmi:uuid="cec48c31-27f2-4b77-9154-572f326daeb6" name="propiedad1"
		// visibility="protected" type="U68014f30-f0d0-44d7-98bf-415da5684c0f"/>
		Element etfAux;
		for (Iterator it = this.c.getFields().iterator(); it.hasNext();) {
			TField tf = (TField) (it.next());
			etfAux = this.doc.createElement("ownedAttribute");
			etfAux.setAttribute("xmi:type", "uml:Property");
			etfAux.setAttribute("xmi:id", tf.getName());
			etfAux.setAttribute("xmi:uuid", tf.getName());
			etfAux.setAttribute("name", tf.getName());
			etfAux.setAttribute("type", tf.getTypeName());
			clase.appendChild(etfAux);
		}
	}

	private Element getStateMachine() {
		// <ownedMember xmi:type="uml:StateMachine"
		// xmi:id="U87426208-7d11-4f69-9b58-7f8504dfbc69"
		// xmi:uuid="87426208-7d11-4f69-9b58-7f8504dfbc69" name="StateMachine1"
		// visibility="public">
		Element stmNode = this.doc.createElement("ownedMember");
		stmNode.setAttribute("xmi:type", "uml:StateMachine");
		stmNode.setAttribute("xmi:id", "stame_machine");
		stmNode.setAttribute("xmi:uuid", "stame_machine");
		stmNode.setAttribute("name", "StateMachine");
		stmNode.setAttribute("visibility", "public");

		stmNode.appendChild(getRegion());

		return stmNode;
	}

	private Element getRegion() {
		// <region xmi:type="uml:Region"
		// xmi:id="Ubd1e73a1-2efd-4e75-bd2a-be8019baf94e"
		// xmi:uuid="bd1e73a1-2efd-4e75-bd2a-be8019baf94e" name="Region1">
		Element region = this.doc.createElement("region");
		region.setAttribute("xmi:type", "uml:Region");
		region.setAttribute("xmi:id", "region_prpal");
		region.setAttribute("xmi:uuid", "region_prpal");
		region.setAttribute("name", "Region");

		for (Iterator it = getStates().iterator(); it.hasNext();)
			region.appendChild((Node) it.next());
		for (Iterator it = getTransitions().iterator(); it.hasNext();)
			region.appendChild((Node) it.next());

		return region;
	}

	private Vector<Element> getTransitions() {
		Vector<Element> transitions = new Vector<Element>();
		for (Iterator it = this.stm.getTransitions().iterator(); it.hasNext();)
			transitions.add(getTransition((ITransition) it.next()));
		return transitions;
	}

	private Element getTransition(ITransition transition) {
		// <transition xmi:type="uml:Transition"
		// xmi:id="U855d5b39-3bca-4ae4-993f-a4625ad95a73"
		// xmi:uuid="855d5b39-3bca-4ae4-993f-a4625ad95a73"
		// source="Uce6312fd-c080-4dc2-b0d8-038f7f28aa56"
		// target="U9251bfd1-49d3-4126-b313-2566e99bb9b3"/>
		Element trans = this.doc.createElement("transition");
		trans.setAttribute("xmi:type", "uml:Transition");
		trans.setAttribute("xmi:id", transition.getId());
		trans.setAttribute("xmi:uuid", transition.getId());
		trans.setAttribute("source", transition.getSource().getName());
		trans.setAttribute("target", transition.getTarget().getName());
		/**
		 * En nuestras mquinas de estados no su puede dar el que haya una guarda
		 * en las transiciones normales y hacia un choice, pr eso se pone un if
		 * else
		 */
		if (transition instanceof TransitionFromChoice) {
			trans.appendChild((Element) getGuarda(transition));
		} else {
			trans.appendChild(getMetodo(transition));
		}
		return trans;
	}

	private Element getMetodo(ITransition transition) {
		TOperation event = transition.getEvent();
		// <trigger xmi:type="uml:Trigger"
		// xmi:id="U55283b64-42c1-402e-8d7b-0382e4583767"
		// xmi:uuid="55283b64-42c1-402e-8d7b-0382e4583767" name="x2=234"/>
		Element evt = this.doc.createElement("trigger");
		evt.setAttribute("xmi:type", "uml:Trigger");
		evt.setAttribute("xmi:id", transition.getId() + "-" + event.getId());
		evt.setAttribute("xmi:uuid", transition.getId() + "-" + event.getId());
		evt.setAttribute("name", event.getId());
		evt.setAttribute("xmi:idref", event.getId());
		// <UML:CallEvent xmi.idref =
		// '-95-67-38-91--5c665f0a:112228d097d:-8000:0000000000000727'/>
		return evt;
	}

	private Element getGuarda(ITransition transition) {
		// <guard xmi:type="uml:Constraint"
		// xmi:id="Ud6b3668f-3015-471e-b77a-1f42e588608a"
		// xmi:uuid="d6b3668f-3015-471e-b77a-1f42e588608a" visibility="public">
		// <specification xmi:type="uml:LiteralString"
		// xmi:id="Ubc5ea804-a89c-4505-809e-07651d50ab69"
		// xmi:uuid="bc5ea804-a89c-4505-809e-07651d50ab69"
		// value="revisores.size()=3"/>
		// </guard>
		Element guard = this.doc.createElement("guard");
		guard.setAttribute("xmi:type", "uml:Constraint");
		String id = transition.getId() + "-" + Math.random();
		guard.setAttribute("xmi:id", id);
		guard.setAttribute("xmi:uuid", id);
		guard.setAttribute("visibility", "public");
		guard.appendChild((Element) getConditions(transition));
		return guard;
	}

	private Element getConditions(ITransition transition) {
		Vector<Condition> conditions = ((TransitionFromChoice) transition)
				.getConditions();
		String condiciones = null, conjuncion = " and ";

		if (conditions != null && conditions.size() > 0)
			condiciones = "";
		for (Iterator it = conditions.iterator(); it.hasNext();)
			condiciones += ((Condition) it.next()).getText() + conjuncion;
		condiciones.substring(0, condiciones.length() - conjuncion.length());
		return getEspecificacion(condiciones);
	}

	private Element getEspecificacion(String cond) {
		// <specification xmi:type="uml:LiteralString"
		// xmi:id="Ubc5ea804-a89c-4505-809e-07651d50ab69"
		// xmi:uuid="bc5ea804-a89c-4505-809e-07651d50ab69"
		// value="revisores.size()=3"/>
		Element especif = this.doc.createElement("specification");
		especif.setAttribute("xmi:type", "uml:LiteralString");
		String id = cond + "-" + Math.random();
		especif.setAttribute("xmi:id", id);
		especif.setAttribute("xmi:uuid", id);
		especif.setAttribute("value", cond);
		return especif;
	}

	/*
	 * private Vector<Element> getEspecificaciones(ITransition transition) {
	 * Vector<Condition>
	 * conditions=((TransitionFromChoice)transition).getConditions(); Vector
	 * <Element> especificaciones=new Vector<Element>();
	 * 
	 * for (Iterator it = conditions.iterator(); it.hasNext();)
	 * especificaciones.add(getEspecificacion((Condition) it.next()));
	 * 
	 * return especificaciones; }
	 */

	/*
	 * private Element getEspecificacion(Condition cond) { // <specification
	 * xmi:type="uml:LiteralString"
	 * xmi:id="Ubc5ea804-a89c-4505-809e-07651d50ab69"
	 * xmi:uuid="bc5ea804-a89c-4505-809e-07651d50ab69"
	 * value="revisores.size()=3"/> Element
	 * especif=this.doc.createElement("specification");
	 * especif.setAttribute("xmi:type","uml:LiteralString"); String
	 * id=cond.getText()+"-"+Math.random(); especif.setAttribute("xmi:id",id);
	 * especif.setAttribute("xmi:uuid",id);
	 * especif.setAttribute("value",cond.getText()); return especif; }
	 */

	private Vector<Element> getStates() {
		Vector<Element> states = new Vector<Element>();
		for (Iterator it = this.stm.getStates().values().iterator(); it
				.hasNext();) {
			states.add(getState((State) it.next()));
		}
		return states;
	}

	private Element getState(State state) {
		// <subvertex xmi:type="uml:State"
		// xmi:id="U9251bfd1-49d3-4126-b313-2566e99bb9b3"
		// xmi:uuid="9251bfd1-49d3-4126-b313-2566e99bb9b3" name="State1">
		Element st = this.doc.createElement("subvertex");

		if (state instanceof InitialState) {
			st.setAttribute("xmi:type", "uml:Pseudostate");
		} else if (state instanceof ChoiceState) {
			st.setAttribute("xmi:type", "uml:Pseudostate");
			st.setAttribute("kind", "choice");
		} else {
			st.setAttribute("xmi:type", "uml:State");
		}

		st.setAttribute("xmi:id", state.getName());
		st.setAttribute("xmi:uuid", state.getName());
		st.setAttribute("name", state.getName());

		Vector<Element> transitions = getInputTrans(state);
		transitions.addAll(getOutputTrans(state));

		for (Iterator it = transitions.iterator(); it.hasNext();)
			st.appendChild((Element) it.next());

		return st;
	}

	private Vector<Element> getOutputTrans(State state) {
		Vector<Element> outputs = new Vector<Element>();
		for (Iterator it = state.getOutputTransitions().iterator(); it
				.hasNext();)
			outputs.add(getOutgoing((ITransition) it.next()));
		return outputs;
	}

	private Element getOutgoing(ITransition transition) {
		Element out = this.doc.createElement("outgoing");
		out.setAttribute("xmi:idref", transition.getId());
		return out;
	}

	private Vector<Element> getInputTrans(State state) {
		Vector<Element> inputs = new Vector<Element>();
		for (Iterator it = stm.getInputTransitions(state).iterator(); it
				.hasNext();)
			inputs.add(getOutgoing((ITransition) it.next()));
		return inputs;
	}

	private Element getIncoming(ITransition transition) {
		Element out = this.doc.createElement("incoming");
		out.setAttribute("xmi:idref", transition.getId());
		return out;
	}

	private void setRootAtribs(Element root) {
		// <xmi:XMI xmlns:xmi="http://schema.omg.org/spec/XMI/2.1"
		// xmlns:uml="http://schema.omg.org/spec/UML/2.0"
		// xmi:version="2.1">
		root.setAttribute("xmlns:xmi", "http://schema.omg.org/spec/XMI/2.1");
		root.setAttribute("xmlns:uml", "http://schema.omg.org/spec/UML/2.0");
		root.setAttribute("xmi:version", "2.1");
	}

	public String getFileName() {
		return this.fileName;
	}

}

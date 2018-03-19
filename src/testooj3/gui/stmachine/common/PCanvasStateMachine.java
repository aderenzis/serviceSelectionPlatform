package testooj3.gui.stmachine.common;

import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.nodes.PText;
import java.awt.Cursor;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import testooj3.domain.TOperation;
import testooj3.domain.states.ITransition;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.exceptions.TransitionExistException;
import testooj3.domain.states.exceptions.TransitionUnsupportedException;
import testooj3.gui.stmachine.statemachine.JPStateMachine;

/**
 * @author  andres
 */
public class PCanvasStateMachine extends PCanvas{
	protected Hashtable<String, PState> states;  //  @jve:decl-index=0:
	protected PLayer statesLayer;  //  @jve:decl-index=0:
	protected PLayer transitionsLayer;  //  @jve:decl-index=0:
	/*protected PLayer labelsLayer;  //  @jve:decl-index=0:*/
	private JPStateMachine parentPanel;
	protected StateDragHandler stateDragHandler;
	protected StateMachine stMachine;  //  @jve:decl-index=0:
	
	public PCanvasStateMachine() {
		this(new StateMachine());
		
	}
	
	public PCanvasStateMachine(StateMachine stM){
		stMachine=stM;
		initialize();
		//System.err.println(this.stMachine.toString());
	}

	protected void initialize() {
		this.states=new Hashtable<String, PState>();
		this.statesLayer =  new PLayer();//getLayer();
		getCamera().addLayer(0, statesLayer);
		
		this.transitionsLayer = new PLayer();
		getCamera().addLayer(0, transitionsLayer);
		
		/*this.labelsLayer = new PLayer();
		getCamera().addLayer(0, labelsLayer);*/
		
		stateDragHandler=new StateDragHandler();
		transitionsLayer.addInputEventListener(new TransitionDragHandler());
		statesLayer.addInputEventListener(stateDragHandler);
		
		PState ps=null;
		State st;
		PTransition pt;
		
		
		
		for(Iterator iter=stMachine.getStates().values().iterator();iter.hasNext();){
			st=(State)iter.next();
			System.err.println(st.getName());
			ps=PStatesFactory.newInstance(st);
			ps.setCanvas(this);
			this.states.put(st.getName(), ps);
			this.statesLayer.addChild(ps);
		}
		
		this.transitionsLayer.addChildren(PTransitionFactory.newsIntances(this.stMachine,this.states));
		/*for(Iterator iter=stMachine.getTransitions().iterator();iter.hasNext();){
			pt=PTransitionFactory.newInstance(states,(ITransition)iter.next());
			this.transitionsLayer.addChild(pt);
		}*/
	}

	public void addState(State st) {
		if (!this.stMachine.getStates().containsKey(st.getName())){
			this.stMachine.add(st);
			PState ps=PStatesFactory.newInstance(st);
			ps.setCanvas(this);
			this.states.put(st.getName(), ps);
			this.statesLayer.addChild(ps);
		}
	}
	
	public boolean addTransition(ITransition t) throws TransitionExistException, TransitionUnsupportedException {
		if (this.stMachine.addTransition(t)){
			PTransition pt=PTransitionFactory.newInstance(states,t,this.stMachine.getTransitionsInLineIndex(t));
			this.transitionsLayer.addChild(pt);
			return true;
		}else{
			return false;
		}
	}
	
	public ITransition getTransition(ITransition tr){
		return this.stMachine.getTransition(tr);
	}

	public void setParentPanel(JPStateMachine panel) {
		this.parentPanel=panel;
	}
	
	public JPStateMachine getParentPanel(){
		return this.parentPanel;
	}

	public void log(String msg) {
		this.parentPanel.log(msg);
	}

	public PLayer getStatesLayer() {
		return statesLayer;
	}
	
	public void removeStateDragHandler(){
		this.statesLayer.removeInputEventListener(this.stateDragHandler);
	}

	public PLayer getTransitionsLayer() {
		return transitionsLayer;
	}

	public void selectMethod(TOperation selectedOperation) {
		PText p=new PText(selectedOperation.getId());
		p.setX(10); p.setY(10);
		this.statesLayer.addChild(p);
	}

	public StateMachine getStateMachine() {
		/*if (this.stMachine==null)
			this.stMachine=new StateMachine();*/
		return this.stMachine;
	}

	public void setStateMachine(StateMachine stm) {
		this.stMachine=stm;
		initialize();
	}

	public PTransition getPTransition(ITransition it) {
		return (PTransition) this.transitionsLayer.getChild(this.transitionsLayer.indexOfChild(PTransitionFactory.newInstance(states, it, 1)));
	}

	public void removeTransition(ITransition it) {
		this.stMachine.removeTransition(it);
		this.statesLayer.removeAllChildren();
		this.transitionsLayer.removeAllChildren();
		this.initialize();
	}

}

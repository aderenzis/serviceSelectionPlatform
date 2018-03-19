package testooj3.domain.states;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import testooj3.domain.states.exceptions.*;
import testooj3.domain.states.gtcsm.GAllStates;
import testooj3.domain.states.gtcsm.GTCSM;

/**
 * @author  andres
 */
public class StateMachine implements Serializable
{
	protected Hashtable mStates;
	protected Vector <ITransition> mTransitions;
	protected GTCSM test;

	public StateMachine()
	{
		mStates=new Hashtable();
		
		State s0=new InitialState();
		s0.setSuperstateName("init");
		this.add(s0);
		mStates.put(s0.getName(), s0);
		mTransitions=new Vector();
	}

	public String toHTMLTable() 
	{
		StatesTable table=new StatesTable(1+mStates.size(), 1+mStates.size());
		Enumeration states=mStates.elements();
		int row=1;
		while (states.hasMoreElements()) 
		{
			State st=(State) states.nextElement();
			table.set(row++, 0, st.getName());
		}
		int col=1;
		states=mStates.elements();
		while (states.hasMoreElements()) {
			State st=(State) states.nextElement();
			table.set(0, col++, st.getName());
		}
		for (int i=0; i<mTransitions.size(); i++)  
		{
			ITransition t=(ITransition) mTransitions.get(i);
			row=table.getRow(t.getSource().getName());
			col=table.getColumn(t.getTarget().getName());
			if (t instanceof TransitionFromChoice)
				table.set(row, col, ((TransitionFromChoice)t).getConditions().toString());
			else
				table.set(row, col, t.getEvent().toString());
		}
		return table.toHTMLTable();
	}

	public State add(String stateName) {
		State st=(State) mStates.get(stateName);
		if ( st==null) {
			st=new State(stateName, "");
			this.mStates.put(stateName, st);
		}
		return st;
	}

	public boolean addTransition(ITransition tr) throws TransitionExistException, TransitionUnsupportedException {
		if (containsTransition(tr))
			throw new TransitionExistException (tr);
		else if (tr.getTarget() instanceof InitialState) {
			throw new TransitionUnsupportedException (tr);
		}else{
			State source=(State)this.mStates.get(tr.getSource().getName());
			tr.setSource(source);
			tr.setTarget((State)this.mStates.get(tr.getTarget().getName()));
			mTransitions.add(tr);
			source.add(tr);
			return true;
		}
	}

	public boolean containsTransition(ITransition tr) {
		/*for (int i=0; i<this.mTransitions.size(); i++) {
			ITransition t=(ITransition) mTransitions.get(i);
			if (tr.getSource().getName().equals(t.getSource().getName()) &&
					tr.getTarget().getName().equals(t.getTarget().getName()) && 
					tr.getEvent().equals(t.getEvent()))
				return true;
		}
		return false;*/
		return this.mTransitions.contains(tr);
	}
	
	public boolean containsInverseTransition(ITransition tr){
		return this.containsTransition(new Transition(tr.getTarget(),tr.getSource(),null));
	}
	
	public ITransition getTransition(ITransition tr){
		int i=this.mTransitions.indexOf(tr);
		if (i>=0)
			return (ITransition)this.mTransitions.get(i);
		else
			return null;
	}
	
	public ITransition getInverseTransition(ITransition tr){
		return this.getTransition(new Transition(tr.getTarget(),tr.getSource(),null));
	}

	public Hashtable getStates() {
		return this.mStates;
	}
	
	public State getState(String k){
		return (State) this.mStates.get(k);
	}

	public Vector <ITransition> getTransitions() {
		return this.mTransitions;
	}

	private State addChoice(String stateName) {
		State st=(State) mStates.get(stateName);
		if (st==null) {
			st=new ChoiceState(stateName);
			this.mStates.put(stateName, st);
		}
		return st;
	}

	private State findState(String name) {
		return (State) mStates.get(name);
	}

	private void removeState(ChoiceState ch) {
		this.mStates.remove(ch.getName());
	}

	public void removeTransition(ITransition t) {
		this.mTransitions.remove(t);
		t.getSource().removeOutputTransition(t);
	}

	private Vector <ITransition> getTransitionsTo(ChoiceState ch) {
		Vector <ITransition>result=new Vector<ITransition>();
		Vector tt = this.mTransitions;
		for (int i=0; i<tt.size(); i++) {
			ITransition t=(ITransition) tt.get(i);
			if (t instanceof TransitionToChoice) {
				TransitionToChoice aux=(TransitionToChoice) t;
				if (aux.getTarget().equals(ch))
					result.add(t);
			}
		}
		return result;
	}

	private ITransition findTransitionFromTo(State source, State target) {
		for (int i=0; i<this.mTransitions.size(); i++) {
			ITransition t=(ITransition) this.mTransitions.get(i);
			if (t.getSource().getName().equals(source.getName()) && t.getTarget().getName().equals(target.getName()))
				return t;
		}
		return null;
	}

	public void add(State state) {
		State st=(State) mStates.get(state.getName());
		if (st==null)
			mStates.put(state.getName(), state);
	}

	public InitialState getInitialState() {
		return (InitialState)this.findState("init");
	}
	
	/**Devuelve el orden en que ha sido añadida la transición
	 * con respecto a las transiciones que se encuentran en la
	 * misma trayectoria. Es decir la que son de la forma:
	 * source->target o target->source*/
	public int getTransitionsInLineIndex(ITransition itr){
		Vector trs=this.getTransitionsInLine(itr);
		/*if (trs.isEmpty())
			return 0;
		else*/
			return trs.indexOf(itr);
	}
	
	/**Devuelve las transiciones que se encuentran en la misma trayectoria*/
	public Vector getTransitionsInLine(ITransition itr){
		Vector trs=new Vector();
		//trs.add(itr);
		ITransition itaux=null;
		for (Iterator it=mTransitions.iterator(); it.hasNext(); ){
			itaux=(ITransition)it.next();
			if ((itaux.getSource().equals(itr.getSource())&&
					itaux.getTarget().equals(itr.getTarget()))||
					(itaux.getSource().equals(itr.getTarget())&&
					itaux.getTarget().equals(itr.getSource())))
				trs.add(itaux);
		}
		return trs;
	}

	public String decisionTables() {
		StringBuilder r=new StringBuilder();
		Enumeration e = this.mStates.elements();
		while (e.hasMoreElements()) {
			State st=(State) e.nextElement();
			if (st instanceof ChoiceState) {
				ChoiceState chs=(ChoiceState) st;
				DecisionTable dt=chs.getDecisionTable();
				if (dt!=null) {
					r.append(dt.toHTML());
					dt.setTableTitle(dt.getTableTitle() + " (reduced)");
					dt.removeEqualsColumns();
					r.append(dt.toHTML());
					dt.setTableTitle(dt.getTableTitle() + " (grouped)");
					dt.groupGroupableColumns();
					r.append(dt.toHTML());
				}
			}
		}
		return r.toString();
	}


	public StateMachine zip1() {
		StateMachine result=new StateMachine();
		Enumeration states = this.mStates.elements();
		while (states.hasMoreElements()) {
			State st=(State) states.nextElement();
			if (st.getSuperstateName()==null) {
				result.add(st.getName());
			} else {
				result.add(st.getSuperstateName());
			}
		}
		states = this.mStates.elements();
		while (states.hasMoreElements()) {
			State st=(State) states.nextElement();
			if (st.getSuperstateName()!=null) {
				State sourceSuperState=(State) result.mStates.get(st.getSuperstateName());
				Vector methods=st.getMethods();
				for (int i=0; i<methods.size(); i++) {
					Transition method=(Transition) methods.get(i);
					String choiceName="Choice: " + sourceSuperState.getName() + " - " + method.getGenericEvent().toString();
					State choice=(State) result.mStates.get(choiceName);
					if (choice==null) {
						choice=new ChoiceState(choiceName);
						result.add(choice);
					}
					TransitionToChoice ttc=new TransitionToChoice(sourceSuperState, choice, method);
					try {
						result.addTransition(ttc);
					} catch (Exception e) {
						//e.printStackTrace(); 				
					}
					
					Vector<Transition> twm=st.getOutputTransitionsWithTheMethod(method.getGenericEvent().toString());
					
					for (int j=0; j<twm.size(); j++) {				
						Transition t=twm.get(j);
						State targetSuperState;
						if (t.getTarget().getSuperstateName()==null)
							targetSuperState=(State) result.mStates.get(t.getTarget().getName());
						else
							targetSuperState=(State) result.mStates.get(t.getTarget().getSuperstateName());
	
						TransitionFromChoice tfc=(TransitionFromChoice) result.findTransitionFromTo(choice, targetSuperState);
						if (tfc==null)
							tfc=new TransitionFromChoice(choice, targetSuperState);
						tfc.addConditions(t.getParameterValues(), st.getDetailedDescriptions());
						try {
							result.addTransition(tfc);
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
				}
			}
		}
		return result;
	}
	
	public StateMachine zip2() {
		StateMachine result=new StateMachine();
		Enumeration states = this.mStates.elements();
		while (states.hasMoreElements()) {
			State st=(State) states.nextElement();
			if (st instanceof ChoiceState) {
				ChoiceState ch=(ChoiceState) st;
				if (ch.getOutputTransitions().size()>1) {
					result.addChoice(ch.getName());
				}
			} else
				result.add(st.getName());
		}
		states = this.mStates.elements();
		while (states.hasMoreElements()) {
			State st=(State) states.nextElement();
			if (st instanceof ChoiceState) {
				ChoiceState ch=(ChoiceState) st;
				if (ch.getOutputTransitions().size()==1) {
					TransitionFromChoice aux=(TransitionFromChoice) ch.getOutputTransitions().get(0);
					State originalTargetState=aux.getTarget();
					State newTargetState=result.findState(originalTargetState.getName());
					Vector inputTransitions=this.getTransitionsTo(ch);
					for (int i=0; i<inputTransitions.size(); i++) {
						TransitionToChoice originalTransition=(TransitionToChoice) inputTransitions.get(i);
						State newSourceState=result.findState(originalTransition.getSource().getName());
						Transition newTransition=new Transition(newSourceState, newTargetState, originalTransition.getEvent());
						try {
							result.addTransition(newTransition);
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
				} else {
					for (int i=0; i<ch.getOutputTransitions().size(); i++) {
						TransitionFromChoice originalTransition=(TransitionFromChoice) ch.getOutputTransitions().get(i);
						State newSourceState=result.findState(originalTransition.getSource().getName());
						State newTargetState=result.findState(originalTransition.getTarget().getName());
						TransitionFromChoice newTFC=new TransitionFromChoice(newSourceState, newTargetState);
						newTFC.setConditions(originalTransition.getConditions());
						//Transition newTransition=new Transition(newSourceState, newTargetState, originalTransition.getEvent(), new Vector(), new Vector());
						try {
							result.addTransition(newTFC);
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
				}
			} else {
				for (int i=0; i<st.getOutputTransitions().size(); i++) {
					ITransition originalTransition=(ITransition) st.getOutputTransitions().get(i);
					State newSourceState=result.findState(originalTransition.getSource().getName());
					State newTargetState=result.findState(originalTransition.getTarget().getName());
					if (newTargetState!=null) {
						Transition newTransition=new Transition(newSourceState, newTargetState, originalTransition.getEvent());
						try {
							result.addTransition(newTransition);
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
				}
			}
		}
		return result;
	}

	public StateMachine zip3() {
		StateMachine result=new StateMachine();
		Enumeration states = this.mStates.elements();
		while (states.hasMoreElements()) {
			State st=(State) states.nextElement();
			if (st instanceof ChoiceState) {
				ChoiceState ch=(ChoiceState) st;
				result.addChoice(ch.getName());
			} else
				result.add(st.getName());
		}
		states = this.mStates.elements();
		while (states.hasMoreElements()) {
			State st=(State) states.nextElement();
			if (st instanceof ChoiceState) {
				ChoiceState ch=(ChoiceState) st;
				for (int i=0; i<ch.getOutputTransitions().size(); i++) {
					TransitionFromChoice originalTransition=(TransitionFromChoice) ch.getOutputTransitions().get(i);
					DecisionTable dt=originalTransition.getDecisionTable();
					dt.removeEqualsColumns();
					dt.setTableTitle(dt.getTableTitle() + " (reduced and grouped)");
					dt.groupGroupableColumns();
					Vector<Condition> groupedConditions=dt.getConditions();
					
					State newSourceState=result.findState(originalTransition.getSource().getName());
					State newTargetState=result.findState(originalTransition.getTarget().getName());
					TransitionFromChoice newTFC=new TransitionFromChoice(newSourceState, newTargetState);
					newTFC.setConditions(groupedConditions);
					try {
						result.addTransition(newTFC);
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}				
			} else {
				for (int i=0; i<st.getOutputTransitions().size(); i++) {
					ITransition originalTransition=(ITransition) st.getOutputTransitions().get(i);
					State newSourceState=result.findState(originalTransition.getSource().getName());
					State newTargetState=result.findState(originalTransition.getTarget().getName());
					if (newTargetState!=null) {
						Transition newTransition=new Transition(newSourceState, newTargetState, originalTransition.getEvent());
						try {
							result.addTransition(newTransition);
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
				}
			}
		}
		result.removeRepatedConditions();
		return result;
	}

	protected void removeRepatedConditions() {
		Vector<DetailedDescription> removableDetailedDescriptions=new Vector<DetailedDescription>();
		for (int i=0; i<this.mTransitions.size(); i++) {
			ITransition t=(ITransition) mTransitions.get(i);
			if (t instanceof TransitionFromChoice) {
				TransitionFromChoice tfc=(TransitionFromChoice) t;
				DecisionTable dt=tfc.getDecisionTable();
				Vector<Condition> cc = dt.getConditions();
				for (int j=0; j<cc.size(); j++) {
					Condition c = cc.get(j);
					Vector<DetailedDescription> dd = c.getDetailedDescriptions();
					for (int k=0; k<dd.size(); k++) {
						DetailedDescription d = dd.get(k);
						if (appearsInAllDecisionTables(tfc, d) && !removableDetailedDescriptions.contains(d))
							removableDetailedDescriptions.add(d);
					}
				}
			} 
		}
		for (int i=0; i<this.mTransitions.size(); i++) {
			ITransition t=(ITransition) mTransitions.get(i);
			if (t instanceof TransitionFromChoice) {
				TransitionFromChoice tfc=(TransitionFromChoice) t;
				tfc.removeConditions(removableDetailedDescriptions);
			} 
		}
	}
	
	private boolean appearsInAllDecisionTables(TransitionFromChoice tfc, DetailedDescription d) {
		for (int i=0; i<this.mTransitions.size(); i++) {
			ITransition t=(ITransition) mTransitions.get(i);
			if (t instanceof TransitionFromChoice && !t.equals(tfc)) {
				TransitionFromChoice auxi=(TransitionFromChoice) t;
				if (!auxi.containsDetailedDescription(d))
					return false;
			}
		}
		return true;
	}
	
	public String toString (){
		return this.mTransitions.toString() + this.mStates.toString();
	}
	
	public Vector <ITransition> getInputTransitions(State st){
		Vector <ITransition>  result =new Vector<ITransition>();
		ITransition itAux=null;
		//st=this.getState(st.getName());
		for (Iterator iter=this.mTransitions.iterator(); iter.hasNext();){
			itAux=(ITransition) iter.next();
			if (itAux.getTarget().equals(st))
				result.add(itAux);
		}
		return result;
	}
	
}
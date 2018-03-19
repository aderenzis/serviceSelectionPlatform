package testooj3.gui.stmachine.common;


import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import testooj3.domain.states.ITransition;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.Transition;

public class PTransitionFactory {

	public static PTransition newInstance(Hashtable<String, PState> states, ITransition it, int index) {
		PState source=states.get(it.getSource().getName()),target=states.get(it.getTarget().getName());
		if (it.getSource().equals(it.getTarget()))
			return new PTransitionSelf(it,source);
		else{
			return new PTransitionAlt(it,source,target,index);
		}
		/*State source=it.getSource(),target=it.getTarget();
		ITransition transAux;
		
		for (Iterator i=target.getOutputTransitions().iterator();i.hasNext();){
			transAux=(ITransition)i.next();
			if (transAux.getSource().equals(target) && transAux.getTarget().equals(source))
				return new PTransitionAlt(it, states.get(source.getName()),states.get(target.getName()));
			
		}
		return new PTransition(it, states.get(source.getName()),states.get(target.getName()));*/
		
		/*if (source.getOutputTransitions().contains(it) || 
				target.contains(new PTransition(source,target))){
			return new PTransitionAlt(it, source,target);
		}
		else{
			return new PTransition(it, source, target);
		}*/
	}
	
	public static Collection newsIntances(StateMachine stm, Hashtable<String, PState> states){
		LinkedList transitions=new LinkedList();
		Vector transaux=(Vector)stm.getTransitions();//.clone();
		ITransition itaux=null,iitaux=null;
		PState source=null,target=null;
		System.err.println(transaux.size()+"\n"+transaux.toString());
		for (int i=0; i<transaux.size();i++){
			itaux=(ITransition)transaux.get(i);
			source=states.get(itaux.getSource().getName());
			if (itaux.getSource().equals(itaux.getTarget())){
				System.err.println("----------------------st");
				transitions.add(new PTransitionSelf(itaux,source));
			}
			else{
				target=states.get(itaux.getTarget().getName());
				transitions.add(new PTransitionAlt(itaux,source,target,stm.getTransitionsInLineIndex(itaux)));
				/*target=states.get(itaux.getTarget().getName());
				iitaux=stm.getInverseTransition(itaux);
				if (iitaux!=null){
					transitions.add(new PTransitionAlt(iitaux,target ,source));
					transaux.remove(iitaux);
				}
				transitions.add(new PTransition(itaux, source,target));*/
			}
		}
		return transitions;
	}

}

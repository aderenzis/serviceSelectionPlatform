package testooj3.domain.states;

import java.io.Serializable;
import testooj3.domain.TOperation;

/**
 * @author  andres
 */
public class TransitionToChoice implements ITransition, Serializable {

	private TOperation event;
	private State source;
	private State target;

	public TransitionToChoice(State source, State choice, Transition t) {
		this.source=source;
		this.target=choice;
		this.event=t.getEvent();
	}

	public TOperation getEvent() {
		return event;
	}

	public State getSource() {
		return source;
	}
	
	public State getTarget() {
		return this.target;
	}
	
	public boolean equals(Object ob){
		if (ob instanceof TransitionToChoice)
			return ((ITransition)ob).getTarget().equals(this.getTarget())&&
			((ITransition)ob).getSource().equals(this.getSource())&&
			((TransitionToChoice)ob).getEvent().equals(this.getEvent());
		else return false;
	}
	
	  public void setSource(State st){
		this.source=st;  
	  }
	  
	  public void setTarget(State st){
			this.target=st;  
	  }
	  
	  public String toString(){
			return "\n"+this.source.getName()+"--"+this.event.toString()+"-->"+this.target.getName();
		}
	  
	  public String getId() {
			return this.getSource()+"-"+this.getEvent()+"-"+this.getTarget();
	  }

}

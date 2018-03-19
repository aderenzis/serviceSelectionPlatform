package testooj3.domain.states;

import testooj3.domain.TOperation;

/**
 * @author  andres
 */
public interface ITransition {
	
	public TOperation getEvent();

	public State getSource();

	public State getTarget();
	
	public void setSource(State st);

	public void setTarget(State st);
	
	public String toString();
	
	public String getId();

}

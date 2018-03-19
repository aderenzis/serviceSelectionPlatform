package testooj3.domain.states;

import java.io.Serializable;
import java.util.Vector;
import testooj3.domain.TOperation;

/**
 * @author  andres
 */
public class Transition implements ITransition, Serializable 
{
  protected State source;
  protected State target;
  protected TOperation event;
  Vector parameterValues;
  
  public Transition(State s, State t, TOperation evt, Vector parValues)
  {
    source=s;
    target=t;
    this.event=evt;
    /*label=l;
    parameterTypes=parTypes;*/
    
    parameterValues=parValues;
    
    
    if (parameterValues==null)
    	parameterValues=new Vector();
    /*if (parameterTypes==null)
    	parameterTypes=new Vector();*/
  }
  
  public Transition(State s, State t, TOperation evt)
  {
    this(s,t,evt,null);
  }
  
  public State getTarget() 
  {
    return target;
  }
  
  public State getSource() 
  {
    return source;
  }
  
  public Vector getParameterValues() {
		return this.event.getParametros();
	}
  
  /*public String getLabel() 
  {
    return label;
  }

	public Vector getParameterValues() {
		return parameterValues;
	}

	public TOperation getEvent() {
		String r=label;
		if (parameterValues.size()>0) {
			r+="(";
			for (int i=0; i<parameterValues.size(); i++) {
				r+=parameterValues.get(i).toString() + ", ";
			}
			r=r.substring(0, r.length()-2);
			r+=")";
		}
		return r;
	}*/
  
  	public TOperation getEvent() {
		return this.event;
	}
  	
  	public TOperation getGenericEvent(){
  		return this.event;
  	}

	/*public String getGenericEvent() {
		String r=label;
		if (parameterTypes.size()>0) {
			r+="(";
			for (int i=0; i<parameterTypes.size(); i++) {
				r+="x" + (i+1) + " : " + parameterTypes.get(i).toString() + ", ";
			}
			r=r.substring(0, r.length()-2);
			r+=")";
		}
		return r;
	}*/
	
	public String toString(){
		return "\n"+
		this.source.getName()+"--"+
		this.event.toString()+"-->"+
		this.target.getName();
	}
	
	public boolean equals(Object ob){
		if (ob instanceof Transition)
			return ((ITransition)ob).getTarget().equals(this.getTarget())&&
			((ITransition)ob).getSource().equals(this.getSource())&&
			((Transition)ob).getEvent().equals(this.getEvent());
		else return false;
	}

	public void setSource(State st){
		this.source=st;  
	  }
	  
	public void setTarget(State st){
			this.target=st;  
	}

	public String getId() {
		return this.getSource()+"-"+this.getEvent()+"-"+this.getTarget();
	}

}
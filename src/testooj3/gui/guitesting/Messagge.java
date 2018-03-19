package testooj3.gui.guitesting;

import java.io.Serializable;
import java.util.Vector;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestValue;

/**
 * @author  andres
 */
public class Messagge implements IMessagge, Serializable 
{
	protected Instance source;
	protected Instance target;
	protected Operation method;
	private int messaggeNumber;

	public Messagge(Instance s, Instance t, Operation evt, int messaggeNumber)
	{
		source=s;
		target=t;
		this.method=evt;
		this.messaggeNumber=messaggeNumber;
	}

	public Instance getTarget() 
	{
		return target;
	}

	public Instance getSource() 
	{
		return source;
	}

	public Vector<TParameter> getParameterValues() {
		return this.method.getParametros();
	}

	public Operation getMethod() {
		return this.method;
	}

	public String toString(){
		return "\n"+
		this.source.getName()+"--"+
		this.method.toString()+"-->"+
		this.target.getName();
	}

	public boolean equals(Object ob){
		if (!(ob instanceof Messagge)) return false;
		Messagge mensaje=(Messagge) ob;
		return (mensaje.getTarget().equals(this.getTarget()))&&
			(mensaje.getSource().equals(this.getSource()))&&
			(mensaje.getMethod().equals(this.getMethod()));
	}

	public void setSource(Instance st){
		this.source=st;  
	}

	public void setTarget(Instance st){
		this.target=st;  
	}

	public String getId() {
		return this.getMethod().getId();
	}

	public int getCounter() {
		return this.messaggeNumber;
	}

	public Vector<TParameter> getParameters() {
		return this.method.getParametros();
	}

	public boolean hasParameters() {
		return this.method.getParametros().size()>0;
	}

}
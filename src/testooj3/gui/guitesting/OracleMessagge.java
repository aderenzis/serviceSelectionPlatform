package testooj3.gui.guitesting;

import java.io.Serializable;
import java.util.Vector;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;

/**
 * @author  andres
 */
public class OracleMessagge implements IMessagge, Serializable 
{
	protected Instance source;
	protected Instance target;
	protected String oracle;
	private int messaggeNumber;

	public OracleMessagge(Instance s, Instance t,String oracle, int messaggeNumber, Vector parValues)
	{
		source=s;
		target=t;
		this.oracle=oracle;
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

	public String toString(){
		return "\n"+
		this.source.getName()+"--"+
		this.oracle +"-->"+
		this.target.getName();
	}

	public boolean equals(Object ob){
		if (!(ob instanceof OracleMessagge)) return false;
		OracleMessagge mensaje=(OracleMessagge) ob;
		return (mensaje.getTarget().equals(this.getTarget()))&&
			(mensaje.getSource().equals(this.getSource()))&&
			(mensaje.getId().equals(this.getId()));
	}

	public void setSource(Instance st){
		this.source=st;  
	}

	public void setTarget(Instance st){
		this.target=st;  
	}

	public String getId() {
		return this.oracle;
	}

	public int getCounter() {
		return this.messaggeNumber;
	}

	public Operation getMethod() {
		return null;
	}

	public Vector<TParameter> getParameters() {
		return null;
	}

	public boolean hasParameters() {
		return false;
	}

}
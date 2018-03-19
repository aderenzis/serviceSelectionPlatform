package testooj3.gui.guitesting;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.TestValue;

/**
 * @author  andres
 */
public interface IMessagge {
	
	public Operation getMethod();

	public Instance getSource();

	public Instance getTarget();
	
	public void setSource(Instance st);

	public void setTarget(Instance st);
	
	public String toString();
	
	public String getId();

	public int getCounter();

	public boolean hasParameters();

	public Vector<TParameter> getParameters();

}

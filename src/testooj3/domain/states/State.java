package testooj3.domain.states;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @author  andres
 */
public class State implements Serializable
{
	protected String name;
	protected String description;
	protected Vector outputTransitions;
	protected String superstateName;
private Vector<DetailedDescription> detailedDescriptions;

	public State(String n, String d)
	{
		this.name=n;
		this.description=d;
		this.outputTransitions=new Vector();
		this.superstateName=null;
	}

	public void add(ITransition t) 
	{
		if (!this.outputTransitions.contains(t))
			this.outputTransitions.add(t);
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Vector getOutputTransitions()
	{
		return this.outputTransitions;
	}

	public String getSuperstateName() {
		return superstateName;
	}

	public void setSuperstateName(String superstateName) {
		this.superstateName = superstateName;
	}

	public Vector getMethods() {
		Hashtable<String, Transition> aux=new Hashtable<String, Transition>();
		for (int i=0; i<this.outputTransitions.size(); i++) {
			Transition t=(Transition) this.outputTransitions.get(i);
			String signature=t.getGenericEvent().toString();
			if (aux.get(signature)==null)
				aux.put(signature, t);
		}
		Vector<Transition> result=new Vector<Transition>();
		Enumeration e = aux.elements();
		while (e.hasMoreElements()) {
			result.add((Transition) e.nextElement());
		}
		return result;
	}

	public Vector<Transition> getOutputTransitionsWithTheMethod(String methodSignature) {
		Vector<Transition> result=new Vector<Transition>();
		for (int i=0; i<this.outputTransitions.size(); i++) {
			Transition t=(Transition) this.outputTransitions.get(i);
			if (t.getGenericEvent().toString().equals(methodSignature)) 
				result.add(t);
		}
		return result;
	}

	public void removeOutputTransition(ITransition t) {
		this.outputTransitions.remove(t);
	}
	
	public boolean equals(Object st){
		if (st instanceof State)
			return ((State)st).getName().equals(this.name);
		else
			return false;
	}
	
	public String toString(){
		return this.name;
	}

	public void setDetailedDescriptions(Vector<DetailedDescription> detailedDescriptions) {
		this.detailedDescriptions=detailedDescriptions;
	}

	public Vector<DetailedDescription> getDetailedDescriptions() {
		return this.detailedDescriptions;
	}
}
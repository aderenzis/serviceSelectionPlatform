package testooj3.gui.guitesting;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @author  andres
 */
@SuppressWarnings("serial")
public class Instance implements Serializable
{
	protected GuiFixture fixture;
	protected String description;
	protected Vector<IMessagge> outputMessagges;
	protected String superstateName;
	
	public Instance(String description)
	{
		this.description=description;
		this.outputMessagges=new Vector<IMessagge>();
		this.superstateName=null;
	}

	public Instance(GuiFixture gf)
	{
		this.fixture=gf;
		this.outputMessagges=new Vector<IMessagge>();
		this.superstateName=null;
	}

	public void add(IMessagge t) 
	{
		if (!this.outputMessagges.contains(t))
			this.outputMessagges.add(t);
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
		if (this.fixture==null)
			return "actor";
		return this.fixture.getName();
	}

	public Vector<IMessagge> getOutputMessagges()
	{
		return this.outputMessagges;
	}

	public String getSuperstateName() {
		return superstateName;
	}

	public void setSuperstateName(String superstateName) {
		this.superstateName = superstateName;
	}

	public Vector<Messagge> getMethods() {
		Hashtable<String, Messagge> aux=new Hashtable<String, Messagge>();
		for (int i=0; i<this.outputMessagges.size(); i++) {
			Messagge t=(Messagge) this.outputMessagges.get(i);
			String signature=t.getMethod().getId();
			if (aux.get(signature)==null)
				aux.put(signature, t);
		}
		Vector<Messagge> result=new Vector<Messagge>();
		Enumeration<Messagge> e = aux.elements();
		while (e.hasMoreElements()) {
			result.add(e.nextElement());
		}
		return result;
	}

	public Vector<Messagge> getOutputTransitionsWithTheMethod(String methodSignature) {
		Vector<Messagge> result=new Vector<Messagge>();
		for (int i=0; i<this.outputMessagges.size(); i++) {
			Messagge t=(Messagge) this.outputMessagges.get(i);
			if (t.getMethod().getId().equals(methodSignature)) 
				result.add(t);
		}
		return result;
	}

	public void removeOutputTransition(IMessagge t) {
		this.outputMessagges.remove(t);
	}
	
	public boolean equals(Object anotherInstance){
		if (anotherInstance instanceof Instance)
			return ((Instance) anotherInstance).getName().equals(this.getName());
		else
			return false;
	}
	
	public String toString(){
		return this.getName();
	}

	public String getTypeName() {
		if (this.fixture==null)
			return "actor";
		return this.fixture.getTypeName();
	}

}
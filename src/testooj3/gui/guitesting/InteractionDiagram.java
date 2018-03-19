package testooj3.gui.guitesting;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

public class InteractionDiagram implements Serializable
{
	protected Hashtable<String, Instance> mInstances;
	protected Vector <IMessagge> mInteractions;

	public InteractionDiagram()
	{
		mInstances=new Hashtable<String, Instance>();
		
		Instance s0=new ActorInstance();
		s0.setSuperstateName("actor");
		this.add(s0);
		mInstances.put(s0.getName(), s0);
		mInteractions=new Vector<IMessagge>();
	}

	public boolean addMessagge(IMessagge tr) throws MessaggeUnsupportedException {
		if (tr.getTarget() instanceof ActorInstance) {
			throw new MessaggeUnsupportedException(tr);
		}else{
			Instance source=this.mInstances.get(tr.getSource().getName());
			tr.setSource(source);
			tr.setTarget(this.mInstances.get(tr.getTarget().getName()));
			mInteractions.add(tr);
			source.add(tr);
			return true;
		}
	}

	public boolean containsMessagge(IMessagge tr) {
		return this.mInteractions.contains(tr);
	}
	
	public IMessagge getMessagge(IMessagge tr){
		int i=this.mInteractions.indexOf(tr);
		if (i>=0)
			return this.mInteractions.get(i);
		else
			return null;
	}

	public Hashtable<String, Instance> getInstances() {
		return this.mInstances;
	}
	
	public Instance getInstance(String k){
		return this.mInstances.get(k);
	}

	public Vector <IMessagge> getMessagges() {
		return this.mInteractions;
	}

	private Instance findInstance(String name) {
		return mInstances.get(name);
	}

	public void removeMessagge(IMessagge t) {
		this.mInteractions.remove(t);
		t.getSource().removeOutputTransition(t);
	}

	public void add(Instance instance) {
		Instance st=mInstances.get(instance.getName());
		if (st==null)
			mInstances.put(instance.getName(), instance);
	}

	public ActorInstance getInitialState() {
		return (ActorInstance)this.findInstance("actor");
	}
	
	/**Devuelve el orden en que ha sido añadida la transición
	 * con respecto a las transiciones que se encuentran en la
	 * misma trayectoria. Es decir la que son de la forma:
	 * source->target o target->source*/
	public int getMessaggesInLineIndex(IMessagge itr){
		Vector<IMessagge> trs=this.getMessaggesInLine(itr);
		return trs.indexOf(itr);
	}
	
	/**Devuelve las transiciones que se encuentran en la misma trayectoria*/
	public Vector<IMessagge> getMessaggesInLine(IMessagge itr){
		Vector<IMessagge> trs=new Vector<IMessagge>();
		//trs.add(itr);
		IMessagge itaux=null;
		for (Iterator<IMessagge> it=mInteractions.iterator(); it.hasNext(); ){
			itaux=it.next();
			if (itaux.equals(it))
				trs.add(itaux);
		}
		return trs;
	}

	public String toString (){
		return this.mInteractions.toString() + this.mInstances.toString();
	}
	
	public Vector <IMessagge> getInputMessagges(Instance st){
		Vector <IMessagge>  result =new Vector<IMessagge>();
		IMessagge itAux=null;
		for (Iterator<IMessagge> iter=this.mInteractions.iterator(); iter.hasNext();){
			itAux=iter.next();
			if (itAux.getTarget().equals(st))
				result.add(itAux);
		}
		return result;
	}
	
}
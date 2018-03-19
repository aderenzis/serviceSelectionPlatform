package testooj3.gui.guitesting;


import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class PMessaggesFactory {

	public static PMessage newInstance(Hashtable<String, PInstance> states, IMessagge it, int index) {
		PInstance source=states.get(it.getSource().getName());
		PInstance target=states.get(it.getTarget().getName());
		if (it.getSource().equals(it.getTarget()))
			return new PMessaggeSelf(it,source);
		else{
			return new PMessaggeAlt(it,source,target,index);
		}
	}
	
	public static Collection newIntances(InteractionDiagram stm, Hashtable<String, PInstance> states){
		LinkedList transitions=new LinkedList();
		Vector transaux=(Vector)stm.getMessagges();//.clone();
		IMessagge itaux=null,iitaux=null;
		PInstance source=null,target=null;
		System.err.println(transaux.size()+"\n"+transaux.toString());
		for (int i=0; i<transaux.size();i++){
			itaux=(IMessagge)transaux.get(i);
			source=states.get(itaux.getSource().getName());
			if (itaux.getSource().equals(itaux.getTarget())){
				System.err.println("----------------------st");
				transitions.add(new PMessaggeSelf(itaux, source));
			}
			else{
				target=states.get(itaux.getTarget().getName());
				transitions.add(new PMessaggeAlt(itaux,source,target,stm.getMessaggesInLineIndex(itaux)));
			}
		}
		return transitions;
	}
}

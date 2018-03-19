package testooj3.domain.states.gtcsm;

import java.util.Iterator;
import java.util.Vector;

import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.states.ITransition;
import testooj3.domain.states.InitialState;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.Transition;
import testooj3.domain.states.TransitionFromChoice;
import testooj3.domain.states.TransitionToChoice;

public class GAllStates extends GTCSM {

	Vector <TestTemplate> tTemplates=null;
	
	
	public GAllStates(StateMachine stm,Interface mC){
		this.stMachine=stm;
		this.tTemplates=new Vector<TestTemplate>();
		this.mClass=mC;
		
		/*try {
			this.mClass.loadDescription();
		} catch (Exception e) {
			System.err.println("Error al cargar la descripcin de la clase");
			e.printStackTrace();
		}*/
	}
	
	
	/**Se parte del estado inicial*/
	public Vector<TestTemplate> genTestTemplates() {
		/**Cualquier transición desde un estado inicial o otro estado se hace utilizando un constructor
		 * por lo que se generarán tantos TestTemplates como transiciones de salida haya
		 * desde el estado inicial*/
		InitialState iS=this.stMachine.getInitialState();
		TestTemplate ttAux=null;
		//TestCase tcAux=null;
		ITransition transAux=null;
		Vector <TestTemplate> ttsAux=new Vector<TestTemplate>();
		Vector <TestTemplate> v=new Vector<TestTemplate>();
		Vector <State>porVisitar =new Vector<State>();
		porVisitar.addAll(this.stMachine.getStates().values());
		porVisitar.remove(iS);
		for (Iterator it=iS.getOutputTransitions().iterator();it.hasNext();){
			transAux = (ITransition) it.next();
			//ttsAux.addAll(this.getSubTestTemplates(transAux,porVisitar,1));
			ttsAux=this.getSubTestTemplates(transAux,porVisitar,1);
			for (int k=0;k<ttsAux.size();k++){
				//Como se trata del estado inicial, no hay que comprobar si es una TransitionFromChoice
				ttAux=(TestTemplate) ttsAux.get(k);
				ttAux.setConstructor((TConstructor) transAux.getEvent());
				ttAux.setName(transAux.getEvent().toString()+"_"+(k+v.size()));
			}
			v.addAll(ttsAux);
		}
		return v;
	}
	
	private Vector <TestTemplate> getSubTestTemplates(ITransition it, Vector<State> porVisitar, int n) {
		Vector <TestTemplate> v=new Vector<TestTemplate>();
		if (porVisitar.contains(it.getTarget())){
			porVisitar.remove(it.getTarget());
			if (porVisitar.isEmpty()){
				/**Es un nodo meta, porque ya se han visitado todos los estados*/
				TestTemplate tt=new TestTemplate();
				v.add(tt);
			}else{
				ITransition transAux=null;
				Vector <TestTemplate> ttsAux=new Vector<TestTemplate>();
				for (Iterator iter=it.getTarget().getOutputTransitions().iterator();iter.hasNext();){
					transAux = (ITransition) iter.next();
					if (transAux instanceof TransitionFromChoice)
						ttsAux=this.getSubTestTemplates(transAux,porVisitar,n);
					else{
						ttsAux=this.getSubTestTemplates(transAux,porVisitar,n+1);
						for (int k=0;k<ttsAux.size();k++)
							((TestTemplate)ttsAux.get(k)).setMethod(n, (Operation)transAux.getEvent());
					}
					v.addAll(ttsAux);
				}
			}
			porVisitar.add(it.getTarget());
		}
		return v;
	}

	@Override
	public String toString() {
		return "All States 1Time";
	}

}

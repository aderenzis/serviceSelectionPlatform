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
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.TransitionFromChoice;

public class GAllTransitions extends GTCSM {

	public GAllTransitions(StateMachine stm, Interface mC) {
		this.stMachine=stm;
		this.tTemplates=null;
		this.mClass=mC;
	}
	

	@Override
	public Vector<TestTemplate> genTestTemplates() {
		Vector <TestTemplate> result=new Vector<TestTemplate>();
		Vector <ITransition> xVisitar=this.stMachine.getTransitions();
		TestTemplate ttAux=null;
		ITransition itAux=null;
		int i=1;
		for (Iterator it=this.stMachine.getInitialState().getOutputTransitions().iterator(); it.hasNext();){
			itAux=(ITransition) it.next();
			for (Iterator itTT=this.getSubTestTemplates(itAux,xVisitar,i++).iterator(); itTT.hasNext();){
				ttAux=(TestTemplate) itTT.next();
				ttAux.setConstructor((TConstructor) itAux.getEvent());
				ttAux.setName("TestTemplate "+(i-1));
				result.add(ttAux);
			}
		}
		return result;
	}

	private Vector <TestTemplate> getSubTestTemplates(ITransition transicion, Vector<ITransition> xVisitar, int n) {
		Vector <TestTemplate> result=new Vector<TestTemplate>();
		TestTemplate ttAux=null;
		
		if (xVisitar.contains(transicion)){
			xVisitar.remove(transicion);
			if (xVisitar.isEmpty()){
				ttAux=new TestTemplate();
				//ttAux.setMethod(n,transicion.getEvent());
				result.add(ttAux);
			}
			else{
				ITransition itAux=null;
				for (Iterator it=transicion.getTarget().getOutputTransitions().iterator(); it.hasNext();){
					itAux=(ITransition) it.next();
					Iterator itTT=null;
					if (itAux instanceof TransitionFromChoice)
						itTT=this.getSubTestTemplates(itAux,xVisitar,n).iterator();
					else
						itTT=this.getSubTestTemplates(itAux,xVisitar,n+1).iterator();
					
					for (; itTT.hasNext();){
						ttAux=(TestTemplate) itTT.next();
						if (!(itAux instanceof TransitionFromChoice))
							ttAux.setMethod(n, (Operation)itAux.getEvent());
						result.add(ttAux);
					}
				}
			}
			xVisitar.add(transicion);
		}
		return result;
	}

	
	
	
	
	/*Vector <TestTemplate> result=new Vector<TestTemplate>();
	Vector <TestTemplate> resultAux=new Vector<TestTemplate>();
	Vector <ITransition> xVisitar=this.stMachine.getTransitions();
	InitialState iS=this.stMachine.getInitialState();
	ITransition transAux=null;
	TestTemplate ttAux=null;
	
	//para cada transición que sale del estado inicial
	for (Iterator itTrans=iS.getOutputTransitions().iterator(); itTrans.hasNext();){
		transAux=(ITransition) itTrans.next();
		//xVisitar.remove(transAux);
		resultAux=this.getSubTestTemplates(transAux,xVisitar,1);
		if (! (transAux instanceof TransitionFromChoice))
			for (int i=0;i<resultAux.size();i++){
				ttAux=(TestTemplate) resultAux.get(i);
				ttAux.setConstructor((TConstructor) transAux.getEvent());
				ttAux.setName( transAux.getEvent().toString()+"_"+(result.size()+i));
			}
		result.addAll(resultAux);
		//xVisitar.add(transAux);
	}
	return result;*/

	/*private Vector<TestTemplate> getSubTestTemplates(ITransition trans, Vector<ITransition> xVisitar, int n) {
		Vector <TestTemplate> result=new Vector<TestTemplate>();
		
		if (xVisitar.contains(trans)){
			
			xVisitar.remove(trans);
			if (xVisitar.isEmpty()){
				result.add(new TestTemplate());
			}else{
				Vector <TestTemplate> resultAux=new Vector<TestTemplate>();
				
				ITransition transAux=null;
				TestTemplate ttAux=null;
				//para cada transición que sale del estado destino
				for (Iterator itTrans=trans.getTarget().getOutputTransitions().iterator(); itTrans.hasNext();){
					transAux=(ITransition) itTrans.next();
					//xVisitar.remove(transAux);
					resultAux=this.getSubTestTemplates(transAux,xVisitar,n+1);
					if (! (transAux instanceof TransitionFromChoice)){
						for (int i=0;i<resultAux.size();i++){
							ttAux=(TestTemplate) resultAux.get(i);
							ttAux.setMethod(n,transAux.getEvent());
						}
					}
					result.addAll(resultAux);
					//xVisitar.add(transAux);
				}
				
			}
			xVisitar.add(trans);
		}
		return result;
	}*/
	
	@Override
	public String toString() {
		return "All Transitions 1Time";
	}

}

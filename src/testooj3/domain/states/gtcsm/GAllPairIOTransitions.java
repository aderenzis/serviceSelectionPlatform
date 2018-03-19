package testooj3.domain.states.gtcsm;

import java.util.Iterator;
import java.util.Vector;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.TestTemplate;
import testooj3.domain.states.ITransition;
import testooj3.domain.states.InitialState;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.TransitionFromChoice;

public class GAllPairIOTransitions extends GTCSM {
	
	private Vector<PairTransition> pairs=null;
	
	public GAllPairIOTransitions(StateMachine stm,Interface mC){
		this.stMachine=stm;
		this.tTemplates=new Vector<TestTemplate>();
		this.mClass=mC;
		pairs=this.buildPairs();
	}

	

	@Override
	protected Vector<TestTemplate> genTestTemplates() {
		Vector<TestTemplate> result=new Vector<TestTemplate>();
		
		ITransition itAux=null;
		TestTemplate ttAux=null;
		for (Iterator it=this.stMachine.getInitialState().getOutputTransitions().iterator();it.hasNext();){
			itAux=(ITransition) it.next();
			Vector pars=this.buildPairs(itAux);
			for (Iterator it2=this.recorrer(itAux,pars,itAux,this.buildStates4Visit(this.stMachine.getStates().size())).iterator();it2.hasNext();){
				ttAux=(TestTemplate) it2.next();
				//ttAux.setConstructor((TConstructor) itAux.getEvent());
				ttAux.setName("TTS_"+(result.size()+1));
				result.add(ttAux);
			}
			System.out.println("tts:\t"+result.toString());
		}
		return result;
	}
	
	private Vector<TestTemplate>  recorrer(ITransition transition, Vector<PairTransition> pars, ITransition transConstructor, Vector<State> stsXVisitar) {
		Vector<TestTemplate> result=new Vector<TestTemplate>();
		if (this.stopCondition(pars,transition)){
			TestTemplate tt=new TestTemplate();
			tt.addOperationFirst(transition.getEvent());
			result.add(tt);
		}else{
			State st=transition.getTarget();
			if (stsXVisitar.remove(st)){
				Vector<TestTemplate> ttsAux=null;
				TestTemplate ttAux=null;
				Vector<PairTransition> localPars=this.buildPairs(transition,st.getOutputTransitions());
				this.randomize(localPars);
				PairTransition parAux=null;
				boolean add=false;
				System.err.println(st.toString());
				for (Iterator iterPars=localPars.iterator();iterPars.hasNext();){
					parAux=(PairTransition) iterPars.next();
					add=pars.remove(parAux);
					
					ttsAux=this.recorrer(parAux.fin, pars, transConstructor,stsXVisitar);
					
					if (!(parAux.ini instanceof TransitionFromChoice)){
						for (Iterator it2=ttsAux.iterator();it2.hasNext();){
							ttAux=((TestTemplate) it2.next());
							ttAux.addOperationFirst(parAux.ini.getEvent());
						}
					}
					
					result.addAll(ttsAux);
					
					if (add)
						pars.add(parAux);
				}
				stsXVisitar.add(st);
			}
			
			
			/*for (Iterator it=st.getOutputTransitions().iterator();it.hasNext();) {
				transAux=(ITransition) it.next();
				
				if (pars.remove(new PairTransition(transition,transAux))){
				//if (this.pairs.contains(new PairTransition(transition,transAux))){
					//pars.remove(new PairTransition(transition,transAux));
					ttsAux=this.recorrer(transAux, pars,transConstructor);
					if (!(transAux instanceof TransitionFromChoice)){
						for (Iterator it2=ttsAux.iterator();it2.hasNext();){
							ttAux=((TestTemplate) it2.next());
							ttAux.addMethodFirst((TMethod) transAux.getEvent());
							//result.add(ttAux);
						}
					}
					if (ttsAux.isEmpty() && (!pars.contains(new PairTransition(transition,transAux))) && this.pairs.contains(new PairTransition(transition,transAux)))
						pars.add(new PairTransition(transition,transAux));
					else
						result.addAll(ttsAux);
					//result.addAll(ttsAux);
					//pairs.add(new PairTransition(transition,transAux));
				}
				//quizá debería añadir las transiciones eliminadas?
			}*/
		}
		return result;
	}
	
	private Vector <State> buildStates4Visit(int n){
		Vector <State> sts=new Vector<State>();
		State st=null;
		for (Iterator it=this.stMachine.getStates().values().iterator();it.hasNext();){
			st=(State) it.next();
			if (!(st instanceof InitialState))
				for (int i=0;i<n;i++)
					sts.add(st);
		}
		return sts;
	}

	
	private boolean stopCondition(Vector pars, ITransition trans){
		if (!pars.isEmpty()){ //|| (trans.getSource() instanceof InitialState)){
			/*for (Iterator it=pars.iterator();it.hasNext();){
				PairTransition pt=(PairTransition) it.next();
				if ((!(pt.ini.getSource() instanceof InitialState)))
					return false;
			}*/
			return false;
			//return false;
		}
		else return true;
	}
	
	private Vector<PairTransition> buildPairs(ITransition it, Vector <ITransition> outputTrans){
		Vector <PairTransition> result=new Vector<PairTransition>();
		for (Iterator iterOut=outputTrans.iterator(); iterOut.hasNext();){
			ITransition itAux=(ITransition)iterOut.next();
			if (!itAux.equals(it))
				result.add(new PairTransition(it,itAux));
		}
		return result;
	}
	
	private  void randomize(Vector v){
		int pos=0;
		Object aux=null;
		for (int i=0;i<v.size();i++){
			pos=(int) (v.size()*Math.random());
			aux=v.get(pos);
			v.remove(pos);
			pos=(int) (v.size()*Math.random());
			v.add(pos, aux);
		}
	}

	private Vector<PairTransition> buildPairs(ITransition itAux){
		Vector<PairTransition> result=new Vector<PairTransition>();
		State stAux=null;
		ITransition inTrans=null;
		
		//para cada estado
		for (Iterator iterSt=this.stMachine.getStates().values().iterator();iterSt.hasNext();){
			stAux=(State) iterSt.next();
			if (!(stAux instanceof InitialState))
				for (Iterator iterIn=this.stMachine.getInputTransitions(stAux).iterator(); iterIn.hasNext();){
					inTrans=(ITransition)iterIn.next();
					if (!(inTrans.getSource() instanceof InitialState) || inTrans.equals(itAux))
						for (Iterator iterOut=stAux.getOutputTransitions().iterator(); iterOut.hasNext();)
							result.add(new PairTransition(inTrans,(ITransition)iterOut.next()));
				}
		}
		return result;
	}
	
	private Vector<PairTransition> buildPairs() {
		Vector<PairTransition> result=new Vector<PairTransition>();
		State stAux=null;
		ITransition inTrans=null;
		
		//para cada estado
		for (Iterator iterSt=this.stMachine.getStates().values().iterator();iterSt.hasNext();){
			stAux=(State) iterSt.next();
			if (!(stAux instanceof InitialState))
				for (Iterator iterIn=this.stMachine.getInputTransitions(stAux).iterator(); iterIn.hasNext();){
					inTrans=(ITransition)iterIn.next();
					if (!(inTrans.getSource() instanceof InitialState))
						for (Iterator iterOut=stAux.getOutputTransitions().iterator(); iterOut.hasNext();)
							result.add(new PairTransition(inTrans,(ITransition)iterOut.next()));
				}
		}
		return result;
	}

	@Override
	public String toString() {
		return "All Pair I/O Transitions";
	}
	
	//clase auxiliar para facilitar el manejo de pares de transiciones
	/**
	 * @author  andres
	 */
	class PairTransition implements Cloneable{
		ITransition ini;
		ITransition fin;
		
		public PairTransition(ITransition i,ITransition f){
			this.ini=i;
			this.fin=f;
		}
		
		public boolean equals(Object ob){
			if (ob instanceof PairTransition){
				PairTransition pt=(PairTransition) ob;
				return pt.ini.equals(this.ini)&&pt.fin.equals(this.fin);
			}else return false;
		}
		
		public Object clone(){
			return new PairTransition(ini,fin);
		}
		
		public String toString(){
			return this.ini.toString()+"*"+this.fin.toString();
		}
	}

}

package testooj3.domain.states.gtcsm;

import java.util.Vector;
import testooj3.domain.Interface;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.states.StateMachine;

/**
 * Generate Test Templates from State Machine
 */
public abstract class GTCSM {
	protected StateMachine stMachine;
	protected Interface mClass;
	protected Vector <TestTemplate> tTemplates;
	
	protected abstract Vector<TestTemplate> genTestTemplates();
	
	public Vector<TestTemplate> getTestTemplates() {
		if (this.tTemplates==null)
			this.tTemplates=this.genTestTemplates();
		return this.tTemplates;
	}
	
	public void setStMachine(StateMachine stm){
		this.tTemplates=null;
		this.stMachine=stm;
	}
	
	public StateMachine getStMachine(){
		return this.stMachine;
	}
	
	public void setTClass(Interface cls){
		this.tTemplates=null;
		this.mClass=cls;
	}
	
	public Interface getTClass(){
		return this.mClass;
	}
	
	public abstract String toString();
	
}

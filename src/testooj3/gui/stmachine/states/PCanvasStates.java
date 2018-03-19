package testooj3.gui.stmachine.states;

import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEventListener;
import java.util.Hashtable;
import java.util.Iterator;
import testooj3.domain.states.ITransition;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.gui.stmachine.common.PCanvasStateMachine;
import testooj3.gui.stmachine.common.PState;
import testooj3.gui.stmachine.common.PStatesFactory;
import testooj3.gui.stmachine.common.PTransition;
import testooj3.gui.stmachine.common.PTransitionFactory;
import testooj3.gui.stmachine.common.StateDragHandler;
import testooj3.gui.stmachine.common.TransitionDragHandler;

/**
 * @author  andres
 */
public class PCanvasStates extends PCanvasStateMachine {
	private JSplitStateMachine parentPanel;

	//private StateMachine stateMachine;  //  @jve:decl-index=0:
	//private PLayer statesLayer;  //  @jve:decl-index=0:
	//private PLayer transitionsLayer;  //  @jve:decl-index=0:

	public PCanvasStates(StateMachine dsm){
		super(dsm);
		this.stateDragHandler.setEditable(false);
		/*stMachine=dsm;
		initialize();*/
	}
	
	public PCanvasStates(){
	}
	
	public void setParentPanel(JSplitStateMachine splitStateMachine) {
		this.parentPanel=splitStateMachine;
	}
	
	public JSplitStateMachine getParentSplitPanel(){
		return (JSplitStateMachine)this.parentPanel;
	}
	
}

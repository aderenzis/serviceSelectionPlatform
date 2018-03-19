package testooj3.gui.stmachine.states;

import javax.swing.JTabbedPane;

import testooj3.domain.states.StateMachine;
import testooj3.gui.stmachine.common.PCanvasStateMachine;
import testooj3.persistence.Agente;

import java.awt.Dimension;

public class JTPStates extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public JTPStates() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(764, 352);

	}

	public void add(StateMachine stateMachine) {
		JSplitStateMachine j=new JSplitStateMachine();
		j.setStateMachine(stateMachine);
		this.add(j);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

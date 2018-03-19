package testooj3.gui.stmachine.states;

import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolox.swing.PScrollPane;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import testooj3.domain.states.StateMachine;
import testooj3.gui.stmachine.common.PCanvasStateMachine;

/**
 * @author  andres
 */
public class JSplitStateMachine extends JSplitPane {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JEditorPane jepMsg = null;
	private PScrollPane pScrollPane = null;
	private PCanvasStates pCanvasStates = null;
	/**
	 * This is the default constructor
	 */
	public JSplitStateMachine() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(562, 388);

		this.setTopComponent(getPScrollPane());
		this.setBottomComponent(getJScrollPane());
		this.setDividerLocation(200);
		this.setOrientation(JSplitPane.VERTICAL_SPLIT);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJepMsg());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jepMsg	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJepMsg() {
		if (jepMsg == null) {
			jepMsg = new JEditorPane();
			jepMsg.setContentType("text/html");
		}
		return jepMsg;
	}

//	public void setStateMachine(StateMachine stateMachine) {
//		this.pCanvasStates=new PCanvasStates(stateMachine);
//		//this.pCanvasStates.setStateMachine(stateMachine);
//		this.log(stateMachine.toHTMLTable());
//	}
	public void setStateMachine(StateMachine stm) {
		this.pCanvasStates=new PCanvasStates(stm);
		pCanvasStates.setParentPanel(this);
		pScrollPane.setViewportView(this.pCanvasStates);
		this.log(stm.toHTMLTable());
		//this.pCanvasStates.setParentPanel(this);
		//this.pCanvasStateMachine.setStateMachine(stm);
	}
	
	public void logError(String msg) {
        msg="<font color='red'>" + msg + "</font>"+jepMsg.getText().trim();
        this.jepMsg.setText(msg);
        jepMsg.select(0, 0);  
    }
    
    public void log(String msg) {
        msg="<font>" + msg + "</font>"+jepMsg.getText().trim();
        this.jepMsg.setText(msg);
        jepMsg.select(0, 0);  
    }

	/**
	 * This method initializes pScrollPane	
	 * 	
	 * @return edu.umd.cs.piccolox.swing.PScrollPane	
	 */
	private PScrollPane getPScrollPane() {
		if (pScrollPane == null) {
			pScrollPane = new PScrollPane();
			pScrollPane.setViewportView(getPCanvasStates());
		}
		return pScrollPane;
	}

	/**
	 * This method initializes pCanvasStates	
	 * 	
	 * @return testooj3.gui.states.PCanvasStates	
	 */
	private PCanvasStates getPCanvasStates() {
		if (pCanvasStates == null) {
			pCanvasStates = new PCanvasStates();
		}
		return pCanvasStates;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

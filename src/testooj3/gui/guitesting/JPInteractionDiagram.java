package testooj3.gui.guitesting;

import edu.umd.cs.piccolox.swing.PScrollPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import testooj3.domain.Interface;
import testooj3.domain.TOperation;

/**
 * @author  andres
 */
public class JPInteractionDiagram extends JPanel {

	private static final long serialVersionUID = 1L;
	private PScrollPane pScrollPane = null;
	private PCanvasInteractionDiagram pCanvasInteractionDiagram = null;
	private JFGuiTesting parentWindow=null;
	private JScrollPane jScrollPane = null;
	private JEditorPane jepMsg = null;
	/**
	 * This is the default constructor
	 */
	public JPInteractionDiagram(JFGuiTesting pw) {
		super();
		this.parentWindow=pw;
		initialize();
		this.pCanvasInteractionDiagram.setParentPanel(this);
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints4.gridx = 1;
		gridBagConstraints4.gridy = 0;
		gridBagConstraints4.ipadx = 150;
		gridBagConstraints4.ipady = 1;
		gridBagConstraints4.weightx = 1.0;
		gridBagConstraints4.anchor = GridBagConstraints.EAST;
		gridBagConstraints4.insets = new Insets(3, 2, 1, 2);
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.insets = new Insets(1, 2, 0, 7);
		gridBagConstraints3.gridy = 0;
		gridBagConstraints3.ipadx = 13;
		gridBagConstraints3.ipady = 0;
		gridBagConstraints3.gridx = 2;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = GridBagConstraints.BOTH;
		gridBagConstraints2.gridwidth = 3;
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 2;
		gridBagConstraints2.ipadx = 2;
		gridBagConstraints2.ipady = 2;
		gridBagConstraints2.weightx = 1.0;
		gridBagConstraints2.weighty = 1.0;
		gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.BOTH;
		gridBagConstraints1.gridwidth = 4;
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 1;
		gridBagConstraints1.ipadx = 2;
		gridBagConstraints1.ipady = 200;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.weighty = 1.0;
		gridBagConstraints1.insets = new Insets(2, 2, 2, 2);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 1, 1);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 1;
		gridBagConstraints.ipady = 1;
		gridBagConstraints.gridx = 0;
		this.setLayout(new GridBagLayout());
		this.setSize(587, 347);
		this.add(getPScrollPane(), gridBagConstraints1);
		this.add(getJScrollPane(), gridBagConstraints2);
	}

	protected void addInit() {
		Instance st=new ActorInstance();
		this.pCanvasInteractionDiagram.addInstance(st);
	}

	/**
	 * This method initializes pScrollPane	
	 * 	
	 * @return edu.umd.cs.piccolox.swing.PScrollPane	
	 */
	private PScrollPane getPScrollPane() {
		if (pScrollPane == null) {
			pScrollPane = new PScrollPane();
			pScrollPane.setViewportView(getPCanvasInteractionDiagram());
		}
		return pScrollPane;
	}

	/**
	 * This method initializes pCanvasStateMachine	
	 * 	
	 * @return testooj3.gui.statemachine.PCanvasStateMachine	
	 */
	private PCanvasInteractionDiagram getPCanvasInteractionDiagram() {
		if (pCanvasInteractionDiagram == null) {
			pCanvasInteractionDiagram = new PCanvasInteractionDiagram(this);
		}
		return pCanvasInteractionDiagram;
	}

	public void addInstance(Instance selectedInstance) {
		this.pCanvasInteractionDiagram.addInstance(selectedInstance);
	}

	public void setParentWindow(JFGuiTesting window) {
		this.parentWindow=window;
	}
	
	public JFGuiTesting getParentWindow(){
		return this.parentWindow;
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
	
	public void log(String msg) {
		jepMsg.setText(msg);
	}

	public void selectMethod(TOperation selectedOperation) {
		log("Selected method: " + selectedOperation);
		this.pCanvasInteractionDiagram.selectMethod(selectedOperation);
	}

	public InteractionDiagram getInteractionDiagram() {
		return this.pCanvasInteractionDiagram.getInteractionDiagram();
	}

	public void setClass(Interface theClass) {
	}

	public void addInstance(GuiFixture fixture) {
		Instance instance=new Instance(fixture);
		this.pCanvasInteractionDiagram.addInstance(instance);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

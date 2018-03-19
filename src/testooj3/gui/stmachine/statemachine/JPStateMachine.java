package testooj3.gui.stmachine.statemachine;

import edu.umd.cs.piccolox.swing.PScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import testooj3.domain.TOperation;
import testooj3.domain.states.ChoiceState;
import testooj3.domain.states.InitialState;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.gtcsm.GAllPairIOTransitions;
import testooj3.domain.states.gtcsm.GAllStates;
import testooj3.domain.states.gtcsm.GAllTransitions;
import testooj3.domain.states.gtcsm.GTCSM;
import testooj3.gui.ITemplatesWindow;
import testooj3.gui.JDStatesDefinition;
import testooj3.gui.stmachine.common.PCanvasStateMachine;

/**
 * @author  andres
 */
public class JPStateMachine extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton jbChoice = null;
	private PScrollPane pScrollPane = null;
	private PCanvasStateMachine pCanvasStateMachine = null;
	private JDStatesDefinition parentWindow=null;
	private JScrollPane jScrollPane = null;
	private JEditorPane jepMsg = null;
	private JButton jButtonTest = null;
	private ITemplatesWindow templatesWindow;  //  @jve:decl-index=0:
	private JComboBox jComboBoxTest = null;
	/**
	 * This is the default constructor
	 */
	public JPStateMachine(JDStatesDefinition pw) {
		super();
		this.parentWindow=pw;
		initialize();
		this.pCanvasStateMachine.setParentPanel(this);
		
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
		gridBagConstraints1.gridwidth = 3;
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
		this.add(getJbChoice(), gridBagConstraints);
		this.add(getPScrollPane(), gridBagConstraints1);
		this.add(getJScrollPane(), gridBagConstraints2);
		this.add(getJButtonTest(), gridBagConstraints3);
		this.add(getJComboBoxTest(), gridBagConstraints4);
	}

	protected void addInit() {
		State st=new InitialState();
		this.pCanvasStateMachine.addState(st);
	}

	/**
	 * This method initializes jbChoice	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbChoice() {
		if (jbChoice == null) {
			jbChoice = new JButton();
			jbChoice.setText("Choice");
			jbChoice.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JDLabel jDLabel=new JDLabel(parentWindow);
					jDLabel.setVisible(true);
					
					addChoice(jDLabel.getText());
				}
			});
		}
		return jbChoice;
	}

	protected void addChoice(String label) {
		this.pCanvasStateMachine.addState(new ChoiceState(label));
	}

	/**
	 * This method initializes pScrollPane	
	 * 	
	 * @return edu.umd.cs.piccolox.swing.PScrollPane	
	 */
	private PScrollPane getPScrollPane() {
		if (pScrollPane == null) {
			pScrollPane = new PScrollPane();
			pScrollPane.setViewportView(getPCanvasStateMachine());
		}
		return pScrollPane;
	}

	/**
	 * This method initializes pCanvasStateMachine	
	 * 	
	 * @return testooj3.gui.statemachine.PCanvasStateMachine	
	 */
	private PCanvasStateMachine getPCanvasStateMachine() {
		if (pCanvasStateMachine == null) {
			pCanvasStateMachine = new PCanvasStateMachine();
		}
		return pCanvasStateMachine;
	}

	public void addState(State selectedState) {
		this.pCanvasStateMachine.addState(selectedState);
	}

	public void setParentWindow(JDStatesDefinition window) {
		this.parentWindow=window;
	}
	
	public JDStatesDefinition getParentWindow(){
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
		this.pCanvasStateMachine.selectMethod(selectedOperation);
	}

	public void setStateMachine(StateMachine stm) {
		this.pCanvasStateMachine=new PCanvasStateMachine(stm);
		pScrollPane.setViewportView(this.pCanvasStateMachine);
		this.pCanvasStateMachine.setParentPanel(this);
		//this.pCanvasStateMachine.setStateMachine(stm);
	}

	public StateMachine getStateMachine() {
		return this.pCanvasStateMachine.getStateMachine();
	}

	/**
	 * This method initializes jButtonTest	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonTest() {
		if (jButtonTest == null) {
			jButtonTest = new JButton();
			jButtonTest.setText("Gen Test");
			jButtonTest.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					generarTest();
				}
			});
		}
		return jButtonTest;
	}

	protected void generarTest() {
		GTCSM g=(GTCSM)this.getJComboBoxTest().getSelectedItem();
		g.setTClass(this.parentWindow.getTClass());
		g.setStMachine(this.getStateMachine());
		this.templatesWindow.setTemplates(g.getTestTemplates());
		System.out.println("\n\nGenerated TestTemplates:\n"+g.getTestTemplates().toString());
	}

	public void setTemplatesWindow(ITemplatesWindow templatesWindow) {
		this.templatesWindow=templatesWindow;
	}

	/**
	 * This method initializes jComboBoxTest	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxTest() {
		if (jComboBoxTest == null) {
			jComboBoxTest = new JComboBox();
			jComboBoxTest.addItem(new GAllStates(pCanvasStateMachine.getStateMachine(),this.parentWindow.getTClass()));
			jComboBoxTest.addItem(new GAllTransitions(pCanvasStateMachine.getStateMachine(),this.parentWindow.getTClass()));
			jComboBoxTest.addItem(new GAllPairIOTransitions(pCanvasStateMachine.getStateMachine(),this.parentWindow.getTClass()));
		}
		return jComboBoxTest;
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"

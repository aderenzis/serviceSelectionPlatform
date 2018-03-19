package testooj3.gui.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import testooj3.domain.states.State;
import testooj3.gui.JDStatesDefinition;

/**
 * @author  andres
 */
public class JPStatesDefinition extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel1 = null;
	private JTextField jtfStateName = null;
	private JLabel jLabel2 = null;
	private JEditorPane jepStateDescription = null;
	private JButton jbAddState = null;
	private JButton jbRemoveState = null;
	private State state;
	private JDStatesDefinition parentWindow;  //  @jve:decl-index=0:
	private JScrollPane jScrollPane = null;

	/**
	 * This is the default constructor
	 */
	public JPStatesDefinition() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.fill = GridBagConstraints.BOTH;
		gridBagConstraints5.gridwidth = 3;
		gridBagConstraints5.gridx = 0;
		gridBagConstraints5.gridy = 2;
		gridBagConstraints5.ipady = 64;
		gridBagConstraints5.weightx = 1.0;
		gridBagConstraints5.weighty = 1.0;
		gridBagConstraints5.insets = new Insets(3, 10, 3, 14);
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints4.gridy = 3;
		gridBagConstraints4.ipadx = 1;
		gridBagConstraints4.ipady = 1;
		gridBagConstraints4.anchor = GridBagConstraints.WEST;
		gridBagConstraints4.gridx = 2;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.gridy = 3;
		gridBagConstraints3.ipadx = 1;
		gridBagConstraints3.ipady = 1;
		gridBagConstraints3.anchor = GridBagConstraints.WEST;
		gridBagConstraints3.gridwidth = 1;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.insets = new Insets(5, 10, 2, 0);
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 1;
		gridBagConstraints2.ipadx = 5;
		gridBagConstraints2.ipady = 5;
		gridBagConstraints2.anchor = GridBagConstraints.WEST;
		gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints2.gridwidth = 1;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.gridwidth = 1;
		gridBagConstraints1.gridx = 2;
		gridBagConstraints1.gridy = 0;
		gridBagConstraints1.ipadx = 475;
		gridBagConstraints1.ipady = 0;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.insets = new Insets(15, 5, 5, 10);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(15, 10, 5, 5);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.gridx = 0;
		jLabel2 = new JLabel();
		jLabel2.setText("State description");
		this.setSize(585, 200);
		this.setLayout(new GridBagLayout());
		this.add(getJLabel1(), gridBagConstraints);
		this.add(getJtfStateName(), gridBagConstraints1);
		this.add(jLabel2, gridBagConstraints2);
		this.add(getJbAddState(), gridBagConstraints3);
		this.add(getJbRemoveState(), gridBagConstraints4);
		this.add(getJScrollPane(), gridBagConstraints5);
	}

	/**
	 * This method initializes jLabel1	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("State name");
		}
		return jLabel1;
	}

	/**
	 * This method initializes jtfStateName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfStateName() {
		if (jtfStateName == null) {
			jtfStateName = new JTextField();
		}
		return jtfStateName;
	}

	/**
	 * This method initializes jepStateDescription	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJepStateDescription() {
		if (jepStateDescription == null) {
			jepStateDescription = new JEditorPane();
		}
		return jepStateDescription;
	}

	/**
	 * This method initializes jbAddState	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAddState() {
		if (jbAddState == null) {
			jbAddState = new JButton();
			jbAddState.setText("Add state");
			jbAddState.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					addState();
				}
			});
		}
		return jbAddState;
	}
	
	   protected void addState() {
	        try {
	            State st=new State(this.jtfStateName.getText(), this.jepStateDescription.getText());
	            this.parentWindow.add(st);
	          }
	          catch (Exception ex) 
	          {
	            this.parentWindow.logError(ex.toString());
	          }
	    }

	/**
	 * This method initializes jbRemoveState	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbRemoveState() {
		if (jbRemoveState == null) {
			jbRemoveState = new JButton();
			jbRemoveState.setText("Remove state");
			jbRemoveState.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					removeState();
				}
			});
		}
		return jbRemoveState;
	}

	protected void removeState() {
		try {
            State st=new State(this.jtfStateName.getText(), this.jepStateDescription.getText());
            this.parentWindow.removeState(st);
          }
          catch (Exception ex) 
          {
            this.parentWindow.logError(ex.toString());
          }
	}

	public String getStateName() {
		 return this.jtfStateName.getText();
	}

	public String getStateDescription() {
		return this.jepStateDescription.getText();
	}

	public void setState(State selectedState) {
		this.state=selectedState;
        this.jtfStateName.setText(this.state.getName());
        this.jepStateDescription.setText(this.state.getDescription());
	}

	public void setParentWindow(JDStatesDefinition window) {
		this.parentWindow=window;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJepStateDescription());
		}
		return jScrollPane;
	}

}  //  @jve:decl-index=0:visual-constraint="13,10"

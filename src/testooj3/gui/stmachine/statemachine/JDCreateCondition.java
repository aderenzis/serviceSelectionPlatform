package testooj3.gui.stmachine.statemachine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import testooj3.domain.states.Condition;
import testooj3.gui.components.conditiontable.JPCreateCondition;

/**
 * @author  andres
 */
public class JDCreateCondition extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPCreateCondition JPCreateCondition = null;
	private JButton jBOK = null;
	private Condition condition=null;
	private Vector methods=null;

	/**
	 * @param parent, methods
	 */

	public JDCreateCondition(JDEvent parent, Vector methods) {
		super(parent);
		this.methods=methods;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(472, 318);
		this.setContentPane(getJPCreateCondition());
		this.setTitle("Create Condition");
	}

	/**
	 * This method initializes JPCreateCondition	
	 * 	
	 * @return testooj3.gui.components.conditiontable.JPCreateCondition	
	 */
	private JPCreateCondition getJPCreateCondition() {
		if (JPCreateCondition == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			JPCreateCondition = new JPCreateCondition(methods);
			JPCreateCondition.add(getJBOK(), gridBagConstraints);
		}
		return JPCreateCondition;
	}

	/**
	 * This method initializes jBOK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBOK() {
		if (jBOK == null) {
			jBOK = new JButton();
			jBOK.setText("OK");
			jBOK.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					condition=JPCreateCondition.getCondition();
					dispose();
				}
			});
		}
		return jBOK;
	}
	
	public Condition getCondition(){
		return this.condition;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

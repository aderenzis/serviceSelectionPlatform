package testooj3.gui.stmachine.statemachine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import testooj3.domain.Interface;
import testooj3.domain.TOperation;
import testooj3.gui.JDStatesDefinition;

/**
 * @author  andres
 */
public class JDLabel extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JLabel jLabel = null;

	private JTextField JTLabel = null;

	private JButton JBok = null;

	/**
	 * @param owner
	 */
	public JDLabel(JDStatesDefinition root) {
		super(root,true);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(488, 163);
		this.setTitle("Add Choice Label");
		this.setAlwaysOnTop(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setEnabled(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(4);
			borderLayout.setVgap(4);
			jContentPane = new JPanel();
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints41.gridy = 0;
			gridBagConstraints41.weightx = 1.0;
			gridBagConstraints41.anchor = GridBagConstraints.WEST;
			gridBagConstraints41.insets = new Insets(0, 6, 0, 6);
			gridBagConstraints41.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints4.ipadx = 0;
			gridBagConstraints4.ipady = 0;
			gridBagConstraints4.insets = new Insets(10, 0, 10, 0);
			gridBagConstraints4.gridy = 4;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(0, 6, 0, 6);
			gridBagConstraints3.ipadx = 6;
			gridBagConstraints3.ipady = 0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.insets = new Insets(0, 6, 0, 0);
			gridBagConstraints2.ipadx = 6;
			gridBagConstraints2.ipady = 0;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.ipadx = 6;
			gridBagConstraints.ipady = 0;
			gridBagConstraints.insets = new Insets(0, 6, 0, 0);
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Add Choice Name");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(getJTLabel(), gridBagConstraints3);
			jPanel.add(getJBok(), gridBagConstraints4);
			
		}
		return jPanel;
	}

	/**
	 * This method initializes JTLabel	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTLabel() {
		if (JTLabel == null) {
			JTLabel = new JTextField();
		}
		return JTLabel;
	}

	/**
	 * This method initializes JBok	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBok() {
		if (JBok == null) {
			JBok = new JButton();
			JBok.setText("OK");
			JBok.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return JBok;
	}
	
	public String getText() {
		return this.JTLabel.getText();
	}

	/**
	 * This method initializes JComboMethods	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
}  //  @jve:decl-index=0:visual-constraint="10,10"

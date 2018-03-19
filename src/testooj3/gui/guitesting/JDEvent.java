package testooj3.gui.guitesting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Method;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import testooj3.auxiliares.OrderingAlgorithms;
import testooj3.domain.Interface;
import testooj3.domain.Operation;

/**
 * @author  andres
 */
public class JDEvent extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JLabel jLabel = null;

	private JButton JBok = null;

	private JComboBox jComboMethods = null;
	
	private Instance instance;

	private JCheckBox jchisAssertMessagge = null;

	private JScrollPane jScrollPane = null;

	private JEditorPane jepOracle = null;


	/**
	 * @param owner
	 */
	public JDEvent(JFGuiTesting root, Instance target, boolean isOracle) {
		super(root,true);
		initialize();
		this.jchisAssertMessagge.setSelected(isOracle);
		this.instance=target;
		Vector<Operation> mm=this.instance.fixture.getMethods();
		OrderingAlgorithms.bubbleSort(mm);
		for (Operation m : mm)
			jComboMethods.addItem(m.getId());
	}

	public JDEvent(JFGuiTesting root, IMessagge messagge) {
		super(root, true);
		initialize();
		this.jchisAssertMessagge.setSelected(true);
		this.instance=messagge.getTarget();
		Vector<Operation> mm=this.instance.fixture.getMethods();
		OrderingAlgorithms.bubbleSort(mm);
		for (Operation m : mm)
			jComboMethods.addItem(m.getId());
		this.jepOracle.setText(messagge.getId());
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(488, 257);
		this.setTitle("Distpatch Event");
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
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 6;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.gridx = 1;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 5;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.anchor = GridBagConstraints.CENTER;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints41.gridy = 0;
			gridBagConstraints41.weightx = 1.0;
			gridBagConstraints41.anchor = GridBagConstraints.WEST;
			gridBagConstraints41.insets = new Insets(0, 6, 0, 6);
			gridBagConstraints41.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints4.ipadx = 0;
			gridBagConstraints4.ipady = 0;
			gridBagConstraints4.insets = new Insets(10, 0, 10, 0);
			gridBagConstraints4.gridy = 5;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.ipadx = 6;
			gridBagConstraints.ipady = 0;
			gridBagConstraints.insets = new Insets(0, 6, 0, 0);
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Messagge:");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(getJBok(), gridBagConstraints4);
			jPanel.add(getJComboMethods(), gridBagConstraints41);
			jPanel.add(getJchisAssertMessagge(), gridBagConstraints11);
			jPanel.add(getJScrollPane(), gridBagConstraints2);
			
		}
		return jPanel;
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
		
	public void setDispatchEnabled(boolean enabled){
		this.jLabel.setEnabled(enabled);
		this.jComboMethods.setEnabled(enabled);
	}
		
	public String getMessagge() {
		String result=null;
		if (this.jchisAssertMessagge.isSelected()) {
			result=this.jepOracle.getText();
		} else {
			result=this.jComboMethods.getSelectedItem().toString();
		}
		return result;
	}

	/**
	 * This method initializes JComboMethods	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboMethods() {
		//si carga los constructores, no carga el resto de metodos
		if (jComboMethods == null) {
			jComboMethods = new JComboBox();
		}
		return jComboMethods;
	}

	/**
	 * This method initializes jchisAssertMessagge	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJchisAssertMessagge() {
		if (jchisAssertMessagge == null) {
			jchisAssertMessagge = new JCheckBox();
			jchisAssertMessagge.setText("Assert message");
		}
		return jchisAssertMessagge;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJepOracle());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jepOracle	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJepOracle() {
		if (jepOracle == null) {
			jepOracle = new JEditorPane();
		}
		return jepOracle;
	}
	
	public boolean isOracleMessagge() {
		return this.jchisAssertMessagge.isSelected();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

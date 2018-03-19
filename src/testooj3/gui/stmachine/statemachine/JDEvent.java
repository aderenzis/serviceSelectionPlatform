package testooj3.gui.stmachine.statemachine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import testooj3.domain.Interface;
import testooj3.domain.TOperation;
import testooj3.domain.states.Condition;
import testooj3.gui.JDStatesDefinition;

/**
 * @author  andres
 */
public class JDEvent extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JLabel jLabel = null;

	private JLabel JLCondition = null;

	private JButton JBok = null;

	private JComboBox JComboMethods = null;
	
	private Interface mclass=null;

	private boolean constructorsLoad=false;

	private JPConditions JPConditions = null;

	/**
	 * @param owner
	 */
	public JDEvent(JDStatesDefinition root) {
		super(root,true);
		mclass=root.getMClass();
		initialize();
	}
	
	
	public JDEvent(JDStatesDefinition root, boolean cLoad) {
		super(root,true);
		this.constructorsLoad=cLoad;
		mclass=root.getMClass();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(488, 163);
		this.setTitle("Distpatch Event");
		this.setAlwaysOnTop(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setConditionEnabled(false);
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
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.insets = new Insets(0, 6, 0, 0);
			gridBagConstraints2.ipadx = 6;
			gridBagConstraints2.ipady = 0;
			gridBagConstraints2.gridy = 1;
			JLCondition = new JLabel();
			JLCondition.setText("Conditions");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.ipadx = 6;
			gridBagConstraints.ipady = 0;
			gridBagConstraints.insets = new Insets(0, 6, 0, 0);
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Dispatch Event");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(JLCondition, gridBagConstraints2);
			jPanel.add(getJBok(), gridBagConstraints4);
			jPanel.add(getJComboMethods(), gridBagConstraints41);
			jPanel.add(getJPConditions(), gridBagConstraints3);
			
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
	
	public void setConditionEnabled(boolean enabled){
		this.JLCondition.setEnabled(enabled);
		this.JPConditions.setEnabled(enabled);
	}
	
	public void setDispatchEnabled(boolean enabled){
		this.jLabel.setEnabled(enabled);
		this.JComboMethods.setEnabled(enabled);
	}
	
	public Vector getConditions(){
		return this.JPConditions.getConditions();
	}
	
	public TOperation getEvent(){
		return (TOperation)this.JComboMethods.getSelectedItem();
	}

	/**
	 * This method initializes JComboMethods	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboMethods() {
		//si carga los constructores, no carga el resto de metodos
		if (JComboMethods == null) {
			JComboMethods = new JComboBox();
			if (this.constructorsLoad==true){
				for (int i=0; i<mclass.constructorsSize(); i++) 
					JComboMethods.addItem(mclass.getConstructor(i));
			}
			else{
				for (int i=0; i<mclass.methodsSize(); i++) 
					JComboMethods.addItem(mclass.getMethod(i));
			}	
		}
		return JComboMethods;
	}
	
	public Vector <TOperation> getMethods() {
		Vector<TOperation> methods=new Vector();
		if (this.constructorsLoad==true)
			for (int i=0; i<mclass.constructorsSize(); i++) 
				methods.add(mclass.getConstructor(i));
		else
			for (int i=0; i<mclass.methodsSize(); i++) 
				methods.add(mclass.getMethod(i));
		return methods;
	}


	/**
	 * This method initializes JPConditions	
	 * 	
	 * @return testooj3.gui.stmachine.statemachine.JPConditions	
	 */
	private JPConditions getJPConditions() {
		if (JPConditions == null) {
			JPConditions = new JPConditions(this);
		}
		return JPConditions;
	}


	public void setConditions(Vector<Condition> conditions) {
		this.JPConditions.setConditions(conditions);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

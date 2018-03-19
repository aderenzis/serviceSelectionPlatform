package testooj3.gui.stmachine.statemachine;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import testooj3.domain.states.Condition;

/**
 * @author  andres
 */
public class JPConditions extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList jList = null;
	private JPanel jPanel = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JDEvent parent=null;
	private Vector<Condition> conditions;  //  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 */
	public JPConditions(JDEvent parent) {
		super();
		this.parent=parent;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints1.anchor = GridBagConstraints.CENTER;
		gridBagConstraints1.ipadx = 4;
		gridBagConstraints1.ipady = 0;
		gridBagConstraints1.gridy = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.add(getJList(), gridBagConstraints);
		this.add(getJPanel(), gridBagConstraints1);
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setListData(getConditions());
		}
		return jList;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = -1;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = -1;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJButton(), gridBagConstraints2);
			jPanel.add(getJButton1(), gridBagConstraints3);
		}
		return jPanel;
	}
	
	public void setEnabled(boolean ena){
		super.setEnabled(ena);
		this.jButton.setEnabled(ena);
		this.jButton1.setEnabled(ena);
		this.jList.setEnabled(ena);
		this.jPanel.setVisible(ena);
		
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("+");
			jButton.setToolTipText("add condition");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JDCreateCondition cc=new JDCreateCondition(parent,parent.getMethods());
					cc.setModal(true);
					cc.setVisible(true);
					if (conditions==null)
						conditions=new Vector<Condition>();
					//System.out.println("add: "+cc.getCondition().toString());
					conditions.add(cc.getCondition());
					jList.setListData(conditions);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("-");
			jButton1.setToolTipText("remove condition selected");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int [] indices=jList.getSelectedIndices();
					for (int i=0;i<indices.length;i++)
						conditions.remove(indices[i]);
					jList.setListData(conditions);
				}
			});
		}
		return jButton1;
	}

	public Vector getConditions() {
		if (conditions==null)
			conditions=new Vector<Condition>();
		return conditions;
	}

	public void setConditions(Vector<Condition> conditions2) {
		if (conditions2==null)
			this.conditions=new Vector<Condition>();
		else
			this.conditions=conditions2;
		
		this.jList.setListData(this.conditions);
	}

}

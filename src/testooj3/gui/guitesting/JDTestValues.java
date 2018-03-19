package testooj3.gui.guitesting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import testooj3.domain.PrimitiveValue;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestValue;

/**
 * @author  andres
 */
public class JDTestValues extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private Operation method;
	private Instance instance;
	private JLabel jLabel = null;
	private JScrollPane jspParameters = null;
	private JScrollPane jspValues = null;
	private JList jlParameters = null;
	private JList jlValues = null;
	private JLabel jLabel1 = null;
	private JTextField jtfValue = null;
	private JButton jbAdd = null;
	private JButton jbOk = null;
	private JButton jbRemove = null;
	private DefaultListModel modelParameters=null;
	private DefaultListModel modelValues=null;
	private TParameter selectedParameter;
	/**
	 * @param owner
	 */
	public JDTestValues(Frame owner, Instance instance, Operation method) {
		super(owner);
		this.method=method;
		this.instance=instance;
		initialize();
		enableAdditions(false);
		this.setTitle(this.instance.getName() + " :: " + this.method.getId());
		this.modelParameters=new DefaultListModel();
		this.modelValues=new DefaultListModel();
		this.jlParameters.setModel(this.modelParameters);
		this.jlValues.setModel(this.modelValues);
		loadParameters();
	}

	private void enableAdditions(boolean action) {
		this.jbAdd.setEnabled(action);
		this.jtfValue.setEnabled(action);
		this.jbRemove.setEnabled(action);
	}

	private void loadParameters() {
		for(TParameter p : this.method.getParametros()) {
			String par=p.getTipo();
			this.modelParameters.addElement(par);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(505, 302);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(6, 110, 59, 23));
			jLabel1.setText("Values");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(6, 6, 116, 22));
			jLabel.setText("Parameters");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJspParameters(), null);
			jContentPane.add(getJspValues(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJtfValue(), null);
			jContentPane.add(getJbAdd(), null);
			jContentPane.add(getJbOk(), null);
			jContentPane.add(getJbRemove(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jspParameters	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspParameters() {
		if (jspParameters == null) {
			jspParameters = new JScrollPane();
			jspParameters.setBounds(new Rectangle(6, 33, 477, 72));
			jspParameters.setViewportView(getJlParameters());
		}
		return jspParameters;
	}

	/**
	 * This method initializes jspValues	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspValues() {
		if (jspValues == null) {
			jspValues = new JScrollPane();
			jspValues.setBounds(new Rectangle(6, 140, 478, 96));
			jspValues.setViewportView(getJlValues());
		}
		return jspValues;
	}

	/**
	 * This method initializes jlParameters	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJlParameters() {
		if (jlParameters == null) {
			jlParameters = new JList();
			jlParameters.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					selectParameter();
				}
			});
		}
		return jlParameters;
	}

	protected void selectParameter() {
		int index=this.jlParameters.getSelectedIndex();
		if (index==-1) return;
		this.enableAdditions(true);
		this.selectedParameter=this.method.getParametro(index);
		showTestValues();
	}

	private void showTestValues() {
		this.modelValues.removeAllElements();
		for (int i=0; i<this.selectedParameter.getTestValues().length; i++) {
			TestValue tv = this.selectedParameter.getTestValue(i);
			this.modelValues.addElement(tv.getValue());
		}
	}

	/**
	 * This method initializes jlValues	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJlValues() {
		if (jlValues == null) {
			jlValues = new JList();
		}
		return jlValues;
	}

	/**
	 * This method initializes jtfValue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfValue() {
		if (jtfValue == null) {
			jtfValue = new JTextField();
			jtfValue.setBounds(new Rectangle(69, 110, 351, 24));
		}
		return jtfValue;
	}

	/**
	 * This method initializes jbAdd	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAdd() {
		if (jbAdd == null) {
			jbAdd = new JButton();
			jbAdd.setBounds(new Rectangle(424, 109, 58, 22));
			jbAdd.setText("Add");
			jbAdd.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					addValue();
				}
			});
		}
		return jbAdd;
	}

	protected void addValue() {
		String sValue=this.jtfValue.getText();
		TestValue value=new PrimitiveValue(sValue);
		this.selectedParameter.addTestValue(value);
		this.showTestValues();
	}

	/**
	 * This method initializes jbOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbOk() {
		if (jbOk == null) {
			jbOk = new JButton();
			jbOk.setBounds(new Rectangle(7, 237, 101, 24));
			jbOk.setText("Ok");
			jbOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					exit();
				}
			});
		}
		return jbOk;
	}

	protected void exit() {
		setVisible(false);
	}

	/**
	 * This method initializes jbRemove	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbRemove() {
		if (jbRemove == null) {
			jbRemove = new JButton();
			jbRemove.setBounds(new Rectangle(375, 239, 107, 22));
			jbRemove.setText("Remove");
			jbRemove.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					removeValue();
				}
			});
		}
		return jbRemove;
	}

	protected void removeValue() {
		int index=this.jlValues.getSelectedIndex();
		if (index==-1)
			return;
		this.selectedParameter.removeTestValue(index);
		this.showTestValues();
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

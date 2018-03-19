package testooj3.gui.compatibility;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import testooj3.domain.Interface;
import testooj3.domain.Operation;
import testooj3.gui.components.ILogWindow;

/**
 * @author  andres
 */
public class JDOracleComparator extends JDialog implements ILogWindow {

	private JPanel jContentPane = null;
	private Interface a;
	private Interface b;
	private JSplitPane jSplitPane = null;
	private JPOracleDescription jPOracleDescriptionA = null;
	private JPOracleDescription jPOracleDescriptionB = null;
	private JScrollPane jScrollPane = null;
	private JEditorPane jepLog = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jtSelectedCompatibilities = null;
	private JButton jbAdaptPrecode = null;
	private JButton jbAdaptPostcode = null;
	private JPanel jPanelLeft = null;
	public JDOracleComparator() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDOracleComparator(Dialog arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDOracleComparator(Dialog arg0, boolean arg1)
			throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDOracleComparator(Frame arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDOracleComparator(Frame arg0, boolean arg1)
			throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDOracleComparator(Dialog arg0, String arg1)
			throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDOracleComparator(Dialog arg0, String arg1, boolean arg2)
			throws HeadlessException {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDOracleComparator(Frame arg0, String arg1) throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDOracleComparator(Frame arg0, String arg1, boolean arg2)
			throws HeadlessException {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDOracleComparator(Dialog arg0, String arg1, boolean arg2,
			GraphicsConfiguration arg3) throws HeadlessException {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDOracleComparator(Frame arg0, String arg1, boolean arg2,
			GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(941, 600);
		this.setTitle("Oracle comparator");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.ipadx = 2;
			gridBagConstraints5.ipady = 2;
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.weighty = 1.0;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridwidth = 3;
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.ipadx = 1;
			gridBagConstraints4.ipady = 1;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.ipadx = 5;
			gridBagConstraints3.ipady = 5;
			gridBagConstraints3.weightx = 10.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.gridwidth = 2;
			gridBagConstraints3.insets = new Insets(5, 2, 3, 5);
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJSplitPane(), gridBagConstraints3);
			jContentPane.add(getJScrollPane(), gridBagConstraints4);
			jContentPane.add(getJPanelLeft(), gridBagConstraints5);
		}
		return jContentPane;
	}

	public void setA(Interface a) {
		try {
			this.a=a;			
			this.jPOracleDescriptionA.setClassLabel(this.a.getName());
			this.a.loadDescription();
		} catch (Exception e) {
			logError(e.toString());
		}
	}

	public void setB(Interface b) {
		try {
			this.b=b;
			this.jPOracleDescriptionB.setClassLabel(this.b.getName());
			this.b.loadDescription();
		} catch (Exception e) {
			logError(e.toString());
		}
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setLeftComponent(getJPOracleDescriptionA());
			jSplitPane.setRightComponent(getJPOracleDescriptionB());
			jSplitPane.setDividerLocation(300);
		}
		return jSplitPane;
	}

	/**
	 * This method initializes JPOracleDescriptionA	
	 * 	
	 * @return testooj3.gui.compatibility.JPOracleDescription	
	 */
	private JPOracleDescription getJPOracleDescriptionA() {
		if (jPOracleDescriptionA == null) {
			jPOracleDescriptionA = new JPOracleDescription();
		}
		return jPOracleDescriptionA;
	}

	/**
	 * This method initializes jPOracleDescriptionB	
	 * 	
	 * @return testooj3.gui.compatibility.JPOracleDescription	
	 */
	private JPOracleDescription getJPOracleDescriptionB() {
		if (jPOracleDescriptionB == null) {
			jPOracleDescriptionB = new JPOracleDescription();
		}
		return jPOracleDescriptionB;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJepLog());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jepLog	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJepLog() {
		if (jepLog == null) {
			jepLog = new JEditorPane();
			jepLog.setContentType("text/html");
		}
		return jepLog;
	}
	
	public void logError(String msg) {
        msg="<font color='red'>" + msg + "</font>"+jepLog.getText().trim();
        this.jepLog.setText(msg);
        jepLog.select(0, 0);  
    }
	
	public void log(String msg) {
        msg="<font color='blue'>" + msg + "</font>"+jepLog.getText().trim();
        this.jepLog.setText(msg);
        jepLog.select(0, 0);  
    }

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJtSelectedCompatibilities());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jtSelectedCompatibilities	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJtSelectedCompatibilities() {
		if (jtSelectedCompatibilities == null) {
			jtSelectedCompatibilities = new JTable();
			jtSelectedCompatibilities.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					operationOracle();
				}
			});
		}
		return jtSelectedCompatibilities;
	}

	public void setTableModel(NonEditableDefaultTableModel model) {
		this.jtSelectedCompatibilities.setModel(model);
	}

	protected void operationOracle() {
		try {
			int row=this.jtSelectedCompatibilities.getSelectedRow();
			int col=this.jtSelectedCompatibilities.getSelectedColumn();
			if (row==-1 || col==-1) {
				logError("Invalid selection");
				return;
			}
			String aMethodName=this.jtSelectedCompatibilities.getValueAt(row, 0).toString();
			String bMethodName=this.jtSelectedCompatibilities.getValueAt(row, 1).toString();
			Operation aMethod=a.findMethodBySignature(aMethodName);
			Operation bMethod=b.findMethodBySignature(bMethodName);
			this.jPOracleDescriptionA.setClassAndMethod(a, aMethod);
			this.jPOracleDescriptionB.setClassAndMethod(b, bMethod);
		} catch (Exception e) {
			logError(e.toString());
		}
	}

	/**
	 * This method initializes jbAdatpPrecode	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAdaptPrecode() {
		if (jbAdaptPrecode == null) {
			jbAdaptPrecode = new JButton();
			jbAdaptPrecode.setText("Adapt precode");
			jbAdaptPrecode.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					adaptPrecode();
				}
			});
		}
		return jbAdaptPrecode;
	}

	protected void adaptPrecode() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method initializes jbAdaptPostcode	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAdaptPostcode() {
		if (jbAdaptPostcode == null) {
			jbAdaptPostcode = new JButton();
			jbAdaptPostcode.setText("Adapt postcode");
		}
		return jbAdaptPostcode;
	}

	/**
	 * This method initializes jPanelLeft	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelLeft() {
		if (jPanelLeft == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = GridBagConstraints.EAST;
			gridBagConstraints2.weightx = 0.0;
			gridBagConstraints2.fill = GridBagConstraints.NONE;
			gridBagConstraints2.ipadx = 1;
			gridBagConstraints2.ipady = 1;
			gridBagConstraints2.gridy = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.anchor = GridBagConstraints.EAST;
			gridBagConstraints1.fill = GridBagConstraints.NONE;
			gridBagConstraints1.weightx = 0.0;
			gridBagConstraints1.ipadx = 1;
			gridBagConstraints1.ipady = 1;
			gridBagConstraints1.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints1.gridy = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 0.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints.ipadx = 1;
			gridBagConstraints.ipady = 1;
			gridBagConstraints.gridx = 0;
			jPanelLeft = new JPanel();
			jPanelLeft.setLayout(new GridBagLayout());
			jPanelLeft.add(getJScrollPane1(), gridBagConstraints);
			jPanelLeft.add(getJbAdaptPostcode(), gridBagConstraints2);
			jPanelLeft.add(getJbAdaptPrecode(), gridBagConstraints1);
		}
		return jPanelLeft;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

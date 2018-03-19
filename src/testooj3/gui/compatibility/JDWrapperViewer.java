package testooj3.gui.compatibility;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import testooj3.domain.Interface;
import testooj3.domain.Operation;
import testooj3.gui.components.ILogWindow;

/**
 * @author  andres
 */
public class JDWrapperViewer extends JDialog implements ILogWindow {

	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JEditorPane jepLog = null;
	private JLabel jLabel = null;
	public JDWrapperViewer() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDWrapperViewer(Dialog arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDWrapperViewer(Dialog arg0, boolean arg1)
			throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDWrapperViewer(Frame arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDWrapperViewer(Frame arg0, boolean arg1)
			throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDWrapperViewer(Dialog arg0, String arg1)
			throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDWrapperViewer(Dialog arg0, String arg1, boolean arg2)
			throws HeadlessException {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDWrapperViewer(Frame arg0, String arg1) throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDWrapperViewer(Frame arg0, String arg1, boolean arg2)
			throws HeadlessException {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDWrapperViewer(Dialog arg0, String arg1, boolean arg2,
			GraphicsConfiguration arg3) throws HeadlessException {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JDWrapperViewer(Frame arg0, String arg1, boolean arg2,
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
		this.setSize(643, 596);
		this.setTitle("Wrappers for JCalcList");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(16,24,398,16));
			jLabel.setText("D:/material/testooj3/results/wrappers/0/JCalculator/JCalculator.java");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			//jContentPane.add(getJTFWrapperViewer(), null);
			jContentPane.add(jLabel, null);
		}
		return jContentPane;
	}



	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new java.awt.Rectangle(10,90,615,545));
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
			jepLog.setContentType("text/plain");
		}
		return jepLog;
	}
	
	public void logError(String msg) {
        msg=msg + jepLog.getText().trim();
        this.jepLog.setText(msg);
        jepLog.select(0, 0);  
    }
	
	public void log(String msg) {
        msg=msg + jepLog.getText().trim();
        this.jepLog.setText(msg);
        jepLog.select(0, 0);  
    }


	
}  //  @jve:decl-index=0:visual-constraint="10,10"

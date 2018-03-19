package testooj3.gui.compatibility;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.Operation;
import testooj3.gui.components.ILogWindow;

/**
 * @author  andres
 */
public class JPOracleDescription extends JPanel {

	private JLabel jlClassName = null;
	private JSplitPane jSplitPane = null;
	private JScrollPane jspPre = null;
	private JScrollPane jspPost = null;
	private JTextArea jtaPre = null;
	private JTextArea jtaPost = null;
	private Operation method;
	private Interface cut;
	private ILogWindow mLogWindow;

	public JPOracleDescription() {
		super();
		// TODO Auto-generated constructor stub
		initialize();
	}
	
	public void setLogWindow(ILogWindow logWindow) {
		this.mLogWindow=logWindow;
	}

	public JPOracleDescription(boolean arg0) {
		super(arg0);
		initialize();
	}

	public JPOracleDescription(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JPOracleDescription(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.BOTH;
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 1;
		gridBagConstraints1.ipadx = 5;
		gridBagConstraints1.ipady = 5;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.weighty = 1.0;
		gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 0;
		jlClassName = new JLabel();
		jlClassName.setText("JLabel");
		this.setLayout(new GridBagLayout());
		this.setSize(399, 460);
		this.add(jlClassName, gridBagConstraints);
		this.add(getJSplitPane(), gridBagConstraints1);
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setDividerLocation(200);
			jSplitPane.setTopComponent(getJspPre());
			jSplitPane.setBottomComponent(getJspPost());
			jSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jspPre	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspPre() {
		if (jspPre == null) {
			jspPre = new JScrollPane();
			jspPre.setViewportView(getJTextArea());
		}
		return jspPre;
	}

	/**
	 * This method initializes jspPost	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspPost() {
		if (jspPost == null) {
			jspPost = new JScrollPane();
			jspPost.setViewportView(getJtaPost());
		}
		return jspPost;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jtaPre == null) {
			jtaPre = new JTextArea();
		}
		return jtaPre;
	}

	/**
	 * This method initializes jtaPost	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJtaPost() {
		if (jtaPost == null) {
			jtaPost = new JTextArea();
		}
		return jtaPost;
	}

	public void setClassAndMethod(Interface a, Operation method) {
		this.cut=a;
		this.method=method;
		this.jlClassName.setText(this.cut.getName());
        try {
          this.jtaPre.setText(this.method.getPreassertions());
          this.jtaPost.setText(this.method.getPostassertions());
        }
        catch (Exception ex) 
        {
          this.mLogWindow.logError(ex.toString());
        }
	}

	public void setClassLabel(String name) {
		this.jlClassName.setText(name);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

package testooj3.gui.externaltools;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import testooj3.domain.Configuration;
import testooj3.gui.JDClassPathExplorer;
import testooj3.gui.components.ClassSelectorPanel;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class JLaunchJUnit extends JFrame {

	private javax.swing.JPanel jContentPane = null;
	private JTextField jtfTestFile = null;
	private JButton jbExecute = null;
	private JScrollPane jScrollPane = null;
	private JEditorPane jepMsg = null;
	private JTextField jtfCUT = null;
	private JButton jbTestFile = null;
	private JButton jbCUT = null;
	/**
	 * This is the default constructor
	 */
	public JLaunchJUnit() {
		super();
		initialize();
	}
	/**
     * @param name
     * @param fileName
     */
    public JLaunchJUnit(String className, String testFileName) {
        this();
        this.jtfTestFile.setText(testFileName);
        this.jtfCUT.setText(className);
    }
    /**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(594, 408);
		this.setContentPane(getJContentPane());
		this.setTitle("JUnit launching");
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(10, 5, 5, 5);
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.ipadx = 12;
			gridBagConstraints5.ipady = 0;
			gridBagConstraints5.anchor = GridBagConstraints.NORTH;
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(10, 5, 5, 5);
			gridBagConstraints4.gridy = 0;
			gridBagConstraints4.ipadx = 60;
			gridBagConstraints4.ipady = 0;
			gridBagConstraints4.anchor = GridBagConstraints.NORTH;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridx = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.ipadx = 405;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.anchor = GridBagConstraints.NORTH;
			gridBagConstraints3.insets = new Insets(10, 5, 5, 15);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.ipady = 217;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.insets = new Insets(7, 2, 0, 0);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(15, 5, 5, 5);
			gridBagConstraints1.gridy = 3;
			gridBagConstraints1.ipadx = 2;
			gridBagConstraints1.ipady = 2;
			gridBagConstraints1.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 405;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.anchor = GridBagConstraints.NORTH;
			gridBagConstraints.insets = new Insets(10, 5, 5, 15);
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJtfTestFile(), gridBagConstraints);
			jContentPane.add(getJbExecute(), gridBagConstraints1);
			jContentPane.add(getJScrollPane(), gridBagConstraints2);
			jContentPane.add(getJtfCUT(), gridBagConstraints3);
			jContentPane.add(getJbTestFile(), gridBagConstraints4);
			jContentPane.add(getJbCUT(), gridBagConstraints5);
		}
		return jContentPane;
	}
	/**
     * 
     */
    protected void selectFile() {
        JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) 
          jtfTestFile.setText(fc.getSelectedFile().getAbsolutePath());
  
    }
    /**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJtfTestFile() {
		if (jtfTestFile == null) {
			jtfTestFile = new JTextField();
		}
		return jtfTestFile;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbExecute() {
		if (jbExecute == null) {
			jbExecute = new JButton();
			jbExecute.setText("Execute on JUnit");
			jbExecute.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					execute();
				}
			});
		}
		return jbExecute;
	}
    /**
     * 
     */
    protected void execute() {
        String classFile=this.jtfTestFile.getText();
        Runtime r=Runtime.getRuntime();
        try {
            String ejecucion="java -classpath \"" + 
            	Configuration.getInstance().getJUnitLocation() + ";" +
            	this.getClassPath() + "\" " + classFile;
            log(ejecucion);
            InputStream os=r.exec(ejecucion).getErrorStream();
            int tam=os.available();
            byte[] b=new byte[tam];
            os.read(b);
            String msg=new String(b);
            log(msg);
        } catch (IOException e) {
            logError("Error executing: " + e.toString());
        }

    }
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJepMsg());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jEditorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */    
	private JEditorPane getJepMsg() {
		if (jepMsg == null) {
			jepMsg = new JEditorPane();
			jepMsg.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 11));
		}
		return jepMsg;
	}
	
    public void logError(String msg) {
        msg="<font color='red'>" + msg + "</font>"+jepMsg.getText().trim();
        this.jepMsg.setText(msg);
        jepMsg.select(0, 0);  
    }
    
    public void log(String msg) {
        msg=msg+jepMsg.getText().trim();
        this.jepMsg.setText(msg);
        jepMsg.select(0, 0);  
    }
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJtfCUT() {
		if (jtfCUT == null) {
			jtfCUT = new JTextField();
		}
		return jtfCUT;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbTestFile() {
		if (jbTestFile == null) {
			jbTestFile = new JButton();
			jbTestFile.setText("Test file");
			jbTestFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					selectTestFile();
				}
			});
		}
		return jbTestFile;
	}
	/**
     * 
     */
    protected void selectTestFile() {
        JDClassPathExplorer j=new JDClassPathExplorer();
        j.setModal(true);
        j.setVisible(true);
        if (j.getSelectedClass()!=null)
            this.jtfTestFile.setText(j.getSelectedClass());
    }
    /**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbCUT() {
		if (jbCUT == null) {
			jbCUT = new JButton();
			jbCUT.setText("Class under test");
			jbCUT.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					selectCUT();
				}
			});
		}
		return jbCUT;
	}
    /**
     * 
     */
    protected void selectCUT() {
        JDClassPathExplorer j=new JDClassPathExplorer();
        j.setModal(true);
        j.setVisible(true);
        if (j.getSelectedClass()!=null)
            this.jtfCUT.setText(j.getSelectedClass());
    }
    protected String getClassPath() {
        Properties pp=System.getProperties();
	    Enumeration ee=pp.propertyNames();
	    String cp=pp.getProperty("java.class.path");
	    if (!cp.endsWith(";"))
	      cp+=";";
	    String sbcp=pp.getProperty("sun.boot.class.path");
	    cp+=sbcp;
	    return cp;
    }
         }  //  @jve:decl-index=0:visual-constraint="10,10"

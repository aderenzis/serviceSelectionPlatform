package testooj3.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import testooj3.auxiliares.Auxi;
import testooj3.domain.TMujavaMethod;
import testooj3.domain.testfiles.mujava.MujavaFile;
import testooj3.gui.components.IMainWindow;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class JDListOfMujavaMethods extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JList jlTestCases = null;
	private JLabel jlTestCaseName = null;
	private JCheckBox jchRemoveMethod = null;
	private JScrollPane jScrollPane1 = null;
	private JButton jbExit = null;
	private JEditorPane jepCode = null;
	private DefaultListModel testCasesModel = null;   //  @jve:decl-index=0:
    private TMujavaMethod mSelectedMethod;
    private String mSessionPath;
    private IMainWindow mParentWindow;
    private int mNumberOfTestCases;
	private JCheckBox jchCompileFile = null;
	/**
	 * This is the default constructor
	 */
	public JDListOfMujavaMethods() {
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("MuJava test cases");
		this.setSize(819, 535);
		this.setContentPane(getJContentPane());
	}

    /**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 3;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(5, 5, 5, 6);
			gridBagConstraints4.gridy = 3;
			gridBagConstraints4.ipadx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.EAST;
			gridBagConstraints4.gridx = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridwidth = 1;
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.ipady = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.ipadx = 1;
			gridBagConstraints3.insets = new Insets(2, 1, 1, 0);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(3, 7, 6, 1);
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.ipadx = 0;
			gridBagConstraints2.ipady = 0;
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(6, 7, 2, 2);
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 1;
			gridBagConstraints1.ipady = 20;
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridheight = 3;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 0;
			gridBagConstraints.ipady = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(4, 5, 2, 0);
			jlTestCaseName = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jlTestCaseName.setText("");
			jContentPane.add(getJScrollPane(), gridBagConstraints);
			jContentPane.add(jlTestCaseName, gridBagConstraints1);
			jContentPane.add(getJchRemoveMethod(), gridBagConstraints2);
			jContentPane.add(getJScrollPane1(), gridBagConstraints3);
			jContentPane.add(getJbExit(), gridBagConstraints4);
			jContentPane.add(getJchCompileFile(), gridBagConstraints11);
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
			jScrollPane.setViewportView(getJlTestCases());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */    
	private JList getJlTestCases() {
		if (jlTestCases == null) {
			jlTestCases = new JList();
			jlTestCases.setModel(getTestCasesModel());
			jlTestCases.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mousePressed(java.awt.event.MouseEvent e) {    
					showTestCase();
				}
			});
		}
		return jlTestCases;
	}
	/**
     * 
     */
    protected void showTestCase() {
        int index = this.jlTestCases.getSelectedIndex();
        if (index == -1)
            return;
        try {
            mSelectedMethod = MujavaFile.load(this.mSessionPath+File.separator+(index+1)+".mujava");
		    this.jlTestCaseName.setText(mSelectedMethod.getNombre());
		    String code = mSelectedMethod.toString(false);
		    code = Auxi.substituteAll(code, "\t", "    ");
		    this.jepCode.setText(mSelectedMethod.toString(false));            
        } catch (IOException e) {
            mParentWindow.logError("Error: " + e.toString());
        } catch (ClassNotFoundException e) {
            mParentWindow.logError("Error: " + e.toString());
        }
    }
    /**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJchRemoveMethod() {
		if (jchRemoveMethod == null) {
			jchRemoveMethod = new JCheckBox();
			jchRemoveMethod.setText("Remove method");
		}
		return jchRemoveMethod;
	}
	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJepCode());
		}
		return jScrollPane1;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbExit() {
		if (jbExit == null) {
			jbExit = new JButton();
			jbExit.setText("Exit");
			jbExit.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					exit();
				}
			});
		}
		return jbExit;
	}
	/**
     * 
     */
    protected void exit() {
        dispose();
    }
    /**
	 * This method initializes jEditorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */    
	private JEditorPane getJepCode() {
		if (jepCode == null) {
			jepCode = new JEditorPane();
			jepCode.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 11));
		}
		return jepCode;
	}
   public void setTestCases(String sessionPath, int numberOfTestCases) throws IOException, ClassNotFoundException {
        this.mSessionPath = sessionPath;
        this.mNumberOfTestCases=numberOfTestCases;
        for (int i = 1; i <=numberOfTestCases; i++) {
            TMujavaMethod m = MujavaFile.load(sessionPath+File.separator+i+".mujava");
            this.getTestCasesModel().addElement(m.getNombre());
        }
    }
    
	/**
	 * This method initializes testCasesModel	
	 * 	
	 * @return javax.swing.DefaultListModel	
	 */    
	private DefaultListModel getTestCasesModel() {
		if (testCasesModel == null) {
			testCasesModel = new DefaultListModel();
		}
		return testCasesModel;
	}
    public void setParentWindow(IMainWindow parentWindow) {
        mParentWindow = parentWindow;
    }
	/**
	 * This method initializes jchCompileFile	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJchCompileFile() {
		if (jchCompileFile == null) {
			jchCompileFile = new JCheckBox();
			jchCompileFile.setText("Compile file when leaving");
		}
		return jchCompileFile;
	}
	
	public boolean getCompile() {
		return this.jchCompileFile.isSelected();
	}
        }  //  @jve:decl-index=0:visual-constraint="13,38"

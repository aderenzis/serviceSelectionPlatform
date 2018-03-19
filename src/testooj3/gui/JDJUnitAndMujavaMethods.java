package testooj3.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import testooj3.auxiliares.Auxi;
import testooj3.domain.TJUnitMethod;
import testooj3.domain.TMujavaMethod;
import testooj3.domain.TestMethod;
import testooj3.domain.testfiles.junit.JUnitFile;
import testooj3.gui.components.IMainWindow;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to Window -  Preferences - Java - Code Style - Code Templates
 */
public class JDJUnitAndMujavaMethods extends JDialog {

    private javax.swing.JPanel jContentPane = null;

    private JScrollPane jScrollPane = null;

    private JList jlTestCases = null;

    private JCheckBox jchExecutionMustFailWith = null;

    private JCheckBox jchRemoveMethod = null;

    private JScrollPane jScrollPane1 = null;

    private JButton jbExit = null;

    private JEditorPane jepJUnitCode = null;

    private DefaultListModel testCasesModel = null; //  @jve:decl-index=0:

    private TJUnitMethod mSelectedJUnitMethod;
    private TMujavaMethod mSelectedMujavaMethod;

    private String mJUnitSessionPath;

    private IMainWindow mParentWindow;

    private int mNumberOfTestCases;

	private JCheckBox jchCompileFile = null;

    private boolean mCompileFile;
	private JSplitPane jSplitPane = null;
	private JScrollPane jScrollPane2 = null;
	private JEditorPane jepMujavaCode = null;

    private String mMujavaSessionPath;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JLabel jlTestCaseName = null;
	private JComboBox jcbMustThrow = null;
	private DefaultComboBoxModel exModel=new DefaultComboBoxModel();
    /**
     * This is the default constructor
     */
    public JDJUnitAndMujavaMethods() {
        super();
        initialize();
        this.jcbMustThrow.setModel(exModel);
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setTitle("JUnit test cases");
        this.setSize(907, 655);
        this.setContentPane(getJContentPane());
    }



    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJContentPane() {
        if (jContentPane == null) {
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.insets = new Insets(1, 1, 4, 203);
            gridBagConstraints3.gridy = 2;
            gridBagConstraints3.ipadx = 30;
            gridBagConstraints3.gridx = 1;
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = GridBagConstraints.BOTH;
            gridBagConstraints2.gridx = 1;
            gridBagConstraints2.gridy = 1;
            gridBagConstraints2.ipady = 477;
            gridBagConstraints2.weightx = 1.0;
            gridBagConstraints2.weighty = 1.0;
            gridBagConstraints2.insets = new Insets(2, 2, 0, 4);
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.fill = GridBagConstraints.BOTH;
            gridBagConstraints1.gridheight = 3;
            gridBagConstraints1.gridx = 0;
            gridBagConstraints1.gridy = 0;
            gridBagConstraints1.ipady = 490;
            gridBagConstraints1.weightx = 1.0;
            gridBagConstraints1.weighty = 1.0;
            gridBagConstraints1.insets = new Insets(5, 5, 0, 1);
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.insets = new Insets(6, 1, 2, 5);
            gridBagConstraints.gridy = 0;
            gridBagConstraints.ipadx = 148;
            gridBagConstraints.ipady = -2;
            gridBagConstraints.gridx = 1;
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(new GridBagLayout());
            jContentPane.add(getJPanel2(), gridBagConstraints);
            jContentPane.add(getJScrollPane(), gridBagConstraints1);
            jContentPane.add(getJSplitPane(), gridBagConstraints2);
            jContentPane.add(getJPanel(), gridBagConstraints3);
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
            jScrollPane.setName("jScrollPane");
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
            mSelectedJUnitMethod = (TJUnitMethod) TestMethod.load(this.mJUnitSessionPath+"\\"+(index+1), TestMethod.JUNIT);
            mSelectedMujavaMethod=(TMujavaMethod) TestMethod.load(this.mMujavaSessionPath+"\\"+(index+1), TestMethod.MUJAVA);
		    this.jlTestCaseName.setText(mSelectedJUnitMethod.getNombre());
		    showJUnitTestCase();
		    this.jepMujavaCode.setText(mSelectedMujavaMethod.toString(false));
        } catch (IOException e) {
            this.mParentWindow.logError("Error: " + e.toString());
        } catch (ClassNotFoundException e) {
            this.mParentWindow.logError("Error: " + e.toString());
        }
    }
    
    private void showJUnitTestCase() {
        Enumeration exceptions=mSelectedJUnitMethod.getPossibleExceptions().elements();
        exModel.removeAllElements();
        while (exceptions.hasMoreElements()) {
            String exception=exceptions.nextElement().toString();
            exModel.addElement(exception);
        }
        if (mSelectedJUnitMethod.getExceptionLaunched()!=null) {
            jchExecutionMustFailWith.setSelected(true);
            exModel.setSelectedItem(mSelectedJUnitMethod.getExceptionLaunched());
        } else {
            jchExecutionMustFailWith.setSelected(false);
        }
	    this.jlTestCaseName.setText(mSelectedJUnitMethod.getNombre());
	    String code = mSelectedJUnitMethod.toString(false);
	    code = Auxi.substituteAll(code, "\t", "    ");
	    this.jepJUnitCode.setText(mSelectedJUnitMethod.toString(false));
    }

    /**
     * This method initializes jCheckBox
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getJchExecutionMustFailWith() {
        if (jchExecutionMustFailWith == null) {
            jchExecutionMustFailWith = new JCheckBox();
            jchExecutionMustFailWith.setText("Execution must fail with");
            jchExecutionMustFailWith
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            executionMustFailWith();
                        }
                    });
        }
        return jchExecutionMustFailWith;
    }

    /**
     *  
     */
    protected void executionMustFailWith() {
        try {
	        if (mSelectedJUnitMethod != null) {
	            String exception=this.exModel.getSelectedItem().toString();
	            if (jchExecutionMustFailWith.isSelected())
	                mSelectedJUnitMethod.setMustFailWith(exception);
	            else
	                mSelectedJUnitMethod.setMustFailWith(null);
	            mSelectedJUnitMethod.save(this.mJUnitSessionPath);
	            this.jepJUnitCode.setText(mSelectedJUnitMethod.toString(false));
	        }
        }
        catch (Exception ex) {
            this.mParentWindow.logError("Error saving method: " + ex.toString());
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
        if (jepJUnitCode == null) {
            jepJUnitCode = new JEditorPane();
            jepJUnitCode.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        }
        return jepJUnitCode;
    }

    /**
     * @param originales
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void setTestCases(String junitSessionPath, String mujavaSessionPath, int numberOfTestCases) throws IOException, ClassNotFoundException {
        this.mJUnitSessionPath = junitSessionPath;
        this.mMujavaSessionPath=mujavaSessionPath;
        this.mNumberOfTestCases=numberOfTestCases;
        for (int i = 1; i <=numberOfTestCases; i++) {
            TestMethod junit = TestMethod.load(junitSessionPath+"\\"+i, TestMethod.JUNIT);
            this.getTestCasesModel().addElement(junit.getNombre());
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
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJchCompileFile() {
		if (jchCompileFile == null) {
			jchCompileFile = new JCheckBox();
			jchCompileFile.setText("Compile JUnit file when leaving");
			jchCompileFile.addItemListener(new java.awt.event.ItemListener() { 
				public void itemStateChanged(java.awt.event.ItemEvent e) {    
					setCompileFile();
				}
			});
		}
		return jchCompileFile;
	}

    /**
     * 
     */
    protected void setCompileFile() {
        this.mCompileFile=this.jchCompileFile.isSelected();
    }
    public boolean getCompileFile() {
        return mCompileFile;
    }
	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */    
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
			jSplitPane.setTopComponent(getJScrollPane1());
			jSplitPane.setDividerLocation(240);
			jSplitPane.setBottomComponent(getJScrollPane2());
		}
		return jSplitPane;
	}
	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJepMujavaCode());
		}
		return jScrollPane2;
	}
	/**
	 * This method initializes jEditorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */    
	private JEditorPane getJepMujavaCode() {
		if (jepMujavaCode == null) {
			jepMujavaCode = new JEditorPane();
			jepMujavaCode.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
		}
		return jepMujavaCode;
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			GridLayout gridLayout2 = new GridLayout();
			jPanel.setLayout(gridLayout2);
			gridLayout2.setRows(1);
			jPanel.add(getJchCompileFile(), null);
			jPanel.add(getJbExit(), null);
		}
		return jPanel;
	}
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			GridLayout gridLayout1 = new GridLayout();
			jPanel1.setLayout(gridLayout1);
			jPanel1.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
			gridLayout1.setRows(1);
			jPanel1.add(getJchExecutionMustFailWith(), null);
			jPanel1.add(getJcbMustThrow(), null);
			jPanel1.add(getJchRemoveMethod(), null);
		}
		return jPanel1;
	}
	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jlTestCaseName = new JLabel();
			GridLayout gridLayout3 = new GridLayout();
			jPanel2.setLayout(gridLayout3);
			jlTestCaseName.setText("");
			gridLayout3.setRows(2);
			jPanel2.add(jlTestCaseName, null);
			jPanel2.add(getJPanel1(), null);
		}
		return jPanel2;
	}
	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */    
	private JComboBox getJcbMustThrow() {
		if (jcbMustThrow == null) {
			jcbMustThrow = new JComboBox();
		}
		return jcbMustThrow;
	}
 }  //  @jve:decl-index=0:visual-constraint="12,-3"

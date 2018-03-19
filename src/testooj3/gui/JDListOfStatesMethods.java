package testooj3.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Vector;
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
import testooj3.auxiliares.Auxi;
import testooj3.domain.TJUnitMethod;
import testooj3.domain.TestMethod;
import testooj3.domain.testfiles.junit.JUnitFile;
import testooj3.gui.components.IMainWindow;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to Window -  Preferences - Java - Code Style - Code Templates
 */
public class JDListOfStatesMethods extends JDialog {

    private javax.swing.JPanel jContentPane = null;

    private JScrollPane jScrollPane = null;

    private JList jlTestCases = null;

    private JLabel jlTestCaseName = null;

    private JCheckBox jchMustFailWith = null;

    private JCheckBox jchRemoveMethod = null;

    private JScrollPane jScrollPane1 = null;

    private JButton jbExit = null;

    private JEditorPane jepCode = null;

    private DefaultListModel testCasesModel = null; //  @jve:decl-index=0:

    private TJUnitMethod mSelectedMethod;

    private String mSessionPath;

    private IMainWindow mParentWindow;

    private int mNumberOfTestCases;

	private JCheckBox jchCompileFile = null;

    private boolean mCompileFile;
	private JComboBox jcbMustThrow = null;
	private DefaultComboBoxModel exModel=new DefaultComboBoxModel();

	private JPanel jPanelBottom = null;

	private JPanel jPanelTop = null;
    /**
     * This is the default constructor
     */
    public JDListOfStatesMethods() {
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
        this.setTitle("States test cases");
        this.setSize(823, 679);
        this.setContentPane(getJContentPane());
    }



    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJContentPane() {
        if (jContentPane == null) {
            GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
            gridBagConstraints9.insets = new Insets(8, 3, 1, 5);
            gridBagConstraints9.gridy = 0;
            gridBagConstraints9.ipadx = 1;
            gridBagConstraints9.ipady = 1;
            gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints9.weightx = 1.0;
            gridBagConstraints9.gridx = 1;
            GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
            gridBagConstraints8.insets = new Insets(1, 1, 6, 5);
            gridBagConstraints8.gridy = 2;
            gridBagConstraints8.ipadx = 2;
            gridBagConstraints8.ipady = 2;
            gridBagConstraints8.anchor = GridBagConstraints.WEST;
            gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints8.gridx = 1;
            GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
            gridBagConstraints7.fill = GridBagConstraints.BOTH;
            gridBagConstraints7.gridx = 1;
            gridBagConstraints7.gridy = 1;
            gridBagConstraints7.ipady = 1;
            gridBagConstraints7.weightx = 1.0;
            gridBagConstraints7.weighty = 1.0;
            gridBagConstraints7.ipadx = 1;
            gridBagConstraints7.insets = new Insets(2, 1, 1, 1);
            GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.fill = GridBagConstraints.BOTH;
            gridBagConstraints6.gridheight = 2;
            gridBagConstraints6.gridx = 0;
            gridBagConstraints6.gridy = 0;
            gridBagConstraints6.ipadx = 1;
            gridBagConstraints6.ipady = 1;
            gridBagConstraints6.weightx = 0.6;
            gridBagConstraints6.weighty = 1.0;
            gridBagConstraints6.anchor = GridBagConstraints.WEST;
            gridBagConstraints6.insets = new Insets(4, 5, 1, 1);
            jlTestCaseName = new JLabel();
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(new GridBagLayout());
            jlTestCaseName.setText("");
            jContentPane.add(getJScrollPane(), gridBagConstraints6);
            jContentPane.add(getJScrollPane1(), gridBagConstraints7);
            jContentPane.add(getJPanelBottom(), gridBagConstraints8);
            jContentPane.add(getJPanelTop(), gridBagConstraints9);
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
                    if (e.getClickCount()==2)
                        executionMustFailWithDoubleClick();
                    else
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
            mSelectedMethod = (TJUnitMethod) TestMethod.load(this.mSessionPath+"\\"+(index+1), TestMethod.STATES_JUNIT);
            Enumeration exceptions=mSelectedMethod.getPossibleExceptions().elements();
            exModel.removeAllElements();
            while (exceptions.hasMoreElements()) {
                String exception=exceptions.nextElement().toString();
                exModel.addElement(exception);
            }
            if (mSelectedMethod.getExceptionLaunched()!=null) {
                jchMustFailWith.setSelected(true);
                exModel.setSelectedItem(mSelectedMethod.getExceptionLaunched());
            } else {
                jchMustFailWith.setSelected(false);
            }
		    this.jlTestCaseName.setText(mSelectedMethod.getNombre());
		    String code = mSelectedMethod.toString(false);
		    code = Auxi.substituteAll(code, "\t", "    ");
		    this.jepCode.setText(mSelectedMethod.toString(false));    
        } catch (IOException e) {
            this.mParentWindow.logError("Error: " + e.toString());
        } catch (ClassNotFoundException e) {
            this.mParentWindow.logError("Error: " + e.toString());
        }
    }

    /**
     * This method initializes jCheckBox
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getJchMustFailWith() {
        if (jchMustFailWith == null) {
            jchMustFailWith = new JCheckBox();
            jchMustFailWith.setText("Must fail with");
            jchMustFailWith
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            executionMustFailWith();
                        }
                    });
        }
        return jchMustFailWith;
    }

    /**
     *  
     */
    protected void executionMustFailWith() {
        try {
	        if (mSelectedMethod != null) {
	            String exception=this.exModel.getSelectedItem().toString();
	            if (jchMustFailWith.isSelected())
	                mSelectedMethod.setMustFailWith(exception);
	            else
	                mSelectedMethod.setMustFailWith(null);
	            mSelectedMethod.save(this.mSessionPath);
	            this.jepCode.setText(mSelectedMethod.toString(false));
	        }
        }
        catch (Exception ex) {
            this.mParentWindow.logError("Error saving method: " + ex.toString());
        }
    }
    
    protected void executionMustFailWithDoubleClick() {
        try {
	        if (mSelectedMethod != null) {
	            mSelectedMethod.setMustFailWith("Exception");
	            mSelectedMethod.save(this.mSessionPath);
	            this.jepCode.setText(mSelectedMethod.toString(false));
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
        if (jepCode == null) {
            jepCode = new JEditorPane();
            jepCode.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 12));
        }
        return jepCode;
    }

    /**
     * @param originales
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void setTestCases(String sessionPath, int numberOfTestCases) throws IOException, ClassNotFoundException {
        this.mSessionPath = sessionPath;
        this.mNumberOfTestCases=numberOfTestCases;
        for (int i = 1; i <=numberOfTestCases; i++) {
            TJUnitMethod m = (TJUnitMethod) TestMethod.load(sessionPath+"\\"+i, TestMethod.STATES_JUNIT);
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
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJchCompileFile() {
		if (jchCompileFile == null) {
			jchCompileFile = new JCheckBox();
			jchCompileFile.setText("Compile file when leaving");
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

	/**
	 * This method initializes jPanelBottom	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelBottom() {
		if (jPanelBottom == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.anchor = GridBagConstraints.EAST;
			gridBagConstraints1.fill = GridBagConstraints.NONE;
			gridBagConstraints1.ipadx = 2;
			gridBagConstraints1.ipady = 2;
			gridBagConstraints1.gridwidth = 1;
			gridBagConstraints1.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridy = 0;
			jPanelBottom = new JPanel();
			jPanelBottom.setLayout(new GridBagLayout());
			jPanelBottom.add(getJchCompileFile(), gridBagConstraints);
			jPanelBottom.add(getJbExit(), gridBagConstraints1);
		}
		return jPanelBottom;
	}

	/**
	 * This method initializes jPanelTop	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelTop() {
		if (jPanelTop == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridy = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridy = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.ipadx = 0;
			gridBagConstraints3.insets = new Insets(0, 24, 0, 0);
			gridBagConstraints3.gridx = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.ipady = 20;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.ipadx = 20;
			gridBagConstraints2.gridwidth = 3;
			gridBagConstraints2.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.gridy = 0;
			jPanelTop = new JPanel();
			jPanelTop.setLayout(new GridBagLayout());
			jPanelTop.add(jlTestCaseName, gridBagConstraints2);
			jPanelTop.add(getJcbMustThrow(), gridBagConstraints3);
			jPanelTop.add(getJchRemoveMethod(), gridBagConstraints4);
			jPanelTop.add(getJchMustFailWith(), gridBagConstraints5);
		}
		return jPanelTop;
	}
  } //  @jve:decl-index=0:visual-constraint="10,10"

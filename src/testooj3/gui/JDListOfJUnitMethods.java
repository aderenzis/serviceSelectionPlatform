package testooj3.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Properties;
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
import testooj3.domain.TMutJUnitMethod;
import testooj3.domain.TestMethod;
import testooj3.domain.tcmutation.TGroupedTestMethod;
import testooj3.domain.testfiles.junit.JUnitFile;
import testooj3.gui.components.IMainWindow;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to Window -  Preferences - Java - Code Style - Code Templates
 */
public class JDListOfJUnitMethods extends JDialog {

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

    private Vector sessions;
	private JButton jbPreviousSession = null;
	private JButton jbNextSession = null;

    private int sessionType;

	private JPanel jPanelBottom = null;

	private JPanel jPanelTop = null;
    /**
     * This is the default constructor
     */
    public JDListOfJUnitMethods() {
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
        this.setSize(993, 679);
        this.setContentPane(getJContentPane());
    }



    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJContentPane() {
        if (jContentPane == null) {
            GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
            gridBagConstraints21.gridx = 1;
            gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints21.gridwidth = 1;
            gridBagConstraints21.insets = new Insets(1, 1, 1, 0);
            gridBagConstraints21.ipadx = 1;
            gridBagConstraints21.ipady = 1;
            gridBagConstraints21.gridy = 0;
            GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
            gridBagConstraints11.gridx = 1;
            gridBagConstraints11.gridwidth = 1;
            gridBagConstraints11.fill = GridBagConstraints.NONE;
            gridBagConstraints11.anchor = GridBagConstraints.EAST;
            gridBagConstraints11.insets = new Insets(1, 1, 1, 1);
            gridBagConstraints11.ipadx = 9;
            gridBagConstraints11.gridy = 3;
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
            gridBagConstraints5.gridy = 3;
            gridBagConstraints5.ipadx = 2;
            gridBagConstraints5.anchor = GridBagConstraints.WEST;
            gridBagConstraints5.gridx = 0;
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.fill = GridBagConstraints.BOTH;
            gridBagConstraints4.gridwidth = 1;
            gridBagConstraints4.gridx = 1;
            gridBagConstraints4.gridy = 2;
            gridBagConstraints4.ipady = 531;
            gridBagConstraints4.weightx = 1.0;
            gridBagConstraints4.weighty = 1.0;
            gridBagConstraints4.insets = new Insets(1, 1, 3, 2);
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.gridheight = 3;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.ipadx = 0;
            gridBagConstraints.ipady = 0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.anchor = GridBagConstraints.WEST;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(3, 3, 0, 0);
            jlTestCaseName = new JLabel();
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(new GridBagLayout());
            jlTestCaseName.setText("");
            jContentPane.add(getJScrollPane(), gridBagConstraints);
            jContentPane.add(getJScrollPane1(), gridBagConstraints4);
            jContentPane.add(getJbExit(), gridBagConstraints5);
            jContentPane.add(getJPanelBottom(), gridBagConstraints11);
            jContentPane.add(getJPanelTop(), gridBagConstraints21);
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
        	mSelectedMethod=(TJUnitMethod) TestMethod.load(this.mSessionPath+"\\" + (index+1), sessionType);
            /*if (sessionType==TestMethod.JUNIT)
                mSelectedMethod = (TJUnitMethod) TestMethod.load(this.mSessionPath+"\\"+(index+1)+".junit");
            else if (sessionType==TestMethod.MUT_JUNIT)
                mSelectedMethod = (TMutJUnitMethod) TestMethod.load(this.mSessionPath+"\\"+(index+1)+".mutjunit");
            else mSelectedMethod = (TGroupedTestMethod) TestMethod.load(this.mSessionPath+"\\"+(index+1)+".grouped");*/
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
		    this.jepCode.select(0, 0);
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
        this.getTestCasesModel().removeAllElements();
        for (int i = 1; i <=numberOfTestCases; i++) {
            TestMethod m=TestMethod.load(sessionPath+"\\" + i, sessionType);
/*            if (this.sessionType==TestMethod.JUNIT)
                m = (TJUnitMethod) TestMethod.load(sessionPath+"\\"+i+".junit");
            else if (this.sessionType==TestMethod.MUT_JUNIT) 
                m=(TMutJUnitMethod) TestMethod.load(sessionPath+"\\"+i+".mutjunit");
            else m=(TGroupedTestMethod) TestMethod.load(sessionPath+"\\"+i+".grouped");
*/            this.getTestCasesModel().addElement(m.getNombre());
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
	
	public void setSessionType(int sessionType) {
	    this.sessionType=sessionType;
	}

    /**
     * @param sessions
     */
    public void setSessions(Vector sessions) {
        this.sessions=sessions;
        this.jbNextSession.setVisible(sessions!=null);
        this.jbPreviousSession.setVisible(sessions!=null);
    }
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbPreviousSession() {
		if (jbPreviousSession == null) {
			jbPreviousSession = new JButton();
			jbPreviousSession.setText("<<");
			jbPreviousSession.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					previousSession();
				}
			});
		}
		return jbPreviousSession;
	}
	/**
     * 
     */
    protected void previousSession() {
        try {
	        int currentSessionPos=this.sessions.indexOf(this.mSessionPath+".session");
	        if (currentSessionPos==0 || currentSessionPos==-1) return;
	        this.mSessionPath=sessions.get(currentSessionPos-1).toString();
	        loadSession();
        }
        catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    /**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbNextSession() {
		if (jbNextSession == null) {
			jbNextSession = new JButton();
			jbNextSession.setText(">>");
			jbNextSession.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					nextSession();
				}
			});
		}
		return jbNextSession;
	}

    /**
     * 
     */
    protected void nextSession() {
        try {
	        int currentSessionPos=this.sessions.indexOf(this.mSessionPath+".session");
	        if (currentSessionPos==this.sessions.size()-1 || currentSessionPos==-1) return;
	        this.mSessionPath=sessions.get(currentSessionPos+1).toString();
	        loadSession();
        }
        catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    private void loadSession() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream f=new FileInputStream(this.mSessionPath);
        Properties pp = new Properties();
        pp.load(f);
        f.close();
        String sessionType=pp.getProperty("SessionType");
        String sNumberOfTC=pp.getProperty("NumberOfTestCases");
        String cut=pp.getProperty("CUT");
        this.mSessionPath=this.mSessionPath.substring(0, this.mSessionPath.lastIndexOf("."));
        if (sessionType!=null && sessionType.equals("junit")) {
            int[] numberOfTestCases={Integer.parseInt(sNumberOfTC)};
            this.setTestCases(this.mSessionPath, numberOfTestCases[0]);       
        }
        this.setTitle("Session " + mSessionPath + " (" +
                sNumberOfTC + " " + sessionType + " test cases for " + cut + ")");
    }

	/**
	 * This method initializes jPanelBottom	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelBottom() {
		if (jPanelBottom == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.insets = new Insets(4, 2, 3, 0);
			gridBagConstraints9.gridy = 0;
			gridBagConstraints9.ipadx = 0;
			gridBagConstraints9.ipady = 0;
			gridBagConstraints9.anchor = GridBagConstraints.WEST;
			gridBagConstraints9.gridx = 4;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.insets = new Insets(4, 1, 3, 1);
			gridBagConstraints8.gridx = 2;
			gridBagConstraints8.gridy = 0;
			gridBagConstraints8.ipadx = 0;
			gridBagConstraints8.ipady = 0;
			gridBagConstraints8.anchor = GridBagConstraints.EAST;
			gridBagConstraints8.fill = GridBagConstraints.NONE;
			gridBagConstraints8.gridwidth = 2;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridwidth = 2;
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.ipadx = 0;
			gridBagConstraints6.ipady = 1;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.insets = new Insets(9, 3, 3, 11);
			jPanelBottom = new JPanel();
			jPanelBottom.setLayout(new GridBagLayout());
			jPanelBottom.add(getJchCompileFile(), gridBagConstraints6);
			jPanelBottom.add(getJbPreviousSession(), gridBagConstraints8);
			jPanelBottom.add(getJbNextSession(), gridBagConstraints9);
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
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridwidth = 1;
			gridBagConstraints7.gridx = 3;
			gridBagConstraints7.gridy = 1;
			gridBagConstraints7.ipadx = 0;
			gridBagConstraints7.ipady = 0;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.insets = new Insets(5, 6, 0, 0);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(4, 1, 1, 2);
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.ipadx = 2;
			gridBagConstraints2.ipady = 0;
			gridBagConstraints2.gridwidth = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(3, 8, 2, 0);
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.ipadx = 2;
			gridBagConstraints3.ipady = 0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridwidth = 3;
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 4;
			gridBagConstraints1.ipady = 20;
			gridBagConstraints1.insets = new Insets(8, 8, 8, 4);
			jPanelTop = new JPanel();
			jPanelTop.setLayout(new GridBagLayout());
			jPanelTop.add(jlTestCaseName, gridBagConstraints1);
			jPanelTop.add(getJchRemoveMethod(), gridBagConstraints3);
			jPanelTop.add(getJchMustFailWith(), gridBagConstraints2);
			jPanelTop.add(getJcbMustThrow(), gridBagConstraints7);
		}
		return jPanelTop;
	}
} //  @jve:decl-index=0:visual-constraint="10,10"

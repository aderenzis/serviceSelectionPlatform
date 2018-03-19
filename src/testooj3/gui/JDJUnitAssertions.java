package testooj3.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import testooj3.domain.Configuration;
import testooj3.domain.PrimitiveValue;
import testooj3.domain.Interface;
import testooj3.domain.TField;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.TestValue;
import testooj3.domain.states.State;
import testooj3.gui.components.ILogWindow;
import testooj3.gui.components.IMainWindow;
import testooj3.gui.components.JSPClassStructure;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class JDJUnitAssertions extends JDialog implements IMainWindow {

	private javax.swing.JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField jtfTestValue = null;
	private JButton jbAddTestValue = null;
	private JScrollPane jScrollPane = null;
	private JList jlTestValues = null;
	private DefaultListModel testValuesModel = null;   //  @jve:decl-index=0:
	private JButton jButton = null;
	private JScrollPane jScrollPane1 = null;
	private JTextPane jepPrecode = null;
	private JScrollPane jScrollPane2 = null;
	private JTextPane jepPostcode = null;
	private JSPClassStructure JSPClassStructure = null;
    private ILogWindow mLogWindow;
	private JPanel jPanel4 = null;
	private JButton jbOpenFile = null;
	private JButton jbSaveAndExit = null;
	private JButton jbExitNoSaving = null;
    private Interface mClass;  //  @jve:decl-index=0:
    private TOperation mSelectedOperation;
    private TParameter mSelectedParameter;  //  @jve:decl-index=0:
	private JLabel jlMustThrow = null;
	private JLabel jlTheValue = null;
	private JLabel jLabel3 = null;
	private JComboBox jcbExceptionList = null;
    private TestValue mSelectedTestValue;
    private DefaultComboBoxModel exModel=new DefaultComboBoxModel();
	private JLabel jLabel4 = null;
	private JTextField jtfNumberOfRandomValues = null;
	private JCheckBox jchUseValuesAsDescribed = null;
	private JButton jbGenerate = null;
	/**
	 * This is the default constructor
	 */
	public JDJUnitAssertions() {
		super();
		initialize();
		this.JSPClassStructure.setParentWindow(this);
		this.jcbExceptionList.setModel(exModel);
	}
	
	public void setLogWindow(ILogWindow v) {
	    this.mLogWindow=v;
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("JUnit assertions");
		this.setSize(850, 538);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.insets = new Insets(1, 1, 7, 1);
			gridBagConstraints18.gridy = 0;
			gridBagConstraints18.ipadx = 1;
			gridBagConstraints18.ipady = 1;
			gridBagConstraints18.fill = GridBagConstraints.BOTH;
			gridBagConstraints18.anchor = GridBagConstraints.CENTER;
			gridBagConstraints18.gridx = 1;
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridy = 0;
			gridBagConstraints17.fill = GridBagConstraints.BOTH;
			gridBagConstraints17.anchor = GridBagConstraints.CENTER;
			gridBagConstraints17.gridx = 0;
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJPanel4(), gridBagConstraints17);
			jContentPane.add(getJPanel(), gridBagConstraints18);
		}
		return jContentPane;
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
			gridLayout2.setRows(3);
			gridLayout2.setHgap(0);
			gridLayout2.setColumns(1);
			jPanel.add(getJPanel3(), null);
			jPanel.add(getJPanel2(), null);
			jPanel.add(getJPanel1(), null);
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
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.insets = new Insets(1, 1, 4, 4);
			gridBagConstraints12.gridy = 0;
			gridBagConstraints12.ipadx = 1;
			gridBagConstraints12.ipady = 1;
			gridBagConstraints12.anchor = GridBagConstraints.EAST;
			gridBagConstraints12.gridx = 4;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.insets = new Insets(1, 2, 3, 7);
			gridBagConstraints11.gridx = 4;
			gridBagConstraints11.gridy = 7;
			gridBagConstraints11.ipadx = 1;
			gridBagConstraints11.ipady = 1;
			gridBagConstraints11.gridwidth = 1;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			gridBagConstraints10.gridwidth = 1;
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.gridy = 7;
			gridBagConstraints10.ipadx = 2;
			gridBagConstraints10.ipady = 3;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.insets = new Insets(1, 2, 0, 2);
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.insets = new Insets(0, 7, 1, 1);
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.gridy = 7;
			gridBagConstraints9.ipadx = 5;
			gridBagConstraints9.ipady = 5;
			gridBagConstraints9.gridwidth = 2;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.gridwidth = 1;
			gridBagConstraints8.gridx = 4;
			gridBagConstraints8.gridy = 6;
			gridBagConstraints8.ipadx = 1;
			gridBagConstraints8.ipady = 1;
			gridBagConstraints8.weightx = 0.0;
			gridBagConstraints8.insets = new Insets(4, 1, 0, 5);
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.insets = new Insets(0, 1, 1, 0);
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.gridy = 6;
			gridBagConstraints7.ipadx = 6;
			gridBagConstraints7.ipady = 5;
			gridBagConstraints7.anchor = GridBagConstraints.EAST;
			gridBagConstraints7.gridwidth = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.gridy = 6;
			gridBagConstraints6.ipadx = 1;
			gridBagConstraints6.ipady = 1;
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.gridwidth = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(0, 7, 1, 1);
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 6;
			gridBagConstraints5.ipadx = 1;
			gridBagConstraints5.ipady = 1;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.gridwidth = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(1, 3, 2, 6);
			gridBagConstraints4.gridy = 4;
			gridBagConstraints4.ipadx = 1;
			gridBagConstraints4.ipady = 1;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridx = 4;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(7, 3, 1, 6);
			gridBagConstraints3.gridx = 4;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.ipadx = 1;
			gridBagConstraints3.ipady = 1;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridheight = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridheight = 4;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.ipadx = 0;
			gridBagConstraints2.ipady = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.anchor = GridBagConstraints.CENTER;
			gridBagConstraints2.insets = new Insets(0, 1, 0, 2);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 0;
			gridBagConstraints1.ipady = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(5, 2, 2, 2);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(1, 5, 0, 0);
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 2;
			gridBagConstraints.ipady = 11;
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.gridx = 0;
			jLabel4 = new JLabel();
			jLabel4.setText("Number of values for random testing:");
			jlMustThrow = new JLabel();
			jlTheValue = new JLabel();
			jLabel3 = new JLabel();
			jLabel2 = new JLabel();
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jLabel2.setText("Test value");
			jlMustThrow.setText("With the value");
			jlTheValue.setText("");
			jLabel3.setText("must throw");
			jPanel1.add(jLabel2, gridBagConstraints);
			jPanel1.add(getJtfTestValue(), gridBagConstraints1);
			jPanel1.add(getJScrollPane(), gridBagConstraints2);
			jPanel1.add(getJbAddTestValue(), gridBagConstraints3);
			jPanel1.add(getJButton(), gridBagConstraints4);
			jPanel1.add(jlMustThrow, gridBagConstraints5);
			jPanel1.add(jlTheValue, gridBagConstraints6);
			jPanel1.add(jLabel3, gridBagConstraints7);
			jPanel1.add(getJcbExceptionList(), gridBagConstraints8);
			jPanel1.add(jLabel4, gridBagConstraints9);
			jPanel1.add(getJtfNumberOfRandomValues(), gridBagConstraints10);
			jPanel1.add(getJchUseValuesAsDescribed(), gridBagConstraints11);
			jPanel1.add(getJbGenerate(), gridBagConstraints12);
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
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.fill = GridBagConstraints.BOTH;
			gridBagConstraints16.gridx = 0;
			gridBagConstraints16.gridy = 1;
			gridBagConstraints16.ipadx = 1;
			gridBagConstraints16.ipady = 1;
			gridBagConstraints16.weightx = 1.0;
			gridBagConstraints16.weighty = 1.0;
			gridBagConstraints16.insets = new Insets(1, 1, 5, 1);
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.insets = new Insets(5, 0, 1, 5);
			gridBagConstraints15.gridy = 0;
			gridBagConstraints15.ipadx = 0;
			gridBagConstraints15.ipady = 0;
			gridBagConstraints15.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints15.anchor = GridBagConstraints.WEST;
			gridBagConstraints15.gridx = 0;
			jLabel1 = new JLabel();
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jLabel1.setText("Postcode");
			jLabel1.setPreferredSize(new java.awt.Dimension(16,16));
			jPanel2.add(jLabel1, gridBagConstraints15);
			jPanel2.add(getJScrollPane2(), gridBagConstraints16);
		}
		return jPanel2;
	}
	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = GridBagConstraints.BOTH;
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.gridy = 1;
			gridBagConstraints14.ipadx = 0;
			gridBagConstraints14.ipady = 0;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.weighty = 1.0;
			gridBagConstraints14.insets = new Insets(0, 0, 5, 2);
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints13.gridy = 0;
			gridBagConstraints13.ipadx = 306;
			gridBagConstraints13.ipady = 3;
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints13.gridx = 0;
			jLabel = new JLabel();
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jLabel.setText("Precode");
			jLabel.setPreferredSize(new java.awt.Dimension(15,16));
			jPanel3.add(jLabel, gridBagConstraints13);
			jPanel3.add(getJScrollPane1(), gridBagConstraints14);
		}
		return jPanel3;
	}
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJtfTestValue() {
		if (jtfTestValue == null) {
			jtfTestValue = new JTextField();
			jtfTestValue.setPreferredSize(new java.awt.Dimension(160,20));
		}
		return jtfTestValue;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbAddTestValue() {
		if (jbAddTestValue == null) {
			jbAddTestValue = new JButton();
			jbAddTestValue.setText("Add");
			jbAddTestValue.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					addTestValue();
				}
			});
		}
		return jbAddTestValue;
	}
	/**
     * 
     */
    protected void addTestValue() {
        if (this.mSelectedParameter==null) return;
        if (this.jtfTestValue.getText().trim().length()==0) return;
        this.mSelectedParameter.addTestValue(new PrimitiveValue(this.jtfTestValue.getText()));
        ((DefaultListModel) this.jlTestValues.getModel()).addElement(this.jtfTestValue.getText());
        this.jtfTestValue.setText("");        
    }

    /**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJlTestValues());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */    
	private JList getJlTestValues() {
		if (jlTestValues == null) {
			jlTestValues = new JList();
			jlTestValues.setModel(getTestValuesModel());
			jlTestValues.addListSelectionListener(new javax.swing.event.ListSelectionListener() { 
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {    
				    assignException();
				}
			});
			jlTestValues.addFocusListener(new java.awt.event.FocusAdapter() { 
				public void focusLost(java.awt.event.FocusEvent e) {    
					assignException();
				}
			});
			jlTestValues.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mousePressed(java.awt.event.MouseEvent e) {    
					selectValue();
				}
			});
		}
		return jlTestValues;
	}

	/**
     * 
     */
    protected void selectValue() {
        int index=this.jlTestValues.getSelectedIndex();
        if (index==-1) return;
        mSelectedTestValue=this.mSelectedParameter.getTestValue(index);
        this.jlTheValue.setText(mSelectedTestValue.getValue().toString());
        this.jlTheValue.setToolTipText(mSelectedTestValue.getValue().toString());
        String exName=mSelectedTestValue.getExceptionLaunched();
        if (exName!=null)
            exModel.setSelectedItem(exName);
        else exModel.setSelectedItem("");
    }

    /**
	 * This method initializes testValuesModel	
	 * 	
	 * @return javax.swing.DefaultListModel	
	 */    
	private DefaultListModel getTestValuesModel() {
		if (testValuesModel == null) {
			testValuesModel = new DefaultListModel();
		}
		return testValuesModel;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Remove");
			jButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					removeTestValue();
				}
			});
		}
		return jButton;
	}
	/**
     * 
     */
    protected void removeTestValue() {
        int index=this.jlTestValues.getSelectedIndex();
        if (index==-1) return;
        testValuesModel.remove(index);
        this.mSelectedParameter.removeTestValue(index);
    }

    /**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJepPrecode());
		}
		return jScrollPane1;
	}
	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */    
	private JTextPane getJepPrecode() {
		if (jepPrecode == null) {
			jepPrecode = new JTextPane();
			jepPrecode.setToolTipText("Use \"obtained\" to reference the instance under test; \"argX\" to reference argument #X; \"result\" to reference the result");
			jepPrecode.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 11));
			jepPrecode.addFocusListener(new java.awt.event.FocusAdapter() { 
				public void focusLost(java.awt.event.FocusEvent e) {    
					addPre();
				}
			});
		}
		return jepPrecode;
	}
	/**
     * 
     */
    protected void addPre() {
        this.mSelectedOperation.removePreassertions();
        this.mSelectedOperation.setPreassertions(jepPrecode.getText());
    }

    /**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJepPostcode());
		}
		return jScrollPane2;
	}
	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */    
	private JTextPane getJepPostcode() {
		if (jepPostcode == null) {
			jepPostcode = new JTextPane();
			jepPostcode.setToolTipText("Use \"obtained\" to reference the instance under test; \"argX\" to reference argument #X; \"result\" to reference the result");
			jepPostcode.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 11));
			jepPostcode.addFocusListener(new java.awt.event.FocusAdapter() { 
				public void focusLost(java.awt.event.FocusEvent e) {    
					addPost();
				}
			});
		}
		return jepPostcode;
	}
	/**
     * 
     */
    protected void addPost() {
        this.mSelectedOperation.removePostassertions();
        this.mSelectedOperation.setPostassertions(this.jepPostcode.getText());
    }

    /**
	 * This method initializes JSPClassStructure	
	 * 	
	 * @return testooj3.gui.components.JSPClassStructure	
	 */    
	private JSPClassStructure getJSPClassStructure() {
		if (JSPClassStructure == null) {
			JSPClassStructure = new JSPClassStructure();
		}
		return JSPClassStructure;
	}
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#showMembers(testooj3.domain.TClass)
     */
    public void showMembers(Interface c) {
        // TODO Auto-generated method stub
        
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#log(java.lang.String)
     */
    public void log(String msg) {
        this.mLogWindow.log(msg);
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#logError(java.lang.String)
     */
    public void logError(String string) {
        this.mLogWindow.logError(string);
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#setClass(testooj3.domain.TClass)
     */
    public void setClass(Interface c) {
        this.mClass=c;
        this.JSPClassStructure.showLevels(3);
        this.JSPClassStructure.setClass(c);
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#methodSelected(int)
     */
    public void methodSelected(TOperation operation) {
        mSelectedOperation=operation;
        this.setTitle("JUnit assertions for " + mSelectedOperation.getId());     
        jepPrecode.setText("");
        String oPre=mSelectedOperation.getPreassertions();
        jepPrecode.setText(mSelectedOperation.getPreassertions());
        jepPostcode.setText("");
        String oPost=mSelectedOperation.getPostassertions();
        jepPostcode.setText(mSelectedOperation.getPostassertions());
        exModel.removeAllElements();
        exModel.addElement("");
        for (int i=0; i<mSelectedOperation.getLaunchedExceptions().size(); i++) {
            String exName=this.mSelectedOperation.getLaunchedExceptions().get(i).toString();
            exModel.addElement(exName);
        }
    }

    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#parameterSelected(testooj3.domain.TParameter)
     */
    public void parameterSelected(TParameter p) {
        this.mSelectedParameter=p;
        DefaultListModel m=(DefaultListModel) this.jlTestValues.getModel();
        m.removeAllElements();
        for (int i=0; i<p.getTestValues().length; i++) 
          m.addElement(p.getTestValue(i).getValue().toString());
        this.jchUseValuesAsDescribed.setSelected(p.useValuesAsDescribed());
        this.jtfNumberOfRandomValues.setText(""+p.getNumberOfRandomTestValues());
    }
	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.insets = new Insets(3, 2, 5, 8);
			gridBagConstraints22.gridy = 1;
			gridBagConstraints22.ipadx = 9;
			gridBagConstraints22.ipady = 0;
			gridBagConstraints22.anchor = GridBagConstraints.EAST;
			gridBagConstraints22.gridx = 2;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.insets = new Insets(2, 2, 2, 4);
			gridBagConstraints21.gridy = 1;
			gridBagConstraints21.ipadx = 1;
			gridBagConstraints21.ipady = 1;
			gridBagConstraints21.anchor = GridBagConstraints.WEST;
			gridBagConstraints21.gridx = 1;
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.insets = new Insets(2, 4, 2, 4);
			gridBagConstraints20.gridy = 1;
			gridBagConstraints20.ipadx = 1;
			gridBagConstraints20.ipady = 1;
			gridBagConstraints20.anchor = GridBagConstraints.EAST;
			gridBagConstraints20.fill = GridBagConstraints.NONE;
			gridBagConstraints20.gridx = 0;
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.fill = GridBagConstraints.BOTH;
			gridBagConstraints19.gridwidth = 3;
			gridBagConstraints19.gridx = 0;
			gridBagConstraints19.gridy = 0;
			gridBagConstraints19.ipadx = 1;
			gridBagConstraints19.ipady = 1;
			gridBagConstraints19.weightx = 1.0;
			gridBagConstraints19.weighty = 1.0;
			gridBagConstraints19.insets = new Insets(0, 0, 3, 6);
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GridBagLayout());
			jPanel4.add(getJSPClassStructure(), gridBagConstraints19);
			jPanel4.add(getJbOpenFile(), gridBagConstraints20);
			jPanel4.add(getJbSaveAndExit(), gridBagConstraints21);
			jPanel4.add(getJbExitNoSaving(), gridBagConstraints22);
		}
		return jPanel4;
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbOpenFile() {
		if (jbOpenFile == null) {
			jbOpenFile = new JButton();
			jbOpenFile.setText("Open file");
			jbOpenFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					openFile();
				}
			});
		}
		return jbOpenFile;
	}
	/**
     * 
     */
    protected void openFile() {
        String path=Configuration.getInstance().getConstraintsPath() + mClass.getName() + "\\" ;
        try {
          mClass.loadDescription();
        }
        catch (Exception ex) 
        {
          mLogWindow.logError(ex.toString());
        }    
    }

    /**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbSaveAndExit() {
		if (jbSaveAndExit == null) {
			jbSaveAndExit = new JButton();
			jbSaveAndExit.setText("Save and exit");
			jbSaveAndExit.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					saveAndExit();
				}
			});
		}
		return jbSaveAndExit;
	}
	/**
     * 
     */
    protected void saveAndExit() {
        if (this.mSelectedOperation==null) {
            dispose();
        } else {
	        mSelectedOperation.removePostassertions();
	        mSelectedOperation.removePreassertions();
	        mSelectedOperation.setPreassertions(this.jepPrecode.getText());
	        mSelectedOperation.setPostassertions(this.jepPostcode.getText());
	        try {
	            String path=Configuration.getInstance().getConstraintsPath();
	            mClass.saveDescription();
	            dispose();
	          }
	          catch (Exception ex) 
	          {
	            mLogWindow.logError(ex.toString());
	          }
        }
    }

    /**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbExitNoSaving() {
		if (jbExitNoSaving == null) {
			jbExitNoSaving = new JButton();
			jbExitNoSaving.setText("Exit, no saving");
			jbExitNoSaving.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					dispose();
				}
			});
		}
		return jbExitNoSaving;
	}
	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */    
	private JComboBox getJcbExceptionList() {
		if (jcbExceptionList == null) {
			jcbExceptionList = new JComboBox();
			jcbExceptionList.addFocusListener(new java.awt.event.FocusAdapter() { 
				public void focusLost(java.awt.event.FocusEvent e) {    
					assignException();
				}
			});
		}
		return jcbExceptionList;
	}

    /**
     * 
     */
    protected void assignException() {
        if (this.mSelectedTestValue!=null) {
            String selectedException=exModel.getSelectedItem().toString();
            if (selectedException.length()!=0)
                this.mSelectedTestValue.setExceptionLaunched(selectedException);
            else
                this.mSelectedTestValue.setExceptionLaunched(null);
        }
    }

    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#stateSelected(testooj3.domain.states.State)
     */
    public void stateSelected(State selectedState) {
        // TODO Auto-generated method stub
        
    }

	/**
	 * This method initializes jtfNumberOfRandomValues	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfNumberOfRandomValues() {
		if (jtfNumberOfRandomValues == null) {
			jtfNumberOfRandomValues = new JTextField();
			jtfNumberOfRandomValues.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					assignNumberOfRandomValues();
				}
			});
		}
		return jtfNumberOfRandomValues;
	}

	protected void assignNumberOfRandomValues() {
		if (this.mSelectedParameter==null) return;
		String s=this.jtfNumberOfRandomValues.getText();
		try {
			int n=Integer.parseInt(s);
			this.mSelectedParameter.setNumberOfRandomTestValues(n);
		}
		catch (Exception ex) {
			this.jtfNumberOfRandomValues.setText("0");
		}
	}

	/**
	 * This method initializes jchUseValuesAsDescribed	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJchUseValuesAsDescribed() {
		if (jchUseValuesAsDescribed == null) {
			jchUseValuesAsDescribed = new JCheckBox();
			jchUseValuesAsDescribed.setText("Use values as described");
			jchUseValuesAsDescribed.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					useValuesAsDescribed();
				}
			});
		}
		return jchUseValuesAsDescribed;
	}

	protected void useValuesAsDescribed() {
		this.mSelectedParameter.useValuesAsDescribed(this.jchUseValuesAsDescribed.isSelected());
	}

	/**
	 * This method initializes jbGenerate	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbGenerate() {
		if (jbGenerate == null) {
			jbGenerate = new JButton();
			jbGenerate.setText("Generate");
			jbGenerate.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					generateTestValues();
				}
			});
		}
		return jbGenerate;
	}

	protected void generateTestValues() {
		try {
			if (this.mSelectedParameter==null) return;
			
		} catch (Exception e) {
			this.logError(e.toString());
		} 
	}
	
	public void fieldSelected(TField selectedField) {
		// Intencionadamente en blanco.
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

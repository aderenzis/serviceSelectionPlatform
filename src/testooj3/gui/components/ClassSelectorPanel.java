package testooj3.gui.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TestoojClassLoader;
import testooj3.gui.JDClassPathExplorer;
import testooj3.gui.JDOperationsSelection;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
@SuppressWarnings("serial")
public class ClassSelectorPanel extends JPanel {

	private JButton jbClassSelection = null;
	private JTextField jtfClassName = null;
	private JCheckBox jchIncludeInherited = null;
	private JButton jbLoadClass = null;
    private IMainWindow mParentWindow;
	private JLabel jLabel = null;
	private JTextField jtfClassPath = null;
    /**
     * @param arg0
     * @param arg1
     */
    public ClassSelectorPanel(LayoutManager arg0, boolean arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
   
		initialize();
 }

    /**
     * @param arg0
     */
    public ClassSelectorPanel(LayoutManager arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
   
		initialize();
 }

    /**
     * @param arg0
     */
    public ClassSelectorPanel(boolean arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
   
		initialize();
 }

    /**
     * 
     */
    public ClassSelectorPanel() {
        super();   
		initialize();
		this.jtfClassPath.setText(Configuration.getInstance().getClassPath());
 }

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private  void initialize() {
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints5.gridwidth = 2;
		gridBagConstraints5.gridx = 2;
		gridBagConstraints5.gridy = 0;
		gridBagConstraints5.ipadx = 0;
		gridBagConstraints5.ipady = 0;
		gridBagConstraints5.weightx = 1.0;
		gridBagConstraints5.insets = new Insets(16, 4, 0, 17);
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.insets = new Insets(16, 15, 2, 3);
		gridBagConstraints4.gridy = 0;
		gridBagConstraints4.ipadx = 10;
		gridBagConstraints4.ipady = 8;
		gridBagConstraints4.anchor = GridBagConstraints.EAST;
		gridBagConstraints4.fill = GridBagConstraints.NONE;
		gridBagConstraints4.gridx = 0;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.insets = new Insets(5, 5, 20, 0);
		gridBagConstraints3.gridy = 2;
		gridBagConstraints3.ipadx = 81;
		gridBagConstraints3.ipady = 0;
		gridBagConstraints3.anchor = GridBagConstraints.WEST;
		gridBagConstraints3.gridx = 3;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.insets = new Insets(0, 6, 2, 9);
		gridBagConstraints2.gridy = 1;
		gridBagConstraints2.ipadx = 16;
		gridBagConstraints2.ipady = 0;
		gridBagConstraints2.anchor = GridBagConstraints.WEST;
		gridBagConstraints2.gridwidth = 1;
		gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints2.gridx = 3;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.gridheight = 1;
		gridBagConstraints1.gridx = 2;
		gridBagConstraints1.gridy = 2;
		gridBagConstraints1.ipadx = 0;
		gridBagConstraints1.ipady = 4;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.insets = new Insets(5, 6, 20, 6);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 8, 20, 5);
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.gridheight = 1;
		jLabel = new JLabel();
		jLabel.setText("Class path:");
		this.setLayout(new GridBagLayout());
		this.setSize(680, 106);
		this.setPreferredSize(new Dimension(680, 106));
		this.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
		this.add(getJbClassSelection(), gridBagConstraints);
		this.add(getJtfClassName(), gridBagConstraints1);
		this.add(getJchIncludeInherited(), gridBagConstraints2);
		this.add(getJbLoadClass(), gridBagConstraints3);
		this.add(jLabel, gridBagConstraints4);
		this.add(getJtfClassPath(), gridBagConstraints5);
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbClassSelection() {
		if (jbClassSelection == null) {
			jbClassSelection = new JButton();
			jbClassSelection.setName("jButton");
			jbClassSelection.setText("Select class");
			jbClassSelection.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					openClassPathLoader();
				}
			});
		}
		return jbClassSelection;
	}
	/**
     * 
     */
    protected void openClassPathLoader() {
    	Configuration.getInstance().setClassPath(this.jtfClassPath.getText());
        JDClassPathExplorer j=new JDClassPathExplorer();
        j.setModal(true);
        j.setVisible(true);
        if (j.getSelectedClass()!=null)
            this.jtfClassName.setText(j.getSelectedClass());
    }

    /**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJtfClassName() {
		if (jtfClassName == null) {
			jtfClassName = new JTextField();
		}
		return jtfClassName;
	}
	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJchIncludeInherited() {
		if (jchIncludeInherited == null) {
			jchIncludeInherited = new JCheckBox();
			jchIncludeInherited.setText("Consider inherited operations");
		}
		return jchIncludeInherited;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbLoadClass() {
		if (jbLoadClass == null) {
			jbLoadClass = new JButton();
			jbLoadClass.setText("Load members");
			jbLoadClass.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					loadMembers();
				}
			});
		}
		return jbLoadClass;
	}

    /**
     * @throws  
     * 
     */
    protected void loadMembers() {
    	Interface c=null;
        try 
        {
            if (this.jtfClassName.getText()==null || this.jtfClassName.getText().trim().length()==0)
                throw new Exception("Write or select a valid class");
          c=TestoojClassLoader.load(this.jtfClassName.getText(), this.jtfClassPath.getText(), this.jchIncludeInherited.isSelected());
          //c=new TClass(Class.forName(this.jtfClassName.getText()), this.jchIncludeInherited.isSelected());
          if (c.getConstructors().size()+c.getMethods().size()>50) {
              JDOperationsSelection j=new JDOperationsSelection(this.mParentWindow, c);
              j.setModal(true);
              j.setVisible(true);    
              c.reassignIds();
          }
          mParentWindow.setClass(c);
          mParentWindow.showMembers(c);
        }
        catch (ClassNotFoundException ex) 
        {
          mParentWindow.logError("The class under test (" + jtfClassName.getText() + ") has not been found");
        } 
        catch (MalformedURLException ex) {
        	mParentWindow.logError("There is an error in the classpath (MalformedURLException");
        }
        catch (Exception ex) {
        	mParentWindow.logError(ex.toString());
        }
    }

    /**
     * @param unit
     */
    public void setParentWindow(IMainWindow v) {
        this.mParentWindow=v;   
    }

	/**
	 * This method initializes jtfClassPath	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfClassPath() {
		if (jtfClassPath == null) {
			jtfClassPath = new JTextField();
		}
		return jtfClassPath;
	}

	public String getClassPath() {
		return this.jtfClassPath.getText();
	}

	public void setClassPath(String classPath) {
		this.jtfClassPath.setText(classPath);
	}
    }  //  @jve:decl-index=0:visual-constraint="10,10"

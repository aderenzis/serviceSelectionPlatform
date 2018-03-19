package testooj3.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TField;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.states.State;
import testooj3.gui.components.ClassSelectorPanel;
import testooj3.gui.components.ClassStructurePanel;
import testooj3.gui.components.IMainWindow;
import testooj3.gui.components.JSPClassStructure;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class JDObjectCreation extends JDialog implements IMainWindow {

	private javax.swing.JPanel jContentPane = null;
	private ClassSelectorPanel classSelectorPanel = null;
	private JScrollPane jScrollPane = null;
	private JEditorPane jepCode = null;
	private JScrollPane jScrollPane1 = null;
	private JEditorPane jepMsg = null;
	private JSplitPane jSplitPane = null;
	private JButton jbSave = null;
	private JButton jbCompile = null;
    private Interface mClass;  //  @jve:decl-index=0:
	private JSPClassStructure jSPClassStructurePanel = null;
	private JPanel jPanelSC = null;
	/**
	 * This is the default constructor
	 */
	public JDObjectCreation() {
		super();
		initialize();
		this.jSPClassStructurePanel.setParentWindow(this);
		this.classSelectorPanel.setParentWindow(this);
        this.jSPClassStructurePanel.showLevels(3);
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Manual creation of objects");
		this.setSize(905, 581);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(4, 6, 3, 5);
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.ipadx = 0;
			gridBagConstraints5.ipady = 0;
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.ipadx = 2;
			gridBagConstraints4.ipady = 2;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.insets = new Insets(1, 5, 3, 1);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(5, 6, 0, 5);
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.ipadx = 20;
			gridBagConstraints3.ipady = 0;
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridx = 0;
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getClassSelectorPanel(), gridBagConstraints3);
			jContentPane.add(getJSplitPane(), gridBagConstraints4);
			jContentPane.add(getJPanelSC(), gridBagConstraints5);
		}
		return jContentPane;
	}
	/**
	 * This method initializes classSelectorPanel	
	 * 	
	 * @return testooj3.gui.components.ClassSelectorPanel	
	 */    
	private ClassSelectorPanel getClassSelectorPanel() {
		if (classSelectorPanel == null) {
			classSelectorPanel = new ClassSelectorPanel();
		}
		return classSelectorPanel;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJepCode());
		}
		return jScrollPane;
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
	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJepMsg());
		}
		return jScrollPane1;
	}
	/**
	 * This method initializes jEditorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */    
	private JEditorPane getJepMsg() {
		if (jepMsg == null) {
			jepMsg = new JEditorPane();
			jepMsg.setContentType("text/html");
		}
		return jepMsg;
	}
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#showMembers(testooj3.domain.TClass)
     */
    public void showMembers(Interface c) {
        // TODO Auto-generated method stub
        
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#logError(java.lang.String)
     */
    public void logError(String msg) {
        this.jepMsg.setText("<font color='red'>" + msg + "</font>");
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#setClass(testooj3.domain.TClass)
     */
    public void setClass(Interface c) {
        this.mClass=c;
        this.jSPClassStructurePanel.setClass(c);
        showFile();
    }
    
    private void showFile() 
    {
      String className=mClass.getName();
      if (className.indexOf(".")!=-1) 
      {
        className=className.substring(className.lastIndexOf(".")+1);
      }
      String paquete=Configuration.getInstance().getCreatorsPackage();
      paquete=Auxi.substituteAll(paquete, "\\", ".");
      String s="package " + paquete + ";\n\n";
      s+="import " + mClass.getName() + ";\n";
      s+="import java.io.FileOutputStream;\n";
      s+="import java.io.ObjectOutputStream;\n\n";
      s+="public class Create" + className + " {\n";
      s+="\tpublic static void create() {\n";
      s+="\t\ttry {\n";
      s+="\t\t\t" + className + " o=new " + className + "();\n";
      s+="\t\t\t// TODO : insert here the construction code\n";
      String auxi=Configuration.getInstance().getSerializedObjectsPath(), path="";
      for (int i=0; i<auxi.length(); i++)
        if (auxi.charAt(i)=='\\')
          path=path+"\\\\";
        else
          path=path+auxi.charAt(i);
      s+="\t\t\tFileOutputStream f=new FileOutputStream(\"" + 
        path + mClass.getName() + ".ser\");\n";
      s+="\t\t\tObjectOutputStream os=new ObjectOutputStream(f);\n";
      s+="\t\t\tos.writeObject(o);\n";
      s+="\t\t\tf.close();\n";
      s+="\t\t}\n";
      s+="\t\tcatch (Exception ex) {\n";
      s+="\t\t\tSystem.err.println(ex.toString());\n";
      s+="\t\t}\n";
      s+="\t}\n";
      s+="\n";
      s+="\tpublic static void main(String[] args) {\n";
      s+="\t\tCreate" + className + ".create();\n";
      s+="\t}\n";
      s+="}\n";
      s=Auxi.substituteAll(s, "\t", "     ");
      this.jepCode.setText(s);
    }
    
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#passToRegularExpression(int)
     */
    public void methodSelected(TOperation operation) {
        // TODO Auto-generated method stub
        
    }
	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */    
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setRightComponent(getJScrollPane());
			jSplitPane.setLeftComponent(getJSPClassStructurePanel());
			jSplitPane.setDividerLocation(260);
		}
		return jSplitPane;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbSave() {
		if (jbSave == null) {
			jbSave = new JButton();
			jbSave.setText("Save");
			jbSave.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					save();
				}
			});
		}
		return jbSave;
	}
	/**
     * 
     */
    protected void save() {
        try 
        {
            String className=mClass.getName();
            if (className.indexOf(".")!=-1) 
            {
              className=className.substring(className.lastIndexOf(".")+1);
            }
            String path=Configuration.getInstance().getCreatorsPath();
            FileOutputStream f=new FileOutputStream(path+"Create" + className +".java");
            f.write(this.jepCode.getText().getBytes());
            f.close();
            this.jepMsg.setText("File saved at " + path + "Create" +className+".java" + jepMsg.getText());    
        }
        catch (Exception ex) 
        {
          this.jepMsg.setText("<font color=red>" + ex.getMessage() + "<font>" + jepMsg.getText());
        }
    }
    /**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbCompile() {
		if (jbCompile == null) {
			jbCompile = new JButton();
			jbCompile.setText("Compile");
		}
		return jbCompile;
	}
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#log(java.lang.String)
     */
    public void log(String msg) {
        this.jepMsg.setText(msg);
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#parameterSelected(testooj3.domain.TParameter)
     */
    public void parameterSelected(TParameter p) {
        // TODO Auto-generated method stub
        
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#stateSelected(testooj3.domain.states.State)
     */
    public void stateSelected(State selectedState) {
        // TODO Auto-generated method stub
        
    }
	/**
	 * This method initializes jSPClassStructurePanel	
	 * 	
	 * @return testooj3.gui.components.JSPClassStructure	
	 */
	private JSPClassStructure getJSPClassStructurePanel() {
		if (jSPClassStructurePanel == null) {
			jSPClassStructurePanel = new JSPClassStructure();
		}
		return jSPClassStructurePanel;
	}
	/**
	 * This method initializes jPanelSC	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelSC() {
		if (jPanelSC == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.gridheight = 2;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.insets = new Insets(1, 3, 1, 0);
			gridBagConstraints1.anchor = GridBagConstraints.NORTH;
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.insets = new Insets(1, 3, 1, 0);
			gridBagConstraints.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints.gridy = 0;
			jPanelSC = new JPanel();
			jPanelSC.setLayout(new GridBagLayout());
			jPanelSC.add(getJbSave(), gridBagConstraints);
			jPanelSC.add(getJbCompile(), gridBagConstraints1);
			jPanelSC.add(getJScrollPane1(), gridBagConstraints2);
		}
		return jPanelSC;
	}
	
	public void fieldSelected(TField selectedField) {
		// Intencionadamente en blanco.
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

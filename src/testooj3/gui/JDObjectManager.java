package testooj3.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import testooj3.domain.Configuration;
import testooj3.persistence.Agente;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class JDObjectManager extends JDialog {
    
    private javax.swing.JPanel jContentPane = null;
    private JButton jbFolderSelection = null;
    private JTextField jtfFolder = null;
    private JButton jbOpen = null;
    private JScrollPane jScrollPane = null;
    private JScrollPane jScrollPane1 = null;
    private JScrollPane jScrollPane2 = null;
    private JList jlObjects = null;
    private JList jlFields = null;
    private JList jlPublicMethods = null;
    private DefaultListModel modelObjects = null;   //  @jve:decl-index=0:
    private DefaultListModel modelFields = null;   //  @jve:decl-index=0:
    private DefaultListModel modelMethods = null;   //  @jve:decl-index=0:
    private Object mObject;
    private Class mClass;
	private JScrollPane jScrollPane3 = null;
	private JEditorPane jepMsg = null;
	private JPanel jPanelTop = null;
	/**
     * This is the default constructor
     */
    public JDObjectManager() {
        super();
        initialize();
        this.jtfFolder.setText(Configuration.getInstance().getSerializedObjectsPath());
    }
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setTitle("Object manager");
        this.setSize(632, 470);
        this.setContentPane(getJContentPane());
    }
    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJContentPane() {
        if(jContentPane == null) {
            GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
            gridBagConstraints7.insets = new Insets(5, 5, 5, 5);
            gridBagConstraints7.gridx = 0;
            gridBagConstraints7.gridy = 0;
            gridBagConstraints7.ipadx = 1;
            gridBagConstraints7.ipady = 1;
            gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints7.anchor = GridBagConstraints.CENTER;
            gridBagConstraints7.gridwidth = 2;
            GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.fill = GridBagConstraints.BOTH;
            gridBagConstraints6.gridx = 1;
            gridBagConstraints6.gridy = 1;
            gridBagConstraints6.ipadx = 0;
            gridBagConstraints6.ipady = 0;
            gridBagConstraints6.weightx = 1.0;
            gridBagConstraints6.weighty = 1.0;
            gridBagConstraints6.anchor = GridBagConstraints.WEST;
            gridBagConstraints6.insets = new Insets(5, 5, 5, 5);
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.fill = GridBagConstraints.BOTH;
            gridBagConstraints5.gridwidth = 2;
            gridBagConstraints5.gridx = 0;
            gridBagConstraints5.gridy = 3;
            gridBagConstraints5.ipadx = 1;
            gridBagConstraints5.ipady = 1;
            gridBagConstraints5.weightx = 1.0;
            gridBagConstraints5.weighty = 1.0;
            gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.fill = GridBagConstraints.BOTH;
            gridBagConstraints4.gridx = 1;
            gridBagConstraints4.gridy = 2;
            gridBagConstraints4.ipadx = 0;
            gridBagConstraints4.ipady = 0;
            gridBagConstraints4.weightx = 1.0;
            gridBagConstraints4.weighty = 1.0;
            gridBagConstraints4.anchor = GridBagConstraints.WEST;
            gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.fill = GridBagConstraints.BOTH;
            gridBagConstraints3.gridheight = 2;
            gridBagConstraints3.gridx = 0;
            gridBagConstraints3.gridy = 1;
            gridBagConstraints3.ipadx = 0;
            gridBagConstraints3.ipady = 0;
            gridBagConstraints3.weightx = 1.0;
            gridBagConstraints3.weighty = 1.0;
            gridBagConstraints3.anchor = GridBagConstraints.WEST;
            gridBagConstraints3.insets = new Insets(5, 5, 5, 5);
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(new GridBagLayout());
            jContentPane.add(getJScrollPane(), gridBagConstraints3);
            jContentPane.add(getJScrollPane2(), gridBagConstraints4);
            jContentPane.add(getJScrollPane3(), gridBagConstraints5);
            jContentPane.add(getJScrollPane1(), gridBagConstraints6);
            jContentPane.add(getJPanelTop(), gridBagConstraints7);
        }
        return jContentPane;
    }
    /**
     * This method initializes jButton	
     * 	
     * @return javax.swing.JButton	
     */    
    private JButton getJbFolderSelection() {
        if (jbFolderSelection == null) {
            jbFolderSelection = new JButton();
            jbFolderSelection.setText("Select a folder");
            jbFolderSelection.addActionListener(new java.awt.event.ActionListener() { 
                public void actionPerformed(java.awt.event.ActionEvent e) {    
                    folderSelection();
                }
            });
        }
        return jbFolderSelection;
    }
    /**
     * 
     */
    protected void folderSelection() {
        String path=Configuration.getInstance().getSerializedObjectsPath();
        JFileChooser chooser = new JFileChooser(path);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) 
            this.jtfFolder.setText(chooser.getSelectedFile().getAbsolutePath());
    }
    /**
     * This method initializes jTextField	
     * 	
     * @return javax.swing.JTextField	
     */    
    private JTextField getJtfFolder() {
        if (jtfFolder == null) {
            jtfFolder = new JTextField();
            jtfFolder.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));
        }
        return jtfFolder;
    }
    /**
     * This method initializes jButton	
     * 	
     * @return javax.swing.JButton	
     */    
    private JButton getJbOpen() {
        if (jbOpen == null) {
            jbOpen = new JButton();
            jbOpen.setText("Open");
            jbOpen.addActionListener(new java.awt.event.ActionListener() { 
                public void actionPerformed(java.awt.event.ActionEvent e) {    
                    openFolder();
                }
            });
        }
        return jbOpen;
    }
    /**
     * 
     */
    protected void openFolder() {
        jlObjects.removeAll ();
        Vector lista;
        try {
            lista = Agente.getAgente().lista(this.jtfFolder.getText(), "ser", false);
            if (lista.size()==0) {
                this.getModelObjects().addElement("There are no serialized objects in the folder");
            } else{
                for (int i=0; i<lista.size() ; i++) {
                    this.getModelObjects().addElement(lista.elementAt(i).toString());
                }
            }            
        } catch (Exception e) {
            jepMsg.setText("<font color='red'>" + e.toString() + "</font>");
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
            jScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Objects in folder", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
            jScrollPane.setViewportView(getJlObjects());
        }
        return jScrollPane;
    }
    /**
     * This method initializes jScrollPane1	
     * 	
     * @return javax.swing.JScrollPane	
     */    
    private JScrollPane getJScrollPane1() {
        if (jScrollPane1 == null) {
            jScrollPane1 = new JScrollPane();
            jScrollPane1.setBorder(BorderFactory.createTitledBorder(null, "Fields", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
            jScrollPane1.setViewportView(getJlFields());
        }
        return jScrollPane1;
    }
    /**
     * This method initializes jScrollPane2	
     * 	
     * @return javax.swing.JScrollPane	
     */    
    private JScrollPane getJScrollPane2() {
        if (jScrollPane2 == null) {
            jScrollPane2 = new JScrollPane();
            jScrollPane2.setBorder(BorderFactory.createTitledBorder(null, "Public methods with no parameters", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
            jScrollPane2.setViewportView(getJlPublicMethods());
        }
        return jScrollPane2;
    }
    /**
     * This method initializes jList	
     * 	
     * @return javax.swing.JList	
     */    
    private JList getJlObjects() {
        if (jlObjects == null) {
            jlObjects = new JList();
            jlObjects.setModel(getModelObjects());
            jlObjects.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 9));
            jlObjects.addMouseListener(new java.awt.event.MouseAdapter() { 
            	public void mousePressed(java.awt.event.MouseEvent e) {    
            		showObject();
            	}
            });
        }
        return jlObjects;
    }
    /**
     * 
     */
    protected void showObject() {
        int index=jlObjects.getSelectedIndex();
        if (index==-1)
            return;
        try {
            String fichero=this.jtfFolder.getText() + "\\" + 
            	this.getModelObjects().getElementAt(index);
            mObject=Agente.getAgente().cargarObjeto(fichero);
            show(fichero);
        }
        catch (Exception ex) 
        {
            jepMsg.setText("<font color='red'>" + ex.toString() + "</font>");
        }
    }
    
    private void show(String fichero) throws Exception
    {
        this.getModelFields().removeAllElements();
        //this.getModelFields().addElement(fichero);
        jlPublicMethods.removeAll();
        mClass=mObject.getClass();
        Field[] ff=mClass.getDeclaredFields();
        for (int i=0; i<ff.length; i++) 
        {
            Field f=ff[i];
            String vis=Modifier.isPublic(f.getModifiers()) ? "public" : "no_public";
            String tipo=getType(f.getType()); 
            String valor="no_visible";
            try {
                valor=getValue(f.get(mObject));
            }
            catch (NullPointerException e) 
            {
                valor="[null]";
            }
            catch (Exception e) 
            {
                valor="[" + e.getMessage() + "]";
            }
            getModelFields().addElement(vis + " " + tipo + " " + f.getName() + "=" + valor);
        }
        Method[] mm=mClass.getMethods();
        DefaultListModel modelo=new DefaultListModel();
        for (int i=0; i<mm.length; i++) 
        {
            Method m=mm[i];
            if (m.getParameterTypes().length==0) {
                String tipo=getType(m.getReturnType());
                modelo.addElement("public " + tipo + " " + m.getName());
            }
        }    
        jlPublicMethods.setModel(modelo);
    }
    
    private String getType(Class tipo)
    {
        if (!tipo.isArray())
            return tipo.getName();
        return tipo.getComponentType().getName() + "[]";
    }
    
    private String getValue(Object o) throws Exception
    {
        Class tipo=o.getClass();
        if (tipo.isArray()) 
        {
            int longitud=Array.getLength(o);
            String r="";
            for (int i=0; i<longitud; i++) 
                r+=Array.get(o, i) + ", ";
            r="[" + r.substring(0, r.length()-2) + "]";
            return r;
        } else if (tipo.isPrimitive() || tipo.getName().startsWith("java.lang")) {
            return o.toString();
        } else {
            Field ff[]=tipo.getDeclaredFields();
            String r="";
            for (int i=0; i<ff.length; i++) 
            {
                Field f=ff[i];
                r+=getValue(f.get(o));
            }
            return o.toString();
        }
        //return o.toString();
    }
    
    /**
     * This method initializes jList	
     * 	
     * @return javax.swing.JList	
     */    
    private JList getJlFields() {
        if (jlFields == null) {
            jlFields = new JList();
            jlFields.setModel(getModelFields());
            jlFields.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 9));
        }
        return jlFields;
    }
    /**
     * This method initializes jList	
     * 	
     * @return javax.swing.JList	
     */    
    private JList getJlPublicMethods() {
        if (jlPublicMethods == null) {
            jlPublicMethods = new JList();
            jlPublicMethods.setModel(getModelMethods());
            jlPublicMethods.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 9));
            jlPublicMethods.addMouseListener(new java.awt.event.MouseAdapter() { 
            	public void mousePressed(java.awt.event.MouseEvent e) {    
            	    showValueOfMethod();
            	}
            });
        }
        return jlPublicMethods;
    }
    /**
     * 
     */
    protected void showValueOfMethod() {
        try {
            Object oM=jlPublicMethods.getSelectedValue();
            if (oM==null) return;
            String methodName=oM.toString().substring(oM.toString().lastIndexOf(" ")).trim();
            Method m=this.mClass.getMethod(methodName, null);
            Object result=m.invoke(mObject, null);
            log("<b>" + methodName+ "</b>=" + result.toString());
          }
          catch (NoSuchMethodException ex) 
          {
            this.jepMsg.setText("<font color=red>" + ex.toString() + "</font>");
          }
          catch (IllegalAccessException ex) 
          {
            this.jepMsg.setText("<font color=red>" + ex.toString() + "</font>");
          }    
          catch (InvocationTargetException ex) 
          {
            this.jepMsg.setText("<font color=red>" + ex.toString() + "</font>");
          }        
    }
    /**
     * @param string
     */
    private void log(String msg) {
        msg=msg + jepMsg.getText().trim();
        this.jepMsg.setText(msg);
        jepMsg.select(0, 0);  
    }
    /**
     * This method initializes modelObjects	
     * 	
     * @return javax.swing.DefaultListModel	
     */    
    private DefaultListModel getModelObjects() {
        if (modelObjects == null) {
            modelObjects = new DefaultListModel();
        }
        return modelObjects;
    }
    /**
     * This method initializes modelFields	
     * 	
     * @return javax.swing.DefaultListModel	
     */    
    private DefaultListModel getModelFields() {
        if (modelFields == null) {
            modelFields = new DefaultListModel();
        }
        return modelFields;
    }
    /**
     * This method initializes modelMethods	
     * 	
     * @return javax.swing.DefaultListModel	
     */    
    private DefaultListModel getModelMethods() {
        if (modelMethods == null) {
            modelMethods = new DefaultListModel();
        }
        return modelMethods;
    }
	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getJepMsg());
		}
		return jScrollPane3;
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
	/**
	 * This method initializes jPanelTop	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelTop() {
		if (jPanelTop == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(0, 6, 0, 6);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 0;
			jPanelTop = new JPanel();
			jPanelTop.setLayout(new GridBagLayout());
			jPanelTop.add(getJbOpen(), gridBagConstraints);
			jPanelTop.add(getJtfFolder(), gridBagConstraints1);
			jPanelTop.add(getJbFolderSelection(), gridBagConstraints2);
		}
		return jPanelTop;
	}
  }  //  @jve:decl-index=0:visual-constraint="10,10"

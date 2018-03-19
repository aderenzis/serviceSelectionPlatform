package testooj3.gui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Method;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.TField;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class ClassStructurePanel extends JPanel {
    
    private JScrollPane jScrollPane = null;
    private JButton jbPassToRegularExpression = null;
    private JTree jtClassMembers = null;
    private Interface mClass;
    private IMainWindow mParentWindow;
	/**
     * This is the default constructor
     */
    public ClassStructurePanel() {
        super();
        initialize();
        DefaultMutableTreeNode empty=new DefaultMutableTreeNode("Class not selected");
        TreeModel modelo=new DefaultTreeModel(empty);
        this.jtClassMembers.setModel(modelo);
    }
    /**
     * This method initializes this
     * 
     * @return void
     */
    private  void initialize() {
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.insets = new Insets(3, 4, 8, 10);
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.ipadx = 4;
        gridBagConstraints1.ipady = 0;
        gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.gridheight = 3;
        gridBagConstraints1.gridx = 0;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.ipady = -102;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 2, 11);
        this.setLayout(new GridBagLayout());
        this.setSize(261, 304);
        this.add(getJScrollPane(), gridBagConstraints);
        this.add(getJbPassToRegularExpression(), gridBagConstraints1);
    }
    /**
     * This method initializes jScrollPane	
     * 	
     * @return javax.swing.JScrollPane	
     */    
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(getJtClassMembers());
        }
        return jScrollPane;
    }
    /**
     * This method initializes jButton	
     * 	
     * @return javax.swing.JButton	
     */    
    public JButton getJbPassToRegularExpression() {
        if (jbPassToRegularExpression == null) {
            jbPassToRegularExpression = new JButton();
            jbPassToRegularExpression.setText("Pass selection to regular expression");
            jbPassToRegularExpression.addActionListener(new java.awt.event.ActionListener() { 
                public void actionPerformed(java.awt.event.ActionEvent e) {    
                    addSelectionToRegularExpression();
                }
            });
        }
        return jbPassToRegularExpression;
    }
    /**
     * 
     */
    protected void addSelectionToRegularExpression() {
        TreePath path=jtClassMembers.getSelectionPath();
        if (path==null) return;
        DefaultMutableTreeNode nodo=(DefaultMutableTreeNode) path.getLastPathComponent();
        DefaultMutableTreeNode padre=(DefaultMutableTreeNode) nodo.getParent();
        if (path.getPathCount()==3 && !padre.getUserObject().equals("Fields")) {
            String operationSignature=nodo.getUserObject().toString();
            TOperation operation=mClass.getOperation(operationSignature);
            if (operation!=null)
            	this.mParentWindow.methodSelected(operation);
        } else {
            String fieldSignature="";
            for (int i=2; i<path.getPathCount(); i++)
            	fieldSignature=fieldSignature + "#" + path.getPath()[i].toString();
            if (fieldSignature.startsWith("#")) fieldSignature=fieldSignature.substring(1);
        	TField tf=null;
        	tf=mClass.findField(fieldSignature);
        	if (tf!=null)
        		this.mParentWindow.fieldSelected(tf);
        }
    }

	/**
     * This method initializes jTree	
     * 	
     * @return javax.swing.JTree	
     */    
    private JTree getJtClassMembers() {
        if (jtClassMembers == null) {
            jtClassMembers = new JTree();
            jtClassMembers.addMouseListener(new java.awt.event.MouseAdapter() { 
                public void mouseClicked(java.awt.event.MouseEvent e) {    
                    if (e.getClickCount()==2)
                        addSelectionToRegularExpression();
                }
            });
        }
        return jtClassMembers;
    }
    
    public void setClass(Interface c) {
        mClass=c;
        load();
    }
    /**
     * 
     */
    private void load() {
        try 
        {
        	String le;
            DefaultMutableTreeNode raiz=new DefaultMutableTreeNode(mClass.getName());
            DefaultMutableTreeNode fields=new DefaultMutableTreeNode("Fields");
            DefaultMutableTreeNode constructors=new DefaultMutableTreeNode("Constructors");
            DefaultMutableTreeNode methods=new DefaultMutableTreeNode("Methods");
            raiz.add(fields);
            raiz.add(constructors);
            raiz.add(methods);
            TreeModel modelo=new DefaultTreeModel(raiz);
            this.jtClassMembers.setModel(modelo);
            for (int i=0; i<mClass.getFields().size(); i++) 
            {
                TField f=(TField) mClass.getFields().get(i);
                DefaultMutableTreeNode fieldNode=new DefaultMutableTreeNode(f.getName() + " : " + f.getTypeName());
                fields.add(fieldNode);
                add(fieldNode, f.getSubFields());
            }	
            for (int i=0; i<mClass.getConstructors().size(); i++) 
            {
                TConstructor c=(TConstructor) mClass.getConstructors().get(i);
                constructors.add(new DefaultMutableTreeNode(c.getId()));
            }		      
            for (int i=0; i<mClass.getMethods().size(); i++) 
            {
                Operation m=mClass.getMethod(i);
            	boolean b = m.getLanzaExcepciones();
            	
            	if (b){
            		le = "*";}
            	else{
            		le = " ";}
            	
                methods.add(new DefaultMutableTreeNode(m.getId()+" "+le));
            }
        }
        catch (Exception ex) 
        {
            mParentWindow.logError("Error: " + ex.toString());
        }
    }
    
	private void add(DefaultMutableTreeNode raiz, Vector<TField> subFields) {
		for(TField f : subFields) {
			DefaultMutableTreeNode hijo=new DefaultMutableTreeNode(f.getName() + " : " + f.getTypeName());
			raiz.add(hijo);
			add(hijo, f.getSubFields());
		}
	}
	/**
     * @param unit
     */
    public void setParentWindow(IMainWindow v) {
        this.mParentWindow=v;
    }
}  //  @jve:decl-index=0:visual-constraint="8,12"

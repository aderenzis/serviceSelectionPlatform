package testooj3.gui.components;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.states.State;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class JSPClassStructure extends JScrollPane {
    
    private JTree jtClassMembers = null;
    private Interface mClass;
    private IMainWindow mParentWindow;
    private int mLevels;
    private boolean mShowStatesNode;
    /**
     * This is the default constructor
     */
    public JSPClassStructure() {
        super();
        initialize();
        DefaultMutableTreeNode empty=new DefaultMutableTreeNode("Class not selected");
        TreeModel modelo=new DefaultTreeModel(empty);
        this.jtClassMembers.setModel(modelo);
    }
    
    public void showLevels(int levels) {
        this.mLevels=levels;
    }
    
    public void setParentWindow(IMainWindow v) {
        this.mParentWindow=v;
    }
    /**
     * This method initializes this
     * 
     * @return void
     */
    private  void initialize() {
        this.setViewportView(getJtClassMembers());
        this.setSize(267, 310);
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
                    selectNode();
                }
            });
        }
        return jtClassMembers;
    }
    
    /**
     * 
     */
    protected void selectNode() {
        TreePath path=jtClassMembers.getSelectionPath();
        if (path==null) return;
        if (path.getPathCount()==3) {
            DefaultMutableTreeNode nodo=(DefaultMutableTreeNode) path.getLastPathComponent();
            DefaultMutableTreeNode padre=(DefaultMutableTreeNode) nodo.getParent();
            if (padre.getUserObject().toString().equals("States")) {
                String stateName=nodo.getUserObject().toString();
                State selectedState=mClass.getState(stateName);
                this.mParentWindow.stateSelected(selectedState);
            } else {
	            TOperation operation=null;
	    	    String operationSignature=nodo.getUserObject().toString();
	    	    operation=mClass.getOperation(operationSignature);
	            this.mParentWindow.methodSelected(operation);
            }
        } else if (path.getPathCount()==4) {     
            showParameterDescription(path);
        }
    }
    
    private void showParameterDescription(TreePath path) {
	    DefaultMutableTreeNode nodo=(DefaultMutableTreeNode) path.getLastPathComponent();
	    DefaultMutableTreeNode padre=(DefaultMutableTreeNode) nodo.getParent();
	    String operationSignature=padre.getUserObject().toString();
	    TOperation operation=mClass.getOperation(operationSignature);
        int pos=padre.getIndex(nodo);
        TParameter p=operation.getParametro(pos);	    
	    this.mParentWindow.parameterSelected(p);
    }
    
    public void setClass(Interface c) {
        mClass=c;
        load();
    }
    
    private void load() {
        try 
        {
            DefaultMutableTreeNode raiz=new DefaultMutableTreeNode(mClass.getName());
            DefaultMutableTreeNode constructors=new DefaultMutableTreeNode("Constructors");
            DefaultMutableTreeNode methods=new DefaultMutableTreeNode("Methods");
            DefaultMutableTreeNode states=new DefaultMutableTreeNode("States");
            raiz.add(constructors);
            raiz.add(methods);
            if (this.mShowStatesNode)
                raiz.add(states);
            TreeModel modelo=new DefaultTreeModel(raiz);
            this.jtClassMembers.setModel(modelo);
            for (int i=0; i<mClass.getConstructors().size(); i++) 
            {
                TConstructor c=(TConstructor) mClass.getConstructors().elementAt(i);
                DefaultMutableTreeNode nodoCons=new DefaultMutableTreeNode(c.getId());
                constructors.add(nodoCons);
                if (mLevels==3) {
                    for (int j=0; j<c.getParametros().size(); j++) {
                        TParameter p=c.getParametro(j);
                        DefaultMutableTreeNode nodoPar=new DefaultMutableTreeNode(p.getTipo());
                        nodoCons.add(nodoPar);
                    }
                }
            }		      
            for (int i=0; i<mClass.getMethods().size(); i++) 
            {
                Operation m=mClass.getMethod(i);
                DefaultMutableTreeNode nodoMethod=new DefaultMutableTreeNode(m.getId());
                methods.add(nodoMethod);
                if (mLevels==3) {
                    for (int j=0; j<m.getParametros().size(); j++) {
                        TParameter p=m.getParametro(j);
                        DefaultMutableTreeNode nodoPar=new DefaultMutableTreeNode(p.getTipo());
                        nodoMethod.add(nodoPar);
                    }
                }
            }
            for (int i=0; i<mClass.getStates().size(); i++) 
            {
                State st=mClass.getState(i);
                DefaultMutableTreeNode nodoState=new DefaultMutableTreeNode(st.getName());
                states.add(nodoState);
            }
        }
        catch (Exception ex) 
        {
            this.mParentWindow.logError("Error: " + ex.toString());
        }
    }

    /**
     * @param b
     */
    public void showStatesNode(boolean action) {
        this.mShowStatesNode=action;
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

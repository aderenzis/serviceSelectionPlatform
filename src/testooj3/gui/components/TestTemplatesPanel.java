package testooj3.gui.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class TestTemplatesPanel extends JPanel {

	private JLabel jLabel = null;
	private JScrollPane jScrollPane = null;
	private JTree jtTestTemplates = null;
    private Vector mTS;
	/**
	 * This is the default constructor
	 */
	public TestTemplatesPanel() {
		super();
		initialize();
		DefaultMutableTreeNode empty=new DefaultMutableTreeNode("No templates");
        TreeModel modelo=new DefaultTreeModel(empty);
        this.jtTestTemplates.setModel(modelo);
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private  void initialize() {
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.BOTH;
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 1;
		gridBagConstraints1.ipadx = 143;
		gridBagConstraints1.ipady = -84;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.weighty = 1.0;
		gridBagConstraints1.insets = new Insets(2, 9, 11, 13);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 5, 3, 5);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 108;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 0;
		jLabel = new JLabel();
		this.setLayout(new GridBagLayout());
		this.setSize(182, 211);
		jLabel.setText("Test templates");
		this.add(jLabel, gridBagConstraints);
		this.add(getJScrollPane(), gridBagConstraints1);
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJtTestTemplates());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */    
	private JTree getJtTestTemplates() {
		if (jtTestTemplates == null) {
			jtTestTemplates = new JTree();
		}
		return jtTestTemplates;
	}
    /**
     * @param tts
     * @throws Exception
     */
    public void setTemplates(Vector tts) {
        DefaultMutableTreeNode raiz=new DefaultMutableTreeNode("Test templates");
        TreeModel modelo=new DefaultTreeModel(raiz);
        this.jtTestTemplates.setModel(modelo);
        for (int i=0; i<tts.size(); i++) 
        {
            TestTemplate ts=(TestTemplate) tts.elementAt(i);
            DefaultMutableTreeNode nodoTS=new DefaultMutableTreeNode(ts.getName());
            raiz.add(nodoTS);
            TConstructor cons=ts.getConstructor();
            nodoTS.add(new DefaultMutableTreeNode(cons.getId()));
            for (int j=0; j<ts.getMethods().size(); j++) {
                Operation m=(Operation) ts.getMethods().get(j);
                nodoTS.add(new DefaultMutableTreeNode(m.getId()));
            }
        }    
        mTS=tts;

    }
	public Vector getTemplates() {
		return this.mTS;
	}
  }  //  @jve:decl-index=0:visual-constraint="10,10"

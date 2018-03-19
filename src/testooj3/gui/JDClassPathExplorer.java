package testooj3.gui;

import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipFile;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.persistence.Agente;
/**
 * @author  Administrador
 */
@SuppressWarnings("serial")
public class JDClassPathExplorer extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JButton jButton = null;
	private JTree jtClassPath = null;
	private JLabel jlClassPath = null;  //  @jve:decl-index=0:
	private String mSelectedClass;
	/**
	 * This is the default constructor
	 */
	public JDClassPathExplorer() {
		super();
		initialize();
		load();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Class path explorer");
		this.setSize(345, 368);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jlClassPath = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jlClassPath.setBounds(6, 5, 324, 26);
			jlClassPath.setText("");
			jlClassPath.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 10));
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(jlClassPath, null);
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
			jScrollPane.setBounds(-1, 43, 338, 261);
			jScrollPane.setViewportView(getJtClassPath());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(226, 311, 105, 23);
			jButton.setText("Select");
			jButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					selectClass();
				}
			});
		}
		return jButton;
	}
	/**
	 * 
	 */
	protected void selectClass() {
		TreePath path=this.jtClassPath.getSelectionPath();
		if (path==null) return;
		if (path.getPathCount()==3) 
		{
			DefaultMutableTreeNode nodo=(DefaultMutableTreeNode) path.getLastPathComponent();
			mSelectedClass=nodo.getUserObject().toString();
			mSelectedClass=normaliza(mSelectedClass);
			setVisible(false);
		}
	}

	private String normaliza(String s) 
	{
		s=Auxi.substituteAll(s, "\\", ".");
		s=Auxi.substituteAll(s, "/", ".");
		if (s.startsWith("."))
			s=s.substring(1);
		if (s.toLowerCase().endsWith(".class"))
			s=s.substring(0, s.length()-6);
		return s;
	}

	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */    
	private JTree getJtClassPath() {
		if (jtClassPath == null) {
			jtClassPath = new JTree();
		}
		return jtClassPath;
	}

	private void load() 
	{
		String classPath=Configuration.getInstance().getClassPath();
		DefaultMutableTreeNode raiz=new DefaultMutableTreeNode("Class path");
		TreeModel modelo=new DefaultTreeModel(raiz);
		/*Properties pp=System.getProperties();
		Enumeration ee=pp.propertyNames();
		while (ee.hasMoreElements()) {
			String pn=ee.nextElement().toString();
			System.out.println(pn + "=" + pp.getProperty(pn));
		}
		String cp=pp.getProperty("java.class.path");
		if (!cp.endsWith(";"))
			cp+=";";
		String sbcp=pp.getProperty("sun.boot.class.path");
		cp+=sbcp;*/	    
		StringTokenizer st=new StringTokenizer(classPath, ";");
		while (st.hasMoreTokens()) 
		{
			String entrada=st.nextToken();
			if (entrada.toLowerCase().endsWith(".zip") || entrada.toLowerCase().endsWith(".jar")) 
			{
				loadZIPOrJAR(raiz, entrada);
			} else { // es un directorio
				loadDir(raiz, entrada);
			}
		}
		jlClassPath.setText(classPath);
		this.jtClassPath.setModel(modelo);
	}	

	private void loadZIPOrJAR(DefaultMutableTreeNode nodo, String zipFile) 
	{
		try {
			DefaultMutableTreeNode nodoZip=new DefaultMutableTreeNode(zipFile);
			nodo.add(nodoZip);
			ZipFile file=new ZipFile(zipFile);
			Enumeration entries=file.entries();
			while (entries.hasMoreElements()) 
			{
				String fileName=entries.nextElement().toString();
				if (fileName.toLowerCase().endsWith(".class"))
					nodoZip.add(new DefaultMutableTreeNode(fileName));
			}
			file.close();
		}
		catch (Exception ex) 
		{
			DefaultMutableTreeNode errorNode=new DefaultMutableTreeNode("Error in " + zipFile);
			nodo.add(errorNode);
		}
	}

	private void loadDir(DefaultMutableTreeNode nodo, String dirName) 
	{
		try
		{
			DefaultMutableTreeNode nodoDir=new DefaultMutableTreeNode(dirName);
			nodo.add(nodoDir);
			Agente a=Agente.getAgente();
			Vector v=a.lista(dirName, ".class", true);
			for (int i=0; i<v.size(); i++) 
			{
				nodoDir.add(new DefaultMutableTreeNode(v.elementAt(i).toString()));
			}
		}
		catch (Exception e)
		{
			DefaultMutableTreeNode errorNode=new DefaultMutableTreeNode("Error in " + dirName);
			nodo.add(errorNode);      
		}
	}
	/**
	 * @return
	 */
	public String getSelectedClass() {
		return this.mSelectedClass;
	}	  
}  //  @jve:decl-index=0:visual-constraint="10,10"

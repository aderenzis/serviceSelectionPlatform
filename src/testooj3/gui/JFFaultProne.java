package testooj3.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import testooj3.auxiliares.Auxi;
import testooj3.auxiliares.ClassPathFunctions;
import testooj3.domain.Interface;
import testooj3.domain.TestoojClassLoader;
import testooj3.domain.measurer.Measurer;

/**
 * @author  andres
 */
public class JFFaultProne extends JFrame implements IMeasurerWindow {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private String classPath;

	private JLabel jLabel = null;

	private JList jlClassPath = null;
	private DefaultListModel cpModel=null;

	private JScrollPane jScrollPane = null;

	private JScrollPane jScrollPane1 = null;

	private JTable jtClasses = null;
	private DefaultTableModel classesModel=new DefaultTableModel();

	private JProgressBar jpbClasses = null;

	/**
	 * This is the default constructor
	 */
	public JFFaultProne(String classPath) {
		super();
		initialize();
		this.classPath=classPath;
		cpModel=new DefaultListModel();
		classesModel=new DefaultTableModel();
		String[] cabs=Measurer.getHeaders();
		classesModel.setColumnIdentifiers(cabs);
		this.jlClassPath.setModel(cpModel);
		this.jtClasses.setModel(classesModel);
		loadClassPath();
	}

	private void loadClassPath() {
		Vector<String> scp = ClassPathFunctions.getSplittedClassPath(classPath);
		for (int i=0; i<scp.size(); i++) {
			String linea=scp.get(i);
			this.cpModel.addElement(linea);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(1040, 488);
		this.setContentPane(getJContentPane());
		this.setTitle("Fault-prone classes finder");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.ipadx = 0;
			gridBagConstraints3.ipady = 3;
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridwidth = 2;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridheight = 1;
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.ipadx = 0;
			gridBagConstraints2.ipady = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.insets = new Insets(0, 5, 1, 11);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridheight = 2;
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.ipadx = 0;
			gridBagConstraints1.ipady = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.insets = new Insets(1, 7, 5, 5);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(15, 8, 1, 20);
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 0;
			gridBagConstraints.ipady = 7;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 0;
			jLabel = new JLabel();
			jLabel.setText("Class path");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jLabel, gridBagConstraints);
			jContentPane.add(getJScrollPane(), gridBagConstraints1);
			jContentPane.add(getJScrollPane1(), gridBagConstraints2);
			jContentPane.add(getJpbClasses(), gridBagConstraints3);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jlClassPath	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJlClassPath() {
		if (jlClassPath == null) {
			jlClassPath = new JList();
			jlClassPath.setFont(new Font("Dialog", Font.BOLD, 9));
			jlClassPath.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount()==2) {
					    showClasses();
					}
				}
			});
		}
		return jlClassPath;
	}

	protected void showClasses() {
		if (this.jlClassPath.getSelectedIndex()==-1)
			return;
		while (this.classesModel.getRowCount()>0)
			this.classesModel.removeRow(0);
		String classPathLine=this.jlClassPath.getSelectedValue().toString();
		Vector<String> classes=ClassPathFunctions.getClassesIn(classPathLine);
		this.jpbClasses.setMaximum(classes.size());
		Measurer m=new Measurer(this.classPath, classes, this);
		Thread t=new Thread(m);
		t.start();
	}
	
	public void addRow(int index, Vector row) {
		this.classesModel.addRow(row);
		this.jpbClasses.setValue(index+1);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJlClassPath());
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
			jScrollPane1.setViewportView(getJtClasses());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jtClasses	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJtClasses() {
		if (jtClasses == null) {
			jtClasses = new JTable();
		}
		return jtClasses;
	}

	/**
	 * This method initializes jpbClasses	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJpbClasses() {
		if (jpbClasses == null) {
			jpbClasses = new JProgressBar();
		}
		return jpbClasses;
	}



}  //  @jve:decl-index=0:visual-constraint="10,10"

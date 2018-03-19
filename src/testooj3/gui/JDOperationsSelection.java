package testooj3.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.states.State;
import testooj3.gui.components.IMainWindow;
import testooj3.persistence.Agente;

/**
 * @author  andres
 */
public class JDOperationsSelection extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private IMainWindow mainWindow;

	private JScrollPane jScrollPane = null;

	private JList jlOperations = null;

	private DefaultListModel modeloOperations;
	private DefaultListModel modeloUnselected;

	private JButton jbRemove = null;

	private JLabel jlLeft = null;

	private Interface mClass;

	private JButton jbSave = null;

	private JButton jbOpen = null;

	private JScrollPane jScrollPane1 = null;

	private JList jlUnselected = null;

	private Vector<String> unselectedOperations;

	/**
	 * @param window 
	 * @param c 
	 * @param owner
	 */
	public JDOperationsSelection(IMainWindow window, Interface c) {
		super();
		initialize();
		this.mainWindow=window;
		modeloOperations=new DefaultListModel();
		this.jlOperations.setModel(modeloOperations);
		modeloUnselected=new DefaultListModel();
		unselectedOperations=new Vector<String>();
		this.jlUnselected.setModel(modeloUnselected);
		mClass=c;
		load();
	}

	private void load() {
		int total=0;
		this.modeloOperations.removeAllElements();
		this.modeloUnselected.removeAllElements();
		if (mClass.getConstructors()!=null)
			for (int i=0; i<mClass.getConstructors().size(); i++) {
				TConstructor c=mClass.getConstructor(i);
				modeloOperations.addElement(c.getId());
				total++;
			}
		if (mClass.getMethods()!=null)
			for (int i=0; i<mClass.getMethods().size(); i++) {
				Operation m=mClass.getMethod(i);
				modeloOperations.addElement(m.getId());
				total++;
			}		
		for (int i=0; i<unselectedOperations.size(); i++)
			this.modeloUnselected.addElement(unselectedOperations.get(i));
		this.jlLeft.setText(total + " operations");
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(649, 455);
		this.setTitle("Class with more than 50 operations");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridwidth = 4;
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.ipadx = 2;
			gridBagConstraints5.ipady = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.weighty = 1.0;
			gridBagConstraints5.insets = new Insets(2, 5, 2, 5);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints4.gridy = 2;
			gridBagConstraints4.ipadx = 1;
			gridBagConstraints4.ipady = 1;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridx = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.ipadx = 1;
			gridBagConstraints3.ipady = 1;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.ipadx = 2;
			gridBagConstraints2.ipady = 2;
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.gridx = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints1.gridy = 2;
			gridBagConstraints1.ipadx = 1;
			gridBagConstraints1.ipady = 1;
			gridBagConstraints1.anchor = GridBagConstraints.EAST;
			gridBagConstraints1.gridx = 3;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridwidth = 4;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 2;
			gridBagConstraints.ipady = 2;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(2, 5, 2, 5);
			jlLeft = new JLabel();
			jlLeft.setText("JLabel");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJScrollPane(), gridBagConstraints);
			jContentPane.add(getJbRemove(), gridBagConstraints1);
			jContentPane.add(jlLeft, gridBagConstraints2);
			jContentPane.add(getJbSave(), gridBagConstraints3);
			jContentPane.add(getJbOpen(), gridBagConstraints4);
			jContentPane.add(getJScrollPane1(), gridBagConstraints5);
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
			jScrollPane.setViewportView(getJlOperations());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jlOperations	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJlOperations() {
		if (jlOperations == null) {
			jlOperations = new JList();
		}
		return jlOperations;
	}

	/**
	 * This method initializes jbRemove	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbRemove() {
		if (jbRemove == null) {
			jbRemove = new JButton();
			jbRemove.setText("Remove");
			jbRemove.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					removeSelected();
				}
			});
		}
		return jbRemove;
	}

	protected void removeSelected() {
		Object[] selected=this.jlOperations.getSelectedValues();
		for (int i=0; i<selected.length; i++) {
			this.mClass.removeOperation(selected[i].toString());
			this.unselectedOperations.add(selected[i].toString());
		}
		load();
	}

	/**
	 * This method initializes jbSave	
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

	protected void save() {
		try {
	        Agente.mkdir(Configuration.getInstance().getConstraintsPath(), this.mClass.getName());  
	        String fileName=Configuration.getInstance().getConstraintsPath() + this.mClass.getName() + "\\" + "selected" + ".operations";
	        FileOutputStream f=new FileOutputStream(fileName);
	        Properties pp=new Properties(); 
	        for (int i=0; i<this.modeloOperations.getSize(); i++) 
	        {
	            String id=this.modeloOperations.get(i).toString();
	            pp.setProperty(id, id);      
	        }
	        pp.store(f, "Selected operations");
	        f.close();
	        this.mainWindow.log("Operations saved");
		}
		catch (Exception ex) {
			this.mainWindow.logError(ex.toString());
		}
	}

	/**
	 * This method initializes jbOpen	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbOpen() {
		if (jbOpen == null) {
			jbOpen = new JButton();
			jbOpen.setText("Open");
			jbOpen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					open();
				}
			});
		}
		return jbOpen;
	}

	protected void open() {
		try {
	        String fileName=Configuration.getInstance().getConstraintsPath() +this.mClass.getName()+"\\"
	        + "selected" + ".operations";
	        Properties pp = new Properties();  
	        FileInputStream f=new FileInputStream(fileName);
	        pp.load(f);
	        Enumeration ppn=pp.propertyNames();
	        Vector selectableOperations=new Vector();
	        while (ppn.hasMoreElements()) 
	        {
	            String pn=ppn.nextElement().toString();
	            selectableOperations.add(pn);
	        }
	        f.close();	
	        if (mClass.getConstructors()!=null) {
		        for (int i=mClass.getConstructors().size()-1; i>=0; i--) {
		        	String operation=mClass.getConstructor(i).getId();
		        	if (!selectableOperations.contains(operation)) {
		        		unselectedOperations.add(operation);
		        		mClass.removeOperation(operation);
		        	}
		        }
	        }
	        if (mClass.getMethods()!=null) {
	        	for (int i=mClass.getMethods().size()-1; i>=0; i--) {
		        	String operation=mClass.getMethod(i).getId();
		        	if (!selectableOperations.contains(operation)) {
		        		unselectedOperations.add(operation);
		        		mClass.removeOperation(operation);
		        	}
		        }
	        }
	        load();
	        this.mainWindow.log("Operations loaded");
		}
		catch (Exception ex) {
			this.mainWindow.logError(ex.toString());
		}
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJlUnselected());
			jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount()==2)
						addOperation();
				}
			});
		}
		return jScrollPane1;
	}

	protected void addOperation() {
		if (this.jlUnselected.getSelectedIndex()==-1) return;
		String operation=this.jlUnselected.getSelectedValue().toString();
		unselectedOperations.remove(operation);
	}

	/**
	 * This method initializes jlUnselected	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJlUnselected() {
		if (jlUnselected == null) {
			jlUnselected = new JList();
		}
		return jlUnselected;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

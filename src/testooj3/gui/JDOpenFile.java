package testooj3.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import testooj3.domain.Configuration;
import testooj3.domain.states.State;
import testooj3.gui.components.IMainWindow;
import testooj3.persistence.Agente;
/**
 * @author  Administrador
 */
public class JDOpenFile extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JList jlFiles = null;
    private String mSelectedFile=null;
    private String mExtension;
    private String mPath;
	private DefaultListModel filesModel = null;   //  @jve:decl-index=0:
    private IMainWindow mParentWindow;
	private JLabel jlSessionId = null;
	private JLabel jlCUT = null;
	private JLabel jlSessionType = null;
	private JLabel jlNumberOfTC = null;
	private JButton jbOpenFile = null;
	private JLabel jlDateTime = null;
	private Vector sesiones=new Vector();
	/**
	 * This is the default constructor
	 */
	public JDOpenFile(IMainWindow v) {
		super();
		initialize();
		mParentWindow=v;
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(408, 357);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints6.gridy = 5;
			gridBagConstraints6.ipadx = 2;
			gridBagConstraints6.ipady = 20;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.gridx = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(4, 6, 11, 6);
			gridBagConstraints5.gridy = 5;
			gridBagConstraints5.ipadx = 1;
			gridBagConstraints5.ipady = 1;
			gridBagConstraints5.anchor = GridBagConstraints.EAST;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 4;
			gridBagConstraints4.ipadx = 2;
			gridBagConstraints4.ipady = 20;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridwidth = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.ipadx = 2;
			gridBagConstraints3.ipady = 20;
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridwidth = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(1, 2, 2, 2);
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.ipadx = 2;
			gridBagConstraints2.ipady = 20;
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridwidth = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.ipadx = 2;
			gridBagConstraints1.ipady = 20;
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridwidth = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 2;
			gridBagConstraints.ipady = 2;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(6, 2, 2, 2);
			jlDateTime = new JLabel();
			jlSessionId = new JLabel();
			jlCUT = new JLabel();
			jlSessionType = new JLabel();
			jlNumberOfTC = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jlSessionId.setText("");
			jlCUT.setText("");
			jlSessionType.setText("");
			jlNumberOfTC.setText("");
			jlDateTime.setText("");
			jContentPane.add(getJScrollPane(), gridBagConstraints);
			jContentPane.add(jlSessionId, gridBagConstraints1);
			jContentPane.add(jlCUT, gridBagConstraints2);
			jContentPane.add(jlSessionType, gridBagConstraints3);
			jContentPane.add(jlNumberOfTC, gridBagConstraints4);
			jContentPane.add(getJbOpenFile(), gridBagConstraints5);
			jContentPane.add(jlDateTime, gridBagConstraints6);
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
			jScrollPane.setViewportView(getJlFiles());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */    
	private JList getJlFiles() {
		if (jlFiles == null) {
			jlFiles = new JList();
			jlFiles.setModel(getFilesModel());
			jlFiles.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseClicked(java.awt.event.MouseEvent e) {  
				    openFile();
				}
			});
		}
		return jlFiles;
	}
    /**
     * 
     */
    protected void openFile() {
        int index=jlFiles.getSelectedIndex();
        if (index==-1) return;
        String selectedFile=jlFiles.getSelectedValue().toString();
        try {
            FileInputStream f=new FileInputStream(mPath+selectedFile);
            Properties pp = new Properties();
            pp.load(f);
            jlCUT.setText("Class under test: " + pp.getProperty("CUT"));
            jlSessionId.setText("Session id: " + pp.getProperty("SessionId"));
            jlSessionType.setText("Session type: " + pp.getProperty("SessionType"));
            jlNumberOfTC.setText("Number of test cases: " + pp.getProperty("NumberOfTestCases"));
            String sDateTime=pp.getProperty("DateTime");
            long lDateTime=Long.parseLong(sDateTime);
            java.util.Date dateTime=new java.util.Date(lDateTime);
            String dt=dateTime.getDate() + "-" + (dateTime.getMonth()+1) + "-" +
            	(dateTime.getYear()+1900) + " " + dateTime.getHours() + ":" + dateTime.getMinutes();
            jlDateTime.setText("Date: " + dt);
            f.close();
        } catch (Exception e) {
            mParentWindow.logError(e.toString());
        }
    }
    /**
     * @param tempPath
     */
    public void setPath(String path) {
        if (!path.endsWith("\\")) 
            path+="\\";
        this.mPath=path;
    }
    /**
     * @param string
     */
    public void setFileType(String extension) {
        this.mExtension=extension;
    }
    /**
     * 
     */
    public void load() {
        try {
            sesiones = Agente.getAgente().lista(this.mPath, this.mExtension, false);
            for (int i=0; i<sesiones.size(); i++) {
                filesModel.addElement(sesiones.get(i).toString());
            }
        } catch (Exception e) {
            mParentWindow.logError(e.toString());
        }
    }
	/**
	 * This method initializes filesModel	
	 * 	
	 * @return javax.swing.DefaultListModel	
	 */    
	private DefaultListModel getFilesModel() {
		if (filesModel == null) {
			filesModel = new DefaultListModel();
		}
		return filesModel;
	}
    public String getSelectedFile() {
        return mSelectedFile;
    }
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbOpenFile() {
		if (jbOpenFile == null) {
			jbOpenFile = new JButton();
			jbOpenFile.setText("Open file");
			jbOpenFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					selectFile();
				}
			});
		}
		return jbOpenFile;
	}
    /**
     * 
     */
    protected void selectFile() {
        int index=this.jlFiles.getSelectedIndex();
        if (index==-1) return;
        this.mSelectedFile=jlFiles.getSelectedValue().toString();
        dispose();
    }
    
    public Vector getSessions() {
        return this.sesiones;
    }
    }  //  @jve:decl-index=0:visual-constraint="10,10"

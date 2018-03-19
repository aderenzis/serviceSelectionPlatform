package me.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import testooj3.auxiliares.Auxi;
import testooj3.persistence.Filtro;

/**
 * @author  andres
 */
public class JDOrganizeFolders extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton jbVersionsDirectory = null;

	private JTextField jtfVersionsDirectory = null;

	private JButton jbOrganizeFolders = null;

	private JLabel jLabel1 = null;

	private JTextField jtfCUTName = null;

	private JDTestCasesExecutor parentWindow;

	/**
	 * @param owner
	 */
	public JDOrganizeFolders(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(686, 200);
		this.setTitle("CUT versions arrangement");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(13, 75, 204, 25));
			jLabel1.setText("Full CUT name (including package)");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJbVersionsDirectory(), null);
			jContentPane.add(getJtfVersionsDirectory(), null);
			jContentPane.add(getJbOrganizeFolders(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJtfCUTName(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jbVersionsDirectory	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbVersionsDirectory() {
		if (jbVersionsDirectory == null) {
			jbVersionsDirectory = new JButton();
			jbVersionsDirectory.setBounds(new Rectangle(8, 15, 142, 24));
			jbVersionsDirectory.setText("Versions directory");
			jbVersionsDirectory.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					selectVersionsDirectory();
				}
			});
		}
		return jbVersionsDirectory;
	}
	protected void selectVersionsDirectory() {
        JFileChooser fc=new JFileChooser();	        
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
          String versionsDirectory=fc.getSelectedFile().getAbsolutePath();
          this.jtfVersionsDirectory.setText(versionsDirectory);
        }
	}
	
	/**
	 * This method initializes jtfVersionsDirectory	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfVersionsDirectory() {
		if (jtfVersionsDirectory == null) {
			jtfVersionsDirectory = new JTextField();
			jtfVersionsDirectory.setBounds(new Rectangle(158, 17, 360, 22));
		}
		return jtfVersionsDirectory;
	}

	/**
	 * This method initializes jbOrganizeFolders	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbOrganizeFolders() {
		if (jbOrganizeFolders == null) {
			jbOrganizeFolders = new JButton();
			jbOrganizeFolders.setBounds(new Rectangle(525, 16, 135, 22));
			jbOrganizeFolders.setText("Organize folders");
			jbOrganizeFolders.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String className=jtfCUTName.getText();
					if (className.endsWith(".java"))
						className=className.substring(0, className.length()-5);
					else if (className.endsWith(".class"))
						className=className.substring(0, className.length()-6);
					jtfCUTName.setText(className);
					arrangeSourceCode(".class");
					jtfCUTName.setText(className);
					arrangeSourceCode(".java");
				}
			});
		}
		return jbOrganizeFolders;
	}
	
	private void arrangeSourceCode(String ext)  {
		try {
			String versionsDirectory=this.jtfVersionsDirectory.getText();
			String fullCUTName=this.jtfCUTName.getText();
			String originalFullCUTName=fullCUTName;
			if (!fullCUTName.endsWith(ext)) {
				fullCUTName+=ext;
				this.jtfCUTName.setText(fullCUTName);
			}
			String searchedFile;
			if (fullCUTName.indexOf(".")!=fullCUTName.lastIndexOf(".")) {
				String auxi=fullCUTName.substring(0, fullCUTName.length()-ext.length());
				searchedFile=auxi.substring(auxi.lastIndexOf(".")+1);
			} else {
				searchedFile=fullCUTName.substring(0, fullCUTName.lastIndexOf("."));
			}
			searchedFile+=ext;
			Filtro f=new Filtro(versionsDirectory, searchedFile);
			f.setStrictEnd(true);
	        Vector ficheros=new Vector(), result=new Vector();
	        f.lista(ficheros, "", true);
	        fullCUTName=Auxi.substituteAll(fullCUTName, ".", "\\");
	        fullCUTName=fullCUTName.substring(0, fullCUTName.length()-ext.length())+ext;
	        for (int i=0; i<ficheros.size(); i++) {
	        	String fichero=ficheros.get(i).toString();
	        	if (fichero.endsWith(fullCUTName)) {
	        		fichero=fichero.substring(0, fichero.length()-fullCUTName.length());
	        		if (fichero.startsWith("\\"))
	        			fichero=fichero.substring(1);
	        		result.add(fichero);
	        	}
	        }
	        StringTokenizer st=new StringTokenizer(originalFullCUTName, ".");
	        Vector directorios=new Vector();
	        while (st.hasMoreTokens()) {
	            String token=st.nextToken();
	            directorios.add(token);
	        }        
	        if (directorios.size()>0)
	            directorios.remove(directorios.size()-1);
	        for (int i=0; i<ficheros.size(); i++) {
	        	String fichero=versionsDirectory + ficheros.get(i).toString();
	        	buildSubfolders(fichero, directorios, searchedFile);
	        }
		}
		catch (Exception ex) {
			this.parentWindow.logError(ex.toString());
		}
    }
	
    private String buildSubfolders(String fullPath, Vector subfolders, String cutFileName) throws IOException {
    	fullPath=fullPath.substring(0, fullPath.lastIndexOf("\\")) + "\\";
        FileInputStream fi=new FileInputStream(fullPath+cutFileName);
        int length=fi.available();
        byte[] contenido=new byte[length];
        fi.read(contenido);
        fi.close();
        for (int i=0; i<subfolders.size(); i++) {
            fullPath+=subfolders.get(i).toString()+"\\";
            new File(fullPath).mkdir();
        }
        cutFileName=fullPath+cutFileName;
        FileOutputStream fo=new FileOutputStream(cutFileName);
        fo.write(contenido);
        fo.close();
        return cutFileName;
    }

	/**
	 * This method initializes jtfCUTName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfCUTName() {
		if (jtfCUTName == null) {
			jtfCUTName = new JTextField();
			jtfCUTName.setBounds(new Rectangle(222, 76, 348, 23));
		}
		return jtfCUTName;
	}

	public void setCutName(String text) {
		this.jtfCUTName.setText(text);
	}

	public void setVersionsDirectory(String text) {
		this.jtfVersionsDirectory.setText(text);
	}

	public void setParentWindow(JDTestCasesExecutor pw) {
		this.parentWindow=pw;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

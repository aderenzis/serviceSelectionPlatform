package testooj3.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.gui.components.ILogWindow;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
@SuppressWarnings("serial")
public class JDSetup extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private JButton jbWorkingPath = null;
	private JTextField jtfWorkingPath = null;
	private JButton jbSaveAndExit = null;
	private JButton jbExitWithoutSaving = null;
    private ILogWindow mParentWindow;
	private JButton jbJUnitJarLocation = null;
	private JTextField jtfJUnitLocation = null;
	private JButton jbMuJavaRootDirectory = null;
	private JTextField jtfMuJavaRoot = null;
	private JButton jbSplitJUnitFilesEvery = null;
	private JTextField jtfSplitJUnitFilesEvery = null;
	private JLabel jLabel = null;
	private JButton jbSplitMujavaFilesEvery = null;
	private JTextField jtfSplitMujavaFilesEvery = null;
	private JLabel jLabel1 = null;
	private JButton jbAdditionalTestFile = null;
	private JTextField jtfAdditionalTestFilesPath = null;
	private JButton jbClassPath = null;
	private JTextField jtfClassPath = null;
	private JPanel jPanel = null;
	private JPanel jPanelBottom = null;
	private JPanel jPanelBytes = null;
	private JLabel jLabel2 = null;
	/**
	 * This is the default constructor
	 */
	public JDSetup(ILogWindow v) {
		super();
		initialize();
		this.mParentWindow=v;
		Configuration cfg=Configuration.getInstance();
		this.jtfWorkingPath.setText(cfg.getWorkingPath());
		this.jtfJUnitLocation.setText(cfg.getJUnitLocation());
		this.jtfMuJavaRoot.setText(cfg.getMujavaRoot());
		this.jtfAdditionalTestFilesPath.setText(cfg.getAdditionalTestFilesPaths());
		this.jtfClassPath.setText(cfg.getClassPath());
		this.jtfSplitMujavaFilesEvery.setText(""+cfg.getSplitMujavaFilesEvery());
		this.jtfSplitJUnitFilesEvery.setText(""+cfg.getSplitJUnitEvery());		
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Testooj3 setup");
		this.setSize(586, 342);
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				exitWithoutSaving();
			}
		});
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.ipadx = 0;
			gridBagConstraints12.gridwidth = 2;
			gridBagConstraints12.gridy = 5;
			jLabel2 = new JLabel();
			jLabel2.setText(" If not sure, only save and exit...");
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 12));
			jLabel2.setForeground(new Color(145, 0, 0));
			this.jLabel2.setVisible(false);
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.insets = new Insets(5, 5, 5, 10);
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.gridy = 4;
			gridBagConstraints21.ipadx = 1;
			gridBagConstraints21.ipady = 1;
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints21.gridwidth = 2;
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints20.gridy = 5;
			gridBagConstraints20.ipadx = 7;
			gridBagConstraints20.ipady = 6;
			gridBagConstraints20.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints20.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints20.gridx = 1;
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.insets = new Insets(5, 5, 5, 10);
			gridBagConstraints19.gridx = 0;
			gridBagConstraints19.gridy = 3;
			gridBagConstraints19.ipadx = 1;
			gridBagConstraints19.ipady = 1;
			gridBagConstraints19.fill = GridBagConstraints.BOTH;
			gridBagConstraints19.gridwidth = 2;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints18.gridx = 1;
			gridBagConstraints18.gridy = 2;
			gridBagConstraints18.ipadx = 1;
			gridBagConstraints18.ipady = 1;
			gridBagConstraints18.weightx = 1.0;
			gridBagConstraints18.anchor = GridBagConstraints.NORTH;
			gridBagConstraints18.insets = new Insets(2, 5, 2, 10);
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.insets = new Insets(2, 5, 2, 10);
			gridBagConstraints17.gridy = 2;
			gridBagConstraints17.ipadx = 1;
			gridBagConstraints17.ipady = 1;
			gridBagConstraints17.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints17.anchor = GridBagConstraints.NORTH;
			gridBagConstraints17.gridx = 0;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints16.gridx = 1;
			gridBagConstraints16.gridy = 1;
			gridBagConstraints16.ipadx = 1;
			gridBagConstraints16.ipady = 1;
			gridBagConstraints16.weightx = 1.0;
			gridBagConstraints16.anchor = GridBagConstraints.NORTH;
			gridBagConstraints16.insets = new Insets(2, 5, 2, 10);
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.insets = new Insets(2, 5, 2, 10);
			gridBagConstraints15.gridy = 1;
			gridBagConstraints15.ipadx = 1;
			gridBagConstraints15.ipady = 1;
			gridBagConstraints15.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints15.anchor = GridBagConstraints.NORTH;
			gridBagConstraints15.gridx = 0;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints14.gridx = 1;
			gridBagConstraints14.gridy = 0;
			gridBagConstraints14.ipadx = 1;
			gridBagConstraints14.ipady = 1;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.anchor = GridBagConstraints.NORTH;
			gridBagConstraints14.insets = new Insets(2, 5, 2, 10);
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.insets = new Insets(2, 5, 2, 10);
			gridBagConstraints13.gridy = 0;
			gridBagConstraints13.ipadx = 1;
			gridBagConstraints13.ipady = 1;
			gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints13.anchor = GridBagConstraints.NORTH;
			gridBagConstraints13.gridx = 0;
			jLabel1 = new JLabel();
			jLabel = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jLabel.setText("bytes");
			jLabel1.setText("bytes");
			jContentPane.add(getJbWorkingPath(), gridBagConstraints13);
			jContentPane.add(getJtfWorkingPath(), gridBagConstraints14);
			jContentPane.add(getJbJUnitJarLocation(), gridBagConstraints15);
			jContentPane.add(getJtfJUnitLocation(), gridBagConstraints16);
			jContentPane.add(getJbMuJavaRootDirectory(), gridBagConstraints17);
			jContentPane.add(getJtfMuJavaRoot(), gridBagConstraints18);
			jContentPane.add(getJPanel(), gridBagConstraints19);
			jContentPane.add(getJPanelBottom(), gridBagConstraints20);
			jContentPane.add(getJPanelBytes(), gridBagConstraints21);
			jContentPane.add(jLabel2, gridBagConstraints12);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbWorkingPath() {
		if (jbWorkingPath == null) {
			jbWorkingPath = new JButton();
			jbWorkingPath.setText("Working path");
			jbWorkingPath.setActionCommand("Results");
			jbWorkingPath.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					selectWorkingPath();
				}
			});
		}
		return jbWorkingPath;
	}
	/**
	 * @throws IOException
     * 
     */
    protected void selectWorkingPath() {
        JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	String result=fc.getSelectedFile().getAbsolutePath();
        	result=Auxi.substituteAll(result, "\\", "/");
          jtfWorkingPath.setText(result);
        }
    }
    /**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJtfWorkingPath() {
		if (jtfWorkingPath == null) {
			jtfWorkingPath = new JTextField();
		}
		return jtfWorkingPath;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbSaveAndExit() {
		if (jbSaveAndExit == null) {
			jbSaveAndExit = new JButton();
			jbSaveAndExit.setText("Save and exit");
			jbSaveAndExit.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					saveAndExit();
				}
			});
		}
		return jbSaveAndExit;
	}
	/**
     * 
     */
	protected void saveAndExit() {
	    String wp=jtfWorkingPath.getText();
	    if (!wp.endsWith("/")) wp+="/";
        try {           
    	    new File(wp + "results").mkdirs();
    	    new File(wp + "serializedObjects").mkdirs();
    	    new File(wp + "constraints").mkdirs();
    	    new File(wp + "templates").mkdirs();
    	    new File(wp + "temp").mkdirs();
    	    new File(wp + "creators").mkdirs();
    	    new File(wp + "required").mkdirs();
    	    Configuration.getInstance().setWorkingPath(wp);
    	    Configuration.getInstance().setJUnitLocation(this.jtfJUnitLocation.getText());
    	    Configuration.getInstance().setMujavaRoot(this.jtfMuJavaRoot.getText());
    	    Configuration.getInstance().setAdditionalTestFilesPaths(this.jtfAdditionalTestFilesPath.getText());
    	    Configuration.getInstance().setSplitJUnitEvery(Integer.parseInt(this.jtfSplitJUnitFilesEvery.getText()));
    	    Configuration.getInstance().setSplitMujavaFilesEvery(Integer.parseInt(this.jtfSplitMujavaFilesEvery.getText()));
    	    Configuration.getInstance().setClassPath(this.jtfClassPath.getText());
            Configuration.getInstance().save();   	
            
    	    this.mParentWindow.log("Options saved at " + Configuration.getCanonicalPath() +"/testooj3setup.txt");
        } catch (IOException e) {
            this.mParentWindow.logError("Error: " + e.toString());
        } catch (Exception e) {
            this.mParentWindow.logError("Error: " + e.toString());
        }
        dispose();
    }
    /**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbExitWithoutSaving() {
		if (jbExitWithoutSaving == null) {
			jbExitWithoutSaving = new JButton();
			jbExitWithoutSaving.setText("Exit without saving");
			jbExitWithoutSaving.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					exitWithoutSaving();
				}
			});
		}
		return jbExitWithoutSaving;
	}
    /**
     * 
     */
    protected void exitWithoutSaving() {
    	File f=new File ("testooj3setup.txt");
		if (!f.exists()){
			this.mParentWindow.logError("You need a setup file. Specify the required paths and save.");
			JOptionPane.showMessageDialog(this, "You need a setup file. Specify the required paths and save.", "Setup required", JOptionPane.ERROR_MESSAGE);
		}else{
			dispose();
		}
        
    }
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbJUnitJarLocation() {
		if (jbJUnitJarLocation == null) {
			jbJUnitJarLocation = new JButton();
			jbJUnitJarLocation.setText("JUnit location");
			jbJUnitJarLocation.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					junitLocation();
				}
			});
		}
		return jbJUnitJarLocation;
	}
	/**
     * 
     */
    protected void junitLocation() {
        JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	String result=fc.getSelectedFile().getAbsolutePath();
        	result=Auxi.substituteAll(result, "\\", "/");
          jtfJUnitLocation.setText(result);
        }
    }
    /**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJtfJUnitLocation() {
		if (jtfJUnitLocation == null) {
			jtfJUnitLocation = new JTextField();
		}
		return jtfJUnitLocation;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbMuJavaRootDirectory() {
		if (jbMuJavaRootDirectory == null) {
			jbMuJavaRootDirectory = new JButton();
			jbMuJavaRootDirectory.setText("MuJava root");
			jbMuJavaRootDirectory.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					muJavaLocation();
				}
			});
		}
		return jbMuJavaRootDirectory;
	}
	/**
     * 
     */
    protected void muJavaLocation() {
        JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	String result=fc.getSelectedFile().getAbsolutePath();
        	result=Auxi.substituteAll(result, "\\", "/");
          jtfMuJavaRoot.setText(result);
        }
    }
    /**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJtfMuJavaRoot() {
		if (jtfMuJavaRoot == null) {
			jtfMuJavaRoot = new JTextField();
		}
		return jtfMuJavaRoot;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbSplitJUnitFilesEvery() {
		if (jbSplitJUnitFilesEvery == null) {
			jbSplitJUnitFilesEvery = new JButton();
			jbSplitJUnitFilesEvery.setText("Split JUnit files every");
		}
		return jbSplitJUnitFilesEvery;
	}
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJtfSplitJUnitFilesEvery() {
		if (jtfSplitJUnitFilesEvery == null) {
			jtfSplitJUnitFilesEvery = new JTextField();
			jtfSplitJUnitFilesEvery.setText("600000");
			jtfSplitJUnitFilesEvery.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfSplitJUnitFilesEvery;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbSplitMujavaFilesEvery() {
		if (jbSplitMujavaFilesEvery == null) {
			jbSplitMujavaFilesEvery = new JButton();
			jbSplitMujavaFilesEvery.setText("Split Mujava files every");
		}
		return jbSplitMujavaFilesEvery;
	}
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJtfSplitMujavaFilesEvery() {
		if (jtfSplitMujavaFilesEvery == null) {
			jtfSplitMujavaFilesEvery = new JTextField();
			jtfSplitMujavaFilesEvery.setText("200000");
			jtfSplitMujavaFilesEvery.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfSplitMujavaFilesEvery;
	}
	/**
	 * This method initializes jbAdditionalTestFile	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAdditionalTestFile() {
		if (jbAdditionalTestFile == null) {
			jbAdditionalTestFile = new JButton();
			jbAdditionalTestFile.setText("Additional test files");
			jbAdditionalTestFile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					additionalTestFilesPath();
				}
			});
		}
		return jbAdditionalTestFile;
	}
	protected void additionalTestFilesPath() {
		JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	String result=fc.getSelectedFile().getAbsolutePath();
        	result=Auxi.substituteAll(result, "\\", "/");
          this.jtfAdditionalTestFilesPath.setText(result);
        }
	}
	/**
	 * This method initializes jtfAdditionalTestFilesPath	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfAdditionalTestFilesPath() {
		if (jtfAdditionalTestFilesPath == null) {
			jtfAdditionalTestFilesPath = new JTextField();
		}
		return jtfAdditionalTestFilesPath;
	}
	/**
	 * This method initializes jbClassPath	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbClassPath() {
		if (jbClassPath == null) {
			jbClassPath = new JButton();
			jbClassPath.setText("Class path");
			jbClassPath.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					classPath();
				}
			});
		}
		return jbClassPath;
	}
	protected void classPath() {
		JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	String result=fc.getSelectedFile().getAbsolutePath()+";"+this.jtfClassPath.getText();
        	result=Auxi.substituteAll(result, "\\", "/");
       		this.jtfClassPath.setText(result);
        }
	}
	/**
	 * This method initializes jtfClassPath	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfClassPath() {
		if (jtfClassPath == null) {
			jtfClassPath = new JTextField();
		}
		return jtfClassPath;
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.ipadx = 0;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.insets = new Insets(2, 2, 2, 2);
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints10.gridy = 2;
			gridBagConstraints10.ipadx = 2;
			gridBagConstraints10.ipady = 2;
			gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.anchor = GridBagConstraints.WEST;
			gridBagConstraints10.gridx = 0;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.gridx = 1;
			gridBagConstraints9.gridy = 1;
			gridBagConstraints9.ipadx = 0;
			gridBagConstraints9.ipady = 1;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.insets = new Insets(2, 2, 2, 2);
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints8.gridy = 1;
			gridBagConstraints8.ipadx = 2;
			gridBagConstraints8.ipady = 2;
			gridBagConstraints8.anchor = GridBagConstraints.WEST;
			gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.gridx = 0;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBorder(BorderFactory.createTitledBorder(null, "Additional options for test case execution", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel.add(getJbAdditionalTestFile(), gridBagConstraints8);
			jPanel.add(getJtfAdditionalTestFilesPath(), gridBagConstraints9);
			jPanel.add(getJbClassPath(), gridBagConstraints10);
			jPanel.add(getJtfClassPath(), gridBagConstraints11);
		}
		return jPanel;
	}
	/**
	 * This method initializes jPanelBottom	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelBottom() {
		if (jPanelBottom == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.anchor = GridBagConstraints.EAST;
			gridBagConstraints1.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.gridy = 0;
			jPanelBottom = new JPanel();
			jPanelBottom.setLayout(new GridBagLayout());
			jPanelBottom.add(getJbSaveAndExit(), gridBagConstraints);
			jPanelBottom.add(getJbExitWithoutSaving(), gridBagConstraints1);
		}
		return jPanelBottom;
	}
	/**
	 * This method initializes jPanelBytes	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelBytes() {
		if (jPanelBytes == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridy = 1;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.ipadx = 2;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints6.gridx = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints5.gridy = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 2;
			gridBagConstraints4.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridy = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.ipadx = 2;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints2.gridy = 0;
			jPanelBytes = new JPanel();
			jPanelBytes.setLayout(new GridBagLayout());
			jPanelBytes.add(getJbSplitJUnitFilesEvery(), gridBagConstraints2);
			jPanelBytes.add(getJtfSplitJUnitFilesEvery(), gridBagConstraints3);
			jPanelBytes.add(jLabel, gridBagConstraints4);
			jPanelBytes.add(getJbSplitMujavaFilesEvery(), gridBagConstraints5);
			jPanelBytes.add(getJtfSplitMujavaFilesEvery(), gridBagConstraints6);
			jPanelBytes.add(jLabel1, gridBagConstraints7);
		}
		return jPanelBytes;
	}
	public String getClassPath() {
		return  this.jtfClassPath.getText();
	}
	public void setVisibleWarning(boolean b) {
		this.jLabel2.setVisible(b);
	}
   }  //  @jve:decl-index=0:visual-constraint="10,10"

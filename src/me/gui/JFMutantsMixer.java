package me.gui;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import me.domain.Compiler;
import me.domain.mix.DifferentOperators;
import me.domain.mix.LastToFirst;
import me.domain.mix.MixAlgorithm;
import me.domain.mix.RandomMix;
import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.gui.components.ILogWindow;
import testooj3.persistence.Filtro;

/**
 * @author  andres
 */
public class JFMutantsMixer extends JFrame implements ILogWindow, IProgressWindow {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel1 = null;

	private JTextField jtfCUTName = null;

	private JButton jbVersionsDirectory = null;

	private JTextField jtfDirectoryOfSourceVersions = null;

	private JButton jbSearchSourceVersions = null;

	private JButton jbDirectoryOfTargetVersions = null;

	private JTextField jtfDirectoryOfTargetVersions = null;

	private JScrollPane jScrollPane3 = null;

	private JEditorPane jepLog = null;

	private JScrollPane jScrollPane1 = null;

	private JTextArea jtaClassPath = null;

	private Configuration cfg = null;

	private JLabel jLabel2 = null;

	private JScrollPane jScrollPane2 = null;

	private JList jLocationsOfTheCUTVersions = null;

	private JButton jbSearchTargetVersions = null;

	private JLabel jlVersions = null;

	private JButton jbMix = null;

	private JButton jbCompile = null;

	private DefaultListModel versionsModel;

	private JButton jbSetAsOriginal = null;

	private String original;

	private JProgressBar jProgressBar = null;

	private JButton jbCompare = null;

	/**
	 * This is the default constructor
	 */
	public JFMutantsMixer() {
		super();
		initialize();
		this.versionsModel=new DefaultListModel();
		this.jLocationsOfTheCUTVersions.setModel(this.versionsModel);
		Configuration cfg=Configuration.getInstance();
		this.jtaClassPath.setText(cfg.getClassPath());
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(812, 530);
		this.setContentPane(getJContentPane());
		this.setTitle("Mutant mixer");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jlVersions = new JLabel();
			jlVersions.setBounds(new Rectangle(19, 181, 195, 24));
			jlVersions.setText("Versions");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(17, 130, 89, 16));
			jLabel2.setText("CLASSPATH");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 16, 216, 23));
			jLabel1.setText("Full CUT name (including package)");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJtfCUTName(), null);
			jContentPane.add(getJbVersionsDirectory(), null);
			jContentPane.add(getJtfDirectoryOfSourceVersions(), null);
			jContentPane.add(getJbSearchSourceVersions(), null);
			jContentPane.add(getJbDirectoryOfTargetVersions(), null);
			jContentPane.add(getJtfDirectoryOfTargetVersions(), null);
			jContentPane.add(getJScrollPane3(), null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJScrollPane2(), null);
			jContentPane.add(getJbSearchTargetVersions(), null);
			jContentPane.add(jlVersions, null);
			jContentPane.add(getJbMix(), null);
			jContentPane.add(getJbCompile(), null);
			jContentPane.add(getJbSetAsOriginal(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(getJbCompare(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jtfCUTName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfCUTName() {
		if (jtfCUTName == null) {
			jtfCUTName = new JTextField();
			jtfCUTName.setBounds(new Rectangle(235, 17, 320, 23));
			jtfCUTName.setText("paper.Triangulo");
		}
		return jtfCUTName;
	}

	/**
	 * This method initializes jbVersionsDirectory	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbVersionsDirectory() {
		if (jbVersionsDirectory == null) {
			jbVersionsDirectory = new JButton();
			jbVersionsDirectory.setBounds(new Rectangle(15, 54, 196, 26));
			jbVersionsDirectory.setText("Directory of source versions");
		}
		return jbVersionsDirectory;
	}

	/**
	 * This method initializes jtfDirectoryOfSourceVersions	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfDirectoryOfSourceVersions() {
		if (jtfDirectoryOfSourceVersions == null) {
			jtfDirectoryOfSourceVersions = new JTextField();
			jtfDirectoryOfSourceVersions.setBounds(new Rectangle(220, 55, 336, 26));
			String vd=Configuration.getInstance().getMujavaRoot() + "result";
			jtfDirectoryOfSourceVersions.setText(vd);
		}
		return jtfDirectoryOfSourceVersions;
	}

	/**
	 * This method initializes jbSearchSourceVersions	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbSearchSourceVersions() {
		if (jbSearchSourceVersions == null) {
			jbSearchSourceVersions = new JButton();
			jbSearchSourceVersions.setBounds(new Rectangle(567, 55, 75, 24));
			jbSearchSourceVersions.setText("Search");
			jbSearchSourceVersions.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jlVersions.setText("Source versions:");
					searchVersions(jtfDirectoryOfSourceVersions.getText(), ".java");
				}
			});
		}
		return jbSearchSourceVersions;
	}

	protected void searchVersions(String path, String ext) {
		String cutName=getCUTName();
		String thePackage=this.getPackage();
		this.versionsModel.clear();
		if (path==null || path.length()==0)
			return;
		try {
			Vector<String> mutants = searchVersions(path, thePackage, cutName, ext);
			for (int i=0; i<mutants.size(); i++) {
				this.versionsModel.addElement(mutants.get(i));
			}
		} catch (Exception e) {
			logError(e.toString());
		}
	}

	private Vector<String> searchVersions(String versionsDirectory, String thePackage, String cutName, String ext) throws Exception {
		if (!cutName.endsWith(ext)) {
			cutName+=ext;
			this.jtfCUTName.setText(thePackage + "." + cutName);
		}
		Filtro f=new Filtro(versionsDirectory, cutName);
		f.setStrictEnd(true);
        Vector<String> ficheros=new Vector<String>(), result=new Vector<String>();
        f.lista(ficheros, "", true);
        
        if (ficheros.size()==0) {
        	String msg="No versions of " + cutName + " found in " + getFullDirectory(versionsDirectory) + ".\n\r";
			javax.swing.JOptionPane.showMessageDialog(this, msg);
    		return result;
        }
        for (int i=0; i<ficheros.size(); i++) {
        	String fichero=ficheros.get(i).toString();
        	fichero=Auxi.substituteAll(fichero, "\\", "/");
        	thePackage=Auxi.substituteAll(thePackage, ".", "/");
        	if (fichero.endsWith(thePackage + "/" + cutName)) {
        		fichero=fichero.substring(0, fichero.length()-cutName.length()-thePackage.length()-1);
        		if (fichero.startsWith("/"))
        			fichero=getFullDirectory(versionsDirectory) + fichero.substring(1);
        		result.add(fichero);
        	}
        }
        return result;
	}
	
	protected String getFullDirectory(String path) {
		path=Auxi.substituteAll(path, "\\", "/");
		if (!path.endsWith("/"))
			path=path+"/";
		return path;
	}
	
    public void logError(String msg) {
        this.jepLog.setText("<font color='red'>" + msg + "</font>");
        jepLog.select(0, 0);  
    }
    
    public void log(String msg) {
    	this.jepLog.setText("<font color='blue'>" + msg + "</font>");
        jepLog.select(0, 0);  
    }

	/**
	 * This method initializes jbDirectoryOfTargetVersions	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbDirectoryOfTargetVersions() {
		if (jbDirectoryOfTargetVersions == null) {
			jbDirectoryOfTargetVersions = new JButton();
			jbDirectoryOfTargetVersions.setBounds(new Rectangle(14, 91, 197, 26));
			jbDirectoryOfTargetVersions.setText("Directory of target versions");
		}
		return jbDirectoryOfTargetVersions;
	}

	/**
	 * This method initializes jtfDirectoryOfTargetVersions	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfDirectoryOfTargetVersions() {
		if (jtfDirectoryOfTargetVersions == null) {
			jtfDirectoryOfTargetVersions = new JTextField();
			jtfDirectoryOfTargetVersions.setBounds(new Rectangle(219, 90, 335, 25));
			String vd=Configuration.getInstance().getMujavaRoot() + "mix";
			jtfDirectoryOfTargetVersions.setText(vd);
		}
		return jtfDirectoryOfTargetVersions;
	}

	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(12, 388, 777, 94));
			jScrollPane3.setViewportView(getJepLog());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes jepLog	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJepLog() {
		if (jepLog == null) {
			jepLog = new JEditorPane();
			jepLog.setFont(new Font("Dialog", Font.PLAIN, 12));
			jepLog.setContentType("text/html");
		}
		return jepLog;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(113, 128, 443, 53));
			jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPane1.setViewportView(getJtaClassPath());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jtaClassPath	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJtaClassPath() {
		if (jtaClassPath == null) {
			String classPath = getCfg().getMujavaRoot() + "testset\\";
			jtaClassPath = new JTextArea();
			jtaClassPath.setColumns(40);
			jtaClassPath.setText(classPath);
		}
		return jtaClassPath;
	}

	/**
	 * This method initializes cfg	
	 * 	
	 * @return testooj3.domain.Configuration	
	 */
	private Configuration getCfg() {
		if (cfg == null) {
			cfg = Configuration.getInstance();
		}
		return cfg;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(18, 211, 646, 157));
			jScrollPane2.setViewportView(getJLocationsOfTheCUTVersions());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes jLocationsOfTheCUTVersions	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJLocationsOfTheCUTVersions() {
		if (jLocationsOfTheCUTVersions == null) {
			jLocationsOfTheCUTVersions = new JList();
			jLocationsOfTheCUTVersions.setModel(new DefaultListModel());
		}
		return jLocationsOfTheCUTVersions;
	}

	/**
	 * This method initializes jbSearchTargetVersions	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbSearchTargetVersions() {
		if (jbSearchTargetVersions == null) {
			jbSearchTargetVersions = new JButton();
			jbSearchTargetVersions.setBounds(new Rectangle(568, 89, 75, 26));
			jbSearchTargetVersions.setText("Search");
			jbSearchTargetVersions.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jlVersions.setText("Target versions:");
					searchVersions(jtfDirectoryOfTargetVersions.getText(), ".java");
				}
			});
		}
		return jbSearchTargetVersions;
	}

	/**
	 * This method initializes jbMix	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbMix() {
		if (jbMix == null) {
			jbMix = new JButton();
			jbMix.setBounds(new Rectangle(674, 210, 112, 27));
			jbMix.setText("Mix");
			jbMix.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mix();
				}
			});
		}
		return jbMix;
	}
	
	public String getCUTName() {
		String className=this.jtfCUTName.getText();
		if (className.endsWith(".java")) 
			className=className.substring(0, className.length()-5);
		else if (className.endsWith(".class"))
			className=className.substring(0, className.length()-6);
		className=className.substring(className.lastIndexOf(".")+1);
		return className;
	}
	
	public String getPackage() {
		String thePackage=this.jtfCUTName.getText();
		if (thePackage.endsWith(".java")) 
			thePackage=thePackage.substring(0, thePackage.length()-5);
		else if (thePackage.endsWith(".class"))
			thePackage=thePackage.substring(0, thePackage.length()-6);
		thePackage=thePackage.substring(0, thePackage.lastIndexOf("."));
		return thePackage;
	}
	
	protected void mix() {
		if (this.original==null || this.original.length()==0) {
			logError("Select the original version of the source directory");
			return;
		}
		String className=getCUTName();
		Object[] versions=new Object[this.versionsModel.getSize()];
		this.versionsModel.copyInto(versions);
		for (int i=0; i<versions.length; i++)
			versions[i]=Auxi.substituteAll(versions[i].toString(), "\\", "/");
		
		MixAlgorithm algorithm=new RandomMix();
		algorithm.setOriginal(this.original);
		algorithm.setCUTName(className);
		algorithm.setVersions(versions);
		String sourceVersionsFolder=this.getFullDirectory(this.jtfDirectoryOfSourceVersions.getText());
		algorithm.setSourceVersionsFolder(sourceVersionsFolder);
		String targetVersionsFolder=this.getFullDirectory(this.jtfDirectoryOfTargetVersions.getText());
		algorithm.setTargetVersionsFolder(targetVersionsFolder);
		algorithm.setPackage(this.getPackage());
		algorithm.setLogWindow(this);
		algorithm.mix();
	}

	/**
	 * This method initializes jbCompile	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbCompile() {
		if (jbCompile == null) {
			jbCompile = new JButton();
			jbCompile.setBounds(new Rectangle(675, 240, 112, 30));
			jbCompile.setText("Compile");
			jbCompile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					compile();
				}
			});
		}
		return jbCompile;
	}

	protected void compile() {
		String cutName=this.getCUTName();
		if (cutName==null || cutName.length()==0)
			return;
		String thePackage=this.getPackage();
		String versionsDirectory=this.jtfDirectoryOfTargetVersions.getText();
		if (versionsDirectory==null || versionsDirectory.length()==0)
			return;
		versionsDirectory=getFullDirectory(this.jtfDirectoryOfTargetVersions.getText());
		try {
			Vector<String> result=searchVersions(versionsDirectory, thePackage, cutName, ".java");
			String classPath=this.jtaClassPath.getText();
			classPath=Auxi.substituteAll(classPath, "\\", "/");
			Compiler compiler=new Compiler(result, thePackage, cutName, classPath, this, this);
			Thread t=new Thread(compiler);
			t.start();
		} catch (Exception e) {
			logError(e.toString());
		}
	}	
	
	public void setCUTName(String cutName) {
		this.jtfCUTName.setText(cutName);
	}

	public void setDirectoryOfSourceVersions(String path) {
		this.jtfDirectoryOfSourceVersions.setText(path);
	}

	/**
	 * This method initializes jbSetAsOriginal	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbSetAsOriginal() {
		if (jbSetAsOriginal == null) {
			jbSetAsOriginal = new JButton();
			jbSetAsOriginal.setBounds(new Rectangle(404, 184, 151, 21));
			jbSetAsOriginal.setText("Set as original");
			jbSetAsOriginal.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (!jlVersions.getText().equals("Source versions:")) {
						logError("Select the original version of source directory");
						return;
					}
					int index=jLocationsOfTheCUTVersions.getSelectedIndex();
					if (index==-1) return;
					setAsOriginal(index);
				}
			});
		}
		return jbSetAsOriginal;
	}
	
	private void setAsOriginal(int index) {
		String auxi=this.versionsModel.get(index).toString();
		this.original=Auxi.substituteAll(auxi, "\\", "/");
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(19, 369, 645, 15));
		}
		return jProgressBar;
	}
	
	public void setPos(int pos) {
		this.jProgressBar.setValue(pos);
	}

	public void setMaximum(int max) {
		this.jProgressBar.setMaximum(max);
	}

	/**
	 * This method initializes jbCompare	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbCompare() {
		if (jbCompare == null) {
			jbCompare = new JButton();
			jbCompare.setBounds(new Rectangle(675, 280, 112, 35));
			jbCompare.setText("Compare");
			jbCompare.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index=jLocationsOfTheCUTVersions.getSelectedIndex();
					if (index==-1) return;
					if (original==null) {
						logError("Select the original version");
						return;
					}
					compare(index);
				}
			});
		}
		return jbCompare;
	}

	protected void compare(int index) {
		String versionName=this.jLocationsOfTheCUTVersions.getSelectedValue().toString();
		versionName=getFullDirectory(versionName);
		String pn=Auxi.substituteAll(getPackage(), ".", "/");
		versionName+=pn + "/" + getCUTName() + ".java";
		String auxi=getFullDirectory(this.original) + pn + "/" + getCUTName() + ".java";
		log("Comparing " + versionName + " against the original (" + auxi);
		JFCompare j=new JFCompare(versionName, auxi);
		j.setVisible(true);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

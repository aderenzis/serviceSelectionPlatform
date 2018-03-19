package me.gui; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import me.MutantExecutor;
import me.domain.LocalMutantExecutor;
import me.domain.ObjectTable;
import me.domain.ResultsAnalyzer;
import me.domain.TimesTable;
import me.domain.algorithms.Decreasing;
import me.domain.algorithms.Increasing;
import me.domain.algorithms.PriorizationAlgorithm;
import me.domain.algorithms.RandomPA;
import me.domain.timeouts.Timeout;
import meremote.Client;
import testooj3.auxiliares.Auxi;
import testooj3.auxiliares.ClassPathFunctions;
import testooj3.domain.Configuration;
import testooj3.gui.JDSetup;
import testooj3.gui.components.ILogWindow;
import testooj3.gui.components.IMainWindow;
import testooj3.persistence.Filtro;
/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
@SuppressWarnings("serial")
public class JDTestCasesExecutor extends JFrame implements ITestCasesWindowExecutor, ILogWindow {

	private javax.swing.JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JEditorPane jepMsgCorrecto = null;
	private JLabel jLabel = null;
	private JComboBox jcbTestFiles = null;
	private DefaultComboBoxModel mTestFilesModel = new DefaultComboBoxModel();
	private JLabel jLabel2 = null;
	private MutantExecutor mExecutor;

    /**
     * 
     * @uml.property name="jScrollPane1" multiplicity="(0 1)"
     */
    private JScrollPane jScrollPane1 = null;

	private JTextArea jtaClassPath = null;
	private JScrollPane jScrollPane2 = null;
	private JList jLocationsOfTheCUTVersions = null;
	private JButton jbMarkAsOriginal = null;
	private DefaultListModel alternativeVersionsModel = null;   //  @jve:decl-index=0:
	private JLabel jLabel3 = null;
	private JButton jButton = null;
	private ResultsAnalyzer mAnalyzer;
	private JProgressBar jpbMutants = null;
	private JRadioButton jrbIncreasing = null;
	private JRadioButton jrbDecreasing = null;
	private ButtonGroup grupo=new ButtonGroup();  //  @jve:decl-index=0:
	private ButtonGroup grupoTimeout=new ButtonGroup();  //  @jve:decl-index=0:
	private JButton jbAddVersion = null;
	private String original;
	private JLabel jLabel1 = null;
	private JTextField jtfCUTName = null;
	private JButton jbRemove = null;
	private JButton jbSearchVersions = null;
	private JButton jbVersionsDirectory = null;
	private JTextField jtfVersionsDirectory = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenuFile = null;
	private JMenuItem jMenuFileOrganizeFolder = null;
	private JButton jbExecuteSelected = null;
	private JRadioButton jrbRandom = null;
	private JMenuItem jMenuFileSetup = null;
	private JButton jbTimeComparison = null;
	private JPanel jpTimeout = null;
	private JLabel jLabel4 = null;
	private JTextField jtfSeconds = null;
	private JRadioButton jrbTimeoutMiliseconds = null;
	private JLabel jLabel5 = null;
	private JRadioButton jrbTimeoutPercentage = null;
	private JTextField jtfPercentage = null;
	private JLabel jLabel6 = null;
	private JRadioButton jrbTimeoutNoTimeOut = null;
	private Timeout timeout;
	private JScrollPane jScrollPane3 = null;
	private JEditorPane jepMsgErrores = null;
	private JCheckBox jchRemote = null;
	private JScrollPane jScrollPane4 = null;
	private JTable jtOnTimeExecution = null;
	private DefaultTableModel onTimeExecutionModel=new DefaultTableModel();
	private boolean hasTestCases;
	private JMenu jMenuNOrder = null;
	private JMenuItem jMenuNOrderMix = null;
	/**
	 * This is the default constructor
	 * @throws Exception
	 */
	public JDTestCasesExecutor() throws Exception {
		super();
		initialize();
		grupo.add(this.jrbIncreasing);
		grupo.add(this.jrbDecreasing);
		grupo.add(this.jrbRandom);
		grupoTimeout.add(this.jrbTimeoutMiliseconds);
		grupoTimeout.add(this.jrbTimeoutPercentage);
		grupoTimeout.add(this.jrbTimeoutNoTimeOut);
		this.mAnalyzer=new ResultsAnalyzer(this);
		this.jcbTestFiles.setModel(this.mTestFilesModel);
		this.jtOnTimeExecution.setModel(this.onTimeExecutionModel);
		loadTestFiles();
	}

	protected void loadTestFiles() throws Exception {
		Configuration cfg=Configuration.getInstance();
//		String testFilesPaths=cfg.getMujavaRoot()+"testset"+File.separator+";"+cfg.getAdditionalTestFilesPaths();
		String testFilesPaths=cfg.getAdditionalTestFilesPaths();
		String currentPath[]=testFilesPaths.split(";");
		for (int i=0; i<currentPath.length; i++) {
			String thePath=currentPath[i].endsWith(File.separator) ? currentPath[i] : currentPath[i]+File.separator;  
			Filtro filtro=new Filtro(thePath, ".class");
			Vector<String> files=new Vector<String>();
	        filtro.lista(files, "", true);
			String[] lista=new String[files.size()];
			for (int j=0; j<files.size(); j++) 
				lista[j]=files.get(j).toString();
	        for (int j=0; j<lista.length; j++) {
	            if (lista[j].endsWith(".class")) {
	            	String auxi=Auxi.substituteAll(lista[j], File.separator, ".");
	            	if (auxi.startsWith("."))
	            		auxi=auxi.substring(1);
	                this.mTestFilesModel.addElement(auxi);
	            }
	        }
		}
	}
	
	public void setParentWindow(IMainWindow v) {
	}
	
    /**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(1122, 643);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Test case executor");
		this.addWindowListener(new java.awt.event.WindowAdapter() { 
			public void windowClosing(java.awt.event.WindowEvent e) {    
				exit();
			}
		});
	}
	/**
     * 
     */
    protected void exit() {
       dispose();
    }

    /**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(5, 49, 205, 20));
			jLabel1.setText("Full CUT name (including package)");
			jLabel3 = new JLabel();
			jLabel2 = new JLabel();
			jLabel = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jLabel.setBounds(5, 23, 143, 19);
			jLabel.setText("Test file");
			jLabel2.setBounds(694, 80, 79, 19);
			jLabel2.setText("CLASSPATH");
			jLabel3.setBounds(11, 106, 192, 19);
			jLabel3.setText("Locations of the CUT versions");
			jContentPane.add(jLabel, null);
			jContentPane.add(getJScrollPane3(), null);
			jContentPane.add(getJcbTestFiles(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(getJScrollPane2(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJbMarkAsOriginal(), null);
			jContentPane.add(getJpbMutants(), null);
			jContentPane.add(getJrbIncreasing(), null);
			jContentPane.add(getJrbDecreasing(), null);
			jContentPane.add(getJbAddVersion(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJtfCUTName(), null);
			jContentPane.add(getJbRemove(), null);
			jContentPane.add(getJbSearchVersions(), null);
			jContentPane.add(getJbVersionsDirectory(), null);
			jContentPane.add(getJtfVersionsDirectory(), null);
			jContentPane.add(getJbExecuteSelected(), null);
			jContentPane.add(getJrbRandom(), null);
			jContentPane.add(getJbTimeComparison(), null);
			jContentPane.add(getJpTimeout(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJScrollPane4(), null);
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
			jScrollPane.setBounds(new Rectangle(692, 192, 413, 54));
			jScrollPane.setViewportView(getJepMsgCorrecto());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jEditorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */    
	private JEditorPane getJepMsgCorrecto() {
		if (jepMsgCorrecto == null) {
			jepMsgCorrecto = new JEditorPane();
			jepMsgCorrecto.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 11));
			jepMsgCorrecto.setContentType("text/plain");
		}
		return jepMsgCorrecto;
	}
	
    public void logError(String msg) {
        this.jepMsgErrores.setText(msg);
        jepMsgErrores.select(0, 0);  
    }
    
    public void log(String msg) {
        this.jepMsgCorrecto.setText(msg);
        jepMsgCorrecto.select(0, 0);  
    }

 	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */    
	private JComboBox getJcbTestFiles() {
		if (jcbTestFiles == null) {
			jcbTestFiles = new JComboBox();
			jcbTestFiles.setBounds(154, 23, 340, 20);
			jcbTestFiles.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					clearTable();
				}
			});
		}
		return jcbTestFiles;
	}

	/**
     * This method initializes jScrollPane1	
     * 	
     * @return javax.swing.JScrollPane
     * 
     * @uml.property name="jScrollPane1"
     */
    private JScrollPane getJScrollPane1() {
        if (jScrollPane1 == null) {
            jScrollPane1 = new JScrollPane();
            jScrollPane1.setBounds(691, 106, 416, 59);
            jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            jScrollPane1.setViewportView(getJtaClassPath());
        }
        return jScrollPane1;
    }

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */    
	private JTextArea getJtaClassPath() {
		if (jtaClassPath == null) {
			jtaClassPath = new JTextArea();
			jtaClassPath.setColumns(40);
			Configuration cfg = Configuration.getInstance();
			String classPath=cfg.getClassPath();
			if (cfg.getAdditionalTestFilesPaths()!=null && cfg.getAdditionalTestFilesPaths().length()>0) { 
				if (!classPath.endsWith(";"))
					classPath+=";";
				classPath+=cfg.getAdditionalTestFilesPaths();
			}
			if (cfg.getMujavaRoot()!=null && cfg.getMujavaRoot().length()>0) {
				if (!classPath.endsWith(";"))
					classPath+=";";
				classPath+=cfg.getMujavaRoot()+"testset"+File.separator;
			}
//			jtaClassPath.setText(classPath);
			//diego modificacion
			jtaClassPath.setText("/home/diego/results/wrappers;"
								+ "/home/diego/lib/commons-logging-1.1.1.jar;"
								+ "/home/diego/lib/axis2-adb-1.6.2.jar;"
								+ "/home/diego/lib/axiom-api-1.2.13.jar;"
								+ "/home/diego/lib/XmlSchema-1.4.7.jar;"
								+ "/home/diego/lib/wsdl4j-1.6.2.jar;"
								+ "/home/diego/lib/axis2-kernel-1.6.2.jar;"
								+ "/home/diego/lib/axiom-impl-1.2.13.jar;"
								+ "/home/diego/lib/neethi-3.0.2.jar;"
								+ "/home/diego/lib/axis2-transport-http-1.6.2.jar;"
								+ "/home/diego/lib/axis2-transport-local-1.6.2.jar;"
								+ "/home/diego/lib/commons-httpclient-3.1.jar;"
								+ "/home/diego/lib/mail-1.4.jar;/home/diego/lib/commons-codec-1.3.jar;"
								+ "/home/diego/lib/httpcore-4.0.jar;");
		}
		return jtaClassPath;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(2, 132, 687, 154);
			jScrollPane2.setViewportView(getJLocationsOfTheCUTVersions());
		}
		return jScrollPane2;
	}
	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */    
	private JList getJLocationsOfTheCUTVersions() {
		if (jLocationsOfTheCUTVersions == null) {
			jLocationsOfTheCUTVersions = new JList();
			jLocationsOfTheCUTVersions.setModel(getTraditionalMutantsModel());
			jLocationsOfTheCUTVersions.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseClicked(java.awt.event.MouseEvent e) {    
					if (e.getClickCount()==2) {
					    executeOnMutant();
					}
				}
			});
		}
		return jLocationsOfTheCUTVersions;
	}
	
	/**
     * 
     */
    protected void executeOnMutant() {
    	int index=jLocationsOfTheCUTVersions.getSelectedIndex();
		if (index==-1) return;
    	executeSelected();
	}
    
	protected void executeSelected() {
		calculateTimeout();
		if (this.alternativeVersionsModel.size()==0) return;
		if (!this.jchRemote.isSelected()) {
			this.mExecutor=new LocalMutantExecutor(this);
	    } else {
	    	this.mExecutor=new Client(this);
	    }
		String fileWithTestCases=this.mTestFilesModel.getSelectedItem().toString();
	    String classPath=this.jtaClassPath.getText();
    	String cutName=this.jtfCUTName.getText();
    	boolean continuar=this.existeFichero(cutName, classPath, "This can lead to no killing mutants.", 0);
    	if (!continuar) {
    			return;
    	}   	
    	continuar=existeFichero(fileWithTestCases, classPath, "This can lead to unexpected results.", 1);
    	if (!continuar) {
			return;
    	}
		this.mExecutor.setExecutionMode(LocalMutantExecutor.ALL_METHODS);
		this.mExecutor.setTimeout(this.timeout);		
		this.mExecutor.setFileWithTestCases(fileWithTestCases);
		this.mExecutor.setClassPath(classPath);
		this.mExecutor.setCUTName(cutName);
		Vector<String> vv=new Vector<String>();
		for (int i=0; i<this.jLocationsOfTheCUTVersions.getSelectedValues().length; i++) {
			String cv=this.jLocationsOfTheCUTVersions.getSelectedValues()[i].toString();
			vv.add(cv);
		}
		this.mExecutor.setVersionsPaths(vv.elements());
		Thread t=new Thread(this.mExecutor);
		t.start();
		
	}

	private boolean existeFichero(String cutName, String classPath, String defaultMsg, int allowedTimes) {
		Vector<String> dondeExiste = ClassPathFunctions.findClass(cutName, classPath);
    	String msg=null; 
    	if (dondeExiste.size()==allowedTimes) return true;
    	if (dondeExiste.size()==1) 
    		msg="Warning: " + cutName + " already exists in the classpath\n\r(" + dondeExiste + ").\n\r" + defaultMsg + " Continue?";
    	if (dondeExiste.size()>1) {
    		msg="Warning: " + cutName + " exists several times in the classpath.\n\r" + defaultMsg + "\n\r";
    		for (int i=0; i<dondeExiste.size(); i++) {
    			msg+=dondeExiste.get(i)+"\n\r";
    		}
    	}
    	if (msg!=null) {
    		int cual=javax.swing.JOptionPane.showConfirmDialog(this, msg);
    		if (cual!=javax.swing.JOptionPane.YES_OPTION)
    			return false;
    	} 
		return true;
	}


	
    private void calculateTimeout() {
		if (this.jrbTimeoutMiliseconds.isSelected()) {
			this.timeout=new Timeout(Double.parseDouble(this.jtfSeconds.getText()));
		} else if (this.jrbTimeoutPercentage.isSelected()) {
			this.timeout=new Timeout(Double.parseDouble(this.jtfPercentage.getText()));
			this.timeout.setMode(Timeout.PERCENTAGE);
		} else this.timeout=new Timeout(Double.MAX_VALUE);
	}

	protected void executeOriginalAndMutants() {        
        String testClassName=this.mTestFilesModel.getSelectedItem().toString();
        String classPath=this.jtaClassPath.getText();
        String cutName=this.jtfCUTName.getText();
        this.mExecutor.setClassPath(classPath);
        this.mExecutor.setCUTName(cutName);
        this.mExecutor.setTestClassName(testClassName);
        Thread t=new Thread(this.mExecutor);
        t.start();
    }
    
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbMarkAsOriginal() {
		if (jbMarkAsOriginal == null) {
			jbMarkAsOriginal = new JButton();
			jbMarkAsOriginal.setBounds(400, 104, 123, 21);
			jbMarkAsOriginal.setText("Set as original");
			jbMarkAsOriginal.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					int index=jLocationsOfTheCUTVersions.getSelectedIndex();
					if (index==-1) return;
					setAsOriginal(index);
				}
			});
		}
		return jbMarkAsOriginal;
	}
	
	private void setAsOriginal(int index) {		
		this.original=this.alternativeVersionsModel.get(index).toString();
	}
	
	/**
	 * This method initializes traditionalMutantsModel	
	 * 	
	 * @return javax.swing.DefaultListModel	
	 */    
	private DefaultListModel getTraditionalMutantsModel() {
		if (alternativeVersionsModel == null) {
			alternativeVersionsModel = new DefaultListModel();
		}
		return alternativeVersionsModel;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(4, 311, 189, 22);
			jButton.setText("Result analysis");
			jButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					resultAnalysis();
				}
			});
		}
		return jButton;
	}
	
	private void resultAnalysis() {
		if (this.original==null) {
			logError("Select the original CUT");
			return;
		}
	    try {
		    initializeAnalyzer();
		    ObjectTable ot=this.mAnalyzer.analyzeResults();
		    JDResultsAnalyzer j=new JDResultsAnalyzer();
		    j.setNumberOfTabs(1);
		    PriorizationAlgorithm algorithm;
		    if (jrbIncreasing.isSelected()) 
		        algorithm=new Increasing(ot);
		    else if (jrbDecreasing.isSelected()) 
		    	algorithm=new Decreasing(ot);
		    else algorithm=null; //new RandomPA(ot);
		    ot.setAlgorithm(algorithm);
		    j.setResults(ot);
		    j.setModal(false);
		    j.setVisible(true);
	    }
	    catch (Exception ex) {
	        this.logError(ex.toString());
	    }
	}

	private void initializeAnalyzer() {
		if (this.jcbTestFiles.getSelectedIndex()==-1) {
			logError("Select a file with test cases");
			return;
		}
		String testFile=this.jcbTestFiles.getSelectedItem().toString();
		if (testFile.endsWith(".class"))
			testFile=testFile.substring(0, testFile.length()-6);
		this.mAnalyzer.setTestFile(testFile);
		this.mAnalyzer.setOriginalCut(this.original);
	}
	
	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */    
	private JProgressBar getJpbMutants() {
		if (jpbMutants == null) {
			jpbMutants = new JProgressBar();
			jpbMutants.setBounds(4, 290, 682, 16);
		}
		return jpbMutants;
	}
	
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */    
	private JRadioButton getJrbIncreasing() {
		if (jrbIncreasing == null) {
			jrbIncreasing = new JRadioButton();
			jrbIncreasing.setBounds(217, 311, 90, 25);
			jrbIncreasing.setText("Increasing");
		}
		return jrbIncreasing;
	}
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */    
	private JRadioButton getJrbDecreasing() {
		if (jrbDecreasing == null) {
			jrbDecreasing = new JRadioButton();
			jrbDecreasing.setBounds(309, 313, 93, 23);
			jrbDecreasing.setText("Decreasing");
			jrbDecreasing.setSelected(true);
		}
		return jrbDecreasing;
	}
        /**
	 * This method initializes jbAddVersion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAddVersion() {
		if (jbAddVersion == null) {
			jbAddVersion = new JButton();
			jbAddVersion.setBounds(new Rectangle(209, 105, 90, 20));
			jbAddVersion.setText("Add");
			jbAddVersion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					addVersion();
				}
			});
		}
		return jbAddVersion;
	}

		protected void addVersion() {
	        JFileChooser fc=new JFileChooser();	        
	        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        int returnVal=fc.showOpenDialog(this);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	          String versionFileName=fc.getSelectedFile().getAbsolutePath();
	          this.alternativeVersionsModel.addElement(versionFileName);
	          if (this.alternativeVersionsModel.getSize()==1)
	        	  this.setAsOriginal(0);
	        }
		}

		/**
		 * This method initializes jtfCUTName	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		private JTextField getJtfCUTName() {
			if (jtfCUTName == null) {
				jtfCUTName = new JTextField();
				jtfCUTName.setText("wtp.SimpleCalculatorService.class");
				jtfCUTName.setBounds(new Rectangle(211, 49, 285, 22));
			}
			return jtfCUTName;
		}

	/**
	 * This method initializes jbRemove	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbRemove() {
		if (jbRemove == null) {
			jbRemove = new JButton();
			jbRemove.setBounds(new Rectangle(304, 105, 92, 21));
			jbRemove.setText("Remove");
			jbRemove.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					remove();
				}
			});
		}
		return jbRemove;
	}

	protected void remove() {
		int index=this.jLocationsOfTheCUTVersions.getSelectedIndex();
		if (index==-1) return;
		String selected=this.alternativeVersionsModel.get(index).toString();
		if (original!=null && selected.equals(original))
			original=null;
		this.alternativeVersionsModel.remove(index);
	}

	/**
	 * This method initializes jbSearchVersions	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbSearchVersions() {
		if (jbSearchVersions == null) {
			jbSearchVersions = new JButton();
			jbSearchVersions.setBounds(new Rectangle(408, 76, 86, 21));
			jbSearchVersions.setText("Search");
			jbSearchVersions.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					searchVersions();
				}
			});
		}
		return jbSearchVersions;
	}

	protected void searchVersions() {
		this.clearTable();
		String fullCUTName=this.jtfCUTName.getText();
		if (fullCUTName==null || fullCUTName.length()==0)
			return;
		String versionsDirectory=this.jtfVersionsDirectory.getText();
		if (versionsDirectory==null || versionsDirectory.length()==0)
			return;
		try {
			searchVersions(versionsDirectory, fullCUTName);
		} catch (Exception e) {
			logError(e.toString());
		}
	}

	private void searchVersions(String versionsDirectory, String fullCUTName) throws Exception {
		if (!fullCUTName.endsWith(".class")) {
			fullCUTName+=".class";
			this.jtfCUTName.setText(fullCUTName);
		}
		String searchedFile;
		if (fullCUTName.indexOf(".")!=fullCUTName.lastIndexOf(".")) {
			String auxi=fullCUTName.substring(0, fullCUTName.length()-6);
			searchedFile=auxi.substring(auxi.lastIndexOf(".")+1);
		} else {
			searchedFile=fullCUTName.substring(0, fullCUTName.lastIndexOf("."));
		}
		searchedFile+=".class";
		Filtro f=new Filtro(versionsDirectory, searchedFile);
		f.setStrictEnd(true);
        Vector<String> ficheros=new Vector<String>(), result=new Vector<String>();
        f.lista(ficheros, "", true);
        fullCUTName=Auxi.substituteAll(fullCUTName, ".", File.separator);
        fullCUTName=fullCUTName.substring(0, fullCUTName.length()-6)+".class";
        
        if (ficheros.size()==0) {
        	String msg="No versions of " + fullCUTName + " found in " + versionsDirectory + ".\n\r" +
        		"If you are sure there are versions on that location, organize the versions folder by the File menu.";
			javax.swing.JOptionPane.showMessageDialog(this, msg);
    		return ;
        }
        for (int i=0; i<ficheros.size(); i++) {
        	String fichero=ficheros.get(i).toString();
        	fichero=Auxi.substituteAll(fichero, "\\", File.separator);
        	if (fichero.endsWith(fullCUTName)) {
        		fichero=fichero.substring(0, fichero.length()-fullCUTName.length());
        		if (fichero.startsWith("/"))
        			fichero=fichero.substring(1);
        		result.add(fichero);
        	}
        }
        this.alternativeVersionsModel.removeAllElements();
        if (!versionsDirectory.endsWith("/")) versionsDirectory+="/";
        for (int i=0; i<result.size(); i++) {
        	String vn=versionsDirectory + result.get(i).toString();
        	this.alternativeVersionsModel.addElement(vn);
        }
	}

	/**
	 * This method initializes jbVersionsDirectory	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbVersionsDirectory() {
		if (jbVersionsDirectory == null) {
			jbVersionsDirectory = new JButton();
			jbVersionsDirectory.setBounds(new Rectangle(5, 76, 140, 21));
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
			jtfVersionsDirectory.setBounds(new Rectangle(151, 75, 253, 22));
			String vd=Configuration.getInstance().getMujavaRoot();
			jtfVersionsDirectory.setText(vd);
		}
		return jtfVersionsDirectory;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenuFile());
			jJMenuBar.add(getJMenuNOrder());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenuFile	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuFile() {
		if (jMenuFile == null) {
			jMenuFile = new JMenu();
			jMenuFile.setText("File");
			jMenuFile.add(getJMenuFileOrganizeFolder());
			jMenuFile.add(getJMenuFileSetup());
		}
		return jMenuFile;
	}

	/**
	 * This method initializes jMenuFileOrganizeFolder	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuFileOrganizeFolder() {
		if (jMenuFileOrganizeFolder == null) {
			jMenuFileOrganizeFolder = new JMenuItem();
			jMenuFileOrganizeFolder.setText("Organize versions folder");
			jMenuFileOrganizeFolder.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					organizeFolders();
				}
			});
		}
		return jMenuFileOrganizeFolder;
	}

	protected void organizeFolders() {
		JDOrganizeFolders j=new JDOrganizeFolders(this);
		j.setParentWindow(this);
		j.setCutName(this.jtfCUTName.getText());
		j.setVersionsDirectory(this.jtfVersionsDirectory.getText());
		j.setVisible(true);
	}

	/**
	 * This method initializes jbExecuteSelected	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbExecuteSelected() {
		if (jbExecuteSelected == null) {
			jbExecuteSelected = new JButton();
			jbExecuteSelected.setBounds(new Rectangle(524, 104, 141, 21));
			jbExecuteSelected.setText("Execute selected");
			jbExecuteSelected.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					executeSelected();
				}
			});
		}
		return jbExecuteSelected;
	}

	/**
	 * This method initializes jrbRandom	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbRandom() {
		if (jrbRandom == null) {
			jrbRandom = new JRadioButton();
			jrbRandom.setBounds(new Rectangle(411, 312, 80, 22));
			jrbRandom.setText("Random");
		}
		return jrbRandom;
	}

	/**
	 * This method initializes jMenuFileSetup	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuFileSetup() {
		if (jMenuFileSetup == null) {
			jMenuFileSetup = new JMenuItem();
			jMenuFileSetup.setText("Setup");
			jMenuFileSetup.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setUp();
				}
			});
		}
		return jMenuFileSetup;
	}

    protected void setUp() {
        JDSetup j=new JDSetup(this);
        j.setModal(true);
        j.setVisible(true);
    }

	/**
	 * This method initializes jbTimeComparison	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbTimeComparison() {
		if (jbTimeComparison == null) {
			jbTimeComparison = new JButton();
			jbTimeComparison.setBounds(new Rectangle(511, 312, 168, 23));
			jbTimeComparison.setText("Time comparison");
			jbTimeComparison.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					timeComparison();
				}
			});
		}
		return jbTimeComparison;
	}

	protected void timeComparison() {
		try {
			if (this.original==null) {
				this.logError("Select the original CUT");
				return;
			}
			JDResultsAnalyzer j=new JDResultsAnalyzer();
			this.initializeAnalyzer();
			TimesTable tt=this.mAnalyzer.analyzeTimesAndMutantsKilled();
			j.setNumberOfTabs(2);
			j.setResults(tt);
			j.setModal(true);
			j.setVisible(true);
		}
		catch (Exception ex) {
			this.logError(ex.toString());
		}
	}

	/**
	 * This method initializes jpTimeout	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpTimeout() {
		if (jpTimeout == null) {
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(340, 7, 102, 23));
			jLabel6.setText("% of original");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(175, 8, 67, 19));
			jLabel5.setText("milis");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(8, 8, 61, 18));
			jLabel4.setText("Timeout");
			jpTimeout = new JPanel();
			jpTimeout.setLayout(null);
			jpTimeout.setBounds(new Rectangle(498, 22, 609, 53));
			jpTimeout.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			jpTimeout.add(jLabel4, null);
			jpTimeout.add(getJtfSeconds(), null);
			jpTimeout.add(getJrbTimeoutMiliseconds(), null);
			jpTimeout.add(jLabel5, null);
			jpTimeout.add(getJrbTimeoutPercentage(), null);
			jpTimeout.add(getJtfPercentage(), null);
			jpTimeout.add(jLabel6, null);
			jpTimeout.add(getJrbTimeoutNoTimeOut(), null);
			jpTimeout.add(getJchRemote(), null);
		}
		return jpTimeout;
	}

	/**
	 * This method initializes jtfSeconds	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfSeconds() {
		if (jtfSeconds == null) {
			jtfSeconds = new JTextField();
			jtfSeconds.setBounds(new Rectangle(109, 7, 61, 20));
			jtfSeconds.setText("3000");
		}
		return jtfSeconds;
	}

	/**
	 * This method initializes jrbTimeoutMiliseconds	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbTimeoutMiliseconds() {
		if (jrbTimeoutMiliseconds == null) {
			jrbTimeoutMiliseconds = new JRadioButton();
			jrbTimeoutMiliseconds.setBounds(new Rectangle(76, 8, 29, 19));
			jrbTimeoutMiliseconds.setSelected(true);
		}
		return jrbTimeoutMiliseconds;
	}

	/**
	 * This method initializes jrbTimeoutPercentage	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbTimeoutPercentage() {
		if (jrbTimeoutPercentage == null) {
			jrbTimeoutPercentage = new JRadioButton();
			jrbTimeoutPercentage.setBounds(new Rectangle(262, 6, 22, 21));
		}
		return jrbTimeoutPercentage;
	}

	/**
	 * This method initializes jtfPercentage	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfPercentage() {
		if (jtfPercentage == null) {
			jtfPercentage = new JTextField();
			jtfPercentage.setBounds(new Rectangle(294, 5, 41, 24));
		}
		return jtfPercentage;
	}

	/**
	 * This method initializes jrbTimeoutNoTimeOut	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbTimeoutNoTimeOut() {
		if (jrbTimeoutNoTimeOut == null) {
			jrbTimeoutNoTimeOut = new JRadioButton();
			jrbTimeoutNoTimeOut.setBounds(new Rectangle(463, 6, 61, 21));
			jrbTimeoutNoTimeOut.setText("None");
		}
		return jrbTimeoutNoTimeOut;
	}

	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(692, 261, 406, 70));
			jScrollPane3.setViewportView(getJepMsgErrores());
		}
		return jScrollPane3;
	}

	/**
	 * This method initializes jepMsgErrores	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJepMsgErrores() {
		if (jepMsgErrores == null) {
			jepMsgErrores = new JEditorPane();
			jepMsgErrores.setContentType("text/plain");
			jepMsgErrores.setFont(new Font("Dialog", Font.PLAIN, 12));
		}
		return jepMsgErrores;
	}

	/**
	 * This method initializes jchRemote	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJchRemote() {
		if (jchRemote == null) {
			jchRemote = new JCheckBox();
			jchRemote.setBounds(new Rectangle(10, 31, 165, 17));
			jchRemote.setText("Remote");
		}
		return jchRemote;
	}

	/**
	 * This method initializes jScrollPane4	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setBounds(new Rectangle(4, 336, 1098, 239));
			jScrollPane4.setViewportView(getJtOnTimeExecution());
		}
		return jScrollPane4;
	}

	/**
	 * This method initializes jtOnTimeExecution	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJtOnTimeExecution() {
		if (jtOnTimeExecution == null) {
			jtOnTimeExecution = new JTable();
		}
		return jtOnTimeExecution;
	}

	/**
	 * This method initializes jMenuNOrder	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuNOrder() {
		if (jMenuNOrder == null) {
			jMenuNOrder = new JMenu();
			jMenuNOrder.setText("N-order mutation");
			jMenuNOrder.add(getJMenuNOrderMix());
		}
		return jMenuNOrder;
	}

	/**
	 * This method initializes jMenuNOrderMix	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuNOrderMix() {
		if (jMenuNOrderMix == null) {
			jMenuNOrderMix = new JMenuItem();
			jMenuNOrderMix.setText("Open mix window");
			jMenuNOrderMix.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mixMutants();
				}
			});
		}
		return jMenuNOrderMix;
	}

	protected void mixMutants() {
		JFMutantsMixer j=new JFMutantsMixer();
		j.setCUTName(this.jtfCUTName.getText());
		j.setDirectoryOfSourceVersions(this.jtfVersionsDirectory.getText());
		j.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
	    JDTestCasesExecutor j=new JDTestCasesExecutor();
	    j.setVisible(true);
	 }

    /**
     * @param length
     */
    public void setNumberOfMutants(int length) {
        this.jpbMutants.setMaximum(length);
    }

    /**
     * @param i
     */
    public void setCurrentMutant(int i) {
        this.jpbMutants.setValue(i);
    }

	public void setTestCases(Method[] methodsWithTestCases) {
		Vector<String> testCaseNames=new Vector<String>();
		testCaseNames.add("");
		for (int i=0; i<methodsWithTestCases.length; i++)
			testCaseNames.add(methodsWithTestCases[i].getName());
		this.onTimeExecutionModel.setColumnIdentifiers(testCaseNames);
		this.hasTestCases=true;
	}

	public int addVersionRow(String versionPath, int length) {
		Vector<String> rowData=new Vector<String>();
		rowData.add(versionPath);
		for (int i=0; i<length; i++)
			rowData.add("");
		this.onTimeExecutionModel.addRow(rowData);
		return this.onTimeExecutionModel.getRowCount()-1;
	}

	public void log(String msg, int row, int column) {
		this.onTimeExecutionModel.setValueAt(msg, row, column);
	}

	public boolean hasTestCases() {
		return this.hasTestCases;
	}
	
	protected void clearTable() {
		this.log("");
		this.logError("");
		this.hasTestCases=false;
		while (this.onTimeExecutionModel.getRowCount()>0)
			this.onTimeExecutionModel.removeRow(0);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

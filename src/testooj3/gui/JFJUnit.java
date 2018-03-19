package testooj3.gui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import me.gui.JDTestCasesExecutor;
import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TField;
import testooj3.domain.TMujavaMethod;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.TestMethod;
import testooj3.domain.TestTemplate;
import testooj3.domain.algorithms.Algorithm;
import testooj3.domain.algorithms.AllCombinationsElegant;
import testooj3.domain.states.State;
import testooj3.domain.tcmutation.TCMutationOperators;
import testooj3.domain.tcmutation.TCMutatorSystem;
import testooj3.domain.tcmutation.TGroupedFile;
import testooj3.domain.testfiles.junit.JUnitFile;
import testooj3.domain.testfiles.mujava.MujavaFile;
import testooj3.domain.testfiles.states.JUnitFileForStates;
import testooj3.gui.compatibility.JFCompatibility;
import testooj3.gui.components.ClassSelectorPanel;
import testooj3.gui.components.ClassStructurePanel;
import testooj3.gui.components.ILogWindow;
import testooj3.gui.components.IMainWindow;
import testooj3.gui.components.JDAlgorithms;
import testooj3.gui.components.TestTemplatesPanel;
import testooj3.gui.externaltools.JLaunchJUnit;
import testooj3.gui.stmachine.states.JFStates;
import testooj3.gui.tcmutation.JDOperators;
/**
 * @author  Administrador
 */
@SuppressWarnings("serial")
public class JFJUnit extends JFrame implements IMainWindow, ILogWindow, ITemplatesWindow {
    
    private javax.swing.JPanel jContentPane = null;
    private ClassSelectorPanel classSelectorPanel = null;
    private ClassStructurePanel classStructurePanel = null;
    private JLabel jLabel = null;
    private JTextField jtfRegularExpression = null;
    private TestTemplatesPanel testTemplatesPanel = null;
    private JLabel jlMaxLength = null;
    private JSlider jSlider = null;
    private JTextField jtfMaxLength = null;
    private JButton jbGetTemplates = null;
    private JMenuBar jJMenuBar = null;
    private JMenu jMenuFile = null;
    private JMenuItem jMenuFileSetup = null;
    private JMenuItem jMenuFileObjectCreation = null;
    private JMenuItem jMenuObjectManager = null;
    private JMenuItem jMenuJUnitAssertions = null;
    private JScrollPane jScrollPane = null;
    private JEditorPane jepMsg = null;
    private JMenuItem jMenuFileExit = null;
    private JMenu jMenuExternalTools = null;
	private JMenuItem jmJUnit = null;
	private JMenu jMenuMujava = null;
	private JMenuItem jMenuMujavaCompilation = null;
	private JMenuItem jMenuMutantGeneration = null;
	private JMenuItem jMenuRebuildTestFile = null;
	private JMenuItem jMenuMutantExecution = null;
	private JMenuItem jMenuFileStatesDefinition = null;
	private JButton jbBuildFile = null;
	private JMenuItem jmMutantExecution = null;
	private JButton jbMutantsJUnit = null;
	private JMenu jMenuTCMutation = null;
	private JMenuItem jmTCMutationOperators = null;
	private JMenu jMenuAlgorithms = null;
	private JMenuItem jmiSelectAlgorithm = null;
    /**
     * This is the default constructor
     */
    public JFJUnit() {
        super();
        initialize();
        ButtonGroup grupo=new ButtonGroup();
        grupo.add(this.jrbBuildJunitFile);
        grupo.add(this.jrbBuildMuJavaFile);
        grupo.add(this.jrbBuildBothFiles);
        grupo.add(this.jrbBuildStatesFile);
        this.algorithm=new AllCombinationsElegant();
        this.classSelectorPanel.setParentWindow(this);
        this.classStructurePanel.setParentWindow(this);
        this.updateMaxLength();
    }
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setJMenuBar(getJJMenuBar());
        this.setSize(679, 601);
        this.setContentPane(getJContentPane());
        this.setTitle("JUnit and Mujava test case generator - (c) Alarcos Group, UCLM");
        
    }
    /**
     * This method initializes jContentPane
     * 
     borderLayout1.setHgap(10);
     
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJContentPane() {
        if(jContentPane == null) {
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.gridx = 1;
            gridBagConstraints5.gridy = 3;
            GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
            gridBagConstraints16.insets = new Insets(1, 1, 1, 1);
            gridBagConstraints16.gridy = 1;
            gridBagConstraints16.ipadx = 0;
            gridBagConstraints16.ipady = 0;
            gridBagConstraints16.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints16.gridx = 1;
            GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
            gridBagConstraints15.insets = new Insets(5, 5, 5, 5);
            gridBagConstraints15.gridy = 3;
            gridBagConstraints15.ipadx = 2;
            gridBagConstraints15.ipady = 3;
            gridBagConstraints15.anchor = GridBagConstraints.WEST;
            gridBagConstraints15.gridx = 0;
            GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
            gridBagConstraints14.fill = GridBagConstraints.BOTH;
            gridBagConstraints14.gridwidth = 3;
            gridBagConstraints14.gridx = 0;
            gridBagConstraints14.gridy = 2;
            gridBagConstraints14.ipadx = 0;
            gridBagConstraints14.ipady = 0;
            gridBagConstraints14.weightx = 1.0;
            gridBagConstraints14.weighty = 1.0;
            gridBagConstraints14.insets = new Insets(2, 10, 2, 10);
            GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
            gridBagConstraints13.insets = new Insets(1, 1, 3, 8);
            gridBagConstraints13.gridx = 2;
            gridBagConstraints13.gridy = 1;
            gridBagConstraints13.ipadx = 0;
            gridBagConstraints13.ipady = 0;
            gridBagConstraints13.fill = GridBagConstraints.BOTH;
            gridBagConstraints13.gridheight = 1;
            GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
            gridBagConstraints12.insets = new Insets(1, 1, 1, 1);
            gridBagConstraints12.gridy = 1;
            gridBagConstraints12.ipadx = 0;
            gridBagConstraints12.ipady = 0;
            gridBagConstraints12.fill = GridBagConstraints.BOTH;
            gridBagConstraints12.gridx = 0;
            GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.insets = new Insets(1, 1, 1, 1);
            gridBagConstraints10.gridx = 0;
            gridBagConstraints10.gridy = 0;
            gridBagConstraints10.ipadx = 0;
            gridBagConstraints10.ipady = 0;
            gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints10.gridwidth = 3;
            jlMaxLength = new JLabel();
            jLabel = new JLabel();
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(new GridBagLayout());
            jLabel.setText("Regular expression");
            jlMaxLength.setText("Max length");
            jContentPane.add(getClassSelectorPanel(), gridBagConstraints10);
            jContentPane.add(getClassStructurePanel(), gridBagConstraints12);
            jContentPane.add(getTestTemplatesPanel(), gridBagConstraints13);
            jContentPane.add(getJScrollPane(), gridBagConstraints14);
            jContentPane.add(getJbMutantsJUnit(), gridBagConstraints15);
            jContentPane.add(getJPanelCenter(), gridBagConstraints16);
            jContentPane.add(getClasspath(), gridBagConstraints5);
        }
        return jContentPane;
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#showMembers(testooj3.domain.TClass)
     */
    public void showMembers(Interface c) {
        this.classStructurePanel.setClass(c);
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#logError(java.lang.String)
     */
    public void logError(String msg) {
        msg="<font color='red'>" + msg + "</font>"+jepMsg.getText().trim();
        this.jepMsg.setText(msg);
        jepMsg.select(0, 0);  
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#setClass(testooj3.domain.TClass)
     */
    public void setClass(Interface c) {
        this.mClass=c;  
    }
    /**
     * This method initializes classSelectorPanel	
     * 	
     * @return testooj3.gui.components.ClassSelectorPanel	
     */    
    private ClassSelectorPanel getClassSelectorPanel() {
        if (classSelectorPanel == null) {
            classSelectorPanel = new ClassSelectorPanel();
            classSelectorPanel.setName("classSelectorPanel");
           // classSelectorPanel.setPreferredSize(new java.awt.Dimension(50,50));
        }
        return classSelectorPanel;
    }
    
    /**
     * This method initializes classStructurePanel	
     * 	
     * @return testooj3.gui.components.ClassStructurePanel	
     */    
    private ClassStructurePanel getClassStructurePanel() {
        if (classStructurePanel == null) {
            classStructurePanel = new ClassStructurePanel();
        }
        return classStructurePanel;
    }
    /**
     * This method initializes jTextField	
     * 	
     * @return javax.swing.JTextField	
     */    
    private JTextField getJtfRegularExpression() {
        if (jtfRegularExpression == null) {
            jtfRegularExpression = new JTextField();
            jtfRegularExpression.addMouseListener(new java.awt.event.MouseAdapter() { 
            	public void mouseClicked(java.awt.event.MouseEvent e) {    
            		if (e.getClickCount()==2)
            		    showExpandedRegularExpression();
            	}
            });
        }
        return jtfRegularExpression;
    }
    /**
     * 
     */
    protected void showExpandedRegularExpression() {
        String re=this.jtfRegularExpression.getText();
        log(mClass.getExpandedRegularExpression(re));
    }
    /**
     * This method initializes testTemplatesPanel	
     * 	
     * @return testooj3.gui.components.TestTemplatesPanel	
     */    
    private TestTemplatesPanel getTestTemplatesPanel() {
        if (testTemplatesPanel == null) {
            testTemplatesPanel = new TestTemplatesPanel();
        }
        return testTemplatesPanel;
    }
    /**
     * This method initializes jSlider	
     * 	
     * @return javax.swing.JSlider	
     */    
    private JSlider getJSlider() {
        if (jSlider == null) {
            jSlider = new JSlider();
            jSlider.setPaintLabels(false);
            jSlider.setPaintTicks(false);
            jSlider.setValue(5);
            jSlider.setMinimum(1);
            jSlider.addChangeListener(new javax.swing.event.ChangeListener() { 
                public void stateChanged(javax.swing.event.ChangeEvent e) {    
                    updateMaxLength();
                }
            });
        }
        return jSlider;
    }
    /**
     * 
     */
    protected void updateMaxLength() {
    	this.jlMaxLength.setText("Max length: " + this.jSlider.getValue());
    	this.jtfMaxLength.setText(""+this.jSlider.getValue());
    }
    /**
     * This method initializes jTextField	
     * 	
     * @return javax.swing.JTextField	
     */    
    private JTextField getJtfMaxLength() {
        if (jtfMaxLength == null) {
            jtfMaxLength = new JTextField();
            jtfMaxLength.setEditable(false);
        }
        return jtfMaxLength;
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#passToRegularExpression(java.lang.String, java.lang.String, int)
     */
    public void methodSelected(TOperation operation) {
        int methodNumber=operation.getPos();
        this.jtfRegularExpression.setText(jtfRegularExpression.getText()+((char) methodNumber));
    }
    
	public void fieldSelected(TField selectedField) {
		// Intencionadamente en blanco.
	}
	
    /**
     * This method initializes jButton	
     * 	
     * @return javax.swing.JButton	
     */    
    private JButton getJbGetTemplates() {
        if (jbGetTemplates == null) {
            jbGetTemplates = new JButton();
            jbGetTemplates.setText("Get test templates");
            jbGetTemplates.addActionListener(new java.awt.event.ActionListener() { 
                public void actionPerformed(java.awt.event.ActionEvent e) {    
                    buildTemplates();
                }
            });
        }
        return jbGetTemplates;
    }
    /**
     * 
     */
    protected void buildTemplates() {
        try 
        {
        	String msg="";
        	if (this.jtfRegularExpression.getText().length()>0) {
	            if (mClass==null) 
	                throw new Exception("There is not any class loaded");
	            Date antes=new Date();
	            Vector tts=mClass.getTestTemplates(Integer.parseInt(jtfMaxLength.getText()), this.jtfRegularExpression.getText());
	            this.testTemplatesPanel.setTemplates(tts);
	            
	            
	            Date despues=new Date();
	            msg=tts.size() + " test templates generated in " + 
	            	((float) (despues.getTime()-antes.getTime())/1000) + " seconds";
        	} else {
        		msg="Test templates proceed from a state machine";
        	}
        	this.mClass.setTestTemplates( this.testTemplatesPanel.getTemplates());
            log(msg);
        }
        catch (NumberFormatException ex) 
        {
            logError("Write an integer number in the \"Max length\" box");
        }
        catch (Exception ex) 
        {
            logError(ex.getMessage());
        }		     
        
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
            jJMenuBar.add(getJMenuExternalTools());
            jJMenuBar.add(getJMenuTCMutation());            
            jJMenuBar.add(getJMenuAlgorithms());
            jJMenuBar.add(getJMenuCompatibility());
            jJMenuBar.add(getJMenuStates());
            jJMenuBar.add(getJMenuFaultPronenness());
        }
        return jJMenuBar;
    }
    /**
     * This method initializes jMenu	
     * 	
     * @return javax.swing.JMenu	
     */    
    private JMenu getJMenuFile() {
        if (jMenuFile == null) {
            jMenuFile = new JMenu();
            jMenuFile.setText("File");
            jMenuFile.add(getJMenuRebuildTestFile());
            jMenuFile.add(getJMenuFileObjectCreation());
            jMenuFile.add(getJMenuObjectManager());
            jMenuFile.add(getJMenuJUnitAssertions());
            jMenuFile.add(getJMenuFileStatesDefinition());
            jMenuFile.add(getJMenuFileSetup());
            jMenuFile.add(getJMenuFileExit());
        }
        return jMenuFile;
    }
    /**
     * This method initializes jMenuItem	
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
    /**
     * 
     */
    protected void setUp() {
        JDSetup j=new JDSetup(this);
        j.setModal(true);
        j.setVisible(true);
        this.classSelectorPanel.setClassPath(j.getClassPath());
    }
    /**
     * This method initializes jMenuItem	
     * 	
     * @return javax.swing.JMenuItem	
     */    
    private JMenuItem getJMenuFileObjectCreation() {
        if (jMenuFileObjectCreation == null) {
            jMenuFileObjectCreation = new JMenuItem();
            jMenuFileObjectCreation.setText("Object creation");
            jMenuFileObjectCreation.addActionListener(new java.awt.event.ActionListener() { 
                public void actionPerformed(java.awt.event.ActionEvent e) {    
                    createObject();
                }
            });
        }
        return jMenuFileObjectCreation;
    }
    /**
     * 
     */
    protected void createObject() {
        JDObjectCreation j=new JDObjectCreation();
        j.setModal(true);
        j.setVisible(true);
    }
    /**
     * This method initializes jMenuItem	
     * 	
     * @return javax.swing.JMenuItem	
     */    
    private JMenuItem getJMenuObjectManager() {
        if (jMenuObjectManager == null) {
            jMenuObjectManager = new JMenuItem();
            jMenuObjectManager.setText("Object manager");
            jMenuObjectManager.addActionListener(new java.awt.event.ActionListener() { 
                public void actionPerformed(java.awt.event.ActionEvent e) {    
                    manageObjects();
                }
            });
        }
        return jMenuObjectManager;
    }
    /**
     * 
     */
    protected void manageObjects() {
        JDObjectManager j=new JDObjectManager();
        j.setModal(true);
        j.setVisible(true);
    }
    /**
     * This method initializes jMenuItem	
     * 	
     * @return javax.swing.JMenuItem	
     */    
    private JMenuItem getJMenuJUnitAssertions() {
        if (jMenuJUnitAssertions == null) {
            jMenuJUnitAssertions = new JMenuItem();
            jMenuJUnitAssertions.setText("JUnit assertions");
            jMenuJUnitAssertions.addActionListener(new java.awt.event.ActionListener() { 
            	public void actionPerformed(java.awt.event.ActionEvent e) {    
            		openJUnitAssertions();
            	}
            });
        }
        return jMenuJUnitAssertions;
    }
    /**
     * 
     */
    protected void openJUnitAssertions() {
        if (mClass==null) {
            logError("Class not loaded");
            return;
        }
        JDJUnitAssertions j=new JDJUnitAssertions();
        j.setClass(this.mClass);
        j.setLogWindow(this);
        j.setModal(true);
        j.setVisible(true);
    }
    /**
     * This method initializes jScrollPane	
     * 	
     * @return javax.swing.JScrollPane	
     */    
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(getJepMsg());
        }
        return jScrollPane;
    }
    /**
     * This method initializes jEditorPane	
     * 	
     * @return javax.swing.JEditorPane	
     */    
    private JEditorPane getJepMsg() {
        if (jepMsg == null) {
            jepMsg = new JEditorPane();
            jepMsg.setContentType("text/html");
        }
        return jepMsg;
    }
    /**
     * This method initializes jMenuItem	
     * 	
     * @return javax.swing.JMenuItem	
     */    
    private JMenuItem getJMenuFileExit() {
        if (jMenuFileExit == null) {
            jMenuFileExit = new JMenuItem();
            jMenuFileExit.setText("Exit");
            jMenuFileExit.addActionListener(new java.awt.event.ActionListener() { 
                public void actionPerformed(java.awt.event.ActionEvent e) {    
                    dispose();
                }
            });
        }
        return jMenuFileExit;
    }
    /**
     * 
     */
    protected void buildJUnitFile() {
        try {
            if (mClass.getTestTemplates()==null)
                this.buildTemplates();
            TCMutatorSystem mutator=new TCMutatorSystem(this.mClass.getTestTemplates());
            Vector templates=mutator.buildMutants();
            for (int i=0; i<templates.size(); i++)
                this.mClass.add((TestTemplate) templates.get(i));            
            Date antes=new Date();
            int[] numberOfTestCases=new int[1];
            String sessionPath=this.mClass.getTestCases(this.algorithm, TestMethod.JUNIT, numberOfTestCases);
            Date despues=new Date();
            String msg=numberOfTestCases[0] + " JUnit test cases generated in " + 
            	((float) (despues.getTime()-antes.getTime())/1000) + " seconds";
            log(msg);
            showJUnitDialog(numberOfTestCases, TestMethod.JUNIT, sessionPath, null, null);
        }
        catch (NullPointerException ex) 
        {
        	ex.printStackTrace();
            this.logError("Have you given values to the parameters?");
        }	    
        catch (IOException ex) 
        {
            logError("Error creating the file: " + ex.toString());
        }
        catch (Exception ex) 
        {
            logError("Error: " + ex.toString());
        }            
    }
    
    private void showStatesDialog(int[] numberOfTestCases, String sessionPath, String cut) throws IOException, ClassNotFoundException, FileNotFoundException, Exception {
        JDListOfStatesMethods junit=new JDListOfStatesMethods();
        junit.setParentWindow(this);
        junit.setTestCases(sessionPath, numberOfTestCases[0]);
        junit.setModal(true);
        junit.setVisible(true);
        boolean compileFile=junit.getCompileFile();
        this.saveStatesFile(numberOfTestCases, sessionPath, compileFile, cut);
    }
    
    private void saveStatesFile(int[] numberOfTestCases, String junitSessionPath, boolean compileJUnitFile, String cutName) throws FileNotFoundException, Exception, IOException {
        if (mClass==null && cutName!=null) {
            setClass(new Interface(cutName, false));
        }
        int cont=1, lastTestCase=1;
        while (lastTestCase<=numberOfTestCases[0]) {
        	String junitFileName="TestStates" + Auxi.recorta(this.mClass.getName()) + cont;
            JUnitFileForStates testFile=new JUnitFileForStates(junitFileName, mClass);
            testFile.setClass(mClass);
	        testFile.setSessionPath(junitSessionPath, numberOfTestCases[0]);
	        String fileName=Configuration.getInstance().getResultsPath()+testFile.getFileName();
	        FileOutputStream f=new FileOutputStream(fileName);		
	        lastTestCase=testFile.saveTo(f, lastTestCase);
	        f.close();
	        if (compileJUnitFile) {
	        	String path=Configuration.getInstance().getResultsPath();
	            compile(junitFileName, path);
	        }
	        cont++;
        }
        this.log((cont-1) + " JUnit states file(s) generated");
        Auxi.deleteDirectory(junitSessionPath, numberOfTestCases[0]);
    }
    
    private void showJUnitDialog(int[] numberOfTestCases, int sessionType, String sessionPath, Vector<String> sessions, String cut) throws IOException, ClassNotFoundException, FileNotFoundException, Exception {
        JDListOfJUnitMethods junit=new JDListOfJUnitMethods();
        junit.setParentWindow(this);
        junit.setSessionType(sessionType);
        junit.setTestCases(sessionPath, numberOfTestCases[0]);
        junit.setSessions(sessions);
        junit.setModal(true);
        junit.setVisible(true);
        boolean compileFile=junit.getCompileFile();
        if (sessionType!=TestMethod.GROUPED)
            this.saveJUnitFile(numberOfTestCases, sessionType, sessionPath, compileFile, cut);
    }
    /**
     * @param fileName
     */
/*    private void compile(String fileName) {
        Runtime r=Runtime.getRuntime();
        try {
            String compilacion="javac -classpath \"" + 
            	Configuration.getInstance().getJUnitLocation() + ";" +
            	this.getClassPath() + "\" " + fileName;
            log(compilacion);
            Process p=r.exec(compilacion);
            InputStream sis=p.getInputStream();
            InputStream ses=p.getErrorStream();
            int tam=sis.available();
            byte[] b=new byte[tam];
            sis.read(b);
            String msg=new String(b);
            log(msg);
            tam=ses.available();
            b=new byte[tam];
            ses.read(b);
            msg=new String(b);
            logError(msg);
        } catch (IOException e) {
            logError("Error compiling: " + e.toString());
        }  
    }*/
    
    private void compile(String path, String cutName) {
    	me.domain.Compiler compiler=new me.domain.Compiler(this);
    	//compiler.simpleCompile(path, ".", cutName); //ORIGINAL
    	compiler.simpleCompile(path, "", cutName); //MODIFICADO POR ISRAEL
    	System.out.println(path+":"+cutName);
    }
    
    protected String getClassPath() {
        Properties pp=System.getProperties();
	    String cp=pp.getProperty("java.class.path");
	    if (!cp.endsWith(";"))
	      cp+=";";
	    String sbcp=pp.getProperty("sun.boot.class.path");
	    cp+=sbcp;
	    return cp;
    }
    
    /**
     * 
     */
    protected void buildMujavaFile() {
        try {
            if (mClass.getTestTemplates()==null)
                this.buildTemplates();
            TCMutatorSystem mutator=new TCMutatorSystem(this.mClass.getTestTemplates());
            Vector templates=mutator.buildMutants();
            for (int i=0; i<templates.size(); i++)
                this.mClass.add((TestTemplate) templates.get(i));            
            Date antes=new Date();
            int[] numberOfTestCases=new int[1];
            String sessionPath=this.mClass.getTestCases(this.algorithm, TestMethod.MUJAVA, numberOfTestCases);
            Date despues=new Date();
            String msg=numberOfTestCases[0] + " Mujava test cases generated in " + 
            	((float) (despues.getTime()-antes.getTime())/1000) + " seconds";
            log(msg);
            showMujavaDialog(numberOfTestCases, sessionPath, null);
        }
        catch (NullPointerException ex) 
        {
            this.logError("Have you given values to the parameters?");
        }	    
        catch (IOException ex) 
        {
            logError("Error creating the file: " + ex.toString());
        }
        catch (Exception ex) 
        {
            logError("Error: " + ex.toString());
        }    
    }
    private void showMujavaDialog(int[] numberOfTestCases, String sessionPath, String cutName) throws ClassNotFoundException, Exception {
        JDListOfMujavaMethods jd=new JDListOfMujavaMethods();
        jd.setParentWindow(this);
        jd.setTestCases(sessionPath, numberOfTestCases[0]);
        jd.setModal(true);
        jd.setVisible(true);
        boolean compileFiles=jd.getCompile();
        int numberOfFiles=1+(numberOfTestCases[0]/300), cont=1;
        saveMujavaFiles(numberOfTestCases, sessionPath, numberOfFiles, cont, cutName);
        if (compileFiles) {
        	Vector<String> files=new Vector<String>();
        	for (int i=1; i<=numberOfFiles; i++) {
        		String fileName="Mujava" + Auxi.recorta(mClass.getName())+"_" + i;
        		files.add(fileName);
        		String path=Configuration.getInstance().getMujavaRoot() + "testset";
        		System.out.println("path compilar:"+path);
        		this.compile(path, fileName);
        	}
        }
    }
    
    private void saveMujavaFiles(int[] numberOfTestCases, String sessionPath, int numberOfFiles, int cont, String cutName) throws ClassNotFoundException, Exception {
        if (mClass==null && cutName!=null) {
            setClass(new Interface(cutName, false));
        }
        for (int i=1; i<=numberOfFiles; i++) {
            MujavaFile testFile=new MujavaFile("Mujava" + Auxi.recorta(mClass.getName())+"_" + i);
            Vector<TMujavaMethod> casos=new Vector<TMujavaMethod>();
            for (int j=cont; j<=300*i; j++) {
                cont++;
                if (j<=numberOfTestCases[0]) {
                    casos.add(MujavaFile.load(sessionPath+"\\"+j+".mujava"));
                }
            }
            testFile.setMethodList(casos);
            String fileName=Configuration.getInstance().getMujavaRoot()+"testset\\"+testFile.getFileName();
            FileOutputStream f=new FileOutputStream(fileName);	
            String regExp="// Regular expression: " + this.jtfRegularExpression.getText() + "\n";
            regExp+="// Max. length: " + this.jtfMaxLength.getText() + "\n";
            f.write(regExp.getBytes());
            String texto=testFile.toString();
            byte[] ct=texto.getBytes();
            f.write(ct);
            f.close();
        }
        Auxi.deleteDirectory(sessionPath, numberOfTestCases[0]);
        this.log(numberOfFiles + " Mujava files generated (leaved at " + Configuration.getInstance().getMujavaRoot()+"testset\\)");
    }
    /**
     * @param sessionPath
     * @param i
     */

    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#log(java.lang.String)
     */
    public void log(String msg) {
        msg=msg+jepMsg.getText().trim();
        this.jepMsg.setText(msg);
        jepMsg.select(0, 0);
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#parameterSelected(testooj3.domain.TParameter)
     */
    public void parameterSelected(TParameter p) {
        // TODO Auto-generated method stub
        
    }
    /**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */    
	private JMenu getJMenuExternalTools() {
		if (jMenuExternalTools == null) {
			jMenuExternalTools = new JMenu();
			jMenuExternalTools.setText("External tools");
			jMenuExternalTools.add(getJmJUnit());
			jMenuExternalTools.add(getJMenuMujava());
			jMenuExternalTools.add(getJmMutantExecution());
		}
		return jMenuExternalTools;
	}
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */    
	private JMenuItem getJmJUnit() {
		if (jmJUnit == null) {
			jmJUnit = new JMenuItem();
			jmJUnit.setText("JUnit");
			jmJUnit.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					launchJUnit();
				}
			});
		}
		return jmJUnit;
	}
    /**
     * 
     */
    protected void launchJUnit() {
        JLaunchJUnit j=new JLaunchJUnit();
        j.setVisible(true);
    }
    /**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */    
	private JMenu getJMenuMujava() {
		if (jMenuMujava == null) {
			jMenuMujava = new JMenu();
			jMenuMujava.setText("MuJava");
			jMenuMujava.add(getJMenuMujavaCompilation());
			jMenuMujava.add(getJMenuMutantGeneration());
			jMenuMujava.add(getJMenuMutantExecution());
		}
		return jMenuMujava;
	}
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */    
	private JMenuItem getJMenuMujavaCompilation() {
		if (jMenuMujavaCompilation == null) {
			jMenuMujavaCompilation = new JMenuItem();
			jMenuMujavaCompilation.setText("Source code compilation");
			jMenuMujavaCompilation.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					mujavaSourceCodeCompilation();
				}
			});
		}
		return jMenuMujavaCompilation;
	}
	/**
     * 
     */
    protected void mujavaSourceCodeCompilation() {
        String mr=Configuration.getInstance().getMujavaRoot();
        String cmd="java -cp CLASSPATH=%CLASSPATH%;" +
        	mr+"jmutation.jar;" + mr + "openjavaHelper.jar " +
        	"jmutation.compileSrc";
        File workingDirectory=new File(Configuration.getInstance().getMujavaRoot());
        this.execute(cmd, workingDirectory);
    }
    /**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */    
	private JMenuItem getJMenuMutantGeneration() {
		if (jMenuMutantGeneration == null) {
			jMenuMutantGeneration = new JMenuItem();
			jMenuMutantGeneration.setText("Mutant generation");
			jMenuMutantGeneration.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					mutantGeneration();
				}
			});
		}
		return jMenuMutantGeneration;
	}
	/**
     * 
     */
    protected void mutantGeneration() {
        String mr=Configuration.getInstance().getMujavaRoot();
        String cmd="java -cp CLASSPATH=%CLASSPATH%;" +
        	mr+"jmutation.jar;" + mr + "openjavaHelper.jar " +
        	"jmutation.gui.GenMutantsMain";
        File workingDirectory=new File(Configuration.getInstance().getMujavaRoot());
        this.execute(cmd, workingDirectory);
    }
    /**
     * 
     */
    protected void mutantExecution() {
        String mr=Configuration.getInstance().getMujavaRoot();
        String cmd="java -cp CLASSPATH=%CLASSPATH%;" +
        	mr+"jmutation.jar;" + mr + "openjavaHelper.jar " +
        	"jmutation.gui.RunTestMain";
        File workingDirectory=new File(Configuration.getInstance().getMujavaRoot());
        this.execute(cmd, workingDirectory);
    }
    /**
     * @param workingDirectory
     * @param string
     */
    private void execute(String cmd, File workingDirectory) {
        Runtime r=Runtime.getRuntime();
        try {
            log(cmd);
            InputStream os=r.exec(cmd, null, workingDirectory).getErrorStream();
            int tam=os.available();
            byte[] b=new byte[tam];
            os.read(b);
            String msg=new String(b);
            log(msg);
        } catch (IOException e) {
            logError("Error executing external command: " + e.toString());
        }         
    }
	/**
     * 
     */
    protected void buildMuJavaAndJUnitFiles() {
        try {
            //if (mClass.getTestTemplates()==null)
                this.buildTemplates();
            TCMutatorSystem mutator=new TCMutatorSystem(this.mClass.getTestTemplates());
            Vector templates=mutator.buildMutants();
            for (int i=0; i<templates.size(); i++)
                this.mClass.add((TestTemplate) templates.get(i));
            Date antes=new Date();
            int[] numberOfTestCases=new int[1];
            String junitSessionPath=this.mClass.getTestCases(this.algorithm, TestMethod.JUNIT, numberOfTestCases);
            String mujavaSessionPath=this.mClass.getTestCases(this.algorithm, TestMethod.MUJAVA, numberOfTestCases);
            Date despues=new Date();
            String msg=numberOfTestCases[0] + " JUnit and " +
            	numberOfTestCases[0] + " Mujava test cases generated in " + 
            	((float) (despues.getTime()-antes.getTime())/1000) + " seconds";
            log(msg);
            JDJUnitAndMujavaMethods j=new JDJUnitAndMujavaMethods();
            j.setParentWindow(this);
            j.setTestCases(junitSessionPath, mujavaSessionPath, numberOfTestCases[0]);
            j.setModal(true);
            j.setVisible(true);
            boolean compileJUnitFile=j.getCompileFile();
            saveJUnitFile(numberOfTestCases, TestMethod.JUNIT, junitSessionPath, compileJUnitFile, null);
            int numberOfFiles=1+(numberOfTestCases[0]/300), cont=1;
            saveMujavaFiles(numberOfTestCases, mujavaSessionPath, numberOfFiles, cont, null);
        }
        catch (NullPointerException ex) 
        {
            this.logError("Have you given values to the parameters?");
        }	    
        catch (IOException ex) 
        {
            logError("Error creating the file: " + ex.toString());
        }
        catch (Exception ex) 
        {
            logError("Error: " + ex.toString());
        }                
    }
    
    private void saveJUnitFile(int[] numberOfTestCases, int sessionType, String junitSessionPath, boolean compileJUnitFile, String cutName) throws FileNotFoundException, Exception, IOException {
        if (mClass==null && cutName!=null) {
            setClass(new Interface(cutName, false));
        }
        int cont=1, lastTestCase=1;
        JUnitFile testFile;
        while (lastTestCase<=numberOfTestCases[0]) {
        	String junitFileName="Test" + Auxi.recorta(this.mClass.getName()) + cont;
            testFile=new JUnitFile(junitFileName, mClass);
            testFile.setSessionType(sessionType);
	        testFile.setSessionPath(junitSessionPath, numberOfTestCases[0]);
	        String fileName=Configuration.getInstance().getResultsPath()+testFile.getFileName();
	        FileOutputStream f=new FileOutputStream(fileName);	
	        String regExp="// Regular expression: " + this.jtfRegularExpression.getText() + "\n";
            regExp+="// Max. length: " + this.jtfMaxLength.getText() + "\n";
            f.write(regExp.getBytes());
	        lastTestCase=testFile.saveTo(f, lastTestCase);
	        f.close();
	        if (compileJUnitFile) {
	        	String path=Configuration.getInstance().getResultsPath();
	            compile(path, fileName);
	        }
	        cont++;
        }
        this.log((cont-1) + " JUnit file(s) generated");
        Auxi.deleteDirectory(junitSessionPath, numberOfTestCases[0]);
    }
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */    
	private JMenuItem getJMenuRebuildTestFile() {
		if (jMenuRebuildTestFile == null) {
			jMenuRebuildTestFile = new JMenuItem();
			jMenuRebuildTestFile.setText("Rebuild test file");
			jMenuRebuildTestFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					rebuildTestFile();
				}
			});
		}
		return jMenuRebuildTestFile;
	}
    /**
     * 
     */
    protected void rebuildTestFile() {
        try {
            JDOpenFile j=new JDOpenFile(this);
            j.setPath(Configuration.getInstance().getTempPath());
            j.setFileType("session");
            j.load();
            j.setModal(true);
            j.setVisible(true);
            String selectedSession=j.getSelectedFile();
            Vector tmp=j.getSessions();
            if (selectedSession==null) return;
            selectedSession=Configuration.getInstance().getTempPath() + selectedSession;
            Vector<String> sessions=new Vector<String>();
            for (int i=0; i<tmp.size(); i++) {
                String session=Configuration.getInstance().getTempPath() + tmp.get(i).toString();
                sessions.add(session);
            }
            log(selectedSession);
            try {
                FileInputStream f=new FileInputStream(selectedSession);
                Properties pp = new Properties();
                pp.load(f);
                f.close();
                String sessionType=pp.getProperty("SessionType");
                String sNumberOfTC=pp.getProperty("NumberOfTestCases");
                String cut=pp.getProperty("CUT");
                if (sessionType!=null && sessionType.equals("junit")) {
                    int[] numberOfTestCases={Integer.parseInt(sNumberOfTC)};
                    selectedSession=selectedSession.substring(0, selectedSession.lastIndexOf("."));
                    this.showJUnitDialog(numberOfTestCases, TestMethod.JUNIT, selectedSession, sessions, cut);                    
                } else if (sessionType!=null && sessionType.equals("mujava")) {
                    int[] numberOfTestCases={Integer.parseInt(sNumberOfTC)};
                    selectedSession=selectedSession.substring(0, selectedSession.lastIndexOf("."));
                    this.showMujavaDialog(numberOfTestCases, selectedSession, cut);
                } else if (sessionType!=null && sessionType.equals("states")) {
                    int[] numberOfTestCases={Integer.parseInt(sNumberOfTC)};
                    selectedSession=selectedSession.substring(0, selectedSession.lastIndexOf("."));
                    this.showStatesDialog(numberOfTestCases, selectedSession, cut);
                } else if (sessionType!=null && sessionType.equals("mutjunit")) {
                    int[] numberOfTestCases={Integer.parseInt(sNumberOfTC)};
                    selectedSession=selectedSession.substring(0, selectedSession.lastIndexOf("."));
                    this.showJUnitDialog(numberOfTestCases, TestMethod.MUT_JUNIT, selectedSession, sessions, cut);
                } else if (sessionType!=null && sessionType.equals("grouped")) {
                    int[] numberOfTestCases={Integer.parseInt(sNumberOfTC)};
                    selectedSession=selectedSession.substring(0, selectedSession.lastIndexOf("."));
                    this.showJUnitDialog(numberOfTestCases, TestMethod.GROUPED, selectedSession, sessions, cut);
                    this.saveGroupedTestFile(numberOfTestCases, selectedSession, cut);
                } else 
                    logError("File type not supported for rebuilding");
            } catch (Exception e) {
                logError(e.toString());
            }
        } catch (Exception e) {
            logError(e.toString());
        }
    }
	

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */    
	private JMenuItem getJMenuMutantExecution() {
		if (jMenuMutantExecution == null) {
			jMenuMutantExecution = new JMenuItem();
			jMenuMutantExecution.setText("Execute like-MuJava");
			jMenuMutantExecution.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					excuteMutantsWithin();
				}
			});
		}
		return jMenuMutantExecution;
	}
    /**
     * 
     */
    protected void excuteMutantsWithin() {
        JDTestCasesExecutor j;
        try {
            j = new JDTestCasesExecutor();
            j.setParentWindow(this);
            j.setVisible(true);
        } catch (Exception e) {
            this.logError(e.toString());
        }
    }
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */    
	private JMenuItem getJMenuFileStatesDefinition() {
		if (jMenuFileStatesDefinition == null) {
			jMenuFileStatesDefinition = new JMenuItem();
			jMenuFileStatesDefinition.setText("States definition");
			jMenuFileStatesDefinition.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					statesDefinition();
				}
			});
		}
		return jMenuFileStatesDefinition;
	}
    /**
     * 
     */
    protected void statesDefinition() {
    	
        if (mClass==null) {
            this.logError("Class not loaded");
        }else{
	        JDStatesDefinition j=new JDStatesDefinition();
	        j.setClass(this.mClass);
	        j.setParentWindow(this);
	        j.setTemplatesWindow(this);
	        j.setModal(true);
	        j.setVisible(true);
        }
    }
    
    public void stateSelected(State selectedState) {
        // TODO Auto-generated method stub
        
    }
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbBuildFile() {
		if (jbBuildFile == null) {
			jbBuildFile = new JButton();
			jbBuildFile.setText("Build file");
			jbBuildFile.setVisible(true);
			jbBuildFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					buildFile();
				}
			});
		}
		return jbBuildFile;
	}
    protected void buildFile() {
		if (this.jrbBuildJunitFile.isSelected())
			this.buildJUnitFile();
		else if (this.jrbBuildMuJavaFile.isSelected())
			this.buildMujavaFile();
		else if (this.jrbBuildBothFiles.isSelected())
			this.buildMuJavaAndJUnitFiles();
		else if (this.jrbBuildStatesFile.isSelected())
			this.buildStatesFile();
		else logError("Select the file type to be generated");
	}
	/**
     * 
     */
    protected void buildStatesFile() {
        try {
            if (mClass.getTestTemplates()==null)
                this.buildTemplates();
            Date antes=new Date();
            int[] numberOfTestCases=new int[1];
            String sessionPath=this.mClass.getTestCases(this.algorithm, TestMethod.STATES_JUNIT, numberOfTestCases);
            Date despues=new Date();
            String msg=numberOfTestCases[0] + " JUnit states test cases generated in " + 
            	((float) (despues.getTime()-antes.getTime())/1000) + " seconds";
            log(msg);
            this.showStatesDialog(numberOfTestCases, sessionPath, null);
        }
        catch (NullPointerException ex) 
        {
            this.logError("Have you given values to the parameters?");
        }	    
        catch (IOException ex) 
        {
            logError("Error creating the file: " + ex.toString());
        }
        catch (Exception ex) 
        {
            logError("Error: " + ex.toString());
        }       
    }
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */    
	private JMenuItem getJmMutantExecution() {
		if (jmMutantExecution == null) {
			jmMutantExecution = new JMenuItem();
			jmMutantExecution.setText("Mutant execution");
			jmMutantExecution.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    excuteMutantsWithin();
				}
			});
		}
		return jmMutantExecution;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbMutantsJUnit() {
		if (jbMutantsJUnit == null) {
			jbMutantsJUnit = new JButton();
			jbMutantsJUnit.setText("JUnit for mutation");
			jbMutantsJUnit.setVisible(true);
			jbMutantsJUnit.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					buildJUnitForMutation();
				}
			});
		}
		return jbMutantsJUnit;
	}
    /**
     * 
     */
    protected void buildJUnitForMutation() {
        try {
            if (mClass.getTestTemplates()==null)
                this.buildTemplates();
            Date antes=new Date();
            int[] numberOfTestCases=new int[1];
            String sessionPath=this.mClass.getTestCases(this.algorithm, TestMethod.MUT_JUNIT, numberOfTestCases);
            Date despues=new Date();
            String msg=numberOfTestCases[0] + " JUnit test cases generated in " + 
            	((float) (despues.getTime()-antes.getTime())/1000) + " seconds";
            log(msg);
            showJUnitDialog(numberOfTestCases, TestMethod.MUT_JUNIT, sessionPath, null, null);
        }
        catch (NullPointerException ex) 
        {
            this.logError("Have you given values to the parameters?");
        }	    
        catch (IOException ex) 
        {
            logError("Error creating the file: " + ex.toString());
        }
        catch (Exception ex) 
        {
            logError("Error: " + ex.toString());
        } 
    }
	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */    
	private JMenu getJMenuTCMutation() {
		if (jMenuTCMutation == null) {
			jMenuTCMutation = new JMenu();
			jMenuTCMutation.setText("Test case mutation");
			jMenuTCMutation.add(getJmTCMutationOperators());
		}
		return jMenuTCMutation;
	}
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */    
	private JMenuItem getJmTCMutationOperators() {
		if (jmTCMutationOperators == null) {
			jmTCMutationOperators = new JMenuItem();
			jmTCMutationOperators.setText("Operators");
			jmTCMutationOperators.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					mutationOperatorsSelection();
				}
			});
		}
		return jmTCMutationOperators;
	}
    /**
     * 
     */
    protected void mutationOperatorsSelection() {
        JDOperators j=new JDOperators();
        j.loadOperators(TCMutationOperators.getInstance());
        j.setModal(true);
        j.setVisible(true);
    }
	/**
     * 
     */
    protected void testCaseMutation() {
        JDOperators j=new JDOperators();
        j.loadOperators(TCMutationOperators.getInstance());
        j.setModal(true);
        j.setVisible(true);
        try {
            //if (mClass.getTestTemplates()==null)
                this.buildTemplates();
            Date antes=new Date();
            Vector testTemplates=this.mClass.getTestTemplates();
            TCMutatorSystem mutator=new TCMutatorSystem(testTemplates);
            Vector templates=mutator.buildMutants();
            for (int i=0; i<templates.size(); i++) {
                TestTemplate t=(TestTemplate) templates.get(i);
                mClass.add(t);
            }
            int[] numberOfTestCases=new int[1];
            String sessionPath=this.mClass.getGroupedTestCases(numberOfTestCases);
            Date despues=new Date();
            this.testTemplatesPanel.setTemplates(templates);
            String msg=numberOfTestCases[0] + " grouped JUnit test cases generated in " + 
        		((float) (despues.getTime()-antes.getTime())/1000) + " seconds";
            log(msg);            
            showJUnitDialog(numberOfTestCases, TestMethod.GROUPED, sessionPath, null, null);
            this.saveGroupedTestFile(numberOfTestCases, sessionPath, mClass.getName());
        }
        catch (NullPointerException ex) 
        {
            this.logError("Have you given values to the parameters?");
        }	    
        catch (Exception ex) 
        {
            logError("Error: " + ex.toString());
        }            
    }
    
    private void saveGroupedTestFile(int[] numberOfTestCases, String sessionPath, String cutName) throws FileNotFoundException, Exception, IOException {
        if (mClass==null && cutName!=null) {
            setClass(new Interface(cutName, false));
        }
        int cont=1, lastTestCase=1;
        TGroupedFile testFile;
        while (lastTestCase<=numberOfTestCases[0]) {
            testFile=new TGroupedFile("Grouped" + Auxi.recorta(this.mClass.getName()) + cont, mClass);
	        testFile.setSessionPath(sessionPath, numberOfTestCases[0]);
	        String fileName=Configuration.getInstance().getResultsPath()+testFile.getFileName();
	        FileOutputStream f=new FileOutputStream(fileName);		
	        lastTestCase=testFile.saveTo(f, lastTestCase);
	        f.close();
	        cont++;
        }
        this.log((cont-1) + " grouped file(s) generated");
        Auxi.deleteDirectory(sessionPath, numberOfTestCases[0]);
    }
	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */    
	private JMenu getJMenuAlgorithms() {
		if (jMenuAlgorithms == null) {
			jMenuAlgorithms = new JMenu();
			jMenuAlgorithms.setText("Algorithms");
			jMenuAlgorithms.add(getJmiSelectAlgorithm());
			jMenuAlgorithms.add(getJCheckBoxMenuRandomTesting());
		}
		return jMenuAlgorithms;
	}
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */    
	private JMenuItem getJmiSelectAlgorithm() {
		if (jmiSelectAlgorithm == null) {
			jmiSelectAlgorithm = new JMenuItem();
			jmiSelectAlgorithm.setText("Select algorithm...");
			jmiSelectAlgorithm.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					selectAlgorithm();
				}
			});
		}
		return jmiSelectAlgorithm;
	}
    /**
     * 
     */
    protected void selectAlgorithm() {
        JDAlgorithms j=new JDAlgorithms();
        j.setModal(true);
        j.setVisible(true);
        this.algorithm=j.getAlgorithm();
    }

    /**
     *  
     * @uml.property name="algorithm"
     * @uml.associationEnd inverse="jFJUnit:testooj3.domain.algorithms.Algorithm" multiplicity="(0 1)"
     * 
     */
    private Algorithm algorithm;

    /**
     *  
     * @uml.property name="algorithm"
     * 
     */
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    /**
     *  
     * @uml.property name="algorithm"
     * 
     */
    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     *  
     * @uml.property name="mClass"
     * @uml.associationEnd inverse="jFJUnit:testooj3.domain.TClass" multiplicity="(0 1)"
     * 
     */
    private Interface mClass;
	private JMenu jMenuCompatibility = null;
	private JMenuItem jMenuInterfaceCompatibility = null;
	private JCheckBoxMenuItem jCheckBoxMenuRandomTesting = null;
	private JMenu jMenuStates = null;
	private JMenuItem jMenuItemReadStateFile = null;
	private JMenu jMenuFaultPronenness = null;
	private JMenuItem jMenuItemFindFaultProneClasses = null;
	private JPanel jPanelCenter = null;
	private JRadioButton jrbBuildJunitFile = null;
	private JRadioButton jrbBuildMuJavaFile = null;
	private JRadioButton jrbBuildBothFiles = null;
	private JRadioButton jrbBuildStatesFile = null;
	private JButton classpath = null;
	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuCompatibility() {
		if (jMenuCompatibility == null) {
			jMenuCompatibility = new JMenu();
			jMenuCompatibility.setText("Compatibility");
			jMenuCompatibility.add(getJMenuInterfaceCompatibility());
		}
		return jMenuCompatibility;
	}
	/**
	 * This method initializes jMenuInterfaceCompatibility	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuInterfaceCompatibility() {
		if (jMenuInterfaceCompatibility == null) {
			jMenuInterfaceCompatibility = new JMenuItem();
			jMenuInterfaceCompatibility.setText("Interfaces compatibility");
			jMenuInterfaceCompatibility
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							compatibility();
						}
					});
		}
		return jMenuInterfaceCompatibility;
	}
	protected void compatibility() {
		JFCompatibility j=new JFCompatibility();
		j.setParentWindow(this);
		if (mClass!=null)
			j.setClassName(this.mClass.getName());
		j.setVisible(true);
	}
	/**
	 * This method initializes jCheckBoxMenuRandomTesting	
	 * 	
	 * @return javax.swing.JCheckBoxMenuItem	
	 */
	private JCheckBoxMenuItem getJCheckBoxMenuRandomTesting() {
		if (jCheckBoxMenuRandomTesting == null) {
			jCheckBoxMenuRandomTesting = new JCheckBoxMenuItem();
			jCheckBoxMenuRandomTesting.setText("Random testing");
			jCheckBoxMenuRandomTesting.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					setRandomTesting();
				}
			});
		}
		return jCheckBoxMenuRandomTesting;
	}
	protected void setRandomTesting() {
		boolean b=this.jCheckBoxMenuRandomTesting.isSelected();
		Configuration.getInstance().setRandomTesting(b);
		log("Random testing " + (b ? "" : "un") + "selected");
	}
	/**
	 * This method initializes jMenuStates	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuStates() {
		if (jMenuStates == null) {
			jMenuStates = new JMenu();
			jMenuStates.setText("States");
			jMenuStates.add(getJMenuItemReadStateFile());
		}
		return jMenuStates;
	}
	/**
	 * This method initializes jMenuItemReadStateFile	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemReadStateFile() {
		if (jMenuItemReadStateFile == null) {
			jMenuItemReadStateFile = new JMenuItem();
			jMenuItemReadStateFile.setText("Open file");
			jMenuItemReadStateFile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFStates j=new JFStates();
					j.setVisible(true);
				}
			});
		}
		return jMenuItemReadStateFile;
	}
	public void setTemplates(Vector<TestTemplate> templates) {
		this.testTemplatesPanel.setTemplates(templates);
	}
	/**
	 * This method initializes jMenuFaultPronenness	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuFaultPronenness() {
		if (jMenuFaultPronenness == null) {
			jMenuFaultPronenness = new JMenu();
			jMenuFaultPronenness.setText("Fault pronenness");
			jMenuFaultPronenness.add(getJMenuItemFindFaultProneClasses());
		}
		return jMenuFaultPronenness;
	}
	/**
	 * This method initializes jMenuItemFindFaultProneClasses	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemFindFaultProneClasses() {
		if (jMenuItemFindFaultProneClasses == null) {
			jMenuItemFindFaultProneClasses = new JMenuItem();
			jMenuItemFindFaultProneClasses.setText("Find fault-prone classes");
			jMenuItemFindFaultProneClasses
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							findFaultProneClasses();
						}
					});
		}
		return jMenuItemFindFaultProneClasses;
	}
	
	protected void findFaultProneClasses() {
		JFFaultProne j=new JFFaultProne(this.classSelectorPanel.getClassPath());
		j.setVisible(true);
	}
	/**
	 * This method initializes jPanelCenter	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelCenter() {
		if (jPanelCenter == null) {
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 0;
			gridBagConstraints41.anchor = GridBagConstraints.WEST;
			gridBagConstraints41.gridy = 8;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 0;
			gridBagConstraints31.anchor = GridBagConstraints.WEST;
			gridBagConstraints31.gridy = 7;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.anchor = GridBagConstraints.WEST;
			gridBagConstraints21.gridy = 6;
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridx = 0;
			gridBagConstraints17.anchor = GridBagConstraints.WEST;
			gridBagConstraints17.gridy = 5;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.gridwidth = 2;
			gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.ipadx = 0;
			gridBagConstraints9.insets = new Insets(2, 4, 2, 4);
			gridBagConstraints9.gridy = 9;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridwidth = 2;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.ipadx = 0;
			gridBagConstraints4.insets = new Insets(2, 4, 2, 4);
			gridBagConstraints4.gridy = 4;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridwidth = 2;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.anchor = GridBagConstraints.EAST;
			gridBagConstraints2.gridx = 1;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.insets = new Insets(0, 2, 0, 0);
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.insets = new Insets(1, 2, 0, 0);
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridy = 0;
			jPanelCenter = new JPanel();
			jPanelCenter.setLayout(new GridBagLayout());
			jPanelCenter.add(jLabel, gridBagConstraints);
			jPanelCenter.add(getJtfRegularExpression(), gridBagConstraints1);
			jPanelCenter.add(jlMaxLength, gridBagConstraints11);
			jPanelCenter.add(getJtfMaxLength(), gridBagConstraints2);
			jPanelCenter.add(getJSlider(), gridBagConstraints3);
			jPanelCenter.add(getJbGetTemplates(), gridBagConstraints4);
			jPanelCenter.add(getJbBuildFile(), gridBagConstraints9);
			jPanelCenter.add(getJrbBuildJunitFile(), gridBagConstraints17);
			jPanelCenter.add(getJrbBuildMuJavaFile(), gridBagConstraints21);
			jPanelCenter.add(getJrbBuildBothFiles(), gridBagConstraints31);
			jPanelCenter.add(getJrbBuildStatesFile(), gridBagConstraints41);
		}
		return jPanelCenter;
	}
	/**
	 * This method initializes jrbBuildJunitFile	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbBuildJunitFile() {
		if (jrbBuildJunitFile == null) {
			jrbBuildJunitFile = new JRadioButton();
			jrbBuildJunitFile.setText("Build JUnit file");
			jrbBuildJunitFile.setSelected(true);
		}
		return jrbBuildJunitFile;
	}
	/**
	 * This method initializes jrbBuildMuJavaFile	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbBuildMuJavaFile() {
		if (jrbBuildMuJavaFile == null) {
			jrbBuildMuJavaFile = new JRadioButton();
			jrbBuildMuJavaFile.setText("Build MuJava file");
		}
		return jrbBuildMuJavaFile;
	}
	/**
	 * This method initializes jrbBuildBothFiles	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbBuildBothFiles() {
		if (jrbBuildBothFiles == null) {
			jrbBuildBothFiles = new JRadioButton();
			jrbBuildBothFiles.setText("Build JUnit & MuJava");
		}
		return jrbBuildBothFiles;
	}
	/**
	 * This method initializes jrbBuildStatesFile	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbBuildStatesFile() {
		if (jrbBuildStatesFile == null) {
			jrbBuildStatesFile = new JRadioButton();
			jrbBuildStatesFile.setText("Build states file");
		}
		return jrbBuildStatesFile;
	}
	/**
	 * This method initializes classpath	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClasspath() {
		if (classpath == null) {
			classpath = new JButton();
			classpath.setText("Click for Classpath");
			classpath.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					System.out.println("java.class.path: \n"+System.getProperty("java.class.path")); // TODO Auto-generated Event stub actionPerformed()
				}
			});
			
		}
		return classpath;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"


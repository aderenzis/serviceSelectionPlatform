package testooj3.gui.guitesting;

import java.awt.Font;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TField;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.TestMethod;
import testooj3.domain.TestTemplate;
import testooj3.domain.algorithms.Algorithm;
import testooj3.domain.algorithms.AllCombinationsElegant;
import testooj3.domain.algorithms.EachChoice;
import testooj3.domain.algorithms.PairWiseStepsFirstCombination;
import testooj3.domain.states.State;
import testooj3.domain.testfiles.junit.JUnitFile;
import testooj3.gui.JDListOfJUnitMethods;
import testooj3.gui.JDSetup;
import testooj3.gui.components.ClassSelectorPanel;
import testooj3.gui.components.ClassStructurePanel;
import testooj3.gui.components.ILogWindow;
import testooj3.gui.components.IMainWindow;
import testooj3.persistence.Agente;

/**
 * @author  andres
 */
public class JFGuiTesting extends JFrame implements IMainWindow, ILogWindow {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private ClassSelectorPanel classSelectorPanel = null;
	private ClassStructurePanel classStructurePanel = null;
	private GuiTClass mClass;  //  @jve:decl-index=0:
	private TField selectedField;  //  @jve:decl-index=0:
	private JTabbedPane jtpSteps = null;
	private JPStep1 jpStep1 = null;
	private JButton jbCreateATestCase = null;

	private Vector<GuiFixture> fixtures;
	private Vector<GuiSetup> setUps;
	private JList jlFixtures = null;
	private JScrollPane jspFixtures = null;
	private DefaultListModel modeloFixtures=null;
	private JButton jbDuplicate = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jmAlgorithms = null;
	private JMenu jmFile = null;
	private JMenuItem jmSetup = null;
	private JRadioButtonMenuItem jrbPairWise = null;
	private JRadioButtonMenuItem jrbEachChoice = null;
	private JRadioButtonMenuItem jrbAllCombinations = null;
	private JMenu jmGenerateScenarios = null;
	private JMenuItem jmGenerateForSelected = null;
	private JMenuItem jmGenerateForAll = null;
	private Algorithm algorithm;
	private JMenuItem jmSave = null;
	
	/**
	 * This is the default constructor
	 */
	public JFGuiTesting() {
		super();
		initialize();
		ButtonGroup g=new ButtonGroup();
		g.add(this.jrbAllCombinations);
		g.add(this.jrbPairWise);
		g.add(this.jrbEachChoice);
		this.fixtures=new Vector<GuiFixture>();
		this.setUps=new Vector<GuiSetup>();
		this.jpStep1.setParentWindow(this);
		this.jtpSteps.setTitleAt(0, "Fixtures");
		this.classSelectorPanel.setParentWindow(this);
		this.classStructurePanel.setParentWindow(this);
		this.modeloFixtures=new DefaultListModel();
		this.jlFixtures.setModel(this.modeloFixtures);
		setAlgorithm(new GuiPairWise());
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(1040, 579);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("testooj - GUI testing");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getClassSelectorPanel(), null);
			jContentPane.add(getClassStructurePanel(), null);
			jContentPane.add(getJtpSteps(), null);
			jContentPane.add(getJbCreateATestCase(), null);
			jContentPane.add(getJspFixtures(), null);
			jContentPane.add(getJbDuplicate(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes classSelectorPanel	
	 * 	
	 * @return testooj3.gui.components.ClassSelectorPanel	
	 */
	private ClassSelectorPanel getClassSelectorPanel() {
		if (classSelectorPanel == null) {
			classSelectorPanel = new ClassSelectorPanel();
			classSelectorPanel.setBounds(new Rectangle(0, 0, 668, 122));
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
			classStructurePanel.setBounds(new Rectangle(6, 133, 260, 355));
		}
		return classStructurePanel;
	}

	public void log(String msg) {
		System.out.println(msg);		
	}

	public void logError(String msg) {
		System.err.println(msg);
	}

	public void methodSelected(TOperation operation) {
		//this.jepFixtures.setText(this.jepFixtures.getText() + "\n" + operation.getSignature());
	}

	public void parameterSelected(TParameter p) {
		// Intencionadamente vacía.
	}

    public void setClass(Interface c) {
        this.mClass=new GuiTClass(c, false); 
        this.jpStep1.setClass(c);
    }

	public void showMembers(Interface c) {
		this.classStructurePanel.setClass(c);
	}

	public void stateSelected(State selectedState) {
		// Intencionadamente vacía.
	}
	
	public void fieldSelected(TField selectedField) {
		this.selectedField=selectedField;
		this.jpStep1.fieldSelected(this.selectedField);
	}

	/**
	 * This method initializes jtpSteps	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJtpSteps() {
		if (jtpSteps == null) {
			jtpSteps = new JTabbedPane();
			jtpSteps.setBounds(new Rectangle(271, 129, 755, 404));
			jtpSteps.addTab(null, null, getJpStep1(), null);
		}
		return jtpSteps;
	}

	/**
	 * This method initializes jpStep1	
	 * 	
	 * @return testooj3.gui.guitesting.JPStep1	
	 */
	private JPStep1 getJpStep1() {
		if (jpStep1 == null) {
			jpStep1 = new JPStep1();
		}
		return jpStep1;
	}

	/**
	 * This method initializes jbCreateATestCase	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbCreateATestCase() {
		if (jbCreateATestCase == null) {
			jbCreateATestCase = new JButton();
			jbCreateATestCase.setBounds(new Rectangle(675, 72, 175, 22));
			jbCreateATestCase.setText("Create a new test case");
			jbCreateATestCase.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					createTestCase();
				}
			});
		}
		return jbCreateATestCase;
	}

	protected void createTestCase() {
		if (this.mClass==null)
			return;
		JPInteractionDiagram j=new JPInteractionDiagram(this);
		j.setClass(this.mClass);
		j.setName("Scenario " + this.jtpSteps.getTabCount());
		this.jtpSteps.add(j);
	}

	public boolean containsFixture(TField field) {
		return this.fixtures.contains(field);
	}

	public void addFixture(GuiFixture gf) {
		this.fixtures.add(gf);
		this.modeloFixtures.addElement(gf.getName());
	}

	public boolean containsSetUp(TField field) {
		return this.setUps.contains(field);
	}

	public void addSetUp(GuiSetup gst) {
		this.setUps.add(gst);
	}

	public Interface getMClass() {
		return this.mClass;
	}

	/**
	 * This method initializes jlFixtures	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJlFixtures() {
		if (jlFixtures == null) {
			jlFixtures = new JList();
			jlFixtures.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			jlFixtures.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount()==2)
						addInstance();
				}
			});
		}
		return jlFixtures;
	}

	protected void addInstance() {
		if (this.jlFixtures.getSelectedIndex()==-1)
			return;
		String fixtureName=jlFixtures.getSelectedValue().toString();
		GuiFixture fixture = this.findFixture(fixtureName);
		if (fixture==null) 
			return;
		if (this.jtpSteps.getSelectedIndex()==0)
			return;
		JPInteractionDiagram selectedTestCasePanel=(JPInteractionDiagram) this.jtpSteps.getSelectedComponent();
		selectedTestCasePanel.addInstance(fixture);
	}

	public GuiFixture findFixture(String fixtureName) {
		for(GuiFixture gf : this.fixtures)
			if (gf.getName().equals(fixtureName))
				return gf;
		return null;
	}

	/**
	 * This method initializes jspFixtures	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspFixtures() {
		if (jspFixtures == null) {
			jspFixtures = new JScrollPane();
			jspFixtures.setBounds(new Rectangle(673, 6, 194, 63));
			jspFixtures.setViewportView(getJlFixtures());
		}
		return jspFixtures;
	}

	/**
	 * This method initializes jbDuplicate	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbDuplicate() {
		if (jbDuplicate == null) {
			jbDuplicate = new JButton();
			jbDuplicate.setBounds(new Rectangle(675, 100, 175, 23));
			jbDuplicate.setText("Duplicate test case");
		}
		return jbDuplicate;
	}

	public void fillTestValues(Instance instance, Operation method) {
		JDTestValues j=new JDTestValues(this, instance, method);
		j.setVisible(true);
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJmFile());
			jJMenuBar.add(getJmAlgorithms());
			jJMenuBar.add(getJmGenerateScenarios());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jmAlgorithms	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJmAlgorithms() {
		if (jmAlgorithms == null) {
			jmAlgorithms = new JMenu();
			jmAlgorithms.setText("Algorithms");
			jmAlgorithms.add(getJrbPairWise());
			jmAlgorithms.add(getJrbEachChoice());
			jmAlgorithms.add(getJrbAllCombinations());
		}
		return jmAlgorithms;
	}

	/**
	 * This method initializes jmFile	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJmFile() {
		if (jmFile == null) {
			jmFile = new JMenu();
			jmFile.setText("File");
			jmFile.add(getJmSave());
			jmFile.add(getJmSetup());
		}
		return jmFile;
	}

	/**
	 * This method initializes jmSetup	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJmSetup() {
		if (jmSetup == null) {
			jmSetup = new JMenuItem();
			jmSetup.setText("Setup");
			jmSetup.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setUp();
				}
			});
		}
		return jmSetup;
	}
	
    protected void setUp() {
        JDSetup j=new JDSetup(this);
        j.setModal(true);
        j.setVisible(true);
        this.classSelectorPanel.setClassPath(j.getClassPath());
    }

	/**
	 * This method initializes jrbPairWise	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJrbPairWise() {
		if (jrbPairWise == null) {
			jrbPairWise = new JRadioButtonMenuItem();
			jrbPairWise.setText("Pair wise");
			jrbPairWise.setSelected(true);
			jrbPairWise.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					setAlgorithm(new GuiPairWise());
				}
			});
		}
		return jrbPairWise;
	}

	protected void setAlgorithm(Algorithm a) {
		this.algorithm=a;
		System.out.println(a.getClass().getName());
	}

	/**
	 * This method initializes jrbEachChoice	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJrbEachChoice() {
		if (jrbEachChoice == null) {
			jrbEachChoice = new JRadioButtonMenuItem();
			jrbEachChoice.setText("Each choice");
			jrbEachChoice.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					setAlgorithm(new EachChoice());
				}
			});
		}
		return jrbEachChoice;
	}

	/**
	 * This method initializes jrbAllCombinations	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJrbAllCombinations() {
		if (jrbAllCombinations == null) {
			jrbAllCombinations = new JRadioButtonMenuItem();
			jrbAllCombinations.setText("All combinations");
			jrbAllCombinations.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					setAlgorithm(new AllCombinationsElegant());
				}
			});
		}
		return jrbAllCombinations;
	}

	/**
	 * This method initializes jmGenerateScenarios	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJmGenerateScenarios() {
		if (jmGenerateScenarios == null) {
			jmGenerateScenarios = new JMenu();
			jmGenerateScenarios.setText("Generate test scenarios");
			jmGenerateScenarios.add(getJmGenerateForSelected());
			jmGenerateScenarios.add(getJmGenerateForAll());
		}
		return jmGenerateScenarios;
	}

	/**
	 * This method initializes jmGenerateForSelected	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJmGenerateForSelected() {
		if (jmGenerateForSelected == null) {
			jmGenerateForSelected = new JMenuItem();
			jmGenerateForSelected.setText("Generate only for the selected test case");
			jmGenerateForSelected.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					generateTestScenarios(true);
				}
			});
		}
		return jmGenerateForSelected;
	}

	protected void generateTestScenarios(boolean onlyOne) {
		if (onlyOne) {
			int index=this.jtpSteps.getSelectedIndex();
			if (index<1)
				return;
			generateTestScenarios(index);
		} else {
			for (int i=1; i<this.jtpSteps.getTabCount(); i++)
				generateTestScenarios(i);
		}
	}

	private void generateTestScenarios(int index) {
		JPInteractionDiagram jp=(JPInteractionDiagram) this.jtpSteps.getComponentAt(index);
		InteractionDiagram diagram = jp.getInteractionDiagram();
		GuiTestTemplate template=new GuiTestTemplate();
		template.setName("test_" + index);
		Vector<IMessagge> mm = diagram.getMessagges();
		for(IMessagge m : mm) {
			template.addMessagge(m);
		}
		Vector<GuiTestTemplate> tts=new Vector<GuiTestTemplate>(); 
		tts.add(template);
		try {
			Date antes=new Date();
			int[] numberOfTestCases=new int[1];
			String sessionPath=this.mClass.getTestCases(this.algorithm, numberOfTestCases, tts);
			Date despues=new Date();
			String msg=numberOfTestCases[0] + " JUnit test cases generated in " + 
				((float) (despues.getTime()-antes.getTime())/1000) + " seconds";
			log(msg);
			showJUnitDialog(numberOfTestCases, TestMethod.JUNIT, sessionPath, null, null);
		} catch (Exception e) {
			this.logError(e.toString());
			e.printStackTrace();
		}
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
	
	private void saveJUnitFile(int[] numberOfTestCases, int sessionType, String junitSessionPath, boolean compileJUnitFile, String cutName) throws FileNotFoundException, Exception, IOException {
        if (mClass==null && cutName!=null) {
            setClass(new GuiTClass(cutName, false));
        }
        int cont=1, lastTestCase=1;
        GuiJUnitFile testFile;
        while (lastTestCase<=numberOfTestCases[0]) {
        	String junitFileName="Test" + Auxi.recorta(this.mClass.getName()) + cont;
            testFile=new GuiJUnitFile(junitFileName, mClass);
            testFile.setFixtures(this.fixtures);
            testFile.setSetups(this.setUps);
            testFile.setSessionType(sessionType);
	        testFile.setSessionPath(junitSessionPath, numberOfTestCases[0]);
	        String fileName=Configuration.getInstance().getResultsPath()+testFile.getFileName();
	        FileOutputStream f=new FileOutputStream(fileName);
	        lastTestCase=testFile.saveTo(f, lastTestCase);
	        f.close();
	        cont++;
        }
        this.log((cont-1) + " JUnit file(s) generated");
        Auxi.deleteDirectory(junitSessionPath, numberOfTestCases[0]);
    }

	/**
	 * This method initializes jmGenerateForAll	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJmGenerateForAll() {
		if (jmGenerateForAll == null) {
			jmGenerateForAll = new JMenuItem();
			jmGenerateForAll.setText("Generate for all test cases");
		}
		return jmGenerateForAll;
	}

	public GuiSetup findSetup(String name) {
		for(GuiSetup gf : this.setUps)
			if (gf.getName().equals(name))
				return gf;
		return null;
	}

	/**
	 * This method initializes jmSave	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJmSave() {
		if (jmSave == null) {
			jmSave = new JMenuItem();
			jmSave.setText("Save");
			jmSave.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					save();
				}
			});
		}
		return jmSave;
	}

	protected void save() {
		try {
			if (this.mClass==null)
				return;
			Agente.mkdir(Configuration.getInstance().getConstraintsPath(), this.mClass.getName());
			String path;
			for (int i=1; i<this.jtpSteps.getTabCount(); i++) {
				JPInteractionDiagram j = (JPInteractionDiagram) this.jtpSteps.getComponentAt(i);
				InteractionDiagram d = j.getInteractionDiagram();
				Agente.mkdir(Configuration.getInstance().getConstraintsPath()+this.mClass.getName(), this.jtpSteps.getTitleAt(i));
				path=Configuration.getInstance().getConstraintsPath()+this.mClass.getName()+"/"+this.jtpSteps.getTitleAt(i)+"/";
				FileOutputStream f=new FileOutputStream(path+"Instances.txt");
				Properties pp=new Properties(); 
				Enumeration<Instance> instances=d.mInstances.elements();
				while (instances.hasMoreElements()) {
					Instance instance = instances.nextElement();
					pp.setProperty(instance.getName(), instance.getTypeName()+"::"+instance.getName());
				}
				pp.store(f, this.jtpSteps.getTitleAt(i));
				f.close();
				f=new FileOutputStream(path+"Interactions.txt");
				pp=new Properties();
				for (int k=0; k<d.mInteractions.size(); k++) {
					IMessagge interaction = d.mInteractions.get(k);
					Instance source = interaction.getSource();
					Instance target = interaction.getTarget();
					pp.setProperty(interaction.getCounter() +"_" + interaction.getId(), 
							source.getTypeName()+"::"+source.getName() +
							"->" +
							target.getTypeName()+"::"+target.getName());
					if (interaction.hasParameters()) {
						for (int m=0; m<interaction.getParameters().size(); m++) {
							String txt="TestValues_" + interaction.getCounter() + "_" + m;
							TParameter par = interaction.getParameters().get(m);
							for (int n=0; n<par.getTestValues().length; n++)
								pp.setProperty(txt, ""+par.getTestValue(n));
						}
					}
				}
				pp.store(f, this.jtpSteps.getTitleAt(i));
				f.close();
			}
		} catch (Exception e) {
			this.logError(e.toString());
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

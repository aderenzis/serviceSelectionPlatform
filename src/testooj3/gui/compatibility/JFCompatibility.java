package testooj3.gui.compatibility;

import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import reducedTest.JDConflictingMethods;
import me.gui.IProgressWindow;
import testooj3.auxiliares.Auxi;
import testooj3.compatibility.InterfacesCompatibilityChecker;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TestoojClassLoader;
import testooj3.gui.JDClassPathExplorer;
import testooj3.gui.components.ILogWindow;
import testooj3.gui.components.IMainWindow;

/**
 * @author  andres
 */
public class JFCompatibility extends JFrame implements ILogWindow, IProgressWindow {

	private JPanel jContentPane = null;
	private JTextField jtfOriginalClass = null;
	private JTextField jtfCandidateClass = null;
	//private JTable jtProposedEquivalences = null;
	private JButton jbCalculateCompatibility = null;
	private IMainWindow parentWindow;
	private JScrollPane jScrollPane = null;
	private JEditorPane jepLog = null;
	private JProgressBar jpb = null;
	private JCheckBox jchInheritedOperations = null;
	private NonEditableDefaultTableModel selectedEquivalencesModel=new NonEditableDefaultTableModel();
	private JPanel jPanelTop = null;
	private JPanel jPanelButtons = null;
	private JButton jBFindClassPath1 = null;
	private JButton jBFindClassPath2 = null;
	private JPanel jPBotones = null;
	private JButton jbDesignWrapper = null;
	private JButton jbClear = null;
	private JButton btnConflictivsTesting=null;
	private JTextField jtfClassPath1 = null;
	private JTextField jtfClassPath2 = null;
	private JButton jBFindOriginalClass = null;
	private JButton jBFindCandidateClass = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JButton jbBuildWrapperSet = null;
	private InterfacesCompatibilityChecker ich;
	private JFBuildWrappers jota;
	
	
	public JFCompatibility() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		initialize();
		
		//Object[] headers={"original", "candidate"};
		//selectedEquivalencesModel.setColumnIdentifiers(headers);
		//this.jtSelectedEquivalences.setModel(this.selectedEquivalencesModel);
	}

	public JFCompatibility(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JFCompatibility(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JFCompatibility(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(722, 654);
		this.setContentPane(getJContentPane());
		this.setTitle("Interface compatibility");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridwidth = 2;
			gridBagConstraints5.gridy = 6;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 1;
			gridBagConstraints16.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints16.anchor = GridBagConstraints.WEST;
			gridBagConstraints16.gridy = 3;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.insets = new Insets(15, 5, 2, 5);
			gridBagConstraints15.gridy = 0;
			gridBagConstraints15.ipadx = 1;
			gridBagConstraints15.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints15.ipady = 1;
			gridBagConstraints15.weighty = 0.0;
			gridBagConstraints15.gridx = 0;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.insets = new Insets(2, 0, 5, 4);
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridy = 7;
			gridBagConstraints10.ipadx = 1;
			gridBagConstraints10.ipady = 1;
			gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.gridwidth = 1;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.gridwidth = 2;
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.gridy = 5;
			gridBagConstraints9.ipadx = 5;
			gridBagConstraints9.ipady = 5;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.weighty = 0.5;
			gridBagConstraints9.insets = new Insets(8, 5, 2, 5);
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJScrollPane(), gridBagConstraints9);
			jContentPane.add(getJpb(), gridBagConstraints10);
			jContentPane.add(getJPanelTop(), gridBagConstraints15);
			jContentPane.add(getJPanelButtons(), gridBagConstraints16);
			jContentPane.add(getJPBotones(), gridBagConstraints5);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jtforiginalClass	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfOriginalClass() {
		if (jtfOriginalClass == null) {
			jtfOriginalClass = new JTextField();
			jtfOriginalClass.setText("calculadora.calculator");
			jtfOriginalClass.setMinimumSize(new java.awt.Dimension(100,20));
			jtfOriginalClass.setPreferredSize(new java.awt.Dimension(321,20));
			jtfOriginalClass.setEditable(true);
		}
		return jtfOriginalClass;
	}

	/**
	 * This method initializes jtfcandidateClass	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfCandidateClass() {
		if (jtfCandidateClass == null) {
			jtfCandidateClass = new JTextField();
			jtfCandidateClass.setText("wtp.SimpleCalculatorService");
			jtfCandidateClass.setMinimumSize(new java.awt.Dimension(100,20));
			jtfCandidateClass.setPreferredSize(new java.awt.Dimension(321,20));
			jtfCandidateClass.setEditable(true);
		}
		return jtfCandidateClass;
	}

	protected void openClassPathLoader(JTextField jtfClassPath, JTextField jtfClassToLoad) {
    	Configuration.getInstance().setClassPath(jtfClassPath.getText());
        JDClassPathExplorer j=new JDClassPathExplorer();
        j.setModal(true);
        j.setVisible(true);
        if (j.getSelectedClass()!=null)
        	jtfClassToLoad.setText(j.getSelectedClass());
    }
//        JDClassPathExplorer j=new JDClassPathExplorer();
//        j.setModal(true);
//        j.setVisible(true);
//        if (j.getSelectedClass()!=null)
//        	cajaTexto.setText(j.getSelectedClass());
//    }

	
	/**
	 * This method initializes jbCalculateCompatibility	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbCalculateCompatibility() {
		if (jbCalculateCompatibility == null) {
			jbCalculateCompatibility = new JButton();
			jbCalculateCompatibility.setText("Calculate compatibility");
			jbCalculateCompatibility.setPreferredSize(new java.awt.Dimension(177,24));
			jbCalculateCompatibility.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jbCalculateCompatibility.setAlignmentX(0.5F);
			jbCalculateCompatibility.setAlignmentY(0.5F);
			jbCalculateCompatibility.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jbCalculateCompatibility.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					calculateCompatibility();
				}
			});
			jbCalculateCompatibility.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				}
			});
		}
		return jbCalculateCompatibility;
	}

	protected void calculateCompatibility() {
		try {
			String candidateClass=this.jtfCandidateClass.getText();
			String originalClass=this.jtfOriginalClass.getText();
			String cp1=this.jtfClassPath1.getText();
			String cp2=this.jtfClassPath2.getText();
			this.ich=new InterfacesCompatibilityChecker(this, 
					originalClass, candidateClass, this.jchInheritedOperations.isSelected(),
					cp1, cp2);
			Thread t=new Thread(ich);
			t.start();
		} catch (Exception e) {
			logError(e.toString());
			this.parentWindow.logError("Error calculating compatibility: " + e.toString());
		}
	}

	public void setParentWindow(IMainWindow parentWindow) {
		this.parentWindow=parentWindow;
	}

	public void setClassName(String className) {
		this.jtfOriginalClass.setText(className);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJepLog());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jepLog	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJepLog() {
		if (jepLog == null) {
			jepLog = new JEditorPane();
			jepLog.setContentType("text/html");
		}
		return jepLog;
	}
	
	public void logError(String msg) {
        msg="<font color='red'>" + msg + "</font>"+jepLog.getText().trim();
        this.jepLog.setText(msg);
        jepLog.select(0, 0);  
    }
	
	public void log(String msg) {
        msg="<font color='blue'>" + msg + "</font>"+jepLog.getText().trim();
        this.jepLog.setText(msg);
        jepLog.select(0, 0);  
    }

	public void setNumberOfIterations(int iterations) {
		this.jpb.setMaximum(iterations);
	}

	/**
	 * This method initializes jpb	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJpb() {
		if (jpb == null) {
			jpb = new JProgressBar();
		}
		return jpb;
	}

	public void setCurrentIteration(int currentIteration) {
		this.jpb.setValue(currentIteration);
	}

	/**
	 * This method initializes jchInheritedOperations	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJchInheritedOperations() {
		if (jchInheritedOperations == null) {
			jchInheritedOperations = new JCheckBox();
			jchInheritedOperations.setText("Consider inherited operations");
		}
		return jchInheritedOperations;
	}

	public void clearLog() {
		this.jepLog.setText("");
	}


	public void setEquivalenceTable(NonEditableDefaultTableModel tableModel) {
		this.selectedEquivalencesModel = tableModel;
	}

	

	private String getCorrespondingMethodOfB(String signature) {
		for (int i=0; i<selectedEquivalencesModel.getRowCount(); i++) {
			String aSignature=selectedEquivalencesModel.getValueAt(i, 0).toString();
			if (aSignature.equals(signature)) {
				return selectedEquivalencesModel.getValueAt(i, 1).toString();
			}
		}
		return null;
	}

	protected void openOracle() {
		try {
			Interface a=new Interface(this.jtfOriginalClass.getText(), this.jchInheritedOperations.isSelected());
			Interface b=new Interface(this.jtfCandidateClass.getText(), this.jchInheritedOperations.isSelected());
			if (a==null || b==null) {
			    logError("Class " + this.jtfOriginalClass.getText() + " not loaded");
			    return;
			}
			JDOracleComparator j=new JDOracleComparator(this);
			j.setTableModel(this.selectedEquivalencesModel);
			j.setA(a); 
			j.setB(b);
			j.setModal(true);
			j.setVisible(true);
		} catch (Exception e) {
			logError(e.toString());
		}
	}

	/**
	 * This method initializes jPanelTop	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelTop() {
		if (jPanelTop == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.gridy = 2;
			GridBagConstraints gridBagConstraints101 = new GridBagConstraints();
			gridBagConstraints101.gridx = 1;
			gridBagConstraints101.gridy = 1;
			GridBagConstraints gridBagConstraints91 = new GridBagConstraints();
			gridBagConstraints91.gridx = 0;
			gridBagConstraints91.gridy = 4;
			GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
			gridBagConstraints81.gridx = 0;
			gridBagConstraints81.gridy = 1;
			GridBagConstraints gridBagConstraints52 = new GridBagConstraints();
			gridBagConstraints52.gridx = 4;
			gridBagConstraints52.gridy = 0;
			GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
			gridBagConstraints42.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints42.gridy = 3;
			gridBagConstraints42.weightx = 1.0;
			gridBagConstraints42.gridx = 2;
			GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
			gridBagConstraints32.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints32.gridy = 0;
			gridBagConstraints32.weightx = 1.0;
			gridBagConstraints32.gridx = 2;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 0;
			gridBagConstraints41.gridy = 3;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 0;
			gridBagConstraints31.gridheight = 1;
			gridBagConstraints31.gridy = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.gridy = 1;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.anchor = GridBagConstraints.CENTER;
			gridBagConstraints8.ipadx = 1;
			gridBagConstraints8.ipady = 1;
			gridBagConstraints8.gridx = 2;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridy = 4;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.anchor = GridBagConstraints.CENTER;
			gridBagConstraints4.ipadx = 1;
			gridBagConstraints4.ipady = 1;
			gridBagConstraints4.gridx = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 4;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.ipadx = 1;
			gridBagConstraints.ipady = 1;
			gridBagConstraints.insets = new Insets(0, 0, 0, 10);
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.gridy = 3;
			jPanelTop = new JPanel();
			jPanelTop.setLayout(new GridBagLayout());
			jPanelTop.add(getJbCalculateCompatibility(), gridBagConstraints);
			jPanelTop.add(getJtfCandidateClass(), gridBagConstraints4);
			jPanelTop.add(getJtfOriginalClass(), gridBagConstraints8);
			jPanelTop.add(getJBFindClassPath1(), gridBagConstraints31);
			jPanelTop.add(getJBFindClassPath2(), gridBagConstraints41);
			jPanelTop.add(getJtfClassPath1(), gridBagConstraints32);
			jPanelTop.add(getJtfClassPath2(), gridBagConstraints42);
			jPanelTop.add(getJchInheritedOperations(), gridBagConstraints52);
			jPanelTop.add(getJBFindCandidateClass(), gridBagConstraints91);
			jPanelTop.add(getJBFindOriginalClass(), gridBagConstraints81);
			jPanelTop.add(getJPanel(), gridBagConstraints101);
			jPanelTop.add(getJPanel1(), gridBagConstraints11);
		}
		return jPanelTop;
	}

	/**
	 * This method initializes jPanelButtons	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelButtons() {
		if (jPanelButtons == null) {
			jPanelButtons = new JPanel();
			jPanelButtons.setLayout(new GridBagLayout());
		}
		return jPanelButtons;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBFindClassPath1() {
		if (jBFindClassPath1 == null) {
			jBFindClassPath1 = new JButton();
			jBFindClassPath1.setText("ClassPath Original");
			jBFindClassPath1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jBFindClassPath1.setAlignmentY(0.0F);
			jBFindClassPath1.setPreferredSize(new java.awt.Dimension(152,24));
			jBFindClassPath1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					classPath(jtfClassPath1);
				}
			});
		}
		return jBFindClassPath1;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBFindClassPath2() {
		if (jBFindClassPath2 == null) {
			jBFindClassPath2 = new JButton();
			jBFindClassPath2.setText("ClassPath Candidate");
			jBFindClassPath2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
			jBFindClassPath2.setPreferredSize(new java.awt.Dimension(152,24));
			jBFindClassPath2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					classPath(jtfClassPath2);
				}
			});
		}
		return jBFindClassPath2;
	}

	/**
	 * This method initializes jPBotones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPBotones() {
		if (jPBotones == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout.setHgap(30);
			jPBotones = new JPanel();
			jPBotones.setLayout(flowLayout);
			jPBotones.setPreferredSize(new java.awt.Dimension(650,36));
			jPBotones.add(getJbClear(), null);
			jPBotones.add(getJbDesignWrapper(), null);
			jPBotones.add(getJbBuildWrapperSet(), null);
			jPBotones.add(getBtnConflictivsTesting(), null);
		}
		return jPBotones;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbDesignWrapper() {
		if (jbDesignWrapper == null) {
			jbDesignWrapper = new JButton();
			jbDesignWrapper.setText("Manual Matching");
			jbDesignWrapper.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					generateWrapper();
				}
			});
		}
		return jbDesignWrapper;
	}	

	protected void generateWrapper() {
		Interface a;
		Interface b;
		try {			
			a=TestoojClassLoader.load(this.jtfCandidateClass.getText(), this.jtfClassPath2.getText(), this.jchInheritedOperations.isSelected());
//			TClass a=new TClass(this.jtfCandidateClass.getText(), this.jchInheritedOperations.isSelected());
			b=TestoojClassLoader.load(this.jtfOriginalClass.getText(), this.jtfClassPath1.getText(), this.jchInheritedOperations.isSelected());									
//			TClass b=new TClass(this.jtfOriginalClass.getText(), this.jchInheritedOperations.isSelected());
			if (a==null || b==null) {
			    logError("Class " + this.jtfCandidateClass.getText() + " not loaded");
			    System.out.println("Clase " + this.jtfCandidateClass.getText() + " no cargada");
			    return;
			}
			JFWrapping j=new JFWrapping(b,a,ich);			
			j.setParentWindow(this);
			j.setReferenceClass(b); 
			j.setCandidateClass(a);
			j.setEquivalenceTable(this.selectedEquivalencesModel,b);			
//			j.setModal(true);
			j.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			logError(e.toString());
		}
}	

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbClear() {
		if (jbClear == null) {
			jbClear = new JButton();
			jbClear.setBounds(new java.awt.Rectangle(11,376,88,20));
			jbClear.setText("Clear");
			jbClear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					clearLog();
				}
			});
		}
		return jbClear;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfClassPath1() {
		if (jtfClassPath1 == null) {
			jtfClassPath1 = new JTextField();
			Configuration cfg=Configuration.getInstance();
			jtfClassPath1.setName(cfg.getClassPath());
			jtfClassPath1.setText(cfg.getClassPath());
			jtfClassPath1.setMinimumSize(new java.awt.Dimension(100,20));
		}
		return jtfClassPath1;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfClassPath2() {
		if (jtfClassPath2 == null) {
			jtfClassPath2 = new JTextField();
			Configuration cfg=Configuration.getInstance();
			jtfClassPath2.setName(cfg.getClassPath());
			jtfClassPath2.setText(cfg.getClassPath());
			jtfClassPath2.setMinimumSize(new java.awt.Dimension(100,20));
		}
		return jtfClassPath2;
	}
	
	protected void classPath(JTextField jtfClassPath) {
		JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	String result=fc.getSelectedFile().getAbsolutePath()+ ";"+ jtfClassPath.getText();
        	result=Auxi.substituteAll(result, "\\", "/");
       		jtfClassPath.setText(result);
        }
	}


	/**
	 * This method initializes jButton5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBFindOriginalClass() {
		if (jBFindOriginalClass == null) {
			jBFindOriginalClass = new JButton();
			jBFindOriginalClass.setAlignmentY(0.0F);
			jBFindOriginalClass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jBFindOriginalClass.setText("Original Class");
			jBFindOriginalClass.setPreferredSize(new java.awt.Dimension(152,24));
			jBFindOriginalClass.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openClassPathLoader(jtfClassPath1,jtfOriginalClass);
				}
			});
		}
		return jBFindOriginalClass;
	}

	/**
	 * This method initializes jButton6	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBFindCandidateClass() {
		if (jBFindCandidateClass == null) {
			jBFindCandidateClass = new JButton();
			jBFindCandidateClass.setPreferredSize(new java.awt.Dimension(152,24));
			jBFindCandidateClass.setText("Candidate Class");
			jBFindCandidateClass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jBFindCandidateClass.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openClassPathLoader(jtfClassPath2,jtfCandidateClass);
				}
			});
		}
		return jBFindCandidateClass;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
		}
		return jPanel1;
	}

	/**
	 * This method initializes jbBuildWrapperSet	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbBuildWrapperSet() {
		if (jbBuildWrapperSet == null) {
			jbBuildWrapperSet = new JButton();
			jbBuildWrapperSet.setPreferredSize(new java.awt.Dimension(145,26));
			jbBuildWrapperSet.setToolTipText("Builds Wrappers Set");
			jbBuildWrapperSet.setText("Build Wrappers Set");
			jbBuildWrapperSet.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buildWrappers();
				}
			});
		}
		return jbBuildWrapperSet;
	}

	
	protected void buildWrappers() {
		this.jota=new JFBuildWrappers(ich);
		this.jota.setVisible(true);
	}
	public void setMaximum(int max) {
		this.jpb.setMaximum(max);
	}

	public void setPos(int pos) {
		this.jpb.setValue(pos);
	}

 
	private JButton getBtnConflictivsTesting() {
		if (btnConflictivsTesting == null) {
			btnConflictivsTesting = new JButton("Build reduced test");
			btnConflictivsTesting.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openJDConflictingMethods();
				}
			});
		}
		return btnConflictivsTesting;
	}
	
	private void openJDConflictingMethods()
	{
		if (this.ich==null) {
            logError("Class not loaded");
            return;
        }
		if(this.ich.getCompatibilities().size()<1)
		{
			logError("not have conflicting methods");
			return;
		}
		
		JDConflictingMethods jdcm=new JDConflictingMethods( );
		jdcm.setCompatibility(this.ich);
		jdcm.setLogWindow(this);
		jdcm.setModal(true);
		jdcm.setVisible(true);
		//compilar Shadow
		if (jdcm.getChckbxCompileShadow().isSelected()) {
        	String fileName=Auxi.recorta(this.ich.getOriginal().getName());
        	String path=Configuration.getInstance().getWorkingPath()+"wrappers"+File.separator+"original"+File.separator
						+Auxi.recorta_package(this.ich.getOriginal().getName());
        	System.out.println("path compilar:"+path);
        	this.compile(path, fileName);
        }
	}
	
	private void compile(String path, String cutName) {
		me.domain.Compiler compiler=new me.domain.Compiler(this);
	   	compiler.simpleCompile(path, "", cutName); //MODIFICADO POR ISRAEL
	    System.out.println(path+":"+cutName);
	 }
	
}  //  @jve:decl-index=0:visual-constraint="-342,-92"


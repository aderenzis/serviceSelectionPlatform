package testooj3.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.xml.transform.TransformerException;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.TField;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.exceptions.TransitionExistException;
import testooj3.domain.states.exceptions.TransitionUnsupportedException;
import testooj3.gui.components.IMainWindow;
import testooj3.gui.components.JPStatesDefinition;
import testooj3.gui.components.JSPClassStructure;
import testooj3.gui.stmachine.statemachine.JPStateMachine;
import testooj3.persistence.Agente;
import testooj3.persistence.xmi.StmXMIExport;
import testooj3.persistence.xmi.StmXMIImport;
/**
 * @author  Administrador
 */
public class JDStatesDefinition extends JDialog implements IMainWindow {

	private javax.swing.JPanel jContentPane = null;
    private IMainWindow mParentWindow;  //  @jve:decl-index=0:
    private Interface mClass;  //  @jve:decl-index=0:
	private JSPClassStructure JSPClassStructure = null;
	private JLabel jLabel = null;
	private JScrollPane jScrollPane = null;
	private JList jlObserverMethods = null;
	private TOperation mSelectedOperation;
	private JButton jbAddObserver = null;
	private JButton jButton = null;
	private JButton jbOpenFile = null;
	private JButton jbSaveAndExit = null;
	private JButton jbDontSaveAndExit = null;
	private DefaultListModel observerMethodsModel = null;   //  @jve:decl-index=0:
	private State mSelectedState;  //  @jve:decl-index=0:
	private JTabbedPane jTabbedPane = null;
	private JPStatesDefinition jpStatesDefinition = null;
	private JPStateMachine jpStateMachine = null;
	private JPanel jPanelButtons = null;
	private JPanel jPanelButtonsObsMethod = null;
	/**
	 * This is the default constructor
	 * @param unit 
	 */
	public JDStatesDefinition() {
		super();
		initialize();
		this.jpStatesDefinition.setParentWindow(this);
		this.JSPClassStructure.showStatesNode(true);
		this.JSPClassStructure.setParentWindow(this);
		this.jpStateMachine.setParentWindow(this);
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("States definition");
		this.setSize(933, 514);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.insets = new Insets(1, 5, 0, 1);
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.anchor = GridBagConstraints.WEST;
			gridBagConstraints10.fill = GridBagConstraints.NONE;
			gridBagConstraints10.gridx = 0;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints9.gridy = 4;
			gridBagConstraints9.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.BOTH;
			gridBagConstraints8.gridheight = 4;
			gridBagConstraints8.gridx = 1;
			gridBagConstraints8.gridy = 0;
			gridBagConstraints8.ipady = 0;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.weighty = 1.0;
			gridBagConstraints8.insets = new Insets(6, 2, 2, 7);
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridheight = 2;
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.ipadx = 1;
			gridBagConstraints7.ipady = 0;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.weighty = 1.0;
			gridBagConstraints7.insets = new Insets(1, 5, 5, 1);
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.ipadx = 1;
			gridBagConstraints6.ipady = 0;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.weighty = 1.0;
			gridBagConstraints6.insets = new Insets(5, 5, 0, 1);
			jLabel = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jLabel.setText("Observer methods:");
			jContentPane.add(getJSPClassStructure(), gridBagConstraints6);
			jContentPane.add(getJScrollPane(), gridBagConstraints7);
			jContentPane.add(getJTabbedPane(), gridBagConstraints8);
			jContentPane.add(getJPanelButtons(), gridBagConstraints9);
			jContentPane.add(getJPanelButtonsObsMethod(), gridBagConstraints10);
		}
		return jContentPane;
	}
    /**
     * @param class1
     */
    public void setClass(Interface c) {
        this.mClass=c;
        this.JSPClassStructure.showLevels(3);
        this.JSPClassStructure.setClass(c);
        for (int i=0; i<mClass.getObserverMethods().size(); i++) 
        {
          Operation m=(Operation) mClass.getObserverMethods().get(i);
          this.observerMethodsModel.addElement(m.getId());
        }
    }
    
    public Interface getMClass(){
    	return this.mClass;
    }
    /**
     * @param unit
     */
    public void setParentWindow(IMainWindow v) {
        this.mParentWindow=v;
    }
	/**
	 * This method initializes JSPClassStructure	
	 * 	
	 * @return testooj3.gui.components.JSPClassStructure	
	 */    
	private JSPClassStructure getJSPClassStructure() {
		if (JSPClassStructure == null) {
			JSPClassStructure = new JSPClassStructure();
		}
		return JSPClassStructure;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJlObserverMethods());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */    
	private JList getJlObserverMethods() {
		if (jlObserverMethods == null) {
			jlObserverMethods = new JList();
			jlObserverMethods.setModel(getObserverMethodsModel());
		}
		return jlObserverMethods;
	}
	/**
     * 
     */
    protected void assignStateName() {
        if (this.mSelectedState==null) return;
        this.mSelectedState.setName(this.jpStatesDefinition.getStateName());
    }
    /**
     * 
     */
    protected void assignStateDescription() {
        if (this.mSelectedState==null) return;
        this.mSelectedState.setDescription(this.jpStatesDefinition.getStateDescription());
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#showMembers(testooj3.domain.TClass)
     */
    public void showMembers(Interface c) {
        // TODO Auto-generated method stub
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#log(java.lang.String)
     */
    public void log(String msg) {
        this.mParentWindow.log(msg);
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#logError(java.lang.String)
     */
    public void logError(String string) {
        this.mParentWindow.logError(string);
    }
    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#parameterSelected(testooj3.domain.TParameter)
     */
    public void parameterSelected(TParameter p) {
        // TODO Auto-generated method stub        
    }
    /* (non-Javadoc)
     * @see testooj3.gui.composnents.IMainWindow#methodSelected(testooj3.domain.TOperation)
     */
    public void methodSelected(TOperation operation) {
        mSelectedOperation=operation;   
        this.getJpStateMachine().selectMethod(this.mSelectedOperation);
    }
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbAddObserver() {
		if (jbAddObserver == null) {
			jbAddObserver = new JButton();
			jbAddObserver.setText("Add");
			jbAddObserver.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					addObserverMethod();
				}
			});
		}
		return jbAddObserver;
	}
	/**
     * 
     */
    protected void addObserverMethod() {
        if (this.mSelectedOperation instanceof TConstructor) {
            logError("A constructor cannot be an observer");
            return;
        }
        Operation m=(Operation) this.mSelectedOperation; 
        if (m.getTipo().equals("void")) {
            logError("A void method cannot be an observer");
            return;
        }
        if (!this.observerMethodsModel.contains(m.getId())) {
            observerMethodsModel.addElement(m.getId());
            mClass.addObserverMethod(m);
        }
    }
    /**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Remove");
			jButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					removeObserverMethod();
				}
			});
		}
		return jButton;
	}
	/**
     * 
     */
    protected void removeObserverMethod() {
        int index=this.jlObserverMethods.getSelectedIndex();
        if (index==-1) return;
        String signature=this.jlObserverMethods.getSelectedValue().toString();
        mClass.removeObserverMethod(signature);
        this.observerMethodsModel.remove(index);
    }
    /**
     * 
     */
    protected void removeState() {
        
    }
    /**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbOpenFile() {
		if (jbOpenFile == null) {
			jbOpenFile = new JButton();
			jbOpenFile.setText("Open file");
			jbOpenFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					openFile();
				}
			});
		}
		return jbOpenFile;
	}
	/**
     * 
     */
    protected void openFile() {
    	String path=Configuration.getInstance().getConstraintsPath()+"/";
        try {
        	
          mClass.getObserverMethods().removeAllElements();
          mClass.getStates().removeAllElements();
          mClass.loadDescription();
          this.JSPClassStructure.setClass(mClass);      
          for (int i=0; i<mClass.getObserverMethods().size(); i++) 
          {
            Operation m=(Operation) mClass.getObserverMethods().get(i);
            this.observerMethodsModel.addElement(m.getId());
          }
          this.JSPClassStructure.showStatesNode(true);
          this.JSPClassStructure.setClass(mClass);
          
          StmXMIImport importer=new StmXMIImport(path+mClass.getName()+"/stm.xmi", this.mClass);
          this.getJpStateMachine().setStateMachine(
        		  importer.getStm()
          		  //(StateMachine) Agente.getAgente().cargarObjeto(path+mClass.getName()+"\\stm.ser")	  
          );
          
        }catch(TransitionExistException ex){
        	mParentWindow.logError("Error abriendo el xmi, hay transiciones que ya existen");
        	ex.printStackTrace();
        }catch(TransitionUnsupportedException ex){
        	mParentWindow.logError("Error abriendo el xmi, hay transiciones no soportadas");
        	ex.printStackTrace();
        }catch (Exception ex){
          mParentWindow.logError(ex.toString());
          ex.printStackTrace();
        }
    }
    /**
	 * This method initializes jButton2	
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
    	String path=Configuration.getInstance().getConstraintsPath();
//        mSelectedState.setName(this.jpStatesDefinition.getStateName());
//        mSelectedState.setDescription(this.jpStatesDefinition.getStateDescription());
        try {
        	Agente.mkdir(path, mClass.getName());        	
        	/**Agente.getAgente().save(this.getJpStateMachine().getStateMachine(), path+mClass.getName()+"\\stm.ser");
            En lugar de serializarla, la guardamos en formato XMI
            */
        	StmXMIExport exporter=new StmXMIExport(path+mClass.getName()+"/stm.xmi",this.getJpStateMachine().getStateMachine(),this.getTClass());
        	exporter.serialize();
        	mClass.saveDescription();    
        	mParentWindow.log("XMI saved: "+exporter.getFileName());
            dispose();
          }catch (TransformerException ex){
        	  mParentWindow.logError("Error saving XMI format: "+ex.toString());
        	  ex.printStackTrace();
          }catch (Exception ex){
            mParentWindow.logError(ex.toString());
            ex.printStackTrace();
          }
    }
    /**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbDontSaveAndExit() {
		if (jbDontSaveAndExit == null) {
			jbDontSaveAndExit = new JButton();
			jbDontSaveAndExit.setText("Exit, no saving");
			jbDontSaveAndExit.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					dispose();
				}
			});
		}
		return jbDontSaveAndExit;
	}
	/**
	 * This method initializes observerMethodsModel	
	 * 	
	 * @return javax.swing.DefaultListModel	
	 */    
	private DefaultListModel getObserverMethodsModel() {
		if (observerMethodsModel == null) {
			observerMethodsModel = new DefaultListModel();
		}
		return observerMethodsModel;
	}

    /* (non-Javadoc)
     * @see testooj3.gui.components.IMainWindow#stateSelected(testooj3.domain.states.State)
     */
    public void stateSelected(State selectedState) {
        this.mSelectedState=selectedState;
        this.jpStatesDefinition.setState(this.mSelectedState);	
        this.jpStateMachine.addState(this.mSelectedState);
    }
	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("States definition", null, getJpStateDefinition(), null);
			jTabbedPane.addTab("State machine", null, getJpStateMachine(), null);
		}
		return jTabbedPane;
	}
	/**
	 * This method initializes jpStateDefinition	
	 * 	
	 * @return testooj3.gui.components.JPStatesDefinition	
	 */
	private JPStatesDefinition getJpStateDefinition() {
		if (jpStatesDefinition == null) {
			jpStatesDefinition = new JPStatesDefinition();
		}
		return jpStatesDefinition;
	}
	
	public void add(State st) {
		try {
			this.mClass.add(st);
			this.JSPClassStructure.setClass(mClass);
		} catch (Exception e) {
			logError("Error adding the state: " + e.toString());
		}        
	}
	/**
	 * This method initializes jpStateMachine	
	 * 	
	 * @return testooj3.gui.statemachine.JPStateMachine	
	 */
	private JPStateMachine getJpStateMachine() {
		if (jpStateMachine == null) {
			jpStateMachine = new JPStateMachine(this);
		}
		return jpStateMachine;
	}
	public void removeState(State st) {
		try {
			this.mClass.removeState(st);
			this.JSPClassStructure.setClass(mClass);
		} catch (Exception e) {
			logError("Error adding the state: " + e.toString());
		} 
	}
	
	public Interface getTClass(){
		return this.mClass;
	}
	public void setTemplatesWindow(ITemplatesWindow templatesWindow) {
		this.getJpStateMachine().setTemplatesWindow(templatesWindow);
	}
	/**
	 * This method initializes jPanelButtons	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelButtons() {
		if (jPanelButtons == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.ipadx = 0;
			gridBagConstraints.insets = new Insets(0, 1, 0, 10);
			gridBagConstraints.gridy = 0;
			jPanelButtons = new JPanel();
			jPanelButtons.setLayout(new GridBagLayout());
			jPanelButtons.add(getJbOpenFile(), gridBagConstraints);
			jPanelButtons.add(getJbSaveAndExit(), gridBagConstraints1);
			jPanelButtons.add(getJbDontSaveAndExit(), gridBagConstraints2);
		}
		return jPanelButtons;
	}
	/**
	 * This method initializes jPanelButtonsObsMethod	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelButtonsObsMethod() {
		if (jPanelButtonsObsMethod == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 2;
			gridBagConstraints5.insets = new Insets(1, 1, 1, 2);
			gridBagConstraints5.gridy = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.insets = new Insets(1, 2, 1, 2);
			gridBagConstraints4.gridy = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.ipadx = 5;
			gridBagConstraints3.ipady = 1;
			gridBagConstraints3.insets = new Insets(1, 1, 1, 1);
			gridBagConstraints3.gridy = 0;
			jPanelButtonsObsMethod = new JPanel();
			jPanelButtonsObsMethod.setLayout(new GridBagLayout());
			jPanelButtonsObsMethod.add(jLabel, gridBagConstraints3);
			jPanelButtonsObsMethod.add(getJbAddObserver(), gridBagConstraints4);
			jPanelButtonsObsMethod.add(getJButton(), gridBagConstraints5);
		}
		return jPanelButtonsObsMethod;
	}
	
	public void fieldSelected(TField selectedField) {
		// Intencionadamente en blanco.
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

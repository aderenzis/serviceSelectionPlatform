package testooj3.gui.compatibility;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import testooj3.compatibility.InterfacesCompatibilityChecker;
import testooj3.domain.Interface;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.gui.JDClassPathExplorer;
import testooj3.gui.components.ILogWindow;
import testooj3.gui.components.IMainWindow;

/**
 * @author  andres
 */
public class JFWrapping extends JFrame implements ILogWindow {

	private JPanel jContentPane = null;
	private JFrame parentWindow;
	private JProgressBar jpb = null;
	private JScrollPane jsp = null;
	private JTable jtProposedEquivalences = null;
	private JScrollPane jsp2 = null;
	private JTable jtSelectedEquivalences = null;
	private NonEditableDefaultTableModel selectedEquivalencesModel=new NonEditableDefaultTableModel();
	private NonEditableDefaultTableModel proposedEquivalencesModel=new NonEditableDefaultTableModel();
	private JButton jbGenerateWrapper = null;
	private Interface referenceClass;  //  @jve:decl-index=0:
	private Interface candidateClass;  //  @jve:decl-index=0:
	private Boolean considerInheritedOperations;
	private JEditorPane jepLog = null;
	private JScrollPane JSPLog = null;	
	private InterfacesCompatibilityChecker ich;
	public JFWrapping(Interface original, Interface candidate, InterfacesCompatibilityChecker ich ) throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		initialize();
		this.referenceClass = original;
		this.candidateClass = candidate;
		this.ich = ich;
		Object[] headers={"Original", "Candidate"};
		selectedEquivalencesModel.setColumnIdentifiers(headers);
		this.jtSelectedEquivalences.setModel(this.selectedEquivalencesModel);		
//		setEquivalenceTable(this.proposedEquivalencesModel, this.referenceClass);
	}
	
	public JFWrapping() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		initialize();				
		Object[] headers={"Original", "Candidate"};
		selectedEquivalencesModel.setColumnIdentifiers(headers);
		this.jtSelectedEquivalences.setModel(this.selectedEquivalencesModel);

	}

	public JFWrapping(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public JFWrapping(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}
	
	public JFWrapping(String arg0, GraphicsConfiguration arg1) {
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
		this.setSize(677, 437);
		this.setContentPane(getJContentPane());
		this.setTitle("Add Matchings");
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
			jContentPane.add(getJsp(), null);
			jContentPane.add(getJsp2(), null);
			jContentPane.add(getJbGenerateWrapper(), null);
			//jContentPane.add(getJepLog(), null);
			jContentPane.add(getJSPLog(), null);
		}
		return jContentPane;
	}

	protected void openClassPathLoader(JTextField cajaTexto) {
        JDClassPathExplorer j=new JDClassPathExplorer();
        j.setModal(true);
        j.setVisible(true);
        if (j.getSelectedClass()!=null)
        	cajaTexto.setText(j.getSelectedClass());
    }

	public void setParentWindow(JFrame parentWindow) {
		this.parentWindow=parentWindow;
	}

	public void setReferenceClass(Interface refClass) {
		try {
			this.referenceClass=refClass;			
			//this.jPOracleDescriptionA.setClassLabel(this.a.getName());
			//this.a.loadDescription();
		} catch (Exception e) {
			logError(e.toString());
		}
	}

	public void setCandidateClass(Interface candClass) {
		try {
			this.candidateClass=candClass;			
			//this.jPOracleDescriptionA.setClassLabel(this.a.getName());
			//this.a.loadDescription();
		} catch (Exception e) {
			logError(e.toString());
		}
	}
	
	public void setInheritedOperations(Boolean value) {
		this.considerInheritedOperations = value;
	}

	public void setEquivalenceTable(NonEditableDefaultTableModel tableModel, Interface original) {
				
		Vector  metodosB = this.candidateClass.getMethods();
		Vector  metodosA = original.getMethods();
		Vector metodo = null;		
				
		
		this.jtProposedEquivalences.setModel(tableModel);
				
		this.jtProposedEquivalences.setAutoResizeMode(this.jtProposedEquivalences.AUTO_RESIZE_OFF);
		
		tableModel.addColumn("");
		
		for (int i=0; i<metodosB.size(); i++){						
			tableModel.addColumn(this.candidateClass.getMethod(i).getNombre());			
		}
										
//		tableModel.insertRow(0,metodosA);
		
		for (int i=0; i<metodosA.size(); i++){			
			tableModel.insertRow(i, metodo);		
			tableModel.setValueAt(original.getMethod(i).getNombre(), i, 0);	
			for (int j = 1; j<tableModel.getColumnCount(); j++){
				tableModel.setValueAt("+",i,j);
			}
			
		}
		
	}

	/**
	 * This method initializes jsp	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJsp() {
		if (jsp == null) {
			jsp = new JScrollPane(this.jtProposedEquivalences);
			jsp.setBounds(new java.awt.Rectangle(10,10,650,150));
			jsp.setViewportView(getJtProposedEquivalences());			
		}
		return jsp;
	}

	/**
	 * This method initializes jtEquivalences	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJtProposedEquivalences() {
		if (jtProposedEquivalences == null) {
			jtProposedEquivalences = new JTable();
			jtProposedEquivalences.setAutoResizeMode(0);
			jtProposedEquivalences.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					selectEquivalence();
				}
			});
		}
		return jtProposedEquivalences;
	}

	protected void selectEquivalence() {
		int row=this.jtProposedEquivalences.getSelectedRow();
		int col=this.jtProposedEquivalences.getSelectedColumn();
		if (row==-1 || col<=0) {
			logError("Invalid selection");
			return;
		}
		Object auxi=this.jtProposedEquivalences.getValueAt(row, col);
		if (auxi==null) {
			logError("Invalid selection");
			return;
		}
		String value=auxi.toString();
		if (!value.equals("+") && !value.equals("-")) {
			logError("Invalid selection");
			return;
		}
		String aMethod=this.jtProposedEquivalences.getValueAt(row, 0).toString();
		String bMethod=this.jtProposedEquivalences.getColumnName(col);
		if (value.equals("+")) {
			this.selectedEquivalencesModel.addRow(new String[] {aMethod, bMethod});
			this.jtProposedEquivalences.setValueAt("-", row, col);
		} else {
			int rowId=-1;
			for (int i=0; i<this.selectedEquivalencesModel.getRowCount(); i++) {
				String value1=this.selectedEquivalencesModel.getValueAt(i, 0).toString();
				String value2=this.selectedEquivalencesModel.getValueAt(i, 1).toString();
				if (value1.equals(aMethod) && value2.equals(bMethod)) {
					rowId=i;
					i=this.selectedEquivalencesModel.getRowCount();
					this.jtProposedEquivalences.setValueAt("+", row, col);
				}
			}
			if (rowId!=-1)
				this.selectedEquivalencesModel.removeRow(rowId);
		}
	}

	/**
	 * This method initializes jsp2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJsp2() {
		if (jsp2 == null) {
			jsp2 = new JScrollPane(this.jtSelectedEquivalences);
			jsp2.setBounds(new java.awt.Rectangle(10,175,650,150));
			jsp2.setViewportView(getJtSelectedEquivalences());
		}
		return jsp2;
	}

	/**
	 * This method initializes jtSelectedEquivalences	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJtSelectedEquivalences() {
		if (jtSelectedEquivalences == null) {
			jtSelectedEquivalences = new JTable();
		}
		return jtSelectedEquivalences;
	}

	/**
	 * This method initializes jbOpenTestSession	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbGenerateWrapper() {
		if (jbGenerateWrapper == null) {
			jbGenerateWrapper = new JButton();
			jbGenerateWrapper.setBounds(new java.awt.Rectangle(500,361,153,27));
			jbGenerateWrapper.setText("Generate Matching");
			jbGenerateWrapper.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					generateWrapper();}
			});
		}
		return jbGenerateWrapper;
	}



	protected void generateWrapper() {
		try {
			Interface a= referenceClass;
			Interface b= candidateClass;
			Operation origMethod;
			Operation candMethod;						
			
			for(int i = 0;i < selectedEquivalencesModel.getRowCount(); i++){
				origMethod = a.findMethod(selectedEquivalencesModel.getValueAt(i,0).toString());
				candMethod = b.findMethod(selectedEquivalencesModel.getValueAt(i,1).toString());
//				System.out.println(origMethod.toString());
//				System.out.println(candMethod.toString());				
//				this.ich.runManualAdd(origMethod,a, candMethod,b);
			}
			
//			String s="/***\n *** A Wrapper for " + b.getName() + "\n***/\n\n";			
//			s+="public class " + a.getName() + " {\n"; //Declaracion de la Clase
//			s+="\tprotected " + b.getName() + " candidate;\n\n"; // declara el objeto de la clase a adaptar
//			s+="\tpublic " + a.getName() + "() {\n";
//			s+="\t\tcandidate= new " + b.getName() + "();\n";
//			s+="\t}\n\n";
//			for (int i=0; i<a.getMethods().size(); i++) {
//				TMethod aMethod=a.getMethod(i);
//				String tipo=aMethod.getTipo();
//				String linea="\tpublic " + tipo + " " + aMethod.getNombre() + "(";
//				Vector cambiosDeTipo=new Vector();
//				for (int j=0; j<aMethod.getParametros().size(); j++) {
//					TParameter aPar=aMethod.getParametro(j);
//					tipo=aPar.getTipo();
//					if (tipo.equals(a.getName())) // parametro de type la clase A - ej. constructor  
//						cambiosDeTipo.add(new Boolean(true));
//					else
//						cambiosDeTipo.add(new Boolean(false));
//					linea+=tipo + " arg" + (j+1) + ", ";
//				}
//				if (linea.endsWith(", ")) linea=linea.substring(0, linea.length()-2);
//				linea+=") {\n";
//				String bMethodSignature=getCorrespondingMethodOfB(aMethod.getSignature());
//				if (bMethodSignature!=null) {
//					TMethod bMethod=b.findMethodBySignature(bMethodSignature);
//					if (!aMethod.getTipo().equals("void"))
//						linea+="\t\treturn ";
//					else
//						linea+="\t\t";
//					linea+="candidate." + bMethod.getNombre() + "(";
//					for (int j=0; j<aMethod.getParametros().size(); j++) {
//						Boolean cambiarTipo=(Boolean) cambiosDeTipo.get(j);
//						if (cambiarTipo.booleanValue())
//							linea+="arg" + (j+1) + ".candidate, ";
//						else
//							linea+="arg" + (j+1) + ", ";
//					}
//					if (linea.endsWith(", ")) linea=linea.substring(0, linea.length()-2);
//					linea+=");\n";
//					linea+="\t}\n\n";
//					s+=linea;
//				}
//			}
//			s+="}\n";
//			JDWrapperViewer j=new JDWrapperViewer(this);
//			j.log(s);
//			j.setModal(true);
//			j.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			logError(e.toString());
		}
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
			Interface a= this.referenceClass;
			Interface b= this.candidateClass;			
			if (a==null || b==null) {
			    logError("Class not loaded"); //Falló la carga de a o b.
			    return;
			}
			JDOracleComparator j=new JDOracleComparator(this);
			j.setTableModel(this.selectedEquivalencesModel);
			j.setA(a); 
			j.setB(b);
			j.setModal(true);
			j.setVisible(true);
		} catch (Exception e) {				
			e.printStackTrace();
			logError(e.toString());
		}		
	}

	/**
	 * This method initializes JSPLog	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJSPLog() {
		if (JSPLog == null) {
			JSPLog = new JScrollPane();
			JSPLog.setBounds(new java.awt.Rectangle(8,340,478,61));
			JSPLog.setViewportView(getJepLog());
		}
		return JSPLog;
	}
	
	
	/**
	 * This method initializes jEditorPane	
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
        this.jepLog.setText(msg);        jepLog.select(0, 0);  
    }

	
	public void log(String msg) {
        msg="<font color='blue'>" + msg + "</font>"+jepLog.getText().trim();
        this.jepLog.setText(msg);
        jepLog.select(0, 0);  
    }


	protected void clearLog() {
		this.jepLog.setText("");
	}




 
}  //  @jve:decl-index=0:visual-constraint="-29,10"


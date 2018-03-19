package reducedTest;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import testooj3.compatibility.InterfacesCompatibilityChecker;
import testooj3.domain.Interface;
import testooj3.domain.TField;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.states.State;
import testooj3.gui.JDListOfMujavaMethods;
import testooj3.gui.components.ILogWindow;
import testooj3.gui.components.IMainWindow;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;

public class JDConflictingMethods extends JDialog implements IMainWindow, ILogWindow{

	/**
	 * Launch the application.
	 */
	private javax.swing.JPanel jContentPane = null;
	private ILogWindow mLogWindow;
	private JTable tableFields;
	private JTable tableConflictingMethods;
	private JButton btnAdd;
	private JButton btnRemove;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	
	private int conflicMethodToSave;
	private int parameterEfectToSave;
	private ManejadorShadow manejador;
	private ReducedMuJavaTS rts;
	
//	private Vector methods;
	private InterfacesCompatibilityChecker ich;
//	private TClass a;
	private JSeparator separator;
	private JTable tableParameters;
	private JTable tableEfects;
	private JButton btnAddEfects;
	private JButton btnDeleteEfects;
	
	private JButton btnAddParameters;
	private JButton btnDeleteParameters;
	private JScrollPane scrollPane_4;
	private JButton btnCancel;
	private JButton btnGenerateShadowClass;
	private JButton btnGenerateTs;
	
	private JEditorPane jepCodeShadow;
	private JScrollPane jScrollPaneShadow;
	private JLabel lblTypeReturn;
	private JScrollPane scrollPane_5;
	private JEditorPane consoleShadow;
	private JCheckBox chckbxCompileShadow;
	private JButton btnSave;
	
	private JDConflictingMethods jdcm;
	
	public static void main(String[] args) {
		try {
			JDConflictingMethods dialog = new JDConflictingMethods();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JDConflictingMethods() {
		super();
		initialize();
	}
	public void setLogWindow(ILogWindow v) {
	    this.mLogWindow=v;
	}
	private void initialize() {
		this.setTitle("Conflicting Methods");
		this.setSize(1200, 611);
		this.setContentPane(getJContentPane());
		this.conflicMethodToSave=-1;
		this.parameterEfectToSave=-1;
		
		jdcm=this;
		
	}
	
	//este metodo podria invocarse en el contructor en vez de estar llamandolo desde el JFCompatibility
	public void setCompatibility(InterfacesCompatibilityChecker ich)
	{
		this.ich=ich;
		this.manejador=new ManejadorShadow(ich,this);
	}
	
	public JTable getTableConflictingMethods()
	{
		return this.tableConflictingMethods;
	}
	
	private javax.swing.JPanel getJContentPane()
	{
		if(jContentPane == null) {
			setBounds(0, 0, 1240, 800);
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			
			JLabel lblFieldsDefinition = new JLabel("Fields Definition");
			lblFieldsDefinition.setBounds(30, 11, 134, 14);
			jContentPane.add(lblFieldsDefinition);
			
			
			jContentPane.add(getBtnAdd());
			jContentPane.add(getBtnRemove());
			jContentPane.add(configTableFields());
			jContentPane.add(configTableConflictingMethods());
			
			JLabel lblConflictiveMethods = new JLabel("Conflictive Methods");
			lblConflictiveMethods.setBounds(30, 136, 168, 14);
			jContentPane.add(lblConflictiveMethods);
			
			
			separator = new JSeparator(SwingConstants.VERTICAL);
			separator.setBounds(580, 11, 8, 790);
			jContentPane.add(separator);
			
			
			
			JLabel lblInputParameters = new JLabel("Input Parameters");
			lblInputParameters.setBounds(30, 293, 119, 14);
			jContentPane.add(lblInputParameters);
			jContentPane.add(configTableParameters());
			jContentPane.add(getBtnAddParameters());
			jContentPane.add(getBtnDeleteParameters());
			
			
			JLabel lblNewLabel = new JLabel("Effects");
			lblNewLabel.setBounds(30, 463, 60, 14);
			jContentPane.add(lblNewLabel);
			jContentPane.add(configTableEfects());
			jContentPane.add(getBtnAddEfects());
			jContentPane.add(getBtnDeleteEfects());
			
			jContentPane.add(getBtnCancel());
			jContentPane.add(getBtnGenerateShadowClass());
			jContentPane.add(getBtnGenerateTs());
			
			jContentPane.add(getJScrollPaneShadow());
			
			lblTypeReturn = new JLabel("Return Type: ");
			lblTypeReturn.setBounds(203, 463, 201, 14);
			jContentPane.add(lblTypeReturn);
			
			jContentPane.add(getJScrollPane_5());
			
			jContentPane.add(configChckbxCompileShadow());
			
//			jContentPane.add(getBtnSave());
			
		}
		return jContentPane;
	}
	
	public JCheckBox configChckbxCompileShadow()
	{
		chckbxCompileShadow = new JCheckBox("Compile Shadow");
		chckbxCompileShadow.setBounds(596, 468, 251, 23);
		return chckbxCompileShadow;
	}
	
	public JCheckBox getChckbxCompileShadow()
	{
		return this.chckbxCompileShadow;
	}
	
//	public JButton getBtnSave()
//	{
//		btnSave = new JButton("Save");
//		btnSave.setBounds(1182, 778, 89, 23);
//		return btnSave;
//	}
	
	private JScrollPane getJScrollPane_5()
	{
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(598, 510, 600, 150);
		scrollPane_5.setViewportView(getConsoleShadow());
		return scrollPane_5;
	}
	private JEditorPane getConsoleShadow()
	{
		consoleShadow = new JEditorPane();
		consoleShadow.setContentType("text/html");
//		consoleShadow.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 11));
		
		return consoleShadow;
	}
	private JButton getBtnCancel()
	{
		btnCancel = new JButton("Exit");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(1111, 672, 89, 23);
		return btnCancel;
	}
	
	private JButton getBtnGenerateShadowClass()
	{
		btnGenerateShadowClass = new JButton("generate Shadow class");
		btnGenerateShadowClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableFields.isEditing())
					tableFields.getCellEditor().stopCellEditing();
				if(tableEfects.isEditing())
					tableEfects.getCellEditor().stopCellEditing();
				if(tableParameters.isEditing())
					tableParameters.getCellEditor().stopCellEditing();
				
				if(manejador.dataChecker())
				{
					String shadowClass=manejador.generarShadow();
					jepCodeShadow.setText(shadowClass);
				}
				else
				{
					consoleShadow.setText(consoleShadow.getText()+"\n"+"Se han realizado cambios en los nombres de los campos que estan en uso... verifique que los datos sean consistentes.");
				}
			}
		});
		btnGenerateShadowClass.setBounds(600, 672, 220, 23);
		return btnGenerateShadowClass;
	}
	
	private JScrollPane getJScrollPaneShadow() {
		if (jScrollPaneShadow == null) {
			jScrollPaneShadow = new JScrollPane();
			jScrollPaneShadow.setBounds(600,10,600,450);
			jScrollPaneShadow.setViewportView(getJepCodeShadow());
		}
		return jScrollPaneShadow;
	}
	
	private JEditorPane getJepCodeShadow() {
		if (jepCodeShadow == null) {
			jepCodeShadow = new JEditorPane();
			jepCodeShadow.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 11));
		}
		return jepCodeShadow;
	}
	
	private JButton getBtnGenerateTs()
	{
		btnGenerateTs = new JButton("generate TS");
		btnGenerateTs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableFields.isEditing())
					tableFields.getCellEditor().stopCellEditing();
				if(tableEfects.isEditing())
					tableEfects.getCellEditor().stopCellEditing();
				if(tableParameters.isEditing())
					tableParameters.getCellEditor().stopCellEditing();
				rts=new ReducedMuJavaTS(ich.getOriginal(), manejador.getConflictMethods(), jdcm);
				rts.generateTS();
				consoleShadow.setText(consoleShadow.getText()+"\nLos TS se han generado correctamente.");
			}
		});
		btnGenerateTs.setBounds(832, 672, 188, 23);
		return btnGenerateTs;
	}
	
	public JTable getTableParameters()
	{
		return tableParameters;
	}
	
	private JScrollPane configTableParameters()
	{
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(30, 319, 395, 120);
		jContentPane.add(scrollPane_2);
		String[] columns={""};
		String[][] data=new String[0][0];
		DefaultTableModel dtm = new DefaultTableModel(data,columns);
		tableParameters = new JTable(dtm);
		tableParameters.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0){
				if(tableFields.isEditing())
					tableFields.getCellEditor().stopCellEditing();
				if(tableEfects.isEditing())
					tableEfects.getCellEditor().stopCellEditing();
			}
		});
		//haciendo controles
//		tableParameters.setDefaultRenderer(Object.class, new RenderTableParameters());
		
		ListSelectionModel lsm = tableParameters.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(tableParameters.getSelectedRow()!=-1)
					manejador.loadEfects(tableParameters.getSelectedRow());
			}
		});
		scrollPane_2.setViewportView(tableParameters);
		return scrollPane_2;
	}
	
	public JTable getTableEfects()
	{
		return this.tableEfects;
	}
	
	private JScrollPane configTableEfects()
	{
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(30, 489, 395, 120);
		jContentPane.add(scrollPane_3);
		String[] columns={"Field","Value","Return"};
		String[][] data=new String[0][0];
		DefaultTableModel dtm = new DefaultTableModel(data,columns);
		tableEfects = new JTable(dtm);
		tableEfects.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tableFields.isEditing())
					tableFields.getCellEditor().stopCellEditing();
				if(tableParameters.isEditing())
					tableParameters.getCellEditor().stopCellEditing();
			}
		});
		scrollPane_3.setViewportView(tableEfects);
		return scrollPane_3;
	}
	
	
	private JButton getBtnAddParameters()
	{
		btnAddParameters = new JButton("Add Parameters");
		btnAddParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableConflictingMethods.getSelectedRow()!=-1)
					manejador.addValuesParameters(tableConflictingMethods.getSelectedRow());
			}
		});
		btnAddParameters.setBounds(437, 343, 142, 23);
		return btnAddParameters;
	}
	
	private JButton getBtnDeleteParameters()
	{
		btnDeleteParameters = new JButton("Delete Parameters");
		btnDeleteParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manejador.deleteParameters();
			}
		});
		btnDeleteParameters.setBounds(437, 378, 142, 23);
		return btnDeleteParameters;
	}
	
	
	private JButton getBtnAddEfects()
	{
		btnAddEfects = new JButton("Add Efects");
		btnAddEfects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableConflictingMethods.getSelectedRow()!=-1)
					manejador.addEfects();
			}
		});
		btnAddEfects.setBounds(442, 527, 126, 23);
		return btnAddEfects;
	}
	
	private JButton getBtnDeleteEfects()
	{
		btnDeleteEfects = new JButton("Delete Efects");
		btnDeleteEfects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manejador.deleteEfect();
			}
		});
		btnDeleteEfects.setBounds(442, 562, 126, 23);
		return btnDeleteEfects;
	}
	
	private JButton getBtnAdd(){
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manejador.addField();
			}
		});
		btnAdd.setBounds(374, 61, 89, 23);
		return btnAdd;
	}
	
//	private void addField()
//	{
//		Object[] newRow={"",""};
//		DefaultTableModel dtm= (DefaultTableModel)this.tableFields.getModel();
//		dtm.addRow(newRow);
//		this.manejador.addField(newRow);
//	}
	
	private JButton getBtnRemove()
	{
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				removeField();
				manejador.deleteField();
			}
		});
		btnRemove.setBounds(374, 96, 89, 23);
		
		return btnRemove;
	}
	
//	
	
//	private JScrollPane configTableConflictingMethods2_Borrar()
//	{
//		
//		scrollPane_1 = new JScrollPane();
//		scrollPane_1.setBounds(30, 218, 388, 166);
//		jContentPane.add(scrollPane_1);
//		String[] columns={"Conflicting Methods", "Return Method"};
//		String[][] data=new String[0][0];
//		DTModelNoEditable dtm = new DTModelNoEditable(data,columns);
//		tableConflictingMethods = new JTable(dtm);
////		JComboBox typeList=new JComboBox();
//		DefaultComboBoxModel<String> cbm=new DefaultComboBoxModel<String>();
//		cbm.addElement("getBalance()");
//		scrollPane_1.setViewportView(tableConflictingMethods);
//		return scrollPane_1;
//	}
	
	private JScrollPane configTableConflictingMethods()
	{
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 162, 332, 106);
		jContentPane.add(scrollPane_1);
		String[] columns={"Conflicting Methods", "Return Method"};
		String[][] data=new String[0][0];
		DTModelNoEditable dtm = new DTModelNoEditable(data,columns);
		tableConflictingMethods = new JTable(dtm);
		tableConflictingMethods.addFocusListener(new FocusAdapter() {//agregado para poder quitar de la edicion a la ultima celda...
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tableFields.isEditing())
					tableFields.getCellEditor().stopCellEditing();
				if(tableEfects.isEditing())
					tableEfects.getCellEditor().stopCellEditing();
				if(tableParameters.isEditing())
					tableParameters.getCellEditor().stopCellEditing();
			}
		});
		tableConflictingMethods.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//para que solo se pueda seleccionar de a 1
		
		ListSelectionModel lsm = tableConflictingMethods.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				manejador.selectionConflictingMethods(tableConflictingMethods.getSelectedRow());
				manejador.setReturnTypeLabel(lblTypeReturn,tableConflictingMethods.getSelectedRow());
			}
		});
		scrollPane_1.setViewportView(tableConflictingMethods);
		return scrollPane_1;
	}
	
	private JScrollPane configTableFields()
	{
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 36, 332, 88);
		jContentPane.add(scrollPane);
		String[] columns={"Name","Type"};
		String[][] data=new String[0][0];
		DefaultTableModel dtm = new DefaultTableModel(data,columns);
		tableFields = new JTable(dtm);
		tableFields.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tableEfects.isEditing())
					tableEfects.getCellEditor().stopCellEditing();
				if(tableParameters.isEditing())
					tableParameters.getCellEditor().stopCellEditing();
			}
		});
		scrollPane.setViewportView(tableFields);
		return scrollPane;
	}
	
	public JTable getTableFields()
	{
		return this.tableFields;
	}
	
//	private void removeField()
//	{
//		if(this.tableFields.getSelectedRow()!=-1)//si hay una columna seleccionada...
//		{
//			if(JOptionPane.showConfirmDialog(this, "Delete Item?", "Confirmar", JOptionPane.YES_NO_OPTION)==0)//si
//			{
//				DefaultTableModel dtm= (DefaultTableModel) this.tableFields.getModel();
//				int pos=this.tableFields.getSelectedRow();
//				dtm.removeRow(pos);
//				this.manejador.deleteField(pos);
//			}
//		}
//	}
	
	public void fieldSelected(TField selectedField) {
		// TODO Auto-generated method stub
	}

	

	public void log(String msg) {
		msg=msg+this.consoleShadow.getText().trim();
	    this.consoleShadow.setText(msg+"<br>");
	    consoleShadow.select(0, 0);
	}

	public void logError(String msg) {
		msg="<font color='red'>" + msg + "</font>"+consoleShadow.getText().trim();
        this.consoleShadow.setText(msg+"<br>");
        consoleShadow.select(0, 0); 
	}

	public void methodSelected(TOperation operation) {
		// TODO Auto-generated method stub
	}

	public void parameterSelected(TParameter p) {
		// TODO Auto-generated method stub
	}

	public void setClass(Interface c) {
		// TODO Auto-generated method stub
	}

	public void showMembers(Interface c) {
		// TODO Auto-generated method stub
	}

	public void stateSelected(State selectedState) {
		// TODO Auto-generated method stub
	}
}

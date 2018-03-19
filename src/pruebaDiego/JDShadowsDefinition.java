package pruebaDiego;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import testooj3.domain.Interface;
import testooj3.domain.TField;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.states.State;
import testooj3.gui.components.ILogWindow;
import testooj3.gui.components.IMainWindow;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDShadowsDefinition extends JDialog implements IMainWindow{

	private JPanel contentPanel = null;
	private ILogWindow mLogWindow;
	private JTable tableParameters;
	private JScrollPane scrollPane_1;
	private JLabel lblEfects;
	private JTable tableEfects;
	private JScrollPane scrollPane_2;
	private JButton btnSave;
	
	
	private Operation method;
	private JButton btnAddParameters;
	private JButton btnDeleteParameters;
	private JButton btnAddEfects;
	private JButton btnDeleteEfects;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDShadowsDefinition dialog = new JDShadowsDefinition();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public JDShadowsDefinition() {
		super();
		initialize();
	}
	public void setLogWindow(ILogWindow v) {
	    this.mLogWindow=v;
	}
	private void initialize() {
		this.setTitle("Shadow Definition");
		this.setSize(692, 500);
		this.setContentPane(getContentJPanel());
	}

	
	private javax.swing.JPanel getContentJPanel()
	{
		if(contentPanel == null) {
			
			setBounds(100, 100, 650, 500);
			contentPanel = new javax.swing.JPanel();
			contentPanel.setLayout(null);
			
			JLabel lblInputParameters = new JLabel("Input Parameters");
			lblInputParameters.setBounds(42, 32, 129, 14);
			contentPanel.add(lblInputParameters);
			
			contentPanel.add(getTableParameters());
			
			lblEfects = new JLabel("Efects");
			lblEfects.setBounds(42, 237, 46, 14);
			contentPanel.add(lblEfects);
			
			contentPanel.add(this.getTableEfects());
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setBounds(404, 428, 76, 23);
			contentPanel.add(btnCancel);
			
			
			contentPanel.add(getBtnSave());
			
			contentPanel.add(getBtnAddParameters());
			contentPanel.add(getBtnDeleteParameters());
			contentPanel.add(getBtnAddEfects());
			contentPanel.add(getBtnDeleteEfects());
		}
		return contentPanel;
	}
	
	private JButton getBtnDeleteEfects()
	{
		btnDeleteEfects = new JButton("Delete Efects");
		btnDeleteEfects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteEfects();
			}
		});
		btnDeleteEfects.setBounds(482, 337, 129, 23);
		return btnDeleteEfects;
	}
	
	private void deleteEfects()
	{
		if(tableEfects.getSelectedRow()!=-1)
		{
			if(JOptionPane.showConfirmDialog(this, "Delete Item?", "Confirmar", JOptionPane.YES_NO_OPTION)==0)//si
			{
				DefaultTableModel dtm= (DefaultTableModel) tableEfects.getModel();
				dtm.removeRow(tableEfects.getSelectedRow());
			}
		}
	}
	
	private JButton getBtnAddEfects()
	{
		btnAddEfects = new JButton("Add Efects");
		btnAddEfects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel dtm=(DefaultTableModel)tableEfects.getModel();
				Object[] newRow=new Object[dtm.getColumnCount()];
				dtm.insertRow(dtm.getRowCount(), newRow);
			}
		});
		btnAddEfects.setBounds(482, 303, 129, 23);
		return btnAddEfects;
	}
	
	private JButton getBtnDeleteParameters()
	{
		btnDeleteParameters = new JButton("Delete Parameters");
		btnDeleteParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				deleteParameter();
			}
		});
		btnDeleteParameters.setBounds(482, 118, 129, 23);
		return btnDeleteParameters;
	}
	
	private void deleteParameter()
	{
		if(tableParameters.getSelectedRow()!=-1)
		{
			if(JOptionPane.showConfirmDialog(this, "Delete Item?", "Confirmar", JOptionPane.YES_NO_OPTION)==0)//si
			{
				DefaultTableModel dtm= (DefaultTableModel) tableParameters.getModel();
				dtm.removeRow(tableParameters.getSelectedRow());
			}
		}
	}
	
	private JButton getBtnAddParameters()
	{
		btnAddParameters = new JButton("Add Parameters");
		btnAddParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DefaultTableModel dtm=(DefaultTableModel)tableParameters.getModel();
				Object[] newRow=new Object[dtm.getColumnCount()];
				dtm.insertRow(dtm.getRowCount(), newRow);
			}
		});
		btnAddParameters.setBounds(482, 84, 129, 23);
		return btnAddParameters;
	}
	
	private JButton getBtnSave()
	{
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarShadows();
			}
		});
		btnSave.setBounds(497, 428, 76, 23);
		return this.btnSave;
	}
	
	private void guardarShadows()
	{
		System.out.println("Guardando shadows");
		obtenerParametros();
	}
	
	private void obtenerParametros()
	{
		int cantColumns=this.tableParameters.getModel().getColumnCount();
		int cantRows=this.tableParameters.getModel().getRowCount();
		for(int i=0;i<cantRows; i++)
		{
			for(int j=0; j<cantColumns; j++)
			{
				String valor=(String) this.tableParameters.getModel().getValueAt(i,j);
				System.out.println(valor);
			}
			System.out.println("********");
		}
	}
	
	private JScrollPane getTableEfects()
	{
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(42, 262, 419, 144);
		String[] columns={"Field","Value","prueba1","prueba2", "prueba3"};
		String[][] data=new String[5][5];
		DefaultTableModel dtm = new DefaultTableModel(data,columns);
		tableEfects = new JTable(dtm);
		tableEfects.setDefaultRenderer(Integer.class, new TableRenderShadow());
		scrollPane_2.setViewportView(this.tableEfects);
		return scrollPane_2;
	}
	
	private JScrollPane getTableParameters()
	{
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(42, 57, 419, 144);
//		contentPanel.add(scrollPane_1);
		
		String[] columns=new String[0];
		String[][] data=new String[0][0];
		DefaultTableModel dtm = new DefaultTableModel(data,columns);
		tableParameters = new JTable(dtm);
		
		scrollPane_1.setViewportView(tableParameters);
//		scrollPane_1.setBounds(571, 120, 2, 2);
		return scrollPane_1;
	}
	
	private void generateParameters()
	{
		Vector parametros=this.method.getParametros();
		int i=0;
		String[] nombresColumnas=new String[parametros.size()];
		while(i<parametros.size())
		{
			
			TParameter p=(TParameter)parametros.get(i);
			nombresColumnas[i]=p.getMNombre();//aca deberian estar los nombres de los parametros segun alito
			i++;
		}
		String[][] data=new String[0][parametros.size()];
		DefaultTableModel dtm= new DefaultTableModel(data,nombresColumnas);
		this.tableParameters.setModel(dtm);
		
	}
	
	public void setMethod(Operation tm)
	{
		this.method=tm;
		this.generateParameters();
	}
	
	public void fieldSelected(TField selectedField) {
		// TODO Auto-generated method stub
		
	}

	public void log(String msg) {
		// TODO Auto-generated method stub
		
	}

	public void logError(String string) {
		// TODO Auto-generated method stub
		
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

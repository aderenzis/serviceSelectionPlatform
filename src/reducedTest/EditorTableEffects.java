package reducedTest;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class EditorTableEffects extends AbstractCellEditor implements TableCellEditor{

	private Shadow shadow;
	private JComboBox fields;
	private JTextField value;
	private JCheckBox isReturn;
	private JTable table;
	private int pos;
	private int posDoMethod;
	
	public EditorTableEffects(Shadow shadow ,int posDoMethod)//, int posDomethod, int posParameter)
	{
		this.shadow=shadow;
		this.posDoMethod=posDoMethod;
	}
	
	public Component getTableCellEditorComponent(JTable tabla, Object arg1,	boolean arg2, int arg3, int arg4) 
	{
		this.table=tabla;
		this.pos=arg3;
		if(arg4==0)
		{
			this.value=null;
			this.isReturn=null;
			String selected=(String)arg1;
			fields=new JComboBox(this.vectorNameFields());
			fields.setSelectedItem(selected);
			fields.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JComboBox jcom=(JComboBox)arg0.getSource();
					String seleccionado=(String)jcom.getSelectedItem();
					if(!seleccionado.equals("none"))
					{
						table.getModel().setValueAt(ControlTypes.viewDefaultValue(shadow.typeOf(seleccionado)), pos, 1);
						table.getModel().setValueAt(seleccionado,pos,0);
					}
					else
					{
						table.getModel().setValueAt("", pos, 1);
						table.getModel().setValueAt("", pos, 0);
					}
					
					table.updateUI();
				}
			});
			return fields;
		}
		if(arg4==1)
		{
			this.fields=null;
			this.isReturn=null;
			String text=(String) arg1;
			value=new JTextField(text);
			return value;
		}
		if(arg4==2)
		{
			this.fields=null;
			this.value=null;
			Boolean bool=(Boolean)arg1;
			this.isReturn=new JCheckBox();
			DoMethod dm=this.shadow.getMethods().get(posDoMethod);
			if(dm.tieneRetorno())//temporalmente desabilidato por pruebas
				this.isReturn.setSelected(bool);
			else
				this.isReturn.setEnabled(false);
			
			this.isReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JCheckBox jcheck=(JCheckBox)arg0.getSource();
					Boolean valor=(Boolean)jcheck.isSelected();
					if(valor)
					{
						for(int i =0; i<table.getModel().getRowCount();i++)
						{
							table.getModel().setValueAt(new Boolean(false), i, 2);
						}
						table.getModel().setValueAt(new Boolean(true), pos, 2);
						table.repaint();
					}
				}
			});
			
			return this.isReturn;
		}
		return null;
	}

	public Object getCellEditorValue() {
		if(fields!=null)
			return fields.getSelectedItem();
		if(value!=null)
			return value.getText();
		if(isReturn!=null)
			return isReturn.isSelected();
		return null;
	}
	
	private Vector<String> vectorNameFields()
	{
		if(this.shadow.getCampos()!=null)
		{
			Iterator ite=this.shadow.getCampos().iterator();
			Vector<String> vectorFields=new Vector<String>();
			vectorFields.add("none");
			while(ite.hasNext())
			{
				Campo c=(Campo)ite.next();
				vectorFields.add(c.getNombre());
			}
			return vectorFields;
		}
		return null;
	}
}

package reducedTest;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class EditorTableFields extends AbstractCellEditor implements TableCellEditor {

	private Shadow shadow;
	private JComboBox types;
	private JTextField nombre;
	
	public EditorTableFields()
	{
		
	}
	public EditorTableFields(Shadow shadow)
	{
		this.shadow=shadow;
	}
	
	public Component getTableCellEditorComponent(JTable arg0, Object arg1,
			boolean arg2, int arg3, int arg4) {
//		System.out.println("paso por Editor "+arg4+" "+arg1.toString());
		if(arg4==1)
		{
			this.nombre=null;
			types=new JComboBox();
			DefaultComboBoxModel<String> cbm=new DefaultComboBoxModel<String>(ControlTypes.getTypes());
			types.setModel(cbm);
			types.setSelectedItem(shadow.getCampos().get(arg3).getTipo());
			return types;
		}
		if(arg4==0)
		{
			this.types=null;
			nombre=new JTextField(shadow.getCampos().get(arg3).getNombre());
			return nombre;
		}
		return new JLabel("Error indice");
	}

	public Object getCellEditorValue() 
	{
		if(types!=null)
			return types.getSelectedItem();
		else
		{
			if(nombre!=null)
				return nombre.getText();
			else
				return null;
		}
	}
}

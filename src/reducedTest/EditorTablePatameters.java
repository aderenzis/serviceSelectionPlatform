package reducedTest;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class EditorTablePatameters extends AbstractCellEditor implements TableCellEditor{

	private DoMethod doMethod;
	private Component comp;
	
	public EditorTablePatameters(DoMethod doMethod)
	{
		this.doMethod=doMethod;
	}
	
	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4)
	{
		String type=this.doMethod.getParametros().get(arg4).getTipo();
		if(ControlTypes.isBooleanType(type))
		{
			System.out.println("boolean");
			this.comp=new JCheckBox();
			return comp;
		}
		else
		{
			String value=(String)arg1;
			this.comp=new JTextField(value);
			return comp;
		}
	}

	public Object getCellEditorValue()
	{
		if(comp instanceof JTextField)
		{
			String valor=((JTextField)comp).getText();
			System.out.println(valor);
			return valor;
		}
		if(comp instanceof JCheckBox)
		{
			System.out.println(((JCheckBox)comp).isSelected());
			if(((JCheckBox)comp).isSelected())
				return "true";
			return "false";
		}
		return null;
	}

}

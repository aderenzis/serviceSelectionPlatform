package reducedTest;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class RenderTableParameters implements TableCellRenderer{
	
	private DoMethod doMethod;
	private Component comp;
	
	public RenderTableParameters(DoMethod doMethod)
	{
		this.doMethod=doMethod;
	}
	
	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) 
	{
		String type=doMethod.getParametros().get(arg5).getTipo();
		String value=(String)arg1;
		if(ControlTypes.isBooleanType(type))
		{
			JCheckBox check=new JCheckBox();
			check.setSelected(value.equals("true"));
			comp=check;
		}
		else
		{
			JTextField jtext=new JTextField(value);
			this.comp=jtext;
			if(ControlTypes.isStringType(type))
				this.comp.setBackground(Color.GREEN);
			else if(ControlTypes.isCharacterType(type))
			{
				if(ControlFields.isCharacter(value))
					this.comp.setBackground(Color.GREEN);
				else this.comp.setBackground(Color.RED);
			}
			else if(ControlTypes.isDecimalType(type))
			{
				if(ControlFields.isDecimal(value))
					this.comp.setBackground(Color.GREEN);
				else this.comp.setBackground(Color.RED);
			}
			else if(ControlTypes.isNumericType(type))
			{
				if(ControlFields.isInteger(value))
					this.comp.setBackground(Color.GREEN);
				else this.comp.setBackground(Color.RED);
			}
		}
		return this.comp;
	}

}

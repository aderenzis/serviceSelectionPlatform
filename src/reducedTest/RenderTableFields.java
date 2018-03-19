package reducedTest;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class RenderTableFields implements TableCellRenderer{

	private Shadow shadow;
	
	public RenderTableFields(Shadow shadow)
	{
		this.shadow=shadow;
	}
	
	public Component getTableCellRendererComponent(JTable arg0, Object arg1,
			boolean arg2, boolean arg3, int arg4, int arg5) 
	{		
		if (arg5==1)
		{
			JComboBox typeList=new JComboBox();
			DefaultComboBoxModel<String> cbm=new DefaultComboBoxModel<String>();
			String valor=(String)arg1;
			if(valor.equals(""))
				cbm.addElement("none");
			else
				cbm.addElement(valor);
			typeList.setModel(cbm);
		    return typeList;
		}
		if(arg5==0)//deberia ser un nombre de variable
		{
			String nameVar=(String)arg1;
			if(nameVar.equals(""))
				return new JTextField("field name...");
			else
			{
				JTextField text=new JTextField(nameVar);
				if(ControlFields.isVarName(nameVar))
					text.setBackground(Color.GREEN);
				else
					text.setBackground(Color.RED);
				return text;
			}
		}
		return null;
	}
	
}

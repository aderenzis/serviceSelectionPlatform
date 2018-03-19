package reducedTest;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TableModelFields implements TableModel
{
	private Shadow shadow;
	
	public TableModelFields(Shadow shadow)
	{
		this.shadow=shadow;
	}

	public void addTableModelListener(TableModelListener arg0)
	{
		
	}

	public Class<?> getColumnClass(int arg0) {
		
//		return Integer.class;
		if(arg0==0)
			return String.class;
		else
			return JComboBox.class;
	}

	public int getColumnCount() 
	{
		return 2;
	}

	public String getColumnName(int arg0) 
	{
		if(arg0==0)
			return "Name";
		if(arg0==1)
			return "Type";
		return "";
	}

	public int getRowCount() 
	{
		return this.shadow.getCampos().size();
	}

	public Object getValueAt(int arg0, int arg1) {
		if(arg1==0)
		return this.shadow.getCampos().get(arg0).getNombre();
		if(arg1==1)
		return this.shadow.getCampos().get(arg0).getTipo();
		return null;
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return true;
	}

	public void removeTableModelListener(TableModelListener arg0) {
		
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		if(arg2==0)
		{
//			System.out.println("guardando nombre");
			this.shadow.getCampos().get(arg1).setNombre((String)arg0);//arg0.toString()
		}
		if(arg2==1)
			this.shadow.getCampos().get(arg1).setTipo((String)arg0);//arg0.toString()
	}
	
}

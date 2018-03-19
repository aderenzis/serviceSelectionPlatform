package reducedTest;

import javax.swing.table.DefaultTableModel;

public class DTModelNoEditable extends DefaultTableModel{

	public DTModelNoEditable(Object[][] arg1,Object[] arg2)
	{
		super(arg1,arg2);
	}
	
	@Override
	public boolean isCellEditable(int row,int column)
	{
		return false;
	}
}

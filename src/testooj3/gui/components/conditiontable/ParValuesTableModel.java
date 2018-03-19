package testooj3.gui.components.conditiontable;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import testooj3.domain.TParameter;

public class ParValuesTableModel extends DefaultTableModel {
	
	static String [] columnNames={"Parameter","Value"};
	
	public ParValuesTableModel (int rows){
		super(columnNames,0);
		this.buildRows(rows);
	}
	
	public void buildRows(int rows){
		for (int i=0; i<rows;i++)
			this.addRow();
	}
	
	public void addRow (){
		Object [] rowData={"x"+(this.getRowCount()+1),null};
		this.addRow(rowData);
	}
	
	public boolean isCellEditable(int row, int col) {
        if (col==0)
        	return false;
        else
        	return true;
	}

	public Vector getParameterValues() {
		Vector <TParameter> parValues=new  Vector<TParameter>();
		for (int i=0;i<this.getRowCount();i++)
			if (this.getValueAt(i, 1)!=null)
				parValues.add(new TParameter(Object.class,this.getValueAt(i, 1).toString()));
		return parValues;
	}
}

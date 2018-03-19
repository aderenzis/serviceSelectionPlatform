package testooj3.gui.components.conditiontable;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import testooj3.domain.states.DetailedDescription;

public class DetailedDescripTableModel extends DefaultTableModel {
	
	static String [] columnNames={"Observer Method","Value"};
	Vector observerMethods=null;
	
	public DetailedDescripTableModel (Vector oM ){
		super();
		this.observerMethods=oM;
		this.addColumn(columnNames[0], observerMethods);
		this.addColumn(columnNames[1], new Vector());
	}
	
	public boolean isCellEditable(int row, int col) {
        if (col==0)
        	return false;
        else
        	return true;
	}

	public Vector<DetailedDescription> getDetailedDescriptions() {
		Vector<DetailedDescription> dds=new Vector<DetailedDescription>();
		for (int i=0; i<this.getRowCount(); i++){
			if (this.getValueAt(i, 1)!=null)
				dds.add(
					new DetailedDescription(
							this.getValueAt(i, 0).toString(),
							this.getValueAt(i, 1).toString()));
		}
		return dds;
	}
}

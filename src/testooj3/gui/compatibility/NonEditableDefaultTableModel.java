package testooj3.gui.compatibility;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class NonEditableDefaultTableModel extends DefaultTableModel {
	public NonEditableDefaultTableModel(Vector rows, Vector headers) {
		super(rows, headers);
	}

	public NonEditableDefaultTableModel() {
		super();
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}
}

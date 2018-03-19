package pruebaDiego;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TableRenderShadow extends DefaultTableModel implements TableCellRenderer {
	 
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	   int row, int column) {
	 
		 JLabel etiqueta = new JLabel();
		 return etiqueta;
	 
	 }
	 
}

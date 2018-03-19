package testooj3.domain.states;

import java.util.Vector;

/**
 * @author  andres
 */
public class DecisionTable {
	private String tableTitle;
	private String[][] data;
	private String[] targets;
	private Vector<String> rowTitles;

	public DecisionTable(String tableTitle, Vector<String> rowTitles, int columns) {
		this.tableTitle=tableTitle;
		this.data=new String[rowTitles.size()][columns];
		for (int i=0; i<rowTitles.size(); i++) {
			for (int j=0; j<columns; j++) {
				data[i][j]="";
			}
		}
		this.rowTitles=rowTitles;
		this.targets=new String[columns];
		for (int i=0; i<columns; i++) {
			targets[i]="";
		}
	}

	public int getRowIndex(String rowTitle) {
		return this.rowTitles.indexOf(rowTitle);
	}

	public void setValue(int rowNumber, int columnNumber, String b) {
		this.data[rowNumber][columnNumber]=b;
	}

	public void setTarget(int column, String targetState) {
		targets[column]=targetState;
	}
	
	public void removeEqualsColumns() {
		boolean hayIguales=true;
		int currentColumn=0;
		while (hayIguales) {
			Vector<Integer> ciq=this.getCondicionesIgualesQue(currentColumn);
			if (ciq.size()>0) {
				int columnaEquivalente=ciq.get(0).intValue();
				this.removeColumn(columnaEquivalente);
			} else {
				currentColumn++;
			}
			if (currentColumn==this.getNumberOfColumns())
				hayIguales=false;
		}
	}
	
	private void removeColumn(int column) {
		String[][] newData=new String[this.rowTitles.size()][this.getNumberOfColumns()-1];
		String[] newTargets=new String[this.getNumberOfColumns()-1];
		for (int i=0; i<this.getNumberOfRows(); i++) {
			int col=0;
			for (int j=0; j<this.getNumberOfColumns(); j++) {
				if (j!=column) { 
					newData[i][col]=this.data[i][j];
					newTargets[col]=this.targets[j];
					col++;
				}
			}
		}
		this.data=newData;
		this.targets=newTargets;
	}

	public String toHTML() {
		String r="";
		for (int i=0; i<this.rowTitles.size(); i++) {
			r+="<tr><td>" + this.rowTitles.get(i).toString() + "</td>";
			for (int j=0; j<this.data[0].length; j++) {
				r+="<td>" + data[i][j] + "</td>";
			}
			r+="</tr>";
		}
		r+="<tr><td>Targets</td>";
		for (int i=0; i<this.targets.length; i++) {
			r+="<td>" + this.targets[i] + "</td>";
		}
		for (int i=0; i<this.getNumberOfColumns(); i++) {
			Vector ciq=this.getCondicionesIgualesQue(i);
			String auxi="";
			if (ciq.size()>0) {
				for (int j=0; j<ciq.size(); j++)
					auxi+=ciq.get(j) + ", ";
			} 
			if (auxi.length()>0)
				r+="<tr><td colspan=" + (this.data[0].length+1) + ">Equal conditions to " + i + ": "+ auxi + "</td></tr>";
		}		
		for (int i=0; i<this.getNumberOfColumns(); i++) {
			Vector ciq=this.getCondicionesQueDifierenEn1(i);
			String auxi="";
			if (ciq.size()>0) {
				for (int j=0; j<ciq.size(); j++)
					auxi+=ciq.get(j) + ", ";
			} 
			if (auxi.length()>0)
			r+="<tr><td colspan=" + (this.data[0].length+1) + ">Groupable conditions to " + i + ": " + auxi + "</td></tr>";
		}		
		r+="</tr>";
		r="<table border=1><tr><td colspan=" + (this.data[0].length+1) + ">" + this.tableTitle + "</td></tr>" + r+ "</table>";
		return r;
	}

	public Vector<String> getRowTitles() {
		return this.rowTitles;
	}

	public void addRowTitles(Vector<String> rt) {
		for (int i=0; i<rt.size(); i++) {
			if (!this.rowTitles.contains(rt.get(i)))
				this.rowTitles.add(rt.get(i));
		}
	}

	public int getNumberOfColumns() {
		return this.data[0].length;
	}

	public int getNumberOfRows() {
		return this.rowTitles.size();
	}

	public String getValueAt(int row, int column) {
		return this.data[row][column];
	}

	public String getRowTitle(int rowIndex) {
		return this.rowTitles.get(rowIndex);
	}

	public String getTarget(int colIndex) {
		return this.targets[colIndex];
	}

	public void setNumberOfColumns(int numberOfColumns) {
		this.data=new String[this.rowTitles.size()][numberOfColumns];
	}

	public Vector<Integer> getCondicionesIgualesQue(int columnIndex) {
		String searchedColumn="";
		for (int i=0; i<this.getNumberOfRows(); i++) 
			searchedColumn+=data[i][columnIndex];
		searchedColumn+=this.targets[columnIndex];
		Vector<Integer> r=new Vector<Integer>();
		for (int i=0; i<this.getNumberOfColumns(); i++) {
			if (i!=columnIndex) {
				String c="";
				for (int j=0; j<this.getNumberOfRows(); j++) {
					c+=data[j][i];
				}
				c+=this.targets[i];
				if (c.equals(searchedColumn))
					r.add(new Integer(i));
			}
		}
		return r;
	}
	
	public Vector<Integer> getCondicionesQueDifierenEn1(int columnIndex) {
		Vector<Integer> r=new Vector<Integer>();
		for (int i=0; i<this.getNumberOfRows(); i++) {
			Vector<Integer> auxi=this.getCondicionesQueDifierenEn1EnLaFila(i, columnIndex);
			r.addAll(auxi);
		}
		return r;
	}

	public String getTableTitle() {
		return tableTitle;
	}

	public void setTableTitle(String tableTitle) {
		this.tableTitle = tableTitle;
	}
	
	public Vector<Integer> getCondicionesQueDifierenEn1EnLaFila(int rowIndex, int columnIndex) {
		Vector<Integer> r=new Vector<Integer>();
		for (int i=columnIndex+1; i<this.getNumberOfColumns(); i++) {
			int numeroDeDiferencias=1;
			if (this.getTarget(i).equals(this.getTarget(columnIndex))) {
				String vA=this.getValueAt(rowIndex, columnIndex);
				String vB=this.getValueAt(rowIndex, i);
				if (!vA.equals(vB)) {
					for (int j=0; j<this.getNumberOfRows(); j++) {
						vA=getValueAt(j, columnIndex);
						vB=getValueAt(j, i);
						if (j!=rowIndex && !vA.equals(vB)) 
							numeroDeDiferencias++;
					}
					if (numeroDeDiferencias==1)
						r.add(new Integer(i));
				}
			}
		}
		return r;
	}

	public void groupGroupableColumns() {
		for (int i=0; i<this.getNumberOfRows(); i++) {
			Vector<Integer> ciq=new Vector<Integer>();
			for (int j=0; j<this.getNumberOfColumns(); j++) {
				ciq=this.getCondicionesQueDifierenEn1EnLaFila(i, j);
				if (ciq.size()>0)
					this.setValue(i, j, "-");
				for (int k=ciq.size()-1; k>=0; k--) {
					this.removeColumn(ciq.get(k));
				}
			}
		}	
	}
	
	public Vector<Condition> getConditions() {
		Vector<Condition> result=new Vector<Condition>();
		addConditionsToTheSameTarget(result);
		return result;
	}

	private void addConditionsToTheSameTarget(Vector<Condition> result) {
		Vector<String> differentTargets=new Vector<String>();
		for (int i=0; i<this.targets.length; i++) {
			if (!differentTargets.contains(this.targets[i]))
				differentTargets.add(this.targets[i]);
		}
		for (int i=0; i<differentTargets.size(); i++) {
			String aTarget=differentTargets.get(i);
			for (int j=0; j<this.targets.length; j++) {
				if (this.targets[j].equals(aTarget)) {
					Condition c=new Condition();
					for (int k=0; k<this.getNumberOfRows(); k++) {
						String osName=this.getRowTitle(k);
						String osValue=this.getValueAt(k, j);
						if (osValue.equals("-")) 
							osValue="[any]";
						DetailedDescription dd=new DetailedDescription(osName, osValue);
						c.add(dd);
					}
					result.add(c);
				}
			}
		}
	}
}

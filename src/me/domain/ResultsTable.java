package me.domain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * @author  andres
 */
public abstract class ResultsTable {
	protected Vector<Vector> rows;
    protected String cutName;
    protected String analysisFolder;
	protected Vector<String> colTitles;

	public ResultsTable(String analysisFolder, String cutName) {
		this.analysisFolder=analysisFolder;
		this.cutName=cutName;
		this.rows = new Vector<Vector>();
		this.colTitles=new Vector<String>();
	}
	 
    public void setRowTitles(String[] titles) {
        rows=new Vector<Vector>();
        for (int i=0; i<titles.length; i++) {
            Vector<String> row=new Vector<String>();
            row.add(titles[i]);
            rows.add(row);
        }
    }    

    public Vector getRowTitles() {
        Vector<String> result=new Vector<String>();
        for (int i=0; i<rows.size(); i++) {
            Vector row=(Vector) rows.get(i);
            result.add(row.get(0).toString());
        }
        return result;
    }
    
    public String getCutName() {
        return cutName;
    }
    
    public Vector getRows() {
        return rows;
    }
    
	public String getAnalysisFolder() {
		return analysisFolder;
	}

	public abstract void saveTimesTable(FileOutputStream f, String fileName) throws IOException;
	
	public void saveAsTXTTable(FileOutputStream f, String fileName) throws IOException {
		saveHeader(f);
        f.write("\n".getBytes());
        f.write(fileName.getBytes());
        for (int i=0; i<this.getRows().size(); i++) {
        	Vector row=(Vector) this.getRows().get(i);
            f.write((row.get(0).toString() + "\t").getBytes());
            for (int j=1; j<row.size(); j++) {
                ITableValue tv=(ITableValue) row.get(j);
                String celda=tv.toString() + "\t";
                f.write(celda.getBytes());
            }
            f.write("\n".getBytes());
        }
	}
	
    protected abstract void saveHeader(FileOutputStream f) throws IOException;
    protected abstract void saveHTMLHeader(FileOutputStream f) throws IOException;

	public Vector<String> getColTitles() {
        return this.colTitles;
    }

	public void addColTitle(String methodName) {
		this.colTitles.add(methodName);
	}

    @SuppressWarnings("unchecked")
	public Vector<ITableValue> getRow(int rowNumber) {
        Vector<ITableValue> result=(Vector<ITableValue>) rows.get(rowNumber);
        return result;
    } 
    
    @SuppressWarnings("unchecked")
	public void setValueAt(String testCaseName, String mutantName, ITableValue tableValue) {
    	int rowIndex=-1, colIndex=-1;
        for (int i=0; i<rows.size(); i++) {
            Vector row=(Vector) rows.get(i);
            String rowTitle=row.get(0).toString();
            if (rowTitle.equals(mutantName)) {
                rowIndex=i;
                break;
            }
        }
        for (int i=0; i<colTitles.size(); i++) {
        	String columnName=colTitles.get(i).toString();
        	if (columnName.equals(testCaseName)) {
        		colIndex=i;
        		break;
        	}
        }
        if (rowIndex!=-1 && colIndex!=-1) {
        	Vector<ITableValue> row=(Vector<ITableValue>) rows.get(rowIndex);
        	row.add(tableValue);
        }
    }
    
    public void setValueAt(String testCaseName, String mutantName, String tableValue) {
    	int rowIndex=-1, colIndex=-1;
        for (int i=0; i<rows.size(); i++) {
            Vector row=(Vector) rows.get(i);
            String rowTitle=row.get(0).toString();
            if (rowTitle.equals(mutantName)) {
                rowIndex=i;
                break;
            }
        }
        for (int i=0; i<colTitles.size(); i++) {
        	String columnName=colTitles.get(i).toString();
        	if (columnName.equals(testCaseName)) {
        		colIndex=i;
        		break;
        	}
        }
        if (rowIndex!=-1 && colIndex!=-1) {
        	Vector<String> row=(Vector<String>) rows.get(rowIndex);
        	row.add(tableValue);
        }
    }
    

	public Vector<String> getRowWithKillResults(int index) {
		Vector row = this.getRow(index);
		Vector<String> result=new Vector<String>();
		for (int i=0; i<row.size(); i++) {
			Object valor=row.get(i);
			result.add(valor.toString());
		}
		return result;
	}

	public void saveAsHTMLTable(FileOutputStream f, String fileName) throws IOException {
		f.write("<table border='1'>".getBytes());
		saveHTMLHeader(f);
        f.write("\n".getBytes());
        f.write(fileName.getBytes());
        f.write("\n".getBytes());
        for (int i=0; i<this.getRows().size(); i++) {
        	Vector row=(Vector) this.getRows().get(i);
            f.write(("<tr><td>" + row.get(0).toString() + "</td>").getBytes());
            for (int j=1; j<row.size(); j++) {
                ITableValue tv=(ITableValue) row.get(j);
                String celda=tv.toHTML();
                f.write(celda.getBytes());
            }
            f.write("</tr>\n".getBytes());
        }
        f.write("</table>".getBytes());
	}
}

package me.domain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import me.domain.algorithms.PriorizationAlgorithm;

/**
 * @author  Administrador
 */
public class ObjectTable extends ResultsTable {
    private PriorizationAlgorithm algorithm;
    
    public ObjectTable(String analysisFolder, String cutName) {
        super(analysisFolder, cutName);
    }

    @SuppressWarnings("unchecked")
	public Vector<ITableValue> getDataRow(int rowNumber) {
        Vector<ITableValue> row=(Vector<ITableValue>) rows.get(rowNumber);
        Vector<ITableValue> result=new Vector<ITableValue>();
        for (int i=1; i<row.size(); i++)
            result.add(row.get(i));
        return result;
    } 
    
    public Vector priorizeTestCases(Vector<String> aliveMutants) {
        return this.algorithm.priorizeTestCases(aliveMutants);
    }

    public void setAlgorithm(PriorizationAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

	@Override
	public void saveTimesTable(FileOutputStream f, String fileName) throws IOException {
		return;
	}

	@Override
	protected void saveHeader(FileOutputStream f) throws IOException {
        for (int i=0; i<this.getColTitles().size(); i++) {
            String celda=this.getColTitles().get(i) + "\t";
            f.write(celda.getBytes());
        }
	}

	@Override
	protected void saveHTMLHeader(FileOutputStream f) throws IOException {
		f.write("<tr><th></th>".getBytes());
        for (int i=1; i<this.getColTitles().size(); i++) {
            String celda="<td>" + this.getColTitles().get(i) + "</td>";
            f.write(celda.getBytes());
        }
        f.write("</tr>".getBytes());
	}

	public PriorizationAlgorithm getAlgorithm() {
		return this.algorithm;
	}

}


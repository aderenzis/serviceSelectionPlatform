package me.domain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Administrador
 */
public class TimesTable extends ResultsTable {
    public TimesTable(String analysisFolder, String cutName) {
        super(analysisFolder, cutName);
    }

	@Override
	public void saveTimesTable(FileOutputStream f, String fileName) throws IOException {
		f.write("\t".getBytes());
		f.write("Killed\t".getBytes());
        for (int i=1; i<this.getColTitles().size(); i++) {
            String celda=this.getColTitles().get(i) + "\t";
            f.write(celda.getBytes());
        }
        f.write("\n".getBytes());
        f.write(fileName.getBytes());
        boolean isKilled=false;
        for (int i=0; i<this.getRows().size(); i++) {
        	Vector row=(Vector) this.getRows().get(i);
            f.write((row.get(0).toString() + "\t").getBytes());
            isKilled=false;
            for (int j=1; j<row.size(); j++) {
                TimeValue time=(TimeValue) row.get(j);
                if (time.isKilled()) {
                	isKilled=true;
                	break;
                }
            }
            if (isKilled) {
            	f.write("1\t".getBytes());
            } else f.write("0\t".getBytes());
            for (int j=1; j<row.size(); j++) {
                TimeValue time=(TimeValue) row.get(j);
                String celda=time.getTime() + "\t";
                f.write(celda.getBytes());
            }
            f.write("\n".getBytes());
        }
	}

	@Override
	protected void saveHeader(FileOutputStream f) throws IOException {
        for (int i=0; i<this.getColTitles().size(); i++) {
            String celda=this.getColTitles().get(i) + "\t\t";
            f.write(celda.getBytes());
        }
	}

	@Override
	protected void saveHTMLHeader(FileOutputStream f) throws IOException {
		f.write("<tr><th></th>".getBytes());
        for (int i=1; i<this.getColTitles().size(); i++) {
            String celda="<th colspan=2>" + this.getColTitles().get(i) + "</th>";
            f.write(celda.getBytes());
        }
        f.write("</tr>".getBytes());
	}
	

	
}


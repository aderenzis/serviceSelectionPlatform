package testooj3.domain.algorithms.pairwise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import testooj3.domain.Configuration;
import testooj3.domain.TestValue;

public class TableEntryVector {
/*	private int id;
	private int size;
	private Vector<TableEntry> tmp;
	private int capacity;

	public TableEntryVector(int numeroDeCombinaciones) {
		this.id=0; //(new java.util.Random()).nextInt();		
		this.size=numeroDeCombinaciones;
		this.capacity=5000;
		this.tmp=new Vector<TableEntry>();
	}

	public void put(int pos, TableEntry te) {
		if (pos%capacity!=capacity-1) {
			tmp.add(te);
		} else {
			tmp.add(te);
			save(pos);
			tmp.removeAllElements();
		} 
	}

	private void save(int pos) {
		int fileIndex=pos/capacity;
		String fileName=Configuration.getInstance().getTempPath() + "combinations\\" + id + "." + fileIndex;
		try {
			FileOutputStream fo=new FileOutputStream(fileName);
			ObjectOutputStream os=new ObjectOutputStream(fo);
			os.writeObject(this.tmp);
			fo.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TableEntry get(int pos) {
		if (size<capacity)
			return this.tmp.get(pos);
		int fileIndex=pos/capacity;
		String fileName=Configuration.getInstance().getTempPath() + "combinations\\" + id + "." + fileIndex;
		try {
			FileInputStream fi=new FileInputStream(fileName);
			ObjectInputStream oi=new ObjectInputStream(fi);
			this.tmp=(Vector<TableEntry>) oi.readObject();
			fi.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int localPos=pos%capacity;
		return this.tmp.get(localPos);
	}

	public int size() {
		return this.size;
	}

	public void setValue(int pos, int parIndex, TestValue testValue) {
		TableEntry te=this.get(pos);
		te.setValue(parIndex, testValue);
		save(pos);
	}

	public void buildFiles(int numberOfParameters) {
		int numberOfFiles=this.size/this.capacity;
		if (this.size%this.capacity>0) 
			numberOfFiles+=1;
		Vector<TableEntry> auxi=new Vector<TableEntry>();
		for (int i=0; i<this.capacity; i++) {
			TableEntry te=new TableEntry(numberOfParameters);
			auxi.add(te);
		}
		for (int i=0; i<numberOfFiles; i++) {
			String fileName=Configuration.getInstance().getTempPath() + "combinations\\" + id + "." + i;
			try {
				FileOutputStream fo=new FileOutputStream(fileName);
				ObjectOutputStream os=new ObjectOutputStream(fo);
				os.writeObject(auxi);
				fo.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/

	
}

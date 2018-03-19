package testooj3.domain.algorithms.pairwise;

import java.util.Vector;

import testooj3.domain.TParameter;
import testooj3.domain.TestTemplate;

public class CombinationsTable {
	private Vector<ByteTableEntry> tabla;
	
	public CombinationsTable(TestTemplate template) throws Exception {		
		Vector<TParameter> parametros=template.getParameters();
		int numeroDeParametros=parametros.size();
		int numeroDeCombinaciones=template.getNumberOfCombinations();
		this.tabla=new Vector<ByteTableEntry>(numeroDeCombinaciones);
		for (int i=0; i<numeroDeCombinaciones; i++)
			this.tabla.add(new ByteTableEntry(numeroDeParametros));
		
		int divisores[]=new int[numeroDeParametros];
		for (int i=0; i<numeroDeParametros; i++) {
			int divisor=1;
			for (int j=i+1; j<numeroDeParametros; j++) {
				TParameter auxi=parametros.get(j);
				divisor=divisor*auxi.getTestValues().length;
			}
			divisores[i]=divisor;
		}
		for (int i=0; i<numeroDeParametros; i++) {
			TParameter p=parametros.get(i);
			for (int j=0; j<numeroDeCombinaciones; j++) {
				int posTestValue=(j/divisores[i])%p.getTestValues().length;
				ByteTableEntry te=this.tabla.get(j);
				te.setValue(i, (byte) posTestValue);
			}
		}
	}

	public String toString() {
		String r="";
		for (int i=0; i<this.tabla.size(); i++) {
			r+=this.tabla.get(i).toString() + "\n";
		}
		return r;
	}

	public int size() {
		return this.tabla.size();
	}

	public ByteTableEntry get(int i) {
		return this.tabla.get(i);
	}

}

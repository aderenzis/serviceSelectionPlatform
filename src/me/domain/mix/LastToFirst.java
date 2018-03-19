package me.domain.mix;


public class LastToFirst extends MixAlgorithm {


	public LastToFirst() {
	}

	public void mix() {
		int pendientes=this.versions.length;
		int posA=-1, posB=pendientes;
		int contador=1;
		int posicionOriginal=posicion(this.original);
		while (posA<posB) {
			posA++; posB--;
			if (posA==posicionOriginal)
				posA++;
			else if (posB==posicionOriginal)
				posB--;
			String fileA=this.versions[posA].toString();
			String fileB=this.versions[posB].toString();
			mix(contador++, fileA, fileB);
		}		
		copyOriginal();
	}
}

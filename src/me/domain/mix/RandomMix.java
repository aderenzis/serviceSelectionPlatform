package me.domain.mix;

import java.util.Random;
import java.util.Vector;

public class RandomMix extends MixAlgorithm {

	@Override
	public void mix() {
		Vector<String> vVersions=new Vector<String>();
		for (int i=0; i<versions.length; i++)
			vVersions.add(versions[i].toString());
		int posicionOriginal=posicion(this.original);
		vVersions.remove(posicionOriginal);
		int contador=1;
		while (vVersions.size()>1) {
			Random r=new Random();
			int posA=r.nextInt(vVersions.size());
			int posB;
			do {
				posB=r.nextInt(vVersions.size());
			} while (posA==posB);
			String fileA=vVersions.get(posA).toString();
			String fileB=vVersions.get(posB).toString();
			mix(contador++, fileA, fileB);
			vVersions.remove(posA);
			if (posB>posA)
				vVersions.remove(posB-1);
			else vVersions.remove(posB);
		}
		if (vVersions.size()==1) {
			int posB=(new Random()).nextInt(this.versions.length);
			String fileA=vVersions.get(0).toString();
			String fileB=this.versions[posB].toString();
			mix(contador++, fileA, fileB);
		}
		copyOriginal();
	}

}

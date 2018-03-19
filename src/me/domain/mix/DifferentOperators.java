package me.domain.mix;

import java.util.Vector;

public class DifferentOperators extends MixAlgorithm {

	@Override
	public void mix() {
		Vector<Pair> vVersions=new Vector<Pair>();
		for (int i=0; i<versions.length; i++) {
			Pair pair=new Pair(versions[i].toString());
			vVersions.add(pair);
		}
		int posicionOriginal=posicion(this.original);
		vVersions.remove(posicionOriginal);
		int contador=1;
		while (hayVersionesNoVisitadas(vVersions)) {
			int posA=getVersion(vVersions);
			String mutantName=getMutantName(vVersions.get(posA).getVersion());
			int posB=getVersionNotMutatedBy(vVersions, mutantName);
			if (posB==-1) {
				posB=getTheVersionLeastUsedNotMutatedBy(vVersions, mutantName);
			}
			visit(vVersions, posA);
			visit(vVersions, posB);
			String fileA=vVersions.get(posA).getVersion();
			String fileB=vVersions.get(posB).getVersion();
			System.out.println(posA + ", " + posB + ":\n\t" + fileA + "\n\t" + fileB);
			mix(contador++, fileA, fileB);
		}
		copyOriginal();
	}

	private void visit(Vector<Pair> versions, int pos) {
		Pair pair=versions.get(pos);
		pair.visit();
	}

	private int getVersionNotMutatedBy(Vector<Pair> vVersions, String mutantName) {
		for (int i=0; i<vVersions.size(); i++) {
			Pair pair=vVersions.get(i);
			String version=pair.getVersion();
			if (!this.getMutantName(version).equals(mutantName)) {
				if (pair.getTimesUsed()==0)
					return i;
			}
		}
		return -1;
	}

	private int getTheVersionLeastUsedNotMutatedBy(Vector<Pair> vVersions, String mutantName) {
		int min=Integer.MAX_VALUE;
		int posMin=0;
		for (int i=0; i<vVersions.size(); i++) {
			Pair pair=vVersions.get(i);
			String version=pair.getVersion();
			if (!this.getMutantName(version).equals(mutantName)) {
				if (pair.getTimesUsed()<min) {
					min=pair.getTimesUsed();
					posMin=i;
				}
			}
		}
		return posMin;
	}

	private String getMutantName(String version) {
		if (version.endsWith("/")) version=version.substring(0, version.length()-1);
		version=version.substring(version.lastIndexOf("/")+1);
		version=version.substring(0, version.indexOf("_"));
		return version;
	}

	private int getVersion(Vector<Pair> versions) {
		for (int i=0; i<versions.size(); i++) {
			Pair pair=versions.get(i);
			if (pair.getTimesUsed()==0)
				return i;
		}
		return -1;
	}

	private boolean hayVersionesNoVisitadas(Vector<Pair> versions) {
		for (int i=0; i<versions.size(); i++) {
			Pair pair=versions.get(i);
			if (pair.getTimesUsed()==0)
				return true;
		}
		return false;
	}

}

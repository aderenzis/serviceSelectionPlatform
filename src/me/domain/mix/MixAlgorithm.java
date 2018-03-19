package me.domain.mix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import testooj3.auxiliares.Auxi;
import testooj3.gui.components.ILogWindow;


/**
 * @author  andres
 */
public abstract class MixAlgorithm {
	protected ILogWindow logWindow;
	protected String className;
	protected String sourceVersionsFolder;
	protected String targetVersionsFolder;
	protected Object[] versions;
	protected String packageName;
	protected String original;
	
	public MixAlgorithm() {
	}
	
	public abstract void mix();

	protected void mix(int contador, String fileA, String fileB) {
		try {
			String a=fileA, b=fileB;
			if (fileA.endsWith("/")) 
				a=fileA.substring(0, fileA.length()-1);
			if (fileB.endsWith("/")) 
				b=fileB.substring(0, fileB.length()-1);
			a=a.substring(a.lastIndexOf("/")+1);
			b=b.substring(b.lastIndexOf("/")+1);
			String thePackage=Auxi.substituteAll(this.packageName, ".", "/");
			thePackage=Auxi.substituteAll(thePackage, "\\", "/");
			fileA+=thePackage + "/" + this.className + ".java";
			fileB+=thePackage + "/" + this.className + ".java";
			byte[] bA = readFile(fileA);
			byte[] bB = readFile(fileB);
			byte[] bOriginal=readFile(this.original + this.className + ".java");
			Vector<String> lineasA=toLinesVector(bA);
			Vector<String> lineasB=toLinesVector(bB);
			Vector<String> lineasOriginal=toLinesVector(bOriginal);
			int max=lineasA.size()>lineasB.size() ? lineasA.size() : lineasB.size();
			max=lineasOriginal.size()>max ? lineasOriginal.size() : max; 
			String result="";
			for (int i=0; i<max; i++) {
				String lineaOriginal=lineasOriginal.get(i);
				String lineaA=lineasA.get(i);
				String lineaB=lineasB.get(i);
				if (!lineaA.equals(lineaOriginal))
					result+=lineaA+"\n";
				else if (!lineaB.equals(lineaOriginal))
					result+=lineaB+"\n";
				else result+=lineaOriginal+"\n";
			}
			save(contador, a, b, result);
		}
		catch (Exception e) {
			this.logWindow.logError(e.toString());
		}
	}
	
	protected void copyOriginal() {
		try {
			FileInputStream f=new FileInputStream(this.original + this.className + ".java");
			byte[] b=new byte[f.available()];
			f.read(b);
			f.close();
			String foPath=this.targetVersionsFolder + this.packageName + "." + this.className + "/original";
			new File(foPath).mkdir();
			StringTokenizer st=new StringTokenizer(this.packageName, ".");
			while (st.hasMoreTokens()) {
				String token=st.nextToken();
				foPath+="/" + token;
				new File(foPath).mkdir();
			}			
			foPath+="/" + this.className + ".java";
			FileOutputStream fo=new FileOutputStream(foPath);
			fo.write(b);
			fo.close();
		} catch (Exception e) {
			this.logWindow.logError("Error reading and/or copying the original file: " + e.toString());
		}		
	}
	
	private void save(int contador, String a, String b, String result) {
		String fileName=this.targetVersionsFolder + this.packageName + "." + this.className;
		new File(fileName).mkdir();
		fileName+="/" + contador + "." + a + "." + b;
		new File(fileName).mkdir();
		
		StringTokenizer st=new StringTokenizer(this.packageName, ".");
		while (st.hasMoreTokens()) {
			String token=st.nextToken();
			fileName+="/" + token;
			new File(fileName).mkdir();
		}
		try {
			FileOutputStream f=new FileOutputStream(fileName + "/" + this.className + ".java");
			f.write(result.getBytes());
			f.close();
			this.logWindow.log("Written: " + fileName + "/" + this.className);
		} catch (Exception e) {
			this.logWindow.logError(e.toString());
		}
	}
	
	private byte[] readFile(String fileA) throws FileNotFoundException, IOException {
		FileInputStream fA=new FileInputStream(fileA);
		int sizeA=fA.available();
		byte[] bA=new byte[sizeA];
		fA.read(bA);
		fA.close();
		return bA;
	}
	
	private Vector<String> toLinesVector(byte[] b) {
		String texto=new String(b);
		StringTokenizer st=new StringTokenizer(texto, "\n");
		Vector<String> lineas=new Vector<String>();
		while (st.hasMoreTokens()) {
			lineas.add(st.nextToken());
		}
		return lineas;
	}
	
	protected int posicion(String fichero) {
		for (int i=0; i<this.versions.length; i++)
			if (this.versions[i].toString().equals(fichero))
				return i;
		return 0;
	}
	
	public void setLogWindow(ILogWindow logWindow) {
		this.logWindow=logWindow;
	}

	public void setCUTName(String className) {
		this.className=className;
	}

	public void setSourceVersionsFolder(String sourceVersionsFolder) {
		this.sourceVersionsFolder=sourceVersionsFolder;
	}

	public void setTargetVersionsFolder(String targetVersionsFolder) {
		this.targetVersionsFolder=targetVersionsFolder;
	}

	public void setVersions(Object[] versions) {
		this.versions=versions;
	}

	public void setPackage(String packageName) {
		this.packageName=packageName;
	}

	public void setOriginal(String original) {
		this.original=original;
	}
}

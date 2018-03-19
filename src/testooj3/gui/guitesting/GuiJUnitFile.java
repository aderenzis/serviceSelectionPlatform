package testooj3.gui.guitesting;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TestMethod;
import testooj3.domain.testfiles.junit.JUnitFile;

/**
 * @author  andres
 */
public class GuiJUnitFile extends JUnitFile {
    private Vector<GuiFixture> fixtures;
	private Vector<GuiSetup> setUps;

	public GuiJUnitFile(String className, Interface c) {
        super(className, c);
        this.mHeader=new GuiJUnitHeader(className, c);
    }

	public void setFixtures(Vector<GuiFixture> fixtures) {
		this.fixtures=fixtures;
	}

	public void setSetups(Vector<GuiSetup> setUps) {
		this.setUps=setUps;
	}
	
    public int saveTo(FileOutputStream f, int firstTestCase) throws Exception {
        String result = mHeader.toString() + "\n";
        f.write(result.getBytes());
       	String texto;
    	for (GuiFixture gf : this.fixtures) {
    		texto="\tprotected " + gf.toString() + "\n";
    		f.write(texto.getBytes());
    	}
    	texto="\n";
		f.write(texto.getBytes());
    	saveSetUp(f);
        int totalSize=0, lastTestCase=0;
        int i=firstTestCase;
        while (i<=this.mNumberOfTestCases && totalSize<Configuration.getInstance().getSplitJUnitEvery()) {
            String fileName = mSessionPath + "\\" + i;
            TestMethod m = TestMethod.load(fileName, this.sessionType);
            result = m.toString(false) + "\n";
            f.write(result.getBytes());
            totalSize+=result.getBytes().length;
            i++;
        }
        lastTestCase=i;
        result = getMain() + "\n";
        f.write(result.getBytes());
        saveAdditionalMethods(f);
        result="\n}";
        f.write(result.getBytes());
        return lastTestCase;
    }

	private void saveAdditionalMethods(FileOutputStream f) throws IOException {
		saveHabilita(f);
		saveFindFieldInstance(f);
		saveFindFields(f);
	}

	private void saveFindFields(FileOutputStream f) throws IOException {
		String s="\tprivate void findFields(Vector<Field> result, Class c, String fieldName) {\n" +
			"\t\tField[] ff=c.getDeclaredFields();\n" +
			"\t\tif (ff.length==0)\n" +
			"\t\t\treturn;\n" +
			"\t\tfor(Field f : ff) {\n" +
			"\t\t\tif (f.getName().equalsIgnoreCase(fieldName))\n" +
			"\t\t\t\tresult.add(f);\n" +
			"\t\t\tfindFields(result, f.getType(), fieldName);\n" +
			"\t\t}\n" +
			"\t}\n";
		f.write(s.getBytes());
	}

	private void saveFindFieldInstance(FileOutputStream f) throws IOException {
		String s="\tprivate Object findFieldInstance(Object o, String fieldName) throws IllegalArgumentException, IllegalAccessException {\n" +
			"\t\tif (o==null) return null;\n" +
		"\t\tif (o.getClass().getDeclaredFields().length==0)\n" +
		"\t\t\treturn null;\n" +
		"\t\tField[] ff=o.getClass().getDeclaredFields();\n" +
		"\t\tfor (int i=0; i<ff.length; i++) {\n" +
		"\t\t\tField f=ff[i];\n" +
		"\t\t\tf.setAccessible(true);\n" +
		"\t\t\tString fn=f.getName();\n" +
		"\t\t\tif (fn.equalsIgnoreCase(fieldName))\n" +
		"\t\t\t\treturn f.get(o);\n" +
		"\t\t\telse if (!f.getType().isPrimitive() && !f.getType().equals(String.class)) {\n" +
		"\t\t\t\tObject contenedor=f.get(o);\n" +
		"\t\t\t\tObject result=findFieldInstance(contenedor, fieldName);\n" +
		"\t\t\t\tif (result!=null) return result;\n" +
		"\t\t\t}\n" +
		"\t\t}\n" +
		"\t\treturn null;\n" +
		"\t}\n\n";
		f.write(s.getBytes());
	}

	private void saveHabilita(FileOutputStream f) throws IOException {
		String s="\tprivate void habilita(Class c) {\n" +
			"\t\tfor (Field f : c.getDeclaredFields()) {\n" +
				"\t\t\tf.setAccessible(true);\n" +
				"\t\t\tfor(Field sf : f.getType().getDeclaredFields()) {\n" +
					"\t\t\t\thabilita(sf.getType());\n" +
				"\t\t\t}\n" +
			"\t\t}\n" + 
		"\t}\n\n";
		f.write(s.getBytes());
	}

	private void saveSetUp(FileOutputStream f) throws IOException {
		String texto="\tpublic void setUp() {\n";
		f.write(texto.getBytes());
		texto="\t\ttry {\n";
		f.write(texto.getBytes());
		texto="\t\t\t" + this.mClassName + " o=new " + this.mClassName + "();\n";
		f.write(texto.getBytes());
		texto="\t\t\thabilita(o.getClass());\n";
		f.write(texto.getBytes());
		for (GuiSetup gs : this.setUps) {
			texto="\t\t\t" + gs.toString() + "\n";
			f.write(texto.getBytes());
		}
		texto="\t\t} catch (Exception ex) {}\n";
		f.write(texto.getBytes());
		texto="\n\t}\n\n";
		f.write(texto.getBytes());
	}
}

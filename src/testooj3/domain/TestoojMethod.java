package testooj3.domain;

import java.util.Vector;
import testooj3.auxiliares.Auxi;

public class TestoojMethod extends TestMethod {
	public TestoojMethod(String nombre, boolean lanzaExcepciones) {
		mNombre = nombre;
		mLineas = new Vector();
		mMutantes = new Vector();
		mLanzaExcepciones = lanzaExcepciones;
	}

	public TestoojMethod copy(int msgNum) {
		TestoojMethod r = new TestoojMethod(mNombre, this.mLanzaExcepciones);
		r.setTipo(new String(mTipo));
		for (int i = 0; i < mLineas.size(); i++) {
			String linea = new String(mLineas.elementAt(i).toString());
			int pos = linea.indexOf("msg[0]+=");
			if (pos != -1) {
				String resto = linea.substring(pos + 8, linea.length());
				linea = linea.substring(0, pos) + "msg[" + msgNum + "]+="
						+ resto;
			}
			r.addLinea(linea);
		}
		return r;
	}

	public String toString(boolean forLotOfTC) {
		String r = "\tpublic " + mTipo + " " + mNombre;
		if (forLotOfTC)
			r += "() throws Exception {\n";
		else if (!this.mLanzaExcepciones)
			r += "(Vector messages) throws Exception {\n";
		else
			r += "(String[] msg) throws Exception {\n";
		for (int i = 0; i < mLineas.size(); i++) {
			r += getLinea(i);
		}
		r += "\t}\n";
		return r;
	}

	public void add(TestoojMethod mutante) {
		this.mMutantes.addElement(mutante);
	}

	public String getOriginalYMutantes(boolean forLotOfTC) {
		String r = this.toString(forLotOfTC) + "\n";
		for (int i = 0; i < this.mMutantes.size(); i++) {
			TestoojMethod mutante = (TestoojMethod) mMutantes.elementAt(i);
			r += mutante.toString(forLotOfTC) + "\n";
		}
		return r;
	}

	public String getMetodoCheck() {
		String r = "\tpublic void check" + this.mNombre + "() {\n";
		if (mLanzaExcepciones) {
			r += "\t\tString[] msg={\"\", ";
			for (int i = 0; i < mMutantes.size(); i++)
				r += "\"\", ";
			r = r.substring(0, r.length() - 2);
			r += "};\n";
		}
		r += "\t\t" + mTipo + " original=null;\n";
		for (int i = 0; i < mMutantes.size(); i++) {
			r += "\t\t" + mTipo + " o" + (i + 1) + "=null;\n";
		}
		TestoojMethod jum;
		r += "\t\ttry { original=" + mNombre;
		if (!mLanzaExcepciones)
			r += "(); ";
		else
			r += "(msg); ";
		r += "} catch (Exception e) { }\n";
		r += "\tString xml=\"<Original\" + (msg[0].length()==0 ? \"\" : \" error='\" + msg[0] + \"'\" ) + \">\" + "
				+ "(original!=null ? original.toString() : \"[null]\")+ \"</Original>\";\n";
		for (int i = 0; i < mMutantes.size(); i++) {
			jum = (TestoojMethod) mMutantes.elementAt(i);
			r += "\t\ttry { o" + (i + 1) + "=" + jum.mNombre;
			if (!jum.mLanzaExcepciones)
				r += "(); ";
			else
				r += "(msg); ";
			r += "} catch (Exception e) { }\n";
			r += "\t\txml+=compare(original, o" + (i + 1) + ", \""
					+ jum.mNombre + "\", msg[0], msg[" + (i + 1) + "]);\n";
		}
		String otro;
		r += "\t\tString cab=\"" + mNombre + ";";
		for (int i = 0; i < mMutantes.size(); i++) {
			otro = "o" + (i + 1);
			jum = (TestoojMethod) mMutantes.elementAt(i);
			r += otro + ";";
		}
		r = r.substring(0, r.length() - 1) + "\\n";
		r += "\";\n";
		r += "\t\tString lin=(original==null ? \"null\" : original.toString()) + ";
		if (mLanzaExcepciones) {
			r += "\" / \" + msg[0] +  ";
		}
		r += "\";\" + \n";
		for (int i = 0; i < mMutantes.size(); i++) {
			otro = "o" + (i + 1);
			jum = (TestoojMethod) mMutantes.elementAt(i);
			r += "\t\t\t(" + otro + "==null ? \"null\" : " + otro
					+ ".toString()) + ";
			if (jum.mLanzaExcepciones) {
				r += "\" / \" + msg[" + (i + 1) + "] + ";
			}
			r += "\n";
		}
		r = r.substring(0, r.length() - 4) + ";\n";
		r += "\t\tlin+=\"\\n\";\n";
		r += "\txml=\"<TestCase name='" + mNombre
				+ "'>\" + xml + \"</TestCase>\";\n";
		r += "\t\ttry { mF.write(cab.getBytes()); mF.write(lin.getBytes()); mXML.write(xml.getBytes()); } catch (Exception e) {}\n";
		r += "\t}\n\n";
		return r;
	}

	public void substitutePatterns() {
		for (int j = 0; j < getLineas().size(); j++) {
			String linea = getLinea(j);
			if (linea.indexOf("PATTERN_NAME") != -1)
				setLinea(j, Auxi
						.substitute(linea, "PATTERN_NAME", this.mNombre));
		}
		for (int i = 0; i < mMutantes.size(); i++) {
			TestoojMethod mutante = (TestoojMethod) mMutantes.elementAt(i);
			for (int j = 0; j < mutante.getLineas().size(); j++) {
				String linea = mutante.getLinea(j);
				if (linea.indexOf("PATTERN_NAME") != -1)
					mutante.setLinea(j, Auxi.substitute(linea, "PATTERN_NAME",
							mutante.mNombre));
			}
		}
	}

	public TestMethod copy(String name) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see testooj3.domain.TestMethod#getExtension()
	 */
	protected String getExtension() {
		return "testooj";
	}
}
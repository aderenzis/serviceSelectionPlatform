package testooj3.gui.guitesting;

import java.net.MalformedURLException;
import java.util.Vector;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TField;
import testooj3.domain.Operation;
import testooj3.domain.TestoojClassLoader;

/**
 * @author  andres
 */
public class GuiSetup {

	private TField field;

	public GuiSetup(TField field) {
		this.field=field;
	}

	public String getName() {
		return this.field.getName();
	}

	public String getTypeName() {
		return this.field.getTypeName();
	}

	public Vector<TField> getSubFields() {
		return this.field.getSubFields();
	}
	
	public Vector<Operation> getMethods() {
		String tn=this.field.getTypeName();
		String classPath=Configuration.getInstance().getClassPath();
		Interface tc=null;
		try {
			tc = TestoojClassLoader.load(tn, classPath, true);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tc.getMethods();
	}
	
	public String toString() {
		String texto=this.field.getName() + "=(" +
    	this.field.getTypeName() + ") findFieldInstance(o, \"" + this.field.getName() + "\");";
		return texto;
	}
}

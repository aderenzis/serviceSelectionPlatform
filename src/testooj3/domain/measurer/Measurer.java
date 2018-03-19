package testooj3.domain.measurer;

import java.util.Hashtable;
import java.util.Vector;
import testooj3.auxiliares.Auxi;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.TField;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestoojClassLoader;
import testooj3.gui.IMeasurerWindow;



/**
 * @author  andres
 */
public class Measurer implements Runnable {
	private static String[] headers=new String[] {"Class", "DIT", "CBO", "Constructors", "Methods", "Modifiers"};
	private String classPath;
	private Vector<String> classNames;
	private IMeasurerWindow window;

	public Measurer(String classPath, Vector<String> classes, IMeasurerWindow window) {
		this.classPath=classPath;
		this.classNames=classes;
		this.window=window;
	}
	
	public static String[] getHeaders() {
		return headers;
	}

	public void run() {
		for (int i=0; i<classNames.size(); i++) {
			String className=classNames.get(i);
			className=Auxi.substituteAll(className, "/", ".");
			className=Auxi.substituteAll(className, "\\", ".");
			if (className.startsWith("."))
				className=className.substring(1);
			Vector row=new Vector();
			try {
				Interface c=TestoojClassLoader.load(className, this.classPath, false);
				row.add(c.getName());
				row.add(dit(c));
				row.add(cbo(c));
				row.add(noc(c));
				row.add(nom(c));
				row.add(modifiers(c));
			} catch (Exception e) {
				row.add(className);
				for (int j=1; j<headers.length; j++)
					row.add("Error");
			}
			this.window.addRow(i, row);
		}
	}

	private int dit(Interface tc) {
		int result=0;
		Class c=tc.getJavaClass();
		while (c.getSuperclass()!=null) {
			result++;
			c=c.getSuperclass();
		}
		return result;
	}

	private int cbo(Interface tc) {
		int result=0;
		Hashtable<String,String> tipos=new Hashtable<String,String>();
		for (int i=0; i<tc.getConstructors().size(); i++) {
			TConstructor c=tc.getConstructor(i);
			for (int j=0; j<c.getParametros().size(); j++) {
				TParameter p=c.getParametro(j);
				if (tipos.get(p.getTipo())==null) {
					tipos.put(p.getTipo(), p.getTipo());
					result++;
				}
			}
		}
		for (int i=0; i<tc.getMethods().size(); i++) {
			Operation m=tc.getMethod(i);
			if (tipos.get(m.getTipo())==null) {
				tipos.put(m.getTipo(), m.getTipo());
				result++;
			}
			for (int j=0; j<m.getParametros().size(); j++) {
				TParameter p=m.getParametro(j);
				if (tipos.get(p.getTipo())==null) {
					tipos.put(p.getTipo(), p.getTipo());
					result++;
				}
			}
		}
		for (int i=0; i<tc.getFields().size(); i++) {
			TField f=(TField) tc.getFields().get(i);
			if (tipos.get(f.getTypeName())==null) {
				tipos.put(f.getTypeName(), f.getTypeName());
				result++;
			}
		}
		return result;
	}
	
	private int noc(Interface tc) {
		return tc.getConstructors().size();
	}
	
	private int nom(Interface tc) {
		return tc.getMethods().size();
	}
	
	private int modifiers(Interface tc) {
		int result=tc.getMethods().size();
		for (int i=0; i<tc.getMethods().size(); i++) {
			Operation m=tc.getMethod(i);
			if (!m.getTipo().equals("void") && m.getParametros().size()==0)
				result--;
		}
		return result;
	}
}

package testooj3.gui.guitesting;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;

public class ListOfMethods {
	protected Vector<Method> methods;
	
	public ListOfMethods() {
		this.methods=new Vector<Method>();
	}
	
	public ListOfMethods(Class c) {
		this();
		Method[] mm = c.getDeclaredMethods();
		for(Method m : mm)
			if (Modifier.isPublic(m.getModifiers()))
				add(m);
		mm = c.getMethods();
		for(Method m : mm)
			if (Modifier.isPublic(m.getModifiers()))
				add(m);
	}
	
	public void add(Method m) {
		if (this.methods.contains(m))
			return;
		if (this.methods.size()==0) {
			this.methods.add(m);
			return;
		}
		int i=0;
		while (i<this.methods.size() && this.methods.get(i).getName().compareTo(m.getName())<0)
			i++;
		if (i==this.methods.size())
			this.methods.add(m);
		else this.methods.insertElementAt(m, i);
	}

	public int size() {
		return this.methods.size();
	}
	
	public Method get(int index) {
		return this.methods.get(index);
	}
	
	public String getMethodSignature(int index) {
		Method m=this.methods.get(index);
		String result=m.getName() + "(";
		for (int i=0; i<m.getParameterTypes().length; i++) {
			result+=m.getParameterTypes()[i].getName() + ", ";
		}
		if (result.endsWith(", ")) result=result.substring(0, result.length()-2);
		result+=")";
		return result;
	}
}

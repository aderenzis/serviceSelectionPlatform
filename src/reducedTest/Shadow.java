package reducedTest;

import java.util.ArrayList;
import java.util.Iterator;

import testooj3.auxiliares.Auxi;

public class Shadow {
	
	private String name;
	private String dir;
	private ArrayList<DoMethod> methods;
	private ArrayList<Campo> campos;
	
	public Shadow(String completeName)
	{
		this.name = Auxi.recorta(completeName);
		this.dir = Auxi.recorta_package(completeName);
		methods=new ArrayList<DoMethod>();
		this.campos=new ArrayList<Campo>();
	}
	
	public ArrayList<DoMethod> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<DoMethod> methods) {
		this.methods = methods;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public ArrayList<Campo> getCampos() {
		return campos;
	}

	public void setCampos(ArrayList<Campo> campos) {
		this.campos = campos;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String typeOf(String field)
	{
		Iterator ite=this.campos.iterator();
		String type="";
		while(ite.hasNext())
		{
			Campo c=(Campo)ite.next();
			if(c.getNombre().equals(field))
				type=c.getTipo();
		}
		return type;
	}
	
	

	public String generateShadow()
	{
		//inicio
		String cadena="package "+ dir+";\n";
		cadena+="public class "+name+"\n{\n";
		cadena+=mostrarCampos();
		int i=0;
		while(i<methods.size())
		{
			cadena+=methods.get(i).generateDoMethod();
			i++;
		}
		cadena+="}";
		return cadena;
	}
	
	
	public String toString()
	{
		String cadena="package temp; \n";
		cadena+="public class "+name+"\n{\n";
		int i=0;
		//agregar aca los atributos privados...
		cadena+=mostrarCampos();
		while(i<methods.size())
		{
			cadena+=methods.get(i).toString()+"\n";
			i++;
		}
		cadena+="}";
		return cadena;
	}
	
	private String mostrarCampos()
	{
		String cadena="";
		Campo c;
		for(int i=0; i<this.campos.size();i++)
		{
			c=(Campo)this.campos.get(i);
			cadena+="\tprivate "+c.getTipo()+" "+c.getNombre()+";\n";
		}
		cadena+="\n";
		return cadena;
	}
}

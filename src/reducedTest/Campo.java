package reducedTest;

public class Campo {

	private String nombre;
	private String tipo;
	
	public Campo()
	{
		this.nombre="";
		this.tipo="";
	}
	
	public Campo(String nombre, String tipo)
	{
		this.nombre=nombre;
		this.tipo=tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}

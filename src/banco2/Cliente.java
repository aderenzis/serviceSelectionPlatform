package banco2;

public class Cliente {

	public Long numero;
	private String nombre;
	private String apellido;
	private Long dni;
	
	public Cliente(){
		this.numero=0L;
		this.nombre="pepita";
		this.apellido="Lapistolera";
		this.dni=99999L;
		
	}
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	
	public String toString()
	{
		return this.apellido+"-"+this.nombre;
	}
}

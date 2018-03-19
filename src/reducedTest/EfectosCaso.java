package reducedTest;

import java.util.Vector;

public class EfectosCaso {

	private Vector<Efecto> efectos;

	public EfectosCaso(Vector<Efecto> efectos)
	{
		this.efectos=efectos;
	}
	
	public Vector<Efecto> getEfectos() {
		return efectos;
	}

	public void setEfectos(Vector<Efecto> efectos) {
		this.efectos = efectos;
	}
}

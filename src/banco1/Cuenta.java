package banco1;

/**
 * @author  andres
 */
public class Cuenta{
	private double saldo;

	public Cuenta() {
		this.saldo=0;
	}
	public double getSaldo() {
		return saldo;
	}
	
	public double getMonto() {
		return saldo;
	}

	public String toString() {
		return ""+saldo;
	}

	public void ingresar(double count) {
		saldo+=count;
	}

	public void retirar(double count) {
		saldo-=count;
	}

	public void transferir(double numberAccountTransmitter, double numberAccountReceptor) {
		retirar(numberAccountTransmitter);
		Cuenta c=new Cuenta();
		c.ingresar(numberAccountReceptor);
	}

}

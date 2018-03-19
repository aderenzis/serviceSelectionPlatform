package ejemplosBancos;

/**
 * @author  andres
 */
public class Cuenta{
	private double saldo;
	
	public Cuenta() {
		this.saldo=0;
	}
	public Double obtenerBalance() {
		return saldo;
	}

	public Double devolverBalance()
	{
		return this.saldo;
	}
	
	public String toString() {
		return ""+saldo;
	}

	public void addAmount(double value, int account, boolean validate, String observation) {
		saldo+=value;
	}

	public void removAmount(double value, int account, boolean validate, String observation) {
		saldo-=value;
	}
	
	public void transfer(double value, String otraCuenta) {
//		retireAmount(value);
//		Cuenta c=new Cuenta();
//		c.addAmount(value);
	}
}

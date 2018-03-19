package banco2;

/**
 * @author  andres
 */
public class Account3 {
	private double balance;
	private Cliente cliente;

	public Account3() {
		this.balance=0;
		this.cliente=new Cliente();
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String toString() {
		return ""+balance;
	}

	
	public void deposit(double x) {
		balance+=x;
	}
	
	public void withdraw(double y_y) {
		balance-=y_y;
	}
	
	/*public void transfer(double x, String otraCuenta, double y) {
		withdraw(x);
		Account c=new Account();
		c.deposit(x);
	}*/
	
	public void transfer( double x) {
		/*withdraw(x);
		Account c=new Account();
		c.deposit(x);*/
	}

	public Cliente getCliente(){
		return this.cliente;
	}
	
	public void clear()
	{
		this.balance=0;
	}
	
	public double calcularCuota(double monto)
	{
		return monto*0.1;
	}
}
	


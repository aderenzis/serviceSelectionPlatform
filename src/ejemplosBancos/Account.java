package ejemplosBancos;

/**
 * @author  andres
 */
public class Account {
	private double balance;

	public Account() {
		this.balance=0;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public String toString() {
		return ""+balance;
	}

	
	public void depositAmount(double value, int account, boolean validate, String observation) {
		balance+=value;
	}
	
	public void withdrawAmount(double value, int account, boolean validate, String observation) {
		balance-=value;
	}
	
	
	public void transferTo(double value, String otraCuenta) {
//		withdrawAmount(value);
//		Account c=new Account();
//		c.depositAmount(value);
	}
}
	


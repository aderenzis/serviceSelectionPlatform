package banco2;

/**
 * @author  andres
 */
public class Account {
	
	private double balance;

	public Account() {
		this.balance=0;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double amount) {
		balance+=amount;
	}
	
	public void withdraw(double amount) {
		balance-=amount;
	}
	
	public void transfer(double amount, String receiver) {
		withdraw(amount);
		Account c=new Account();
		c.deposit(amount);
	}
	
}
	


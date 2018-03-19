package banco2;
public class Account
{
	private Double balance;

	public void withdraw(double amount)
	{
		if(amount==100.0)
		{
			balance=new Double(-100.0);
		}
		if(amount==50.0)
		{
			balance=new Double(-50.0);
		}
		return (void);
	}

	public void deposit(double amount)
	{
		if(amount==100.0)
		{
			balance=new Double(0.0);
		}
		if(amount==50.0)
		{
			balance=new Double(50.0);
		}
		return (void);
	}
	
	public double getbalance()
	{
		return balance;
	}


}
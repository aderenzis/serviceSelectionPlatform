// Regular expression: 
// Max. length: 5
import java.io.*;
import java.util.Vector;

public class MujavaAccount  {
	public MujavaAccount() {
	}

	public String testTS_0_1() {
		banco2.Account obtained=null;
		obtained =new banco2.Account();
		double arg1=(double) 100.0;
		obtained.withdraw(arg1);
		double result1=obtained.getBalance();
		return new Double(result1).toString();
	}

	public String testTS_0_2() {
		banco2.Account obtained=null;
		obtained =new banco2.Account();
		double arg1=(double) 50.0;
		obtained.withdraw(arg1);
		double result1=obtained.getBalance();
		return new Double(result1).toString();
	}

	public String testTS_1_1() {
		banco2.Account obtained=null;
		obtained =new banco2.Account();
		double arg1=(double) 100.0;
		obtained.deposit(arg1);
		double result1=obtained.getBalance();
		return new Double(result1).toString();
	}

	public String testTS_1_2() {
		banco2.Account obtained=null;
		obtained =new banco2.Account();
		double arg1=(double) 50.0;
		obtained.deposit(arg1);
		double result1=obtained.getBalance();
		return new Double(result1).toString();
	}

	public static void main (String [] args) {
		MujavaAccount o=new MujavaAccount();
		o.testTS_0_1();
		o.testTS_0_2();
		o.testTS_1_1();
		o.testTS_1_2();
	}
}
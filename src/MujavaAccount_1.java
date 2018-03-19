// Regular expression: 
// Max. length: 5
import java.io.*;
import java.util.Vector;

public class MujavaAccount_1  {
	public MujavaAccount_1() {
	}

	public String testTS_0_1() {
		banco2.Account3 obtained=null;
		obtained =new banco2.Account3();
		double arg1=(double) 7;
		obtained.deposit(arg1);
		java.lang.String result1=obtained.toString();
		return result1;
	}

	public String testTS_0_2() {
		banco2.Account3 obtained=null;
		obtained =new banco2.Account3();
		double arg1=(double) 8;
		obtained.deposit(arg1);
		java.lang.String result1=obtained.toString();
		return result1;
	}

	public String testTS_0_3() {
		banco2.Account3 obtained=null;
		obtained =new banco2.Account3();
		double arg1=(double) 0;
		obtained.deposit(arg1);
		java.lang.String result1=obtained.toString();
		return result1;
	}

	public String testTS_1_1() {
		banco2.Account3 obtained=null;
		obtained =new banco2.Account3();
		double arg1=(double) 7;
		obtained.deposit(arg1);
		banco2.Cliente result1=obtained.getCliente();
		return result1.toString();
	}

	public String testTS_1_2() {
		banco2.Account3 obtained=null;
		obtained =new banco2.Account3();
		double arg1=(double) 8;
		obtained.deposit(arg1);
		banco2.Cliente result1=obtained.getCliente();
		return result1.toString();
	}

	public String testTS_1_3() {
		banco2.Account3 obtained=null;
		obtained =new banco2.Account3();
		double arg1=(double) 0;
		obtained.deposit(arg1);
		banco2.Cliente result1=obtained.getCliente();
		return result1.toString();
	}

	public String testTS_2_1() {
		banco2.Account3 obtained=null;
		obtained =new banco2.Account3();
		double arg1=(double) 7;
		obtained.deposit(arg1);
		double result1=obtained.getBalance();
		return new Double(result1).toString();
	}

	public String testTS_2_2() {
		banco2.Account3 obtained=null;
		obtained =new banco2.Account3();
		double arg1=(double) 8;
		obtained.deposit(arg1);
		double result1=obtained.getBalance();
		return new Double(result1).toString();
	}

	public String testTS_2_3() {
		banco2.Account3 obtained=null;
		obtained =new banco2.Account3();
		double arg1=(double) 0;
		obtained.deposit(arg1);
		double result1=obtained.getBalance();
		return new Double(result1).toString();
	}

	public String testTS_3_1() {
		banco2.Account3 obtained=null;
		obtained =new banco2.Account3();
		banco2.Cliente result0=obtained.getCliente();
		return result0.toString();
	}

	public static void main (String [] args) {
		MujavaAccount_1 o=new MujavaAccount_1();
		o.testTS_0_1();
		o.testTS_0_2();
		o.testTS_0_3();
		o.testTS_1_1();
		o.testTS_1_2();
		o.testTS_1_3();
		o.testTS_2_1();
		o.testTS_2_2();
		o.testTS_2_3();
		o.testTS_3_1();
	}
}
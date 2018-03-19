// Regular expression: 
// Max. length: 5
import java.io.*;
import java.util.Vector;

public class MujavaAccount_1  {
	public MujavaAccount_1() {
	}

	public String testTS_0_1() {
		ejemplosBancos.Account obtained=null;
		obtained =new ejemplosBancos.Account();
		double arg1=(double) 0.0;
		int arg2=(int) 0;
		boolean arg3=(boolean) true;
		java.lang.String arg4=(java.lang.String) "asdasd";
		obtained.depositAmount(arg1, arg2, arg3, arg4);
		java.lang.String result1=obtained.toString();
		return result1;
	}

	public String testTS_1_1() {
		ejemplosBancos.Account obtained=null;
		obtained =new ejemplosBancos.Account();
		double arg1=(double) 0.0;
		int arg2=(int) 0;
		boolean arg3=(boolean) true;
		java.lang.String arg4=(java.lang.String) "asdasd";
		obtained.depositAmount(arg1, arg2, arg3, arg4);
		java.lang.Double result1=obtained.getBalance();
		return result1.toString();
	}

	public String testTS_2_1() {
		ejemplosBancos.Account obtained=null;
		obtained =new ejemplosBancos.Account();
		double arg1=(double) 0.012;
		int arg2=(int) 0;
		boolean arg3=(boolean) true;
		java.lang.String arg4=(java.lang.String) "asdasd";
		obtained.withdrawAmount(arg1, arg2, arg3, arg4);
		java.lang.String result1=obtained.toString();
		return result1;
	}

	public String testTS_3_1() {
		ejemplosBancos.Account obtained=null;
		obtained =new ejemplosBancos.Account();
		double arg1=(double) 0.012;
		int arg2=(int) 0;
		boolean arg3=(boolean) true;
		java.lang.String arg4=(java.lang.String) "asdasd";
		obtained.withdrawAmount(arg1, arg2, arg3, arg4);
		java.lang.Double result1=obtained.getBalance();
		return result1.toString();
	}

	public String testTS_4_1() {
		ejemplosBancos.Account obtained=null;
		obtained =new ejemplosBancos.Account();
		java.lang.Double result0=obtained.getBalance();
		return result0.toString();
	}

	public static void main (String [] args) {
		MujavaAccount_1 o=new MujavaAccount_1();
		o.testTS_0_1();
		o.testTS_1_1();
		o.testTS_2_1();
		o.testTS_3_1();
		o.testTS_4_1();
	}
}
// Regular expression: 
// Max. length: 5
import java.io.*;
import java.util.Vector;

public class Mujavacalculator  {
	public Mujavacalculator() {
	}

	public String testTS_0_1() {
		calculadora.calculator obtained=null;
		obtained =new calculadora.calculator();
		float arg1=(float) 0.0;
		float arg2=(float) 0.0;
		float result0=obtained.sum(arg1, arg2);
		return new Float(result0).toString();
	}

	public String testTS_1_1() {
		calculadora.calculator obtained=null;
		obtained =new calculadora.calculator();
		float arg1=(float) 0.0;
		float arg2=(float) 0.0;
		float result0=obtained.product(arg1, arg2);
		return new Float(result0).toString();
	}

	public static void main (String [] args) {
		Mujavacalculator o=new Mujavacalculator();
		o.testTS_0_1();
		o.testTS_1_1();
	}
}
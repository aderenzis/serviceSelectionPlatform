// Regular expression: 
// Max. length: 5
import java.io.*;
import java.util.Vector;

public class Mujavacalculadora  {
	public Mujavacalculadora() {
	}

	public String testTS_0_1() {
		calculadora.calculadora obtained=null;
		obtained =new calculadora.calculadora();
		float arg1=(float) 0.0;
		float arg2=(float) 0.0;
		float result0=obtained.sum(arg1, arg2);
		return new Float(result0).toString();
	}

	public String testTS_1_1() {
		calculadora.calculadora obtained=null;
		obtained =new calculadora.calculadora();
		float arg1=(float) 0.0;
		float arg2=(float) 0.0;
		float result0=obtained.product(arg1, arg2);
		return new Float(result0).toString();
	}

	public static void main (String [] args) {
		Mujavacalculadora o=new Mujavacalculadora();
		o.testTS_0_1();
		o.testTS_1_1();
	}
}
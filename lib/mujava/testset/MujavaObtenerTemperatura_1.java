// Regular expression: 
// Max. length: 5
import java.io.*;
import java.util.Vector;

public class MujavaObtenerTemperatura_1  {
	public MujavaObtenerTemperatura_1() {
	}

	public String testTS_0_1() {
		tempe.ObtenerTemperatura obtained=null;
		obtained =new tempe.ObtenerTemperatura();
		double arg1=(double) 10;
		double result0=obtained.doFarenheitCentigrado(arg1);
		return new Double(result0).toString();
	}

	public String testTS_0_2() {
		tempe.ObtenerTemperatura obtained=null;
		obtained =new tempe.ObtenerTemperatura();
		double arg1=(double) 50;
		double result0=obtained.doFarenheitCentigrado(arg1);
		return new Double(result0).toString();
	}

	public String testTS_1_1() {
		tempe.ObtenerTemperatura obtained=null;
		obtained =new tempe.ObtenerTemperatura();
		double arg1=(double) 5.0;
		double result0=obtained.doCentigradoFarenheit(arg1);
		return new Double(result0).toString();
	}

	public String testTS_1_2() {
		tempe.ObtenerTemperatura obtained=null;
		obtained =new tempe.ObtenerTemperatura();
		double arg1=(double) 0.0;
		double result0=obtained.doCentigradoFarenheit(arg1);
		return new Double(result0).toString();
	}

	public static void main (String [] args) {
		MujavaObtenerTemperatura_1 o=new MujavaObtenerTemperatura_1();
		o.testTS_0_1();
		o.testTS_0_2();
		o.testTS_1_1();
		o.testTS_1_2();
	}
}
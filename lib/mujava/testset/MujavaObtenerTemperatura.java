// Regular expression: 
// Max. length: 5
import java.io.*;
import java.util.Vector;

public class MujavaObtenerTemperatura  {
	public MujavaObtenerTemperatura() {
	}

	public String testTS_0_1() {
		tempe.ObtenerTemperatura obtained=null;
		obtained =new tempe.ObtenerTemperatura();
		double arg1=(double) 0.0;
		double result0=obtained.doFarenheitCentigrado(arg1);
		return new Double(result0).toString();
	}

	public String testTS_1_1() {
		tempe.ObtenerTemperatura obtained=null;
		obtained =new tempe.ObtenerTemperatura();
		double arg1=(double) 0.0;
		double result0=obtained.doCentigradoFarenheit(arg1);
		return new Double(result0).toString();
	}

	public static void main (String [] args) {
		MujavaObtenerTemperatura o=new MujavaObtenerTemperatura();
		o.testTS_0_1();
		o.testTS_1_1();
	}
}
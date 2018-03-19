package tempe;
// Esta clase representa el shadow del interface IF
// contiene las respuestas esperadas para valores de test representativos
// en las funciones principales del SW

public class ObtenerTemperatura{
 
    public double doCentigradoFarenheit(double temp) {
    	double resultado = 0;
		if (temp == 0) {
			resultado = 32;
		}
		if (temp == 25) {
			resultado = 77;
		}
		if (temp == 26) {
			resultado = 78.8;
		}
		return resultado;
    }


    public double doFarenheitCentigrado(double temp) {
		double resultado = 0;

		if (temp == 32) {
			resultado = 0;
		}
		if (temp == 77.0) {
			resultado = 25;
		}
		if (temp == 78.8) {
			resultado = 26;
		}
		return resultado;

    }
}

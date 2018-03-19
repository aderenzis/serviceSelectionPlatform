package me.domain;

public class Controlador implements Runnable {
	private Thread hiloControlado;
	private long timeOut;
	private String testCaseName;

	public Controlador(Thread hiloTardon, long timeOut, String testCaseName) {
		this.timeOut=timeOut+3000;
		this.hiloControlado=hiloTardon;
		this.testCaseName=testCaseName;
	}
	
	public void run() {
		long timeIni=System.currentTimeMillis();
		while (true) {
			long time=System.currentTimeMillis()-timeIni;
			if (time>timeOut) {
				this.hiloControlado.stop();
				System.out.println("\nInterrumpido " + this.testCaseName);
				return;
			}
		}
	}
}

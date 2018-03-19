package banco2; 

public class Account2 {
	protected banco2.Account3 candidate=new banco2.Account3();

	public Account2(){
//		this.proxy = new banco2.Account();
	}
	public float getBalance () {
		Float ret0=new Float(0);
//					ret0=	candidate.getBalance();

		return ret0;
}
	public double calcularCuota (double arg1) {
		Double ret0=new Double(0.0);
					ret0=	candidate.calcularCuota(arg1);

		return ret0;
}
	public java.lang.String toString () {
		String ret0=new String();
					ret0=	candidate.toString();

		return ret0;
}
	public void transfer (double arg1) {
			candidate.transfer(arg1);

}
//	public banco2.Cliente getCliente () {
//		 ret0=new ();
//					ret0=	candidate.getCliente();
//
//		return ret0;
//}
	public void deposit (int arg1) {
			candidate.deposit(arg1);

}
	public void clear () {
			candidate.clear();

}
	public void withdraw (double arg1) {
			candidate.withdraw(arg1);

}
}

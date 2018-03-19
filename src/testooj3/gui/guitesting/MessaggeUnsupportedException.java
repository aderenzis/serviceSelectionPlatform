package testooj3.gui.guitesting;


public class MessaggeUnsupportedException extends Exception {

	public MessaggeUnsupportedException(IMessagge it) {
		this("El mensaje no está permitido: "+it.toString());
	}

	public MessaggeUnsupportedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MessaggeUnsupportedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public MessaggeUnsupportedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}

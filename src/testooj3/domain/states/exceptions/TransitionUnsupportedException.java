package testooj3.domain.states.exceptions;

import testooj3.domain.states.ITransition;

public class TransitionUnsupportedException extends Exception {

	public TransitionUnsupportedException(ITransition it) {
		this("La transición no está permitida: "+it.toString());
	}

	public TransitionUnsupportedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TransitionUnsupportedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public TransitionUnsupportedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}

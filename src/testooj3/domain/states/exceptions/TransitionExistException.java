package testooj3.domain.states.exceptions;

import testooj3.domain.states.ITransition;


public class TransitionExistException extends Exception {

	public TransitionExistException(ITransition it) {
		this("La transición ya existe: "+it.toString());
	}

	public TransitionExistException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TransitionExistException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TransitionExistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}

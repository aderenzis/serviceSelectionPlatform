package testooj3.domain.states;

import java.io.Serializable;

public class InitialState extends State implements Serializable{

	public InitialState() {
		super("init", "init");
	}

}

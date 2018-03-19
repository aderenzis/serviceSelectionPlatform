package testooj3.gui.stmachine.common;

import testooj3.domain.states.ChoiceState;
import testooj3.domain.states.InitialState;
import testooj3.domain.states.State;

public class PStatesFactory {

	public static PState newInstance(State st) {
		if (st instanceof InitialState) {
			InitialState ist=(InitialState) st;
			return new PInitialState(ist);
		} else if (st instanceof ChoiceState) {
			ChoiceState chst=(ChoiceState) st;
			return new PChoiceState(chst);
		} else return new PState(st);
	}

}

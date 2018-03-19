package testooj3.gui.guitesting;

public class PInstancesFactory {

	public static PInstance newInstance(Instance st) {
		if (st instanceof ActorInstance) {
			ActorInstance ist=(ActorInstance) st;
			return new PActorInstance(ist);
		} else return new PInstance(st);
	}

}
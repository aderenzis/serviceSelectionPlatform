package testooj3.gui.stmachine.common;

import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

public class LabelDragHandler extends PDragSequenceEventHandler {
	
	public void mouseClicked(PInputEvent e) {
		PLabel label=(PLabel)e.getPickedNode();
		label.change();
	}
}

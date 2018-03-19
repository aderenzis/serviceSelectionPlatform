package testooj3.gui.stmachine.common;

import java.awt.Color;


import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.nodes.PText;

public class TransitionDragHandler extends PDragSequenceEventHandler {
	public TransitionDragHandler() {
		getEventFilter().setMarksAcceptedEventsAsHandled(true);
	}
	
	public void mouseEntered(PInputEvent e) {
		if (e.getPickedNode() instanceof PTransition) {
			PTransition pt=(PTransition) e.getPickedNode();
			pt.highlight(Color.red);
			//e.getInputManager().mouseEntered(e);
			e.setHandled(true);
		}
	}

	public void mouseExited(PInputEvent e) {
		if (e.getPickedNode() instanceof PTransition) {
			PTransition pt=(PTransition) e.getPickedNode();
			pt.unhighlight();
			//e.getInputManager().mouseExited(e);
			e.setHandled(true);
		}
	}

	public void drag(PInputEvent e) {
		/*PTransition node = (PTransition) e.getPickedNode();
		PText text=node.getText();
		double deltaWidth=e.getDelta().width;
		double deltaHeight=e.getDelta().height;
		node.text.setX(text.getX()+deltaWidth);
		node.text.setY(text.getY()+deltaHeight);*/
		/*float x=(float) (node.getX()+deltaWidth);
		float y=(float) (node.getY()+deltaHeight);
		node.line.moveTo(100, 100);
		node.line.lineTo(x, y);*/
	}
	
	public void mouseClicked(PInputEvent e) {
		PTransition pt=(PTransition)e.getPickedNode();
		pt.change();
	}
	
}
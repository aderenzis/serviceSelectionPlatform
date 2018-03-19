package testooj3.gui.guitesting;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.nodes.PText;
import java.awt.Color;
import testooj3.domain.Operation;

/**
 * @author  andres
 */
public class MessaggeDragHandler extends PDragSequenceEventHandler {
	private JFGuiTesting parentWindow;

	public MessaggeDragHandler(JFGuiTesting pw) {
		this.parentWindow=pw;
		getEventFilter().setMarksAcceptedEventsAsHandled(true);
	}
	
	public void mouseEntered(PInputEvent e) {
		if (e.getPickedNode() instanceof PMessage) {
			PMessage pt=(PMessage) e.getPickedNode();
			pt.highlight(Color.red);
			//e.getInputManager().mouseEntered(e);
			e.setHandled(true);
		}
	}

	public void mouseExited(PInputEvent e) {
		if (e.getPickedNode() instanceof PMessage) {
			PMessage pt=(PMessage) e.getPickedNode();
			pt.unhighlight();
			//e.getInputManager().mouseExited(e);
			e.setHandled(true);
		}
	}
	
	public void mouseClicked(PInputEvent e) {
		PMessage pt=(PMessage)e.getPickedNode();
		if (e.getClickCount()==1) {
			pt.change();
		} else if (e.getClickCount()==2) {
			Instance instance = pt.messagge.getTarget();
			if (pt.getMessagge() instanceof Messagge) {
				Operation method = pt.getMethod();
				this.parentWindow.fillTestValues(instance, method);
			} else {
				JDEvent evento=new JDEvent(this.parentWindow, pt.getMessagge());
				evento.setVisible(true);
			}
		}
	}
	
}
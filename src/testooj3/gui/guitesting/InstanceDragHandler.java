package testooj3.gui.guitesting;

import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.nodes.PText;
import java.awt.Color;
import java.awt.Frame;
import java.lang.reflect.Method;
import java.util.Vector;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;

/**
 * @author  andres
 */
public class InstanceDragHandler extends PDragSequenceEventHandler {
	private PInstance source;
	private PMessage linea;
	private PInstance target;
	private boolean editable;
	private int messaggeCounter;

	public InstanceDragHandler() {
		this.editable=true;
		getEventFilter().setMarksAcceptedEventsAsHandled(true);
	}
	
	public InstanceDragHandler(boolean editable) {
		this.editable=editable;
		getEventFilter().setMarksAcceptedEventsAsHandled(true);
	}
	
	public void mouseEntered(PInputEvent e) {
		if (e.getButton() == 0) {
			PInstance st=(PInstance) e.getPickedNode();
			st.highlight(Color.red);
		}
	}
	
	public void mouseClicked(PInputEvent e) {
		if (editable==true && e.getClickCount() == 2) {
			PInstance st=(PInstance) e.getPickedNode();
			st.unhighlight();
			String msg="";
			if (this.source==null) {
				if (!(st instanceof PActorInstance)) {
					this.source=st;
					this.target=st;
					IMessagge it=getIMessagge();
					try {
						this.target.canvas.addMessage(it);
					} catch (MessaggeUnsupportedException e1) {
					}
					return;
				}				
				this.source=st;
				msg="Source instance: " + st.getName() + "<br>Double click on the target instance";				
			} else if (this.target==null) {
				if (st instanceof PActorInstance) {
					this.source=null;
					msg="";
					st.canvas.log(msg);
					return;
				}
				
				this.target=st;
				msg="Target instance: " + st.getName() + "<br>Select the trigger method";
				
				IMessagge it=getIMessagge();
				if (it==null){
					msg="No se pueden crear mensajes hacia la instancia actor.";
				} else
					try {
						this.target.canvas.addMessage(it);
					} catch (MessaggeUnsupportedException e1) {
						msg="Este tipo de mensajes no están permitidos," +
								" revise los nodos origen y destino:"+e1.getMessage();
					}
				/*Para que se pueda definir otra transición*/
				this.source=null;
				this.target=null;
			}
			st.canvas.log(msg);
		}else
			if (!editable){
				PInstance st=(PInstance) e.getPickedNode();
				System.out.println(st.toString());
			}
	}
	
	private IMessagge getIMessagge(){
		boolean sourceIsActor=this.source.instance instanceof ActorInstance;
		boolean targetIsActor=this.target.instance instanceof ActorInstance;
		JDEvent evento=null;
		if (sourceIsActor && !targetIsActor) {
			evento=new JDEvent(target.canvas.getParentPanel().getParentWindow(), target.instance, false);
		} else {
			evento=new JDEvent(target.canvas.getParentPanel().getParentWindow(), target.instance, true);
		} 
		evento.setVisible(true);
		IMessagge it=null;
		if (!evento.isOracleMessagge()) {
			Operation m=find(target.instance.fixture, evento.getMessagge());
			it=new Messagge(source.instance, target.instance, m, ++this.messaggeCounter);
		} else {
			it=new OracleMessagge(source.instance, target.instance, evento.getMessagge(), ++this.messaggeCounter, null);
		}
		return it;
	}

	private Operation find(GuiFixture guiFixture, String methodSignature) {
		for (Operation m : guiFixture.getMethods())
			if (m.getId().equals(methodSignature))
				return m;
		return null;
	}

	public void mouseExited(PInputEvent e) {
		if (e.getButton() == 0) {
			PInstance st=(PInstance) e.getPickedNode();
			st.unhighlight();
		}
	}

	public void drag(PInputEvent e) {
		{
			PNode node = (PNode) e.getPickedNode();
			double deltaWidth=e.getDelta().width;
			double deltaHeight=e.getDelta().height;
			//node.translate(deltaWidth, deltaHeight);
			node.setX(node.getX()+deltaWidth);
			node.setY(node.getY()+deltaHeight);
	
			PInstance st=(PInstance) node;
			Vector inputs=st.inputMessagges;
			for (int i=0; i<inputs.size(); i++) {
				PMessage pt=(PMessage) inputs.get(i);
				pt.reset();
				pt.draw();
			}
			Vector outputs=st.outputMessagges;
			for (int i=0; i<outputs.size(); i++) {
				PMessage pt=(PMessage) outputs.get(i);
				pt.reset();
				pt.draw();
			}
		}
	}

	public void setEditable(boolean b) {
		this.editable=b;
	}

}
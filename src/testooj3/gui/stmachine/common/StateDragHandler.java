package testooj3.gui.stmachine.common;

import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PDragSequenceEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.nodes.PText;
import java.awt.Color;
import java.awt.Frame;
import java.util.Vector;
import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.states.ITransition;
import testooj3.domain.states.Transition;
import testooj3.domain.states.TransitionFromChoice;
import testooj3.domain.states.TransitionToChoice;
import testooj3.domain.states.exceptions.TransitionExistException;
import testooj3.domain.states.exceptions.TransitionUnsupportedException;
import testooj3.gui.stmachine.statemachine.JDEvent;

/**
 * @author  andres
 */
public class StateDragHandler extends PDragSequenceEventHandler {
	private PState source;
	private PTransition linea;
	private PState target;
	private boolean editable;

	public StateDragHandler() {
		this.editable=true;
		getEventFilter().setMarksAcceptedEventsAsHandled(true);
	}
	
	public StateDragHandler(boolean editable) {
		this.editable=editable;
		getEventFilter().setMarksAcceptedEventsAsHandled(true);
	}
	
	public void mouseEntered(PInputEvent e) {
		if (e.getButton() == 0) {
			PState st=(PState) e.getPickedNode();
			st.highlight(Color.red);
		}
	}
	
	public void mouseClicked(PInputEvent e) {
		if (editable==true && e.getClickCount() == 2) {
			PState st=(PState) e.getPickedNode();
			st.unhighlight();
			String msg="";
			if (this.source==null) {
				this.source=st;
				msg="Source state: " + st.getName() + "<br>Double click on the target state, or double click on " + st.getName();				
			} else if (this.target==null) {
				this.target=st;
				msg="Target state: " + st.getName() + "<br>Select the trigger method";
				
				ITransition it=getITransition();
				if (it==null){
					msg="No se pueden establecer transiciones desde un estado choice si no se han establecido condiciones.";
				} else
					try {
						this.target.canvas.addTransition(it);
					} catch (TransitionExistException e1) {
						msg="No se puede añadir la transicion porque ya existe: "+e1.getMessage();
					} catch (TransitionUnsupportedException e1) {
						msg="Este tipo de transiciones no están permitidas," +
								" revise los nodos origen y destino:"+e1.getMessage();
					}
				
				
				/*Para que se pueda definir otra transición*/
				this.source=null;
				this.target=null;
			}
			st.canvas.log(msg);
		}else
			if (!editable){
				PState st=(PState) e.getPickedNode();
				System.out.println(st.toString());
			}
		
	}
	
	private ITransition getITransition(){
		JDEvent evento=new JDEvent(target.canvas.getParentPanel().getParentWindow());
		ITransition it=null;
		
		if (source instanceof PInitialState){
			//se cargan solo los constructores, si no se activa,
			// por defecto están los métodos que no son constructores
			evento=new JDEvent(target.canvas.getParentPanel().getParentWindow(),true);
			evento.setVisible(true);
			
			it=new Transition(source.state,target.state,evento.getEvent(),null);
			
		}else if (source instanceof PChoiceState){
			evento.setConditionEnabled(true);
			evento.setDispatchEnabled(false);
			
			it=new TransitionFromChoice(source.state,target.state);
			ITransition itAux=source.canvas.getTransition(it);
			if (itAux!=null){
				it=itAux;
				source.canvas.removeTransition(it);
			}
		
			evento.setConditions(((TransitionFromChoice)it).getConditions());
			evento.setVisible(true);
			
			Vector conditions=evento.getConditions();
			if (conditions==null || conditions.isEmpty())
				it= null;
			else{
				System.err.println(conditions.toString());
				((TransitionFromChoice)it).appendConditions(conditions);		
			}
		}else if (target instanceof PChoiceState){
			evento.setVisible(true);
			it=new TransitionToChoice(source.state,target.state,new Transition(source.state,target.state,evento.getEvent(),null));
		}else {
			evento.setVisible(true);
			it=new Transition(source.state,target.state,evento.getEvent(),null);
		}
		
		

		//System.out.println(evento.getCondition()+evento.getEvent());
		
		return it;
	}

	public void mouseExited(PInputEvent e) {
		if (e.getButton() == 0) {
			PState st=(PState) e.getPickedNode();
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
	
			PState st=(PState) node;
			Vector inputs=st.inputTransitions;
			for (int i=0; i<inputs.size(); i++) {
				PTransition pt=(PTransition) inputs.get(i);
				pt.reset();
				pt.draw();
			}
			Vector outputs=st.outputTransitions;
			for (int i=0; i<outputs.size(); i++) {
				PTransition pt=(PTransition) outputs.get(i);
				pt.reset();
				pt.draw();
			}
		}
	}

	public void setEditable(boolean b) {
		this.editable=b;
	}

}
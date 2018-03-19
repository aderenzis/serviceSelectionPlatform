package testooj3.gui.guitesting;

import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.nodes.PText;
import java.util.Hashtable;
import java.util.Iterator;
import testooj3.domain.TOperation;

/**
 * @author  andres
 */
@SuppressWarnings("serial")
public class PCanvasInteractionDiagram extends PCanvas{
	protected Hashtable<String, PInstance> instances;  //  @jve:decl-index=0:
	protected PLayer instancesLayer;  //  @jve:decl-index=0:
	protected PLayer messaggesLayer;  //  @jve:decl-index=0:
	private JPInteractionDiagram parentPanel;
	protected InstanceDragHandler instancesDragHandler;
	protected MessaggeDragHandler messaggesDragHandler;
	protected InteractionDiagram interactionDiagram;  //  @jve:decl-index=0:
	
	public PCanvasInteractionDiagram() {		
	}
	
	public PCanvasInteractionDiagram(JPInteractionDiagram parentPanel) {
		this(parentPanel, new InteractionDiagram());		
	}
	
	public PCanvasInteractionDiagram(JPInteractionDiagram parentPanel, InteractionDiagram stM){
		this.parentPanel=parentPanel;
		interactionDiagram=stM;
		initialize();
	}

	protected void initialize() {
		this.instances=new Hashtable<String, PInstance>();
		this.instancesLayer =  new PLayer();//getLayer();
		getCamera().addLayer(0, instancesLayer);
		
		this.messaggesLayer = new PLayer();
		getCamera().addLayer(0, messaggesLayer);
				
		this.instancesDragHandler=new InstanceDragHandler();
		this.messaggesDragHandler=new MessaggeDragHandler(this.parentPanel.getParentWindow());
		messaggesLayer.addInputEventListener(messaggesDragHandler);
		instancesLayer.addInputEventListener(instancesDragHandler);
		
		PInstance ps=null;
		Instance st;
		PMessage pt;
		
		for(Iterator iter=interactionDiagram.getInstances().values().iterator();iter.hasNext();){
			st=(Instance)iter.next();
			System.err.println(st.getName());
			ps=PInstancesFactory.newInstance(st);
			ps.setCanvas(this);
			this.instances.put(st.getName(), ps);
			this.instancesLayer.addChild(ps);
		}
		
		this.messaggesLayer.addChildren(PMessaggesFactory.newIntances(this.interactionDiagram,this.instances));
	}

	public void addInstance(Instance st) {
		if (!this.interactionDiagram.getInstances().containsKey(st.getName())){
			this.interactionDiagram.add(st);
			PInstance ps=new PInstance(st);
			ps.setCanvas(this);
			this.instances.put(st.getName(), ps);
			this.instancesLayer.addChild(ps);
		}
	}
	
	public boolean addMessage(IMessagge msg) throws MessaggeUnsupportedException {
		if (this.interactionDiagram.addMessagge(msg)){
			PMessage pt=PMessaggesFactory.newInstance(instances, msg, this.interactionDiagram.getMessaggesInLineIndex(msg));
			this.messaggesLayer.addChild(pt);
			return true;
		}else{
			return false;
		}
	}
	
	public IMessagge getTransition(IMessagge tr){
		return this.interactionDiagram.getMessagge(tr);
	}

	public void setParentPanel(JPInteractionDiagram panel) {
		this.parentPanel=panel;
	}
	
	public JPInteractionDiagram getParentPanel(){
		return this.parentPanel;
	}

	public void log(String msg) {
		this.parentPanel.log(msg);
	}

	public PLayer getStatesLayer() {
		return instancesLayer;
	}
	
	public void removeStateDragHandler(){
		this.instancesLayer.removeInputEventListener(this.instancesDragHandler);
	}

	public PLayer getTransitionsLayer() {
		return messaggesLayer;
	}

	public void selectMethod(TOperation selectedOperation) {
		PText p=new PText(selectedOperation.getId());
		p.setX(10); p.setY(10);
		this.instancesLayer.addChild(p);
	}

	public InteractionDiagram getInteractionDiagram() {
		return this.interactionDiagram;
	}

	public void setStateMachine(InteractionDiagram stm) {
		this.interactionDiagram=stm;
		initialize();
	}

	public PMessage getPTransition(IMessagge it) {
		return (PMessage) this.messaggesLayer.getChild(this.messaggesLayer.indexOfChild(PMessaggesFactory.newInstance(instances, it, 1)));
	}

	public void removeTransition(IMessagge it) {
		this.interactionDiagram.removeMessagge(it);
		this.instancesLayer.removeAllChildren();
		this.messaggesLayer.removeAllChildren();
		this.initialize();
	}

}

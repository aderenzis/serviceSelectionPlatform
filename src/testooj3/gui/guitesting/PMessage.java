package testooj3.gui.guitesting;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.nodes.PComposite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import testooj3.domain.Operation;
import testooj3.gui.stmachine.common.Calculus;
import testooj3.gui.stmachine.common.PLabel;

/**
 * @author  andres
 */
public class PMessage extends PComposite {
	protected IMessagge messagge;
	protected PLabel text;
	protected PPath line;
	protected PPath arrow;
	protected PInstance source;
	protected PInstance target;
	protected Color color;
	
	public PMessage(IMessagge messagge, PInstance source, PInstance target) {
		this.messagge=messagge;
		this.source=source; this.source.addOutputMessagge(this);
		this.target=target; this.target.addInputMessagge(this);
		this.color=Color.BLACK;
		
		//Creamos la linea
		line = new PPath(); 
		line.setVisible(true);
		float[] p0=this.source.getPuntoMedio();
		float[] pF=this.target.getPuntoMedio();
		this.line=PPath.createLine(p0[0], p0[1], pF[0], pF[1]);
		
		//La punta de la flecha
		arrow=PPath.createPolyline(Calculus.getCoordsArrowFull(this.source, this.target));
		arrow.setPaint(Color.black);
		
		//La etiqueta
		Vector vaux=new Vector();
		vaux.add(messagge.getCounter() + ". " + messagge.getId());
		//vaux.add(transition.getMethod());
		text=new PLabel(vaux);
		text.setTextPaint(Color.blue);
		
		addChild(text);
		addChild(line);
		addChild(arrow);
		
		draw();
		
		//System.out.println(System.currentTimeMillis() + " Transición " + getLabel() + ": texto en (" + text.getBounds().x + ", " + text.getBounds().y + ")");
	}	
	
	public PLabel getText() {
		return this.text;
	}
	
	/*public void setText(String str) {
		this.text.setText(str);
	}*/
	
	public PInstance getSource() {
		return source;
	}
	
	public PInstance getTarget() {
		return target;		
	}
	
	public void reset() {
		this.line.reset();
	}

	public void draw() {
		//float[] p0=this.source.getPuntoMedio();
		float[] p0=Calculus.getCoordsArrow(target, source);
		float[] pF=Calculus.getCoordsArrow(source, target);
		
		this.removeChild(this.line);
		this.line=PPath.createLine(p0[0], p0[1], pF[0], pF[1]);
		this.addChild(this.line);
		
		float [] coordsAux={p0[0], p0[1], pF[0], pF[1]};
		coordsAux=Calculus.getCoordsLabel(coordsAux, (float)this.text.getWidth());
		
		arrow.setPathToPolyline(Calculus.getCoordsArrowFull(source, target));
		
		this.text.setOffset(coordsAux[0],coordsAux[1]);
	}

	public void highlight(Color c) {
		this.line.setStrokePaint(c);
		this.arrow.setPaint(c);
		this.text.setTextPaint(c);
	}
	
	public void unhighlight() {
		this.line.setStrokePaint(this.color);
		this.arrow.setPaint(this.color);
		this.text.setTextPaint(this.color);
	}
	
	public boolean equals(Object trans){		
		if (trans instanceof PMessage)
			return 	(this.target.equals(((PMessage)trans).target) &&
					this.source.equals(((PMessage)trans).source));/* ||
					(this.target.equals(((PTransition)trans).source) &&
					this.source.equals(((PTransition)trans).target));*/
		else
			return false;
	}

	public void change() {
		this.text.change();
	}
	
	public IMessagge getMessagge() {
		return this.messagge;
	}

	public Operation getMethod() {
		return this.messagge.getMethod();
	}
}

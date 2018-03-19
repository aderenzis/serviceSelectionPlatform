package testooj3.gui.guitesting;

import java.awt.Color;
import java.awt.Point;

import testooj3.gui.stmachine.common.Calculus;

public class PMessaggeSelf extends PMessage {

	public PMessaggeSelf(IMessagge it, PInstance st){
		super(it,st,st);
	}
	
	public void draw(){
		Point [] ps=Calculus.getCoordsTransitionSelf(source);
		
		this.line.setPathToPolyline(ps);
		
		
		arrow.setPathToPolyline(Calculus.getCoordsArrowAltFull(ps[3],Calculus.N));
		
		this.text.setOffset(ps[1].getX(),ps[1].getY()-this.text.getHeight());
		
		this.line.setStrokePaint(Color.RED);
		this.line.setPaint(null);
		
		arrow.setPaint(Color.RED);
		arrow.setStrokePaint(Color.RED);
		this.text.setTextPaint(Color.RED);
	}

}

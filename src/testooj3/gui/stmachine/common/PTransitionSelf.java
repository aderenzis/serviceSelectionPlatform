package testooj3.gui.stmachine.common;

import java.awt.Color;
import java.awt.Point;

import testooj3.domain.states.ITransition;

public class PTransitionSelf extends PTransition {

	public PTransitionSelf(ITransition it,PState st){
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
		
		/*this.line.setPathToPolyline(ps);
		
		this.text.setOffset(ps[2]);

		arrow.setPathToPolyline(Calculus.getCoordsArrowAltFull(ps[3],coord[0]));	
		
		this.text.setTextPaint(Color.MAGENTA);
		arrow.setPaint(Color.magenta);
		arrow.setStrokePaint(Color.magenta);
		this.line.setPaint(null);
		this.line.setStrokePaint(Color.magenta);*/
	}

}

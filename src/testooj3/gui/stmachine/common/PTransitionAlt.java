package testooj3.gui.stmachine.common;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;

import edu.umd.cs.piccolo.nodes.PPath;

import testooj3.domain.states.ITransition;
import testooj3.domain.states.State;

public class PTransitionAlt extends PTransition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index=0;

	public PTransitionAlt(ITransition it, PState source, PState target) {
		super(it, source, target);
	}
	
	public PTransitionAlt(ITransition it, PState source, PState target, int index) {
		super(it,source,target);
		this.index=index;
		//this.color=new Color((int)(200*Math.random()),(int)(200*Math.random()),(int)(200*Math.random()));
		this.color=new Color((index*30)%200,(index*60)%200,(index*85)%200);//Si si quiere conseguir una gama de colores uniforme
	}

	/*public void draw(){
		float[] p0=Calculus.getCoordsArrow(target, source);
		float[] pF=Calculus.getCoordsArrow(source, target);
		int [] coord=new int [1];
		
		Point [] ps=Calculus.getCoordsTransitionAlt3(source,target,coord);
		
		this.line.setPathToPolyline(ps);
		
		this.text.setOffset(ps[2]);

		arrow.setPathToPolyline(Calculus.getCoordsArrowAltFull(ps[3],coord[0]));	
		
		this.text.setTextPaint(Color.MAGENTA);
		arrow.setPaint(Color.magenta);
		arrow.setStrokePaint(Color.magenta);
		this.line.setPaint(null);
		this.line.setStrokePaint(Color.magenta);
	}*/
	
	//Dibuja las transiciones de otraforma
	public void draw(){
		float[] p0=Calculus.getCoordsArrow(target, source);
		float[] pF=Calculus.getCoordsArrow(source, target);
		int [] coord=new int [1];
		
		Point [] ps=Calculus.getCoordsTransitionAlt2(source,target,index);
		
		this.line.setPathToPolyline(ps);
		
		this.line.setPaint(null);
		this.line.setStrokePaint(color);
		
		this.text.setOffset(ps[1]);
		this.text.setTextPaint(color);
		arrow.setPaint(color);
		arrow.setStrokePaint(color);
		arrow.setPathToPolyline(Calculus.getCoordsArrowFull(ps[1],ps[2]));	
	}

}

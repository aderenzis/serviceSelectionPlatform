package testooj3.gui.guitesting;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;

import edu.umd.cs.piccolo.nodes.PPath;

import testooj3.gui.stmachine.common.Calculus;

public class PMessaggeAlt extends PMessage {
	private static final long serialVersionUID = 1L;
	private int index=0;

	public PMessaggeAlt(IMessagge it, PInstance source, PInstance target) {
		super(it, source, target);
	}
	
	public PMessaggeAlt(IMessagge it, PInstance source, PInstance target, int index) {
		super(it,source,target);
		this.index=index;
		//this.color=new Color((index*30)%200,(index*60)%200,(index*85)%200);//Si quiere conseguir una gama de colores uniforme
		this.color=Color.BLACK;
	}
	
	//Dibuja las transiciones de otraforma
	public void draw(){
		float[] p0=Calculus.getCoordsArrow(target, source);
		float[] pF=Calculus.getCoordsArrow(source, target);
		int [] coord=new int [1];
		
		Point [] ps=Calculus.getCoordsMessaggeAlt2(source,target,index);
		
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

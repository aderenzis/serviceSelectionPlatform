package testooj3.gui.stmachine.common;

import java.awt.Point;
import java.util.Random;

import edu.umd.cs.piccolo.nodes.PPath;
import testooj3.domain.states.ChoiceState;
import testooj3.domain.states.State;

public class PChoiceState extends PState {

	float dx = 30 ;
	float dy = 20 ;
	
	public PChoiceState(ChoiceState st) {
		super(st);
		this.state=st;
		this.text.setText("");
		Random rnd = new Random();
		float x = (float) (300. * rnd.nextDouble());
		float y = (float) (400. * rnd.nextDouble());
		float[] xp={x, x+dx, x+2*dx, x+dx, x};
		float[] yp={y, y-dy, y, y+dy, y};
		this.removeChild(rectangle);
		rectangle=PPath.createPolyline(xp, yp);
//		addChild(text);
		addChild(rectangle);
		this.text.moveToFront();		
		showPosition();
	}
	
	public boolean setX(double x) {
		text.setX(x+deltaX);
		rectangle.setX(x);
		//rectangle.setWidth(text.getWidth()*1.10);
		return true;
	}
	
	public float [] getInputCoord(int cardinal){
		float [] p= new float[2];
		int i=1;
		
		Point e=Calculus.getCoordsEste(this);
		Point o=Calculus.getCoordsOeste(this);
		Point s=Calculus.getCoordsSur(this);
		Point n=Calculus.getCoordsNorte(this);
		
		switch (cardinal){
			case Calculus.N://si está al norte, pongo la coord al sur
				p[0]=(float) (n.x+e.x)/2;
				p[1]=(float) (n.y+e.y)/2;
			break;
			case Calculus.S://si esta al sur lo pongo al norte
				p[0]=(float) (s.x+o.x)/2;
				p[1]=(float) (s.y+o.y)/2;
			break;
			case Calculus.E:
				p[0]=(float) (s.x+e.x)/2;
				p[1]=(float) (s.y+e.y)/2;
			break;
			default:
				p[0]=(float) (n.x+o.x)/2;
			p[1]=(float) (n.y+o.y)/2;
			break;
		}
		return p;
	}

}

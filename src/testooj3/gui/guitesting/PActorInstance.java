package testooj3.gui.guitesting;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import testooj3.gui.stmachine.common.Calculus;

import edu.umd.cs.piccolo.nodes.PPath;

public class PActorInstance extends PInstance {

	int diametro=20;
	
	public PActorInstance(ActorInstance st) {
		super(st);
		this.removeAllChildren();
		Random rnd = new Random();
		float x = (float) (300. * rnd.nextDouble());
		float y = (float) (400. * rnd.nextDouble());
		rectangle=PPath.createEllipse(x, y, diametro,diametro);
		rectangle.setPaint(Color.black);
		this.addChild(rectangle);
	}

	public void unhighlight() {
		this.rectangle.setPaint(Color.black);
	}
	
	public boolean setX(double d){
		return this.rectangle.setX(d);
	}
	
	public boolean setY(double d){
		return this.rectangle.setY(d);
	}
	
	public float [] getInputCoord(int cardinal){
		float [] p= new float[2];
		int i=1;
		
		Point e=Calculus.getCoordsEste(this);
		Point o=Calculus.getCoordsOeste(this);
		Point s=Calculus.getCoordsSur(this);
		Point n=Calculus.getCoordsNorte(this);
		
		float d=(float) ((diametro/2)*(1-Math.cos(Math.PI/4)));
		
		switch (cardinal){
			case Calculus.N://si está al norte, pongo la coord al sur
				p[0]=(float) d+((n.x+e.x)/2);
				p[1]=(float)((n.y+e.y)/2)-d;
			break;
			case Calculus.S://si esta al sur lo pongo al norte
				p[0]=(float) ((s.x+o.x)/2)-d;
				p[1]=(float) d+((s.y+o.y)/2);
			break;
			case Calculus.E:
				p[0]=(float) d+((s.x+e.x)/2);
				p[1]=(float) d+((s.y+e.y)/2);
			break;
			default:
				p[0]=(float) ((n.x+o.x)/2)-d;
				p[1]=(float) ((n.y+o.y)/2)-d;
			break;
		}
		return p;
	}
}

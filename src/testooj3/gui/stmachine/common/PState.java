package testooj3.gui.stmachine.common;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolox.nodes.PComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import testooj3.domain.states.State;

/**
 * @author  andres
 */
public class PState extends PComposite {
	protected State state;
	protected PText text;
	protected PNode rectangle;
	protected Vector outputTransitions;
	protected Vector inputTransitions;
	protected PCanvasStateMachine canvas;
	protected static float deltaX = 3 ;
	protected static float deltaY = 3 ;
	
	public PState(State st) {
		this.state=st;
		Random rnd = new Random();
		float x = (float) (300. * rnd.nextDouble());
		float y = (float) (400. * rnd.nextDouble());
		text=new PText(this.state.getName());
		text.setX(x+deltaX);
		text.setY(y+deltaY);
		rectangle=PPath.createRectangle(x, y, (float) (deltaX+text.getWidth()), (float) (text.getHeight()*1.25));
		inputTransitions=new Vector();
		outputTransitions=new Vector();
		addChild(text);
		addChild(rectangle);
		this.text.moveToFront();		
		showPosition();
	}

	public void showPosition() {
		System.out.println(System.currentTimeMillis() + " El nodo " + getName() + " está en (" + getX() + ", " + getY() + ")");
		System.out.println("\tTexto: (" + text.getX() + ", " + text.getY() + ")");
		System.out.println("\tRectángulo: (" + rectangle.getX() + ", " + rectangle.getY() + ")");
	}
	
	public boolean setX(double x) {
		text.setX(x+deltaX);
		rectangle.setX(x);
		rectangle.setWidth(text.getWidth()*1.10);
		return true;
	}
	
	public boolean setY(double y) {
		text.setY(y+deltaY);
		rectangle.setY(y);
		return true;
	}

	public String getName() {
		return this.state.getName();
	}
	
	public double getX() {
		return rectangle.getX();
	}
	
	public double getY() {
		return rectangle.getY();
	}
	
	public double getWidth() {
		return rectangle.getWidth();
	}
	
	public double getHeight() {
		return rectangle.getHeight();
	}
	
	public void highlight(Color color) {
		this.rectangle.setPaint(color);
	}
	
	public void unhighlight() {
		this.rectangle.setPaint(Color.white);
	}
	
	public float randomHorizontal(int tercio) {
		Random r=new Random();
		double valor=r.nextDouble()*(this.getWidth()/3); valor=1;
		if (tercio==2) {
			valor=this.getWidth()/3+valor;
		} else if (tercio==3) {
			valor=2*this.getWidth()/3+valor;
		}
		return (float) (this.getX()+valor);
	}

	public float randomVertical(int tercio) {
		Random r=new Random();
		double valor=r.nextDouble()*(this.getHeight()/3); valor=1;
		if (tercio==2) {
			valor=this.getHeight()/3+valor;
		} else {
			valor=2*this.getHeight()/3+valor;
		}
		return (float) (this.getY()+valor);
	}
	
	public float[] getPuntoMedio() {
		float[] r=new float[2];
		r[0]=(float) (this.getX()+(this.getWidth()/2));
		r[1]=(float) (this.getY()+ (this.getHeight()/2));
		return r;
	}
	
	public void setCanvas(PCanvasStateMachine canvas) {
		this.canvas=canvas;
	}

	public void addOutputTransition(PTransition linea) {
		this.outputTransitions.add(linea);
	}

	public void addInputTransition(PTransition linea) {
		//if(this.inputTransitions==null)
			//this.inputTransitions=new Vector();
		this.inputTransitions.add(linea);
	}
	
	public boolean contains (Object pt){
		for (int i=0;i<this.inputTransitions.size();i++)
			if (this.inputTransitions.get(i).equals(pt))
				return true;
		for (int i=0;i<this.outputTransitions.size();i++)
			if (this.outputTransitions.get(i).equals(pt))
				return true;
		return false;
	}
	
	public boolean equals(Object ps){
		if (ps instanceof PState)
			return this.state.equals(((PState)ps).state);
		else return false;
	}
	
	public String toString(){
		return this.state.toString();
	}
		
	public float [] getInputCoord(int cardinal){
		float [] p= new float[2];
		int i=1;
		
		Point e=Calculus.getCoordsEste(this);
		Point o=Calculus.getCoordsOeste(this);
		Point s=Calculus.getCoordsSur(this);
		Point n=Calculus.getCoordsNorte(this);
		
		switch (cardinal){
			case Calculus.N:
				p[0]=(float) (n.x - this.getWidth()/2);
				p[1]=(float) (n.y);
			break;
			case Calculus.S:
				p[0]=(float) (s.x + this.getWidth()/2);
				p[1]=(float) (s.y);
			break;
			case Calculus.E:
				p[0]=(float) (e.x);
				p[1]=(float) (e.y-this.getHeight()/2);
			break;
			default:
				p[0]=(float) (o.x);
				p[1]=(float) (o.y+this.getHeight()/2);
			break;
		}
		return p;
	}
	
	public float [] getOutputCoord(int cardinal){
		float [] p= new float[2];
		int i=1;
		switch (cardinal){
			case Calculus.N://si está al norte, pongo la coord al sur
				p[0]=(float) (this.getX() + (this.getWidth()/2));
				p[1]=(float) (this.getY() + this.getHeight());
			break;
			case Calculus.S://si esta al sur lo pongo al norte
				p[0]=(float) (this.getX() + (this.getWidth()/2));
				p[1]=(float) (this.getY());
			break;
			case Calculus.E:
				p[0]=(float) (this.getX());
				p[1]=(float) (this.getY() + (this.getHeight()/2));
			break;
			default:
				p[0]=(float) (this.getX() + this.getWidth());
				p[1]=(float) (this.getY() + (this.getHeight()/2));
			break;
		}
		return p;
	}
}
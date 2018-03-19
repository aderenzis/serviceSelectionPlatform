package testooj3.gui.stmachine.common;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.lang.Math;

import testooj3.gui.guitesting.PInstance;
import edu.umd.cs.piccolo.PNode;

public class Calculus {
	public static float RADIO=10;
	public static double ARROW_OFFSET = 0.3 ;
	public static double ARROW_SIZE = 15 ; 
	public static final int N = - 1 ;
	public static final int S = - 2 ;
	public static final int E = 1 ;
	public static final int O = 2 ;
	
	public static float getAngle(PState a, PState b) {
		float alfa=getAngle(puntoMedio(a),puntoMedio(b));
		System.out.println("Ángulo formado por " + a.getName() + " y " + b.getName() + ": " +
				alfa + " radianes (" + Math.toDegrees((double)alfa) + "º)");
		return alfa;
	}
	
	public static float getAngle(float [] pmA, float [] pmB){
		double alfa=0;
		float x=pmA[0]-pmB[0];
		float y=pmA[1]-pmB[1];
		
		if (x>0) {
			if (y<0) 
				alfa=Math.atan(Math.abs(y)/Math.abs(x)); // Primer cuadrante
			else if (y>0)
				alfa=2*Math.PI-Math.atan(Math.abs(y)/Math.abs(x));   // Cuarto cuadrante
			else alfa=0;
		} else if (x<0) {
			if (y<0)
				alfa=Math.PI-Math.atan(y/x);		// Segundo cuadrante
			else if (y>0)
				alfa=Math.PI-Math.atan(y/x);	// Tercer cuadrante
			else 
				alfa=180;
		} else {
			if (y<0)
				alfa=90;
			else 
				alfa=270;
		}
		double alfaG=Math.toDegrees(alfa);
		
		return (float) alfaG;
	}
	
	public static float[] getTransition(PState a, PState b) {
		float[] r=new float[6];
		float angle=getAngle(a, b);
		if (angle==0) {
			r[0]=(float) a.getX();
			r[1]=(float) (a.getY()+a.getHeight()/2);
			r[2]=(float) (b.getX()+b.getWidth());
			r[3]=(float) (b.getY()+b.getHeight()/2);
		} else if (angle==90) {
			r[0]=(float) (a.getX()+a.getWidth()/2);
			r[1]=(float) (a.getY()+a.getHeight());
			r[2]=(float) (b.getX()+b.getWidth()/2);
			r[3]=(float) (b.getY());
		} else if (angle==180) {
			r[0]=(float) (a.getX()+a.getWidth());
			r[1]=(float) (a.getY()+a.getHeight()/2);
			r[2]=(float) (b.getX());
			r[3]=(float) (b.getY()+b.getHeight()/2);
		} else if (angle==270) {
			r[0]=(float) (a.getX()+a.getWidth()/2);
			r[1]=(float) (a.getY());
			r[2]=(float) (b.getX()+b.getWidth()/2);
			r[3]=(float) (b.getY()+b.getHeight());
		} else if (angle>0 && angle<=30) {
			r[0]=(float) (a.getX());
			r[1]=a.randomVertical(3);
			r[2]=(float) (b.getX()+b.getWidth());
			r[3]=b.randomVertical(2);
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO/2;
		} else if (angle>30 && angle<=60) {
			r[0]=(float) (a.getX());
			r[1]=a.randomVertical(2);
			r[2]=(float) (b.getX()+b.getWidth());
			r[3]=b.randomVertical(1);
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO;			
		} else if (angle>60 && angle<90) {
			r[0]=a.randomHorizontal(1);
			r[1]=(float) (a.getY()+a.getHeight());
			r[2]=b.randomHorizontal(3);
			r[3]=(float) b.getY();
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO/2;
		} else if (angle>90 && angle<=120) {
			r[0]=a.randomHorizontal(3);
			r[1]=(float) (a.getY()+a.getHeight());
			r[2]=b.randomHorizontal(2);
			r[3]=(float) b.getY();
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO/2;
		} else if (angle>120 && angle<=150) {
			r[0]=a.randomHorizontal(3);
			r[1]=(float) (a.getY()+a.getHeight());
			r[2]=b.randomHorizontal(1);
			r[3]=(float) b.getY();
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO/2;
		} else if (angle>150 && angle<180) {
			r[0]=(float) (a.getX() + a.getWidth());
			r[1]=a.randomVertical(3);
			r[2]=(float) b.getX();
			r[3]=b.randomVertical(1);
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO;
		} else if (angle>180 && angle<=210) {
			r[0]=(float) (a.getX() + a.getWidth());
			r[1]=a.randomVertical(1);
			r[2]=(float) b.getX();
			r[3]=b.randomVertical(3);
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO/2;
		} else if (angle>210 && angle<=240) {
			r[0]=(float) (a.getX()+a.getWidth());
			r[1]=a.randomVertical(1);
			r[2]=b.randomHorizontal(1);
			r[3]=(float) (b.getY()+b.getHeight());
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO/2;
		} else if (angle>240 && angle<=270) {
			r[0]=a.randomHorizontal(3);
			r[1]=(float) a.getY();
			r[2]=b.randomHorizontal(2);
			r[3]=(float) (b.getY()+b.getHeight());
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO/2;
		} else if (angle>270 && angle<=300) {
			r[0]=a.randomHorizontal(1);
			r[1]=(float) a.getY();
			r[2]=b.randomHorizontal(2);
			r[3]=(float) (b.getY()+b.getHeight());
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO/2;
		} else if (angle>300 && angle<=330) {
			r[0]=a.randomHorizontal(2);
			r[1]=(float) a.getY();
			r[2]=b.randomHorizontal(2);
			r[3]=(float) (b.getY()+b.getHeight());
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO/2;
		} else {
			r[0]=(float) a.getX();
			r[1]=a.randomVertical(1);
			r[2]=(float) (b.getX()+b.getWidth());
			r[3]=b.randomVertical(3);
			r[4]=r[2]-RADIO/2;
			r[5]=r[3]-RADIO/2;
		}
		return r;
	}
	
	public static float[] getTransition2(PState a, PState b) {
		float[] r=new float[4];
		if (alNorte(a, b)) {
			r[0]=(float) (a.getX() + (a.getWidth()/2));
			r[1]=(float) (a.getY() + a.getHeight());
			r[2]=(float) (b.getX() + (b.getWidth()/2));
			r[3]=(float) b.getY();
		} else if (alSur(a, b)) {
			r[0]=(float) (a.getX() + (a.getWidth()/2));
			r[1]=(float) (a.getY());
			r[2]=(float) (b.getX() + (b.getWidth()/2));
			r[3]=(float) (b.getY() + b.getHeight());
		} else if (alEste(a, b)) {
			r[0]=(float) (a.getX());
			r[1]=(float) (a.getY() + (a.getHeight()/2));
			r[2]=(float) (b.getX() + b.getWidth());
			r[3]=(float) (b.getY() + b.getHeight()/2);
		} else {
			r[0]=(float) (a.getX()+a.getWidth());
			r[1]=(float) (a.getY()+a.getHeight()/2);
			r[2]=(float) b.getX();
			r[3]=(float) (b.getY()+b.getHeight()/2);
		}
		getAngle(a, b);
		return r;
	}
	
	private static float[] puntoMedio(PState a) {
		float[] r=new float[2];
		r[0]=(float) (a.getX()+(a.getWidth()/2));
		r[1]=(float) (a.getY()+ (a.getHeight()/2));
		return r;
	}
	
	/*private static boolean alOeste(PNode a, PNode b) {
		return (a.getX()<=b.getX() && a.getX()<=b.getX()+b.getWidth());
	}*/


	private static boolean alEste(PNode a, PNode b) {
		return (a.getX()>b.getX() && a.getX()>b.getX()+b.getWidth());
	}

	private static boolean alSur(PNode a, PNode b) {
		return (b.getY()<=a.getY() && a.getY()>b.getY()+b.getHeight());
	}

	private static boolean alNorte(PNode a, PNode b) {
		return (a.getY()<=b.getY() && b.getY()>a.getY()+a.getHeight());
	}
	
	public static int getCardPoint(PNode a, PNode b){
		double ns=a.getY()-b.getY(),eo=a.getX()-b.getX();
		if (Math.abs(ns)>Math.abs(eo)){
			if (ns>0)
				return S;
			else
				return N;
		}else{
			if (eo>0)
				return E;
			else
				return O;
		}
	}
	
	public static Point getCoordsNorte(PNode a){
		float [] r=new float [2];
		Point p=new Point();
		r[0]=(float) (a.getX() + (a.getWidth()/2));
		r[1]=(float) (a.getY());
		p.setLocation(r[0],r[1]);
		return p;
	}
	
	public static Point getCoordsSur(PNode a){
		float [] r=new float [2];
		Point p=new Point();
		r[0]=(float) (a.getX() + (a.getWidth()/2)/*-(RADIO/2)*/);
		r[1]=(float) (a.getY() + a.getHeight()/* + RADIO/2*/);
		p.setLocation(r[0],r[1]);
		return p;
	}
	
	public static Point getCoordsEste(PNode a){
		float [] r=new float [2];
		Point p=new Point();
		r[0]=(float) (a.getX() + a.getWidth()/*+RADIO/2*/);
		r[1]=(float) (a.getY() + (a.getHeight()/2)/*-(RADIO/2)*/);
		p.setLocation(r[0],r[1]);
		return p;
	}
	
	public static Point getCoordsOeste(PNode a){
		float [] r=new float [2];
		Point p=new Point();
		r[0]=(float) (a.getX()/* - RADIO/2*/);
		r[1]=(float) (a.getY() + (a.getHeight()/2)/*-(RADIO/2)*/);
		p.setLocation(r[0],r[1]);
		return p;
	}
	
	public static float[] getCoordsLabel(float[] ct, float w) {
		float[] r=new float[2];
		r[0]=(float) (ct[0]+ct[2]-w)/2;
		r[1]=(float) (ct[1]+ct[3])/2;
		return r;
	}

	/**Devuelve las coordenadas de la punta de la flecha*/
	public static float[] getCoordsArrow(PNode a, PNode b) {
		float[] r=new float[2];
		if (alNorte(a, b)) {
			r[0]=(float) (b.getX() + (b.getWidth()/2)/*-(RADIO/2)*/);
			r[1]=(float) (b.getY()/* - RADIO/2*/);
		} else if (alSur(a, b)) {
			r[0]=(float) (b.getX() + (b.getWidth()/2)/*-(RADIO/2)*/);
			r[1]=(float) (b.getY() + b.getHeight()/* + RADIO/2*/);
		} else if (alEste(a, b)) {
			r[0]=(float) (b.getX() + b.getWidth()/*+RADIO/2*/);
			r[1]=(float) (b.getY() + (b.getHeight()/2)/*-(RADIO/2)*/);
		} else {
			r[0]=(float) (b.getX()/* - RADIO/2*/);
			r[1]=(float) (b.getY() + (b.getHeight()/2)/*-(RADIO/2)*/);
		}
		return r;
	}
	
	/**Devuelve las coordenadas origen y fin de la flecha
	 * en función de si es de entrada o de salida*/
	public static float[] getCoordsArrow2(PState a, PState b) {
		float[] r=new float[4];
		int cardinal=Calculus.getCardPoint(a,b);
		float [] paux=new float[2],p=new float[4];
		if (cardinal==N || cardinal==S){
			paux=a.getInputCoord(cardinal);
		}else{
			paux=a.getInputCoord(cardinal);
		}
		r[0]=paux[0];
		r[1]=paux[1];
		
		paux=b.getOutputCoord(Calculus.getCardPoint(b,a));
		r[2]=paux[0];
		r[3]=paux[1];
		return r;
	}
	
	/**Devuelve las coordenadas de la cabeza de la flecha. 
	 * Como es un triángulo devuelve 3 puntos.
	 * Se supone que el nodo destino es el b*/
	public static Point []  getCoordsArrowFull (float [] pa, float [] pb){
		double angle=-Math.toRadians(getAngle(pa,pb));
		
		Point [] p = new Point [3];
		
		for (int k=0;k<p.length;k++)
			p[k]=new Point();
		
		p[0].setLocation(pb[0],pb[1]);
		p[1].setLocation(pb[0]+ARROW_SIZE*Math.cos(angle-ARROW_OFFSET),pb[1]+ARROW_SIZE*Math.sin(angle-ARROW_OFFSET));
		p[2].setLocation(pb[0]+ARROW_SIZE*Math.cos(angle+ARROW_OFFSET),pb[1]+ARROW_SIZE*Math.sin(angle+ARROW_OFFSET));
		return p;
	}
	
	/**es como el anterior pero se pasan los estados directamente*/
	public static Point []  getCoordsArrowFull (PState a, PState b){
		float [] pb=getCoordsArrow(a,b);
		float [] pa=getCoordsArrow(b,a);
		return getCoordsArrowFull(pa,pb);
	}
	
	/**es como el anterior pero se pasan los estados directamente*/
	public static Point []  getCoordsArrowFull (PInstance a, PInstance b){
		float [] pb=getCoordsArrow(a,b);
		float [] pa=getCoordsArrow(b,a);
		return getCoordsArrowFull(pa,pb);
	}
	
	public static Point []  getCoordsArrowFull (Point a, Point b){
		float [] pa={(float)a.getX(),(float)a.getY()},pb={(float)b.getX(),(float)b.getY()};
		return getCoordsArrowFull(pa,pb);
	}
	
	/*public static Point []  getCoordsArrowAltFull (Point a, Point b){
		float [] pa={(float)a.getX(),(float)a.getY()},
				pb={(float)b.getX(),(float)b.getY()};
		return getCoordsArrowAltFull(pa,pb);
	}*/
	
	public static Point[] getCoordsArrowAltFull(Point a, int coord) {
		double angle=0;
		switch (coord){
			case N:
				angle=(3*Math.PI)/2;
			break;
			case S:
				angle=Math.PI/2;
			break;
			case E:
				angle=0;
			break;
			case O:
				angle=Math.PI;
			break;
		}
		
		Point [] p = new Point [3];
		for (int i=0;i<p.length;i++)
			p[i]=new Point();
			
		p[0].setLocation(a.x,a.y);
		p[1].setLocation(a.x+ARROW_SIZE*Math.cos(angle+ARROW_OFFSET),a.y+ARROW_SIZE*Math.sin(angle+ARROW_OFFSET));
		p[2].setLocation(a.x+ARROW_SIZE*Math.cos(angle-ARROW_OFFSET),a.y+ARROW_SIZE*Math.sin(angle-ARROW_OFFSET));
		return p;
	}

	/**Devuelve los puntos que forman una polilínea para una transición alternativa*/
	public static Point [] getCoordsTransitionAlt (PState source, PState target, int [] coord){
		Point [] p=new Point[4];
		for (int i=0;i<p.length;i++)
			p[i]=new Point();
		float [] pd=getCoordsArrow(source,target);
		float [] pa=getCoordsArrow(target,source);
		float offsetX=(float) (RADIO+Math.max(source.getWidth(), target.getWidth()));
		float offsetY=(float) (RADIO+Math.max(source.getHeight(), target.getHeight()));
		
		//Se establecen el punto inicial y final
		if (alNorte(source,target) || alSur(source,target)){
			if (pa[0]-pd[0]<0){
				p[0]=getCoordsOeste(source);
				p[3]=getCoordsOeste(target);
				p[1].setLocation(p[0].getX()-offsetX,p[0].getY());
				p[2].setLocation(p[0].getX()-offsetX,p[3].getY());
				coord[0]=O;
			}else{
				p[0]=getCoordsEste(source);
				p[3]=getCoordsEste(target);
				p[1].setLocation(p[0].getX()+offsetX,p[0].getY());
				p[2].setLocation(p[0].getX()+offsetX,p[3].getY());
				coord[0]=E;
			}
		}
		else{
			if (pa[1]-pd[1]<0){
				p[0]=getCoordsNorte(source);
				p[3]=getCoordsNorte(target);
				p[1].setLocation(p[0].getX(),p[0].getY()-offsetY);
				p[2].setLocation(p[3].getX(),p[0].getY()-offsetY);
				coord[0]=N;
				
			}else{
				p[0]=getCoordsSur(source);
				p[3]=getCoordsSur(target);
				p[1].setLocation(p[0].getX(),p[0].getY()+offsetY);
				p[2].setLocation(p[3].getX(),p[0].getY()+offsetY);
				coord[0]=S;
			}
		}
		
				
		return p;
	}
	
	
	public static double getPendiente(float [] a, float [] b){
		return (b[1]-a[1])/(b[0]-a[0]);
	}
	
	public static float getDistancia(float [] a, float [] b){
		return (float) Math.sqrt(Math.pow(a[0]-b[0], 2)+ Math.pow(a[1]-b[1], 2));
	}
	
	/**desplaza el punto en x e y*/
	public static void moverPunto(float [] p, float x, float y){
		p[0]+=x;
		p[1]+=y;
	}
	
	/**Pone el punto de origen a en otro lado del estado, devuelve el nuevo lado*/
	public static byte recolocarPunto(float [] p,PState a, PState b){
		//Se comprueba el punto * y no >, de una flecha de la forma *---->
		if (alNorte (b,a)){
			moverPunto(p,((float)b.getWidth()/2),(float)(-b.getHeight()/2));
			return E;
		}
		else if (alSur(b,a)){
			moverPunto(p,((float)b.getWidth()/2),((float)b.getHeight()/2));
			return E;
		}
		else if (alEste(b,a)){
			moverPunto(p,((float)b.getWidth()/2),((float)b.getHeight()/2));
			return N;
		}else {
			moverPunto(p,(-(float)b.getWidth()/2),((float)b.getHeight()/2));
			return N;
		}
	}

	public static Point[] getCoordsTransitionAlt2(PState source, PState target, int index) {
		Point [] p=new Point[3];
		for (int i=0;i<p.length;i++)
			p[i]=new Point();
		//float [] pfo=getCoordsArrow2(source,target);
		float offset=0;
		int cardPoint=Calculus.getCardPoint(source,target);
		int i=index;
		
		if (index>0){
			offset = 2*RADIO;
			if (index%2==0){
				i=-(index);
				offset=-offset;
				
			}
		}
		
		float [] pfo=source.getOutputCoord(cardPoint);
		p[0].setLocation(pfo[0],pfo[1]);
		
		pfo=target.getInputCoord(cardPoint);
		p[2].setLocation(pfo[0],pfo[1]);
		
		p[1].setLocation(puntoMedioAlt(p[0],p[2],i*(Calculus.ARROW_SIZE)+offset));
		return p;
	}
	
	public static Point[] getCoordsMessaggeAlt2(PInstance source, PInstance target, int index) {
		Point [] p=new Point[3];
		for (int i=0;i<p.length;i++)
			p[i]=new Point();
		//float [] pfo=getCoordsArrow2(source,target);
		float offset=0;
		int cardPoint=Calculus.getCardPoint(source,target);
		int i=index;
		
		if (index>0){
			offset = 2*RADIO;
			if (index%2==0){
				i=-(index);
				offset=-offset;
				
			}
		}
		
		float [] pfo=source.getOutputCoord(cardPoint);
		p[0].setLocation(pfo[0],pfo[1]);
		
		pfo=target.getInputCoord(cardPoint);
		p[2].setLocation(pfo[0],pfo[1]);
		
		p[1].setLocation(puntoMedioAlt(p[0],p[2],i*(Calculus.ARROW_SIZE)+offset));
		return p;
	}
	
	/**Devuelve los puntos que forman una polilínea para una transición alternativa*/
	public static Point [] getCoordsTransitionAlt3 (PState source, PState target, int [] coord){
		Point [] p=new Point[4];
		for (int i=0;i<p.length;i++)
			p[i]=new Point();
		float [] pd=getCoordsArrow(source,target);
		float [] pa=getCoordsArrow(target,source);
		float offsetX=(float) (RADIO+Math.max(source.getWidth(), target.getWidth()));
		float offsetY=(float) (RADIO+Math.max(source.getHeight(), target.getHeight()));
		
		//Se establecen el punto inicial y final
		switch (Calculus.getCardPoint(source,target)){
			case N:
				p[0]=getCoordsNorte(source);
				p[3]=getCoordsNorte(target);
				p[1].setLocation(p[0].getX(),p[0].getY()-offsetY);
				p[2].setLocation(p[3].getX(),p[0].getY()-offsetY);
				coord[0]=N;
				break;
			case S:
				p[0]=getCoordsSur(source);
				p[3]=getCoordsSur(target);
				p[1].setLocation(p[0].getX(),p[0].getY()+offsetY);
				p[2].setLocation(p[3].getX(),p[0].getY()+offsetY);
				coord[0]=S;
				break;
			case E:
				p[0]=getCoordsEste(source);
				p[3]=getCoordsEste(target);
				p[1].setLocation(p[0].getX()+offsetX,p[0].getY());
				p[2].setLocation(p[0].getX()+offsetX,p[3].getY());
				coord[0]=E;
				break;
			case O:
				p[0]=getCoordsOeste(source);
				p[3]=getCoordsOeste(target);
				p[1].setLocation(p[0].getX()-offsetX,p[0].getY());
				p[2].setLocation(p[0].getX()-offsetX,p[3].getY());
				coord[0]=O;
				break;
		}	
		return p;
	}
	
	public static Point puntoMedio(Point a, Point b){
		Point p=new Point();
		p.setLocation((a.getX()+b.getX())/2,(a.getY()+b.getY())/2);
		return p;
	}
	
	public static Point puntoMedioAlt(Point a, Point b, double coef){
		Point p=puntoMedio(a,b);
		p.setLocation(((a.getX()+b.getX())/2)+coef,((a.getY()+b.getY())/2)+coef);
		return p;
	}

	public static Point[] getCoordsTransitionSelf(PState st) {
		Point [] p=new Point[4];
		for (int i=0;i<p.length;i++)
			p[i]=new Point();
		
		float offset=(float) (st.getWidth()/4);
		
		p[0]=Calculus.getCoordsNorte(st);
		p[0].setLocation(p[0].getX()-offset,p[0].getY());
		p[3].setLocation(p[0].getX()+(2*offset),p[0].getY());
		
		p[1].setLocation(p[0].getX(),p[0].getY()-offset-Calculus.ARROW_SIZE);
		p[2].setLocation(p[3].getX(),p[3].getY()-offset-Calculus.ARROW_SIZE);
		
		return p;
	}
	
	public static Point[] getCoordsTransitionSelf(PInstance st) {
		Point [] p=new Point[4];
		for (int i=0;i<p.length;i++)
			p[i]=new Point();
		
		float offset=(float) (st.getWidth()/4);
		
		p[0]=Calculus.getCoordsNorte(st);
		p[0].setLocation(p[0].getX()-offset,p[0].getY());
		p[3].setLocation(p[0].getX()+(2*offset),p[0].getY());
		
		p[1].setLocation(p[0].getX(),p[0].getY()-offset-Calculus.ARROW_SIZE);
		p[2].setLocation(p[3].getX(),p[3].getY()-offset-Calculus.ARROW_SIZE);
		
		return p;
	}
	
	public static int invertCardinal(int cardinal){
		if (cardinal==N)return S;
		else 
			if (cardinal==S) return N;
		else 
			if (cardinal==E)return O;
		else return E;
	}

	

}
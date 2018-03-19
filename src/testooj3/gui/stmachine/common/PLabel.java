package testooj3.gui.stmachine.common;

import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolox.nodes.PComposite;

public class PLabel extends PComposite {
	
	Vector <PText> elementos=null;
	public PText txtSpan = new PText ( "-" ) ;
	public PText txtNoSpan = new PText ( "+" ) ;
	PNode expanded = new PNode ( ) ;
	PNode unexpanded = new PNode ( ) ;
	boolean span;
	
	public PLabel (Vector <String> elements){
		this.elementos=new Vector<PText>();
		for(Iterator it=elements.iterator();it.hasNext();){
			this.elementos.add(new PText(it.next().toString()));
		}
		
		expanded.addChild(txtSpan);
		expanded.addChildren(this.elementos);
		colocarElementos();
		
		unexpanded.addChild(txtNoSpan);
		expanded.addChildren(this.elementos);
		
		this.addChild(expanded);
		this.addChild(unexpanded);
		
		if (this.elementos.size()<=1)
			this.span=true;
		else 
			this.span=false;
		
		this.showSpan(this.span);
		//this.addInputEventListener(new LabelDragHandler());
	}

	private void colocarElementos(){
		double alto=0;
		for (int i=0;i<this.elementos.size();i++){
			if (i>1)
				alto=((PText)elementos.get(i-1)).getHeight()+((PText)elementos.get(i-1)).getY();
			((PText)elementos.get(i)).setY(alto);
			((PText)elementos.get(i)).setX(10);
			((PText)elementos.get(i)).setVisible(true);
		}
	}
	
	//mejor hacer 2 capas y cambiar una por otra en función de si está expandido o no
	public void showSpan(boolean s){
		this.expanded.setVisible(s);
		this.unexpanded.setVisible(!s);
	}

	public void change() {
		//if (this.elementos.size()>1){
			this.span=!this.span;
			this.showSpan(this.span);
		//}
	}

	public void setTextPaint(Color c) {
		for (int i=0;i<this.elementos.size();i++)
			((PText)elementos.get(i)).setTextPaint(c);
		txtSpan.setTextPaint(c);
		txtNoSpan.setTextPaint(c);
	}
}

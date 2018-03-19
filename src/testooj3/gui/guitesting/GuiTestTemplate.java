package testooj3.gui.guitesting;

import java.util.Vector;
import testooj3.domain.TestTemplate;
import testooj3.domain.algorithms.pairwise.PairsTable;

/**
 * @author  andres
 */
@SuppressWarnings("serial")
public class GuiTestTemplate extends TestTemplate {
	protected Vector<IMessagge> messagges;

	public GuiTestTemplate() {
        super();
        this.messagges=new Vector<IMessagge>();
    }
	
    public GuiTestTemplate(TestTemplate sec) {
        super(sec);
        this.messagges=new Vector<IMessagge>();
    }

	public void addMessagge(IMessagge m) {
		this.messagges.add(m);
		this.mMetodos.add(m.getMethod());
	}

	public Vector<IMessagge> getMessagges() {
		return this.messagges;
	}
    
 }

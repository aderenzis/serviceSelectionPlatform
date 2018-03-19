package testooj3.gui.guitesting;

import java.util.Vector;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;

/**
 * @author  andres
 */
public class GuiTestCase extends TestCase {

	protected GuiTestTemplate guiTemplate;

	public GuiTestCase(TestTemplate ts) {
		super(ts);
		this.guiTemplate=(GuiTestTemplate) ts;
	}

	public Vector<IMessagge> getMessagges() {
		return guiTemplate.getMessagges(); 
	}

}

package testooj3.gui.components.conditiontable;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import testooj3.domain.states.Condition;

/**
 * @author  andres
 */
public class JPCreateCondition extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSplitPane jSplitPane = null;
	private JPTableParValues JPTableParValues = null;
	private JPTableDetailedDescrip JPTableDetailedDescrip = null;
	private Vector methods;
	/**
	 * This is the default constructor
	 */
	public JPCreateCondition(Vector methods) {
		super();
		this.methods=methods;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.add(getJSplitPane(), gridBagConstraints);
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPane.setBottomComponent(getJPTableDetailedDescrip());
			jSplitPane.setTopComponent(getJPTableParValues());
		}
		return jSplitPane;
	}

	/**
	 * This method initializes JPTableParValues	
	 * 	
	 * @return testooj3.gui.components.conditiontable.JPTableParValues	
	 */
	private JPTableParValues getJPTableParValues() {
		if (JPTableParValues == null) {
			JPTableParValues = new JPTableParValues();
		}
		return JPTableParValues;
	}

	/**
	 * This method initializes JPTableDetailedDescrip	
	 * 	
	 * @return testooj3.gui.components.conditiontable.JPTableDetailedDescrip	
	 */
	private JPTableDetailedDescrip getJPTableDetailedDescrip() {
		if (JPTableDetailedDescrip == null) {
			JPTableDetailedDescrip = new JPTableDetailedDescrip(this.methods);
		}
		return JPTableDetailedDescrip;
	}

	public Condition getCondition() {
		Condition condition=new Condition();
		condition.setDetailedDescriptions(this.JPTableDetailedDescrip.getDetailedDescriptions());
		condition.setParameterValues(this.JPTableParValues.getParameterValues());
		return condition;
	}

}

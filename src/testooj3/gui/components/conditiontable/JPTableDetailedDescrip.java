package testooj3.gui.components.conditiontable;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import testooj3.domain.states.DetailedDescription;

/**
 * @author  andres
 */
public class JPTableDetailedDescrip extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private DetailedDescripTableModel tModelDetailedDescrip=null;
	//private Vector observerMethods=null;
	/**
	 * This is the default constructor
	 */
	public JPTableDetailedDescrip(Vector oM) {
		super();
		//this.observerMethods=oM;
		this.tModelDetailedDescrip=new DetailedDescripTableModel(oM);
		initialize();
	}
	
	public JPTableDetailedDescrip() {
		this(new Vector());
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
		this.setSize(300, 96);
		this.setLayout(new GridBagLayout());
		this.add(getJScrollPane(), gridBagConstraints);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable(tModelDetailedDescrip);
		}
		return jTable;
	}

	public Vector<DetailedDescription> getDetailedDescriptions() {
		return this.tModelDetailedDescrip.getDetailedDescriptions();
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

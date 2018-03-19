package testooj3.gui.guitesting;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import testooj3.domain.Interface;
import testooj3.domain.TField;

/**
 * @author  andres
 */
public class JPStep1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jpPassTo = null;
	private JButton jbPass = null;
	private JCheckBox jchPassToFixture = null;
	private JCheckBox jchPassToSetUp = null;
	private JLabel jLabel = null;
	private JScrollPane jspFixtures = null;
	private JEditorPane jepFixtures = null;
	private JLabel jLabel1 = null;
	private JScrollPane jspSetUp = null;
	private JEditorPane jepSetUp = null;
	private Interface mClass;
	private TField fieldSelected;  //  @jve:decl-index=0:
	private JFGuiTesting parentWindow;
	
	/**
	 * This is the default constructor
	 */
	public JPStep1() {
		super();
		initialize();
	}
	
	public void setParentWindow(JFGuiTesting pw) {
		this.parentWindow=pw;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(167, 156, 87, 22));
		jLabel1.setText("setUp()");
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(166, 14, 76, 18));
		jLabel.setText("Fixtures");
		this.setSize(725, 410);
		this.setLayout(null);
		this.add(getJpPassTo(), null);
		this.add(jLabel, null);
		this.add(getJspFixtures(), null);
		this.add(jLabel1, null);
		this.add(getJspSetUp(), null);
	}
	
	public void setClass(Interface c) {
		this.mClass=c;
	}

	/**
	 * This method initializes jpPassTo	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJpPassTo() {
		if (jpPassTo == null) {
			jpPassTo = new JPanel();
			jpPassTo.setLayout(null);
			jpPassTo.setBounds(new Rectangle(12, 14, 136, 113));
			jpPassTo.add(getJbPass(), null);
			jpPassTo.add(getJchPassToFixture(), null);
			jpPassTo.add(getJchPassToSetUp(), null);
		}
		return jpPassTo;
	}

	/**
	 * This method initializes jbPass	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbPass() {
		if (jbPass == null) {
			jbPass = new JButton();
			jbPass.setBounds(new Rectangle(15, 75, 106, 26));
			jbPass.setText("Pass");
			jbPass.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					passField();
				}
			});
		}
		return jbPass;
	}

	/**
	 * This method initializes jchPassToFixture	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJchPassToFixture() {
		if (jchPassToFixture == null) {
			jchPassToFixture = new JCheckBox();
			jchPassToFixture.setBounds(new Rectangle(15, 15, 121, 16));
			jchPassToFixture.setText("Pass to fixture");
			jchPassToFixture.setSelected(true);
		}
		return jchPassToFixture;
	}

	/**
	 * This method initializes jchPassToSetUp	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJchPassToSetUp() {
		if (jchPassToSetUp == null) {
			jchPassToSetUp = new JCheckBox();
			jchPassToSetUp.setBounds(new Rectangle(15, 45, 121, 16));
			jchPassToSetUp.setText("Pass to setUp()");
			jchPassToSetUp.setSelected(true);
		}
		return jchPassToSetUp;
	}

	/**
	 * This method initializes jspFixtures	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspFixtures() {
		if (jspFixtures == null) {
			jspFixtures = new JScrollPane();
			jspFixtures.setBounds(new Rectangle(166, 38, 550, 115));
			jspFixtures.setViewportView(getJepFixtures());
		}
		return jspFixtures;
	}

	/**
	 * This method initializes jepFixtures	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJepFixtures() {
		if (jepFixtures == null) {
			jepFixtures = new JEditorPane();
		}
		return jepFixtures;
	}

	/**
	 * This method initializes jspSetUp	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspSetUp() {
		if (jspSetUp == null) {
			jspSetUp = new JScrollPane();
			jspSetUp.setBounds(new Rectangle(166, 182, 549, 125));
			jspSetUp.setViewportView(getJepSetUp());
		}
		return jspSetUp;
	}

	/**
	 * This method initializes jepSetUp	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJepSetUp() {
		if (jepSetUp == null) {
			jepSetUp = new JEditorPane();
		}
		return jepSetUp;
	}
	
	protected void passField() {
		if (this.fieldSelected==null)
			return;
		if (this.jchPassToFixture.isSelected())
			passToFixture();
		if (this.jchPassToSetUp.isSelected())
			passToSetUp();
	}
	
	private void passToFixture() {
		// JTextField dividendo;
		GuiFixture gf=this.parentWindow.findFixture(this.fieldSelected.getName());
		if (gf!=null)
			return;
		gf=new GuiFixture(this.fieldSelected);
		String texto=gf.toString();
    	if (this.parentWindow.containsFixture(this.fieldSelected))
    		return;
    	this.parentWindow.addFixture(gf);
    	texto=this.jepFixtures.getText() + "\n" + texto;
    	this.jepFixtures.setText(texto);
	}

	private void passToSetUp() {
		// JTextField dividendo=(JTextField) findFieldInstance(o, "jtfDividendo");
		GuiSetup gst=this.parentWindow.findSetup(this.fieldSelected.getName());
		if (gst!=null)
			return;
		gst=new GuiSetup(this.fieldSelected);
    	String texto=gst.toString();
    	this.parentWindow.addSetUp(gst);
    	texto=this.jepSetUp.getText() + "\n" + texto;
    	this.jepSetUp.setText(texto);
	}

	public void fieldSelected(TField selectedField) {
		this.fieldSelected=selectedField;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"

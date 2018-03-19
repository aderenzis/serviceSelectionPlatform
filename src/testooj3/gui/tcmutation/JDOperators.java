package testooj3.gui.tcmutation;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import testooj3.domain.tcmutation.TCMutationOperators;
/**
 * @author  Administrador
 */
public class JDOperators extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JCheckBox jchCL = null;
	private JCheckBox jchIN = null;
	private JCheckBox jchPI = null;
	private JCheckBox jchNUL = null;
	private JCheckBox jchRC = null;
	private JCheckBox jchMAX = null;
	private JButton jbOk = null;
	private JCheckBox jchMIN = null;
	private JCheckBox jchINC = null;
	private JCheckBox jchDEC = null;
	/**
	 * This is the default constructor
	 */
	public JDOperators() {
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Test case mutation operators");
		this.setSize(219, 330);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(11);
			gridLayout.setHgap(0);
			gridLayout.setVgap(2);
			gridLayout.setColumns(1);
			jLabel = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jLabel.setText("Select the operators to apply");
			jContentPane.setLayout(gridLayout);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJrbCL(), null);
			jContentPane.add(getJrbIN(), null);
			jContentPane.add(getJrbPI(), null);
			jContentPane.add(getJCheckBox(), null);
			jContentPane.add(getJrbRC(), null);
			jContentPane.add(getJrbST(), null);
			jContentPane.add(getJCheckBox2(), null);
			jContentPane.add(getJCheckBox3(), null);
			jContentPane.add(getJCheckBox4(), null);
			jContentPane.add(getJbOk(), null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJrbCL() {
		if (jchCL == null) {
			jchCL = new JCheckBox();
			jchCL.setText("CL (remove a call)");
		}
		return jchCL;
	}
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJrbIN() {
		if (jchIN == null) {
			jchIN = new JCheckBox();
			jchIN.setText("IN (interchange of calls)");
		}
		return jchIN;
	}
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJrbPI() {
		if (jchPI == null) {
			jchPI = new JCheckBox();
			jchPI.setText("PI (parameter interchange)");
		}
		return jchPI;
	}
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJCheckBox() {
		if (jchNUL == null) {
			jchNUL = new JCheckBox();
			jchNUL.setText("NUL (set to NULL)");
		}
		return jchNUL;
	}
	/**
	 * This method initializes jRadioButton1	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJrbRC() {
		if (jchRC == null) {
			jchRC = new JCheckBox();
			jchRC.setText("RC (repeat calls)");
		}
		return jchRC;
	}
	/**
	 * This method initializes jRadioButton1	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJrbST() {
		if (jchMAX == null) {
			jchMAX = new JCheckBox();
			jchMAX.setText("MAX (set to MAX)");
		}
		return jchMAX;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbOk() {
		if (jbOk == null) {
			jbOk = new JButton();
			jbOk.setText("Ok");
			jbOk.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					close();
				}
			});
		}
		return jbOk;
	}
    /**
     * 
     */
    protected void close() {
        TCMutationOperators instance=TCMutationOperators.getInstance();
        instance.CL=this.jchCL.isSelected();
        instance.IN=this.jchIN.isSelected();
        instance.PI=this.jchPI.isSelected();
        instance.NUL=this.jchNUL.isSelected();
        instance.RC=this.jchRC.isSelected();
        instance.MAX=this.jchMAX.isSelected();    
        instance.MIN=this.jchMIN.isSelected();
        instance.INC=this.jchINC.isSelected();
        instance.DEC=this.jchDEC.isSelected();
        setVisible(false);
    }
    /**
     * @param instance
     */
    public void loadOperators(TCMutationOperators instance) {
        this.jchCL.setSelected(instance.CL);
        this.jchIN.setSelected(instance.IN);
        this.jchPI.setSelected(instance.PI);
        this.jchNUL.setSelected(instance.NUL);
        this.jchRC.setSelected(instance.RC);
        this.jchMAX.setSelected(instance.MAX);
        this.jchMIN.setSelected(instance.MIN);
        this.jchINC.setSelected(instance.INC);
        this.jchDEC.setSelected(instance.DEC);
    }
	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJCheckBox2() {
		if (jchMIN == null) {
			jchMIN = new JCheckBox();
			jchMIN.setText("MIN (set to MIN)");
		}
		return jchMIN;
	}
	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJCheckBox3() {
		if (jchINC == null) {
			jchINC = new JCheckBox();
			jchINC.setText("INC (increases 1)");
		}
		return jchINC;
	}
	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJCheckBox4() {
		if (jchDEC == null) {
			jchDEC = new JCheckBox();
			jchDEC.setText("DEC (decreases 1)");
		}
		return jchDEC;
	}
    }  //  @jve:decl-index=0:visual-constraint="10,10"

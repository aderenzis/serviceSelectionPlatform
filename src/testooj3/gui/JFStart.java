package testooj3.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;

import testooj3.gui.compatibility.JFCompatibility;
import testooj3.gui.components.ILogWindow;
import testooj3.gui.guitesting.JFGuiTesting;
import me.gui.JDTestCasesExecutor;

/**
 * @author  Administrador  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class JFStart extends JFrame implements ILogWindow{

	private javax.swing.JPanel jContentPane = null;
	private JButton jbJUnitMujava = null;
	private JButton jbTestCaseExecutor = null;
	private JButton jbGUITesting = null;
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbJUnitMujava() {
		if (jbJUnitMujava == null) {
			jbJUnitMujava = new JButton();
			jbJUnitMujava.setText("JUnit/MuJava test case generator");
			jbJUnitMujava.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					startJUnitMujava();
				}
			});
		}
		return jbJUnitMujava;
	}
	/**
     * 
     */
    protected void startJUnitMujava() {
        JFJUnit j=new JFJUnit();
        j.setVisible(true);
    }
    /**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbTestCaseMutant() {
		if (jbTestCaseExecutor == null) {
			jbTestCaseExecutor = new JButton();
			jbTestCaseExecutor.setText("Test case executor");
			jbTestCaseExecutor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					testCaseExecutor();
				}
			});
		}
		return jbTestCaseExecutor;
	}
	
    protected void testCaseExecutor() {
    	try {
    		JDTestCasesExecutor j=new JDTestCasesExecutor();
    		j.setVisible(true);
    	}
    	catch (Exception ex) {
    		System.out.println(ex.toString());
    	}
	}
    
	/**
	 * This method initializes jbGUITesting	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbGUITesting() {
		if (jbGUITesting == null) {
			jbGUITesting = new JButton();
			jbGUITesting.setText("GUI test case designer");
			jbGUITesting.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					guiTesting();
				}
			});
		}
		return jbGUITesting;
	}
	
	protected void guiTesting() {
		JFGuiTesting j=new JFGuiTesting();
		j.setVisible(true);
	}
	public static void main(String[] args) {
		JFStart j=new JFStart();
//		JFCompatibility j = new JFCompatibility();
		
		/**Comprobamos si es la primera vez que se ejecuta el programa*/
		File f=new File ("./testooj3setup.txt");
		if (!f.exists()){
			JDSetup jst=new JDSetup(j);
			jst.setModal(true);
			jst.setVisible(true);
			jst.setVisibleWarning(true);
		}
		
        
        j.setVisible(true);
    }
	/**
	 * This is the default constructor
	 */
	public JFStart() {
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(586, 125);
		this.setContentPane(getJContentPane());
		this.setTitle("Testooj - A Java testing tool for Java");
		this.addWindowListener(new java.awt.event.WindowAdapter() { 
			public void windowClosing(java.awt.event.WindowEvent e) {    
				quit();
			}
		});
	}
	/**
     * 
     */
    protected void quit() {
        System.exit(0);
    }
    /**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getJbJUnitMujava(), java.awt.BorderLayout.WEST);
			jContentPane.add(getJbTestCaseMutant(), java.awt.BorderLayout.EAST);
			jContentPane.add(getJbGUITesting(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	public void log(String msg) {
		System.out.println(msg);
	}
	public void logError(String string) {
		System.out.println(string);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

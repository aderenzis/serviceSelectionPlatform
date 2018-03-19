package testooj3.gui.stmachine.states;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolox.PFrame;
import edu.umd.cs.piccolox.swing.PScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import testooj3.domain.Configuration;
import testooj3.domain.states.State;
import testooj3.domain.states.StateMachine;
import testooj3.domain.states.Transition;
import testooj3.domain.xml.XMLStatesReader;

/**
 * @author  andres
 */
public class JFStates extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton jbSelectFile = null;

	private JTextField jtfFileName = null;

	private JButton jbOpen = null;

	private JButton jbZipSM = null;

	private StateMachine stateMachine;

	private JTPStates jTPStates = null;

	private JPanel jPanelTop = null;

	/**
	 * This is the default constructor
	 */
	public JFStates() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(1173, 694);
		this.setContentPane(getJContentPane());
		this.setTitle("States");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.ipadx = 2;
			gridBagConstraints5.ipady = 2;
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.anchor = GridBagConstraints.CENTER;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.ipadx = 2;
			gridBagConstraints4.ipady = 2;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJTPStates(), gridBagConstraints4);
			jContentPane.add(getJPanelTop(), gridBagConstraints5);
		}
		return jContentPane;
	}
	
	public void selectFile() {
        JFileChooser fc=new JFileChooser();
        
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal=fc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) 
          jtfFileName.setText(fc.getSelectedFile().getAbsolutePath());		
	}

	/**
	 * This method initializes jbSelectFile	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbSelectFile() {
		if (jbSelectFile == null) {
			jbSelectFile = new JButton();
			jbSelectFile.setText("Select file");
			jbSelectFile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					selectFile();
				}
			});
		}
		return jbSelectFile;
	}

	/**
	 * This method initializes jtfFileName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJtfFileName() {
		if (jtfFileName == null) {
			jtfFileName = new JTextField();
			
			jtfFileName.setText(Configuration.getInstance().getWorkingPath()+"results\\StatesTestStatesAccountWithLoan1.xml");
		}
		return jtfFileName;
	}

	/**
	 * This method initializes jbOpen	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbOpen() {
		if (jbOpen == null) {
			jbOpen = new JButton();
			jbOpen.setText("Open");
			jbOpen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openFile();
				}
			});
		}
		return jbOpen;
	}

	protected void openFile() {
		try {
			XMLStatesReader reader=new XMLStatesReader(this.jtfFileName.getText());
			stateMachine = reader.getDetailedStateMachine();
			this.jTPStates.add(this.stateMachine);
			//this.pCanvasStates.setStateMachine(stateMachine);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.toString());
		}
	}



	/**
	 * This method initializes jbZipSM	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbZipSM() {
		if (jbZipSM == null) {
			jbZipSM = new JButton();
			jbZipSM.setText("Minimize state machine");
			jbZipSM.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					minimizeStateMachine();
				}
			});
		}
		return jbZipSM;
	}

	protected void minimizeStateMachine() {
		StateMachine msm=this.stateMachine.zip1();
		this.jTPStates.add(msm);
		StateMachine msm2=msm.zip2();
		this.jTPStates.add(msm2);
		StateMachine msm3=msm2.zip3();
		this.jTPStates.add(msm3);
	}

	/**
	 * This method initializes jTPStates	
	 * 	
	 * @return testooj3.gui.states.JTPStates	
	 */
	private JTPStates getJTPStates() {
		if (jTPStates == null) {
			jTPStates = new JTPStates();
		}
		return jTPStates;
	}

	/**
	 * This method initializes jPanelTop	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelTop() {
		if (jPanelTop == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.fill = GridBagConstraints.NONE;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.insets = new Insets(0, 5, 0, 5);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jPanelTop = new JPanel();
			jPanelTop.setLayout(new GridBagLayout());
			jPanelTop.add(getJbSelectFile(), gridBagConstraints);
			jPanelTop.add(getJtfFileName(), gridBagConstraints1);
			jPanelTop.add(getJbOpen(), gridBagConstraints2);
			jPanelTop.add(getJbZipSM(), gridBagConstraints3);
		}
		return jPanelTop;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

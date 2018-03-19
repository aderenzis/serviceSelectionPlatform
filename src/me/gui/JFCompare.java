package me.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

/**
 * @author  andres
 */
public class JFCompare extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JSplitPane jSplitPane = null;

	private JScrollPane jScrollPane = null;

	private JScrollPane jScrollPane1 = null;

	private JTextPane jepOriginal = null;

	private JTextPane jepVersion = null;

	private JScrollPane jScrollPane2 = null;

	private JTextPane jtpLog = null;

	/**
	 * This is the default constructor
	 */
	public JFCompare(String version, String original) {
		super();
		initialize();
		this.jepOriginal.setToolTipText("Original file (" + original + ")");
		this.jepVersion.setToolTipText("Version (" + version + ")");
		load(version, original);
	}

	protected void load(String version, String original) {		
		try {
			FileInputStream fVersion = new FileInputStream(version);
			byte[] bVersion=new byte[fVersion.available()];
			fVersion.read(bVersion);
			fVersion.close();
			FileInputStream fOriginal=new FileInputStream(original);
			byte[] bOriginal=new byte[fOriginal.available()];
			fOriginal.read(bOriginal);
			fOriginal.close();
			Vector<String> lineasOriginal = toLinesVector(bOriginal);
			Vector<String> lineasVersion= toLinesVector(bVersion);
			Vector<Integer> diferences=new Vector<Integer>();
			int max=lineasOriginal.size()>lineasVersion.size() ? lineasOriginal.size() : lineasVersion.size();
			for (int i=0; i<max; i++) {
				String lineaOriginal=lineasOriginal.get(i).trim();
				String lineaVersion=lineasVersion.get(i).trim(); 
				if (lineaVersion.trim().equals(lineaOriginal.trim())) {
					lineaOriginal=(i+1) + ". " +  lineaOriginal;
					lineaVersion=(i+1) + ". " + lineaVersion;					
				} else {
					lineaOriginal="** " + (i+1) + ". " + lineaOriginal;
					lineaVersion="** " + (i+1) + ". " + lineaVersion;
					diferences.add(i+1);
				}
				this.jepOriginal.setText(this.jepOriginal.getText() + "\n" + lineaOriginal);
				this.jepVersion.setText(this.jepVersion.getText() + "\n" + lineaVersion);
			}
			this.jepOriginal.select(0,0);
			this.jepVersion.select(0,0);
			String msg="No diferences found";
			if (diferences.size()>0) {
				msg="Diferences in lines: ";
				for (int i=0; i<diferences.size(); i++)
					msg+=diferences.get(i) + ", ";
				msg=msg.substring(0, msg.length()-2);
			}
			this.jtpLog.setText(msg);
		} catch (Exception e) {
			this.jtpLog.setText("Error reading " + e.getMessage());
		}
	}
	
	private Vector<String> toLinesVector(byte[] b) {
		String texto=new String(b);
		StringTokenizer st=new StringTokenizer(texto, "\n");
		Vector<String> lineas=new Vector<String>();
		while (st.hasMoreTokens()) {
			lineas.add(st.nextToken());
		}
		return lineas;
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(795, 582);
		this.setContentPane(getJContentPane());
		this.setTitle("Mutant comparer");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJSplitPane(), null);
			jContentPane.add(getJScrollPane2(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setBounds(new Rectangle(0, 0, 786, 488));
			jSplitPane.setDividerSize(5);
			jSplitPane.setDividerLocation(250);
			jSplitPane.setTopComponent(getJScrollPane());
			jSplitPane.setBottomComponent(getJScrollPane1());
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJepOriginal());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJepVersion());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jepOriginal	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJepOriginal() {
		if (jepOriginal == null) {
			jepOriginal = new JTextPane();
			jepOriginal.setContentType("text/plain");
		}
		return jepOriginal;
	}

	/**
	 * This method initializes jepVersion	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJepVersion() {
		if (jepVersion == null) {
			jepVersion = new JTextPane();
			jepVersion.setContentType("text/plain");
		}
		return jepVersion;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(3, 489, 781, 55));
			jScrollPane2.setViewportView(getJtpLog());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes jtpLog	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJtpLog() {
		if (jtpLog == null) {
			jtpLog = new JTextPane();
		}
		return jtpLog;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

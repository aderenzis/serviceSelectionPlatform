package me.gui;

import java.awt.Rectangle;
import java.io.FileOutputStream;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import me.domain.ObjectTable;
import me.domain.ResultsTable;
import me.domain.TimesTable;
import testooj3.domain.Configuration;
/**
 * @author  Administrador
 */
@SuppressWarnings("serial")
public class JDResultsAnalyzer extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private DefaultTableModel resultsModel = null;   //  @jve:decl-index=0:
	private JScrollPane jScrollPane1 = null;
	private JEditorPane jepMsg = null;
	private JButton jbBuildStubFile = null;
    private ResultsTable resultsTable;  //  @jve:decl-index=0:
    private Vector bestTestCases;
	private JButton jbSaveAs = null;
	private JButton jbReanalysis = null;
	private Vector<String> aliveMutants;  //  @jve:decl-index=0:
	private JTable jtResults = null;
	private String tabs;  //  @jve:decl-index=0:
	private JButton jbSaveTimes = null;
	private JRadioButton jrbTXT = null;
	private JRadioButton jrbHTML = null;
	private ButtonGroup grupoSaveAs;
	/**
	 * This is the default constructor
	 */
	public JDResultsAnalyzer() {
		super();
		initialize();
		this.resultsModel=new DefaultTableModel();
		this.jtResults.setModel(this.resultsModel);
		grupoSaveAs=new ButtonGroup();
		grupoSaveAs.add(this.jrbHTML);
		grupoSaveAs.add(this.jrbTXT);
	}
	
	public void setResults(ObjectTable ot) {
		this.resultsTable=ot;
	    Vector<String> colTitles=ot.getColTitles();
	    colTitles.insertElementAt("", 0);
	    this.resultsModel.setColumnIdentifiers(colTitles);
	    for (int i=0; i<ot.getRowTitles().size(); i++) {
	        Vector<String> row=ot.getRowWithKillResults(i);
	        this.resultsModel.addRow(row); 
	    }
	    double mutants=ot.getRows().size()-1; 
	    this.aliveMutants=new Vector<String>();
	    if (ot.getAlgorithm()!=null) {
	    	bestTestCases=ot.priorizeTestCases(aliveMutants);
		    String msg="<b><u>Summary of results:</b></u><br>";
		    msg+="Minimal set of test cases (" + bestTestCases.size() + " test cases of "
		    	+ (ot.getColTitles().size()-1) + "):<ul>";
		    for (int i=0; i<bestTestCases.size(); i++) {
		        msg+="<li>" + bestTestCases.get(i).toString() + "</li>";
		    }
		    msg+="</ul>";
		    double alive=this.aliveMutants.size()-1;
		    double percentage=(mutants-alive)/mutants;
		    percentage=Math.round(percentage*10000)/100;
		    msg+=(int) alive + " alive and " + ((int) mutants-alive) + " killed of " + (int) mutants + " mutants (" + percentage + "% killed):<ul>";
		    for (int i=0; i<this.aliveMutants.size(); i++) {
		        msg+="<li>" + this.aliveMutants.get(i).toString() + "</li>";
		    }
		    msg+="</ul>";
		    log(msg);
	    }
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Result analyzer");
		this.setSize(1097, 585);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(getJbBuildStubFile(), null);
			jContentPane.add(getJbSaveAs(), null);
			jContentPane.add(getJbReanalysis(), null);
			jContentPane.add(getJbSaveTimes(), null);
			jContentPane.add(getJrbTxt(), null);
			jContentPane.add(getJrbHTML(), null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(6, 6, 1073, 283);
			jScrollPane.setViewportView(getJtResults());
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
			jScrollPane1.setBounds(6, 300, 1073, 181);
			jScrollPane1.setViewportView(getJepMsg());
		}
		return jScrollPane1;
	}
	/**
	 * This method initializes jEditorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */    
	private JEditorPane getJepMsg() {
		if (jepMsg == null) {
			jepMsg = new JEditorPane();
			jepMsg.setContentType("text/html");
		}
		return jepMsg;
	}
	
    public void logError(String msg) {
        msg="<font color='red'>" + msg + "</font>"+jepMsg.getText().trim();
        this.jepMsg.setText(msg);
        jepMsg.select(0, 0);  
    }
    
    public void log(String msg) {
        msg=msg+jepMsg.getText().trim();
        this.jepMsg.setText(msg);
        jepMsg.select(0, 0);  
    }

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbBuildStubFile() {
		if (jbBuildStubFile == null) {
			jbBuildStubFile = new JButton();
			jbBuildStubFile.setBounds(800, 488, 162, 36);
			jbBuildStubFile.setText("Build stub file");
			jbBuildStubFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					buildStubFile();
				}
			});
		}
		return jbBuildStubFile;
	}

    /**
     * 
     */
    protected void buildStubFile() {
        try {
            String cutName=this.resultsTable.getCutName();
            if (cutName.lastIndexOf(".")!=-1)
                cutName=cutName.substring(cutName.lastIndexOf(".")+1);
	        String fileName=Configuration.getInstance().getResultsPath() + "TestMinimal" + cutName + ".java";
	        FileOutputStream f=new FileOutputStream(fileName);
	        String s="package testooj3.results;\n";
	        f.write(s.getBytes());
	        s="import junit.framework.*;\n";
	        f.write(s.getBytes());
	        s="public class TestMinimal" + cutName + " extends TestCase {\n";
	        f.write(s.getBytes());
	        for (int i=0; i<bestTestCases.size(); i++) {
	            String tcn=bestTestCases.get(i).toString();
	            s="\tpublic void " + tcn + "() {\n";
		        f.write(s.getBytes());
		        s="\t\t(new Test" + cutName + "())." + tcn + "();\n";
		        f.write(s.getBytes());
	            s="\t}\n\n";
		        f.write(s.getBytes());
	        }
	        s="}\n";
	        f.write(s.getBytes());
	        f.close();
        }
        catch (Exception ex) {
            this.logError(ex.toString());
        }
    }
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJbSaveAs() {
		if (jbSaveAs == null) {
			jbSaveAs = new JButton();
			jbSaveAs.setBounds(443, 488, 155, 36);
			jbSaveAs.setText("Save as...");
			jbSaveAs.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					saveAs();
				}
			});
		}
		return jbSaveAs;
	}

    /**
     * 
     */
    protected void saveAs() {
        try {
        	String fileName=this.resultsTable.getCutName();
        	if (fileName.endsWith("/")) fileName=fileName.substring(0, fileName.length()-1);
	        fileName=Configuration.getInstance().getResultsPath() + fileName;
	        if (this.jrbHTML.isSelected())
	        	fileName+=".html";
	        else fileName+=".txt";	        
	        FileOutputStream f=new FileOutputStream(fileName);
	        if (this.jrbHTML.isSelected()) {
	        	this.resultsTable.saveAsHTMLTable(f, fileName);
	        	f.write(this.jepMsg.getText().getBytes());
	        } else {
	        	this.resultsTable.saveAsTXTTable(f, fileName);
	        }
	        f.close();
	        this.log("Saved as " + fileName);
        }
        catch (Exception ex) {
            this.logError(ex.toString());
        }
    }

	/**
	 * This method initializes jbReanalysis	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbReanalysis() {
		if (jbReanalysis == null) {
			jbReanalysis = new JButton();
			jbReanalysis.setBounds(new Rectangle(6, 488, 207, 36));
			jbReanalysis.setText("Re-analysis excluding alive");
			jbReanalysis.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					reanalysisExcludingAlive();
				}
			});
		}
		return jbReanalysis;
	}

	protected void reanalysisExcludingAlive() {

	}

	/**
	 * This method initializes jtResults	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJtResults() {
		if (jtResults == null) {
			jtResults = new JTable();
		}
		return jtResults;
	}

	public void setResults(TimesTable tt) {
		this.resultsTable=tt;
	    Vector<String> colTitles=tt.getColTitles();
	    colTitles.insertElementAt("", 0);
	    this.resultsModel.setColumnIdentifiers(colTitles);
	    for (int i=0; i<tt.getRowTitles().size(); i++) {
	        Vector row=tt.getRow(i);
	        this.resultsModel.addRow(row); 
	    }
	}

	public void setNumberOfTabs(int n) {
		this.tabs="";
		for (int i=0; i<n; i++)
			this.tabs+="\t";
	}

	/**
	 * This method initializes jbSaveInSeparated	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbSaveTimes() {
		if (jbSaveTimes == null) {
			jbSaveTimes = new JButton();
			jbSaveTimes.setBounds(new Rectangle(608, 488, 183, 36));
			jbSaveTimes.setText("Save times' table");
			jbSaveTimes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					saveTimesTable();
				}
			});
		}
		return jbSaveTimes;
	}

	protected void saveTimesTable() {
	       try {
	        	String timesFileName=this.resultsTable.getCutName();
	        	if (timesFileName.endsWith("/")) timesFileName=timesFileName.substring(0, timesFileName.length()-1);
		        timesFileName=Configuration.getInstance().getResultsPath() + timesFileName + ".times.txt";
		        FileOutputStream fTimes=new FileOutputStream(timesFileName);
		        this.resultsTable.saveTimesTable(fTimes, timesFileName);
		        fTimes.close();
		        this.log("Saved as " + timesFileName);
	       }
	       catch (Exception ex) {
	    	   logError(ex.toString());
	       }
	}

	/**
	 * This method initializes jrbTxt	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbTxt() {
		if (jrbTXT == null) {
			jrbTXT = new JRadioButton();
			jrbTXT.setBounds(new Rectangle(444, 526, 57, 15));
			jrbTXT.setSelected(true);
			jrbTXT.setText(".txt");
		}
		return jrbTXT;
	}

	/**
	 * This method initializes jrbHTML	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbHTML() {
		if (jrbHTML == null) {
			jrbHTML = new JRadioButton();
			jrbHTML.setBounds(new Rectangle(511, 526, 67, 17));
			jrbHTML.setText(".html");
		}
		return jrbHTML;
	}
 }  //  @jve:decl-index=0:visual-constraint="10,10"

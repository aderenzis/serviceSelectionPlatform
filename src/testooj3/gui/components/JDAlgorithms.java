package testooj3.gui.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import testooj3.domain.algorithms.Algorithm;
import testooj3.domain.algorithms.AllCombinationsElegant;
import testooj3.domain.algorithms.AntiRandom;
import testooj3.domain.algorithms.AntiRandomPairWise;
import testooj3.domain.algorithms.EachChoice;
import testooj3.domain.algorithms.PairWiseAC;
import testooj3.domain.algorithms.PairWiseSteps;
import testooj3.domain.algorithms.PairWiseStepsFirstCombination;
/**
 * @author  Administrador
 */
@SuppressWarnings("serial")
public class JDAlgorithms extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JRadioButton jrbEachChoice = null;
	private JRadioButton jrbBaseChoice = null;
	private JRadioButton jrbAllCombinations = null;
	private JRadioButton jrbAntiRandom = null;
	private JRadioButton jrbPairWiseAC = null;
	private JButton jbOk = null;
	private Algorithm algorithm;
	private JRadioButton jrbPairWise2 = null;
	private JRadioButton jrbPairWiseStepsFirstCombination = null;
	private JRadioButton jrbAntiRandomForPairWise = null;
	/**
	 * This is the default constructor
	 */
	public JDAlgorithms() {
		super();
		initialize();
		ButtonGroup grupo=new ButtonGroup();
		grupo.add(this.jrbAllCombinations);
		grupo.add(this.jrbAntiRandom);
		grupo.add(this.jrbPairWiseAC);
		grupo.add(this.jrbBaseChoice);
		grupo.add(this.jrbEachChoice);
		grupo.add(this.jrbPairWise2);
		grupo.add(this.jrbPairWiseStepsFirstCombination);
		grupo.add(this.jrbAntiRandomForPairWise);
		this.algorithm=new AllCombinationsElegant();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Algorithms for test case generation");
		this.setSize(303, 346);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.insets = new Insets(2, 5, 1, 2);
			gridBagConstraints9.gridy = 6;
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.ipadx = 2;
			gridBagConstraints9.ipady = 2;
			gridBagConstraints9.anchor = GridBagConstraints.WEST;
			gridBagConstraints9.gridx = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.insets = new Insets(2, 5, 1, 2);
			gridBagConstraints8.gridy = 7;
			gridBagConstraints8.ipadx = 2;
			gridBagConstraints8.fill = GridBagConstraints.BOTH;
			gridBagConstraints8.ipady = 2;
			gridBagConstraints8.anchor = GridBagConstraints.WEST;
			gridBagConstraints8.gridx = 0;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.insets = new Insets(5, 0, 2, 0);
			gridBagConstraints7.gridy = 9;
			gridBagConstraints7.ipadx = 0;
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridx = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(2, 5, 1, 2);
			gridBagConstraints6.gridy = 8;
			gridBagConstraints6.ipadx = 2;
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.ipady = 2;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.gridx = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(2, 5, 1, 2);
			gridBagConstraints5.gridy = 5;
			gridBagConstraints5.ipadx = 2;
			gridBagConstraints5.ipady = 2;
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(2, 5, 1, 2);
			gridBagConstraints4.gridy = 4;
			gridBagConstraints4.ipadx = 2;
			gridBagConstraints4.ipady = 2;
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridx = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(2, 5, 1, 2);
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.ipadx = 2;
			gridBagConstraints3.ipady = 2;
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(2, 5, 1, 2);
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.ipadx = 2;
			gridBagConstraints2.ipady = 2;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(2, 5, 1, 2);
			gridBagConstraints1.gridy = 2;
			gridBagConstraints1.ipadx = 2;
			gridBagConstraints1.ipady = 2;
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(2, 6, 10, 1);
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 1;
			gridBagConstraints.ipady = 1;
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridx = 0;
			jLabel = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jLabel.setText("Select the algorithm");
			jContentPane.add(jLabel, gridBagConstraints);
			jContentPane.add(getJrbEachChoice(), gridBagConstraints1);
			jContentPane.add(getJrbAllCombinations(), gridBagConstraints2);
			jContentPane.add(getJrbAntiRandom(), gridBagConstraints3);
			jContentPane.add(getJrbPairWiseAC(), gridBagConstraints4);
			jContentPane.add(getJrbPairWise2(), gridBagConstraints5);
			jContentPane.add(getJrbPariWiseStepsFirstCombination(), gridBagConstraints6);
			jContentPane.add(getJbOk(), gridBagConstraints7);
			jContentPane.add(getJrbAntiRandomForPairWise(), gridBagConstraints8);
			jContentPane.add(getJrbBaseChoice(), gridBagConstraints9);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */    
	private JRadioButton getJrbEachChoice() {
		if (jrbEachChoice == null) {
			jrbEachChoice = new JRadioButton();
			jrbEachChoice.setText("Each choice");
		}
		return jrbEachChoice;
	}
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */    
	private JRadioButton getJrbBaseChoice() {
		if (jrbBaseChoice == null) {
			jrbBaseChoice = new JRadioButton();
			jrbBaseChoice.setText("Base choice");
			jrbBaseChoice.setMnemonic(KeyEvent.VK_UNDEFINED);
			jrbBaseChoice.setVisible(true);
		}
		return jrbBaseChoice;
	}
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */    
	private JRadioButton getJrbAllCombinations() {
		if (jrbAllCombinations == null) {
			jrbAllCombinations = new JRadioButton();
			jrbAllCombinations.setText("All combinations");
			jrbAllCombinations.setSelected(true);
		}
		return jrbAllCombinations;
	}
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */    
	private JRadioButton getJrbAntiRandom() {
		if (jrbAntiRandom == null) {
			jrbAntiRandom = new JRadioButton();
			jrbAntiRandom.setText("Anti-random");
		}
		return jrbAntiRandom;
	}
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */    
	private JRadioButton getJrbPairWiseAC() {
		if (jrbPairWiseAC == null) {
			jrbPairWiseAC = new JRadioButton();
			jrbPairWiseAC.setText("Pair-wise AC");
		}
		return jrbPairWiseAC;
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
					selectAlgorithm();
				}
			});
		}
		return jbOk;
	}
	/**
     * 
     */
    protected void selectAlgorithm() {
        if (this.jrbAllCombinations.isSelected()) {
            this.algorithm=new AllCombinationsElegant();
        } else if (this.jrbEachChoice.isSelected()) {
            this.algorithm=new EachChoice();
        } else if (this.jrbAntiRandom.isSelected()) {
            this.algorithm=new AntiRandom();
        } else if (this.jrbPairWiseAC.isSelected()) {
            this.algorithm=new PairWiseAC();
        } else if (this.jrbPairWise2.isSelected()) {
            this.algorithm=new PairWiseSteps();
        } else if (this.jrbPairWiseStepsFirstCombination.isSelected()) {
            this.algorithm=new PairWiseStepsFirstCombination();
        } else if (this.jrbAntiRandomForPairWise.isSelected()) {
            this.algorithm=new AntiRandomPairWise();
        } 
        setVisible(false);
    }
    /**
     * @return
     */
    public Algorithm getAlgorithm() {
        return this.algorithm;
    }
	/**
	 * This method initializes jrbPairWise2	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbPairWise2() {
		if (jrbPairWise2 == null) {
			jrbPairWise2 = new JRadioButton();
			jrbPairWise2.setText("Pair-wise AC step by step");
		}
		return jrbPairWise2;
	}
	/**
	 * This method initializes jrbPariWiseStepsFirstCombination	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbPariWiseStepsFirstCombination() {
		if (jrbPairWiseStepsFirstCombination == null) {
			jrbPairWiseStepsFirstCombination = new JRadioButton();
			jrbPairWiseStepsFirstCombination.setText("Pair-wise first combination");
		}
		return jrbPairWiseStepsFirstCombination;
	}
	/**
	 * This method initializes jrbAntiRandomForPairWise	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJrbAntiRandomForPairWise() {
		if (jrbAntiRandomForPairWise == null) {
			jrbAntiRandomForPairWise = new JRadioButton();
			jrbAntiRandomForPairWise.setText("Anti-random for pair-wise");
		}
		return jrbAntiRandomForPairWise;
	}
       }  //  @jve:decl-index=0:visual-constraint="10,10"

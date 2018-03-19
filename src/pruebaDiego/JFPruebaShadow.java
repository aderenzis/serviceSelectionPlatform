package pruebaDiego;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class JFPruebaShadow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private TextArea textArea;
	private TextArea textArea_1;
	private List list;
	private Button button;
	private Label label;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	
	/**
	 * This is the default constructor
	 */
	public JFPruebaShadow() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(755, 472);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.jContentPane.setVisible(true);
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
			
			textArea = new TextArea();
			textArea.setBounds(327, 10, 380, 160);
			jContentPane.add(textArea);
			
			textArea_1 = new TextArea();
			textArea_1.setBounds(327, 212, 380, 160);
			jContentPane.add(textArea_1);
			
			list = new List();
			list.setBounds(10, 57, 125, 246);
			jContentPane.add(list);
			
			button = new Button("agregar");
			button.setBounds(386, 402, 70, 22);
			jContentPane.add(button);
			
			label = new Label("Metodos Conflictivos:");
			label.setBounds(10, 29, 125, 22);
			jContentPane.add(label);
			
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setBounds(141, 0, 7, 434);
			jContentPane.add(separator);
			
			label_1 = new Label("Ingrese las condiciones del test:");
			label_1.setBounds(154, 29, 167, 56);
			jContentPane.add(label_1);
			
			label_2 = new Label("Ingrese los datos de las causas");
			label_2.setBounds(154, 265, 167, 22);
			jContentPane.add(label_2);
			
			label_3 = new Label(" o efectos producidos:");
			label_3.setBounds(154, 281, 125, 22);
			jContentPane.add(label_3);
			
		}
		return jContentPane;
	}
	
	public static void main(String[] args)
	{
		JFPruebaShadow ps=new JFPruebaShadow();
		ps.setVisible(true);
		
		
	}
}

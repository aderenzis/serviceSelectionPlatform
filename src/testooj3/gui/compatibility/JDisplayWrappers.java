package testooj3.gui.compatibility;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import testooj3.compatibility.InterfacesCompatibilityChecker;
import testooj3.domain.Configuration;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


import javax.swing.JEditorPane;
import javax.swing.JScrollPane;



import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;


import java.io.File;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;


public class JDisplayWrappers  extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContenedor = null;
	private JScrollPane jScrollPane = null;
	private JScrollPane jScrollCodigo = null;
	private JList jList = null;
	private JEditorPane jEditorPane = null;
	
	private DefaultListModel listaWrappersModel = null;   //  @jve:decl-index=0:
	public InterfacesCompatibilityChecker ich;  //  @jve:decl-index=0:
	private String outputPath  = "";  //  @jve:decl-index=0:
	private String pathFile = "";  //  @jve:decl-index=0:
	private JPanel panel;
	
	
	
	public JDisplayWrappers(InterfacesCompatibilityChecker pin_ich) {
		super();		
		initialize(pin_ich);
	}



	/**
	 * This method initializes this
	 * 
	 */
	private void initialize(InterfacesCompatibilityChecker pin_ich) {
		this.ich=pin_ich;
		this.setSize(1024,768);
		this.setTitle("Wrapper Viewer");
		this.setContentPane(getJContenedor());
			
	}

	/**
	 * This method initializes jContenedor	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContenedor() {
		if (jContenedor == null) {
			/*gridBagConstraints3.anchor = GridBagConstraints.NORTHEAST;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridheight = 2;*/
			jContenedor = new JPanel();
						jContenedor.setLayout(new BoxLayout(jContenedor, BoxLayout.X_AXIS));
						//			jContenedor.add(getJScrollPane(), null);
									jContenedor.add(getJScrollPane());
						jContenedor.add(getPanel());
			//			jContenedor.add(getJScrolCodigo(), null);
						jContenedor.add(getJScrolCodigo());
		}	
		return jContenedor;
	}

	
	private JScrollPane getJScrolCodigo(){
		if (jScrollCodigo==null)
		{
			jScrollCodigo = new JScrollPane(getJEditorPane());
//			jScrollPane.setBounds(10, 10, 200, 600);
		}
		return jScrollCodigo;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane(getJList());
//			jScrollPane.setBounds(10, 10, 200, 600);
			jScrollPane.setViewportView(getJList());
			jScrollPane.setMinimumSize(new Dimension(1,1));
			jScrollPane.setMaximumSize(new Dimension(1400,1400));
//			jScrollPane.setPreferredSize(new Dimension(1,1));
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
//			this.pathFile ="C:/Tesis/paper/results/wrappers/0/banco2/Account.java";			
//			this.outputPath="C:/Tesis/paper/results/wrappers/";

//			Configuration conf = Configuration.getInstance();
//			String pathss = Configuration.getInstance().getMujavaRoot();
//			pathss = Configuration.getInstance().getWorkingPath();
			this.pathFile =Configuration.getInstance().getWorkingPath()+"wrappers/";//+this.ich.getOriginal().getName();//"7/ejemplosBancos/Account.java";			
			
			this.outputPath=Configuration.getInstance().getWorkingPath()+"wrappers/";//+this.ich.getOriginal().getName();
			File dir = new File(this.outputPath); 
			String [] directorio = dir.list();
			listaWrappersModel = new DefaultListModel();
			jList = new JList(listaWrappersModel);
			jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ListSelectionModel listSelectionModel = jList.getSelectionModel();
			
			for(int j=0; j < directorio.length;j++){ 
//				listaWrappersModel.add(j,"/"+directorio[j]+"/"+this.ich.NomPackOriginal()+"/Account.java");   
//				System.out.println("directorio["+j+"]: "+"/"+directorio[j]+"/banco2/Account.java");
//				listaWrappersModel.add(j,"/"+directorio[j]+"/"+this.ich.NomPackOriginal()+"/"+ this.ich.getOriginal().getName() +".java");
				listaWrappersModel.add(j,"/"+directorio[j]+"/"+ this.ich.getOriginal().getName().replace('.','/') +".java");
				System.out.println("directorio["+j+"]: "+"/"+directorio[j]+"/"+this.ich.NomPackOriginal()+"/"+ this.ich.getOriginal().getName() +".java");
			}
			

			jList.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mousePressed(java.awt.event.MouseEvent e) {    
						try {
							int posicion = jList.locationToIndex(e.getPoint());
							String cadena = (String)listaWrappersModel.getElementAt(posicion);
							cadena = outputPath + cadena; 
							showCodeWrapper(posicion,cadena);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			});
			listSelectionModel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {

					ListSelectionModel lsm = (ListSelectionModel)e.getSource();
					int posicion; 
		            int minIndex = lsm.getMinSelectionIndex();
	                int maxIndex = lsm.getMaxSelectionIndex();
	                for (int i = minIndex; i <= maxIndex; i++) {
	                    if (lsm.isSelectedIndex(i)) {
	                    		posicion = i;
	        					String cadena = (String)listaWrappersModel.getElementAt(posicion);
	        					cadena = outputPath + cadena; 
	        					System.out.println("cadena: "+cadena);
	        					try{
	        						showCodeWrapper(posicion,cadena);}
	        					catch (IOException f){
	        						f.printStackTrace();
	        					}
	                    }
	                }

			}
				});

	}
		return jList;
	}
	
	protected void showCodeWrapper(int posicion,String pathFile) throws IOException{
		FileReader fr = new FileReader(pathFile);
		BufferedReader bf = new BufferedReader(fr);
		String sCadena;
		StringBuffer wrapperTxt = new StringBuffer("");
		while ((sCadena = bf.readLine())!=null) {
			wrapperTxt.append(sCadena+"\n");
			sCadena="";
			}
		 
		jEditorPane.setText(wrapperTxt.toString());
		
	}
	/**
	 * This method initializes jEditorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJEditorPane() {
		if (jEditorPane == null) {
			jEditorPane = new JEditorPane();
			jEditorPane.setContentType("text/plain");
			
			jEditorPane.setText("Codigo fuente");			
		}
		return jEditorPane;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			
		}
		return panel;
	}
}  //  @jve:decl-index=0:visual-constraint="10,-4"

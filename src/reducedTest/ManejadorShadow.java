package reducedTest;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import testooj3.auxiliares.Auxi;
import testooj3.compatibility.InterfacesCompatibilityChecker;
import testooj3.domain.Configuration;
import testooj3.domain.PrimitiveValue;
import testooj3.domain.Interface;
import testooj3.domain.Operation;
import testooj3.domain.TParameter;
import testooj3.domain.TestValue;

public class ManejadorShadow 
{
	private InterfacesCompatibilityChecker ich;
	
	private Shadow shadow;
	private ArrayList<TableModelParameters> vectorTableModelConflicting;
	private ArrayList<DoMethod> mDoMethods;
	private Vector conflictMethods;
	private JDConflictingMethods parentWin;
	
	
	public ManejadorShadow(InterfacesCompatibilityChecker ich, JDConflictingMethods win)
	{
		mDoMethods=new ArrayList<DoMethod>();
		conflictMethods=new Vector();
		parentWin=win;
		this.ich=ich;
		getConflictingMethods();//ya se sabe quienes son los conflictivos
		setInitialShadow();
	}
	
	private void setInitialShadow()
	{
//		System.out.println(Auxi.recorta(this.ich.getOriginal().getName()));
		this.shadow=new Shadow(this.ich.getOriginal().getName());
		this.shadow.setCampos(new ArrayList<Campo>());
		this.shadow.setMethods(this.mDoMethods);
		this.cargarDoMethods();
		// agregamos el DefaultModel de TableFields
		this.parentWin.getTableFields().setModel(new TableModelFields(this.shadow));
		this.parentWin.getTableFields().setDefaultRenderer(Object.class, new RenderTableFields(this.shadow));
		this.parentWin.getTableFields().setDefaultEditor(Object.class, new EditorTableFields(this.shadow));
	}
	
	//inicializa cada los DoMethods
	private void cargarDoMethods()
	{
		DoMethod dm=null;
		Operation tm=null;
		int i=0;
		this.vectorTableModelConflicting=new ArrayList<TableModelParameters>();
		while(i<this.conflictMethods.size())//a cada DoMethod le setea los parametros correspondientes
		{
			tm=(Operation)this.conflictMethods.get(i);
			dm=new DoMethod(tm.getNombre(),tm.getTipo());
			Vector<EfectosCaso> vec=new Vector<EfectosCaso>();
			dm.setEfectosCaso(vec);
			dm.setEfectosSinCausa(new Vector<Efecto>());
			this.emptyParameters(tm.getParametros());
			dm.setParametros(tm.getParametros());
			this.vectorTableModelConflicting.add(new TableModelParameters(dm));//argrago el table model 
			this.mDoMethods.add(dm);
			i++;
		}
	}
	
	private void emptyParameters(Vector<TParameter> parametros) 
	{
		Iterator ite=parametros.iterator();
		while(ite.hasNext())
		{
			TParameter tp=(TParameter)ite.next();
			tp.resetTestValues();
		}
	}

	public void addField(Object[] campo)
	{
		Campo c=new Campo();
		c.setNombre((String)campo[0]);//name
		c.setTipo((String)campo[1]);//type
		this.shadow.getCampos().add(c);
	}
	
	
	
	public void selectionConflictingMethods(int methodSelected)
	{
		//si tiene causa se agrega lo necesario para agregar los parametros...
		DoMethod dm=this.shadow.getMethods().get(methodSelected);
		if(dm.tieneCausa())
		{
			this.parentWin.getTableParameters().setModel(this.vectorTableModelConflicting.get(methodSelected));
			//Modificar aca no sacar del vector... simplemente buscar y setear...
			this.parentWin.getTableEfects().setModel(new TableModelEffects(null,-1,-1));
			this.parentWin.getTableParameters().setDefaultRenderer(Object.class, new RenderTableParameters(this.mDoMethods.get(methodSelected)));
			this.parentWin.getTableParameters().setDefaultEditor(Object.class, new EditorTablePatameters(this.mDoMethods.get(methodSelected)));
		}
		else
		{
			this.parentWin.getTableParameters().setModel(new TableModelParameters(dm));
//			this.parentWin.getTableEfects().setModel(new TableModelEffects(null,-1,-1));
			loadEfects(-1);
		}
	}
	
	public void deleteParameters()
	{
		if(this.parentWin.getTableParameters().isEditing())
			this.parentWin.getTableParameters().getCellEditor().stopCellEditing();
		if(this.parentWin.getTableEfects().isEditing())
			this.parentWin.getTableEfects().getCellEditor().stopCellEditing();
		
		int pos=this.parentWin.getTableParameters().getSelectedRow();
		DoMethod dm=this.shadow.getMethods().get(this.parentWin.getTableConflictingMethods().getSelectedRow());
		//se elimina el llamado de parametros y los Efectos casos asociados a ellos...
		dm.deleteParameters(pos);
		this.parentWin.getTableParameters().updateUI();
		this.parentWin.getTableEfects().updateUI();
	}
	
	public void addValuesParameters(int pos)
	{
		DoMethod dm=this.shadow.getMethods().get(pos);
		if(dm.tieneCausa())
		{
			TableModelParameters dtm=(TableModelParameters)this.parentWin.getTableParameters().getModel();
			dtm.addRow();
			this.parentWin.getTableParameters().updateUI();
		}
		else
		{
			this.parentWin.getTableParameters().setModel(new TableModelParameters(dm));
			this.parentWin.getTableParameters().updateUI();
		}
			
	}
	
	public void addEfects()
	{
		TableModelEffects tme=(TableModelEffects)this.parentWin.getTableEfects().getModel();
		tme.addRow();
		this.parentWin.getTableEfects().updateUI();
	}
	
	public void deleteField()
	{
		int selectedRow=this.parentWin.getTableFields().getSelectedRow();
		if(selectedRow!=-1)
			if(!this.verificarCampoEnUso(this.shadow.getCampos().get(selectedRow).getNombre()))
			{
				if(this.parentWin.getTableFields().isEditing())
					this.parentWin.getTableFields().getCellEditor().stopCellEditing();
				
				this.shadow.getCampos().remove(selectedRow);
				this.parentWin.getTableFields().updateUI();
			}
			else
				System.out.println("No se puede eliminar el campo se encuentra en uso.");
		else
			System.out.println("No se ha seleccionado ningun Campo a eliminar.");
	}
	
	private boolean verificarCampoEnUso(String field) 
	{
		Iterator ite=this.shadow.getMethods().iterator();
		while(ite.hasNext())
		{
			DoMethod dm=(DoMethod)ite.next();
			if(dm.tieneCausa())
			{
				Iterator ite2=dm.getEfectosCaso().iterator();
				while(ite2.hasNext())
				{
					EfectosCaso ec=(EfectosCaso)ite2.next();
					Iterator ite3=ec.getEfectos().iterator();
					while(ite3.hasNext())
					{
						Efecto e=(Efecto)ite3.next();
						if(e.getField().equals(field))
							return true;
					}
				}
			}
		}
		return false;
	}

	private void getConflictingMethods()
	{
		try {
			Iterator ite=this.ich.getCompatibilities().entrySet().iterator();
			Interface a=this.ich.getOriginal();
			Vector methodsA=a.getMethods();
			while(ite.hasNext())
			{
				Map.Entry e = (Map.Entry)ite.next();
				Vector compat= (Vector) e.getValue();
				if(compat.size()>=2)//puede ser conflictivo
				{
					Vector compatible1=(Vector)compat.get(0);
					Vector compatible2=(Vector)compat.get(1);
					String clasificacion1=(String)compatible1.get(1);//n_exact
					String clasificacion2=(String)compatible2.get(1);
					if(clasificacion1.equals(clasificacion2))
					{
						Operation m= searchMethod(methodsA, e.getKey().toString());
						Object[] newRow={m.getNombre()};
						DefaultTableModel dtm= (DefaultTableModel)this.parentWin.getTableConflictingMethods().getModel();
						dtm.addRow(newRow);
						conflictMethods.add(m);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error ");
		}
	}
	
	private Operation searchMethod(Vector methodsA, String key)
	{
		for(int i=0; i<methodsA.size(); i++)
		{
			Operation tm=(Operation)methodsA.get(i);
			if(tm.getWholeSignature().equals(key))
				return tm;
		}
		return null;
	}
	
	public void loadEfects(int parameterEfectSelected)
	{
		int posDoMethod=this.parentWin.getTableConflictingMethods().getSelectedRow();//pos del conflictivo
//		DoMethod dm=(DoMethod)this.shadow.getMethods().get(posDoMethod);
		TableModelEffects tme=new TableModelEffects(this.shadow,posDoMethod,parameterEfectSelected);
		
		this.parentWin.getTableEfects().setModel(tme);
		this.parentWin.getTableEfects().setDefaultRenderer(Object.class, new RenderTableEffects(shadow,posDoMethod,parameterEfectSelected));
//		this.parentWin.getTableEfects().setDefaultRenderer(String.class, new RenderTableEffects(shadow,posDoMethod,parameterEfectSelected));
//		this.parentWin.getTableEfects().setDefaultRenderer(JComboBox.class, new RenderTableEffects(shadow,posDoMethod,parameterEfectSelected));
		this.parentWin.getTableEfects().setDefaultEditor(Object.class, new EditorTableEffects(this.shadow,posDoMethod));
	}

	public void deleteEfect() 
	{
		if(this.parentWin.getTableEfects().isEditing())
			this.parentWin.getTableEfects().getCellEditor().stopCellEditing();
		int pos=this.parentWin.getTableEfects().getSelectedRow();
		DoMethod dm=this.shadow.getMethods().get(this.parentWin.getTableConflictingMethods().getSelectedRow());
		dm.deleteEfect(pos,this.parentWin.getTableParameters().getSelectedRow());
		this.parentWin.getTableEfects().updateUI();
	}

	
	public void addField() 
	{
		this.shadow.getCampos().add(new Campo("",""));
		this.parentWin.getTableFields().updateUI();
	}
	
	public String generarShadow()
	{
		String shadowClass="";
		shadowClass=this.shadow.generateShadow();
//		String path="D:\\Dropbox\\workspace\\TestOojAlan\\src\\temp\\ShadowsFiles\\";
		String path2=Configuration.getInstance().getWorkingPath();
		//reemplaza la original debe ir a para a la carpeta /wrappers/original
		String dirOriginal = path2+File.separator+"wrappers"+File.separator+"original"+File.separator+this.shadow.getDir()+File.separator;
		File dir=new File(dirOriginal);
		dir.mkdirs();
		File fileSha=new File(dirOriginal+this.shadow.getName()+".java");
		try {
			FileOutputStream fo = new FileOutputStream(fileSha);
			try
			{
				fo.write(shadowClass.getBytes());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shadowClass;
	}
	
	public Vector getConflictMethods()
	{
		return this.conflictMethods;
	}

	public void setReturnTypeLabel(JLabel lblTypeReturn,int pos) {
		DoMethod dm=this.shadow.getMethods().get(pos);
		lblTypeReturn.setText("Type Return: "+dm.getTipo());
	}

	public boolean dataChecker() 
	{
		Iterator ite=this.shadow.getMethods().iterator();
		boolean encontrado=true;
		while(ite.hasNext() && encontrado)
		{
			DoMethod dm=(DoMethod)ite.next();
			if(dm.tieneCausa())
			{
				Iterator ite2=dm.getEfectosCaso().iterator();
				if(!dm.getEfectosCaso().isEmpty())
				{
					while(ite2.hasNext() && encontrado)
					{
						EfectosCaso ec=(EfectosCaso)ite2.next();
						encontrado=fieldsChecker(ec.getEfectos());
					}
				}
			}
			else
			{
				if(!dm.getEfectosSinCausa().isEmpty())
					encontrado=fieldsChecker(dm.getEfectosSinCausa());
			}
		}
		return encontrado;
	}
	
	private boolean fieldsChecker(Vector<Efecto> vec)//verificamos todo lo relacionado con el uso de un fields
	{
		boolean encontrado=true;
		Iterator ite=vec.iterator();
		while(ite.hasNext() && encontrado)
		{
			Efecto effect=(Efecto)ite.next();
			encontrado=checkField(effect);
		}
		return encontrado;
	}
	
	private boolean checkField(Efecto effect)
	{
		Iterator ite2=this.shadow.getCampos().iterator();
		if(effect.getField().equals(""))
		{
			return true;
		}
		while(ite2.hasNext())
		{
			Campo c=(Campo)ite2.next();
			if(c.getNombre().equals(effect.getField()))
				return true;
		}
		
		return false;
	}
}

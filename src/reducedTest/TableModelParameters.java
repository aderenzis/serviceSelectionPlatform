package reducedTest;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import testooj3.domain.PrimitiveValue;
import testooj3.domain.TParameter;
import testooj3.domain.TestValue;


public class TableModelParameters implements TableModel{
	
	private DoMethod doMethod;
	
	public TableModelParameters(DoMethod doMethod){
		this.doMethod= doMethod;
	}

	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public Class<?> getColumnClass(int arg0) {
		
		if(ControlTypes.isBooleanType(this.doMethod.getParametros().get(arg0).getTipo()))
			return JCheckBox.class;
		return String.class;
	}

	public int getColumnCount() 
	{
		return doMethod.getParametros().size();
	}

	public String getColumnName(int arg0) {
		
		return doMethod.getParametros().get(arg0).getMNombre();
	}

	public int getRowCount() {
		
		try {
			if(this.doMethod.tieneCausa())
				return doMethod.getParametros().get(0).getTestValues().length;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Object getValueAt(int arg0, int arg1) {
		try {
			if(arg0<doMethod.getParametros().get(arg1).getTestValues().length)
				return doMethod.getParametros().get(arg1).getTestValue(arg0).toString();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

	public boolean isCellEditable(int arg0, int arg1) {
		return true;
	}

	public void removeTableModelListener(TableModelListener arg0) {
		
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		doMethod.getParametros().get(arg2).setTestValue(arg1, new PrimitiveValue(arg0));
	}
	
	public void addRow()
	{
		Iterator ite=this.doMethod.getParametros().iterator();
		while(ite.hasNext())
		{
			TParameter param=(TParameter)ite.next();
			Object valueDefault=ControlTypes.viewDefaultValue(param);
			if(valueDefault instanceof String)
				param.addTestValue(new PrimitiveValue((String)valueDefault));
			//ver en caso de que sea booleano para agregar tilde...
		}
		Vector<Efecto> vecEfects=new Vector<Efecto>();
		EfectosCaso ec=new EfectosCaso(vecEfects);
		this.doMethod.getEfectosCaso().add(ec);
	}

}

package reducedTest;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import testooj3.domain.PrimitiveValue;
import testooj3.domain.TParameter;

public class TableModelEffects implements TableModel{

	private DoMethod doMethod;
	private int posParameter;
	private Shadow shadow;
	
	public TableModelEffects(Shadow shadow,int posDoMethod, int posParameter){
		this.shadow=shadow;
		if(posDoMethod!=-1)
			doMethod=(DoMethod)this.shadow.getMethods().get(posDoMethod);
		this.posParameter=posParameter;
	}
	
	public void addTableModelListener(TableModelListener arg0) {
		
	}

	public Class<?> getColumnClass(int arg0) {
		if(arg0==0)
			return JComboBox.class;
		if(arg0==1)
			return String.class;
		if(arg0==2)
//			return Boolean.class;
			return JCheckBox.class;
		return null;
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int arg0) {
		if(arg0==0)
			return "Field";
		if(arg0==1)
			return "Value";
		if(arg0==2)
			return "Return";
		return "";
	}

	public int getRowCount() {
		if(this.doMethod!=null)
		{
			if(this.doMethod.tieneCausa())
				if(this.doMethod.getEfectosCaso().size()>posParameter)
					return this.doMethod.getEfectosCaso().get(posParameter).getEfectos().size();
				else return 0;
			else
				return this.doMethod.getEfectosSinCausa().size();
		}
		else return 0;
	}

	public Object getValueAt(int arg0, int arg1) {
		if(this.doMethod!=null)
		{
			if(this.doMethod.tieneCausa())
			{
				if(arg1==0)
					return this.doMethod.getEfectosCaso().get(posParameter).getEfectos().get(arg0).getField();
				if(arg1==1)
					return this.doMethod.getEfectosCaso().get(posParameter).getEfectos().get(arg0).getValue();
				if(arg1==2)
					return this.doMethod.getEfectosCaso().get(posParameter).getEfectos().get(arg0).isReturn();
			}
			else
			{
				if(arg1==0)
					return this.doMethod.getEfectosSinCausa().get(arg0).getField();
				if(arg1==1)
					return this.doMethod.getEfectosSinCausa().get(arg0).getValue();
				if(arg1==2)
					return this.doMethod.getEfectosSinCausa().get(arg0).isReturn();
			}
			return "";
		}
		else return "";
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return true;
	}

	public void removeTableModelListener(TableModelListener arg0) {
		
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		if(this.doMethod!=null)
		{
			if(this.doMethod.tieneCausa())
			{
				
				if(arg2==0)//no me preocupo por si el efecto esta instaciado
				{
					String value=(String) arg0;
					this.doMethod.getEfectosCaso().get(posParameter).getEfectos().get(arg1).setField(value);
					this.doMethod.getEfectosCaso().get(posParameter).getEfectos().get(arg1).setType(shadow.typeOf(value));
				}
				if(arg2==1)
				{
					String value=(String) arg0;
					this.doMethod.getEfectosCaso().get(posParameter).getEfectos().get(arg1).setValue(value);
				}
				if(arg2==2)
				{
					Boolean value=(Boolean) arg0;
					this.doMethod.getEfectosCaso().get(posParameter).getEfectos().get(arg1).setReturn(value);
				}
			}
			else
			{
				
				if(arg2==0)
				{
					this.doMethod.getEfectosSinCausa().get(arg1).setField((String) arg0);
					this.doMethod.getEfectosSinCausa().get(arg1).setType(shadow.typeOf((String) arg0));
				}
				if(arg2==1)
					this.doMethod.getEfectosSinCausa().get(arg1).setValue((String) arg0);
				if(arg2==2)
					this.doMethod.getEfectosSinCausa().get(arg1).setReturn((Boolean) arg0);
			}
		}
	}

	public void addRow()
	{
		Efecto ef=new Efecto("","","");
		if(this.doMethod!=null)
		{
			if(doMethod.tieneCausa())
			{
				this.doMethod.getEfectosCaso().get(this.posParameter).getEfectos().add(ef);
			}
			else
			{
				this.doMethod.getEfectosSinCausa().add(ef);
			}
		}
	}
	
}

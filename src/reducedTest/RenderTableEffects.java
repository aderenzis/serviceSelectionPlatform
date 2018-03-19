package reducedTest;

import java.awt.Color;
import java.awt.Component;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import org.exolab.javasource.JField;

public class RenderTableEffects implements TableCellRenderer{

	private Shadow shadow;
	private DoMethod doMethod;
	private int posParameter;
	
	public RenderTableEffects(Shadow shadow,int posDoMethod, int posParameter)
	{
		this.shadow=shadow;
		this.doMethod=(DoMethod)this.shadow.getMethods().get(posDoMethod);
		this.posParameter=posParameter;
	}
	
	public Component getTableCellRendererComponent(JTable arg0, Object arg1,
			boolean arg2, boolean arg3, int arg4, int arg5) 
	{
		
		if (arg5==0)
		{
			String value=(String)arg1;
			JComboBox typeList=new JComboBox();
			DefaultComboBoxModel<String> cbm=new DefaultComboBoxModel<String>();
			if(value.equals(""))
				cbm.addElement("none");
			else
				cbm.addElement(value);
			typeList.setModel(cbm);
		    return typeList;
		}
		if(arg5==1)
		{
			String value=(String)arg1;
			JTextField dato=new JTextField(value);
//			String field;
			Efecto efecto=null;
			if(this.doMethod.tieneCausa())
			{
				efecto=this.doMethod.getEfectosCaso().get(posParameter).getEfectos().get(arg4);
//				field=efecto.getField();
			}
			else
			{
				efecto=this.doMethod.getEfectosSinCausa().get(arg4);
//				field=efecto.getField();
			}
//			String type=this.shadow.typeOf(field);//ahora el field tiene el typo dentro... no hace falta esta sentencia
			//si el campo no es un retorno se checkea...sino se chea tipo de retorno
			if(!efecto.isReturn())//si es un efecto que no se retorna
			{
				if(ControlTypes.checkValue(efecto.getType(),value))
					dato.setBackground(Color.GREEN);
				else
					dato.setBackground(Color.RED);
			}
			else //si es un efecto que se retorna
			{
				if(efecto.getField().equals("")||efecto.getField().equals("none"))
				{
					if(ControlTypes.checkValue(doMethod.getTipo(),value))
						dato.setBackground(Color.GREEN);
					else
						dato.setBackground(Color.RED);
				}
				else
				{
					if(ControlTypes.conpatibleTypes(doMethod.getTipo(), efecto.getType()))//CONTROLAR QUE SEAN TIPOS EQUIVALENTES
						dato.setBackground(Color.GREEN);
					else
						dato.setBackground(Color.RED);
				}
			}
			return dato;
		}
		if(arg5==2)
		{
			Boolean value=(Boolean)arg1;
			JCheckBox dato=new JCheckBox();
			if(this.doMethod.tieneRetorno())//temporalmente desabilitado por pruebas
				dato.setSelected(value);
			else
				dato.setEnabled(false);
			return dato;
		}
		return null;
	}

}

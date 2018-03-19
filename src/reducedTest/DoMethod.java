package reducedTest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import testooj3.domain.TParameter;


public class DoMethod {
	//agregar efectos y causas aca...
	private Vector<TParameter> parametros;
	private Vector<EfectosCaso> efectosCaso;
	private String name;
	private String tipo;
	private Vector<Efecto> efectosSinCausa;
	
	public DoMethod(String nombre,String tipo)
	{
		this.name=nombre;
		this.parametros=new Vector<TParameter>();
		this.tipo=tipo;
	}

	public Vector<TParameter> getParametros() {
		return parametros;
	}

	public void setParametros(Vector<TParameter> parametros) {
		this.parametros = parametros;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<EfectosCaso> getEfectosCaso() {
		return efectosCaso;
	}

	public void setEfectosCaso(Vector<EfectosCaso> efectosCaso) {
		this.efectosCaso = efectosCaso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Vector<Efecto> getEfectosSinCausa() {
		return efectosSinCausa;
	}

	public void setEfectosSinCausa(Vector<Efecto> valores) {
		this.efectosSinCausa = valores;
	}

	public boolean tieneCausa()
	{
		return !this.parametros.isEmpty();
	}
	public boolean tieneRetorno()
	{
		return !this.tipo.equals("void");
	}
	
	public String generateDoMethod()
	{
		String cadena= "";
		cadena+="\tpublic "+this.tipo+" "+this.name+agregarParametros()+"\n";
		cadena+="\t{\n";
		if(tieneCausa())//no void - con parametros - retorno valor y efecto
		{
			int j=0;
			while(j<this.efectosCaso.size())
			{
				cadena+="\t\tif"+generarCausa(j)+"\n";
				cadena+="\t\t{\n";
				cadena+=generarEfectosCausa(j);
				cadena+="\t\t}\n";
				j++;
			}
			
			cadena+="\t\treturn "+"("+this.tipo+")"+ControlTypes.getInstanceValue(this.tipo, ControlTypes.printDefaultValue(this.tipo))+";\n";
		}
		else	//no void - sin parametros - retorno valor o efecto
		{
			cadena+=generarEfectosSinCausa();
		}
		cadena+="\t}\n\n";
		return cadena;
	}
	
	//tanto seteos como return
	public String generarEfectosCausa(int pos)
	{
		String cadena="";
		Vector vecEffects=this.efectosCaso.get(pos).getEfectos();
		int k=0;
		String returnSentence="";
		while(k<vecEffects.size())
		{
			Efecto efecto=(Efecto)vecEffects.get(k);
			if(efecto.isReturn())
				returnSentence="\t\t\treturn "+efecto.getSentence(this.tipo)+";\n";
			else
				cadena+="\t\t\t"+efecto.getSentence("")+";\n";
			k++;
		}
		cadena+=returnSentence;
		return cadena;
	}
	
	private String generarEfectosSinCausa()
	{
		String cadena="";
		int k=0;
		String returnSentence="";
		while(k<this.efectosSinCausa.size())
		{
			Efecto efecto=(Efecto)efectosSinCausa.get(k);
			if(efecto.isReturn())
				returnSentence="\t\treturn "+efecto.getSentence(this.tipo)+";\n";
			else
			{
				cadena+="\t\t"+efecto.getSentence("")+";\n";
			}
			k++;
		}
		cadena+=returnSentence;
		return cadena;
	}
	
//	if(nombre.equal("juancito") && cantidad==1)
	public String generarCausa(int j)
	{
		String cadena="(";
		int k=0;
		TParameter para;
		while(k<this.parametros.size())
		{
			para=this.parametros.get(k);
			cadena+=ControlTypes.sentenceCompare(para.getMNombre(),para.getTipo(),para.getTestValue(j).toString());
//			cadena+=para.getMNombre()+"=="+para.getTestValue(j);
			k++;
			if(k<this.parametros.size())
				cadena+=" && ";
		}
		cadena+=")";
		return cadena;
	}
	
	public String toString()
	{
		return this.generateDoMethod();
	}
	
//	public String toString()
//	{
//		String cadena= "";
////		int i=0;
//		cadena+="\tpublic "+this.tipo+" "+this.name+"(";
//		cadena=agregarParametros(cadena);
//		cadena+=")\n\t{\n";
//		
//		if(!this.tipo.equals("void"))//no void 
//		{
//			if(this.parametros.size()>0)//no void - con parametros - retorno valor y efecto
//			{
//				int j=0;
//				TParameter para;
//				while(j<this.efectosSinCausa.size())
//				{
//					cadena+="\t\tif(";
//					int k=0;
//					while(k<this.parametros.size())
//					{
//						para=this.parametros.get(k);
//						cadena+="arg"+(k+1)+"=="+para.getTestValue(j);
//						k++;
//						if(k<this.parametros.size())
//							cadena+=" && ";
//					}
//					cadena+=")\n";
//					cadena+="\t\t{\n";
//					
//					if(0<this.efectosSinCausa.size())
//					{
//						String retorno=(String) this.efectosSinCausa.get(j).toString();
//						cadena+="\t\t\treturn "+retorno+";\n";
//					}
//					cadena+="\t\t}\n";
//					j++;
//				}
//			}
//			else	//no void - sin parametros - retorno valor o efecto
//			{
//				String retorno=(String) this.efectosSinCausa.get(0).toString();
//				cadena+="\t\treturn "+retorno+";\n";
//			}
//			cadena+="\t}\n";
//		}
//		else	//void 
//		{
//			int j=0;
//			TParameter para;
//			while(j<this.efectosCaso.size())//la cantidad de IFs
//			{
//				int k=0;
//				if(this.parametros.size()>0)//void - con parametros - causa
//				{
//					cadena+="\t\tif(";
//					while(k<this.parametros.size())
//					{
//						para=this.parametros.get(k);
//						cadena+="arg"+(k+1)+"=="+para.getTestValue(j);
//						k++;
//						if(k<this.parametros.size())
//							cadena+=" && ";
//					}
//					cadena+=")\n";
//					cadena+="\t\t{\n";
//				}
//				k=0;
//				EfectosCaso ec=this.efectosCaso.get(j);
//				while(k<ec.getEfectos().size())
//				{
//					if(this.parametros.size()>0)//void - con parametros - efectos
//						cadena+="\t\t\t"+ec.getEfectos().get(k).getField()+" = "+ec.getEfectos().get(k).getValue()+";\n";
//					else
//						cadena+="\t\t"+ec.getEfectos().get(k).getField()+" = "+ec.getEfectos().get(k).getValue()+";\n";
//					k++;
//				}
//				if(this.parametros.size()>0)
//					cadena+="\t\t}\n";
//				j++;
//			}
//			cadena+="\t}\n";
//		}
////		cadena+="\t}\n";
//		return cadena;
//	}
	
	//(String nombre, Integer cantidad)
	private String agregarParametros()
	{
		String cadena="(";
		TParameter para;
		int i=0;
		while(i<this.parametros.size())
		{
			para=parametros.get(i);
			cadena+=para.getTipo()+" "+para.getMNombre() ;
			i++;
			if(i<this.parametros.size())
				cadena+=",";
		}
		cadena+=")";
		return cadena;
	}

	public void deleteParameters(int pos) 
	{
		Iterator ite=this.parametros.iterator();
		while(ite.hasNext())
		{
			TParameter p=(TParameter)ite.next();
			p.removeTestValue(pos);
		}
		this.efectosCaso.removeElementAt(pos);
		//tabla de efectos debera quedar vacia...
	}

	public void deleteEfect(int posEfect, int posParam) {
		
		if(this.tieneCausa())
		{
			this.efectosCaso.get(posParam).getEfectos().removeElementAt(posEfect);
		}
		else
		{
			this.efectosSinCausa.removeElementAt(posEfect);
		}
	}
	
	public void deleteReturn(int posParam)
	{
		if(this.tieneCausa())
		{
			Vector efectos=this.efectosCaso.get(posParam).getEfectos();
			for(int i=0; i<efectos.size();i++)
			{
				Efecto ef=(Efecto)efectos.get(i);
				ef.setReturn(false);
			}	
		}
		else
		{
			for(int i=0; i<efectosSinCausa.size();i++)
			{
				Efecto ef=(Efecto)efectosSinCausa.get(i);
				ef.setReturn(false);
			}	
		}
	}
	
//	public void setUniqueReturn(int posParam,int arg1)
//	{
//		if(this.tieneCausa())
//		{
//			Vector efectos=this.efectosCaso.get(posParam).getEfectos();
//			for(int i=0; i<efectos.size();i++)
//			{
//				Efecto ef=(Efecto)efectos.get(i);
//				if(i==arg1)
//					ef.setReturn(true);
//				else
//					ef.setReturn(false);
//			}	
//		}
//		else
//		{
//			for(int i=0; i<efectosSinCausa.size();i++)
//			{
//				Efecto ef=(Efecto)efectosSinCausa.get(i);
//				if(i==arg1)
//					ef.setReturn(true);
//				else
//					ef.setReturn(false);
//			}	
//		}
//	}
	
}

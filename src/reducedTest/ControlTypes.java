package reducedTest;

import java.util.Vector;

import testooj3.domain.TParameter;

public class ControlTypes {
	
	private static final String DEF_COMPLETE="java.lang.";
	
	private static String[] types={"String","int","Long","Integer","Byte","Short","Double","Float","Character","char","Boolean","boolean"};
	
	public static Object viewDefaultValue(TParameter parameter)
	{
		return viewDefaultValue(parameter.getTipo());
	}
	
	public static Object viewDefaultValue(String type)
	{
		Object initial="";
		if(isStringType(type)
				|| isCharacterType(type))
			initial="";
		else if(isNumericType(type))
			initial="0";
		else if(isDecimalType(type))
				initial="0.0";
		else if(isBooleanType(type))
				initial="true";
		return initial;
	}
	
	public static String printDefaultValue(String type){
		String initial="";
		if(isStringType(type))
			initial="\"\"";
		else if(isCharacterType(type))
			initial="' '";
		else if(isNumericType(type))
			initial="0";
		else if(isDecimalType(type))
				initial="0.0";
		else if(isBooleanType(type))
				initial="true";
		return initial;
	}
	
	private static boolean equalType(String type, String value)
	{
		return type.equalsIgnoreCase(value)||type.equalsIgnoreCase(DEF_COMPLETE+value);
	}
	
	private static boolean equalTypeCaseSensitive(String type, String value)
	{
		return type.equals(value)||type.equals(DEF_COMPLETE+value);
	}

	public static Vector getTypes()
	{
		Vector vec=new Vector();
		for(int i=0; i<types.length;i++)
			vec.add(types[i]);
		return vec;
	}
	
	public static boolean checkValue(String type,String value)
	{
		boolean correctType=false;
		if(isStringType(type))
			correctType=true;
		if(isCharacterType(type))
			correctType=ControlFields.isCharacter(value);
		else if(isNumericType(type))
			correctType=ControlFields.isInteger(value);
		else if(isDecimalType(type))
			correctType=ControlFields.isDecimal(value);
		else if(isBooleanType(type))
			correctType=ControlFields.isBoolean(value);
		return correctType;
	}
	
	public static boolean isNumericType(String type)
	{
		if(equalType(type, "int")
				||equalType(type, "integer")
				||equalType(type, "byte") 
				|| equalType(type, "long")
				|| equalType(type, "short"))
			return true;
		return false;
	}
	
	public static boolean isBooleanType(String type)
	{
		if(equalType(type, "boolean"))
			return true;
		return false;
	}
	
	public static boolean isCharacterType(String type)
	{
		if(equalType(type, "char")
				||equalType(type, "Character"))
			return true;
		return false;
	}
	
	public static boolean isDecimalType(String type)
	{
		if(equalType(type, "float")||equalType(type, "double"))
			return true;
		return false;
	}
	
	public static boolean isStringType(String type)
	{
		if(equalType(type, "String"))
			return true;
		return false;
	}
	
	public static String getInstanceValue(String type,String value)
	{
		if(type.equals("int")||type.equals("byte")||type.equals("long")||type.equals("short")
				||type.equals("double")|| type.equals("float")||type.equals("boolean"))
			return value;
		
		if(type.equals("char"))
			return "'"+value+"'";
		if(equalTypeCaseSensitive(type, "Character"))
			return "new Character('"+value+"')";
		
		if(equalTypeCaseSensitive(type, "Integer"))
			return "new Integer("+value+")";
		if(equalTypeCaseSensitive(type, "Long"))
			return "new Long("+value+")";
		if(equalTypeCaseSensitive(type, "Short"))
			return "new Short((short) "+value+")";
		if(equalTypeCaseSensitive(type, "Byte"))
			return "new Byte((byte) "+value+")";
		if(equalTypeCaseSensitive(type, "Double"))
			return "new Double("+value+")";
		if(equalTypeCaseSensitive(type, "Float"))
			return "new Float("+value+")";
		
		if(equalTypeCaseSensitive(type, "String"))
			return "\""+value+"\"";
		
		if(equalTypeCaseSensitive(type, "Boolean"))
			return "new Boolean("+value+")";
		return "";
	}
	
	public static String sentenceCompare(String campo,String type, String value)
	{
		if(type.equals("int")||type.equals("byte")||type.equals("long")||type.equals("short")
				||type.equals("double")|| type.equals("float")||type.equals("char")||type.equals("boolean"))
			return campo+"=="+value;
		
		if(equalTypeCaseSensitive(type, "Integer")||equalTypeCaseSensitive(type, "Long")
				||equalTypeCaseSensitive(type, "Short")||equalTypeCaseSensitive(type, "Byte")
				||equalTypeCaseSensitive(type, "Double")||equalTypeCaseSensitive(type, "Float")
				||equalTypeCaseSensitive(type, "String")||equalTypeCaseSensitive(type, "Boolean")
				||equalTypeCaseSensitive(type, "Character"))
			return campo+".equals("+getInstanceValue(type,value)+")";
		return "";
	}
	
	public static boolean conpatibleTypes(String type1, String type2)
	{
		if((equalTypeCaseSensitive(type1, "Integer")||equalTypeCaseSensitive(type1, "int"))&&(
				equalTypeCaseSensitive(type2, "Integer")||equalTypeCaseSensitive(type2, "int")))
			return true;

		if((equalTypeCaseSensitive(type1, "Long")||equalTypeCaseSensitive(type1, "long"))&&(
				equalTypeCaseSensitive(type2, "Long")||equalTypeCaseSensitive(type2, "long")))
			return true;
		
		if((equalTypeCaseSensitive(type1, "Short")||equalTypeCaseSensitive(type1, "short"))&&(
				equalTypeCaseSensitive(type2, "Short")||equalTypeCaseSensitive(type2, "short")))
			return true;
		
		if((equalTypeCaseSensitive(type1, "Byte")||equalTypeCaseSensitive(type1, "byte"))&&(
				equalTypeCaseSensitive(type2, "Byte")||equalTypeCaseSensitive(type2, "byte")))
			return true;
		
		if((equalTypeCaseSensitive(type1, "Double")||equalTypeCaseSensitive(type1, "double"))&&(
				equalTypeCaseSensitive(type2, "Double")||equalTypeCaseSensitive(type2, "double")))
			return true;
		
		if((equalTypeCaseSensitive(type1, "Float")||equalTypeCaseSensitive(type1, "float"))&&(
				equalTypeCaseSensitive(type2, "Float")||equalTypeCaseSensitive(type2, "float")))
			return true;
		
		if(equalTypeCaseSensitive(type1, "String")&&equalTypeCaseSensitive(type2, "String"))
			return true;
		
		if((equalTypeCaseSensitive(type1, "Boolean")||equalTypeCaseSensitive(type1, "boolean"))&&(
				equalTypeCaseSensitive(type2, "Boolean")||equalTypeCaseSensitive(type2, "boolean")))
			return true;
		
		if((equalTypeCaseSensitive(type1, "Character")||equalTypeCaseSensitive(type1, "char"))&&(
				equalTypeCaseSensitive(type2, "Character")||equalTypeCaseSensitive(type2, "char")))
			return true;
		return false;
	}
	
	public static String transformarPrimitivo(String primitivo)
	{
		String suObjeto="";
		if(primitivo.equals("int"))
				suObjeto="Integer";
		else if(primitivo.equals("double"))
				suObjeto="Double";
		else if(primitivo.equals("boolean"))
				suObjeto="Boolean";
		else if(primitivo.equals("float"))
				suObjeto="Float";
		else if(primitivo.equals("long"))
				suObjeto="Long";
		else if(primitivo.equals("byte"))
				suObjeto="Byte";
		else if(primitivo.equals("char"))
				suObjeto="Character";
		return suObjeto;
	}
	
}

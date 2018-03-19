package reducedTest;

public class ControlFields {
	
	public static boolean isVarName(String dato)
	{
		if(dato.matches("[a-zA-Z]+[a-zA-Z_0-9]*{1}"))
			return true;
		else
			return false;
	}
	
	public static boolean isDecimal(String dato)
	{
		if(!dato.equals("-0.0"))
			if(dato.matches("0.[0-9]*|[1-9][0-9]*.[0-9]*|-0.[0-9]*|-[1-9][0-9]*.[0-9]*"))
				return true;
		return false;
	}
	
	public static boolean isInteger(String dato)
	{
		if(dato.matches("[1-9][0-9]*|[-][1-9][0-9]*|0"))
			return true;
		else
			return false;
	}
	
	public static boolean isCharacter(String dato)
	{
		if(dato.length()==1)
			return true;
		else
			return false;
	}
	
	public static boolean isBoolean(String dato)
	{
		if(dato.matches("true|false"))
			return true;
		else
			return false;
	}
	
	public static void main(String[] args)
	{
//		System.out.println("isVarName(\"nombre\")="+isVarName("nombre"));
//		System.out.println("isVarName(\"Nombre\")="+isVarName("Nombre"));
//		System.out.println("isVarName(\"Nombre123\")="+isVarName("Nombre123"));
//		System.out.println("isVarName(\"a1\")="+isVarName("a1"));
//		System.out.println("isVarName(\"nombre_apellido\")="+isVarName("nombre_apellido"));
//		System.out.println("isVarName(\"NOMBRE\")="+isVarName("NOMBRE"));
//		System.out.println("isVarName(\" \")="+isVarName(" "));
//		System.out.println("isVarName(\"\")="+isVarName(""));
//		System.out.println("isVarName(\"1nombre\")="+isVarName("1nombre"));
		
//		System.out.println("isDecimal(\"0.0\")="+isDecimal("0.0"));
//		System.out.println("isDecimal(\".0\")="+isDecimal(".0"));
//		System.out.println("isDecimal(\"1\")="+isDecimal("1"));
//		System.out.println("isDecimal(\"1.\")="+isDecimal("1."));
//		System.out.println("isDecimal(\"10.20\")="+isDecimal("10.20"));
//		System.out.println("isDecimal(\"10.20.9\")="+isDecimal("10.20.9"));
		
//		System.out.println("isInteger(\"10.20\")="+isInteger("10.20"));
//		System.out.println("isInteger(\"10\")="+isInteger("10"));
//		System.out.println("isInteger(\"010\")="+isInteger("010"));
//		System.out.println("isInteger(\"010\")="+isInteger("010"));
//		System.out.println("isInteger(\"-10\")="+isInteger("-10"));
//		System.out.println("isInteger(\"0\")="+isInteger("0"));
//		System.out.println("isInteger(\"-0\")="+isInteger("-0"));
//		System.out.println("isInteger(\"0-1\")="+isInteger("0-1"));
//		System.out.println("isInteger(\"-01\")="+isInteger("-01"));
//		System.out.println("isInteger(\"101\")="+isInteger("101"));
		
//		System.out.println("isBooelan(\"true\")="+isBoolean("true"));
//		System.out.println("isBooelan(\"false\")="+isBoolean("false"));
//		System.out.println("isBooelan(\"True\")="+isBoolean("True"));
		
		
	}
	
}

package reducedTest;

public class Efecto {

	private String field;
	private String type;
	private String value;
	private Boolean isReturn;
	
	public Efecto()
	{
		
	}
	public Efecto(String field,String type, String value)
	{
		this.field=field;
		this.type=type;
		this.value=value;
		this.isReturn=false;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean isReturn()
	{
		return isReturn;
	}
	
	public void setReturn(Boolean isReturn)
	{
		this.isReturn=isReturn;
	}
	
	public String getSentence(String typeReturn)
	{
		if(field.equals("")||field.equals("none"))//es un retorno de valor
			return "("+ typeReturn +")"+ControlTypes.getInstanceValue(typeReturn,this.value);
		else
			if(value.equals("") && this.isReturn)
				return this.field;
			else
				return this.field+"="+ControlTypes.getInstanceValue(this.type,this.value);
	}
	
	public String toString()
	{
		String str="";
		if(!this.field.equals("") && !this.value.equals(""))
		{
			str=field+" = "+value;
		}
		else
		{
			str=field+value;
		}
		return str;
	}
	
}

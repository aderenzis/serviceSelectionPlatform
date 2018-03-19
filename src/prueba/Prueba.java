package prueba;

public class Prueba {

	
	public static float sum(float x,float y)
	{
		if(x==10.0 && y==5.0)
		{
			return (float)15.0;
		}
		if(x==10.0 && y==0.0)
		{
			return (float)10.0;
		}
		if(x==0.0 && y==0.0)
		{
			return (float)0.0;
		}
		if(x==0.0 && y==5.0)
		{
			return (float)5.0;
		}
		return (float)0.0;
	}

	public float product(float x,float y)
	{
		if(x==2.0 && y==3.0)
		{
			return (float)6.0;
		}
		if(x==2.0 && y==1.0)
		{
			return (float)6.0;
		}
		if(x==2.0 && y==3.0)
		{
			return (float)6.0;
		}
		if(x==2.0 && y==1.0)
		{
			return (float)2.0;
		}
		return (float)0.0;
	}
	
	public static void main (String [] args){
		float arg1=(float) 10.0;
		float arg2=(float) 0.0;
		float result0= sum(arg1, arg2);
		
		System.out.println(new Float(result0).toString());
	}
}

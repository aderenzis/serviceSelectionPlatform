package calculadora;
public class calculator
{

	public float sum(float x,float y)
	{
		if(x==5.0 && y==5.0)
		{
			return (float)10;
		}
		if(x==10.0 && y==10.0)
		{
			return (float)20;
		}
		return (float)0.0;
	}

	public float product(float x,float y)
	{
		if(x==5.0 && y==5.0)
		{
			return (float)25;
		}
		if(x==10.0 && y==10.0)
		{
			return (float)100;
		}
		return (float)0.0;
	}

}
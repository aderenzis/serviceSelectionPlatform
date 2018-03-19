package testooj3.domain;
import java.io.Serializable;

import testooj3.auxiliares.Auxi;

public class SerializedValue extends TestValue implements Serializable
{
  public SerializedValue(Object valor)
  {
    super(valor);
  }
  
  public Object getValue() 
  {
    return Auxi.escapa(mValue.toString());
  }
  
  public TestValue copy() 
  {
      TestValue result=new PrimitiveValue(this.mValue);
      result.mExceptionLaunched=this.mExceptionLaunched;
      return result;
  }
}
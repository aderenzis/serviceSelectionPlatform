package testooj3.domain;

import java.io.Serializable;

public class PrimitiveValue extends TestValue implements Serializable
{
    public PrimitiveValue(Object valor)
    {
        super(valor);
    }
    
    public Object getValue() 
    {
        return mValue;
    }
    
    public TestValue copy() 
    {
        TestValue result=new PrimitiveValue(this.mValue);
        result.mExceptionLaunched=this.mExceptionLaunched;
        return result;
    }
}
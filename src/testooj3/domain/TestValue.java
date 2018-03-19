package testooj3.domain;

import java.io.Serializable;

public abstract class TestValue implements Serializable
{
  protected Object mValue;
  protected String mExceptionLaunched;
  
  public TestValue(Object valor)
  {
    mValue=valor;
  }
  
  public boolean equals(Object o) {
	  if (!(o instanceof TestValue))
		  return false;
	  TestValue auxi=(TestValue) o;
	  return mValue.equals(auxi.mValue);
  }
  
  public abstract Object getValue();  
  
  public String toString() 
  {
    return mValue.toString();
  }

	/**
	 * @return
	 */
	public String getExceptionLaunched() {
	    return mExceptionLaunched;
	}  
	
	public void setExceptionLaunched(String exceptionLaunched) {
	    mExceptionLaunched = exceptionLaunched;
	}

    /**
     * @return
     */
    public abstract TestValue copy();
}
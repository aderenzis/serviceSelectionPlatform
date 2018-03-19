package testooj3.domain.testfiles;

import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;

public abstract class Header 
{
  protected String mCode;
  
  public Header()
  {
    mCode="";
  }
  
  protected String getPackageDeclaration() {
      String paquete="package " + Configuration.getInstance().getTestingClassesPackage();
      paquete=Auxi.substituteAll(paquete, "\\", ".") + ";\n\n";
      return paquete;
  }
  
  public String toString() 
  {
    return mCode;
  }
}
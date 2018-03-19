package testooj3.domain.grouped;
import java.util.Vector;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.Operation;
import testooj3.domain.TestTemplate;
import testooj3.domain.TestoojMethod;

/**
 * @author  andres
 */
public class GroupedTestMethodForLotOfCases extends GroupedTestMethod
{
  protected Interface mClass;
  public GroupedTestMethodForLotOfCases(Interface c, String name, boolean lanzaExcepciones)
  {
    super(name,  lanzaExcepciones);
    mClass=c;
  }
  
 public void group(TestTemplate ts) throws Exception
  {
    this.setTipo("int");
    Vector declaraciones=new Vector(), aperturas=new Vector(), contadores=new Vector();
    Vector metodos=ts.getMethods();
    
    TConstructor cons=ts.getConstructor();
    buildCode(ts.getConstructor(), declaraciones, contadores, aperturas, 0);
      
    for (int i=0; i<metodos.size(); i++) 
    {
      Operation m=(Operation) metodos.elementAt(i);
      buildCode(m, declaraciones, contadores, aperturas, i+1);      
    }
    for (int i=0; i<aperturas.size(); i++) 
      mLineas.add(aperturas.elementAt(i));    
    mLineas.add("\t\tint cont=1;\n");
    for (int i=0; i<declaraciones.size(); i++) 
      mLineas.add(declaraciones.elementAt(i));
    addFors(contadores, ts);
    mLineas.add("\t\treturn cont-1;\n");
    System.out.println(this.toString(true));
  }  
  
  protected void addFors(Vector contadores, TestTemplate ts) 
  {
    for (int i=0; i<contadores.size(); i++) 
    {
      String contador=contadores.elementAt(i).toString();
      String linea="\t\t";
      for (int j=0; j<i; j++) 
        linea+="\t";
      linea+="for (int c" + contador + "=0; c" + contador + "<" + contador + ".length; c" + contador + "++) {\n";
      mLineas.add(linea);
    }
    String linea="", tabs="\t\t";
    for (int j=0; j<contadores.size(); j++) 
      tabs+="\t";
    TConstructor cons=ts.getConstructor();
    linea=tabs+cons.getDeclaracionDeObjeto("o")+" int pos=0; Vector messages=new Vector();\n";
    mLineas.add(linea);
    linea=tabs + "try {\n";
    mLineas.add(linea);
    mLineas.add(tabs + "\t" + this.buildCall(cons, 0));
    
    for (int i=0; i<ts.getMethods().size(); i++) 
    {
      Operation m=(Operation) ts.getMethods().elementAt(i);
      mLineas.add(tabs + "\tpos++; " + this.buildCall(m, i+1));
    }
    if (mClass.getInvariants().trim().length()>0) {
      mLineas.add(tabs+"\t"+mClass.getInvariants().trim()+"\n");
    }
    mLineas.add(tabs+"}\n");
    int numeroDeMetodos=this.getNumeroDeMetodos()-1;
    if (mClass.getInvariants().trim().length()>0) {
      String violInv="catch (InvariantViolationException ex) {\n";
      mLineas.add(tabs + violInv);
      violInv="\tmessages.set(0, \"InvariantViolationException\");\n";
      mLineas.add(tabs + violInv);
      violInv="\tSystem.err.println(\"PATTERN_NAME_"+ "\" + cont + " + "\": \" + ex.toString());\n";
      mLineas.add(tabs + violInv);        
      mLineas.add(tabs+"}\n");  
    }
    linea=tabs + "catch (Exception ex) { messages.add(ex.toString()); " + 
      "for (int i=pos+1; i<" +  numeroDeMetodos + "; i++) messages.add(\"\");" + 
      "System.err.println(\"PATTERN_NAME_"+ "\" + cont + " + "\": \" + ex.toString()); } // Closing call\n";
    mLineas.add(linea);
    mLineas.add(tabs+"TestObject ot=new TestObject(\"PATTERN_NAME_\" + cont, o, messages);\n");
    mLineas.add(tabs+"saveTestObject(mTempFolder, \"PATTERN_NAME_\", cont++, ot);\n"); 
    for (int i=contadores.size()-1; i>=0; i--) 
    {
      String contador=contadores.elementAt(i).toString();
      linea="";
      for (int j=0; j<i+2; j++) 
        linea+="\t";
      linea+="}\n";
      mLineas.add(linea);
    }    
  }  
  
  public String getMetodoCheck(String cut) 
  {
		String r="\tpublic void check" + this.mNombre + "() {\n";
		if (mLanzaExcepciones) {
			r+="\t\tString[] msg={\"\", ";
			for (int i=0; i<mMutantes.size(); i++)
				r+="\"\", ";
			r=r.substring(0, r.length()-2);
			r+="};\n";
		}

    r+="\t\tint casos=0;\n";
    r+="\t\ttry { casos=" + this.getNombre() + "(); } catch (Exception ex) { } \n";
    for (int i=0; i<mMutantes.size(); i++) 
    {
      TestoojMethod mutante=(TestoojMethod) mMutantes.elementAt(i);
      r+="\t\ttry {" + mutante.getNombre() + "();  } catch (Exception ex) { }\n";
    }
    r+="\n\t\tfor (int i=0; i<casos; i++) {\n";
    r+="\t\t\tTestObject original=this.readTestObject(\"" + mNombre + "_\", i+1);\n";
    for (int i=0; i<mMutantes.size(); i++) {
      TestoojMethod mutante=(TestoojMethod) mMutantes.elementAt(i);
      r+="\t\t\tTestObject oMut" + (i+1) + "=this.readTestObject(\"" + mutante.getNombre() + "_\", i+1);\n";
    }
    r+="\t\t\tTestObject[] mutants={";
    for (int i=0; i<mMutantes.size(); i++) 
    {
      r+="oMut" + (i+1) + ", ";
    }
    if (r.endsWith(", "))
      r=r.substring(0, r.length()-2);
    r+="};\n";
    int numeroDeMetodos=this.getNumeroDeMetodos()-1;
    //r+="\t\t\tcompareObjects(original, mutants, " + numeroDeMetodos + ");\n";
    r+="\t\t\tcompareObjects(original, mutants);\n";
    r+="\t\t}\n";
    r+="\t}\n\n";
    return r;
  }    
}
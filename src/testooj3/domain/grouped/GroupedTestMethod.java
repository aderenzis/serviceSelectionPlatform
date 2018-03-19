package testooj3.domain.grouped;

import testooj3.auxiliares.Auxi;
import testooj3.domain.Configuration;
import testooj3.domain.TConstructor;
import testooj3.domain.TestoojMethod;
import testooj3.domain.Operation;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;
import testooj3.domain.TestTemplate;
import java.util.Vector;
import testooj3.domain.TestValue;
import testooj3.persistence.Agente;

public class GroupedTestMethod extends TestoojMethod
{
  public GroupedTestMethod(String name, boolean lanzaExcepciones)
  {
    super(name, lanzaExcepciones);
  }
  
  public void group(TestTemplate ts) throws Exception
  {
    this.setTipo("Vector");
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
    mLineas.add("\t\tVector result=new Vector();\n");
    for (int i=0; i<declaraciones.size(); i++) 
      mLineas.add(declaraciones.elementAt(i));
    addFors(contadores, ts);
    mLineas.add("\t\treturn result;\n");
    System.out.println(this.toString(false));
  }
  
  protected void buildCode(TOperation operation, Vector declaraciones, Vector contadores, 
      Vector aperturas, int opNumber) throws Exception
  {
    for (int j=0; j<operation.getParametros().size(); j++) 
    {
      TParameter p=(TParameter) operation.getParametros().elementAt(j);
      TestValue[] valores=p.getTestValues();
      String[] ficheros=Agente.getAgente().cogerNombreFicheros(p.getTipo());
      String declaracion="\t\t"+p.getTipo() + "[] v" + j + "_" + opNumber + "= {";
      contadores.add("v" + j + "_" + opNumber);
      if (Agente.getAgente().esPrimitivo(p.getTipo())) {
        for (int k=0; k<valores.length; k++) {
          String comillas="";
          if (p.getTipo().equals("java.lang.String"))
            comillas="\"";
          else if (p.getTipo().equals("char"))
            comillas="'";
          declaracion+=comillas + valores[k].getValue() + comillas;
          if (k<valores.length-1) 
            declaracion+=", ";            
        }
      } else // Si no es primitivo
      {
        for (int numeroDeFichero=0; numeroDeFichero<ficheros.length; numeroDeFichero++) {
          declaracion+="so"+j + "_" + opNumber + "_" + numeroDeFichero;
          if (numeroDeFichero<ficheros.length-1) 
            declaracion+=", ";            
          addApertura(aperturas, p, j, opNumber, numeroDeFichero, ficheros[numeroDeFichero]);
        }          
      }
      declaracion+="};\n";
      declaraciones.add(declaracion);
    }    
  }
    
  protected String buildCall(TOperation op, int opNumber) 
  {
      String parametros="";
      for (int j=0; j<op.getParametros().size(); j++) 
      {
        parametros+="v" + j + "_" + opNumber + "[cv" + j + "_" + opNumber + "], ";
      }
      if (parametros.length()>0) {
        parametros=parametros.substring(0, parametros.length()-2);
      }
      String instruccion= "{";
      for (int k=0; k<op.getPre().size(); k++) {
        String pre=op.getPre().elementAt(k).toString();
        pre=adaptaPre(pre, opNumber);
        instruccion+=pre;
      }
      instruccion+=op.getLlamada("o", parametros, 0);
      for (int k=0; k<op.getPost().size(); k++) {
        String post=op.getPost().elementAt(k).toString();
        post=adaptaPost(post, opNumber);
        instruccion+=post;
      }
      instruccion+="messages.add(\"\");}\n";    
      return instruccion;
  }
  
  protected String buildCallAsText(TOperation op, int opNumber) 
  {
      String parametros="";
      for (int j=0; j<op.getParametros().size(); j++) 
      {
        parametros+="v" + j + "_" + opNumber + "[cv" + j + "_" + opNumber + "]";
        if (j<op.getParametros().size()-1)
          parametros+="+ \", \" + ";
      }
      String llamada= (op instanceof Operation ? "o." + op.getNombre() : "o=new " + op.getNombre());
      String instruccion=llamada + "(\" + " + parametros + " + \");";
      if (parametros.length()==0)
        instruccion=llamada+"();";
      return instruccion;
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
    linea=tabs+cons.getDeclaracionDeObjeto("o")+" int pos=0;\n";
    mLineas.add(linea);
    linea=tabs + "try {\n";
    mLineas.add(linea);
    //linea=tabs+ "\t{";
    mLineas.add(this.buildCall(cons, 0));
    
    for (int i=0; i<ts.getMethods().size(); i++) 
    {
      Operation m=(Operation) ts.getMethods().elementAt(i);
      mLineas.add(tabs + "\tpos++; " + this.buildCall(m, i+1));
    }
    mLineas.add(tabs + "} catch (Exception ex) { ");
    
    linea=tabs + "} catch (Exception ex) { messages.add(ex.getMessage()); " + 
      "for (int i=pos+1; i<" +  (this.getNumeroDeMetodos()-1) + "; i++) messages.add(\"\");" + 
      "System.err.println(\""+ ts.getName() + ": \" + ex.toString()); } // Closing call\n";
    mLineas.removeElementAt(mLineas.size()-1);
    mLineas.add(linea);
    mLineas.add(tabs+"result.add(o);\n");
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
  
  protected String adaptaPre(String pre, int numeroDeMetodo) 
  {
    return adaptaPost(pre, numeroDeMetodo);
  }
  
  protected String adaptaPost(String post, int numeroDeMetodo) 
  {
    int pos=post.indexOf("x");
    int numeroDeParametro=0;
    while (pos!=-1) 
    {
      if (Character.isDigit(post.charAt(pos+1)))
      {
        numeroDeParametro=Integer.parseInt(""+post.charAt(pos+1));
        String auxi=post.substring(0, pos);
        auxi+="v" + numeroDeParametro + "_" + numeroDeMetodo + "[cv" + numeroDeParametro + "_" + numeroDeMetodo + "]";
        auxi+=post.substring(pos+2);
        post=auxi;
        //numeroDeParametro++;
      }
      pos=post.indexOf("x", pos+1);      
    }
    return post;
  }  
  
  protected void addApertura(Vector aperturas, TParameter p, int parNumber, int opNumber, int numeroDeFichero, String fileName) 
  {
    // Formato: fso-x_y_z: x: nº de parámetro; y: nº de método; z: nº de fichero
    String fichero= Configuration.getInstance().getSerializedObjectsPath() + fileName;
    aperturas.add("FileInputStream fso" + parNumber + "_" + opNumber + "_" + numeroDeFichero + 
      "=new FileInputStream(\"" + Auxi.escapa(fichero) + "\");");
    aperturas.add("ObjectInputStream ffso" + parNumber + "_" + opNumber + "_" + numeroDeFichero + 
      "=new ObjectInputStream(fso" + parNumber + "_" + opNumber + "_" + numeroDeFichero +  ");");
    aperturas.add(p.getTipo() + " so" + parNumber + "_" + opNumber + "_" + numeroDeFichero + 
      "=(" + p.getTipo() + ") ffso" + parNumber + "_" + opNumber + "_" + numeroDeFichero + ".readObject();");
    aperturas.add("fso" + parNumber + "_" + opNumber + "_" + numeroDeFichero + ".close();\n");    
  }
  
  protected int getNumeroDeMetodos() 
  {
    int inicio=0;
    int i=0; String linea;
    do 
    {
      linea=this.getLinea(i++);
    } while (!linea.trim().startsWith("try {"));
    inicio=i;
    do 
    {
      linea=this.getLinea(i++);
    } while (!linea.trim().startsWith("}"));  // Busco la } que cierra el try 
    return i-inicio-1;
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

    r+="\t\tVector original=null"; String tmp="\t\tVector[] messages={new Vector()";
    for (int i=0; i<mMutantes.size(); i++) 
    {
      r+=", mutante" + (i+1) + "=null";
      tmp+=", new Vector()";
    }
    r+=", captions=new Vector()";
    r+=";\n";
    tmp+="};\n";
    r+=tmp;
    r+="\t\ttry { original=" + this.getNombre() + "(messages[0]); captions.add(\"Original\"); } catch (Exception ex) { } \n";
    for (int i=0; i<mMutantes.size(); i++) 
    {
      TestoojMethod mutante=(TestoojMethod) mMutantes.elementAt(i);
      r+="\t\ttry { mutante" + (i+1) + "=" + mutante.getNombre() + "(messages[" + (i+1) + "]); " +
        "captions.add(\"" + mutante.getNombre() + "\"); } catch (Exception ex) { }\n";
    }
    r+="\n\t\tfor (int i=0; i<original.size(); i++) {\n";
    r+="\t\t\t" + cut + " o=(" + cut + ") original.elementAt(i);\n";
    for (int i=0; i<mMutantes.size(); i++) {
      r+="\t\t\t" + cut + " oMut" + (i+1) + "= mutante" + (i+1) + "==null ? null :  (" + cut + ") mutante" + (i+1) + ".elementAt(i);\n"; 
    }
    tmp="\t\t\tObject[] mutants={";
    for (int i=0; i<mMutantes.size(); i++) 
    {
      tmp+="oMut" + (i+1) + ", ";
    }
    if (tmp.endsWith(", "))
      tmp=tmp.substring(0, tmp.length()-2);
    tmp+="};\n";
    r+=tmp;
    int numeroDeMetodos=this.getNumeroDeMetodos()-1;
    r+="\t\t\tcompare(\"" + this.getNombre() + "\", o,mutants, captions, i, messages, i*" + numeroDeMetodos + 
        ", " + numeroDeMetodos + ");\n";
    r+="\t\t}\n";
    r+="\t}\n\n";
    return r;
  }  
}
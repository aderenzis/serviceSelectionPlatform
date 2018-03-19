package testooj3.domain;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

import testooj3.domain.testmethodlines.APeloLine;
import testooj3.domain.testmethodlines.CallLineToConstructor;
import testooj3.domain.testmethodlines.CallLineToMethod;
import testooj3.domain.testmethodlines.ObjectDeclarationLine;
import testooj3.domain.testmethodlines.xml.XMLEndCallToMethod;
import testooj3.domain.testmethodlines.xml.XMLLine;
import testooj3.domain.testmethodlines.xml.XMLLineCheckDetailedState;
import testooj3.domain.testmethodlines.xml.XMLLineEndCallToConstructor;
import testooj3.domain.testmethodlines.xml.XMLLineEndTestCase;
import testooj3.domain.testmethodlines.xml.XMLLineStartTestCase;
import testooj3.domain.testmethodlines.xml.XMLParameterDeclarationLine;
import testooj3.domain.testmethodlines.xml.XMLStartCallToMethodPart1;
import testooj3.domain.testmethodlines.xml.XMLStartCallToMethodPart2;
import testooj3.domain.testmethodlines.xml.XMLStartConstructor;

public class TJUnitMethodForState extends TJUnitMethod implements Serializable
{
  public TJUnitMethodForState(TestCase tc) throws IOException
  {
    mNombre=tc.getNombre();
    mLanzaExcepciones=false;
    String firstExceptionLaunched=null;
    mTipo="void";
    mLineas=new Vector();
    int[] cont={1};
    int pos=cont[0];
    XMLLine xml1=new XMLLineStartTestCase(tc);
    mLineas.add(xml1);
    ObjectDeclarationLine odl=new ObjectDeclarationLine(tc.getConstructor());
    mLineas.add(odl);
    Vector xmlPars=new Vector();
    Vector lineas=tc.getConstructor().getDeclaraciones(tc.mParsConstructor, cont, true, xmlPars);
    firstExceptionLaunched=tc.getConstructor().getExceptionLaunched(tc.mParsConstructor);
    for (int i=0; i<tc.getConstructor().getLaunchedExceptions().size(); i++) {
        String lEx=tc.getConstructor().getLaunchedExceptions().get(i).toString();
        if (!mPossibleExceptions.containsKey(lEx))
            mPossibleExceptions.put(lEx, lEx);
    }
    pos=cont[0];
    for (int j=0; j<lineas.size(); j++)
        mLineas.add(lineas.get(j));
    XMLLine xml2=new XMLStartConstructor();
    mLineas.add(xml2);
    for (int i=0; i<xmlPars.size(); i++) {
        mLineas.add(xmlPars.get(i));
    }
    APeloLine llave1=new APeloLine("{");
    mLineas.add(llave1);
    //addAssertions(true, tc.getConstructor(), mLineas, pos);
    if (tc.getConstructor().getLanzaExcepciones())
        this.mLanzaExcepciones=true;
    CallLineToConstructor cl=new CallLineToConstructor(tc.getConstructor());
    mLineas.add(cl);
    XMLLine xml_n=new XMLStartCallToMethodPart2();
    mLineas.add(xml_n);
    addAssertions(false, tc.getConstructor(), mLineas, pos);
    APeloLine llave2=new APeloLine("}");
    mLineas.add(llave2);
    TConstructor cons=tc.getConstructor();
    //mLineas.add(xmlPars[0]);
    XMLLine xml3=new XMLLineEndCallToConstructor();
    mLineas.add(xml3);
    XMLLine xml4=new XMLLineCheckDetailedState();
    mLineas.add(xml4);
    APeloLine apl=new APeloLine("\t\t");
    mLineas.add(apl);
    cont[0]=tc.getConstructor().getParametros().size()+1;
    for (int i=0; i<tc.getMetodos().size(); i++) 
    {
      pos=cont[0];
      Operation m=(Operation) tc.getMetodos().elementAt(i);
      if (m.getLanzaExcepciones())
        mLanzaExcepciones=true; 
      Vector valores=(Vector) tc.mParsMetodos[i];
      xmlPars=new Vector();
      lineas=m.getDeclaraciones((Vector) tc.mParsMetodos[i], cont, true, xmlPars);
      if (firstExceptionLaunched==null)
          firstExceptionLaunched=m.getExceptionLaunched(valores);
      for (int j=0; j<m.getLaunchedExceptions().size(); j++) {
          String lEx=m.getLaunchedExceptions().get(j).toString();
          if (!mPossibleExceptions.containsKey(lEx))
              mPossibleExceptions.put(lEx, lEx);
      }
      cont[0]+=(valores!=null ? valores.size() : 0);
      for (int j=0; j<lineas.size(); j++)
        mLineas.add(lineas.get(j));
      XMLLine xml5=new XMLStartCallToMethodPart1(m);
      mLineas.add(xml5);
      for (int j=0; j<xmlPars.size(); j++) {
          mLineas.add(xmlPars.get(j));
      }
      mLineas.add(llave1);
      CallLineToMethod clm=new CallLineToMethod(m, pos, i);
      if (m.getLaunchedExceptions().size()>0) {
		mLineas.add(new APeloLine("try {"));
		mLineas.add(clm);
		XMLLine xml6=new XMLStartCallToMethodPart2();
	    mLineas.add(xml6);
		mLineas.add(new APeloLine("}"));
		for (int j=0; j<m.getLaunchedExceptions().size(); j++) {
			String ex=m.getLaunchedExceptions().get(j).toString();
			APeloLine catch1=new APeloLine("catch (" + ex + " e) {");
			APeloLine catch2=new APeloLine("xml+=\"<targetState name='\" + e.toString() + \"' exception='true'/>\";");
			APeloLine catch3=new APeloLine("}");
			mLineas.add(catch1);
			mLineas.add(catch2);
			mLineas.add(catch3);
		}
      } else {
    	  mLineas.add(clm);
          XMLLine xml6=new XMLStartCallToMethodPart2();
          mLineas.add(xml6);
      }
      mLineas.add(llave2);
      XMLLine xml7=new XMLLineCheckDetailedState();
      mLineas.add(xml7);
      XMLLine xml9=new XMLEndCallToMethod();
      mLineas.add(xml9);
    }
    if (firstExceptionLaunched!=null)
        surroundWith(firstExceptionLaunched);
    XMLLine xml10=new XMLLineEndTestCase(tc);
    mLineas.add(xml10);    
    APeloLine linea=new APeloLine("try { mXML.write(xml.getBytes()); } catch(Exception ex) {}");
    mLineas.add(linea);    
  }
  
  protected void surroundWith(String exception) {
      this.mExceptionLaunched=exception;
      APeloLine a1=new APeloLine("try {");
      mLineas.insertElementAt(a1, 1);
      APeloLine a2=new APeloLine("fail(\"" + getNombre() + ": " + exception + 
      	" expected\");\n");
      mLineas.insertElementAt(a2, mLineas.size()-2);
      APeloLine a3=new APeloLine("}\n");
      mLineas.add(a3);
      APeloLine a4=new APeloLine("catch (" + exception + " ex) {");
      mLineas.add(a4);
      APeloLine a5=new APeloLine("xml+=\"<targetState name='\" + ex.toString() + \"' exception='true'/>\";");
      mLineas.add(a5);
      APeloLine a6=new APeloLine("xml+=\"</operation>\";");
      mLineas.add(a6);
      APeloLine a7=new APeloLine("}\n");
      mLineas.add(a7);
      if (!exception.equals("Exception") && !exception.equals("java.lang.Exception")) {
		APeloLine a8=new APeloLine("catch (Exception ex) {\n");
		mLineas.add(a8);
		APeloLine a9=new APeloLine("fail(\"" + getNombre() + ": " + exception + " expected\");\n");
		mLineas.add(a9);
		mLineas.add(a5);
		mLineas.add(a6);
		APeloLine a10=new APeloLine("}\n");
		mLineas.add(a10);
      }
  }
  
  protected String getExtension() {
      return "states";
  }
}
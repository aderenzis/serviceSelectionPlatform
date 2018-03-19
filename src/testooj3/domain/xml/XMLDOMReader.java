package testooj3.domain.xml;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDOMReader 
{
  private Document mDoc;

  public XMLDOMReader(String fileName) throws ParserConfigurationException, IOException, SAXException
  {
    DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
    File f=new File(fileName); 
    mDoc=db.parse(f);
  }
  
  public String getCoverageAnalysis() 
  {
      Vector coverages=new Vector();
      Node raiz=mDoc.getFirstChild();
      int r=0; 
      NodeList hijos=raiz.getChildNodes();
      for (int i=0; i<hijos.getLength()-1; i++) 
      {
        Node tc=hijos.item(i);
        Node nOrig=tc.getFirstChild();
        Node nCoverage=nOrig.getFirstChild();
        if (nCoverage!=null) 
        {
          String coverage=nCoverage.getNodeValue();
          if (coverage!=null && (coverage.toLowerCase().startsWith("coverage"))) {
            int posAlmohadilla=coverage.indexOf("#");
            if (posAlmohadilla==-1) posAlmohadilla=coverage.length();
            coverage=coverage.substring(10, posAlmohadilla);
            if (!coverages.contains(coverage))
              coverages.add(coverage);
          }
        }
      }
      String result="<table><tr><th>Coverage</th></tr>\n";
      for (int i=0; i<coverages.size(); i++) 
      {
        result+="<tr><td>" + coverages.elementAt(i) + "</td></tr>\n";
      }
      result+="</table>";
      return result;
  }
  
  public String getArbol() 
  {
    Node raiz=mDoc.getFirstChild();
    StringBuffer s=new StringBuffer("<b>Number of test cases:</b>" + (raiz.getChildNodes().getLength()-1));
    Vector vivos=new Vector();
    String[] operadores={"_CL_", "_IN_", "_PI_", "_ZE_", "_MAX_", "_MIN_", "_NEG_", "_ADD_", "_SUB_", "_RC_"};
    int[] mutantesMuertos=new int[operadores.length], 
      numeroDeMutantes=new int[operadores.length], 
      violacionesOrig={0},
      violacionesMutantes={0},
      invariantesOriginal={0},
      precondicionesOperacionesOriginal={0},
      precondicionesOperacionesMutantes=new int[operadores.length], // precondiciones de operaciones
      postcondicionesOperacionesOriginal={0},
      postcondicionesOperacionesMutantes=new int[operadores.length], // postcondiciones de operaciones
      invariantesMutantes=new int[operadores.length],     // invariantes de clase
      postcondicionesResultadoOriginal={0},
      postcondicionesResultadoMutantes=new int[operadores.length];
      mutantesMuertos=this.getKilledWith(raiz, operadores, numeroDeMutantes, violacionesOrig, violacionesMutantes, 
      invariantesOriginal, invariantesMutantes, 
      precondicionesOperacionesOriginal, precondicionesOperacionesMutantes,
      postcondicionesOperacionesOriginal, postcondicionesOperacionesMutantes,
      postcondicionesResultadoOriginal, postcondicionesResultadoMutantes, vivos);
    s.append("<table border=1><tr>");
    s.append("<th font>Operator</th>");
    s.append("<th>Mutants</th>");
    s.append("<th>Injured<br>(total/%)</th>");
    s.append("<th>Killed<br>(total/%)</th>");
    s.append("<th>Total<br>(total/%)</th>");
    s.append("<th>Precondition violations<br>(total/%)</th>");
    s.append("<th>Postcondition violations<br>(total/%)</th>");
    s.append("<th>Invariant violations<br>(total/%)</th>");
    s.append("<th>Result post. violations<br>(total/%)</th>");
    s.append("</tr>");
    for (int i=0; i<operadores.length; i++) {
      s.append("<tr><th>");
      s.append(operadores[i].substring(1, operadores[i].lastIndexOf("_")));
      s.append("</th>");
      s.append("<td align=right>" + numeroDeMutantes[i] + "</td>");
      s.append("<td align=right>" + mutantesMuertos[i+operadores.length] + "/" + (int) ((float) mutantesMuertos[i+operadores.length]*100/numeroDeMutantes[i]) + "%</td>");
      s.append("<td align=right>" + mutantesMuertos[i] + "/" + (int) ((float) mutantesMuertos[i]*100/numeroDeMutantes[i]) + "%</td>");
      s.append("<td align=right>" + (mutantesMuertos[i+operadores.length]+mutantesMuertos[i]) + "/" +
        (int) ((float) (mutantesMuertos[i]+mutantesMuertos[i+operadores.length])*100/numeroDeMutantes[i]) + "%</td>");
      s.append("<td align=right>" + precondicionesOperacionesMutantes[i] + "/" + (int) ((float) precondicionesOperacionesMutantes[i]*100/numeroDeMutantes[i]) + "%</td>");
      s.append("<td align=right>" + postcondicionesOperacionesMutantes[i] + "/" + (int) ((float) postcondicionesOperacionesMutantes[i]*100/numeroDeMutantes[i]) + "%</td>");
      s.append("<td align=right>" + invariantesMutantes[i] + "/" + (int) ((float) invariantesMutantes[i]*100/numeroDeMutantes[i]) + "%</td>");
      s.append("<td align=right>" + postcondicionesResultadoMutantes[i] + "/" + (int) ((float) postcondicionesResultadoMutantes[i]*100/numeroDeMutantes[i]) + "%</td>");
      s.append("</tr>");
    }
    s.append("</table>");
    if (invariantesOriginal[0]>0) 
    {
      s.append("<font color=red>Warning:</font> " + invariantesOriginal[0] + " original test cases violate class invariants<br>");
    }
    if (postcondicionesResultadoOriginal[0]>0) 
    {
      s.append("<font color=red>Warning:</font> " + postcondicionesResultadoOriginal[0] + 
        " original test cases violate the result postcondition<br>");
    }
    if (postcondicionesOperacionesOriginal[0]>0) 
    {
      s.append("<font color=red>Warning:</font> " + postcondicionesOperacionesOriginal[0] + 
        " original test cases violate operation postconditions<br>");
    }
    s.append("<b>Test cases with constraint violations:<br>");
    s.append("in original test cases</b>: " + violacionesOrig[0] + "<br>");
    s.append("<b>in mutations</b>: " + violacionesMutantes[0] + "<br>");
    if (vivos.size()>0) {
      if (vivos.size()>200)
        s.append("There are " + vivos.size() + " alive mutants.<br>");
      else {
        s.append("<b>Alive mutants:</b><br>");
        for (int i=0; i<vivos.size(); i++) 
        {
          s.append(vivos.elementAt(i).toString()+", ");
        }
        //s.delete(0, s.length()-2);
      }
    }
    return s.toString();
  }
  
  private int[] getKilledWith(Node raiz, String[] operadores, int[] numeroDeMutantes, 
                        int[] violacionesOrig, int[] violacionesMut, 
                        int[] invariantesOrig, int[] invariantesMut, 
                        int[] precondicionesOperacionesOrig, int[] precondicionesOperacionesMut,
                        int[] postcondicionesOperacionesOrig, int[] postcondicionesOperacionesMut, 
                        int[] postcondicionesResultadoOrig, int[] postcondicionesResultadoMut, 
                        Vector vivos)
  {
      int[] r=new int[operadores.length*2];
      NodeList hijos=raiz.getChildNodes();
      for (int i=0; i<hijos.getLength(); i++) 
      {
        Node tc=hijos.item(i);
        Node nodoOriginal=tc.getFirstChild();
        Node errorOrig=nodoOriginal.getAttributes().getNamedItem("error");
        if (errorOrig!=null) {
          violacionesOrig[0]++;
          if (errorOrig.getNodeValue().indexOf("InvariantViolationException")!=-1)
            invariantesOrig[0]++;
          if (errorOrig.getNodeValue().indexOf("ResultPostconditionException")!=-1)
            postcondicionesResultadoOrig[0]++;
          if (errorOrig.getNodeValue().indexOf("PostconditionViolationException")!=-1)
            postcondicionesOperacionesOrig[0]++;
          if (errorOrig.getNodeValue().indexOf("PreconditionViolationException")!=-1)
            precondicionesOperacionesOrig[0]++;
        }
        while (nodoOriginal.getNextSibling()!=null) 
        {
          nodoOriginal=nodoOriginal.getNextSibling();
          int operador=esOperador(nodoOriginal.getNodeName(), operadores);
          if (operador!=-1) {
              Node result=nodoOriginal.getAttributes().getNamedItem("result");
              numeroDeMutantes[operador]+=1;
              Node errorMut=nodoOriginal.getAttributes().getNamedItem("error");
              if (errorMut!=null) {
                violacionesMut[0]++;
                if (errorMut.getNodeValue().indexOf("InvariantViolationException")!=-1)
                  invariantesMut[operador]+=1;       
                if (errorMut.getNodeValue().indexOf("ResultPostconditionException")!=-1)
                  postcondicionesResultadoMut[operador]+=1;   
                if (errorMut.getNodeValue().indexOf("PreconditionViolationException")!=-1)
                  precondicionesOperacionesMut[operador]+=1;
                if (errorMut.getNodeValue().indexOf("PostconditionViolationException")!=-1)
                  postcondicionesOperacionesMut[operador]+=1;
              }
              if (result!=null && (result.getNodeValue().equalsIgnoreCase("killed")))
                r[operador]++;  // las dos siguientes ramas son "injured"
              else if (result!=null && (result.getNodeValue().equalsIgnoreCase("injured")))
                r[operadores.length+operador]++;  // hay error en el original y en el mutante, pero un error distinto
              else  // vivo (ni herido ni muerto)
                vivos.add(nodoOriginal.getNodeName());
          }
        }
      }
      return r;
  }  
  
  private int esOperador(String nodo, String[] operadores) 
  {
    for (int i=0; i<operadores.length; i++)
      if (nodo.indexOf(operadores[i])!=-1)
        return i;
    return -1;
  }

}
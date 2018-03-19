package testooj3.domain.algorithms;

import java.util.Vector;

import testooj3.domain.Operation;
import testooj3.domain.TestCase;
import testooj3.domain.TestTemplate;
import testooj3.domain.arboles.Arbol;

public class AntiRandom extends Algorithm {

    public Vector<TestCase> getTestCases(TestTemplate template) throws Exception {      
        TestCase tc;
        int numeroDeParametros=this.numberOfParameters();
        Arbol a = buildArbol();
      
        Vector<TestCase> resultado=new Vector<TestCase>();
        Vector hojas=new Vector();
        a.cargaHojas(hojas);
        
        Vector<String> codigos=codificarRamas(a);        
        int hojaSeleccionada=0;
        Vector<String> codigosUsados=new Vector<String>();
        for (int i=0; i<Math.pow(2, numeroDeParametros); i++) 
        {
            Arbol hoja=(Arbol) hojas.elementAt(hojaSeleccionada);
            codigosUsados.add(codigos.get(hojaSeleccionada));
            Vector rama=hoja.getRama();
            tc=new TestCase(template);
            tc.setNombre(templateName+"_"+(i+1));
            int cont=0;
            for (int j=0; j<constructor.getParametros().size(); j++) 
            {
                Arbol nodo=(Arbol) rama.get(cont++);
                tc.addParametro(nodo.getTestValue());
            }
            cont=0;
            for (int j=0; j<methods.size(); j++) 
            {
                Operation m=(Operation) methods.get(j);
                for (int k=0; k<m.getParametros().size(); k++) {
                    Arbol nodo=(Arbol) rama.get(cont++);
                    tc.addParametro(nodo.getTestValue(), j);
                }
            }
            resultado.add(tc);
            hojaSeleccionada=getSiguienteHoja(codigosUsados, codigos);
        }
        return resultado;
    }

    private int getSiguienteHoja(Vector<String> codigosUsados, Vector<String> codigos) {
        int distanciaHammingMaxima=-1;
        int result=-1;
        for (int i=0; i<codigos.size(); i++) {
            String codigo=codigos.get(i).toString();
            int distancia=0;
            if (!codigosUsados.contains(codigo)) {
	            for (int j=0; j<codigosUsados.size(); j++) {
	                String codigoUsado=codigosUsados.get(j).toString();
	                distancia+=distanciaHamming(codigo, codigoUsado);
	            }
            }
            if (distancia>distanciaHammingMaxima) {
                distanciaHammingMaxima=distancia;
                result=i;
            }
        }
        return result;
    }

    private int distanciaHamming(String codigo, String codigoUsado) {
        int result=0;
        for (int i=0; i<codigo.length(); i++) {
            if (codigo.charAt(i)!=codigoUsado.charAt(i))
                result+=1;
        }
        return result;
    }

    /**
     * @param a
     */
    private Vector<String> codificarRamas(Arbol a) {
        Vector hojas=new Vector();
        a.cargaHojas(hojas);
        double bitsEstrictos=Math.log(hojas.size())/Math.log(2);
        if (Math.floor(bitsEstrictos)>0)
            bitsEstrictos+=1;
        Vector<String> codificaciones=new Vector<String>();
        for (int i=0; i<hojas.size(); i++) {
            String codigo=codigoBinario(i, (int) bitsEstrictos);
            codificaciones.add(codigo);
        }
        return codificaciones;
    }
    
    protected String codigoBinario(int n, int bits) {
        String result="";
        int dividendo=n;
        int cociente, resto=0;
        while (dividendo>=2) {
            cociente=dividendo/2;
            resto=dividendo%2;
            result=resto+result;
            dividendo=cociente;
        }
        result=dividendo+result;
        for (int i=result.length(); i<bits; i++)
            result="0" + result;
        return result;
    }
    
    protected int numberOfParameters() {
        int result=constructor.getParametros().size();
        for (int i=0; i<methods.size(); i++) 
        {
            Operation metodo=(Operation) methods.get(i);
            result+=metodo.getParametros().size();
        }
        return result;
    }

		
}

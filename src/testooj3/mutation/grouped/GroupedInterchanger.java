package testooj3.mutation.grouped;

import java.io.FileOutputStream;
import java.io.IOException;

import testooj3.domain.TestoojMethod;
import testooj3.domain.grouped.GroupedTestMethod;
import testooj3.mutation.Intercambiador;

public class GroupedInterchanger extends Intercambiador 
{
    public GroupedInterchanger()
    {
        super();
    }
    
    public  int mutar(TestoojMethod o, FileOutputStream f, int msgNum) throws IOException 
    {
        GroupedTestMethod original=(GroupedTestMethod) o;  
        // Hago una sola copia del método original JUnit  
        TestoojMethod mutante=(TestoojMethod) original.copy(msgNum);
        mutante.setNombre(original.getNombre() + "_IN");
        int i=0;
        String linea="";
        do 
        {
            linea=original.getLinea(i++);
            if (linea.trim().startsWith("result.add(o);")) return msgNum;
        } while (!linea.trim().startsWith("for (int"));
        // En este punto estoy parado en el primer for
        while ((linea=original.getLinea(i).trim()).startsWith("for (int"))
            i++;
        // En este punto estoy en la primera línea ejecutable (de llamada a la CUT), que es la llamada al constructor
        int primeraLinea=i+2;   // Salto a la primera llamada a método, pq. no se muta la llamada al constructor
        while (!(linea=original.getLinea(i).trim()).startsWith("result.add(o);"))
            i++;
        int ultimaLinea=i-1;
        if (ultimaLinea-primeraLinea>2) {
            String linea1=new String(mutante.getLinea(primeraLinea+1));
            String linea2=new String(mutante.getLinea(primeraLinea+2));
            mutante.setLinea(primeraLinea+1, linea2);
            mutante.setLinea(primeraLinea+2, linea1);
            
            original.add(mutante);
            if (f!=null) 
            {
                f.write(mutante.toString().getBytes());
            }
        }
        return msgNum;
    }    
}
package testooj3.domain;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import testooj3.domain.tcmutation.TGroupedTestMethod;

public abstract class TestMethod implements Serializable
{
    protected String mNombre;
    protected String mTipo;
    protected Vector mLineas;
    protected boolean mLanzaExcepciones;
    protected Vector mMutantes;
    public static final int JUNIT = 1 ;
    public static final int MUJAVA = 2 ;
    public static final int STATES_JUNIT = 3 ;
    public static final int MUT_JUNIT = 4 ;
    public static final int GROUPED = 5 ;
    private int mId;
    
    public TestMethod()
    {
        
    }  
    
    public void setTipo(String tipo) 
    {
        mTipo=tipo;
    }
    
    public void setNombre(String nombre) 
    {
        mNombre=nombre;
    }
    
    public String getNombre() 
    {
        return mNombre;
    }
    
    public void addLinea(String linea) 
    {
        mLineas.addElement(linea);
    }
    
    public void setLinea(int numLinea, String texto) 
    {
        mLineas.setElementAt(texto, numLinea);
    }
    
    public String getLinea(int i) 
    {
        return mLineas.elementAt(i).toString();
    }
    
    public Vector getLineas() 
    {
        return mLineas;
    }
    
    public abstract String toString(boolean forLotOfTC);
    public abstract TestMethod copy(String name);
    
    /**
     * @param contador
     */
    public void setId(int contador) {
        this.mId=contador;
    }
    public int getId() {
        return mId;
    }
    
    public void save(String sessionPath) throws IOException {
        FileOutputStream fo=new FileOutputStream(sessionPath + File.separator + getId() + "." + getExtension());
        ObjectOutputStream os=new ObjectOutputStream(fo);
        os.writeObject(this);
        fo.close();
    }

    /*public static TestMethod load(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(fileName);
        ObjectInputStream oi = new ObjectInputStream(fi);
        TestMethod result = (TestMethod) oi.readObject();
        fi.close();
        return result;
    }*/
    
    public static TestMethod load(String fileName, int sessionType) throws IOException, ClassNotFoundException {
        if (sessionType==TestMethod.JUNIT)
        	fileName+=".junit";
        else if (sessionType==TestMethod.MUT_JUNIT) 
        	fileName+=".mutjunit";
        else if (sessionType==TestMethod.STATES_JUNIT)
        	fileName+=".states";
        else fileName+=".grouped";
        FileInputStream fi = new FileInputStream(fileName);
        ObjectInputStream oi = new ObjectInputStream(fi);
        TestMethod result = (TestMethod) oi.readObject();
        fi.close();
        return result;
    }
    
    /**
     * @return
     */
    protected abstract String getExtension();
}
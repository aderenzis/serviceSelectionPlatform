package testooj3.domain.testfiles.mujava;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import testooj3.domain.TMujavaMethod;
import testooj3.domain.TestMethod;
import testooj3.domain.testfiles.MethodList;
import testooj3.domain.testfiles.TestFile;

public class MujavaFile extends TestFile 
{
    public MujavaFile(String className)
    {
        super(className);
        mHeader=new MujavaHeader(className);
    }
    
    protected String getMain() 
    {
        StringBuffer result=new StringBuffer("\tpublic static void main (String [] args) {\n");
        result.append("\t\t" + this.mClassName + " o=new " + mClassName + "();\n");
        for (int i=0; i<this.mMethodList.size(); i++) 
        {
            TMujavaMethod m=(TMujavaMethod) mMethodList.get(i);
            result.append("\t\to." + m.getNombre() + "();\n");
        }
        result.append("\t}\n");
        return result.toString();
    }
    
    public static TMujavaMethod load(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fi=new FileInputStream(fileName);
        ObjectInputStream oi=new ObjectInputStream(fi);
        TMujavaMethod result=(TMujavaMethod) oi.readObject();
        fi.close();
        return result;
    }
    
    /**
     * @param casos
     */
    public void setMethodList(Vector casos) {
        this.mMethodList=new MethodList();
        for (int i=0; i<casos.size(); i++)
            this.mMethodList.add((TestMethod) casos.get(i));
    }
}
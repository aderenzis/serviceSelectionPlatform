package testooj3.domain.tcmutation;

import java.io.FileOutputStream;

import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TestMethod;
import testooj3.domain.testfiles.TestFile;

public class TGroupedFile extends TestFile {

    public TGroupedFile(String className, Interface cut) {
        super(className);
        this.mHeader=new TGroupedHeader(className, cut);
    }

    protected String getMain() 
    {
        StringBuffer result=new StringBuffer("\tpublic static void main (String [] args) {\n");
        result.append("\t\t" + this.mClassName + " o=new " + mClassName + "();\n");
        for (int i=0; i<this.mMethodList.size(); i++) 
        {
            TGroupedTestMethod m=(TGroupedTestMethod) mMethodList.get(i);
            result.append("\t\to." + m.getNombre() + "();\n");
        }
        result.append("\t}\n");
        return result.toString();
    }

    public int saveTo(FileOutputStream f, int firstTestCase) throws Exception {
        String result = mHeader.toString() + "\n";
        f.write(result.getBytes());
        int totalSize=0, lastTestCase=0;
        int i=firstTestCase;
        while (i<=this.mNumberOfTestCases && totalSize<Configuration.getInstance().getSplitJUnitEvery()) {
            String fileName = mSessionPath + "\\" + i;
            TestMethod m = TestMethod.load(fileName, TestMethod.GROUPED);
            result = m.toString(false) + "\n";
            this.mMethodList.add(m);
            f.write(result.getBytes());
            totalSize+=result.getBytes().length;
            i++;
        }
        lastTestCase=i;
        result = getMain() + "\n}";
        f.write(result.getBytes());
        return lastTestCase;
    }

}

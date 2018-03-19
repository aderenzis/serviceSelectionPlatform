package testooj3.domain.testfiles.junit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TJUnitMethod;
import testooj3.domain.TestMethod;
import testooj3.domain.testfiles.TestFile;

/**
 * @author  andres
 */
public class JUnitFile extends TestFile {
    protected String extension;
	protected int sessionType;

    public JUnitFile(String className, Interface c) {
        super(className);
        mHeader = new JUnitHeader(className, c);
    }

    protected String getMain() {
        String result = "\tpublic static void main (String [] args) {\n";
        result += "\t\tjunit.swingui.TestRunner.run(" + mClassName
                + ".class);\n";
        result += "\n\t}\n";
        return result;
    }

    public int saveTo(FileOutputStream f, int firstTestCase) throws Exception {
        String result = mHeader.toString() + "\n";
        f.write(result.getBytes());
        int totalSize=0, lastTestCase=0;
        int i=firstTestCase;
        while (i<=this.mNumberOfTestCases && totalSize<Configuration.getInstance().getSplitJUnitEvery()) {
            String fileName = mSessionPath + "\\" + i;
            TestMethod m = TestMethod.load(fileName, this.sessionType);
            result = m.toString(false) + "\n";
            f.write(result.getBytes());
            totalSize+=result.getBytes().length;
            i++;
        }
        lastTestCase=i;
        result = getMain() + "\n}";
        f.write(result.getBytes());
        return lastTestCase;
    }

    /**
     * @param sessionType
     */
    public void setSessionType(int sessionType) {
    	this.sessionType=sessionType;
/*        switch (sessionType) {
        	case TestMethod.JUNIT : extension=".junit"; break;
        	case TestMethod.MUT_JUNIT : extension=".mutjunit"; break;
        	case TestMethod.GROUPED : extension=".grouped"; break;
        	default: extension=".junit";
        }        
*/    }
}
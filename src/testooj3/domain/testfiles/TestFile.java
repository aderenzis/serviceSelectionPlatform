package testooj3.domain.testfiles;

/**
 * @author  andres
 */
public abstract class TestFile {
    protected String mClassName;

    protected Header mHeader;

    protected MethodList mMethodList;

    protected String mSessionPath; // Path en el que se encuentran los ficheros con los casos de prueba serializados

    protected int mNumberOfTestCases;

    public TestFile(String name) {
        mClassName = name;
        mMethodList = new MethodList();
    }

    public String getFileName() {
        return mClassName.trim() + ".java";
    }

    public void setSessionPath(String sessionPath, int numberOfTestCases) {
        this.mSessionPath = sessionPath;
        this.mNumberOfTestCases = numberOfTestCases;
    }

    public String toString() {
        String result = mHeader.toString() + "\n" + mMethodList.toString()
                + getMain() + "}";
        return result;
    }

    protected abstract String getMain();
}
package testooj3.domain;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import testooj3.auxiliares.Auxi;

/**
 * @author  andres
 */
public class Configuration 
{
    protected String mWorkingPath="./testooj3/results/";
    protected static Configuration mYo=null;
    private String mMujavaRoot;
    private String mJUnitLocation;
    private int mSplitJUnitEvery=600000;
    private int mSplitMujavaFilesEvery=200000;
	private boolean randomTesting;
	private String additionalTestFilesPaths;
	private String classPath;
	private String wsdlsPath;
    
    protected Configuration() {
        try {
        	mWorkingPath=getCanonicalPath()+"/results/";
        	String fileName=getCanonicalPath() + "/testooj3setup.txt";
            FileInputStream f=new FileInputStream(fileName);
        	Properties pp = new Properties();  
            pp.load(f); 
            Enumeration ppn=pp.propertyNames();
            while (ppn.hasMoreElements()) 
            {
                String pn=ppn.nextElement().toString();
                if (pn.equals("WorkingPath")) 
                {
                    this.setWorkingPath(pp.getProperty(pn));
                } else if (pn.equals("JUnitLocation")) 
                {
                    this.setJUnitLocation(pp.getProperty(pn));
                } else if (pn.equals("MuJavaRoot")) 
                {
                	this.setMujavaRoot(pp.getProperty(pn));
                } else if (pn.startsWith("AdditionalTestFilesPaths")) 
                {
                    this.additionalTestFilesPaths=pp.getProperty(pn);
                } else if (pn.startsWith("SplitJUnitFilesEvery")) 
                {
                    this.setSplitJUnitEvery(Integer.parseInt(pp.getProperty(pn)));                 
                } else if (pn.equals("SplitMuJavaFilesEvery")) 
                {
                    this.setSplitMujavaFilesEvery(Integer.parseInt(pp.getProperty(pn)));
                } else if (pn.equals("ClassPath"))
            	{
                	this.setClassPath(pp.getProperty("ClassPath"));
	            } else if (pn.equals("WsdlsPath"))
	            	this.setWsdlsPath(pp.getProperty("WsdlsPath"));
	            }
            f.close(); 
        } catch (Exception e) {
        	try {
				mMujavaRoot=getCanonicalPath() + "/lib/mujava";
				mJUnitLocation=getCanonicalPath() + "/lib/junit.jar";
				additionalTestFilesPaths="";
				classPath="";
				wsdlsPath="";
			} catch (IOException e1) {
				mMujavaRoot="./lib/mujava";
				mJUnitLocation="./lib/junit3.8.1/junit.jar";
				additionalTestFilesPaths="";
				classPath="";
				wsdlsPath="";
			}
	        
        }
    }
    
	public void setClassPath(String classPath) {
		this.classPath=classPath;
	}

	public void setWsdlsPath(String classPath) {
		this.wsdlsPath=classPath;
	}
	
	public static String getCanonicalPath() throws IOException {
		String result=new File (".").getCanonicalPath();
		result=Auxi.substituteAll(result, "\\", "/");
		return result;
	}

	public void save() throws IOException {       
    	String fileName=getCanonicalPath() + "/testooj3setup.txt";
    	FileOutputStream f=new FileOutputStream(fileName);
        Properties pp=new Properties(); 
        pp.setProperty("WorkingPath", this.mWorkingPath);
        pp.setProperty("JUnitLocation", this.mJUnitLocation);
        pp.setProperty("MuJavaRoot", this.mMujavaRoot);
        pp.setProperty("AdditionalTestFilesPaths", this.additionalTestFilesPaths);
        pp.setProperty("SplitJUnitFilesEvery", ""+this.mSplitJUnitEvery);
        pp.setProperty("SplitMuJavaFilesEvery", ""+this.mSplitMujavaFilesEvery);
        pp.setProperty("ClassPath", this.classPath);
        pp.setProperty("WsdlsPath", this.wsdlsPath);
        pp.store(f, "testooj3 setup");
        f.close();
	}
    
    public static Configuration getInstance() 
    {
        if (mYo==null) 
            mYo=new Configuration();
        return mYo;
    }
    
    public String getPrimitivesFile()
    {
        return getWorkingPath() + "/required/primitives.ini";
    }
    
    public String getResultsPath()
    {
        return getWorkingPath();
    }
    
    public String getValuesFile()
    {
        return getWorkingPath() + "required/values.properties";
    }
    
    public String getCreatorsPath()
    {
       return getWorkingPath() + "creators/";
    }  
    
    public String getSerializedObjectsPath()
    {
        return getWorkingPath() + "serializedObjects/";
    }   
   
    public String getConstraintsPath()
    {
        return getWorkingPath() + "constraints/";
    }
    
    public String getTemplatesPath() 
    {
        return getWorkingPath() + "templates/";
    }
        
    public String getTempPath() 
    {
        return getWorkingPath() + "temp/";
    }

    /**
     * @return
     */
    public String getWorkingPath() {
    	String result=this.mWorkingPath;
    	result=Auxi.substituteAll(result, "\\", "/");
        result= result.endsWith("/") ? result : result + "/";
        return result;
    }

    /**
     * @param text
     */
    public void setJUnitLocation(String text) {
    	String result=Auxi.substituteAll(text, "\\", "/");
        this.mJUnitLocation=result;
    }

    /**
     * @param text
     */
    public void setMujavaRoot(String text) {
    	String result=Auxi.substituteAll(text, "\\", "/");
        this.mMujavaRoot=result;
    }  
    
    public String getJUnitLocation() {
    	String result=this.mJUnitLocation;
    	result=Auxi.substituteAll(result, "\\", "/");
        return result;
    }
    
    public String getMujavaRoot() {
    	String result=this.mMujavaRoot;
    	result=Auxi.substituteAll(result, "\\", "/");  	
    	result=result.endsWith("/") ? result : result + "/";
    	return result;
    }
    
    public void setWorkingPath(String workingPath) {
        mWorkingPath = workingPath;
    }

    /**
     * @return
     */
    public String getTestingClassesPackage() {
        return "results";
    }

    /**
     * @return
     */
    public String getCreatorsPackage() {
        return "results.creators";
    }

    /**
     * @return
     */
    public int getSplitJUnitEvery() {
        return this.mSplitJUnitEvery;
    }
    public void setSplitJUnitEvery(int splitJUnitEvery) {
        mSplitJUnitEvery = splitJUnitEvery;
    }

    /**
     * @param i
     */
    public void setSplitMujavaFilesEvery(int splitMujavaFilesEvery) {
        this.mSplitMujavaFilesEvery=splitMujavaFilesEvery;
    }
    
    public int getSplitMujavaFilesEvery() {
        return mSplitMujavaFilesEvery;
    }

	public void setRandomTesting(boolean b) {
		this.randomTesting=b;
	}

	public boolean isRandomTesting() {
		return randomTesting;
	}

	public String getAdditionalTestFilesPaths() {
		String result=this.additionalTestFilesPaths;
		result=Auxi.substituteAll(result, "\\", "/");
		return additionalTestFilesPaths;
	}

	public void setAdditionalTestFilesPaths(String additionalTestFilesPaths) {
		String result=additionalTestFilesPaths;
		result=Auxi.substituteAll(result, "\\", "/");
		this.additionalTestFilesPaths = result;
	}

	public String getClassPath() {
		String result=Auxi.substituteAll(classPath, "\\", "/");
		return result;
	}

	
	public String getWsdlsPath() {
		String result=Auxi.substituteAll(wsdlsPath, "\\", "/");
		return result;
	}
	
}
package testooj3.gui.guitesting;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import testooj3.domain.Configuration;
import testooj3.domain.Interface;
import testooj3.domain.TConstructor;
import testooj3.domain.TJUnitMethod;
import testooj3.domain.TJUnitMethodForState;
import testooj3.domain.Operation;
import testooj3.domain.TMujavaMethod;
import testooj3.domain.TMutJUnitMethod;
import testooj3.domain.TestCase;
import testooj3.domain.TestMethod;
import testooj3.domain.TestTemplate;
import testooj3.domain.algorithms.Algorithm;
import testooj3.domain.states.State;
import testooj3.persistence.Agente;

public class GuiTClass extends Interface {
	public GuiTClass() {
		super();
	}

	public GuiTClass(String name, boolean alsoInherited) throws ClassNotFoundException, Exception 
	{
		super(Class.forName(name), alsoInherited);
	}

	public GuiTClass(Class c, boolean alsoInherited)  
	{
		super(c, alsoInherited);
	}

	public GuiTClass(Interface c, boolean b) {
		super(c.mClase, b);
	}

	public void saveDescription() throws Exception 
	{
		Agente.mkdir(Configuration.getInstance().getConstraintsPath(), this.getName());  
		for (int i=0; i<this.getConstructors().size(); i++) 
		{
			TConstructor c=this.getConstructor(i);
			c.saveDescription(Configuration.getInstance().getConstraintsPath() + this.getName() + "\\");
		}    
		for (int i=0; i<this.getMethods().size(); i++) 
		{
			Operation m=this.getMethod(i);
			m.saveDescription(Configuration.getInstance().getConstraintsPath() + this.getName() + "\\");
		}
		String fileName=Configuration.getInstance().getConstraintsPath() + this.getName() + "\\" + "states" + ".states";
		FileOutputStream f=new FileOutputStream(fileName);
		Properties pp=new Properties(); 
		for (int i=0; i<mStates.size(); i++) 
		{
			State st=(State) this.mStates.get(i);
			pp.setProperty(st.getName(), st.getDescription());      
		}
		pp.store(f, "Description of the states");
		f.close();
		fileName=Configuration.getInstance().getConstraintsPath() + this.getName() + "\\" + "observer" + ".observer";
		f=new FileOutputStream(fileName);
		pp=new Properties(); 
		for (int i=0; i<this.mObserverMethods.size(); i++) 
		{
			Operation m=(Operation) this.mObserverMethods.get(i);
			pp.setProperty("observer_" + i, m.getId());
		}
		pp.store(f, "Observer methods");
		f.close();
		fileName=Configuration.getInstance().getConstraintsPath() + 
		this.getName() + "\\Invariants" + ".constraints";
		f=new FileOutputStream(fileName);
		f.write(this.mInvariants.getBytes());
		f.close();
	}  

	public String getTestCases(Algorithm algorithm,int[] numberOfTestCases, Vector tts) throws Exception {
		String sessionType="junit";
		Date dateTime=new Date();
		String sessionId=this.getName() + "_" + dateTime.getDate() + "-" + (1+dateTime.getMonth()) + "-" + 
		(1900+dateTime.getYear()) + "_" + dateTime.getHours() + "-" + dateTime.getMinutes();
		String sessionPath=Configuration.getInstance().getTempPath()+sessionId;
		new File(sessionPath).mkdir();
		int contador=0;
		for (int i = 0; i < tts.size(); i++) {
			GuiTestTemplate template = (GuiTestTemplate) tts.elementAt(i);
			algorithm.setTestTemplate(template);
			Vector ttc = algorithm.getTestCases(template);
			for (int j = 0; j < ttc.size(); j++) {
				GuiTestCase tc = (GuiTestCase) ttc.elementAt(j);
				contador++;
				TestMethod testCase=new GuiJUnitMethod(tc);
				testCase.setId(contador);
				testCase.save(sessionPath);
			}
		}
		String fileName=Configuration.getInstance().getTempPath() + "\\" + sessionId + ".session";
		FileOutputStream f=new FileOutputStream(fileName);
		Properties pp=new Properties(); 
		String cut=this.getName();
		pp.setProperty("CUT", cut);      
		pp.setProperty("SessionId", ""+sessionId);
		pp.setProperty("SessionType", sessionType);
		pp.setProperty("NumberOfTestCases", ""+contador);
		pp.setProperty("DateTime", ""+ dateTime.getTime());
		pp.store(f, "Session description");
		f.close();
		String statesDescription=this.getXMLStatesDescription();
		fileName=Configuration.getInstance().getTempPath() + "\\" + sessionId + "\\states_definition.xml";
		f=new FileOutputStream(fileName);
		f.write(statesDescription.getBytes());
		f.close();
		numberOfTestCases[0]=contador;
		return sessionPath;
	}
}

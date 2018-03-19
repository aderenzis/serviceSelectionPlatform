package testooj3.compatibility.wrappers;

import java.io.Serializable;
import java.util.Vector;
import testooj3.auxiliares.Auxi;


/**
 * @author  andres
 */
public class Wrapper implements Serializable {

	protected Vector<WMethod> methods;
	protected String originalClass;
	protected String candidateClass;
	protected String packageCandidate;
	protected String tipoWrapper; //Servicio = "S" / Componente = "C"
	protected String ImplementaIF; //""(vacio) si no implementa / "Nombre_Interface"  si sí implementa
	
	public Wrapper() {
		this.methods=new Vector<WMethod>();
	}

	public void addMethod(WMethod wm) {
		this.methods.add(wm);
	}

	public String getJava() {
		String r="";
		String origClassName = Auxi.recorta(this.originalClass);
		
		if (! this.originalClass.equals(origClassName)) {
			String thePackage = this.originalClass.substring(0,this.originalClass.indexOf(origClassName)-1);
			r="package " + thePackage + "; \n\n";
		}
		String impleIF="";
		if (ImplementaIF.equals("")){
			r+="public class " + origClassName +" {\n";
			}
		else{
			impleIF=" implements " + ImplementaIF;
			r+="public class " + origClassName + impleIF +" {\n";
		}
		
		if (tipoWrapper.equals("C")){
			r+="\tprotected " + this.candidateClass + " candidate";
			r+="=new " + this.candidateClass + "()";
		}
		else{
			//r+="\t"+this.candidateClass+" proxy = new "+this.candidateClass+"();";
			r+="\t"+this.candidateClass+" proxy;";
			r+="\n\n"; 
			r+=opProxyService();
		}
		
		r+="\n\n"; // declara el objeto de la clase a adaptar
		r+="\tpublic "+origClassName+"(){\n";
		r+="\t\tthis.proxy = new "+this.candidateClass+"();";
		r+="}\n";
		
		for(WMethod wm : this.methods) {
			if (!wm.originalMethod.getFinal())
				r+=wm.getJava(this.tipoWrapper);
		}
		r+="}\n";
		return r;
	}
	
	public void setCandidateClass(String candidateClass){
		this.candidateClass = candidateClass;
	}

	public void setOriginalClass(String originalClass){
		this.originalClass = originalClass;
	}
	
	public void setTipoWrapper(String tipoWrapper){
		this.tipoWrapper=tipoWrapper;
	}
	
	public void setImplementaIF(String nombreIF){
		this.ImplementaIF=nombreIF;
	}
	public void setNamePackageCandidate(String packageName){
		this.packageCandidate=packageName;
	}
	
	public String getNamePackageCandidate(){
		return this.packageCandidate;
	}
	
	//arma operaciones para el trabajo con proxy en RPC
	private String opProxyService(){
		String r="";
		r+="\tpublic void setProxy("+this.candidateClass+" proxy){\n";
		r+="\t\tthis.proxy = proxy;\n";
		r+="\t}\n\n";
		r+="\tpublic "+this.candidateClass+" getProxy(){\n";
		r+="\t\treturn this.proxy;\n";
		r+="\t}\n";
		return r;
	}
}

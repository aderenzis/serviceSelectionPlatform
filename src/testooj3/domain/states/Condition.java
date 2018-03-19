package testooj3.domain.states;

import java.io.Serializable;
import java.util.Vector;
import testooj3.domain.TParameter;

/**
 * @author  andres
 */
public class Condition implements Serializable {
	private Vector <TParameter> parameterValues;
	private Vector <DetailedDescription> detailedDescriptions;

	public Condition() {
		super();
		this.detailedDescriptions=new Vector<DetailedDescription>();
	}

	public void setParameterValues(Vector <TParameter> parameterValues) {
		this.parameterValues=parameterValues;
	}

	public void setDetailedDescriptions(Vector<DetailedDescription> detailedDescriptions) {
		this.detailedDescriptions=detailedDescriptions;
	}
	
	public void add(DetailedDescription dd) {
		this.detailedDescriptions.add(dd);
	}

	public Vector<DetailedDescription> getDetailedDescriptions() {
		if (detailedDescriptions==null) return new Vector<DetailedDescription>();
		return detailedDescriptions;
	}

	public Vector getParameterValues() {
		if (parameterValues==null) return new Vector();
		return parameterValues;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Condition))
			return false;
		Condition aux=(Condition) o;
		if (aux.detailedDescriptions==null && this.detailedDescriptions!=null) return false;
		if (aux.detailedDescriptions!=null && this.detailedDescriptions==null) return false;
		if (aux.detailedDescriptions!=null && this.detailedDescriptions!=null) {
			if (aux.detailedDescriptions.size()!=this.detailedDescriptions.size())
				return false;
			for (int i=0; i<aux.detailedDescriptions.size(); i++) {
				String auxDescription=aux.detailedDescriptions.get(i).toString();
				String thisDescription=this.detailedDescriptions.get(i).toString();
				if (!auxDescription.equals(thisDescription))
					return false;
			}
		}
		if (aux.parameterValues==null && this.parameterValues!=null) return false;
		if (aux.parameterValues!=null && this.parameterValues==null) return false;
		if (aux.parameterValues!=null && this.parameterValues!=null) {
			if (aux.parameterValues.size()!=this.parameterValues.size())
				return false;
			for (int i=0; i<aux.parameterValues.size(); i++) {
				String auxPar=aux.parameterValues.get(i).toString();
				String thisPar=this.parameterValues.get(i).toString();
				if (!auxPar.equals(thisPar))
					return false;
				
				
				//nuevo
				TParameter taux = (TParameter)aux.parameterValues.get(i);
				String auxValor = taux.getTestValue().toString();
				TParameter t = (TParameter)this.parameterValues.get(i);
				String thisValor = t.getTestValue().toString();
				if (!auxValor.equals(thisValor))
					return false;
			}
		}
		return true;
	}

	public String getText() {
		String r="", r1="";
		if (parameterValues!=null) {
			for (int i=0; i<parameterValues.size(); i++) {
				//String parValue="x" + (i+1) + "=" + parameterValues.get(i).toString();
				TParameter tp = (TParameter)parameterValues.get(i);
				String parValue = "x" + (i+1) + "=" + (String)tp.getTestValue().toString();
				r1+=parValue + " and ";
			}
		}
		if (r1.length()>0) r1=r1.substring(0, r1.length()-5);
		String r2="";
		if (detailedDescriptions!=null) {
			for (int i=0; i<detailedDescriptions.size(); i++) {
				DetailedDescription description=detailedDescriptions.get(i);
				r2+=description.getText() + " and ";
			}
		}
		r=r1.trim();
		if (r2.length()>0) r2=r2.substring(0, r2.length()-5);
		if (r.length()>0 && r2.length()>0) r+=" and ";
		r+=r2;
		return r;
	}

	public boolean containsDetailedDescription(DetailedDescription d) {
		for (int i=0; i<this.detailedDescriptions.size(); i++) {
			DetailedDescription auxi=this.detailedDescriptions.get(i);
			if (auxi.equals(d)) return true;
		}
		return false;
	}

	public void removeConditions(Vector<DetailedDescription> removableDetailedDescriptions) {
		for (int i=0; i<this.detailedDescriptions.size(); i++) {
			DetailedDescription d=this.detailedDescriptions.get(i);
			for (int j=0; j<removableDetailedDescriptions.size(); j++) {
				DetailedDescription auxi=removableDetailedDescriptions.get(j);
				if (d.equals(auxi)) {
					this.detailedDescriptions.remove(i);
					i=i-1;
				}
			}
		}
	}
	
	public String toString(){
		return this.getText();
	}

	//Se añaden DetailledDescriptions a partir de una cadena que se supone tiene una o varias condiciones
	public void add(String condStr) {
		String [] res1=condStr.split(" and ");
		for (int i=0;i<res1.length;i++){
			String [] res2=res1[i].split("=");
			this.add(new DetailedDescription(res2[0],res2[1]));
		}
	}
}

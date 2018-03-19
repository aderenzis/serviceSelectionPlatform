package testooj3.domain.states;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;
import testooj3.domain.TOperation;
import testooj3.domain.TParameter;

/**
 * @author  andres
 */
public class TransitionFromChoice implements ITransition, Serializable {
	protected State source;
	protected State target;
	//protected String condition;
	private Vector<Condition> conditions;
	
	public TransitionFromChoice(State source, State target) {
		this.source=source;
		this.target=target;
		this.conditions=new Vector<Condition>();
	}

	public TOperation getEvent() {
		return null;
	}

	public State getSource() {
		return this.source;
	}

	public State getTarget() {
		return this.target;
	}

/*	public void addToCondition(Vector parameterValues) {
		String newCondition="";
		for (int i=0; i<parameterValues.size(); i++) {
			String parName="x" + (i+1);
			newCondition=newCondition + parName + "=" + parameterValues.get(i).toString() + " and ";
		}
		if (newCondition.length()>0)
			newCondition=newCondition.substring(0, newCondition.length()-5);
		newCondition=newCondition.trim();
		if (this.condition.indexOf(newCondition)!=-1)
			return;
		if (this.condition.length()>0)
			this.condition=this.condition + " || " + newCondition;
		else
			this.condition=newCondition;
	}

	public void addToCondition(String text) {
		if (this.condition.indexOf(text)!=-1)
			return;
		if (this.condition.length()>0)
			this.condition=this.condition + " and " + text;
		else
			this.condition=text;		
	}*/
	
	public void addToCondition(Vector parameterValues) {
		String newCondition="";
		for (int i=0; i<parameterValues.size(); i++) {
			String parName="x" + (i+1);
			newCondition=newCondition + parName + "=" + parameterValues.get(i).toString() + " and ";
		}
		if (newCondition.length()>0)
			newCondition=newCondition.substring(0, newCondition.length()-5);
		newCondition=newCondition.trim();
	}
	
	public void addConditions(Vector parameterValues, Vector<DetailedDescription> detailedDescriptions) {
		Condition cn=new Condition();
		cn.setParameterValues(parameterValues);
		cn.setDetailedDescriptions(detailedDescriptions);
		if (!conditions.contains(cn))
			this.conditions.add(cn);
	}
	
	public void addConditions(Condition cn) {
		if (!conditions.contains(cn))
			this.conditions.add(cn);
	}
	
	public void appendConditions(Vector cndtns){
		for (Iterator it=cndtns.iterator();it.hasNext();)
			this.addConditions((Condition) it.next());
	}

	public DecisionTable getDecisionTable() {
		Vector<String> filas=new Vector<String>();
		for (int i=0; i<conditions.size(); i++) {
			Condition c=conditions.get(i);
			for (int j=0; j<c.getParameterValues().size(); j++) {
				String row="x" + (j+1);
				if (!filas.contains(row)) 
					filas.add(row);
			}
		}
		for (int i=0; i<conditions.size(); i++) {
			Condition c=conditions.get(i);
			for (int j=0; j<c.getDetailedDescriptions().size(); j++) {
				DetailedDescription description=c.getDetailedDescriptions().get(j);
				if (!filas.contains(description.getObserverMethodName())) 
					filas.add(description.getObserverMethodName());
			}
		}
		if (filas.size()==0) return null;
		DecisionTable result=new DecisionTable(this.source.getName(), filas, conditions.size());
		for (int i=0; i<conditions.size(); i++) {
			Condition c=conditions.get(i);
			for (int j=0; j<c.getParameterValues().size(); j++) {
				//String parValue=c.getParameterValues().get(j).toString();
				TParameter tValue = (TParameter)c.getParameterValues().get(j);
				String parValue = (String)tValue.getTestValue();
				String row="x" + (j+1);
				int rowNumber=result.getRowIndex(row);
				result.setValue(rowNumber, i, parValue);
			}
			for (int j=0; j<c.getDetailedDescriptions().size(); j++) {
				DetailedDescription row=c.getDetailedDescriptions().get(j);
				int rowNumber=result.getRowIndex(row.getObserverMethodName());
				result.setValue(rowNumber, i, row.getObserverMethodValue());
			}
			result.setTarget(i, this.target.getName());
		}
		return result;
	}

	public Vector<Condition> getConditions() {
		return conditions;
	}
	
	public void setConditions(Vector<Condition> conditions) {
		this.conditions=conditions;
	}

	public boolean containsDetailedDescription(DetailedDescription d) {
		for (int i=0; i<this.conditions.size(); i++) {
			Condition c=this.conditions.get(i);
			if (c.containsDetailedDescription(d))
				return true;
		}
		return false;
	}

	public void removeConditions(Vector<DetailedDescription> removableDetailedDescriptions) {
		for (int i=0; i<this.conditions.size(); i++) {
			Condition c=this.conditions.get(i);
			c.removeConditions(removableDetailedDescriptions);
		}
	}

	public void setSource(State st) {
		// TODO Auto-generated method stub
		
	}

	public void setTarget(State st) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean equals(Object ob){
		if (ob instanceof TransitionFromChoice){
			TransitionFromChoice tfc=(TransitionFromChoice)ob;
			return 	tfc.getSource().equals(this.getSource())&&
					tfc.getTarget().equals(this.getTarget());
		}else
			return false;
	}	
	
	public String toString(){
		return "\n"+this.source.getName()+"--"+this.conditions.toString()+"-->"+this.target.getName();
	}
	
	public String getId() {
		return this.getSource()+"-"+this.getEvent()+"-"+this.getTarget();
	}
}

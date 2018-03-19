package testooj3.domain.states;

import java.io.Serializable;
import java.util.Vector;

public class ChoiceState extends State implements Serializable {

	public ChoiceState() {
		super("", "");
	}

	public ChoiceState(String n) {
		super(n, "");
	}
	
	public DecisionTable getDecisionTable() {
		boolean isNull=true;
		Vector<String> rowTitles=new Vector<String>();
		int numberOfColumns=0;
		for (int i=0; i<this.outputTransitions.size(); i++) {
			TransitionFromChoice tfc=(TransitionFromChoice) this.outputTransitions.get(i);
			DecisionTable dt=tfc.getDecisionTable();
			if (dt!=null) {
				isNull=false;
				for (int j=0; j<dt.getRowTitles().size(); j++)
					if (!rowTitles.contains(dt.getRowTitles().get(j)))
						rowTitles.add(dt.getRowTitles().get(j));
				numberOfColumns+=dt.getNumberOfColumns();
			}
		}
		if (isNull) return null;
		DecisionTable result=new DecisionTable(this.getName(), rowTitles, numberOfColumns);
		int currentColumn=0;
		for (int i=0; i<this.outputTransitions.size(); i++) {
			TransitionFromChoice tfc=(TransitionFromChoice) this.outputTransitions.get(i);
			DecisionTable dt=tfc.getDecisionTable();
			for (int j=0; j<dt.getNumberOfRows(); j++) {
				String rowTitle=dt.getRowTitle(j);
				int rowIndex=result.getRowIndex(rowTitle);
				for (int k=0; k<dt.getNumberOfColumns(); k++) {
					String value=dt.getValueAt(j, k);
					result.setValue(rowIndex, currentColumn+k, value);
					result.setTarget(currentColumn+k, dt.getTarget(k));
				}
			}	
			currentColumn+=dt.getNumberOfColumns();
		}
		return isNull ? null : result;
	}
}

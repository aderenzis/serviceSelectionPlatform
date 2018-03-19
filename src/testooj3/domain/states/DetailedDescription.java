package testooj3.domain.states;

import java.io.Serializable;

/**
 * @author  andres
 */
public class DetailedDescription implements Serializable{

	private String observerMethodName;
	private String observerMethodValue;

	public DetailedDescription(String osName, String osValue) {
		this.observerMethodName=osName;
		this.observerMethodValue=osValue;
	}

	public String getObserverMethodName() {
		return observerMethodName;
	}

	public String getObserverMethodValue() {
		return observerMethodValue;
	}

	public String getText() {
		return this.observerMethodName+"="+this.observerMethodValue;
	}

	public boolean equals(Object o) {
		if (!(o instanceof DetailedDescription))
			return false;
		DetailedDescription d=(DetailedDescription) o;
		if (d.observerMethodName.equals(this.observerMethodName) && d.observerMethodValue.equals(this.observerMethodValue))
			return true;
		return false;
	}
}

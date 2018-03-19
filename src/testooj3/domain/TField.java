package testooj3.domain;

import java.lang.reflect.*;
import java.util.Vector;

/**
 * @author  andres
 */
public class TField 
{
	String mName;
	Class mType;
	protected Vector<TField> subFields;

	public TField(Field f)
	{
		this.mName=f.getName();
		this.mType=f.getType();
		this.subFields=new Vector<TField>();
        if (!f.getType().isPrimitive() && !f.getName().equals("java.lang.String") && f.getType().getDeclaredFields().length>0) {
        	for(int i=0; i<f.getType().getDeclaredFields().length; i++) {
        		Field subField=f.getType().getDeclaredFields()[i];
        		TField subTField=new TField(subField);
        		this.subFields.add(subTField);
        	}
        }
	}
	
	public void add(TField f) {
		this.subFields.add(f);
	}

	public String getName() {
		return mName;
	}
	
	public String getTypeName() {
		return this.mType.getName();
	}

	public Vector<TField> getSubFields() {
		return subFields;
	}

	public String getSignature() {
		return this.mName + " : " + this.getTypeName();
	}

	public TField findSubField(String parentFieldSignature, String fieldSignature) {
		for (TField tf : this.subFields) {
			String signature=parentFieldSignature + "#" + tf.getSignature();
			if (signature.equals(fieldSignature))
				return tf;
			else {
				TField auxi=tf.findSubField(signature, fieldSignature);
				if (auxi!=null)
					return auxi;
			}
		}
		return null;
	}
}
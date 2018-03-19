package OMS2; 

public class Message {
	OMS2.Message proxy;

	public void setProxy(OMS2.Message proxy){
		this.proxy = proxy;
	}

	public OMS2.Message getProxy(){
		return this.proxy;
	}


	public Message(){
		this.proxy = new OMS2.Message();}
	public java.lang.String getDateSent () {
		java.lang.String ret0;
		try{
			ret0=getProxy().getDateSent();
		}
		catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex); 
		}
		return ret0;
	}
	public java.lang.String getSender () {
		java.lang.String ret0;
		try{
			ret0=getProxy().getSender();
		}
		catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex); 
		}
		return ret0;
	}
	public void setContent (java.lang.String arg1) {
		try{
	getProxy().setContent(arg1);
		}
		catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex); 
		}
	}
	public boolean equals (java.lang.Object arg1) {
		boolean ret0 = true ;
		try{
			ret0=getProxy().equals(arg1);
		}
		catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex); 
		}
		return ret0;
	}
	public void setDateSent (java.lang.String arg1) {
		try{
	getProxy().setDateSent(arg1);
		}
		catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex); 
		}
	}
	public int hashCode () {
		int ret0;
		try{
			ret0=getProxy().hashCode();
		}
		catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex); 
		}
		return ret0;
	}
	public java.lang.String getContent () {
		java.lang.String ret0;
		try{
			ret0=getProxy().getContent();
		}
		catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex); 
		}
		return ret0;
	}
	public void setSender (java.lang.String arg1) {
		try{
	getProxy().setSender(arg1);
		}
		catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex); 
		}
	}
}

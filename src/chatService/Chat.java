/**
 * @author  andres
 */

package chatService; 

public class Chat {


    String user1 = "";
    String user2 = "";
    Boolean login1 = false;
    Boolean login2 = false;
    String msg1 = "";
    String msg2 = "";
    
 
    /* (non-Javadoc)
     * @see ChatIF#createUser(java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.Long,java.lang.Long,java.lang.Long)
     */
    public boolean createUser(
        java.lang.String firstname,
        java.lang.String lastname,
        java.lang.String email,
        java.lang.String username,
        java.lang.String password1,
        java.lang.String password2,
        java.lang.String country,
        long birthyear,
        long birthmonth,
        long birthday) {

    	if (username == "user1" && password1 == "psswrd1" && password2 == "psswrd1" && user1 == ""){
    		user1 = username;
    		return true;
    	}
    	
    	if (username == "user2" && password1 == "psswrd2" && password2 == "psswrd2" && user2 == ""){
    		user2 = username;
    		return true;
    	}
      return false;  
    }

    /* (non-Javadoc)
     * @see ChatIF#login(java.lang.String,java.lang.String)
     */
    public boolean login(java.lang.String username, java.lang.String password) {
 
       	if (username == user1 && password == "psswrd1" && !login1){
    		login1 = true;
    		return true;
    	}
 
       	if (username == user2 && password == "psswrd2" && !login2){
    		login2 = true;
    		return true;
    	}
        	
      return false;  
    
    }

    /* (non-Javadoc)
     * @see ChatIF#logout(java.lang.String,java.lang.String)
     */
    public boolean logout(java.lang.String username, java.lang.String password) {
    	 
       	if (username == user1 && password == "psswrd1" && login1){
    		login1 = false;
    		return true;
    	}
 
       	if (username == user2 && password == "psswrd2" && login2){
    		login2 = false;
    		return true;
    	}

       	return false;  
    
    }

    /* (non-Javadoc)
     * @see ChatIF#sendMessageTo(java.lang.String,java.lang.String,java.lang.String,java.lang.String)
     */
    public boolean sendMessageTo(java.lang.String username, java.lang.String password,
        java.lang.String receiver, java.lang.String message) {

       	if (username == user1 && password == "psswrd1" && login1 ){
    		msg2 = message;
    		return true;
    	}
 
       	if (username == user2 && password == "psswrd2" && login2 ){
    		msg1 = message;
    		return true;
    	}

       	return false;  
    }

    /* (non-Javadoc)
     * @see ChatIF#receiveNextMessage(java.lang.String,java.lang.String)
     */
    public java.lang.String receiveNextMessage(java.lang.String username, java.lang.String password) {
 
    	if (username == user1 && password == "psswrd1" && login1){
    		return msg1;
    	}

    	if (username == user2 && password == "psswrd2" && login2){
    		return msg2;
    	}
    	
      return "error";
    }
}

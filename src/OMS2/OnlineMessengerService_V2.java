/**
 * OnlineMessengerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package OMS2;


public class OnlineMessengerService_V2 {
    public boolean OMS_CreateUser(java.lang.String firstname, java.lang.String lastname, java.lang.String email, java.lang.String username, java.lang.String password1, java.lang.String password2, java.lang.String country, long birthyear, long birthmonth, long birthday) {return true;};
    public boolean OMS_Login(java.lang.String username, java.lang.String password) {return true;};
    public boolean OMS2_Logout(java.lang.String username, java.lang.String password) {return true;};
    public boolean OMS_EditUser(java.lang.String username, java.lang.String password, java.lang.String firstname, java.lang.String lastname, java.lang.String email, java.lang.String password1, java.lang.String password2, java.lang.String country, long birthyear, long birthmonth, long birthday) {return true;};
    public boolean OMS_DeleteUser(java.lang.String username, java.lang.String password) {return true;};
    
    public Message OMS_ReceiveMessage(java.lang.String username, java.lang.String password) {return new Message("","","");};   
    public boolean OMS_DeleteMessage(java.lang.String username, java.lang.String password) {return true;};
    public boolean OMS_PostMessage(java.lang.String username, java.lang.String password, java.lang.String to, java.lang.String message) {return true;};
    public boolean OMS_AddOfflineUser(java.lang.String username, java.lang.String password, java.lang.String offlineUser) {return true;};
    public boolean OMS_DeleteOfflineUser(java.lang.String username, java.lang.String password, java.lang.String offlineUser) {return true;};

    public java.lang.String[] OMS2_GetStatusOptions() {String[] result= new String[1]; return result;};
    public boolean OMS2_SetStatus(java.lang.String username, java.lang.String password, java.lang.String status, java.lang.String statusMessage) {return true;};
    
    public boolean OMS2_SetDisplayName(java.lang.String username, java.lang.String password, java.lang.String displayName) {return true;};
    
    public boolean OMS2_HideMeFromOtherUsers(java.lang.String username, java.lang.String password) {return true;};
    public boolean OMS2_UnHideMeFromOtherUsers(java.lang.String username, java.lang.String password) {return true;};
    public boolean OMS2_AllowUserToSeeMeWhenHidden(java.lang.String username, java.lang.String password, java.lang.String allowedUser) {return true;};
    public boolean OMS2_DisAllowUserToSeeMeWhenHidden(java.lang.String username, java.lang.String password, java.lang.String disAllowedUser) {return true;};
    public java.lang.String[] OMS2_GetUsersThatCanSeemeWhenHidden(java.lang.String username, java.lang.String password) {String[] result= new String[1]; return result;};
    public java.lang.String OMS2_CreateChat(java.lang.String username, java.lang.String password) {return " ";};
    public boolean OMS2_AddUserToChat(java.lang.String username, java.lang.String password, java.lang.String userToAdd, java.lang.String chatID) {return true;};
    public boolean OMS2_RemoveMeFromChat(java.lang.String username, java.lang.String password, java.lang.String chatID) {return true;};
    public java.lang.String[] OMS2_GetUsersFromChat(java.lang.String username, java.lang.String password, java.lang.String chatID) {String[] result= new String[1]; return result;};
    public java.lang.String[] OMS2_GetChats(java.lang.String username, java.lang.String password) {String[] result= new String[1]; return result;};
    public boolean OMS2_SendMessageToChat(java.lang.String username, java.lang.String password, java.lang.String chatID, java.lang.String message) {return true;};
    
    public boolean OMS2_DeleteChatMessage(java.lang.String username, java.lang.String password) {return true;};


    public java.lang.String OMS2_GetUserlist_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_GetOfflineUsers_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_GetOnlineUsers_simple(java.lang.String username, java.lang.String password) {return " ";};
}

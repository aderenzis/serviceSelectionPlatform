/**
 * OnlineMessengerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package OMS2_Simple;

public class OnlineMessengerService_Simple  {
    public java.lang.String OMS_CreateUser_simple(java.lang.String firstname, java.lang.String lastname, java.lang.String email, java.lang.String username, java.lang.String password1, java.lang.String password2, java.lang.String country, long birthyear, long birthmonth, long birthday) {return " ";};
    public java.lang.String OMS_Login_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_Logout_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS_EditUser_simple(java.lang.String username, java.lang.String password, java.lang.String firstname, java.lang.String lastname, java.lang.String email, java.lang.String password1, java.lang.String password2, java.lang.String country, long birthyear, long birthmonth, long birthday) {return " ";};
    public java.lang.String OMS_DeleteUser_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_GetUserlist_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS_ReceiveMessage_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS_DeleteMessage_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS_PostMessage_simple(java.lang.String username, java.lang.String password, java.lang.String to, java.lang.String message) {return " ";};
    public java.lang.String OMS_AddOfflineUser_simple(java.lang.String username, java.lang.String password, java.lang.String offlineUser) {return " ";};
    public java.lang.String OMS_DeleteOfflineUser_simple(java.lang.String username, java.lang.String password, java.lang.String offlineUser) {return " ";};
    public java.lang.String OMS2_GetUserInfo_simple(java.lang.String username, java.lang.String password, java.lang.String usernameForInfo) {return " ";};
    public java.lang.String OMS2_FindUser_simple(java.lang.String criteria) {return " ";};
    public java.lang.String OMS2_GetOfflineUsers_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_GetOnlineUsers_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_GetStatusOptions_simple() {return " ";};
    public java.lang.String OMS2_SetStatus_simple(java.lang.String username, java.lang.String password, java.lang.String status, java.lang.String statusMessage) {return " ";};
    public java.lang.String OMS2_GetStatus_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_SetDisplayName_simple(java.lang.String username, java.lang.String password, java.lang.String displayName) {return " ";};
    public java.lang.String OMS2_GetDisplayName_simple(java.lang.String username) {return " ";};
    public java.lang.String OMS2_HideMeFromOtherUsers_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_UnHideMeFromOtherUsers_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_AllowUserToSeeMeWhenHidden_simple(java.lang.String username, java.lang.String password, java.lang.String allowedUser) {return " ";};
    public java.lang.String OMS2_DisAllowUserToSeeMeWhenHidden_simple(java.lang.String username, java.lang.String password, java.lang.String disAllowedUser) {return " ";};
    public java.lang.String OMS2_GetUsersThatCanSeemeWhenHidden_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_CreateChat_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_AddUserToChat_simple(java.lang.String username, java.lang.String password, java.lang.String userToAdd, java.lang.String chatID) {return " ";};
    public java.lang.String OMS2_RemoveMeFromChat_simple(java.lang.String username, java.lang.String password, java.lang.String chatID) {return " ";};
    public java.lang.String OMS2_GetUsersFromChat_simple(java.lang.String username, java.lang.String password, java.lang.String chatID) {return " ";};
    public java.lang.String OMS2_GetChats_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_SendMessageToChat_simple(java.lang.String username, java.lang.String password, java.lang.String chatID, java.lang.String message) {return " ";};
    public java.lang.String OMS2_ReceiveChatMessage_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_DeleteChatMessage_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_GetNewMessageStatus_simple(java.lang.String username, java.lang.String password) {return " ";};
    public java.lang.String OMS2_GetServerTime_simple() {return " ";};
}

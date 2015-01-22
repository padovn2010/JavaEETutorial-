/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.duongpt.ejbwebclient;

import com.duongpt.ejb.AccountDaoBean;
import com.duongpt.javaeewebprojekt.Account;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Pado
 * Define client of web which request EJB named AccountDaoBean - this client is Controller between JSF page and Data model - Account.java
 * Every transactions execute via EJB???
 */
@Named
@SessionScoped
public class AccountController implements Serializable {
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private String dob;
    
    //variable in edit function in userinfo page
    private boolean editModePW;
    private boolean editModeFN;
    private boolean editModeEM;
    private boolean editModeDOB;
    
    private static String storeUser;
    
    @EJB  //@EJB allows an instance of EJB is injected
    private AccountDaoBean accountDaoBean;
    private Account account;
    
    
    //Constructor
    public AccountController() {
        account = new Account();
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public String addAccount(){
        String retVal = "confirmation";
        
        try{
            // Get info when user registers new account
            getAccountInfo();
            
            // Add this info to DB by calling instance of EJB
            accountDaoBean.addAccount(account);
        } catch (
                IllegalStateException |
                        SecurityException |
                        EJBException ex) {
            if (ex instanceof EJBException){
                retVal = "error_tran";
                ex.printStackTrace();
            } else {
                ex.printStackTrace();
                retVal = "error";
            }
        }
        
        return retVal;
    }
    
    private void getAccountInfo() {
        if (account == null){
            account = new Account();
        }
        
        account.setuserName(getUserName());
        account.setpassword(getPassword());
        account.setFullName(getFullName());
        account.setEmail(getEmail());
        account.setDob(getDob());
    }
    
    public String checkLogIn(){
        String retVal = "userinfo";
        
        if (accountDaoBean.checkAccount(getUserName(),getPassword())){
        
            //save loggedn userName to
            storeUser = getUserName();
            //Get valid User Info to present in userinfo page
            account = accountDaoBean.getAccount(getUserName());
            userName=account.getuserName();
            password=account.getpassword();
            fullName = account.getFullName();
            email = account.getEmail();
            dob = account.getDob();
        
        } else {
            retVal = "error_login";
        }
        return retVal;
    }
    
    
    /**
     * Edit mode in user_info page
     */
    public void editPW(){
        loadInfoEdit();
        this.editModePW = true;
    }
    
    public void savePW(){
        editInfo();
        this.editModePW = false;
    }
    
    public void editFN(){
        loadInfoEdit();
        this.editModeFN = true;
    }
    
    public void saveFN(){
        editInfo();
        this.editModeFN = false;
    }
    
    public void editEM(){
        loadInfoEdit();
        this.editModeEM = true;
    }
    public void saveEM(){
        editInfo();
        this.editModeEM = false;
    }
    
    public void editDOB(){
        loadInfoEdit();
        this.editModeDOB = true;
    }
    public void saveDOB(){
        editInfo();
        this.editModeDOB = false;
    }
    /**
     * load present user's data to keep data in fields of user info pages
     */
    private void loadInfoEdit(){
        System.out.println("Edit() method _ StoreUser value::::::" + storeUser);
        account = accountDaoBean.getAccount(storeUser);
        userName = account.getuserName();
        password = account.getpassword();
        fullName = account.getFullName();
        email = account.getEmail();
        dob = account.getDob();
    }
    
    private void editInfo(){
        
        System.out.println("Save() method _ StoreUser value::::::" + storeUser);
        account = accountDaoBean.getAccount(storeUser);
        
        userName = account.getuserName();
        account.setpassword(getPassword());
        account.setuserName(getUserName());
        account.setEmail(getEmail());
        account.setFullName(getFullName());
        account.setDob(getDob());
        
        accountDaoBean.updateAccount(account);
    }
    
    public String moveToRegister(){
        userName = "";
        email="";
        dob="";
        fullName="";
        return "register";
    } 
    
    
    /**
     * Setter & Getter methods
     * @return
     */
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getDob() {
        return dob;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public boolean isEditModePW() {
        return editModePW;
    }
    
    public void setEditModePW(boolean editModePW) {
        this.editModePW = editModePW;
    }
    
    public boolean isEditModeFN() {
        return editModeFN;
    }
    
    public void setEditModeFN(boolean editModeFN) {
        this.editModeFN = editModeFN;
    }
    
    public boolean isEditModeEM() {
        return editModeEM;
    }
    
    public void setEditModeEM(boolean editModeEM) {
        this.editModeEM = editModeEM;
    }
    
    public boolean isEditModeDOB() {
        return editModeDOB;
    }
    
    public void setEditModeDOB(boolean editModeDOB) {
        this.editModeDOB = editModeDOB;
    }    

}


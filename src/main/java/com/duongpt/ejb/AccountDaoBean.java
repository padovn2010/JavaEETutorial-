package com.duongpt.ejb;

import com.duongpt.javaeewebprojekt.Account;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pado
 */

@Stateful
@LocalBean //don't need remote interface implementation
public class AccountDaoBean {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public void addAccount(Account account){
        if (account.getuserName() == null){
            addNewAccount(account);
        } else {
            addNewAccount(account);
            //updateAccount(account);
        }
    }
    
    private void addNewAccount(Account account) {
        entityManager.persist(account);
    }
    
    public boolean checkAccount(String userName, String password){
        Account account = getAccount(userName);
        if (account != null && account.getpassword().equals(password)) {
            return  true;
        }
        else {
            return false;
        }
    }
    
    public void updateAccount(Account account) {
        entityManager.merge(account);
    }
    
    public Account getAccount(String userAccount){
        Account account;
        account =entityManager.find(Account.class, userAccount);
        
        return account;
    }
    
    public void deleteAccount(Account account){
        entityManager.remove(account);
    }
    
}

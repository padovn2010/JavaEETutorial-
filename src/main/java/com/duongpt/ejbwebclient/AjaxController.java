/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.duongpt.ejbwebclient;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.duongpt.ejb.AjaxDaoBean;
import javax.ejb.EJB;

/**
 *
 * @author Pado
 * define EJB for Ajax
 *
 */
@Named
@SessionScoped
public class AjaxController implements Serializable{
    
    private final static String NO_EXISTED_USER = "Cannot find this user";
    
    private String searchTxt;
    private String st;
    private List<String> listUsers;
    
    
    @EJB
    private AjaxDaoBean ajaxDaoBean;
    
    public  AjaxController(){
        searchTxt = "";
    }
    
    public String hintUser(){
        
        st = getSearchTxt();
        
        switch (st) {
            case "d":
                System.out.println("IF return TRUE_St valuuuuuuuuuuuuuuuuue:......." + st);
                return "duongpt2010";
            case "h":
                return "hongtran";
            case "pa":
                return "padovn2010";
            case "p" :
                return "phamduong";
            case "ph" :
                return "phamduong";
            default:
                System.out.println("else_St valuuuuuuuuuuuuuuuuue:......." + st);
                return "no hints";
        }
    }
    
    public String searchUsers(){
        
        st = getSearchTxt();
        
        listUsers = ajaxDaoBean.getUserNameList();
        
        System.out.println( "VALUE OF listUsers: " + listUsers);
        
        if (listUsers.contains(st)){
            return st;
        } else {
            return NO_EXISTED_USER;
        }
    }
    
    
    /**
     * Getter and Setter methods
     * @return
     */
    
    public String getSearchTxt() {
        return searchTxt;
    }
    
    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }
    
}

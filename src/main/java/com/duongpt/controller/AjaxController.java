package com.duongpt.controller;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.duongpt.database.AjaxDao;
import javax.ejb.EJB;

/**
 *
 * @author Pado
 * Define EJB for Ajax
 */
@Named
@SessionScoped
public class AjaxController implements Serializable{
    
    private final static String NO_EXISTED_USER = "Cannot find this user";
    
    private String searchTxt;
    private String st;
    private List<String> listUsers;
    
    
    @EJB
    private AjaxDao ajaxDaoBean;
    
    public  AjaxController(){
        searchTxt = "";
    }
    
    public String hintUser(){
        
        st = getSearchTxt();
        
        switch (st) {
            case "d":
                System.out.println("If return True" + st);
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
                System.out.println("Default value: " + st);
                return "no hints";
        }
    }
    
    public String searchUsers(){
        
        st = getSearchTxt();
        
        listUsers = ajaxDaoBean.getUserNameList();
        
        System.out.println( "Value of listUsers: " + listUsers);
        
        if (listUsers.contains(st)){
            return st;
        } else {
            return NO_EXISTED_USER;
        }
    }
    
    public String getSearchTxt() {
        return searchTxt;
    }
    
    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }
    
}

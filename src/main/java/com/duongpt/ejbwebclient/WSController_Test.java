/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.duongpt.ejbwebclient;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Pado
 */
@Named (value = "wscontroller")
@SessionScoped
public class WSController_Test implements Serializable{
    
    private String para01;
    private String para02;
    
    
    public  String subFunc(){
        para01 = getPara01();
        
        return para01 + "TAIL";
                
    }
    
    
    public String getPara01() {
        return para01;
    }
    
    public void setPara01(String para01) {
        this.para01 = para01;
    }
    
    public String getPara02() {
        return para02;
    }
    
    public void setPara02(String para02) {
        this.para02 = para02;
    }
   
}

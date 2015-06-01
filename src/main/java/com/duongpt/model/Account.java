package com.duongpt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Pado
 * Define JPA Entity
 */

@Entity
@Table(name = "USERACCOUNT")
public class Account implements Serializable {
        
    //primary key
    @Id
    @Column(name = "USER_NAME")
    private String userName;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "FULL_NAME")
    private String fullName;
    
    private String email;
    
    private String dob;
    
    public String getuserName() {
        return userName;
    }
    
    public void setuserName(String userName) {
        this.userName = userName;
    }
    
    public String getpassword() {
        return password;
    }
    
    public void setpassword(String password) {
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

}

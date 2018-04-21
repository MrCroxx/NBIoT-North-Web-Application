package com.croxx.nbiot.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @Column
    @GeneratedValue
    private Long id;
    @Column(unique = true,nullable = false,length = 128)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,length = 64)
    private String name;
    @Column(nullable = false)
    private Date lastPasswordResetDate;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public User(){

    }

    public User(String email,String password,String name){
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
        this.setLastPasswordResetDate(new Date());
    }

    /*    Getters & Setters     */

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

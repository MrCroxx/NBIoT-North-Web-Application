package com.croxx.nbiot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Id
    @Column
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false, length = 128)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 64)
    private String name;
    @Column(nullable = false)
    private Date lastPasswordResetDate;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Device> devices;

    public User() {

    }

    public User(@NotNull String email,@NotNull String password,@NotNull String name) {
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
        this.setLastPasswordResetDate(new Date());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            if (this.getId().equals(((User)obj).getId())){
                return true;
            }
        }
        return super.equals(obj);
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

    public List<Device> getDevices() {
        return devices;
    }
}

package com.croxx.nbiot.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ReqNewUser {
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;

    public ReqNewUser(){
        super();
    }

    public ReqNewUser(@NotNull String email, @NotNull String password, @NotNull String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    /*    Getters & Setters     */

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
}

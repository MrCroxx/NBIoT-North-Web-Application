package com.croxx.nbiot.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ReqJwtUser implements Serializable {

    @Email
    @NotNull
    private String username;
    @NotNull
    private String password;

    public ReqJwtUser() {
        super();
    }

    public ReqJwtUser(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

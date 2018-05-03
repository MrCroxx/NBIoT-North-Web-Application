package com.croxx.nbiot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JwtUser implements UserDetails {
    private final Long id;
    private final String email;
    private final String name;
    private final String password;
    private final Date lastPasswordResetDate;
    private final List<String> roles;


    public JwtUser(Long id, String email, String password, String name, Date lastPasswordResetDate, List<String> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.roles = roles;
    }
    public JwtUser(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.lastPasswordResetDate = user.getLastPasswordResetDate();
        this.roles = user.getRoles();
    }

    //返回分配给用户的角色列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        for (String role : this.roles) {
            auths.add(new SimpleGrantedAuthority(role));
        }
        return auths;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 密码是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

}

package com.croxx.nbiot.response;

import java.io.Serializable;

public class ResJwtAccessToken implements Serializable {

    private static final long serialVersionUID = -5242476196827718051L;
    private String access_token;
    private long expiration;

    public ResJwtAccessToken() {
    }

    public ResJwtAccessToken(String access_token, long expiration) {
        this.access_token = access_token;
        this.expiration = expiration;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
}

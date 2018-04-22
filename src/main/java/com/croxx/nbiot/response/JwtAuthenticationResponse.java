package com.croxx.nbiot.response;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -5242476196827718051L;
    private final String access_token;

    public JwtAuthenticationResponse(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return this.access_token;
    }
}

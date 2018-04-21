package com.croxx.nbiot.model;

public final class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(),user.getEmail(),user.getPassword(),user.getName(),user.getLastPasswordResetDate(),user.getRoles());
    }
}

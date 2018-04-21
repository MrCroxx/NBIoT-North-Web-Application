package com.croxx.nbiot.service;

import com.croxx.nbiot.model.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}


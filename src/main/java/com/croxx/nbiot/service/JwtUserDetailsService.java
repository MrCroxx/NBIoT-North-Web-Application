package com.croxx.nbiot.service;

import com.croxx.nbiot.model.JwtUserFactory;
import com.croxx.nbiot.model.User;
import com.croxx.nbiot.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
            //throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}

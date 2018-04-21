package com.croxx.nbiot.controller;

import com.croxx.nbiot.jsoncode.StatusCode;
import com.croxx.nbiot.jsonmsg.DefaultMSG;
import com.croxx.nbiot.model.Role;
import com.croxx.nbiot.model.User;
import com.croxx.nbiot.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@ApiIgnore
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public DefaultMSG signup(@RequestParam("email") String email, @RequestParam("password") String passwrod, @RequestParam("name") String name) {
        User user = new User(email, passwrod, name);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<String> roles = new ArrayList<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);
        return new DefaultMSG(StatusCode.STATUS_SUCCESS, null);
    }
}

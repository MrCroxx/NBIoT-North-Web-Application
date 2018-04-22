package com.croxx.nbiot.controller;

import com.croxx.nbiot.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user")
@ApiIgnore
public class UserController {

    @Autowired
    private UserRepository userRepository;

}

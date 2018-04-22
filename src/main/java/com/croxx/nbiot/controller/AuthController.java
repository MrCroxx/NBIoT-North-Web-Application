package com.croxx.nbiot.controller;

import com.croxx.nbiot.request.JwtAuthenticationRequest;
import com.croxx.nbiot.response.JwtAuthenticationResponse;
import com.croxx.nbiot.model.User;
import com.croxx.nbiot.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private AuthService authService;


    @ApiOperation(value = "jwt登录", notes = "通过username、password获取access_token")
    @RequestMapping(value = "${jwt.route.authentication.auth}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtAuthenticationRequest authenticationRequest, BindingResult bindingResult) throws AuthenticationException {
        if(bindingResult.hasErrors()){
            Map<String,Object>map = new HashMap<>();
            for(FieldError fieldError:bindingResult.getFieldErrors()){
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Map<String ,Object> >(map,HttpStatus.BAD_REQUEST);
        }
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword()); // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }


    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User user) throws AuthenticationException {
        User addedUser = authService.register(user);
        if(addedUser==null){
            return ResponseEntity.ok().body(null);
        }else {
            return ResponseEntity.ok().body(null);
        }
    }
}

package com.croxx.nbiot.controller.v1;

import com.croxx.nbiot.model.User;
import com.croxx.nbiot.request.ReqJwtUser;
import com.croxx.nbiot.request.ReqNewUser;
import com.croxx.nbiot.response.ResJwtAccessToken;
import com.croxx.nbiot.response.ResMsg;
import com.croxx.nbiot.service.AuthService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/jwt")
public class AuthControllerV1 {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private AuthService authService;


    @ApiOperation(value = "JWT登录鉴权", notes = "通过username(即注册用户的email字段)、password获取access_token(此路由不需要Authorization参数)")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResMsg<ResJwtAccessToken>> createAuthenticationToken(@Valid @RequestBody ReqJwtUser authenticationRequest, BindingResult bindingResult) throws AuthenticationException {
        if (bindingResult.hasErrors()) {
            List<String> msgs = ResMsg.getBindErrorsMessage(bindingResult);
            return ResponseEntity.badRequest().body(new ResMsg<ResJwtAccessToken>(msgs));
        }
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword()); // Return the token
        if (token != null) {
            return ResponseEntity.ok(new ResMsg<ResJwtAccessToken>(new ResJwtAccessToken(token, authService.getExpiration()), ResMsg.MSG_SUCCESS));
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResMsg<>(ResMsg.MSG_FORBIDDEN));
        }
    }

    @ApiOperation(value = "JWT刷新Token", notes = "使用过期的token获取刷新的token")
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResMsg<ResJwtAccessToken>> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(new ResMsg<ResJwtAccessToken>(ResMsg.MSG_DATA_ILLEGAL));
        } else {
            return ResponseEntity.ok(new ResMsg<ResJwtAccessToken>(new ResJwtAccessToken(refreshedToken, authService.getExpiration()), ResMsg.MSG_SUCCESS));
        }
    }

    @ApiOperation(value = "用户注册", notes = "用户注册API")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResMsg> register(@Valid @RequestBody ReqNewUser reqUser, BindingResult bindingResult) throws AuthenticationException {
        if (bindingResult.hasErrors()) {
            List<String> msgs = ResMsg.getBindErrorsMessage(bindingResult);
            return ResponseEntity.badRequest().body(new ResMsg(msgs));
        }
        User user = new User(reqUser.getEmail(), reqUser.getPassword(), reqUser.getName());
        User addedUser = authService.register(user);
        if (addedUser == null) {
            return ResponseEntity.badRequest().body(new ResMsg("email already exists"));
        } else {
            return ResponseEntity.ok(new ResMsg(ResMsg.MSG_SUCCESS));
        }
    }
}

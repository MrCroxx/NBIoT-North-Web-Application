package com.croxx.nbiot.controller;

import com.croxx.nbiot.model.JwtUser;
import com.croxx.nbiot.model.User;
import com.croxx.nbiot.model.UserRepository;
import com.croxx.nbiot.response.ResMsg;
import com.croxx.nbiot.service.NBIoTDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("device")
public class DeviceController {

    @Autowired
    private NBIoTDeviceService nbIoTDeviceService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add/{deviceId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResMsg> add(@PathVariable("deviceId") String deviceId) {
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(jwtUser.getUsername());
        if (nbIoTDeviceService.RegistDevice(deviceId, user)) {
            return ResponseEntity.ok(new ResMsg(ResMsg.MSG_SUCCESS));
        } else {
            return ResponseEntity.badRequest().body(new ResMsg(ResMsg.MSG_DATA_ILLEGAL));
        }
    }
}

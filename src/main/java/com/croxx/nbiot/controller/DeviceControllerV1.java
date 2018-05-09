package com.croxx.nbiot.controller;

import com.croxx.nbiot.model.Device;
import com.croxx.nbiot.model.JwtUser;
import com.croxx.nbiot.model.User;
import com.croxx.nbiot.model.UserRepository;
import com.croxx.nbiot.request.ReqDevice;
import com.croxx.nbiot.request.ReqNewDevice;
import com.croxx.nbiot.response.ResDevice;
import com.croxx.nbiot.response.ResJwtAccessToken;
import com.croxx.nbiot.response.ResMsg;
import com.croxx.nbiot.service.NBIoTDeviceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/v1/device")
public class DeviceControllerV1 {

    @Autowired
    private NBIoTDeviceService nbIoTDeviceService;
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "注册NBIoT设备")
    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public ResponseEntity<ResMsg<ResDevice>> manage_register(@Valid @RequestBody ReqNewDevice reqNewDevice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> msgs = ResMsg.getBindErrorsMessage(bindingResult);
            return ResponseEntity.badRequest().body(new ResMsg<ResDevice>(msgs));
        }
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(jwtUser.getUsername());
        String status = nbIoTDeviceService.registDevice(reqNewDevice, user);
        if (!(status.equals(NBIoTDeviceService.REGISTER_DEVICEID_EXISTS) || status.equals(NBIoTDeviceService.REGISTER_UNKNOWN_ERROR))) {
            return ResponseEntity.ok(new ResMsg<ResDevice>(ResMsg.MSG_SUCCESS, status));
        } else {
            return ResponseEntity.badRequest().body(new ResMsg<ResDevice>(status));
        }
    }

    @ApiOperation(value = "删除NBIoT设备")
    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public ResponseEntity<ResMsg> manage_delete(@Valid @RequestBody ReqDevice reqDevice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> msgs = ResMsg.getBindErrorsMessage(bindingResult);
            return ResponseEntity.badRequest().body(new ResMsg<ResJwtAccessToken>(msgs));
        }
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(jwtUser.getUsername());
        String status = nbIoTDeviceService.deleteDevice(reqDevice.getDeviceId(), user);
        if (status.equals(NBIoTDeviceService.DELETE_SUCCESS)) {
            return ResponseEntity.ok(new ResMsg(ResMsg.MSG_SUCCESS));
        } else {
            return ResponseEntity.badRequest().body(new ResMsg(status));
        }
    }

    @ApiOperation(value = "查询注册过的NBIoT设备")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public ResponseEntity<ResMsg<List<ResDevice>>> queryDevices() {
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(jwtUser.getUsername());
        List<ResDevice> resDevices = nbIoTDeviceService.getResDevicesByOwner(user);
        return ResponseEntity.ok(new ResMsg<List<ResDevice>>(resDevices, ResMsg.MSG_SUCCESS));
    }

    @ApiOperation(value = "查询NBIoT设备详细信息")
    @RequestMapping(value = "/detail/{deviceId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public ResponseEntity<ResMsg<ResDevice>> detail(@PathVariable String deviceId) {
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(jwtUser.getUsername());
        ResDevice resDevice = nbIoTDeviceService.getResDeviceByDeviceId(deviceId, user);
        if (resDevice == null) {
            return ResponseEntity.badRequest().body(new ResMsg<ResDevice>(NBIoTDeviceService.DETAIL_NOT_FOUND_OR_DENIED));
        } else {
            return ResponseEntity.ok(new ResMsg<ResDevice>(resDevice, ResMsg.MSG_SUCCESS));
        }
    }

}

package com.croxx.nbiot.controller.v1;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/v1/device")
@Validated
public class DeviceControllerV1 {

    @Autowired
    private NBIoTDeviceService nbIoTDeviceService;
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "注册NBIoT设备")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public ResponseEntity<ResMsg<ResDevice>> manage_register(@Valid @RequestBody ReqNewDevice reqNewDevice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> msgs = ResMsg.getBindErrorsMessage(bindingResult);
            return ResponseEntity.badRequest().body(new ResMsg<ResDevice>(msgs));
        }
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(jwtUser.getUsername());
        ResMsg<ResDevice> resMsg = nbIoTDeviceService.registDevice(reqNewDevice, user);
        if (resMsg.getContent() != null) {
            return ResponseEntity.ok(resMsg);
        } else {
            return ResponseEntity.badRequest().body(resMsg);
        }
    }

    @ApiOperation(value = "删除NBIoT设备")
    @RequestMapping(value = "/{deviceId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public ResponseEntity<ResMsg> manage_delete(@PathVariable @Size(min = 6, max = 48) String deviceId) {
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(jwtUser.getUsername());
        String status = nbIoTDeviceService.deleteDevice(deviceId, user);
        if (status.equals(NBIoTDeviceService.DELETE_SUCCESS)) {
            return ResponseEntity.ok(new ResMsg(ResMsg.MSG_SUCCESS));
        } else {
            return ResponseEntity.badRequest().body(new ResMsg(status));
        }
    }

    @ApiOperation(value = "查询注册过的NBIoT设备")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public ResponseEntity<ResMsg<List<ResDevice>>> queryDevices() {
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(jwtUser.getUsername());
        List<ResDevice> resDevices = nbIoTDeviceService.getResDevicesByOwner(user);
        return ResponseEntity.ok(new ResMsg<List<ResDevice>>(resDevices, ResMsg.MSG_SUCCESS));
    }

    @ApiOperation(value = "查询NBIoT设备详细信息")
    @RequestMapping(value = "/{deviceId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public ResponseEntity<ResMsg<ResDevice>> detail(@PathVariable @Size(min = 6, max = 48) String deviceId) {

        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(jwtUser.getUsername());
        ResDevice resDevice = nbIoTDeviceService.getResDeviceByDeviceId(deviceId, user);
        if (resDevice == null) {
            return ResponseEntity.badRequest().body(new ResMsg<ResDevice>(NBIoTDeviceService.DETAIL_NOT_FOUND_OR_DENIED));
        } else {
            return ResponseEntity.ok(new ResMsg<ResDevice>(resDevice, ResMsg.MSG_SUCCESS));
        }
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResMsg> handleResourceNotFoundException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        return ResponseEntity.badRequest().body(new ResMsg(strBuilder.toString()));
    }

}

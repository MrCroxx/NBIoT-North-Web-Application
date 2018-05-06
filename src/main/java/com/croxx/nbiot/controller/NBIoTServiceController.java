package com.croxx.nbiot.controller;

import com.croxx.nbiot.response.ResMsg;
import com.croxx.nbiot.service.NBIoTDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nbiot")
public class NBIoTServiceController {

    @RequestMapping(value = "/deviceDataUpdate", method = RequestMethod.POST)
    //public ResponseEntity<ResMsg> deviceDataUpdate(@RequestBody ReqNBIoTServiceNotify<DeviceServiceData> reqNBIoTService) {
    public ResponseEntity<ResMsg> deviceDataUpdate(@RequestBody String str) {
        Logger logger = LoggerFactory.getLogger(NBIoTDeviceService.class);
        /*
        String msg = "";
        msg += reqNBIoTService.getDeviceId();
        msg += reqNBIoTService.getGatewayId();
        msg += reqNBIoTService.getNotifyType();
        msg += reqNBIoTService.getRequestId();
        msg += reqNBIoTService.getService();
        logger.info(msg);
        */
        logger.info(str);
        return ResponseEntity.ok(new ResMsg());
    }

}

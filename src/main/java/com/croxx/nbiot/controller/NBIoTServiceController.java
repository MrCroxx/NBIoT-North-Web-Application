package com.croxx.nbiot.controller;

import com.croxx.nbiot.request.nbiotservice.ReqNBIoTServiceNotify;
import com.croxx.nbiot.response.ResMsg;
import com.croxx.nbiot.service.NBIoTDeviceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity<ResMsg> deviceDataUpdate(@RequestBody ReqNBIoTServiceNotify reqNBIoTServiceNotify) {
        Logger logger = LoggerFactory.getLogger(NBIoTDeviceService.class);
        ObjectMapper mapper = new ObjectMapper();
        String str = "BUG";
        try {
            str = mapper.writeValueAsString(reqNBIoTServiceNotify);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info(str);
        return ResponseEntity.ok(new ResMsg());
    }

}

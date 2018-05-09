package com.croxx.nbiot.controller.v1;

import com.croxx.nbiot.request.nbiotservice.ReqNBIoTService;
import com.croxx.nbiot.request.nbiotservice.ReqNBIoTServiceNotify;
import com.croxx.nbiot.service.NBIoTAuthService;
import com.croxx.nbiot.service.NBIoTDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;


@RestController
@ApiIgnore
@RequestMapping("/debug")
public class DebugController {

    @Autowired
    private NBIoTAuthService nbIoTAuthService;
    @Autowired
    private NBIoTDeviceService nbIoTDeviceService;


    @RequestMapping("/token")
    public String nbiot() {
        return nbIoTAuthService.getAccessToken();
    }

    @RequestMapping("/device/{nodeId}")
    public String device(@PathVariable("nodeId") String nodeId) {
        //return nodeId;
        //return nbIoTDeviceService.registDevice(nodeId);
        return "Abandoned.";
    }


    @RequestMapping(value = "/nbiot/service", method = RequestMethod.POST)
    public ReqNBIoTService nbiot_service_location(@RequestBody ReqNBIoTService reqNBIoTService) {
        return reqNBIoTService;
    }

    @RequestMapping(value = "/nbiot/notify", method = RequestMethod.POST)
    public ReqNBIoTServiceNotify nbiot_service_location(@RequestBody ReqNBIoTServiceNotify reqNBIoTServiceNotify) {
        return reqNBIoTServiceNotify;
    }



    @RequestMapping("/json")
    //@PreAuthorize("hasRole('USER')")
    public Egg json() {
        return new Egg("Croxx", "gg");
    }

    class Egg {
        private String name;
        private String value;

        public Egg() {
        }

        public Egg(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}

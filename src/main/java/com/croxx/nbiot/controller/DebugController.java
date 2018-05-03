package com.croxx.nbiot.controller;

import com.croxx.nbiot.service.NBIoTAuthService;
import com.croxx.nbiot.service.NBIoTDeviceService;
import com.huawei.iotplatform.client.NorthApiClient;
import com.huawei.iotplatform.client.NorthApiException;
import com.huawei.iotplatform.client.dto.AuthOutDTO;
import com.huawei.iotplatform.client.dto.ClientInfo;
import com.huawei.iotplatform.client.invokeapi.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@ApiIgnore
@RequestMapping("/debug")
public class DebugController {

    @Autowired
    private NBIoTAuthService nbIoTAuthService;
    @Autowired
    private NBIoTDeviceService nbIoTDeviceService;


    @RequestMapping("/token")
    public String nbiot(){
        return nbIoTAuthService.getAccessToken();
    }

    @RequestMapping("/device/{nodeId}")
    public String device(@PathVariable("nodeId") String nodeId){
        //return nodeId;
       //return nbIoTDeviceService.registDevice(nodeId);
        return "Abandoned.";
    }

    @RequestMapping("/json")
    //@PreAuthorize("hasRole('USER')")
    public Egg json(){
        return new Egg("Croxx","gg");
    }

    class Egg{
        private String name;
        private String value;

        public Egg(){
        }

        public Egg(String name,String value){
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

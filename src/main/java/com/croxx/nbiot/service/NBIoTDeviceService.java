package com.croxx.nbiot.service;

import com.croxx.nbiot.model.Device;
import com.croxx.nbiot.model.DeviceRepository;
import com.croxx.nbiot.model.User;
import com.huawei.iotplatform.client.NorthApiClient;
import com.huawei.iotplatform.client.NorthApiException;
import com.huawei.iotplatform.client.dto.*;
import com.huawei.iotplatform.client.invokeapi.DeviceManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class NBIoTDeviceService {

    @Autowired
    private NBIoTService nbIoTService;
    @Autowired
    private NBIoTAuthService nbIoTAuthService;
    @Autowired
    private DeviceRepository deviceRepository;

    public Boolean RegistDevice(@NotNull String deviceId, @NotNull User owner) {
        try {
            NorthApiClient northApiClient = nbIoTService.getNorthApiClient();
            DeviceManagement deviceManagement = new DeviceManagement(northApiClient);
            RegDirectDeviceInDTO rddid = new RegDirectDeviceInDTO();
            rddid.setNodeId(deviceId);
            RegDirectDeviceOutDTO rddod = deviceManagement.regDirectDevice(rddid, nbIoTService.getAppId(), nbIoTAuthService.getAccessToken());
            Device addedDevice = new Device(deviceId, owner);
            deviceRepository.save(addedDevice);
            return true;
        } catch (NorthApiException nae) {
            nae.printStackTrace();
            return false;
        }
    }


    public Boolean DeleteDevice(@NotNull Device device,@NotNull User owner){
        try{
            if(!device.getOwner().equals(owner)){
                //权限
                return false;
            }
            NorthApiClient northApiClient = nbIoTService.getNorthApiClient();
            DeviceManagement deviceManagement = new DeviceManagement(northApiClient);
            deviceManagement.deleteDirectDevice(device.getDeviceId(),nbIoTService.getAppId(),nbIoTAuthService.getAccessToken());
            return true;
        }catch (NorthApiException nae){
            nae.printStackTrace();
            return false;
        }
    }



}

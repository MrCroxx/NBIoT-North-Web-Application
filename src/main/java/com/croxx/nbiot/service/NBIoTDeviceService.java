package com.croxx.nbiot.service;

import com.croxx.nbiot.model.Device;
import com.croxx.nbiot.model.DeviceRepository;
import com.croxx.nbiot.model.User;
import com.croxx.nbiot.request.ReqNewDevice;
import com.croxx.nbiot.response.ResDevice;
import com.huawei.iotplatform.client.NorthApiClient;
import com.huawei.iotplatform.client.NorthApiException;
import com.huawei.iotplatform.client.dto.ModifyDeviceInfoInDTO;
import com.huawei.iotplatform.client.dto.RegDirectDeviceInDTO;
import com.huawei.iotplatform.client.dto.RegDirectDeviceOutDTO;
import com.huawei.iotplatform.client.invokeapi.DeviceManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class NBIoTDeviceService {


    public static String REGISTER_DEVICEID_EXISTS = "deviceId exists";
    public static String REGISTER_UNKNOWN_ERROR = "unknown error";

    public static String DELETE_SUCCESS = "success";
    public static String DELETE_DENIED = "denied";
    public static String DELETE_UNKNOWN_ERROR = "unknown error";
    public static String DELETE_DEVICEID_NOT_FOUND = "deviceId not found";


    @Autowired
    private NBIoTService nbIoTService;
    @Autowired
    private NBIoTAuthService nbIoTAuthService;
    @Autowired
    private DeviceRepository deviceRepository;
    @Value("${device.deviceType}")
    private String deviceType;
    @Value("${device.model}")
    private String model;
    @Value("${device.manufacturerId}")
    private String manufacturerId;
    @Value("${device.manufacturerName}")
    private String manufacturerName;
    @Value("${device.protocolType}")
    private String protocolType;

    public String RegistDevice(@NotNull ReqNewDevice reqNewDevice, @NotNull User owner) {
        try {
            String nodeId = reqNewDevice.getNodeId();
            String name = reqNewDevice.getName();
            NorthApiClient northApiClient = nbIoTService.getNorthApiClient();
            DeviceManagement deviceManagement = new DeviceManagement(northApiClient);
            RegDirectDeviceInDTO rddid = new RegDirectDeviceInDTO();
            rddid.setNodeId(nodeId);
            rddid.setVerifyCode(nodeId);
            RegDirectDeviceOutDTO rddod = deviceManagement.regDirectDevice(rddid, nbIoTService.getAppId(), nbIoTAuthService.getAccessToken());
            String deviceId = rddod.getDeviceId();
            if (deviceRepository.findDeviceByDeviceIdAndStatusNot(deviceId, Device.STATUS_ABANDOND) != null) {
                return REGISTER_DEVICEID_EXISTS;
            }
            Device addedDevice = new Device(nodeId, deviceId, owner, name);
            ModifyDeviceInfoInDTO mdiid = new ModifyDeviceInfoInDTO();
            mdiid.setName(name);
            mdiid.setDeviceId(deviceId);
            mdiid.setDeviceType(deviceType);
            mdiid.setModel(model);
            mdiid.setManufacturerId(manufacturerId);
            mdiid.setManufacturerName(manufacturerName);
            mdiid.setProtocolType(protocolType);
            deviceManagement.modifyDeviceInfo(mdiid, nbIoTService.getAppId(), nbIoTAuthService.getAccessToken());
            addedDevice.setDeviceId(rddod.getDeviceId());
            deviceRepository.save(addedDevice);
            return rddod.getDeviceId();
        } catch (NorthApiException nae) {
            nae.printStackTrace();
            return REGISTER_UNKNOWN_ERROR;
        }
    }


    public String DeleteDevice(@NotNull String deviceId, @NotNull User owner) {
        //try {
        Device device = deviceRepository.findDeviceByDeviceIdAndStatusNot(deviceId, Device.STATUS_ABANDOND);
        if (device == null) {
            return DELETE_DEVICEID_NOT_FOUND;
        }
        if (!device.getOwner().equals(owner)) {
            return DELETE_DENIED;
        }
        //NorthApiClient northApiClient = nbIoTService.getNorthApiClient();
        //DeviceManagement deviceManagement = new DeviceManagement(northApiClient);
        //deviceManagement.deleteDirectDevice(device.getDeviceId(), nbIoTService.getAppId(), nbIoTAuthService.getAccessToken());
        device.setStatus(Device.STATUS_ABANDOND);
        deviceRepository.save(device);
        //deviceRepository.delete(device);
        return DELETE_SUCCESS;
        //} catch (NorthApiException nae) {
        //    nae.printStackTrace();
        //    return DELETE_UNKNOWN_ERROR;
        //}
    }

    public List<ResDevice> GetResDevicesByOwner(User owner) {
        List<Device> devices = deviceRepository.findDeviceByOwnerAndStatusNot(owner, Device.STATUS_ABANDOND);
        List<ResDevice> resDevices = new ArrayList<>();
        for (Device device : devices) {
            resDevices.add(new ResDevice(device.getNodeId(), device.getDeviceId(), device.getName()));
        }
        return resDevices;
    }


}

package com.croxx.nbiot.service;

import com.croxx.nbiot.model.Device;
import com.croxx.nbiot.model.DeviceRepository;
import com.croxx.nbiot.model.User;
import com.croxx.nbiot.request.ReqNewDevice;
import com.croxx.nbiot.response.ResDevice;
import com.croxx.nbiot.response.ResMsg;
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
import java.util.Date;
import java.util.List;

@Service
public class NBIoTDeviceService {


    public static String REGISTER_DEVICEID_EXISTS = "deviceId exists";
    public static String REGISTER_UNKNOWN_ERROR = "unknown error";
    public static String REGISTER_SUCCESS = "success";

    public static String DELETE_SUCCESS = "success";
    public static String DELETE_DENIED = "denied";
    public static String DELETE_UNKNOWN_ERROR = "unknown error";
    public static String DELETE_DEVICEID_NOT_FOUND = "deviceId not found";

    public static String UPDATE_SUCCESS = "success";
    public static String UPDATE_DEVICEID_NOT_FOUND = "deviceId not found";

    public static String DETAIL_NOT_FOUND_OR_DENIED = "not found or denied";


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

    public ResMsg<ResDevice> registDevice(@NotNull ReqNewDevice reqNewDevice, @NotNull User owner) {

        String nodeId = reqNewDevice.getNodeId();
        String name = reqNewDevice.getName();
        if (deviceRepository.findDeviceByNodeIdAndStatusNot(nodeId, Device.STATUS_ABANDOND) != null) {
            return new ResMsg<>(REGISTER_DEVICEID_EXISTS);
        }
        if (deviceRepository.findDeviceByNodeIdAndStatus(nodeId, Device.STATUS_ABANDOND) != null) {
            Device device = deviceRepository.findDeviceByNodeIdAndStatus(nodeId, Device.STATUS_ABANDOND);
            device.setStatus(Device.STATUS_ONLINE);
            device.setOwner(owner);
            device.setBaseinfoModifiedTime(new Date());
            deviceRepository.save(device);
            ResDevice resDevice = new ResDevice();
            resDevice.setDeviceId(device.getDeviceId());
            resDevice.setName(name);
            resDevice.setNodeId(nodeId);
            return new ResMsg<ResDevice>(resDevice, REGISTER_SUCCESS);
        }
        try {
            NorthApiClient northApiClient = nbIoTService.getNorthApiClient();
            DeviceManagement deviceManagement = new DeviceManagement(northApiClient);
            RegDirectDeviceInDTO rddid = new RegDirectDeviceInDTO();
            rddid.setNodeId(nodeId);
            rddid.setVerifyCode(nodeId);
            rddid.setTimeout(0);
            RegDirectDeviceOutDTO rddod = deviceManagement.regDirectDevice(rddid, nbIoTService.getAppId(), nbIoTAuthService.getAccessToken());
            String deviceId = rddod.getDeviceId();
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
            addedDevice.setBaseinfoModifiedTime(new Date());
            deviceRepository.save(addedDevice);
            ResDevice resDevice = new ResDevice();
            resDevice.setDeviceId(rddod.getDeviceId());
            resDevice.setName(name);
            resDevice.setNodeId(nodeId);
            resDevice.setBaseinfoModifiedTime(addedDevice.getBaseinfoModifiedTime());
            return new ResMsg<ResDevice>(resDevice, REGISTER_SUCCESS);
        } catch (NorthApiException nae) {
            nae.printStackTrace();
            return new ResMsg<>(REGISTER_UNKNOWN_ERROR);
        }
    }


    public String deleteDevice(@NotNull String deviceId, @NotNull User owner) {
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

    public List<ResDevice> getResDevicesByOwner(@NotNull User owner) {
        List<Device> devices = deviceRepository.findDeviceByOwnerAndStatusNot(owner, Device.STATUS_ABANDOND);
        List<ResDevice> resDevices = new ArrayList<>();
        for (Device device : devices) {
            resDevices.add(new ResDevice(device.getNodeId(), device.getDeviceId(), device.getName(),
                    device.getBatteryLevel(), device.getNetworkQuality(),
                    device.getLocationLongitude(), device.getLocationLatitude(),
                    device.getBaseinfoModifiedTime()));
        }
        return resDevices;
    }

    public ResDevice getResDeviceByDeviceId(@NotNull String deviceId, @NotNull User owner) {
        Device device = deviceRepository.findDeviceByDeviceIdAndStatusNot(deviceId, Device.STATUS_ABANDOND);
        if (device == null) {
            return null;
        }
        if (!device.getOwner().equals(owner)) {
            return null;
        }
        return new ResDevice(device.getNodeId(), device.getDeviceId(), device.getName(),
                device.getBatteryLevel(), device.getNetworkQuality(),
                device.getLocationLongitude(), device.getLocationLatitude(),
                device.getBaseinfoModifiedTime());
    }

    public String updateBatteryByDeviceId(@NotNull String deviceId, @NotNull int level, Date event_time) {
        Device device = deviceRepository.findDeviceByDeviceId(deviceId);
        if (device == null) {
            return UPDATE_DEVICEID_NOT_FOUND;
        }
        device.setBatteryLevel(level);
        device.setBaseinfoModifiedTime((event_time != null) ? (event_time) : (new Date()));
        deviceRepository.save(device);
        return UPDATE_SUCCESS;
    }

    public String updateNetworkByDeviceId(@NotNull String deviceId, @NotNull int quality, Date event_time) {
        Device device = deviceRepository.findDeviceByDeviceId(deviceId);
        if (device == null) {
            return UPDATE_DEVICEID_NOT_FOUND;
        }
        device.setNetworkQuality(quality);
        device.setBaseinfoModifiedTime((event_time != null) ? (event_time) : (new Date()));
        deviceRepository.save(device);
        return UPDATE_SUCCESS;
    }

    public String updateLocationByDeviceId(@NotNull String deviceId, @NotNull float longitude, @NotNull float latitude, Date event_time) {
        Device device = deviceRepository.findDeviceByDeviceId(deviceId);
        if (device == null) {
            return UPDATE_DEVICEID_NOT_FOUND;
        }
        device.setLocationLongitude(longitude);
        device.setLocationLatitude(latitude);
        device.setBaseinfoModifiedTime((event_time != null) ? (event_time) : (new Date()));
        deviceRepository.save(device);
        return UPDATE_SUCCESS;
    }
}

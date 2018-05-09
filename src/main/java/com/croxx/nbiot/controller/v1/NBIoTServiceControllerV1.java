package com.croxx.nbiot.controller.v1;

import com.croxx.nbiot.request.nbiotservice.ReqNBIoTService;
import com.croxx.nbiot.request.nbiotservice.ReqNBIoTServiceNotify;
import com.croxx.nbiot.response.ResMsg;
import com.croxx.nbiot.service.NBIoTAlarmService;
import com.croxx.nbiot.service.NBIoTDeviceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/nbiot")
public class NBIoTServiceControllerV1 {
    @Autowired
    private NBIoTDeviceService nbIoTDeviceService;
    @Autowired
    private NBIoTAlarmService nbIoTAlarmService;

    @ApiOperation(value = "NBIoT平台设备数据变更Callback", notes = "NBIoT平台设备数据变更Callback")
    @RequestMapping(value = "/deviceDataUpdate", method = RequestMethod.POST)
    public ResponseEntity<ResMsg> deviceDataUpdate(@RequestBody ReqNBIoTServiceNotify reqNBIoTServiceNotify) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logger.info(objectMapper.writeValueAsString(reqNBIoTServiceNotify));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            String deviceId = reqNBIoTServiceNotify.getDeviceId();
            if (reqNBIoTServiceNotify.getService() == null) {
                return ResponseEntity.ok(new ResMsg(ResMsg.MSG_NBIOT_PUSH_TEST));
            }
            String serviceType = reqNBIoTServiceNotify.getService().getServiceType();
            String status = null;
            if (serviceType.equals(ReqNBIoTService.SERVICE_TYPE_BATTERY)) {
                status = nbIoTDeviceService.updateBatteryByDeviceId(deviceId, reqNBIoTServiceNotify.getService().getBatteryLevel());
            } else if (serviceType.equals(ReqNBIoTService.SERVICE_TYPE_NETWORK)) {
                status = nbIoTDeviceService.updateNetworkByDeviceId(deviceId, reqNBIoTServiceNotify.getService().getNetworkQuality());
            } else if (serviceType.equals(ReqNBIoTService.SERVICE_TYPE_LOCATION)) {
                status = nbIoTDeviceService.updateLocationByDeviceId(deviceId,
                        reqNBIoTServiceNotify.getService().getLocationLongitude(),
                        reqNBIoTServiceNotify.getService().getLocationLatitude());
            } else if (serviceType.equals(ReqNBIoTService.SERVICE_TYPE_CLICK)) {
                status = nbIoTAlarmService.addNewAlarmByDeviceId(reqNBIoTServiceNotify.getDeviceId(),
                        reqNBIoTServiceNotify.getService().getClickHoldtime(), reqNBIoTServiceNotify.getService().getEventTime());
            }
            return ResponseEntity.ok(new ResMsg(status));
        }

    }

}

package com.croxx.nbiot.service;

import com.croxx.nbiot.model.Alarm;
import com.croxx.nbiot.model.Device;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NBIoTAlarmService {

    private void freshAlarmsDatabaseByDevice(Device device){
        //return null;
    }

    public List<Alarm> GetAlarmsByDevice(Device device){
        return null;
    }

    public Map<Device,List<Alarm>> GetAlarmsByDevices(List<Device> devices){
        return null;
    }


}

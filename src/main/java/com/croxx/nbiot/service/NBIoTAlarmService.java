package com.croxx.nbiot.service;

import com.croxx.nbiot.model.*;
import com.croxx.nbiot.response.ResAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NBIoTAlarmService {

    public static String NEW_SUCCESS = "success";
    public static String NEW_DEVICEID_NOT_FOUND = "deviceId not found";

    public static String HANDLE_SUCCESS = "success";
    public static String HANDLE_ALARMID_NOT_FOUND = "alarmId not found";
    public static String HANDLE_DENIED = "denied";

    public static String QUERY_DEVICEID_NOT_FOUND_OR_DENIED = "deviceId not found or denied";


    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private AlarmRepository alarmRepository;

    public String addNewAlarmByDeviceId(@NotNull String deviceId, @NotNull float holdtime, Date dateOfAlarm) {
        Device device = deviceRepository.findDeviceByDeviceIdAndStatusNot(deviceId, Device.STATUS_ABANDOND);
        if (device == null)
            return NEW_DEVICEID_NOT_FOUND;
        Alarm alarm = new Alarm(device, holdtime, (dateOfAlarm != null) ? (dateOfAlarm) : (new Date()), device.getLocationLongitude(), device.getLocationLatitude());
        if (holdtime > 5) {
            alarm.setRisk(5);
        } else if (holdtime > 2) {
            alarm.setRisk(3);
        } else if (holdtime > 0) {
            alarm.setRisk(1);
        }
        alarmRepository.save(alarm);
        return NEW_SUCCESS;
    }

    public String handleAlarmByAlarmId(@NotNull Long alarm_id, @NotNull User owner, @NotNull int type, @NotNull int risk) {
        Alarm alarm = alarmRepository.findAlarmById(alarm_id);
        if (alarm == null)
            return HANDLE_ALARMID_NOT_FOUND;
        if (!alarm.getDevice().getOwner().equals(owner))
            return HANDLE_DENIED;
        alarm.solve(Alarm.STATUS_SOLVED, type, risk);
        alarmRepository.save(alarm);
        return HANDLE_SUCCESS;
    }

    public List<ResAlarm> getResAlarmsByDeviceIdAndStatus(@NotNull String deviceId, @NotNull User owner, @NotNull int status) {
        Device device = deviceRepository.findDeviceByDeviceIdAndStatusNot(deviceId, Device.STATUS_ABANDOND);
        if (device == null) return null;
        if (!device.getOwner().equals(owner)) return null;
        List<ResAlarm> resAlarms = new ArrayList<>();
        List<Alarm> alarms = (status == Alarm.STATUS_ALL)
                ? alarmRepository.findAlarmsByDeviceOrderByDateOfAlarm(device)
                : alarmRepository.findAlarmsByDeviceAndStatusOrderByDateOfAlarm(device, status);
        for (Alarm alarm : alarms) {
            resAlarms.add(
                    new ResAlarm(alarm.getId(), alarm.getDevice().getDeviceId(), alarm.getHoldtime(),
                            alarm.getType(), alarm.getStatus(), alarm.getRisk(),
                            alarm.getLocationLongitude(), alarm.getLocationLatitude(),
                            alarm.getDateOfAlarm(), alarm.getDateOfClear())
            );
        }
        return resAlarms;
    }

    public List<ResAlarm> getResAlarmsByUserAndStatus(@NotNull User user, @NotNull int status) {
        List<ResAlarm> resAlarms = new ArrayList<>();
        List<Alarm> alarms = (status == Alarm.STATUS_ALL)
                ? alarmRepository.findAlarmsByUser(user)
                : alarmRepository.findAlarmsByUserAndStatus(user, status);
        for (Alarm alarm : alarms) {
            resAlarms.add(
                    new ResAlarm(alarm.getId(), alarm.getDevice().getDeviceId(), alarm.getHoldtime(),
                            alarm.getType(), alarm.getStatus(), alarm.getRisk(),
                            alarm.getLocationLongitude(), alarm.getLocationLatitude(),
                            alarm.getDateOfAlarm(), alarm.getDateOfClear())
            );
        }
        return resAlarms;
    }

    public ResAlarm getResAlarmByAlarmId(@NotNull Long alarmId, @NotNull User owner) {
        Alarm alarm = alarmRepository.findAlarmById(alarmId);
        if (alarm == null) return null;
        if (!alarm.getDevice().getOwner().equals(owner)) return null;
        ResAlarm resAlarm = new ResAlarm(alarm.getId(), alarm.getDevice().getDeviceId(), alarm.getHoldtime(),
                alarm.getType(), alarm.getStatus(), alarm.getRisk(),
                alarm.getLocationLongitude(), alarm.getLocationLatitude(),
                alarm.getDateOfAlarm(), alarm.getDateOfClear());
        return resAlarm;
    }

}

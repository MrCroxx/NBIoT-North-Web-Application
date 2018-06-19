package com.croxx.nbiot.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ResAlarm {

    private Long id;
    private String deviceId;
    private float holdtime;
    private int type;
    private int status;
    private int risk;
    private float locationLatitude;
    private float locationLongitude;
    private Date dateOfAlarm;
    private Date dateOfClear;

    public ResAlarm() {
    }

    public ResAlarm(@NotNull Long id, @NotNull String deviceId,
                    @NotNull float holdtime, @NotNull int type, @NotNull int status, @NotNull int risk,
                    @NotNull float locationLongitude, @NotNull float locationLatitude,
                    @NotNull Date dateOfAlarm, @NotNull Date dateOfClear) {
        this.id = id;
        this.deviceId = deviceId;
        this.holdtime = holdtime;
        this.type = type;
        this.status = status;
        this.risk = risk;
        this.holdtime = holdtime;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.dateOfAlarm = dateOfAlarm;
        this.dateOfClear = dateOfClear;
    }

    /*    Getters & Setters     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public float getHoldtime() {
        return holdtime;
    }

    public void setHoldtime(float holdtime) {
        this.holdtime = holdtime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public float getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(float locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public float getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(float locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getDateOfAlarm() {
        return dateOfAlarm;
    }

    public void setDateOfAlarm(Date dateOfAlarm) {
        this.dateOfAlarm = dateOfAlarm;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getDateOfClear() {
        return dateOfClear;
    }

    public void setDateOfClear(Date dateOfClear) {
        this.dateOfClear = dateOfClear;
    }
}

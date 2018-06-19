package com.croxx.nbiot.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ResDevice {
    private String nodeId;
    private String deviceId;
    private String name;
    private int batteryLevel;
    private int networkQuality;
    private float locationLongitude;
    private float locationLatitude;
    private Date baseinfoModifiedTime;

    public ResDevice() {
    }

    public ResDevice(@NotNull String nodeId, @NotNull String deviceId, @NotNull String name) {
        this.nodeId = nodeId;
        this.deviceId = deviceId;
        this.name = name;
    }

    public ResDevice(@NotNull String nodeId, @NotNull String deviceId, @NotNull String name,
                     @NotNull int batteryLevel, @NotNull int networkQuality,
                     @NotNull float locationLongitude, @NotNull float locationLatitude,
                     @NotNull Date baseinfoModifiedTime) {
        this.nodeId = nodeId;
        this.deviceId = deviceId;
        this.name = name;
        this.batteryLevel = batteryLevel;
        this.networkQuality = networkQuality;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.baseinfoModifiedTime = baseinfoModifiedTime;
    }

    /*    Getters & Setters     */

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getNetworkQuality() {
        return networkQuality;
    }

    public void setNetworkQuality(int networkQuality) {
        this.networkQuality = networkQuality;
    }

    public float getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(float locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public float getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(float locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getBaseinfoModifiedTime() {
        return baseinfoModifiedTime;
    }

    public void setBaseinfoModifiedTime(Date baseinfoModifiedTime) {
        this.baseinfoModifiedTime = baseinfoModifiedTime;
    }
}

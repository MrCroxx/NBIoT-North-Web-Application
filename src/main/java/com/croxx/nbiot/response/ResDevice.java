package com.croxx.nbiot.response;

import javax.validation.constraints.NotNull;

public class ResDevice {
    private String deviceId;
    private String name;

    public ResDevice() {
    }

    public ResDevice(@NotNull String deviceId) {
        this.deviceId = deviceId;
    }

    public ResDevice(@NotNull String deviceId,@NotNull String name) {
        this.deviceId = deviceId;
        this.name = name;
    }

    /*    Getters & Setters     */

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
}

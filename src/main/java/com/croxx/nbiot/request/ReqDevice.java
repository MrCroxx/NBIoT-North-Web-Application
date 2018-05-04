package com.croxx.nbiot.request;

import javax.validation.constraints.NotNull;

public class ReqDevice {
    @NotNull
    private String deviceId;

    public ReqDevice() {
    }

    public ReqDevice(@NotNull String deviceId) {
        this.deviceId = deviceId;
    }

    /*    Getters & Setters     */

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}

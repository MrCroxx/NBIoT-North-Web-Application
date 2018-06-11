package com.croxx.nbiot.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReqDevice {
    @NotNull
    @Size(min = 6,max = 48)
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

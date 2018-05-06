package com.croxx.nbiot.request;

public class ReqNBIoTServiceNotify {
    private String notifyType;
    private String requestId;
    private String deviceId;
    private String gatewayId;
    private ReqNBIoTService service;

    /*    Getters & Setters     */

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public ReqNBIoTService getService() {
        return service;
    }

    public void setService(ReqNBIoTService service) {
        this.service = service;
    }
}

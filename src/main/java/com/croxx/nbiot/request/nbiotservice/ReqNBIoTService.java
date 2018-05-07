package com.croxx.nbiot.request.nbiotservice;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

public class ReqNBIoTService {

    public static String SERVICE_TYPE_LOCATION = "Location";
    public static String SERVICE_TYPE_BATTERY = "Battery";
    public static String SERVICE_TYPE_NETWORK = "Network";
    public static String SERVICE_TYPE_CLICK = "Click";


    private String serviceId;
    private String serviceType;
    private Date eventTime;
    private Map<String, String> data;

    public ReqNBIoTService() {
    }

    public ReqNBIoTService(@NotNull String serviceId, @NotNull String serviceType, @NotNull Date eventTime, @NotNull Map<String, String> data) {
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.eventTime = eventTime;
        this.data = data;
    }

    public float getLocationLongitude() {
        if (serviceType.equals(SERVICE_TYPE_LOCATION)) {
            int intBits = Integer.parseInt(data.get("longitude"));
            return Float.intBitsToFloat(intBits);
        }
        return 0f;
    }

    public float getLocationLatitude() {
        if (serviceType.equals(SERVICE_TYPE_LOCATION)) {
            int intBits = Integer.parseInt(data.get("latitude"));
            return Float.intBitsToFloat(intBits);
        }
        return 0f;
    }

    public int getBatteryLevel() {
        if (serviceType.equals(SERVICE_TYPE_BATTERY)) {
            return Integer.parseInt(data.get("level"));
        }
        return 0;
    }

    public int getNetworkQuality() {
        if (serviceType.equals(SERVICE_TYPE_NETWORK)) {
            return Integer.parseInt(data.get("quality"));
        }
        return 0;
    }


    /*    Getters & Setters     */

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getEventTime() {
        return eventTime;
    }

    @JsonFormat(pattern = "yyyyMMdd'T'HHmmss'Z'", timezone = "GMT+8")
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}

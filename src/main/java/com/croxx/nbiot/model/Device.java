package com.croxx.nbiot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
public class Device {

    public static int STATUS_OFFLINE = 0;
    public static int STATUS_ONLINE = 1;
    public static int STATUS_ABANDOND = -1;


    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    private User owner;
    @Column(nullable = false,unique = true)
    private String nodeId;
    @Column(nullable = false, unique = true)
    private String deviceId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int status;
    @Column
    private int battery;
    @Column
    private int network;
    @Column
    private double latitude;
    @Column
    private double longitude;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "owner")
    private List<Device> devices;

    public Device() {
    }

    public Device(@NotNull String nodeId,@NotNull String deviceId, @NotNull User owner,@NotNull String name) {
        this.nodeId = nodeId;
        this.owner = owner;
        this.name = name;
        this.status = STATUS_OFFLINE;
    }

    /*    Getters & Setters     */

    public Long getId() {
        return id;
    }

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getNetwork() {
        return network;
    }

    public void setNetwork(int network) {
        this.network = network;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

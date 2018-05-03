package com.croxx.nbiot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private User owner;
    @Column(nullable = false)
    private String deviceId;

    public Device() {
    }

    public Device(@NotNull String deviceId, @NotNull User owner) {
        this.owner = owner;
        this.deviceId = deviceId;
    }

    /*    Getters & Setters     */

    public Long getId() {
        return id;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

}

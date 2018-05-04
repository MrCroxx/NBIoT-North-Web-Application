package com.croxx.nbiot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    private User owner;
    @Column(nullable = false, unique = true)
    private String deviceId;
    @Column(nullable = false)
    private String name;

    public Device() {
    }

    public Device(@NotNull String deviceId, @NotNull User owner,@NotNull String name) {
        this.owner = owner;
        this.deviceId = deviceId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

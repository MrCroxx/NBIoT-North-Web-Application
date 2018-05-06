package com.croxx.nbiot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Alarm {

    public static int TYPE_UNKNOWN = 0;

    public static int STATUS_UNSOLVED = 0;
    public static int STATUS_SOLVED = 1;

    public static int RISK_HIGH = 5;
    public static int RISK_MIDDLE = 3;
    public static int RISK_LOW = 1;
    public static int RISK_UNHANDLED = 0;

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "device_id")
    private Device device;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private int status;
    @Column(nullable = false)
    private int risk;
    @Column(nullable = false)
    private Date dateOfAlarm;
    @Column
    private Date dateOfClear;

    public Alarm() {
    }

    public Alarm(@NotNull Device device) {
        this.device = device;
        this.status = STATUS_UNSOLVED;
        this.dateOfAlarm = new Date();
        this.type = TYPE_UNKNOWN;
        this.status = STATUS_UNSOLVED;
        this.risk = RISK_UNHANDLED;
    }

    public void solve(@NotNull int status, @NotNull int type, @NotNull int risk) {
        this.status = status;
        this.type = type;
        this.risk = risk;
        this.dateOfClear = new Date();
    }

    /*    Getters & Setters     */

    public Long getId() {
        return id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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

    public Date getDateOfAlarm() {
        return dateOfAlarm;
    }

    public void setDateOfAlarm(Date dateOfAlarm) {
        this.dateOfAlarm = dateOfAlarm;
    }

    public Date getDateOfClear() {
        return dateOfClear;
    }

    public void setDateOfClear(Date dateOfClear) {
        this.dateOfClear = dateOfClear;
    }
}

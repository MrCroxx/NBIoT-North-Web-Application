package com.croxx.nbiot.request;

import javax.validation.constraints.NotNull;

public class ReqAlarmHandler {
    @NotNull
    private Long id;
    @NotNull
    private int type;
    @NotNull
    private int risk;

    public ReqAlarmHandler() {
    }

    public ReqAlarmHandler(@NotNull Long id, @NotNull int type, @NotNull int risk) {
        this.id = id;
        this.type = type;
        this.risk = risk;
    }

    /*    Getters & Setters     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }
}

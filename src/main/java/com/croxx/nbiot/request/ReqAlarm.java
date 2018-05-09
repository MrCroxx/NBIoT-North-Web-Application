package com.croxx.nbiot.request;

import javax.validation.constraints.NotNull;

public class ReqAlarm {
    @NotNull
    private Long id;

    public ReqAlarm() {
    }

    public ReqAlarm(@NotNull Long id) {
        this.id = id;
    }
    /*    Getters & Setters     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

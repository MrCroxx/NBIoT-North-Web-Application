package com.croxx.nbiot.test.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReqAlarmHandlerTest {
    private String id;
    private String type;
    private String risk;

    public ReqAlarmHandlerTest() {
    }

    /*    Getters & Setters     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}

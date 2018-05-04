package com.croxx.nbiot.request;

import javax.validation.constraints.NotNull;

public class ReqNewDevice {
    @NotNull
    private String nodeId;
    @NotNull
    private String name;

    public ReqNewDevice() {
    }

    public ReqNewDevice(@NotNull String nodeId,@NotNull String name) {
        this.nodeId = nodeId;
        this.name = name;
    }

    /*    Getters & Setters     */

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

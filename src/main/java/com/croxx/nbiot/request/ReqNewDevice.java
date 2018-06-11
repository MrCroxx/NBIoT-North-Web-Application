package com.croxx.nbiot.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReqNewDevice {
    @NotEmpty
    @Size(min = 6, max = 32)
    private String nodeId;
    @NotEmpty
    @Size(min = 6, max = 32)
    private String name;

    public ReqNewDevice() {
    }

    public ReqNewDevice(@NotNull String nodeId, @NotNull String name) {
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

package com.croxx.nbiot.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    public Device findDeviceByDeviceId(String deviceId);
    public Device findDeviceByNodeId(String nodeId);
}

package com.croxx.nbiot.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    public Device findDeviceByDeviceIdAndStatusNot(String deviceId,int status);
    public Device findDeviceByDeviceIdAndStatus(String deviceId,int status);
    public Device findDeviceByNodeIdAndStatusNot(String nodeId,int status);
    public List<Device> findDeviceByOwnerAndStatusNot(User owner,int status);

    public Device findDeviceByDeviceId(String deviceId);
}

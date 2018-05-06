package com.croxx.nbiot.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    public Alarm findAlarmByDevice(Device device);
}

package com.croxx.nbiot.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    public Alarm findAlarmById(Long id);

    public List<Alarm> findAlarmsByDeviceAndStatusOrderByDateOfAlarm(Device device, int status);

    @Query("SELECT a FROM  Alarm a JOIN a.device d WHERE d.owner=:user AND a.status=:status ORDER BY a.dateOfAlarm DESC")
    public List<Alarm> findAlarmsByUserAndStatus(@Param("user") User user, @Param("status") int status);

    public List<Alarm> findAlarmsByDeviceOrderByDateOfAlarm(Device device);

    @Query("SELECT a FROM  Alarm a JOIN a.device d WHERE d.owner=:user ORDER BY a.dateOfAlarm DESC")
    public List<Alarm> findAlarmsByUser(@Param("user") User user);
}

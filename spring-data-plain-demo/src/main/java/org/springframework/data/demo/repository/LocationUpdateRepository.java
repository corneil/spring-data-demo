package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.DeviceInfo;
import org.springframework.data.demo.data.LocationUpdate;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LocationUpdateRepository extends CrudRepository<LocationUpdate, String> {
    List<LocationUpdate> findByDeviceAndLocTimeBetween(DeviceInfo deviceInfo, Date startDate, Date endDate);
}

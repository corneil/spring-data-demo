package org.springframework.data.neo4j.demo.repository;

import org.springframework.data.neo4j.demo.data.DeviceInfo;
import org.springframework.data.neo4j.demo.data.LocationUpdate;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.Date;
import java.util.List;

public interface LocationUpdateRepository extends GraphRepository<LocationUpdate> {
    List<LocationUpdate> findByDeviceAndLocTimeGreaterThanAndLocTimeLessThan(DeviceInfo deviceInfo, Date startDate, Date endDate);
}

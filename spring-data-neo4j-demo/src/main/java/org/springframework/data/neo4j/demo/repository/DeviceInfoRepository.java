package org.springframework.data.neo4j.demo.repository;

import org.springframework.data.neo4j.demo.data.DeviceInfo;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface DeviceInfoRepository extends GraphRepository<DeviceInfo> {
    DeviceInfo findByDeviceId(String deviceId);
}

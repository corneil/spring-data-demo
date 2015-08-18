package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.DeviceInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DeviceInfoRepository extends CrudRepository<DeviceInfo, String> {
    DeviceInfo findByDeviceId(String deviceId);
}

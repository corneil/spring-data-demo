package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.demo.data.DeviceInfo;
import org.springframework.data.repository.CrudRepository;
@ViewIndexed(designDoc = "deviceInfo", viewName = "all")
public interface DeviceInfoRepository extends CrudRepository<DeviceInfo, String> {
    DeviceInfo findByDeviceId(String deviceId);
}

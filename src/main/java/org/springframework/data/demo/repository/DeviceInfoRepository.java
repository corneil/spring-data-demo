package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.DeviceInfo;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Corneil du Plessis
 */
public interface DeviceInfoRepository extends QueryDslPredicateExecutor<DeviceInfo>, CrudRepository<DeviceInfo, String> {
    DeviceInfo findByDeviceId(String deviceId);
}

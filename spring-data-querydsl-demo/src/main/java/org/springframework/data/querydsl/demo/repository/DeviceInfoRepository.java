package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.DeviceInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Corneil du Plessis
 */
@Repository
public interface DeviceInfoRepository extends QueryDslPredicateExecutor<DeviceInfo>, CrudRepository<DeviceInfo, String> {
    DeviceInfo findByDeviceId(String deviceId);
}

package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.DeviceInfo;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

/**
 * @author Corneil du Plessis
 */
public interface DeviceInfoRepository extends QueryDslPredicateExecutor<DeviceInfo>, CrudRepository<DeviceInfo, BigInteger> {
    DeviceInfo findByDeviceId(String deviceId);
}

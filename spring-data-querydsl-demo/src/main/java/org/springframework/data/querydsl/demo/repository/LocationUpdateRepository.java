package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.DeviceInfo;
import org.springframework.data.querydsl.demo.data.LocationUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by corneil on 3/28/14.
 */
@Repository
public interface LocationUpdateRepository extends QueryDslPredicateExecutor<LocationUpdate>, CrudRepository<LocationUpdate, BigInteger> {
    List<LocationUpdate> findByDeviceAndLocTimeBetween(DeviceInfo deviceInfo, Date startDate, Date endDate);
}

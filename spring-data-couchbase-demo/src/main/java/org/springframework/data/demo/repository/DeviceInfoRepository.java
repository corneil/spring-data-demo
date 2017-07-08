package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.demo.data.DeviceInfo;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "deviceInfo")
public interface DeviceInfoRepository extends CouchbaseRepository<DeviceInfo, String> {
	DeviceInfo findByDeviceId(String deviceId);
}

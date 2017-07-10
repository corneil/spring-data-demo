package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.demo.data.LocationUpdate;

import java.util.Date;
import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "locationUpdate")
public interface LocationUpdateRepository extends CouchbaseRepository<LocationUpdate, String> {
	List<LocationUpdate> findByLocTimeBetweenAndDevice_DeviceId(Date startDate, Date endDate, String deviceId);
}

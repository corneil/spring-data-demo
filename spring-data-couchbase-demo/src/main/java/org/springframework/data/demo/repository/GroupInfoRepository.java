package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.demo.data.GroupInfo;

@ViewIndexed(designDoc = "groupInfo")
public interface GroupInfoRepository extends CouchbaseRepository<GroupInfo, String> {
	GroupInfo findByGroupName(String name);
}

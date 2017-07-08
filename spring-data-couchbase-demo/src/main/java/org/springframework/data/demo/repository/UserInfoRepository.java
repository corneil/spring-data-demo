package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.demo.data.UserInfo;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "userInfo")
public interface UserInfoRepository extends CouchbaseRepository<UserInfo, String> {
	UserInfo findByUserId(String userId);
}

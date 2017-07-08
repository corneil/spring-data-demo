package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.demo.data.UserInfo;
import org.springframework.data.repository.CrudRepository;

@ViewIndexed(designDoc = "user", viewName = "all")
public interface UserRepository extends CrudRepository<UserInfo, String> {
    public UserInfo findByUserId(String userId);
}

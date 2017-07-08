package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.demo.data.GroupInfo;
import org.springframework.data.repository.CrudRepository;

@ViewIndexed(designDoc = "group", viewName = "all")
public interface GroupRepository extends CrudRepository<GroupInfo, String> {
    GroupInfo findByGroupName(String name);
}

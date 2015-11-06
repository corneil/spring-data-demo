package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.GroupInfo;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupInfo, String> {
    GroupInfo findByGroupName(String name);
}

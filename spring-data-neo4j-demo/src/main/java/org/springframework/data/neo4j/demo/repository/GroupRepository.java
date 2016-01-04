package org.springframework.data.neo4j.demo.repository;

import org.springframework.data.neo4j.demo.data.GroupInfo;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface GroupRepository extends GraphRepository<GroupInfo> {
    GroupInfo findByGroupName(String name);
}

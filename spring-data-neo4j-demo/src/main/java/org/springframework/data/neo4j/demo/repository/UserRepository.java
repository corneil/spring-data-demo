package org.springframework.data.neo4j.demo.repository;

import org.springframework.data.neo4j.demo.data.UserInfo;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<UserInfo> {
    UserInfo findByUserId(String userId);
}

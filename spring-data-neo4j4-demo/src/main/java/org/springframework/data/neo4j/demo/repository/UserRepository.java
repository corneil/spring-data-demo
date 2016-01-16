package org.springframework.data.neo4j.demo.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.demo.data.UserInfo;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<UserInfo> {
    UserInfo findByUserId(String userId);
    @Query("MATCH (user:UserInfo) WHERE user.userId={0} RETURN user")
    UserInfo findUser(String userId);
}

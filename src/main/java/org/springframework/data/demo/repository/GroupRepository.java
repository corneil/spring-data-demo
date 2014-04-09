package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.GroupInfo;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends QueryDslPredicateExecutor<GroupInfo>, CrudRepository<GroupInfo, String> {
    GroupInfo findByGroupName(String name);
}

package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.GroupInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface GroupRepository extends QueryDslPredicateExecutor<GroupInfo>, CrudRepository<GroupInfo, BigInteger> {
    GroupInfo findByGroupName(String name);
}

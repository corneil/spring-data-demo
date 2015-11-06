package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.GroupInfo;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface GroupRepository extends QueryDslPredicateExecutor<GroupInfo>, CrudRepository<GroupInfo, BigInteger> {
    GroupInfo findByGroupName(String name);
}

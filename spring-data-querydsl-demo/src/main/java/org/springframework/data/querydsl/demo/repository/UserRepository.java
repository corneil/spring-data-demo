package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UserRepository extends QueryDslPredicateExecutor<UserInfo>, CrudRepository<UserInfo, BigInteger> {
    public UserInfo findByUserId(String userId);
}

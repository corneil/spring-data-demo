package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends QueryDslPredicateExecutor<UserInfo>, CrudRepository<UserInfo, BigInteger> {
    public UserInfo findByUserId(String userId);
}

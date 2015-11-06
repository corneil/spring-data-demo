package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.GroupMember;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface GroupMemberRepository extends QueryDslPredicateExecutor<GroupMember>, CrudRepository<GroupMember, BigInteger> {
}

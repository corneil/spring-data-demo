package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.GroupMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface GroupMemberRepository extends QueryDslPredicateExecutor<GroupMember>, CrudRepository<GroupMember, BigInteger> {
}

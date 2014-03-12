package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.GroupMember;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupMemberRepository extends QueryDslPredicateExecutor<GroupMember>, CrudRepository<GroupMember, String> {
}

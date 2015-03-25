package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.GroupMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupMemberRepository extends QueryDslPredicateExecutor<GroupMember>, CrudRepository<GroupMember, String> {
    public List<GroupMember> findByMemberOfgroupGroupNameAndEnabledTrueOrderByMemberUserIdDesc(String groupName);

    public List<GroupMember> findByMemberUserIdAndEnabledTrue(String userId);

    public List<GroupMember> findByMemberOfgroupGroupName(String groupName);
}

package org.springframework.data.neo4j.demo.repository;

import org.springframework.data.neo4j.demo.data.GroupInfo;
import org.springframework.data.neo4j.demo.data.GroupMember;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface GroupMemberRepository extends GraphRepository<GroupMember> {
    List<GroupMember> findByMemberOfgroup(GroupInfo group);
    List<GroupMember> findByMemberOfgroupAndEnabledTrue(GroupInfo group);
    List<GroupMember> findByMemberUserIdAndEnabledTrue(String userId);
}

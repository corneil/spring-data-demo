package org.springframework.data.neo4j.demo.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.demo.data.GroupInfo;
import org.springframework.data.neo4j.demo.data.GroupMember;
import org.springframework.data.neo4j.demo.data.UserInfo;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface GroupMemberRepository extends GraphRepository<GroupMember> {
    @Query("MATCH (user:UserInfo)-[member:GROUPMEMBER]->(group:GroupInfo) WHERE group.groupName={0} AND member.enabled = true RETURN user")
    List<UserInfo> findActiveMembersOfGroup(String group);
    @Query("MATCH (user:UserInfo)-[member:GROUPMEMBER]->(group:GroupInfo) WHERE group.groupName={0} RETURN user")
    List<UserInfo> findAllMembersOfGroup(String group);
    List<GroupMember> findByEnabledTrueAndMemberOfgroupGroupName(String group);
    List<GroupMember> findByMemberOfgroup(GroupInfo group);
    List<GroupMember> findByMemberOfgroupAndEnabledTrue(GroupInfo group);
    List<GroupMember> findByMemberUserIdAndEnabledTrue(String userId);
    @Query("MATCH (user:UserInfo)-[member:GROUPMEMBER]->(group:GroupInfo) WHERE user.userId={0} AND member.enabled = true RETURN group")
    List<GroupInfo> findGroupsForUser(String userId);
}

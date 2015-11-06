package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.GroupInfo;
import org.springframework.data.demo.data.GroupMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupMemberRepository extends CrudRepository<GroupMember, String> {
    public List<GroupMember> findByMemberOfgroup(GroupInfo group);

    public List<GroupMember> findByMemberOfgroupAndEnabledTrue(GroupInfo group);

    public List<GroupMember> findByMemberUserIdAndEnabledTrue(String userId);
}

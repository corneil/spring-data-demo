package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.GroupMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupMemberRepository extends CrudRepository<GroupMember, String> {
    public List<GroupMember> findByMemberOfgroupGroupNameAndEnabledTrueOrderByMemberUserIdDesc(String groupName);

    public List<GroupMember> findByMemberUserIdAndEnabledTrue(String userId);

    public List<GroupMember> findByMemberOfgroupGroupName(String groupName);
}

package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.demo.data.GroupInfo;
import org.springframework.data.demo.data.GroupMember;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "groupMember")
public interface GroupMemberRepository extends CouchbaseRepository<GroupMember, String> {
	List<GroupMember> findByMemberOfgroup(GroupInfo group);
	List<GroupMember> findByMemberOfgroupAndEnabledTrue(GroupInfo group);
	List<GroupMember> findByMemberUserIdAndEnabledTrue(String userId);
}

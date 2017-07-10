package org.springframework.data.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.demo.data.GroupInfo;
import org.springframework.data.demo.data.GroupMember;
import org.springframework.data.demo.data.UserInfo;
import org.springframework.data.demo.repository.GroupInfoRepository;
import org.springframework.data.demo.repository.GroupMemberRepository;
import org.springframework.data.demo.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@Slf4j
public class UserGroupDataServiceImpl implements UserGroupDataService {
	@Autowired
	protected GroupInfoRepository groupInfoRepository;

	@Autowired
	protected GroupMemberRepository memberRepository;

	@Autowired
	protected UserInfoRepository userInfoRepository;

	@Override
	public void deleteAllData() {
		log.debug("deleteAllData-start");
		memberRepository.deleteAll();
		groupInfoRepository.deleteAll();
		userInfoRepository.deleteAll();
		log.debug("deleteAllData-complete");
	}

	@Override
	public void deleteGroupInfo(GroupInfo groupInfo) {
		log.debug("deleteGroupInfo:{}", groupInfo);
		groupInfoRepository.delete(groupInfo);
	}

	@Override
	public void deleteGroupMember(GroupMember groupMember) {
		log.debug("deleteGroupMember:{}", groupMember);
		memberRepository.delete(groupMember);
	}

	@Override
	public void deleteUserInfo(UserInfo userInfo) {
		log.debug("deleteUserInfo:{}", userInfo);
		userInfoRepository.delete(userInfo);
	}

	@Override
	public GroupInfo findGroup(String name) {
		log.debug("findGroup:{}", name);
		return groupInfoRepository.findByGroupName(name);
	}

	@Override
	public UserInfo findUser(String userId) {
		log.debug("findUser:{}", userId);
		return userInfoRepository.findOneByUserId(userId);
	}

	@Override
	public List<UserInfo> listActiveUsersInGroup(String groupName) {
		log.debug("listActiveUsersInGroup:{}", groupName);
		List<UserInfo> userList = new ArrayList<UserInfo>();
		List<GroupMember> members = memberRepository.findByMemberOfgroup_GroupNameAndEnabledTrue(groupName);
		for (GroupMember member : members) {
			userList.add(member.getMember());
		}
		Collections.sort(userList, new Comparator<UserInfo>() {
			@Override
			public int compare(UserInfo o1, UserInfo o2) {
				return -1 * o1.getUserId().compareTo(o2.getUserId());
			}
		});
		return userList;
	}

	@Override
	public List<UserInfo> listAllUsers() {
		List<UserInfo> result = new ArrayList<UserInfo>();
		for (UserInfo info : userInfoRepository.findAll()) {
			result.add(info);
		}
		return result;
	}

	@Override
	public List<UserInfo> listAllUsersInGroup(String groupName) {
		log.debug("listAllUsersInGroup:{}", groupName);
		List<GroupMember> members = memberRepository.findByMemberOfgroup_GroupName(groupName);
		List<UserInfo> users = new ArrayList<UserInfo>();
		for (GroupMember member : members) {
			users.add(member.getMember());
		}
		return users;
	}

	@Override
	public List<GroupInfo> listGroupsForUser(String userId) {
		log.debug("listGroupsForUser:{}", userId);
		List<GroupInfo> groups = new ArrayList<GroupInfo>();
		Iterable<GroupMember> members = memberRepository.findByMember_UserIdAndEnabledTrue(userId);
		for (GroupMember member : members) {
			groups.add(member.getMemberOfgroup());
		}
		return groups;
	}

	@Override
	public void saveGroupInfo(@Valid GroupInfo groupInfo) {
		log.debug("saveGroupInfo:saving:{}", groupInfo);
		groupInfoRepository.save(groupInfo);
		log.debug("saveGroupInfo:saved:{}", groupInfo);
	}

	@Override
	public void saveGroupMember(@Valid GroupMember groupMember) {
		log.debug("saveGroupMember:saving:{}", groupMember);
		memberRepository.save(groupMember);
		log.debug("saveGroupMember:saved:{}", groupMember);
	}

	@Override
	public void saveUserInfo(@Valid UserInfo userInfo) {
		log.debug("saveUserInfo:saving:{}", userInfo);

		userInfoRepository.save(userInfo);
		log.debug("saveUserInfo:saved:{}", userInfo);
	}
}

package org.springframework.data.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.demo.data.GroupInfo;
import org.springframework.data.demo.data.GroupMember;
import org.springframework.data.demo.data.UserInfo;
import org.springframework.data.demo.repository.GroupMemberRepository;
import org.springframework.data.demo.repository.GroupRepository;
import org.springframework.data.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.demo.data.QGroupMember.groupMember;

@Service
@Transactional
@Validated
public class UserGroupDataServiceImpl implements UserGroupDataService {
    private static Logger logger = LoggerFactory.getLogger(UserGroupDataServiceImpl.class);
    @Autowired
    protected GroupRepository groupRepository;

    @Autowired
    protected GroupMemberRepository memberRepository;

    @Autowired
    protected UserRepository userRepository;

    @Override
    @Transactional
    public void deleteAllData() {
        logger.info("deleteAllData-start");
        memberRepository.deleteAll();
        groupRepository.deleteAll();
        userRepository.deleteAll();
        logger.info("deleteAllData-complete");
    }

    @Override
    @Transactional
    public void deleteGroupInfo(GroupInfo groupInfo) {
        logger.info("deleteGroupInfo:" + groupInfo);
        groupRepository.delete(groupInfo);
    }

    @Override
    @Transactional
    public void deleteGroupMember(GroupMember groupMember) {
        logger.info("deleteGroupMember:" + groupMember);
        memberRepository.delete(groupMember);
    }

    @Override
    @Transactional
    public void deleteUserInfo(UserInfo userInfo) {
        logger.info("deleteUserInfo:" + userInfo);
        userRepository.delete(userInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupInfo findGroup(String name) {
        logger.info("findGroup:" + name);
        return groupRepository.findByGroupName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo findUser(String userId) {
        logger.info("findUser:" + userId);
        return userRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInfo> listActiveUsersInGroup(String groupName) {
        logger.info("listActiveUsersInGroup:" + groupName);
        List<UserInfo> userList = new ArrayList<UserInfo>();

        List<GroupMember> members = memberRepository.findByMemberOfgroupGroupNameAndEnabledTrueOrderByMemberUserIdDesc(groupName);
        for (GroupMember member : members) {
            userList.add(member.getMember());
        }
        return userList;
    }

    @java.lang.Override
    public List<UserInfo> listActiveUsersInGroupDSL(String groupName) {
        logger.info("listActiveUsersInGroupDSL:" + groupName);
        List<UserInfo> userList = new ArrayList<UserInfo>();
        Iterable<GroupMember> members = memberRepository.findAll(groupMember.memberOfgroup.groupName.eq(groupName).and(groupMember.enabled.eq(Boolean.TRUE)),
                groupMember.member.userId.desc());
        for (GroupMember member : members) {
            userList.add(member.getMember());
        }
        return userList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserInfo> listAllUsersInGroup(String groupName) {
        logger.info("listAllUsersInGroup:" + groupName);
        List<UserInfo> users = new ArrayList<UserInfo>();
        List<GroupMember> members = memberRepository.findByMemberOfgroupGroupName(groupName);
        for (GroupMember member : members) {
            users.add(member.getMember());
        }
        return users;
    }

    @java.lang.Override
    public List<UserInfo> listAllUsersInGroupDSL(String groupName) {
        logger.info("listAllUsersInGroupDSL:" + groupName);
        List<UserInfo> users = new ArrayList<UserInfo>();
        Iterable<GroupMember> members = memberRepository.findAll(groupMember.memberOfgroup.groupName.eq(groupName));
        for (GroupMember member : members) {
            users.add(member.getMember());
        }
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupInfo> listGroupsForUser(String userId) {
        logger.info("listGroupsForUser:" + userId);
        List<GroupInfo> groups = new ArrayList<GroupInfo>();
        Iterable<GroupMember> members = memberRepository.findByMemberUserIdAndEnabledTrue(userId);
        for (GroupMember member : members) {
            groups.add(member.getMemberOfgroup());
        }
        return groups;
    }

    @java.lang.Override
    public List<GroupInfo> listGroupsForUserDSL(String userId) {
        logger.info("listGroupsForUserDSL:" + userId);
        List<GroupInfo> groups = new ArrayList<GroupInfo>();
        Iterable<GroupMember> members = memberRepository.findAll(groupMember.member.userId.eq(userId).and(groupMember.enabled.eq(Boolean.TRUE)));
        for (GroupMember member : members) {
            groups.add(member.getMemberOfgroup());
        }
        return groups;
    }

    @Override
    @Transactional
    public void saveGroupInfo(@Valid GroupInfo groupInfo) {
        logger.info("saveGroupInfo:" + groupInfo);
        groupRepository.save(groupInfo);
    }

    @Override
    @Transactional
    public void saveGroupMember(@Valid GroupMember groupMember) {
        logger.info("saveGroupMember:" + groupMember);
        memberRepository.save(groupMember);
    }

    @Override
    @Transactional
    public void saveUserInfo(@Valid UserInfo userInfo) {
        logger.info("saveUserInfo:" + userInfo);
        userRepository.save(userInfo);
    }

    @Override
    public List<UserInfo> listAllUsers() {
        List<UserInfo> result = new ArrayList<UserInfo>();
        for (UserInfo info : userRepository.findAll()) {
            result.add(info);
        }
        return result;
    }
}

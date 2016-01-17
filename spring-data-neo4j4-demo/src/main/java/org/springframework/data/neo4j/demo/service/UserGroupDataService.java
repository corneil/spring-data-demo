package org.springframework.data.neo4j.demo.service;

import org.springframework.data.neo4j.demo.data.GroupInfo;
import org.springframework.data.neo4j.demo.data.GroupMember;
import org.springframework.data.neo4j.demo.data.UserInfo;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;


public interface UserGroupDataService {
    // Added for testing
    @Transactional
    public void deleteAllData();
    @Transactional
    public void deleteGroupInfo(GroupInfo groupInfo);
    @Transactional
    public void deleteGroupMember(GroupMember groupMember);
    @Transactional
    public void deleteUserInfo(UserInfo userInfo);
    @Transactional(readOnly = true)
    public GroupInfo findGroup(String name);
    @Transactional(readOnly = true)
    public UserInfo findUser(String userId);
    @Transactional(readOnly = true)
    public List<UserInfo> listActiveUsersInGroup(String groupName);
    @Transactional(readOnly = true)
    public List<UserInfo> listActiveUsersInGroupFinder(String groupName);
    @Transactional(readOnly = true)
    public List<UserInfo> listAllUsers();
    @Transactional(readOnly = true)
    public List<UserInfo> listAllUsersInGroup(String groupName);
    @Transactional(readOnly = true)
    public List<GroupInfo> listGroupsForUser(String userId);
    @Transactional(readOnly = true)
    public List<GroupInfo> listAllGroups();
    @Transactional
    public void saveGroupInfo(@Valid GroupInfo groupInfo);
    @Transactional
    public void saveGroupMember(@Valid GroupMember groupMember);
    @Transactional
    public void saveUserInfo(@Valid UserInfo userInfo);
}

package org.springframework.data.neo4j.demo.service;

import org.springframework.data.neo4j.demo.data.GroupInfo;
import org.springframework.data.neo4j.demo.data.GroupMember;
import org.springframework.data.neo4j.demo.data.UserInfo;

import javax.validation.Valid;
import java.util.List;

public interface UserGroupDataService {
    // Added for testing
    public void deleteAllData();
    public void deleteGroupInfo(GroupInfo groupInfo);
    public void deleteGroupMember(GroupMember groupMember);
    public void deleteUserInfo(UserInfo userInfo);
    public GroupInfo findGroup(String name);
    public UserInfo findUser(String userId);
    public List<UserInfo> listActiveUsersInGroup(String groupName);
    public List<UserInfo> listActiveUsersInGroupFinder(String groupName);
    public List<UserInfo> listAllUsers();
    public List<UserInfo> listAllUsersInGroup(String groupName);
    public List<GroupInfo> listGroupsForUser(String userId);
    public void saveGroupInfo(@Valid GroupInfo groupInfo);
    public void saveGroupMember(@Valid GroupMember groupMember);
    public void saveUserInfo(@Valid UserInfo userInfo);
}

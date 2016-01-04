package org.springframework.data.neo4j.demo.data;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import javax.validation.constraints.NotNull;

@NodeEntity
public class GroupInfo {
    @NotNull
    @Indexed
    private String groupName;
    @NotNull
    @Fetch
    @RelatedTo(type = "OWNER")
    private UserInfo groupOwner;
    @GraphId
    private Long id;

    public GroupInfo() {
        super();
    }

    public GroupInfo(String groupName, UserInfo groupOwner) {
        super();
        this.groupName = groupName;
        this.groupOwner = groupOwner;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public UserInfo getGroupOwner() {
        return this.groupOwner;
    }

    public void setGroupOwner(UserInfo groupOwner) {
        this.groupOwner = groupOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int result = groupName != null ? groupName.hashCode() : 0;
        result = 31 * result + (groupOwner != null ? groupOwner.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GroupInfo groupInfo = (GroupInfo) o;
        if (groupName != null ? !groupName.equals(groupInfo.groupName) : groupInfo.groupName != null)
            return false;
        return groupOwner != null ? groupOwner.equals(groupInfo.groupOwner) : groupInfo.groupOwner == null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroupInfo{");
        sb.append("groupName='").append(groupName).append('\'');
        sb.append(", groupOwner=").append(groupOwner);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

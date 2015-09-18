package org.springframework.data.querydsl.demo.data;

import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@QueryEntity
@org.springframework.data.mongodb.core.mapping.Document
public class GroupInfo {
    @NotNull
    @Indexed(unique = true)
    @Column(unique = true)
    private String groupName;
    @NotNull
    @ManyToOne
    @DBRef
    private UserInfo groupOwner;
    // Using a string id for use in both MongoDB and JPA.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

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

    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return groupName.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GroupInfo groupInfo = (GroupInfo) o;
        return groupName.equals(groupInfo.groupName);
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

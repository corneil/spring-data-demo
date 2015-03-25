package org.springframework.data.demo.data;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Document(indexName = "groupinfo")
public class GroupInfo {
    @NotNull
    @Indexed(unique = true)
    @Column(unique = true)
    private String groupName;

    @NotNull
    @ManyToOne
    private UserInfo groupOwner;

    // Using a string id for use in both MongoDB and JPA.
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")

    private String id;

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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

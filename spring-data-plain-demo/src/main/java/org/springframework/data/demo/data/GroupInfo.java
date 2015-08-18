package org.springframework.data.demo.data;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
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

    public UserInfo getGroupOwner() {
        return this.groupOwner;
    }

    public String getId() {
        return this.id;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupOwner(UserInfo groupOwner) {
        this.groupOwner = groupOwner;
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

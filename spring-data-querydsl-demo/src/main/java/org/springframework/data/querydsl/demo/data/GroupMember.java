package org.springframework.data.querydsl.demo.data;

import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@QueryEntity
@org.springframework.data.mongodb.core.mapping.Document
public class GroupMember {
    @NotNull
    private Boolean enabled;

    // Using a string id for use in both MongoDB and JPA.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @NotNull
    @ManyToOne
    @DBRef
    private UserInfo member;

    @NotNull
    @ManyToOne
    @DBRef
    private GroupInfo memberOfgroup;

    public GroupMember() {
        super();
    }

    public GroupMember(GroupInfo memberOfgroup, UserInfo member, Boolean enabled) {
        super();
        this.memberOfgroup = memberOfgroup;
        this.member = member;
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public UserInfo getMember() {
        return this.member;
    }

    public void setMember(UserInfo groupMember) {
        this.member = groupMember;
    }

    public GroupInfo getMemberOfgroup() {
        return this.memberOfgroup;
    }

    public void setMemberOfgroup(GroupInfo memberOfgroup) {
        this.memberOfgroup = memberOfgroup;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroupMember{");
        sb.append("enabled=").append(enabled);
        sb.append(", id=").append(id);
        sb.append(", member=").append(member);
        sb.append(", memberOfgroup=").append(memberOfgroup);
        sb.append('}');
        return sb.toString();
    }
}

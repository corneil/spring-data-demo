package org.springframework.data.demo.data;

import com.mysema.query.annotations.QueryEntity;
import java.math.BigInteger;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@QueryEntity
public class GroupMember {
    @NotNull
    private Boolean enabled;

    // Using a string id for use in both MongoDB and JPA.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @NotNull
    @ManyToOne
    private UserInfo member;

    @NotNull
    @ManyToOne
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

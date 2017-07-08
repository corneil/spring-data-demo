package org.springframework.data.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@org.springframework.data.mongodb.core.mapping.Document
@Data
@EqualsAndHashCode(of = {"member", "memberOfgroup"})
public class GroupMember {
    @NotNull
    private Boolean enabled;
    // Using a string id for use in both MongoDB and JPA.
    @Id
    @com.couchbase.client.java.repository.annotation.Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
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
}

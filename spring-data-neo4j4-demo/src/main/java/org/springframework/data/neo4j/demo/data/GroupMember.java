package org.springframework.data.neo4j.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import javax.validation.constraints.NotNull;

@RelationshipEntity(type = "GROUPMEMBER")
@Data
@EqualsAndHashCode(of = {"member", "memberOfgroup"})
public class GroupMember {
    @NotNull
    private Boolean enabled;
    @GraphId
    private Long id;
    @NotNull
    @StartNode
    private UserInfo member;
    @NotNull
    @EndNode
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

package org.springframework.data.neo4j.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.validation.constraints.NotNull;

@NodeEntity
@Data
@EqualsAndHashCode(of = "groupName")
public class GroupInfo {
    @NotNull
    private String groupName;
    @NotNull
    @Relationship(type = "OWNER")
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
}

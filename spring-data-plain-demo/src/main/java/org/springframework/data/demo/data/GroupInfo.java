package org.springframework.data.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@org.springframework.data.mongodb.core.mapping.Document
@Data
@EqualsAndHashCode(of="groupName")
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
}

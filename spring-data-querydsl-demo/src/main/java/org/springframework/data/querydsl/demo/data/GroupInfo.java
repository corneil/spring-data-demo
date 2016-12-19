package org.springframework.data.querydsl.demo.data;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@QueryEntity
@org.springframework.data.mongodb.core.mapping.Document
@Data
@EqualsAndHashCode(of = "groupName")
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
}

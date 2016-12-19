package org.springframework.data.neo4j.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NodeEntity
@Data
@EqualsAndHashCode(of = "userId")
public class UserInfo implements Comparable<UserInfo> {
    @DateTimeFormat(style = "M-")
    private Date dateOfBirth;
    private String emailAddress;
    @NotNull
    private String fullName;
    @GraphId
    private Long id;
    @NotNull
    private String userId;

    public UserInfo() {
        super();
    }

    public UserInfo(String userId, String fullName) {
        super();
        this.userId = userId;
        this.fullName = fullName;
    }

    @Override
    public int compareTo(UserInfo o) {
        int result = 0;
        if (userId != null && o.userId != null) {
            result = userId.compareTo(o.userId);
        }
        return result;
    }
}

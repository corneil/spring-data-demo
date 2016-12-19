package org.springframework.data.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@org.springframework.data.mongodb.core.mapping.Document
@Data
@EqualsAndHashCode(of="userId")
public class UserInfo {
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date dateOfBirth;
    private String emailAddress;
    @NotNull
    private String fullName;
    // Using a string id for use in both MongoDB and JPA.
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @NotNull
    @Column(unique = true)
    @Indexed(unique = true)
    private String userId;

    public UserInfo() {
        super();
    }
    public UserInfo(String userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }
}

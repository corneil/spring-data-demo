package org.springframework.data.querydsl.demo.data;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Entity
@QueryEntity
@org.springframework.data.mongodb.core.mapping.Document
@Data
@EqualsAndHashCode(of = "userId")
public class UserInfo {
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date dateOfBirth;
    private String emailAddress;
    @NotNull
    private String fullName;
    // Using a string id for use in both MongoDB and JPA.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @NotNull
    @Column(unique = true)
    @Indexed(unique = true)
    private String userId;

    public UserInfo() {
    }

    public UserInfo(String userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }
}

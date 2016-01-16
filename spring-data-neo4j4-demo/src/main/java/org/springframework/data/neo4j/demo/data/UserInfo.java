package org.springframework.data.neo4j.demo.data;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NodeEntity
public class UserInfo implements Comparable<UserInfo> {
    @DateTimeFormat(style = "M-")
    @Index
    private Date dateOfBirth;
    @Index
    private String emailAddress;
    @NotNull
    @Index
    private String fullName;
    @GraphId
    private Long id;
    @NotNull
    @Index
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

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserInfo userInfo = (UserInfo) o;
        return userId != null ? userId.equals(userInfo.userId) : userInfo.userId == null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", emailAddress='").append(emailAddress).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

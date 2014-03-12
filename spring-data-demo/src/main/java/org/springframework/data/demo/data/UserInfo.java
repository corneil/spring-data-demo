package org.springframework.data.demo.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import com.mysema.query.annotations.QueryEntity;

@Entity
@QueryEntity
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
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@NotNull
	@Column(unique = true)
	@Indexed(unique = true)
	private String userId;

	@Version
	private Integer version;

	public UserInfo() {
		super();
	}

	public UserInfo(String userId, String fullName) {
		super();
		this.userId = userId;
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public String getFullName() {
		return this.fullName;
	}

	public String getId() {
		return this.id;
	}

	public String getUserId() {
		return this.userId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

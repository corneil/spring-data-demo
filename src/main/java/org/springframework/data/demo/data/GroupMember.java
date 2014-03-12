package org.springframework.data.demo.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.annotations.QueryEntity;

@Entity
@QueryEntity
@Document
public class GroupMember {
	@NotNull
	private Boolean enabled;

	// Using a string id for use in both MongoDB and JPA.
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@NotNull
	@ManyToOne
	private UserInfo member;

	@NotNull
	@ManyToOne
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

	public Boolean getEnabled() {
		return this.enabled;
	}

	public String getId() {
		return this.id;
	}

	public UserInfo getMember() {
		return this.member;
	}

	public GroupInfo getMemberOfgroup() {
		return this.memberOfgroup;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMember(UserInfo groupMember) {
		this.member = groupMember;
	}

	public void setMemberOfgroup(GroupInfo memberOfgroup) {
		this.memberOfgroup = memberOfgroup;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

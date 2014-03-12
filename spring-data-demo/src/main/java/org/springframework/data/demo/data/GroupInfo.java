package org.springframework.data.demo.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;

import com.mysema.query.annotations.QueryEntity;

@Entity
@QueryEntity
public class GroupInfo {
	@NotNull
	@Indexed(unique = true)
	@Column(unique = true)
	private String groupName;

	@NotNull
	@ManyToOne
	private UserInfo groupOwner;

	// Using a string id for use in both MongoDB and JPA.
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	public GroupInfo() {
		super();
	}

	public GroupInfo(String groupName, UserInfo groupOwner) {
		super();
		this.groupName = groupName;
		this.groupOwner = groupOwner;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public UserInfo getGroupOwner() {
		return this.groupOwner;
	}

	public String getId() {
		return this.id;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setGroupOwner(UserInfo groupOwner) {
		this.groupOwner = groupOwner;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

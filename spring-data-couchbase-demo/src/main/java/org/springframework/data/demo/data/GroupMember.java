package org.springframework.data.demo.data;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.mapping.id.IdAttribute;

import javax.validation.constraints.NotNull;

@Document
@Data
@EqualsAndHashCode(of = {"member", "memberOfgroup"})
public class GroupMember {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;

	@NotNull
	@Field
	private Boolean enabled;

	@NotNull
	@Field
	private UserInfo member;

	@NotNull
	@Field
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
}

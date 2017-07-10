package org.springframework.data.demo.data;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.validation.constraints.NotNull;

@Document
@Data
@EqualsAndHashCode(of = "groupName")
public class GroupInfo {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;

	@NotNull
	@Field
	private String groupName;

	@NotNull
	@Field
	private UserInfo groupOwner;

	public GroupInfo() {
		super();
	}

	public GroupInfo(String groupName, UserInfo groupOwner) {
		super();
		this.groupName = groupName;
		this.groupOwner = groupOwner;
	}
}

package org.springframework.data.demo.data;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document
@Data
@EqualsAndHashCode(of = "userId")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;

	@Field
	private Date dateOfBirth;

	@Field
	private String emailAddress;

	@NotNull
	@Field
	private String fullName;

	@NotNull
	@Field
	private String userId;

	public UserInfo() {
		super();
	}

	public UserInfo(String userId, String fullName) {
		this.userId = userId;
		this.fullName = fullName;
	}
}

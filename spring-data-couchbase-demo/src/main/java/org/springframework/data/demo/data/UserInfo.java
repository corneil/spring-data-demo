package org.springframework.data.demo.data;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document
@Data
@EqualsAndHashCode(of = {"userId"})
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	String id;

	@Field
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	Date dateOfBirth;

	@Field
	String emailAddress;

	@NotNull
	@Field
	String fullName;

	@NotNull
	@Field
	String userId;

	public UserInfo() {
		super();
	}

	public UserInfo(String userId, String fullName) {
		this.userId = userId;
		this.fullName = fullName;
	}
}

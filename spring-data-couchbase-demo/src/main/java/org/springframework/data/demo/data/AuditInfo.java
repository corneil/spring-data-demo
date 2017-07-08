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
@EqualsAndHashCode(of = "name")
public class AuditInfo {

	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;

	@Field
	private String afterValue;

	@Field
	private String beforeValue;

	@Field
	@NotNull
	private String name;

	public AuditInfo(String name, String beforeValue, String afterValue) {
		this.name = name;
		this.beforeValue = beforeValue;
		this.afterValue = afterValue;
	}

	public AuditInfo() {
	}

	public AuditInfo(String name, String afterValue) {
		this.name = name;
		this.afterValue = afterValue;
	}
}

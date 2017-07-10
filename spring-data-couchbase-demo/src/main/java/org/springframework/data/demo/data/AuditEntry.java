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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Data
@EqualsAndHashCode(of = {"auditTime", "auditType", "eventType"})
public class AuditEntry {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;

	@Field
	private List<AuditInfo> auditInfo;

	@Field
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date auditTime;

	@NotNull
	@Field
	private String auditType;

	@NotNull
	@Field
	private String eventType;

	public AuditEntry() {
		this.auditInfo = new ArrayList<AuditInfo>();
	}

	public AuditEntry(Date auditTime, String auditType, String eventType) {
		this.auditTime = auditTime;
		this.auditType = auditType;
		this.eventType = eventType;
		this.auditInfo = new ArrayList<AuditInfo>();
	}
}

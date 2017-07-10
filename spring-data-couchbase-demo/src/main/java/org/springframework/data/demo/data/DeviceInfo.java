package org.springframework.data.demo.data;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Data;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.validation.constraints.NotNull;

@Document
@Data
public class DeviceInfo {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;

	@NotNull
	@Field
	private String deviceId;

	@Field
	private String deviceName;
}

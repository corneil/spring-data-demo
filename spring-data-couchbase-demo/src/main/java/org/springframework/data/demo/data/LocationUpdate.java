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
import java.util.Date;

@Document
@Data
@EqualsAndHashCode(of = {"locTime", "device"})
public class LocationUpdate {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;

	@Field
	@NotNull
	private DeviceInfo device;

	@Field
	private double latX;

	@Field
	private double latY;

	@Field
	private String locDetail;

	@NotNull
	@Field
	private Date locTime;
}

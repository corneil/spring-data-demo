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
@EqualsAndHashCode(of = {"locTime", "device"})
public class LocationUpdate {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	String id;

	@Field
	@NotNull
	DeviceInfo device;

	@Field
	double latX;

	@Field
	double latY;

	@Field
	String locDetail;

	@NotNull
	@Field
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	Date locTime;
}

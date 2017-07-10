package org.springframework.data.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(of = "name")
public class AuditInfo {

	private String afterValue;

	private String beforeValue;

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

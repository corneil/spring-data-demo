package org.springframework.data.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(of = "name")
public class AuditInfo {
    private String afterValue;
    private String beforeValue;
    @Id
    @com.couchbase.client.java.repository.annotation.Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Indexed(unique = false)
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

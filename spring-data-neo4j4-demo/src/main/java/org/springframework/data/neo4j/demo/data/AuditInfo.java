package org.springframework.data.neo4j.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.validation.constraints.NotNull;

@NodeEntity
@Data
@EqualsAndHashCode(of = "name")
public class AuditInfo {
    private String afterValue;
    private String beforeValue;
    @GraphId
    private Long id;
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

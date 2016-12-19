package org.springframework.data.neo4j.demo.data;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
@Data
public class AuditEntry {
    @Relationship
    private Set<AuditInfo> auditInfo;
    @NotNull
    private Date auditTime;
    @NotNull
    private String auditType;
    @NotNull
    private String eventType;
    @GraphId
    private Long id;

    public AuditEntry() {
        this.auditInfo = new HashSet<AuditInfo>();
    }

    public AuditEntry(Date auditTime, String auditType, String eventType) {
        this.auditTime = auditTime;
        this.auditType = auditType;
        this.eventType = eventType;
        this.auditInfo = new HashSet<AuditInfo>();
    }

    public void addInfo(AuditInfo info) {
        Assert.notNull(info);
        Assert.notNull(auditInfo);
        auditInfo.add(info);
    }
}

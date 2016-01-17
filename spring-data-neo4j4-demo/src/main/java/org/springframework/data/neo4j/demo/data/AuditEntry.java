package org.springframework.data.neo4j.demo.data;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class AuditEntry {
    
    private Set<AuditInfo> auditInfo;
    @NotNull
    @Index
    private Date auditTime;
    @NotNull
    @Index
    private String auditType;
    @NotNull
    @Index
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

    public Set<AuditInfo> getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(Set<AuditInfo> auditInfo) {
        this.auditInfo = auditInfo;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuditEntry{");
        sb.append("id=").append(id);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", auditType='").append(auditType).append('\'');
        sb.append(", eventType='").append(eventType).append('\'');
        sb.append(", auditInfo=").append(auditInfo);
        sb.append('}');
        return sb.toString();
    }
}

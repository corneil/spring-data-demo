package org.springframework.data.demo.data;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@org.springframework.data.mongodb.core.mapping.Document
public class AuditEntry {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Indexed(unique = false)
    @NotNull
    private Date auditTime;
    @NotNull
    @Indexed(unique = false)
    private String auditType;
    @NotNull
    @Indexed(unique = false)
    private String eventType;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<AuditInfo> auditInfo;

    public AuditEntry() {
        this.auditInfo = new ArrayList<AuditInfo>();
    }

    public AuditEntry(Date auditTime, String auditType, String eventType) {
        this.auditTime = auditTime;
        this.auditType = auditType;
        this.eventType = eventType;
        this.auditInfo = new ArrayList<AuditInfo>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        AuditEntry that = (AuditEntry) o;
        if (!auditTime.equals(that.auditTime)) {
            return false;
        }
        if (!auditType.equals(that.auditType)) {
            return false;
        }
        if ((eventType != null)
                ? !eventType.equals(that.eventType)
                : that.eventType != null) {
            return false;
        }
        return true;
    }

    public List<AuditInfo> getAuditInfo() {
        return auditInfo;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public String getAuditType() {
        return auditType;
    }

    public String getEventType() {
        return eventType;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int result = auditTime.hashCode();
        result = 31 * result + auditType.hashCode();
        result = 31 * result + ((eventType != null)
                ? eventType.hashCode()
                : 0);
        return result;
    }

    public void setAuditInfo(List<AuditInfo> auditInfo) {
        this.auditInfo = auditInfo;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setId(String id) {
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
//~ Formatted by Jindent --- http://www.jindent.com

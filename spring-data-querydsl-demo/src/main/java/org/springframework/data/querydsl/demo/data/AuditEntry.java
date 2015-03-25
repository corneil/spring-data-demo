package org.springframework.data.querydsl.demo.data;

//~--- non-JDK imports --------------------------------------------------------

import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//~--- JDK imports ------------------------------------------------------------

//~--- JDK imports ------------------------------------------------------------

/**
 * Created by corneil on 3/28/14.
 */
@Entity
@QueryEntity
@org.springframework.data.mongodb.core.mapping.Document
public class AuditEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
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

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public List<AuditInfo> getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(List<AuditInfo> auditInfo) {
        this.auditInfo = auditInfo;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
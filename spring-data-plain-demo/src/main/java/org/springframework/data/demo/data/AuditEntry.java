package org.springframework.data.demo.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@org.springframework.data.mongodb.core.mapping.Document
@Data
@EqualsAndHashCode(of = {"auditTime", "auditType", "eventType"})
public class AuditEntry {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AuditInfo> auditInfo;
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
    @Id
    @com.couchbase.client.java.repository.annotation.Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    public AuditEntry() {
        this.auditInfo = new ArrayList<AuditInfo>();
    }

    public AuditEntry(Date auditTime, String auditType, String eventType) {
        this.auditTime = auditTime;
        this.auditType = auditType;
        this.eventType = eventType;
        this.auditInfo = new ArrayList<AuditInfo>();
    }
}

package org.springframework.data.querydsl.demo.data;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@QueryEntity
@Data
@org.springframework.data.mongodb.core.mapping.Document
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

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


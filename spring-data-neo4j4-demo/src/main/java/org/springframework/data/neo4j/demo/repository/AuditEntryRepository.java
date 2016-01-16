package org.springframework.data.neo4j.demo.repository;

import org.springframework.data.neo4j.demo.data.AuditEntry;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.Date;
import java.util.List;

public interface AuditEntryRepository extends GraphRepository<AuditEntry> {
    List<AuditEntry> findByAuditTimeGreaterThanAndAuditTimeLessThanOrderByAuditTimeDesc(Date startDate, Date endDate);
    List<AuditEntry> findByAuditTypeAndAuditTimeGreaterThanAndAuditTimeLessThanOrderByAuditTimeDesc(String auditType, Date startDate, Date endDate);
}

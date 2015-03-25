package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.AuditEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by corneil on 3/28/14.
 */
@Repository
public interface AuditEntryRepository extends CrudRepository<AuditEntry, String> {
    List<AuditEntry> findByAuditTimeBetweenOrderByAuditTimeDesc(Date startDate, Date endDate);

    List<AuditEntry> findByAuditTypeAndAuditTimeBetweenOrderByAuditTimeDesc(String auditType, Date startDate, Date endDate);
}

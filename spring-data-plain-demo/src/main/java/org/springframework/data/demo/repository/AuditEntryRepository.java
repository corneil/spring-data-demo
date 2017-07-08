package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.demo.data.AuditEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

@ViewIndexed(designDoc = "auditEntry", viewName = "all")
public interface AuditEntryRepository extends PagingAndSortingRepository<AuditEntry, String> {
    List<AuditEntry> findByAuditTimeBetweenOrderByAuditTimeDesc(Date startDate, Date endDate);
    Page<AuditEntry> findByAuditTypeAndAuditTimeBetween(String auditType, Date startDate, Date endDate, Pageable pageable);

    List<AuditEntry> findByAuditTypeAndAuditTimeBetweenOrderByAuditTimeDesc(String auditType, Date startDate, Date endDate);
}

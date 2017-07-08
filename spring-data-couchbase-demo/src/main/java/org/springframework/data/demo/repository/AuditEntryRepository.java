package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.data.demo.data.AuditEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "auditEntry")
public interface AuditEntryRepository extends CouchbasePagingAndSortingRepository<AuditEntry, String> {
	List<AuditEntry> findByAuditTimeBetweenOrderByAuditTimeDesc(Date startDate, Date endDate);
	Page<AuditEntry> findByAuditTypeAndAuditTimeBetween(String auditType, Date startDate, Date endDate, Pageable pageable);
	List<AuditEntry> findByAuditTypeAndAuditTimeBetweenOrderByAuditTimeDesc(String auditType, Date startDate, Date endDate);
}

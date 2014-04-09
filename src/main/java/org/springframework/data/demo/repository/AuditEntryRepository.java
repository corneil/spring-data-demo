package org.springframework.data.demo.repository;

import org.springframework.data.demo.data.AuditEntry;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by corneil on 3/28/14.
 */
public interface AuditEntryRepository extends QueryDslPredicateExecutor<AuditEntry>, CrudRepository<AuditEntry, String> {
    List<AuditEntry> findByAuditTimeBetweenOrderByAuditTimeDesc(Date startDate, Date endDate);

    List<AuditEntry> findByAuditTypeAndAuditTimeBetweenOrderByAuditTimeDesc(String auditType, Date startDate, Date endDate);
}

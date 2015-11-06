package org.springframework.data.querydsl.demo.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.demo.data.AuditEntry;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by corneil on 3/28/14.
 */
public interface AuditEntryRepository extends QueryDslPredicateExecutor<AuditEntry>, CrudRepository<AuditEntry, BigInteger> {
    List<AuditEntry> findByAuditTimeBetweenOrderByAuditTimeDesc(Date startDate, Date endDate);

    List<AuditEntry> findByAuditTypeAndAuditTimeBetweenOrderByAuditTimeDesc(String auditType, Date startDate, Date endDate);
}

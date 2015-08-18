package org.springframework.data.querydsl.demo.service;

import org.springframework.data.querydsl.demo.data.AuditEntry;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface AuditService {
    void deleteAllData();

    List<AuditEntry> find(String auditType, Date startDate, Date endDate);

    void save(@Valid AuditEntry entry);
}

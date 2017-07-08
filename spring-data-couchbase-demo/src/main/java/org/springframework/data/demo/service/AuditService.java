package org.springframework.data.demo.service;

import org.springframework.data.demo.data.AuditEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface AuditService {
    void deleteAllData();

    List<AuditEntry> find(String auditType, Date startDate, Date endDate);
    Page<AuditEntry> find(String auditType, Date startDate, Date endDate, Pageable pageable);

    void save(@Valid AuditEntry entry);
}

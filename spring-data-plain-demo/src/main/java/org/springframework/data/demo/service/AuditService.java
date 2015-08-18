package org.springframework.data.demo.service;

import org.springframework.data.demo.data.AuditEntry;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface AuditService {
    void deleteAllData();

    List<AuditEntry> find(String auditType, Date startDate, Date endDate);

    void save(@Valid AuditEntry entry);
}

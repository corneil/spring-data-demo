package org.springframework.data.demo.service;

import org.springframework.data.demo.data.AuditEntry;

import java.util.Date;
import java.util.List;

/**
 * @author Corneil du Plessis
 */
public interface AuditService {
    void save(AuditEntry entry);

    List<AuditEntry> find(String auditType, Date startDate, Date endDate);
}

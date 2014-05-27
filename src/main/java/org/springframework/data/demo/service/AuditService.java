package org.springframework.data.demo.service;

import org.springframework.data.demo.data.AuditEntry;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author Corneil du Plessis
 */
public interface AuditService {

    void deleteAllData();

    void save(@Valid AuditEntry entry);

    List<AuditEntry> find(String auditType, Date startDate, Date endDate);

    List<AuditEntry> findDSL(String auditType, Date startDate, Date endDate);
}

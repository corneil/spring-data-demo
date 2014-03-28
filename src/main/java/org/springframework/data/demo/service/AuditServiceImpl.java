package org.springframework.data.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.demo.data.AuditEntry;
import org.springframework.data.demo.repository.AuditEntryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.demo.data.QAuditEntry.auditEntry;

/**
 * @author Corneil du Plessis
 */
@Service
public class AuditServiceImpl implements AuditService {
    @Autowired
    protected AuditEntryRepository repository;

    @Override
    public void save(AuditEntry entry) {
        repository.save(entry);
    }

    @Override
    public List<AuditEntry> find(String auditType, Date startDate, Date endDate) {

        List<AuditEntry> result = new ArrayList<AuditEntry>();
        Iterable<AuditEntry> resultSet = repository
                .findAll(auditEntry.auditType.eq(auditType).and(auditEntry.auditTime.between(startDate, endDate)), auditEntry.auditTime.desc());
        for (AuditEntry e : resultSet) {
            result.add(e);
        }
        return result;
        // return repository.findByAuditTypeAndAuditTimeBetweenOrderByAuditTimeDesc(auditType, startDate, endDate);
    }
}

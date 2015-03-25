package org.springframework.data.querydsl.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.demo.data.AuditEntry;
import org.springframework.data.querydsl.demo.repository.AuditEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.querydsl.demo.data.QAuditEntry.auditEntry;

/**
 * @author Corneil du Plessis
 */
@Service
@Transactional
@Validated
public class AuditServiceImpl implements AuditService {
    private static Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);

    @Autowired
    protected AuditEntryRepository repository;

    @Override
    @Transactional
    public void deleteAllData() {
        logger.info("deleteAllData");
        repository.deleteAll();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public void save(@Valid AuditEntry entry) {
        logger.info("save:" + entry);
        repository.save(entry);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditEntry> find(String auditType, Date startDate, Date endDate) {
        logger.info("find:" + auditType + ":" + startDate + ":" + endDate);
        List<AuditEntry> result = new ArrayList<AuditEntry>();
        Iterable<AuditEntry> resultSet = repository
                .findAll(auditEntry.auditType.eq(auditType).and(auditEntry.auditTime.between(startDate, endDate)), auditEntry.auditTime.desc());
        for (AuditEntry e : resultSet) {
            result.add(e);
        }
        return result;
    }
}

package org.springframework.data.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.demo.data.AuditEntry;
import org.springframework.data.demo.repository.AuditEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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
        return repository.findByAuditTypeAndAuditTimeBetweenOrderByAuditTimeDesc(auditType, startDate, endDate);
    }

}

package org.springframework.data.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.demo.data.AuditEntry;
import org.springframework.data.demo.repository.AuditEntryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Service

@Validated
@Slf4j
public class AuditServiceImpl implements AuditService {
	@Autowired
	protected AuditEntryRepository repository;

	@Autowired
	protected CouchbaseTemplate couchbaseTemplate;

	@Override

	public void deleteAllData() {
		log.debug("deleteAllData");
		repository.deleteAll();
	}

	@Override
	public List<AuditEntry> find(String auditType, Date startDate, Date endDate) {
		log.debug("find:{}:{}:{}", auditType, startDate, endDate);
		return repository.findByAuditTypeAndAuditTimeBetweenOrderByAuditTimeDesc(auditType, startDate, endDate);
	}

	@Override
	public Page<AuditEntry> find(String auditType, Date startDate, Date endDate, Pageable pageable) {
		return repository.findByAuditTypeAndAuditTimeBetween(auditType, startDate, endDate, pageable);
	}

	@Override
	@SuppressWarnings("unchecked")

	public void save(@Valid AuditEntry entry) {
		log.debug("save:{}", entry);
		repository.save(entry);
	}
}

package org.springframework.data.demo.repository;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.demo.data.AuditInfo;

@ViewIndexed(designDoc = "auditInfo")
public interface AuditInfoRepository extends CouchbaseRepository<AuditInfo, String> {
}

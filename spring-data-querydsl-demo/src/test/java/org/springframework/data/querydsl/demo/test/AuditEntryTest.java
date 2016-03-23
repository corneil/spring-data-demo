package org.springframework.data.querydsl.demo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.querydsl.demo.data.AuditEntry;
import org.springframework.data.querydsl.demo.data.AuditInfo;
import org.springframework.data.querydsl.demo.service.AuditService;
import org.springframework.data.querydsl.demo.test.config.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.*;

/**
 * @author Corneil du Plessis
 */
@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class AuditEntryTest {
    private static Logger logger = LoggerFactory.getLogger("tests");
    @Autowired
    protected AuditService auditService;

    @SuppressWarnings("unchecked")
    private static <T> T findCause(Throwable x, Class<T> type) {
        if (type.isAssignableFrom(x.getClass())) {
            return (T) x;
        }
        if (x.getCause() != null) {
            return findCause(x.getCause(), type);
        }
        return null;
    }

    private void createAuditEntries() {
        assertNotNull(auditService);
        AuditEntry entry = new AuditEntry(new Date(), "User", null);
        try {
            auditService.save(entry);
            fail("Expected constraint violations");
        } catch (Throwable x) {
            ConstraintViolationException cv = findCause(x, ConstraintViolationException.class);
            assertNotNull("Expected ConstraintViolationException:" + x, cv);
            for (ConstraintViolation<?> v : cv.getConstraintViolations()) {
                logger.info("Constraint:" + v);
                logger.info("Violation:" + v.getPropertyPath() + ":" + v.getMessage());
            }
            assertFalse("Expected violations", cv.getConstraintViolations().isEmpty());
        }
        entry = new AuditEntry(new Date(), "User", "create");
        entry.getAuditInfo().add(new AuditInfo("name", "joe"));
        entry.getAuditInfo().add(new AuditInfo("surname", "soap"));
        auditService.save(entry);
        entry = new AuditEntry(new Date(), "User", "update");
        entry.getAuditInfo().add(new AuditInfo("name", "joe", "john"));
        auditService.save(entry);
    }

    @Before
    public void setup() {
        System.setProperty("DEBUG.MONGO", "true");
        System.setProperty("DB.TRACE", "true");
        java.util.logging.Logger mongoLogger = java.util.logging.Logger.getLogger("com.mongodb");
        mongoLogger.setLevel(Level.FINEST); // e.g. or Log.WARNING, etc.
        auditService.deleteAllData();
    }

    @Test
    public void testAuditEntry() {
        long startTime = System.currentTimeMillis();
        createAuditEntries();
        Date startDate = new Date(System.currentTimeMillis() - (1000 * 60 * 60));
        Date endDate = new Date();
        List<AuditEntry> entries = auditService.find("User", startDate, endDate);
        Date prev = null;
        for (AuditEntry e : entries) {
            logger.info("Entry:" + e);
            if (prev != null) {
                assertFalse("Expected:" + prev + " !before " + e.getAuditTime(), prev.before(e.getAuditTime()));
            }
            prev = e.getAuditTime();
        }
        assertFalse(entries.isEmpty());
        long endTime = System.currentTimeMillis();
        double duration = ((double) (endTime - startTime)) / 1000.0;
        logger.info(String.format("Test duration:%9.2f\n", duration));
    }
}

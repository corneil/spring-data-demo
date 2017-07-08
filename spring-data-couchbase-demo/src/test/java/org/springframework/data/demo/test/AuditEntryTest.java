package org.springframework.data.demo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.demo.data.AuditEntry;
import org.springframework.data.demo.data.AuditInfo;
import org.springframework.data.demo.service.AuditService;
import org.springframework.data.demo.test.config.TestConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    private void createAuditEntries(int extra) {
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
        for (int i = 0; i < extra; i++) {
            entry = new AuditEntry(new Date(), "User", "update");
            entry.getAuditInfo().add(new AuditInfo("name", "joe", "john"));
            auditService.save(entry);
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        createAuditEntries(100);

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
        assertEquals(101, entries.size());
        long endTime = System.currentTimeMillis();
        double duration = ((double) (endTime - startTime)) / 1000.0;
        logger.info(String.format("Test duration:%9.2f\n", duration));
    }

    @Test
    public void testAuditEntryPaging() {
        long startTime = System.currentTimeMillis();
        createAuditEntries(100);
        Date startDate = new Date(System.currentTimeMillis() - (1000 * 60 * 60));
        Date endDate = new Date();
        PageRequest pageRequest = new PageRequest(0, 20, Sort.Direction.DESC, "auditTime");
        Page<AuditEntry> entries = auditService.find("User", startDate, endDate, pageRequest);
        long totalEntries = entries.getTotalElements();
        Date prev = null;
        long visited = 0;
        while (true) {
            logger.info("Number={}, Entries={}, Size={}", entries.getNumber(), entries.getNumberOfElements(), entries.getSize());
            for (AuditEntry e : entries.getContent()) {
                visited += 1;
                logger.info("Entry:" + e);
                if (prev != null) {
                    assertFalse("Expected:" + prev + " !before " + e.getAuditTime(), prev.before(e.getAuditTime()));
                }
                prev = e.getAuditTime();
            }
            if (entries.hasNext()) {
                entries = auditService.find("User", startDate, endDate, entries.nextPageable());
            } else {
                break;
            }
        }
        assertFalse(entries.getTotalElements() == 0);
        assertEquals(totalEntries, visited);
        assertEquals(101, visited);
        long endTime = System.currentTimeMillis();
        double duration = ((double) (endTime - startTime)) / 1000.0;
        logger.info(String.format("Test duration:%9.2f\n", duration));
    }
}

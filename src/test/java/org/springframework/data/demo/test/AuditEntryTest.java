package org.springframework.data.demo.test;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.demo.data.AuditEntry;
import org.springframework.data.demo.data.AuditInfo;
import org.springframework.data.demo.service.AuditService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Corneil du Plessis
 */
@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
// @ActiveProfiles(profiles = "jpa-hibernate")
// @ActiveProfiles(profiles = "mongo")
public class AuditEntryTest {
    @Before
    public void setup() {
        System.setProperty("DEBUG.MONGO", "true");
        System.setProperty("DB.TRACE", "true");
        java.util.logging.Logger mongoLogger = java.util.logging.Logger.getLogger("com.mongodb");
        mongoLogger.setLevel(Level.FINEST); // e.g. or Log.WARNING, etc.
    }

    @Autowired
    protected AuditService auditService;

    @Test
    public void testAuditEntry() {
        long startTime = System.currentTimeMillis();
        assertNotNull(auditService);
        AuditEntry entry = new AuditEntry(new Date(), "User", "create");
        entry.getAuditInfo().add(new AuditInfo("name", "joe"));
        entry.getAuditInfo().add(new AuditInfo("surname", "soap"));
        auditService.save(entry);
        entry = new AuditEntry(new Date(), "User", "update");
        entry.getAuditInfo().add(new AuditInfo("name", "joe", "john"));
        auditService.save(entry);
        Date startDate = new Date(System.currentTimeMillis() - (1000 * 60 * 60));
        Date endDate = new Date();
        List<AuditEntry> entries = auditService.find("User", startDate, endDate);
        Date prev = null;
        for (AuditEntry e : entries) {
            System.out.println("Entry:" + e);
            if (prev != null) {
                assertFalse("Expected:" + prev + " !before " + e.getAuditTime(), prev.before(e.getAuditTime()));
            }
            prev = e.getAuditTime();
        }
        assertFalse(entries.isEmpty());
        long endTime = System.currentTimeMillis();
        double duration = ((double) (endTime - startTime)) / 1000.0;
        System.out.printf("Test duration:%9.2f\n", duration);
    }
}

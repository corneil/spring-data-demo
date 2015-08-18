package org.springframework.data.demo.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AuditEntryTest.class, LocationUpdateTest.class,
        UserGroupDataServiceIntegrationTest.class})
public class AllTests {
}

package org.springframework.data.neo4j.demo.test;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.demo.data.GroupInfo;
import org.springframework.data.neo4j.demo.data.GroupMember;
import org.springframework.data.neo4j.demo.data.UserInfo;
import org.springframework.data.neo4j.demo.service.UserGroupDataService;
import org.springframework.data.neo4j.demo.test.config.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class UserGroupDataServiceIntegrationTest {
    private static Logger logger = LoggerFactory.getLogger("tests");
    final Date dob;
    @Autowired
    protected UserGroupDataService dataService;
    @Autowired
    protected Environment env;

    public UserGroupDataServiceIntegrationTest() throws ParseException {
        super();
        dob = makeDate("1977-7-7");
    }

    private Date makeDate(String dateString) throws ParseException {
        return new SimpleDateFormat(DateFormatUtils.ISO_DATE_FORMAT.getPattern()).parse(dateString);
    }

    @Before
    public void deleteData() {
        UserInfo pietPompies = dataService.findUser("piet");
        logger.info("piet=" + pietPompies);
        dataService.deleteAllData();
        if (pietPompies != null) {
            dataService.saveUserInfo(pietPompies);
        }
    }

    @Test
    public void testCreateUsersAndGroups() throws ParseException {
        if (Boolean.getBoolean("test.neo4j.bugs")) {
            long startTime = System.currentTimeMillis();
            try {
                // assertNotNull("Did not find user added with config", dataService.findUser("piet"));
                createUsers();
                // Add Members
                GroupInfo groupOne = dataService.findGroup("groupOne");
                GroupInfo groupTwo = dataService.findGroup("groupTwo");
                // Assertions
                assertNotNull(groupOne);
                assertNotNull(groupTwo);
                UserInfo corneil = dataService.findUser("corneil");
                assertNotNull(corneil);
                UserInfo joe = dataService.findUser("joe");
                assertNotNull(joe);
                dataService.saveGroupMember(new GroupMember(groupOne, corneil, true));
                dataService.saveGroupMember(new GroupMember(groupOne, joe, true));
                dataService.saveGroupMember(new GroupMember(groupTwo, corneil, true));
                // Assertions
                List<UserInfo> usersG1 = dataService.listActiveUsersInGroup("groupOne");
                logger.info("Group1:" + usersG1);
                if (Boolean.getBoolean("test.neo4j.bugs")) {
                    assertEquals(2, usersG1.size());
                    // Test descending
                    Collections.sort(usersG1);
                    assertNotNull(usersG1.get(0).getUserId());
                    assertNotNull(usersG1.get(1).getUserId());
                    assertEquals(corneil.getId(), usersG1.get(0).getId());
                    assertEquals(joe.getId(), usersG1.get(1).getId());
                }
                List<UserInfo> usersG2 = dataService.listActiveUsersInGroup("groupTwo");
                logger.info("Group2:" + usersG2);
                if (Boolean.getBoolean("test.neo4j.bugs")) {
                    assertEquals(1, usersG2.size());
                }
                // Add inactive member
                dataService.saveGroupMember(new GroupMember(groupTwo, joe, false));
                // Assertions
                usersG2 = dataService.listActiveUsersInGroup("groupTwo");
                if (Boolean.getBoolean("test.neo4j.bugs")) {
                    assertEquals(1, usersG2.size());
                }
                usersG2 = dataService.listAllUsersInGroup("groupTwo");
                if (Boolean.getBoolean("test.neo4j.bugs")) {
                    assertEquals(2, usersG2.size());
                }
            } catch (Throwable x) {
                logger.error("testCreateUsersAndGroups:" + x, x);
                fail(x.toString());
            } finally {
                long endTime = System.currentTimeMillis();
                double duration = ((double) (endTime - startTime)) / 1000.0;
                logger.info(String.format("Test duration:%9.2f\n", duration));
            }
        } else {
            logger.warn("testCreateUsersAndGroups:Excluded for bugs");
        }
    }

    private void createUsers() throws ParseException {
        assertNotNull(dataService);
        // Create Users
        if (dataService.listAllUsers().isEmpty()) {
            UserInfo user = new UserInfo("corneil", "Corneil du Plessis");
            user.setEmailAddress("corneil.duplessis@gmail.com");
            user.setDateOfBirth(dob);
            dataService.saveUserInfo(user);
            // Assertions
            assertNotNull(user.getId());
            user = new UserInfo("joe", "Joe Soap");
            user.setDateOfBirth(makeDate("1981-03-04"));
            dataService.saveUserInfo(user);
        }
        if (dataService.listAllGroups().isEmpty()) {
            UserInfo corneil = dataService.findUser("corneil");
            assertNotNull(corneil);
            GroupInfo group = new GroupInfo("groupOne", corneil);
            dataService.saveGroupInfo(group);
            group = new GroupInfo("groupTwo", corneil);
            dataService.saveGroupInfo(group);
        }
    }

    @Test
    public void testUsersAndGroupsFinders() throws ParseException {
        if (Boolean.getBoolean("test.neo4j.bugs")) {
            long startTime = System.currentTimeMillis();
            try {
                // assertNotNull("Did not find user added with config", dataService.findUser("piet"));
                createUsers();
                // Add Members
                GroupInfo groupOne = dataService.findGroup("groupOne");
                GroupInfo groupTwo = dataService.findGroup("groupTwo");
                // Assertions
                assertNotNull(groupOne);
                assertNotNull(groupTwo);
                UserInfo corneil = dataService.findUser("corneil");
                UserInfo joe = dataService.findUser("joe");
                dataService.saveGroupMember(new GroupMember(groupOne, corneil, true));
                dataService.saveGroupMember(new GroupMember(groupOne, joe, true));
                dataService.saveGroupMember(new GroupMember(groupTwo, corneil, true));
                // Assertions
                List<UserInfo> usersG1 = dataService.listActiveUsersInGroupFinder("groupOne");
                logger.info("Group1:" + usersG1);
                assertEquals(2, usersG1.size());
                // Test descending
                Collections.sort(usersG1);
                assertNotNull(usersG1.get(0).getUserId());
                assertNotNull(usersG1.get(1).getUserId());
                assertEquals(corneil.getId(), usersG1.get(0).getId());
                assertEquals(joe.getId(), usersG1.get(1).getId());
                List<UserInfo> usersG2 = dataService.listActiveUsersInGroupFinder("groupTwo");
                logger.info("Group2:" + usersG2);
                assertEquals(1, usersG2.size());
                // Add inactive member
                dataService.saveGroupMember(new GroupMember(groupTwo, joe, false));
                // Assertions
                usersG2 = dataService.listActiveUsersInGroupFinder("groupTwo");
                assertEquals(1, usersG2.size());
            } catch (Throwable x) {
                logger.error("testCreateUsersAndGroups:" + x, x);
                fail(x.toString());
            } finally {
                long endTime = System.currentTimeMillis();
                double duration = ((double) (endTime - startTime)) / 1000.0;
                logger.info(String.format("Test duration:%9.2f\n", duration));
            }
        } else {
            logger.warn("testUsersAndGroupsFinders:Exluded for bugs");
        }
    }
}

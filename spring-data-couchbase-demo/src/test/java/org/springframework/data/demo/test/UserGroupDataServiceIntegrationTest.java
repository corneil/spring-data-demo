package org.springframework.data.demo.test;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.demo.data.GroupInfo;
import org.springframework.data.demo.data.GroupMember;
import org.springframework.data.demo.data.UserInfo;
import org.springframework.data.demo.repository.UserInfoRepository;
import org.springframework.data.demo.service.UserGroupDataService;
import org.springframework.data.demo.test.config.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
/*
 * Configure profile use in test execution by adding spring.profiles.default=mongo|jpa-hibernate to environment
 * @ActiveProfiles(profiles = "mongo")
 * @ActiveProfiles(profiles = "jpa-hibernate")
 * 
 */ public class UserGroupDataServiceIntegrationTest {
	private static Logger logger = LoggerFactory.getLogger("tests");

	final Date dob;

	@Autowired
	protected UserGroupDataService dataService;

	@Autowired
	protected UserInfoRepository userInfoRepository;

	public UserGroupDataServiceIntegrationTest() throws ParseException {
		super();
		dob = makeDate("1977-7-7");
	}

	private void createUsers() throws ParseException {
		assertNotNull(dataService);
		// Create Users
		if (dataService.findUser("corneil") == null) {
			UserInfo user = new UserInfo("corneil", "Corneil du Plessis");
			user.setEmailAddress("corneil.duplessis@gmail.com");
			user.setDateOfBirth(dob);
			dataService.saveUserInfo(user);
			// Assertions
			//assertNotNull(user.getId());
			UserInfo corneil = dataService.findUser("corneil");
			assertNotNull(corneil);
			assertEquals("corneil", corneil.getUserId());
			assertEquals("Corneil du Plessis", corneil.getFullName());
			assertEquals(dob, corneil.getDateOfBirth());
		}
		if (dataService.findUser("joe") == null) {
			UserInfo user = new UserInfo("joe", "Joe Soap");
			user.setDateOfBirth(makeDate("1981-03-04"));
			dataService.saveUserInfo(user);
		}
		// Create Groups
		if (dataService.findGroup("groupOne") == null) {
			UserInfo corneil = dataService.findUser("corneil");
			assertNotNull(corneil);
			GroupInfo group = new GroupInfo("groupOne", corneil);
			dataService.saveGroupInfo(group);
		}
		if (dataService.findGroup("groupTwo") == null) {
			UserInfo corneil = dataService.findUser("corneil");
			assertNotNull(corneil);
			GroupInfo group = new GroupInfo("groupTwo", corneil);
			dataService.saveGroupInfo(group);
		}
	}

	@Before
	public void deleteData() {
		UserInfo pietPompies = dataService.findUser("piet");
		pietPompies.setId(null);
		logger.debug("piet={}", pietPompies);
		dataService.deleteAllData();
		if (pietPompies != null) {
			userInfoRepository.save(pietPompies);
		}
	}

	private Date makeDate(String dateString) throws ParseException {
		return new SimpleDateFormat(DateFormatUtils.ISO_DATE_FORMAT.getPattern()).parse(dateString);
	}

	@Test
	public void testBasicSave() throws ParseException {
		UserInfo user = new UserInfo();
		user.setUserId("joe");
		user.setDateOfBirth(makeDate("1999-11-11"));
		user.setEmailAddress("joe@soap.com");
		user.setFullName("Joe Soap");
		userInfoRepository.save(user);
		assertNotNull(user.getId());
	}
	@Test
	public void testCreateUsersAndGroups() throws ParseException {
		long startTime = System.currentTimeMillis();
		try {
			assertNotNull("Did not find user added with config", dataService.findUser("piet"));
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
			List<UserInfo> usersG1 = dataService.listActiveUsersInGroup("groupOne");
			logger.info("Group1:" + usersG1);
			assertEquals(2, usersG1.size());
			// Test descending
			assertEquals(joe.getId(), usersG1.get(0).getId());
			assertEquals(corneil.getId(), usersG1.get(1).getId());
			List<UserInfo> usersG2 = dataService.listActiveUsersInGroup("groupTwo");
			logger.info("Group2:" + usersG2);
			assertEquals(1, usersG2.size());
			// Add inactive member
			dataService.saveGroupMember(new GroupMember(groupTwo, joe, false));
			// Assertions
			usersG2 = dataService.listActiveUsersInGroup("groupTwo");
			assertEquals(1, usersG2.size());
			usersG2 = dataService.listAllUsersInGroup("groupTwo");
			assertEquals(2, usersG2.size());
			boolean foundPiet = false;
			for (UserInfo info : dataService.listAllUsers()) {
				logger.info(info.toString());
				if ("piet".equals(info.getUserId())) {
					foundPiet = true;
				}
			}
			// TODO find problem with repo load. assertTrue("Did not find user added with config", foundPiet);
		} catch (Throwable x) {
			logger.error("testCreateUsersAndGroups:" + x, x);
			fail(x.toString());
		} finally {
			long endTime = System.currentTimeMillis();
			double duration = ((double) (endTime - startTime)) / 1000.0;
			logger.info(String.format("Test duration:%9.2f\n", duration));
		}
	}
}

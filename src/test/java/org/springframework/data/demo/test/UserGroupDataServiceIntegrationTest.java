package org.springframework.data.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.demo.data.GroupInfo;
import org.springframework.data.demo.data.GroupMember;
import org.springframework.data.demo.data.UserInfo;
import org.springframework.data.demo.service.UserGroupDataService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
/*
 * Configure profile use in test execution by adding spring.profiles.default=mongo|jpa-hibernate to environment
 * @ActiveProfiles(profiles = "mongo")
 * @ActiveProfiles(profiles = "jpa-hibernate")
 * 
 */
public class UserGroupDataServiceIntegrationTest {
	public UserGroupDataServiceIntegrationTest() throws ParseException {
		super();
		dob = makeDate("1965-10-10");
	}

	@Autowired
	protected UserGroupDataService dataService;

	final Date dob;

	private Date makeDate(String dateString) throws ParseException {
		return new SimpleDateFormat(DateFormatUtils.ISO_DATE_FORMAT.getPattern()).parse(dateString);
	}

	@Before
	public void deleteData() {
		dataService.deleteAllData();
	}

	@Test
	public void testCreateUsersAndGroups() throws ParseException {
		assertNotNull(dataService);
		// Create Users
		if (dataService.findUser("corneil") == null) {
			UserInfo user = new UserInfo("corneil", "Corneil du Plessis");
			user.setEmailAddress("corneil.duplessis@gmail.com");
			user.setDateOfBirth(dob);
			dataService.saveUserInfo(user);
			// Assertions
			assertNotNull(user.getId());
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
		System.out.println("Group1:" + usersG1);
		assertEquals(2, usersG1.size());
		// Test descending
		assertEquals(joe.getId(), usersG1.get(0).getId());
		assertEquals(corneil.getId(), usersG1.get(1).getId());
		List<UserInfo> usersG2 = dataService.listActiveUsersInGroup("groupTwo");
		System.out.println("Group2:" + usersG2);
		assertEquals(1, usersG2.size());
		// Add inactive member
		dataService.saveGroupMember(new GroupMember(groupTwo, joe, false));
		// Assertions
		usersG2 = dataService.listActiveUsersInGroup("groupTwo");
		assertEquals(1, usersG2.size());
		usersG2 = dataService.listAllUsersInGroup("groupTwo");
		assertEquals(2, usersG2.size());
	}
}

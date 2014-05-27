package org.springframework.data.demo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.demo.data.DeviceInfo;
import org.springframework.data.demo.data.LocationUpdate;
import org.springframework.data.demo.service.LocationAndDeviceService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author Corneil du Plessis
 */
@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class LocationUpdateTest {
    @Autowired
    protected LocationAndDeviceService locationAndDeviceService;

    @Autowired
    protected Environment environment;

    @Autowired
    protected ApplicationContext applicationContext;

    @Before
    public void setup() {
        locationAndDeviceService.deleteAllData();
    }

    @Test
    public void testLocationUpdates() {
        long startTime = System.currentTimeMillis();
        Date startDate = new Date();
        DeviceInfo device1 = createDeviceAndLocations();
        Date endDate = new Date();
        List<LocationUpdate> locations = locationAndDeviceService.findLocations(device1.getDeviceId(), startDate, endDate);
        for (LocationUpdate loc : locations) {
            System.out.println("Loc:" + loc);
        }
        assertFalse("Expected locations", locations.isEmpty());
        assertEquals(50, locations.size());
        long endTime = System.currentTimeMillis();
        double duration = ((double) (endTime - startTime)) / 1000.0;
        System.out.printf("Test duration:%9.2f\n", duration);
        if (environment.acceptsProfiles("mongo")) {
            testFindMongoTemplate(device1, startDate, endDate, locations.size());
        }
    }

    @Test
    public void testLocationUpdatesDSL() {
        long startTime = System.currentTimeMillis();
        Date startDate = new Date();
        DeviceInfo device1 = createDeviceAndLocations();
        Date endDate = new Date();
        List<LocationUpdate> locations = locationAndDeviceService.findLocationsDSL(device1.getDeviceId(), startDate, endDate);
        for (LocationUpdate loc : locations) {
            System.out.println("Loc:" + loc);
        }
        assertFalse("Expected locations", locations.isEmpty());
        assertEquals(50, locations.size());
        long endTime = System.currentTimeMillis();
        double duration = ((double) (endTime - startTime)) / 1000.0;
        System.out.printf("Test duration:%9.2f\n", duration);
        if (environment.acceptsProfiles("mongo")) {
            testFindMongoTemplate(device1, startDate, endDate, locations.size());
        }
    }

    private DeviceInfo createDeviceAndLocations() {
        Random rand = new Random();
        DeviceInfo device1 = new DeviceInfo();
        device1.setDeviceId("1234-4567-6789");
        device1.setDeviceName("My Device 1");
        locationAndDeviceService.saveDevice(device1);
        DeviceInfo device2 = new DeviceInfo();
        device2.setDeviceId("4567-1234-6789");
        device2.setDeviceName("My Device 2");
        locationAndDeviceService.saveDevice(device2);
        for (int i = 0; i < 100; i++) {
            LocationUpdate loc = new LocationUpdate();
            loc.setDevice(i % 2 == 0 ? device1 : device2);
            loc.setLatX(rand.nextDouble() * 180 - 90);
            loc.setLatY(rand.nextDouble() * 180 - 90);
            loc.setLocDetail("Sample-" + (i + 1));
            loc.setLocTime(new Date());
            locationAndDeviceService.saveLocation(loc);
        }
        return device1;
    }

    private void testFindMongoTemplate(DeviceInfo device, Date startDate, Date endTime, final int expected) {
        try {
            MongoTemplate mongoTemplate = applicationContext.getBean(MongoTemplate.class);
            List<LocationUpdate> locations = mongoTemplate
                    .find(query(where("device").is(device).andOperator(
                            where("locTime").gte(startDate),
                            where("locTime").lte(endTime))), LocationUpdate.class);
            assertFalse("Expected locations", locations.isEmpty());
            assertEquals(expected, locations.size());
        } catch (Throwable x) {
            x.printStackTrace();
            fail(x.toString());
        }
    }
}

package org.springframework.data.neo4j.demo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.demo.data.DeviceInfo;
import org.springframework.data.neo4j.demo.data.LocationUpdate;
import org.springframework.data.neo4j.demo.service.LocationAndDeviceService;
import org.springframework.data.neo4j.demo.test.config.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author Corneil du Plessis
 */
@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class LocationUpdateTest {
    private static Logger logger = LoggerFactory.getLogger("tests");
    @Autowired
    protected ApplicationContext applicationContext;
    @Autowired
    protected Environment environment;
    @Autowired
    protected LocationAndDeviceService locationAndDeviceService;

    @Before
    public void setup() {
        locationAndDeviceService.deleteAllData();
    }

    @Test
    public void testLocationUpdates() {
        if (Boolean.getBoolean("test.neo4j.bugs")) {
            long startTime = System.currentTimeMillis();
            Date startDate = new Date();
            DeviceInfo device1 = createDeviceAndLocations();
            Date endDate = new Date();
            List<LocationUpdate> locations = locationAndDeviceService.findLocations(device1.getDeviceId(), startDate, endDate);
            for (LocationUpdate loc : locations) {
                logger.debug("Loc:" + loc);
            }
            assertFalse("Expected locations", locations.isEmpty());
            //assertEquals(10, locations.size());
            long endTime = System.currentTimeMillis();
            double duration = ((double) (endTime - startTime)) / 1000.0;
            logger.info(String.format("Test duration:%9.2f\n", duration));
        } else {
            logger.warn("testLocationUpdates:Excluded known bug");
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
        for (int i = 0; i < 20; i++) {
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
}

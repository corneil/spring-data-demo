package org.springframework.data.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.demo.data.DeviceInfo;
import org.springframework.data.demo.data.LocationUpdate;
import org.springframework.data.demo.repository.DeviceInfoRepository;
import org.springframework.data.demo.repository.LocationUpdateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.demo.data.QLocationUpdate.locationUpdate;

/**
 * @author Corneil du Plessis
 */
@Service
@Transactional
public class LocationAndDeviceServiceImpl implements LocationAndDeviceService {
    private final static Logger logger = LoggerFactory.getLogger(LocationAndDeviceServiceImpl.class);

    @Autowired
    protected LocationUpdateRepository locationUpdateRepository;

    @Autowired
    protected DeviceInfoRepository deviceInfoRepository;

    @Override
    @Transactional
    public void deleteAllData() {
        logger.info("deleteAllData");
        locationUpdateRepository.deleteAll();
        deviceInfoRepository.deleteAll();
    }

    @Override
    @Transactional
    public void saveDevice(@Valid DeviceInfo device) {
        logger.info("saveDevice:" + device);
        deviceInfoRepository.save(device);
    }

    @Override
    @Transactional(readOnly = true)
    public DeviceInfo findDevice(String deviceId) {
        logger.info("findDevice:" + deviceId);
        return deviceInfoRepository.findByDeviceId(deviceId);
    }

    @Override
    @Transactional
    public void saveLocation(@Valid LocationUpdate locationUpdate) {
        logger.info("saveLocation:" + locationUpdate);
        locationUpdateRepository.save(locationUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationUpdate> findLocations(String deviceId, Date startDate, Date endDate) {
        return findLocationsFunctions(deviceId, startDate, endDate);
        // return findLocationsQsl(deviceId, startDate, endDate);
    }

    private List<LocationUpdate> findLocationsFunctions(String deviceId, Date startDate, Date endDate) {
        logger.info("findLocations:" + deviceId + "," + startDate + "," + endDate);
        DeviceInfo device = deviceInfoRepository.findByDeviceId(deviceId);
        if (device == null) {
            throw new DataRetrievalFailureException("DeviceInfo:" + deviceId);
        }
        return locationUpdateRepository.findByDeviceAndLocTimeBetween(device, startDate, endDate);
    }

    private List<LocationUpdate> findLocationsQsl(String deviceId, Date startDate, Date endDate) {
        logger.info("findLocations:" + deviceId + "," + startDate + "," + endDate);
        DeviceInfo device = deviceInfoRepository.findByDeviceId(deviceId);
        if (device == null) {
            throw new DataRetrievalFailureException("DeviceInfo:" + deviceId);
        }
        List<LocationUpdate> result = new ArrayList<LocationUpdate>();
        Iterable<LocationUpdate> resultSet = locationUpdateRepository
                .findAll(locationUpdate.device.eq(device).and(locationUpdate.locTime.between(startDate, endDate)));
        for (LocationUpdate loc : resultSet) {
            result.add(loc);
        }
        return result;
    }
}

package org.springframework.data.querydsl.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.querydsl.demo.data.DeviceInfo;
import org.springframework.data.querydsl.demo.data.LocationUpdate;
import org.springframework.data.querydsl.demo.repository.DeviceInfoRepository;
import org.springframework.data.querydsl.demo.repository.LocationUpdateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.querydsl.demo.data.QLocationUpdate.locationUpdate;

/**
 * @author Corneil du Plessis
 */
@Service
@Transactional
@Validated
public class LocationAndDeviceServiceImpl implements LocationAndDeviceService {
    private final static Logger logger = LoggerFactory.getLogger(LocationAndDeviceServiceImpl.class);

    @Autowired
    protected LocationUpdateRepository locationUpdateRepository;

    @Autowired
    protected DeviceInfoRepository deviceInfoRepository;

    @Override
    @Transactional
    public void deleteAllData() {
        logger.info("deleteAllData-start");
        locationUpdateRepository.deleteAll();
        deviceInfoRepository.deleteAll();
        logger.info("deleteAllData-complete");
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
        logger.debug("saveLocation:" + locationUpdate);
        locationUpdateRepository.save(locationUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationUpdate> findLocations(String deviceId, Date startDate, Date endDate) {
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

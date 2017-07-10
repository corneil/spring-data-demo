package org.springframework.data.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.demo.data.DeviceInfo;
import org.springframework.data.demo.data.LocationUpdate;
import org.springframework.data.demo.repository.DeviceInfoRepository;
import org.springframework.data.demo.repository.LocationUpdateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Service

@Validated
@Slf4j
public class LocationAndDeviceServiceImpl implements LocationAndDeviceService {
	@Autowired
	protected LocationUpdateRepository locationUpdateRepository;

	@Autowired
	protected DeviceInfoRepository deviceInfoRepository;

	@Override

	public void deleteAllData() {
		log.debug("deleteAllData-start");
		locationUpdateRepository.deleteAll();
		deviceInfoRepository.deleteAll();
		log.debug("deleteAllData-complete");
	}

	@Override
	public DeviceInfo findDevice(String deviceId) {
		log.debug("findDevice:{}", deviceId);
		return deviceInfoRepository.findByDeviceId(deviceId);
	}

	@Override

	public List<LocationUpdate> findLocations(String deviceId, Date startDate, Date endDate) {
		log.debug("findLocations:{}:{}:{}", deviceId, startDate, endDate);
		return locationUpdateRepository.findByLocTimeBetweenAndDevice_DeviceId(startDate, endDate, deviceId);
	}

	@Override

	public void saveDevice(@Valid DeviceInfo device) {
		log.debug("saveDevice:{}", device);
		deviceInfoRepository.save(device);
	}

	@Override

	public void saveLocation(@Valid LocationUpdate locationUpdate) {
		log.debug("saveLocation:{}", locationUpdate);
		locationUpdateRepository.save(locationUpdate);
	}
}

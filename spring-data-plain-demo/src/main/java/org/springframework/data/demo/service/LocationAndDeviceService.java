package org.springframework.data.demo.service;

import org.springframework.data.demo.data.DeviceInfo;
import org.springframework.data.demo.data.LocationUpdate;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface LocationAndDeviceService {
    void deleteAllData();

    DeviceInfo findDevice(String deviceId);

    List<LocationUpdate> findLocations(String deviceId, Date startDate, Date endDate);

    void saveDevice(@Valid DeviceInfo device);

    void saveLocation(@Valid LocationUpdate locationUpdate);
}

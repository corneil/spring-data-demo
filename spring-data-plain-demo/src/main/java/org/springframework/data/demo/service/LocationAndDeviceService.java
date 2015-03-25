package org.springframework.data.demo.service;

import org.springframework.data.demo.data.DeviceInfo;
import org.springframework.data.demo.data.LocationUpdate;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author Corneil du Plessis
 */
public interface LocationAndDeviceService {
    void deleteAllData();

    void saveDevice(@Valid DeviceInfo device);

    DeviceInfo findDevice(String deviceId);

    void saveLocation(@Valid LocationUpdate locationUpdate);

    List<LocationUpdate> findLocations(String deviceId, Date startDate, Date endDate);


}

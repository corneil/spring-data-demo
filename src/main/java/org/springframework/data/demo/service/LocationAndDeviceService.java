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
    public void deleteAllData();

    public void saveDevice(@Valid DeviceInfo device);

    public DeviceInfo findDevice(String deviceId);

    public void saveLocation(@Valid LocationUpdate locationUpdate);

    public List<LocationUpdate> findLocations(String deviceId, Date startDate, Date endDate);
}

package org.springframework.data.demo.service;

import org.springframework.data.demo.data.DeviceInfo;
import org.springframework.data.demo.data.LocationUpdate;

import java.util.Date;
import java.util.List;

/**
 * Created by corneil on 3/28/14.
 */
public interface LocationAndDeviceService {
    public void deleteAllData();

    public void saveDevice(DeviceInfo device);

    public DeviceInfo findDevice(String deviceId);

    public void saveLocation(LocationUpdate locationUpdate);

    public List<LocationUpdate> findLocations(String deviceId, Date startDate, Date endDate);
}

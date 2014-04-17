package org.springframework.data.demo.data;

import com.mysema.query.annotations.QueryEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Corneil du Plessis
 */
@Entity
@QueryEntity
public class DeviceInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 40)
    private String id;

    @NotNull
    @Indexed(unique = true)
    @Column(unique = true)
    private String deviceId;

    private String deviceName;

    @Override
    public int hashCode() {
        return deviceId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceInfo that = (DeviceInfo) o;

        if (!deviceId.equals(that.deviceId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceInfo{");
        sb.append("id='").append(id).append('\'');
        sb.append(", deviceId='").append(deviceId).append('\'');
        sb.append(", deviceName='").append(deviceName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Object getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}

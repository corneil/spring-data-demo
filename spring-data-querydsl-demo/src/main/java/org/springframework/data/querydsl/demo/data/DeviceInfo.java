package org.springframework.data.querydsl.demo.data;

import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@QueryEntity
@org.springframework.data.mongodb.core.mapping.Document
public class DeviceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @NotNull
    @Indexed(unique = true)
    @Column(unique = true)
    private String deviceId;
    private String deviceName;

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

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public BigInteger getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return deviceId.hashCode();
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceInfo{");
        sb.append("id=").append(id);
        sb.append(", deviceId='").append(deviceId).append('\'');
        sb.append(", deviceName='").append(deviceName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package org.springframework.data.neo4j.demo.data;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NodeEntity
public class LocationUpdate {
    @NotNull
    @Fetch
    @RelatedTo
    private DeviceInfo device;
    @GraphId
    private Long id;
    private double latX;
    private double latY;
    private String locDetail;
    @NotNull
    @Indexed
    private Date locTime;

    public DeviceInfo getDevice() {
        return device;
    }

    public void setDevice(DeviceInfo device) {
        this.device = device;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatX() {
        return latX;
    }

    public void setLatX(double latX) {
        this.latX = latX;
    }

    public double getLatY() {
        return latY;
    }

    public void setLatY(double latY) {
        this.latY = latY;
    }

    public String getLocDetail() {
        return locDetail;
    }

    public void setLocDetail(String locDetail) {
        this.locDetail = locDetail;
    }

    public Date getLocTime() {
        return locTime;
    }

    public void setLocTime(Date locTime) {
        this.locTime = locTime;
    }

    @Override
    public int hashCode() {
        int result = locTime.hashCode();
        result = 31 * result + device.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LocationUpdate that = (LocationUpdate) o;
        if (!locTime.equals(that.locTime))
            return false;
        return device.equals(that.device);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocationUpdate{");
        sb.append("id=").append(id);
        sb.append(", latX=").append(latX);
        sb.append(", latY=").append(latY);
        sb.append(", locDetail='").append(locDetail).append('\'');
        sb.append(", locTime=").append(locTime);
        sb.append(", device=").append(device);
        sb.append('}');
        return sb.toString();
    }
}
